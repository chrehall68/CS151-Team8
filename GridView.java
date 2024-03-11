package CALab;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import mvc.*;

public class CellView extends JButton implements ActionListener, Subscriber {
    private Cell myCell;
    private int row;
    private int col;

    public CellView(Cell c) {
        myCell = c;
        if (c != null) {
            c.subscribe(this);
        }
        this.addActionListener(this);
    }

    public CellView() {
        this(null);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        myCell.nextState();
        setBackground(myCell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.black));
        setText("" + myCell.getStatus());
    }

    @Override
    public void update(String msg, Object oldState, Object newState) {
        setBackground(myCell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.black));
        setText("" + myCell.getStatus());
    }
}