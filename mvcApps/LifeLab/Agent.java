package LifeLab;

import CALab.Cell;

import java.awt.*;

public class Agent extends Cell {

    private int status; // 0 = dead, 1 = alive
    private int ambience; // Number of living neighbors

    public Agent() {
        status = 0; // Initialize status to dead
        ambience = 0; // Initialize ambience to 8
    }

    @Override
    public void observe() {
        ambience = 0; // Reset ambience
        for (Cell neighbor : neighbors) {
            if (((Agent)neighbor).status == 1) {
                ambience++; // Increment ambience if neighbor is alive
            }
        }
        // don't need to put changed here because this is only called by
        // grid, which already calls changed or this class, which takes care of it
    }

    @Override
    public void update() {
        if (status == 0 && Society.rebirth.contains(ambience)) {
            status = 1; // Bring dead agent back to life
        } else if (status == 1 && Society.death.contains(ambience)) {
            status = 0; // Kill living agent
        }
        // don't need to put changed here because this is only called by
        // grid, which already calls changed
    }

    @Override
    public void nextState() {
        status = (status + 1) % 2;
        // notify view to update
        changed();

        // notify neighbors to update their ambiences
        for (Cell neighbor : neighbors){
            neighbor.observe();
            neighbor.changed();
        }
    }

    @Override
    public void reset(boolean randomly) {
        status = randomly ? (Math.random()*100 < Society.percentAlive ? 1 : 0) : 0; // Reset status randomly or to dead
        changed();
    }

    @Override
    public int getStatus() {
        return ambience;
    }

    @Override
    public Color getColor() {
        return status == 1 ? Color.GREEN : Color.RED; // Alive = green, Dead = red
    }

    public void interact() {
    }
}
