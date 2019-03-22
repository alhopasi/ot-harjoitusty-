package swduel.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {

    int width;
    int height;
    int[][] arena;

    public Arena(String arenaFile) {
        createArena(arenaFile);
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getTile(int y, int x) {
        return arena[y][x];
    }

    private void createArena(String arenaFile) {
        List<String> lines = readArenaFile(arenaFile);
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
    
    private List<String> readArenaFile(String arenaFile) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("arenas/" + arenaFile))) {
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }
}
