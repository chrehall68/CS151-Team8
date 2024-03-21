package CALab;

import mvc.Model;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Cell extends Model {
    protected int row = 0, col = 0;
    protected Set<Cell> neighbors = new HashSet<>();
    protected Grid myGrid = null;
    protected Cell partner = null;


    // choose a random neighbor as a partner
    public void choosePartner() {
        if (partner == null && neighbors != null) {
			/*
			Set partner to null
			Convert neighbors set to a local array
			Starting at a random position in the array search for a neighbor without a partner
			Make the first such neighbor (if any) the partner and set its partner field to this
			*/
            Cell[] neighborsArray = neighbors.toArray(new Cell[0]);
            Random random = new Random();
            int size = neighborsArray.length;

            // search for first available neighbor to partner with
            int start = random.nextInt(size);
            int offset = 0;
            while (offset < size && neighborsArray[(start + offset) % size].partner != null) {
                ++offset;
            }

            // if we've reached a neighbor without a partner, partner with them
            if (neighborsArray[(start + offset) % size].partner == null) {
                neighborsArray[(start + offset) % size].partner = this;
                partner = neighborsArray[(start + offset) % size];
            }
        }
    }

    public void unpartner() {
        if (partner != null) {
            partner.partner = null;
            partner = null;
        }
    }

    // subclasses should override this 
    public abstract Color getColor();

    // observer neighbors' states
    public abstract void observe();

    // interact with a random neighbor
    public abstract void interact();

    // update my state, and notify any subscribers
    public abstract void update();

    // set status to status + 1 mod whatever
    public abstract void nextState();

    public abstract int getStatus();

    // set status to a random or initial value
    public abstract void reset(boolean randomly);

    @Override
    public void changed() {
        super.changed();
        myGrid.setUnsavedChanges(true);
    }
}
