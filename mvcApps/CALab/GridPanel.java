package CALab;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;

public class GridPanel extends AppPanel {
    public GridPanel(AppFactory factory) {
        super(factory);

        JButton run1 = new JButton("Run1");
        run1.addActionListener(this);
        controls.add(run1);

        JButton run50 = new JButton("Run50");
        run50.addActionListener(this);
        controls.add(run50);

        JButton populate = new JButton("Populate");
        populate.addActionListener(this);
        controls.add(populate);

        JButton clear = new JButton("Clear");
        clear.addActionListener(this);
        controls.add(clear);
    }
}