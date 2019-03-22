package swduel.domain;

public class Logic {

    private Arena arena;

    public Logic(String arenaFile) {
        this.arena = new Arena(arenaFile);
    }
    
    public Arena getArena() {
        return this.arena;
    }
}
