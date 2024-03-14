package CALab;

import java.awt.*;
import mvc.*;

public class GridView extends View {

    private CellView[][] cellViews;

    public GridView(Model model) {
        super(model);
        Grid grid = (Grid) model;
        int dim = grid.getDim();
        cellViews = new CellView[dim][dim];

        setModel(model);
    }

    @Override
    public void setModel(Model model) {
        super.setModel(model);
        removeAll();  // remove previous CellViews, if applicable
        Grid grid = model.as(Grid.class);

        // add CellViews for each cell in the grid
        int dim = grid.getDim();
        setLayout(new GridLayout(dim, dim));
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                Cell cell = grid.getCell(row, col);
                cellViews[row][col] = new CellView(cell);
                cellViews[row][col].update();
                add(cellViews[row][col]);
            }
        }
        revalidate();  // update layout (necessary after removeAll)
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