package swduel.domain;

import java.util.List;
import swduel.utils.Utils;

/**
 * Luokassa on tiedot pelin areenasta
 *
 * @author alhopasi
 */
public class Arena {

    private int width;
    private int height;
    private int[][] arena;

    /**
     * Luokan konstruktori
     *
     * @param arenaFile areenan nimi
     */
    public Arena(String arenaFile) {
        createArena(arenaFile);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * palauttaa areenan tiilen numeron kohdasta y, x
     *
     * @param y korkeudelta y
     * @param x leveydeltä x
     * @return tiilen numero
     */
    public int getTile(int y, int x) {
        if (y < 0 || x < 0 || y >= height || x >= width) {
            return -1;
        }
        return arena[y][x];
    }

    private void createArena(String arenaFile) {
        List<String> lines = Utils.readFile("arenas/" + arenaFile);
        width = lines.get(0).length();
        height = lines.size();
        arena = new int[height][width];

        for (int y = 0; y < height; y++) {
            String[] chars = lines.get(y).split("");
            for (int x = 0; x < width; x++) {
                arena[y][x] = Integer.valueOf(chars[x]);
            }
        }
    }
}
