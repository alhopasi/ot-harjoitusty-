package swduel.domain;

public class Logic {

    private Arena arena;

    public Logic(String arena) {
        this.arena = new Arena(arena);
    }
    
    public Arena getArena() {
        return this.arena;
    }
}
