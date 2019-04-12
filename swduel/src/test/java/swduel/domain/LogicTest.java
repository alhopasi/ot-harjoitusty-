package swduel.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogicTest {

    Logic logic;

    @Before
    public void setUp() {
        logic = new Logic("testArena");
        logic.getPlayers().get(0).setX(100);
        logic.getPlayers().get(0).setY(735);
        logic.getPlayers().get(1).setX(400);
        logic.getPlayers().get(1).setY(735);
    }

    @Test
    public void logicIsCreated() {
        assertNotNull(logic);
    }

    @Test
    public void arenaCanBeGet() {
        assertNotNull(logic.getArena());
    }

    @Test
    public void playersCanBeGet() {
        assertEquals(2, logic.getPlayers().size());
    }

    @Test
    public void playerSlowsDownOnUpdate() {
        logic.getPlayers().get(0).addVelocity(40, 0);
        logic.updateAll(0.1);
        assertEquals(25, logic.getPlayers().get(0).getVelocityX(), 0);
        logic.updateAll(0.1);
        assertEquals(10, logic.getPlayers().get(0).getVelocityX(), 0);
        logic.updateAll(0.1);
        assertEquals(0, logic.getPlayers().get(0).getVelocityX(), 0);
        logic.getPlayers().get(0).addVelocity(-30, 0);
        logic.updateAll(0.1);
        assertEquals(-15, logic.getPlayers().get(0).getVelocityX(), 0);
    }

    @Test
    public void playerIsNotAffectedByGravityWhenOnGround() {
        logic.updateAll(0.1);
        assertEquals(0, logic.getPlayers().get(0).getVelocityY(), 0);
    }

    @Test
    public void playerIsAffectedByGravity() {
        logic.getPlayers().get(0).addVelocity(0, -50);
        logic.updateAll(0.1);
        assertEquals(-35, logic.getPlayers().get(0).getVelocityY(), 0);
        logic.updateAll(0.1);
        assertEquals(-20, logic.getPlayers().get(0).getVelocityY(), 0);
        logic.updateAll(0.1);
        assertEquals(-5, logic.getPlayers().get(0).getVelocityY(), 0);
        logic.updateAll(0.1);
        assertEquals(10, logic.getPlayers().get(0).getVelocityY(), 0);
        logic.updateAll(0.1);
        assertEquals(25, logic.getPlayers().get(0).getVelocityY(), 0);
        logic.updateAll(0.1);
        assertEquals(40, logic.getPlayers().get(0).getVelocityY(), 0);
        logic.updateAll(0.1);
        assertEquals(55, logic.getPlayers().get(0).getVelocityY(), 0);
        logic.updateAll(0.1);
        assertEquals(0, logic.getPlayers().get(0).getVelocityY(), 0);
    }

    @Test
    public void playerWeaponCanBeGet() {
        assertNotNull(logic.getPlayers().get(0).getWeapon());
    }

    @Test
    public void playerAttackCreatesAmmunition() {
        Player p1 = logic.getPlayers().get(0);
        logic.attack(p1);
        assertEquals(1, logic.getAmmunition().size());
    }

    @Test
    public void playerCanNotAttackWhenWeaponOnCooldown() {
        Player p1 = logic.getPlayers().get(0);
        p1.getWeapon().addCooldown();
        logic.attack(p1);
        assertEquals(0, logic.getAmmunition().size());
    }

    @Test
    public void gameStatusCanBeGet() {
        assertFalse(logic.getGameFinished());
    }
    
    @Test
    public void somethingHappensWhenPlayerIsHit() {
        Player p1 = logic.getPlayers().get(0);
        Player p2 = logic.getPlayers().get(1);
        logic.attack(p2);
        logic.updateAll(1);
        logic.updateAll(1);
        logic.updateAll(1);
    }
}
