package LifeLab;
import CALab.Cell;
import java.awt.Color;
public class Agent extends Cell {

    private int status; // 0 = dead, 1 = alive
    private int ambience; // Number of living neighbors
    public Agent() {
        status = 0; // Initialize status to dead
        ambience = 8; // Initialize ambience to 8
    }

    @Override
    public void observe() {
        ambience = 0; // Reset ambience
        Set<Cell> neighbors = myGrid.getNeighbors(this, 1); // Get neighbors
        for (Cell neighbor : neighbors) {
            if (((Agent) neighbor).getStatus() == 1) {
                ambience++; // Increment ambience if neighbor is alive
            }
        }
    }

    @Override
    public void update() {
        if (status == 0 && rebirth.contains(ambience)) {
            status = 1; // Bring dead agent back to life
        } else if (status == 1 && death.contains(ambience)) {
            status = 0; // Kill living agent
        }
    }
    @Override
    public void nextState() {}

    @Override
    public void reset(boolean randomly) {
        status = randomly ? (Math.random() < 0.5 ? 1 : 0) : 0; // Reset status randomly or to dead
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public Color getColor() {
        return status == 1 ? Color.GREEN : Color.RED; // Alive = green, Dead = red
    }

    public void interact() {}
}
