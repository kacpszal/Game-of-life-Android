package pl.edu.agh.gameoflife.game.cell;

public interface Cell {
    int STATE_ALIVE = 1;
    int STATE_DEAD = 0;

    void reset(int state);
    long getId();
    int getY();
    int getX();
    void setState(int state);
    int getState();
    boolean isAlive();
    boolean isDead();
    void onNeighborStateChange(int newState);
    void setOverseer(Overseer overseer);
}
