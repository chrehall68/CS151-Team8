package CALab;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public abstract class GridFactory implements AppFactory {
    @Override
    public abstract View makeView(Model m);

    @Override
    public abstract Model makeModel();

    @Override
    public abstract String getTitle();

    @Override
    public abstract String[] getHelp();

    @Override
    public abstract String about();

    @Override
    public abstract String[] getEditCommands();

    @Override
    public abstract Command makeEditCommand(Model model, String type, Object source);
}
