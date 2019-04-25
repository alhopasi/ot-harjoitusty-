package swduel.domain;

import swduel.domain.ammunition.Ammunition;

public class WallCollisionHandler {

    Arena arena;

    public WallCollisionHandler(Arena arena) {
        this.arena = arena;
    }

    public void checkIfInsideWall(Player player) {

        while (true) {
            int swCorner = arena.getTile(player.getY() / 32, player.getX() / 32);
            int nwCorner = arena.getTile((player.getY() - 63) / 32, player.getX() / 32);
            int seCorner = arena.getTile(player.getY() / 32, (player.getX() + 31) / 32);
            int neCorner = arena.getTile((player.getY() - 63) / 32, (player.getX() + 31) / 32);
            int middleLeft = arena.getTile((player.getY() - 32) / 32, player.getX() / 32);
            int middleRight = arena.getTile((player.getY() - 32) / 32, (player.getX() + 31) / 32);

            if (checkBottomAndTop(nwCorner, neCorner, swCorner, seCorner, player)) {
                continue;
            }
            if (checkMiddle(nwCorner, neCorner, swCorner, seCorner, middleLeft, middleRight, player)) {
                continue;
            }
            if (checkCorners(nwCorner, neCorner, swCorner, seCorner, player)) {
                continue;
            }
            break;
        }
    }

    public boolean checkIfInsideWall(Ammunition ammo) {
        int swCorner = arena.getTile(ammo.getY() / 32, ammo.getX() / 32);
        int nwCorner = arena.getTile((ammo.getY() - ammo.getHeight()) / 32, ammo.getX() / 32);
        int seCorner = arena.getTile(ammo.getY() / 32, (ammo.getX() + ammo.getWidth()) / 32);
        int neCorner = arena.getTile((ammo.getY() - ammo.getHeight()) / 32, (ammo.getX() + ammo.getWidth()) / 32);
        if (swCorner != 0 || seCorner != 0 || nwCorner != 0 || neCorner != 0) {
            return true;
        }
        return false;
    }

    private boolean checkBottomAndTop(int nwCorner, int neCorner, int swCorner, int seCorner, Player player) {
        if (swCorner != 0 && seCorner != 0) {
            setUp(player);
            return true;
        }
        if (nwCorner != 0 && neCorner != 0) {
            setDown(player);
            return true;
        }
        return false;
    }

    private boolean checkMiddle(int nwCorner, int neCorner, int swCorner, int seCorner, int middleLeft, int middleRight, Player player) {
        if ((nwCorner != 0 && middleLeft != 0) || (swCorner != 0 && middleLeft != 0)) {
            setRight(player);
            return true;
        }
        if ((neCorner != 0 && middleRight != 0) || (seCorner != 0 && middleRight != 0)) {
            setLeft(player);
            return true;
        }
        if (middleLeft != 0) {
            setRight(player);
            return true;
        }
        if (middleRight != 0) {
            setLeft(player);
            return true;
        }
        return false;
    }

    private boolean checkCorners(int nwCorner, int neCorner, int swCorner, int seCorner, Player player) {
        if (nwCorner != 0) {
            nwCornerCorrection(player);
            return true;
        }
        if (swCorner != 0) {
            swCornerCorrection(player);
            return true;
        }
        if (seCorner != 0) {
            seCornerCorrection(player);
            return true;
        }
        if (neCorner != 0) {
            neCornerCorrection(player);
            return true;
        }
        return false;
    }

    private void nwCornerCorrection(Player player) {
        int playerY = player.getY() - 63;
        int playerX = player.getX();

        int tileBottom = ((player.getY() - 63) / 32) * 32 + 31;
        int tileRight = (player.getX() / 32) * 32 + 31;

        int distanceY = tileBottom - playerY;
        int distanceX = tileRight - playerX;

        if (distanceX < distanceY) {
            setRight(player);
        } else {
            setDown(player);
        }
    }

    private void neCornerCorrection(Player player) {
        int playerY = player.getY() - 63;
        int playerX = player.getX() + 31;

        int tileBottom = ((player.getY() - 63) / 32) * 32 + 31;
        int tileLeft = ((player.getX() + 31) / 32) * 32;

        int distanceY = tileBottom - playerY;
        int distanceX = playerX - tileLeft;

        if (distanceX < distanceY) {
            setLeft(player);
        } else {
            setDown(player);
        }
    }

    private void seCornerCorrection(Player player) {
        int playerY = player.getY();
        int playerX = player.getX() + 31;

        int tileTop = (player.getY() / 32) * 32;
        int tileLeft = ((player.getX() + 31) / 32) * 32;

        int distanceY = playerY - tileTop;
        int distanceX = playerX - tileLeft;

        if (distanceX < distanceY) {
            setLeft(player);
        } else {
            setUp(player);
        }
    }

    private void swCornerCorrection(Player player) {
        int playerY = player.getY();
        int playerX = player.getX();

        int tileTop = (player.getY() / 32) * 32;
        int tileRight = (player.getX() / 32) * 32 + 31;

        int distanceY = playerY - tileTop;
        int distanceX = tileRight - playerX;

        if (distanceX < distanceY) {
            setRight(player);
        } else {
            setUp(player);
        }
    }

    private void setDown(Player player) {
        player.setY((player.getY() / 32 + 1) * 32);
        player.setVelocity(player.getVelocityX(), 0);
    }

    private void setRight(Player player) {
        player.setX(((player.getX() / 32) + 1) * 32 + 1);
        player.setVelocity(0, player.getVelocityY());
    }

    private void setLeft(Player player) {
        player.setX((player.getX() / 32) * 32 - 1);
        player.setVelocity(0, player.getVelocityY());
    }

    private void setUp(Player player) {
        player.setY((player.getY() / 32) * 32 - 1);
        player.setVelocity(player.getVelocityX(), 0);
    }
}
