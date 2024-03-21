package CALab;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends AppPanel {
    public GridPanel(AppFactory factory) {
        super(factory);
        controls.setLayout(new GridLayout(2, 2));

        JButton run1 = new JButton("Run1");
        addControl(run1);

        JButton run50 = new JButton("Run50");
        addControl(run50);

        JButton populate = new JButton("Populate");
        addControl(populate);

        JButton clear = new JButton("Clear");
        addControl(clear);
    }

    /**
     * Helper method to avoid rewriting control adding code
     * @param control - the button to subscribe this to and to add to the control panel
     */
    private void addControl(JButton control){
        control.addActionListener(this);
        JPanel holder = new JPanel();
        holder.add(control);
        controls.add(holder);
    }
}