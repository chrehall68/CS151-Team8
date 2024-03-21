package CALab;

import java.awt.*;
import mvc.*;

public class GridView extends View {

    private final CellView[][] cellViews;

    public GridView(Model model) {
        super(model);
        Grid grid = (Grid) model;
        int dim = grid.getDim();
        cellViews = new CellView[dim][dim];
        setLayout(new GridLayout(dim, dim));

        // add CellViews for each cell in the grid
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                Cell cell = grid.getCell(row, col);
                cellViews[row][col] = new CellView(cell);
                add(cellViews[row][col]);
                cellViews[row][col].update();
            }
        }
    }

    @Override
    public void setModel(Model model) {
        super.setModel(model);
        Grid grid = model.as(Grid.class);
        int dim = grid.getDim();

        // add CellViews for each cell in the grid
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                Cell cell = grid.getCell(row, col);
                cellViews[row][col].setMyCell(cell);
                cellViews[row][col].update();
            }
        }
    }

    @Override
    public void update() {
        for (int row = 0; row < cellViews.length; row++) {
            for (int col = 0; col < cellViews[0].length; col++) {
                cellViews[row][col].update();
            }
        }
    }
}