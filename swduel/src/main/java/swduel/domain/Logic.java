package swduel.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import swduel.domain.ammunition.Ammunition;
import swduel.domain.weapon.AutoBlaster;
import swduel.domain.weapon.BlasterPistol;
import swduel.domain.weapon.LongshotBlaster;
import swduel.domain.weapon.CrappySword;
import swduel.domain.weapon.LaserWall;
import swduel.domain.weapon.Lightsaber;
import swduel.domain.weapon.Weapon;
import swduel.ui.AudioHandler;

/**
 * Luokka huolehtii pelin sisäisestä toiminnallisuudesta.
 *
 */
public class Logic {

    private Arena arena;
    private List<Player> players;
    private SpawnHandler spawner;
    private WallCollisionHandler wallCollisionHandler;
    private TreeSet<Weapon> weapons;
    private List<Ammunition> shotsFired;
    private boolean gameFinished;
    private AudioHandler audioHandler;
    private List<Smoke> jetpackSmoke;

    /**
     * Konstruktorille syötetään areenan nimi.
     *
     * @param arenaFile Areenan nimi
     */
    public Logic(String arenaFile) {
        this.arena = new Arena(arenaFile);
        players = new ArrayList<>();
        spawner = new SpawnHandler(players, arena);
        wallCollisionHandler = new WallCollisionHandler(arena);

        weapons = generateWeapons();
        audioHandler = new AudioHandler();
        generateAudio(audioHandler);

        players.add(new Player(0));
        players.add(new Player(1));
        players.get(0).setFacing(1);
        for (Player player : players) {
            spawner.randomPlayerLocation(player);
            setWeapon(player, weapons.first().getName());
        }
        shotsFired = new ArrayList<>();
        jetpackSmoke = new ArrayList<>();
    }

    public Arena getArena() {
        return this.arena;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public List<Smoke> getSmoke() {
        return this.jetpackSmoke;
    }

    /**
     * Metodi päivittää pelaajien sijaintia, hidastaa liikettä, tarkastaa onko
     * pelaajat seinän sisällä, tarkastaa painovoiman vaikutukset sekä päivittää
     * ammusten sijaintia.
     *
     * @see swduel.domain.Player#update(double)
     * swduel.domain.WallCollisionHandler#checkIfInsideWall(swduel.domain.Player)
     * @param time Aika joka on kulunut edellisestä päivityksestä
     */
    public void updateAll(double time) {
        for (Player player : players) {
            player.update(time);
            addSmoke(player);
            slowDown(player);
            wallCollisionHandler.checkIfInsideWall(player);
            gravity(player);
        }
        updateAmmoPosition(time);
        updateSmoke(time);
    }

    /**
     * Metodi tarkastaa voiko pelaaja hyökätä. Jos voi, luodaan ammus ja
     * lisätään aseeseen cooldownia. Lisäksi metodi soittaa aseen ampumisäänen.
     *
     * @param player Pelaaja joka yrittää ampua.
     */
    public void attack(Player player) {
        if (player.getWeapon().getCooldown() > 0) {
            return;
        }
        createAmmunition(player);
        player.getWeapon().addCooldown();
        audioHandler.playSound(player.getWeapon().getSoundEffectName());
    }

    public boolean getGameFinished() {
        return gameFinished;
    }

    public List<Ammunition> getAmmunition() {
        return this.shotsFired;
    }

    private void addSmoke(Player player) {
        if (player.isUsingJetpack()) {
            int smokeX = 0;
            int smokeY = 20;
            int x = player.getX() + smokeX;
            if (player.getFacing() == 0) {
                x = player.getX() + player.getWidth() - smokeX - 10;
            }
            int y = player.getY() - smokeY;
            Smoke smoke = new Smoke(x, y);
            jetpackSmoke.add(smoke);
        }
    }

    private void updateSmoke(double time) {
        Iterator<Smoke> it = jetpackSmoke.iterator();
        while (it.hasNext()) {
            if (gameFinished) {
                return;
            }
            Smoke smoke = it.next();
            if (smoke.getAliveTime() < 0) {
                it.remove();
                continue;
            }
            smoke.update(time);
        }
    }

    private void updateAmmoPosition(double time) {
        checkAmmoHits();

        Iterator<Ammunition> it = shotsFired.iterator();
        while (it.hasNext()) {
            if (gameFinished) {
                return;
            }
            Ammunition ammo = it.next();
            if (ammoOutOfTimeOrHitsSomething(ammo)) {
                it.remove();
                continue;
            }
            ammo.update(time);
        }
    }

    private boolean ammoOutOfTimeOrHitsSomething(Ammunition ammo) {
        if (ammo.getAliveTime() < 0
                || checkArenaBoundaries(ammo)
                || checkPlayerHits(ammo)
                || wallCollisionHandler.checkIfInsideWall(ammo)) {
            return true;
        }

        return false;
    }

    private void checkAmmoHits() {
        boolean hit = false;
        for (Ammunition ammo : shotsFired) {
            for (Ammunition other : shotsFired) {
                if (ammo.collides(other)) {
                    shotsFired.remove(other);
                    shotsFired.remove(ammo);
                    hit = true;
                    break;
                }
            }
            if (hit) {
                break;
            }
        }
        if (hit) {
            checkAmmoHits();
        }
    }

    private boolean checkArenaBoundaries(Ammunition ammo) {
        if (ammo.getX() < 0
                || ammo.getX() + ammo.getWidth() >= arena.getWidth() * 32
                || ammo.getY() - ammo.getHeight() < 0
                || ammo.getY() >= arena.getHeight() * 32) {
            return true;
        }
        return false;
    }

    private boolean checkPlayerHits(Ammunition ammo) {
        for (Player player : players) {
            if (ammo.collides(player)) {
                handlePlayerHit(player);
                return true;
            }
        }
        return false;
    }

    private void handlePlayerHit(Player player) {
        audioHandler.playSound("hurt");
        int winnerId;
        if (player.getId() == 0) {
            winnerId = 1;
        } else {
            winnerId = 0;
        }
        players.get(winnerId).addScore();
        if (players.get(winnerId).getScore() == weapons.size()) {
            gameFinished = true;
            return;
        }
        Weapon nextWeapon = weapons.higher(players.get(winnerId).getWeapon());
        setWeapon(players.get(winnerId), nextWeapon.getName());

        player.setX(-1);
        player.setY(-1);
        spawner.randomPlayerLocation(player);
    }

    private void createAmmunition(Player player) {
        int facing = player.getFacing();
        int x = player.getX();
        int y = player.getY();

        Ammunition newAmmo = player.getWeapon().getNewAmmo(x, y, facing);
        shotsFired.add(newAmmo);
    }

    private void slowDown(Player player) {
        double velocityX = player.getVelocityX();
        double velocityY = player.getVelocityY();
        if (velocityX >= -15 && velocityX <= 15) {
            player.setVelocity(0, velocityY);
        }
        if (velocityX < -15) {
            player.addVelocity(15, 0);
        }
        if (velocityX > 15) {
            player.addVelocity(-15, 0);
        }
    }

    private void gravity(Player player) {
        double velocityX = player.getVelocityX();
        double velocityY = player.getVelocityY();
        int playerY = (player.getY() + 1) / 32;
        int playerXsw = player.getX() / 32;
        int playerXse = (player.getX() + 31) / 32;
        if (arena.getTile(playerY, playerXsw) != 0 || arena.getTile(playerY, playerXse) != 0) {
            player.setVelocity(velocityX, 0);
        } else {
            player.addVelocity(0, 15);
        }
    }

    private TreeSet<Weapon> generateWeapons() {
        TreeSet<Weapon> newWeapons = new TreeSet<>();
        newWeapons.add(new LongshotBlaster());
        newWeapons.add(new AutoBlaster());
        newWeapons.add(new Lightsaber());
        newWeapons.add(new CrappySword());
        newWeapons.add(new LaserWall());
        newWeapons.add(new BlasterPistol());
        return newWeapons;
    }

    private void setWeapon(Player player, String weapon) {
        Weapon newWeapon;
        if (weapon.equals("Auto Blaster")) {
            newWeapon = new AutoBlaster();
        } else if (weapon.equals("Longshot Blaster")) {
            newWeapon = new LongshotBlaster();
        } else if (weapon.equals("Crappy Sword")) {
            newWeapon = new CrappySword();
        } else if (weapon.equals("Laser Wall")) {
            newWeapon = new LaserWall();
        } else if (weapon.equals("Blaster Pistol")) {
            newWeapon = new BlasterPistol();
        } else {
            newWeapon = new Lightsaber();
        }
        player.setWeapon(newWeapon);
    }

    private void generateAudio(AudioHandler audioHandler) {
        audioHandler.addAudioClip("lasershot", "sounds/lasershot.wav");
        audioHandler.addAudioClip("lasershotBig", "sounds/lasershotBig.wav");
        audioHandler.addAudioClip("laserwall", "sounds/laserwall.wav");
        audioHandler.addAudioClip("sword", "sounds/sword.wav");
        audioHandler.addAudioClip("lightsaber1", "sounds/lightsaber1.wav");
        audioHandler.addAudioClip("lightsaber2", "sounds/lightsaber2.wav");
        audioHandler.addAudioClip("hurt", "sounds/hurt.wav");
    }
}
