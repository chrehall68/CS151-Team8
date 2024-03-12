package CALab;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public abstract class GridFactory implements AppFactory {
    @Override
    public View makeView(Model m) {
        return new GridView(m);
    }

    @Override
    public abstract Model makeModel();

    @Override
    public String getTitle() {
        return "CA Lab";
    }

    @Override
    public String[] getHelp() {
        return new String[]{"Click Run1 to update each cell once.", "Click Run50 to update each cell 50 times.", "Click Populate to set each cell to a random value.", "Click Clear to set each cell to an initial value."};
    }

    @Override
    public String about() {
        return "CA Lab version 1.0. Copyright 2024 by Alex Ross, Eliot Hall, and Xioke Ran.";
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Run1", "Run50", "Populate", "Clear"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {

        if (type.equals("Run1")) return new RunCommand.Run1(model);
        else if (type.equals("Run50")) return new RunCommand.Run50(model);
        else if (type.equals("Clear")) return new ClearCommand(model);
        else if (type.equals("Populate")) return new PopulateCommand(model);
        else return null;
    }
}
