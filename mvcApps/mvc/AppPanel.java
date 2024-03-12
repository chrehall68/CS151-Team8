package mvc;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel implements ActionListener, Subscriber {
    protected ControlPanel controls;
    protected View view;
    protected Model model;
    protected final AppFactory factory;

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        controls = new ControlPanel();
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
     * Edit use cases should be overridden in subclasses
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
                    model = Utilities.open();
                    view.setModel(model);
                    break;
                }

                case "New": {
                    Utilities.saveChanges(model);
                    model = factory.makeModel();
                    view.setModel(model);
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

    @Override
    public void update() {
        controls.update();
    }

    public static class ControlPanel extends JPanel {
        private static int row = 0;

        public ControlPanel() {
            setLayout(new GridBagLayout());
            setBorder(new LineBorder(Color.black));
        }

        @Override
        public Component add(Component comp) {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 0.5;
            constraints.weighty = 0.5;
            constraints.gridy = row++;
            JPanel containerPanel = new JPanel();

            containerPanel.add(comp);
            this.add(containerPanel, constraints);
            return comp;
        }

        public void update() {
            // customizers can override this if they need to update the
            // Control panel's display
        }
    }
}
