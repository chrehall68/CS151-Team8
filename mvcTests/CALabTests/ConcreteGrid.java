package CALabTests;

import CALab.Cell;
import CALab.Grid;

import java.util.function.BiConsumer;

class ConcreteGrid extends Grid {
    public ConcreteGrid(int dim) {
        super(dim);
    }

    public ConcreteGrid() {
        super();
    }

    @Override
    public Cell makeCell() {
        return new ConcreteCell();
    }

    @Override
    public void foreach(BiConsumer<Integer, Integer> consumer) {
        super.foreach(consumer);
    }
}
