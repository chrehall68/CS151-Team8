package CALabTests;

import CALab.Cell;

import java.awt.*;
import java.util.Set;

class ConcreteCell extends Cell {
    int observeTimes = 0;
    int interactTimes = 0;
    int updateTimes = 0;
    Color color;

    public ConcreteCell() {
        super();
        color = Color.green;
    }

    @Override
    public void observe() {
        ++observeTimes;
    }

    @Override
    public void interact() {
        ++interactTimes;
    }

    @Override
    public void update() {
        ++updateTimes;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void nextState() {
        if (color.equals(Color.green)) {
            color = Color.red;
        } else {
            color = Color.green;
        }
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public void reset(boolean randomly) {
        unpartner();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Set<Cell> getNeighbors() {
        return neighbors;
    }

    public String toString() {
        return "(" + row + "," + col + ")";
    }

    public void setPartner(ConcreteCell o) {
        this.partner = o;
        o.partner = this;
    }

    public Cell getPartner() {
        return partner;
    }
}


