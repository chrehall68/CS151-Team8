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

        setLayout(new GridLayout(dim, dim));
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                Cell cell = grid.getCell(row, col);
                CellView cellView = new CellView(cell);
                cellViews[row][col] = cellView;
                cellView.setRow(row);
                cellView.setCol(col);
                add(cellView);
            }
        }
    }

    public void update(String msg, Object oldState, Object newState) {
        for (int row = 0; row < cellViews.length; row++) {
            for (int col = 0; col < cellViews[0].length; col++) {
                cellViews[row][col].update(msg, oldState, newState);
            }
        }
    }
}