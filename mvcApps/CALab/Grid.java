package CALab;

import mvc.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public abstract class Grid extends Model {
    static private int time = 0;
    protected int dim;
    protected Cell[][] cells;

    public int getDim() {
        return dim;
    }

    public int getTime() {
        return time;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public abstract Cell makeCell();


    public Grid(int dim) {
        this.dim = dim;
        cells = new Cell[dim][dim];
        populate();
    }

    public Grid() {
        this(20);
    }

    protected void populate() {
        // 1. use makeCell to fill in cells
        foreach((row, col) -> {
            cells[row][col] = makeCell();
            getCell(row, col).row = row;
            getCell(row, col).col = col;
            getCell(row, col).myGrid = this;
        });

        // 2. use getNeighbors to set the neighbors field of each cell
        foreach((row, col) -> getCell(row, col).neighbors = getNeighbors(getCell(row, col), 1));
    }

    // called when Populate button is clicked
    public void repopulate(boolean randomly) {
        foreach((row, col) -> getCell(row, col).reset(randomly));
        // make sure that observe is the latest observe
        observe();
        // notify subscribers
        changed();
    }


    public Set<Cell> getNeighbors(Cell asker, int radius) {
        /*
        return the set of all cells that can be reached from the asker in radius steps.
        If radius = 1 this is just the 8 cells touching the asker.
        Tricky part: cells in row/col 0 or dim - 1.
        The asker is not a neighbor of itself.
        */
        HashSet<Cell> neighbors = new HashSet<>();
        for (int rowOffset = -radius; rowOffset <= radius; ++rowOffset) {
            for (int colOffset = -radius; colOffset <= radius; ++colOffset) {
                // calculate true rows/cols
                int trueCol = (asker.col + colOffset + dim) % dim;
                int trueRow = (asker.row + rowOffset + dim) % dim;

                // don't add this cell as its own neighbor
                if (trueCol != asker.col || trueRow != asker.row) {
                    neighbors.add(cells[trueRow][trueCol]);
                }
            }
        }

        return neighbors;
    }

    // cell phases:

    public void observe() {
        // call each cell's observe method and notify subscribers
        foreach((row, col) -> getCell(row, col).observe());
        changed();
    }

    public void interact() {
        // call each cell's interact method and notify subscribers
        foreach((row, col) -> getCell(row, col).interact());
        changed();
    }

    public void update() {
        // call each cell's update method and notify subscribers
        foreach((row, col) -> getCell(row, col).update());
        changed();
    }

    public void updateLoop(int cycles) {
        observe();
        for (int cycle = 0; cycle < cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
        }
    }

    /**
     * Helper method that calls a given consumer on all (row, col)
     *
     * @param consumer the consumer to call with (int row, int col)
     */
    protected void foreach(BiConsumer<Integer, Integer> consumer) {
        for (int row = 0; row < dim; ++row) {
            for (int col = 0; col < dim; ++col) {
                consumer.accept(row, col);
            }
        }
    }
}