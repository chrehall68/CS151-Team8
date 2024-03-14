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

    /**
     * Sets `this.model` to the provided model, and recursively calls
     * `setModel` on any children View objects
     * @param model - the new model
     */
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

    /**
     * Repaints the View object
     */
    @Override
    public void update() {
        repaint();
    }
}
