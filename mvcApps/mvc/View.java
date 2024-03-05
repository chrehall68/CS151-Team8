package mvc;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class View extends JPanel implements Subscriber {
    protected Model model;

    public View(Model model) {
        this.model = model;
        model.subscribe(this);
        setBorder(new LineBorder(Color.black));
    }

    public void setModel(Model model) {
        // remove previous connection
        this.model.unsubscribe(this);

        // update any internal views
        for (Component c : this.getComponents()) {
            if (c instanceof View) {
                ((View) c).setModel(model);
            }
        }

        // add new connection
        this.model = model;
        model.subscribe(this);
        repaint();
    }

    @Override
    public void update() {
        this.repaint();
    }
}
