package CALab;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;


// TODO - make this class actually useful
public class GridFactory implements AppFactory {
    @Override
    public View makeView(Model m) {
        return null;
    }

    @Override
    public Model makeModel() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String[] getHelp() {
        return new String[0];
    }

    @Override
    public String about() {
        return null;
    }

    @Override
    public String[] getEditCommands() {
        return new String[0];
    }

    @Override
    public Command makeEditCommand(Model model, String name, Object source) {
        return null;
    }
}
