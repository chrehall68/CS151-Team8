package CALab;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import mvc.*;

public class CellView extends JButton implements ActionListener, Subscriber {
    private Cell myCell;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        myCell.nextState();
        update();
    }

    @Override
    public void update() {
        setBackground(myCell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.black));
        setText("" + myCell.getStatus());
    }

    public void setMyCell(Cell myCell) {
        if (this.myCell != null){
            this.myCell.unsubscribe(this);
        }
        this.myCell = myCell;
        if (this.myCell != null){
            this.myCell.subscribe(this);
        }
    }
}