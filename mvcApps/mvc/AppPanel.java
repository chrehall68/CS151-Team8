package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel implements ActionListener, Subscriber {
    protected JPanel controls;  // control panel where edit buttons go
    protected View view;  // view to display on the RHS of the screen
    protected Model model;  // internal model that commands should update
    protected final AppFactory factory;  // provides factory methods to create necessary objects

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        controls = new JPanel();
        model = factory.makeModel();
        view = factory.makeView(model);
        model.subscribe(this); // just in case the control panel needs to be able to update

        // add sub-panels
        GridLayout layout = new GridLayout();
        layout.setColumns(2);
        this.setLayout(layout);
        this.add(controls);
        this.add(view);
    }

    /**
     * Sets the AppPanel's model to a new model. In the process, this method
     * unsubscribes AppPanel from previous model and subscribes it to the new model.
     * It also calls `view.setModel` to make sure the view sets the model as well.
     * @param newModel - the model to set AppPanel's model to
     */
    protected void setModel(Model newModel){
        model.unsubscribe(this);
        model = newModel;
        model.subscribe(this);
        view.setModel(model);
        update();
    }

    /**
     * Create a menu bar that will be displayed in the frame. The menu bar will contain
     * File options (New, Save, Save As, Open, Quit), Help options (About, Help), and
     * Edit options (determined by `factory.getEditCommands()`)
     * @return JMenuBar - the menu bar
     */
    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    /**
     * Creates a frame and displays the app panel. This is what should be
     * inside every `main` method in a customization. It is blocking.
     */
    public void display() {
        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);

        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    /**
     * Handles basic File and Help use cases;
     * Edit use cases are handled by `factory.makeEditCommand`
     *
     * @param actionEvent the event that was fired
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String cmmd = actionEvent.getActionCommand();
        try {
            switch (cmmd) {
                case "Save": {
                    Utilities.save(model, false);
                    break;
                }
                case "Save As": {
                    Utilities.save(model, true);
                    break;
                }

                case "Open": {
                    Utilities.saveChanges(model);
                    setModel(Utilities.open());
                    break;
                }

                case "New": {
                    Utilities.saveChanges(model);
                    setModel(factory.makeModel());
                    break;
                }

                case "Quit": {
                    Utilities.saveChanges(model);
                    System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform(factory.about());
                    break;
                }

                case "Help": {
                    Utilities.inform(factory.getHelp());
                    break;
                }
                default: {
                    Command command = factory.makeEditCommand(model, cmmd, actionEvent.getSource());
                    if (command != null) {
                        command.execute();
                    } else {
                        throw new Exception("Unrecognized command " + cmmd);
                    }
                }
            }
        } catch (Exception ex) {
            if (ex instanceof NullPointerException) {
                Utilities.error("Operation canceled");  // attempting to read from null file
                ex.printStackTrace();
            } else {
                Utilities.error(ex);
            }
        }
    }

    /**
     * Can be overridden
     * if the control panels need to be repainted/notified when the model changes
     */
    @Override
    public void update() {}
}
