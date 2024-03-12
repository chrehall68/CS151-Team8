package CALabTests;

import CALab.GridFactory;
import CALab.GridPanel;
import CALab.GridView;
import mvc.Command;
import mvc.Model;
import mvc.View;


public class ConcreteFactory extends GridFactory {
    @Override
    public View makeView(Model m) {
        return new GridView(m);
    }

    @Override
    public Model makeModel() {
        return new ConcreteGrid();
    }

    @Override
    public String getTitle() {
        return "Test";
    }

    @Override
    public String[] getHelp() {
        return new String[]{"Hi"};
    }

    @Override
    public String about() {
        return "An example test";
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Cool"};
    }

    @Override
    public Command makeEditCommand(Model model, String name, Object source) {
        return null;
    }

    public static void main(String[] args) {
        GridFactory factory = new ConcreteFactory();
        GridPanel panel = new GridPanel(factory);
        panel.display();
    }
}

