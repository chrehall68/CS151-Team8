package mvc;

public interface AppFactory {
    /**
     * This method should make and return a View object
     * that will be displayed by the AppPanel of your customization
     * @param m - the model for the View object to display
     * @return View - a View that can be put inside an AppPanel
     */
    View makeView(Model m);

    /**
     * This method should make and return a Model. The Model
     * is specific to each customization, and it is the underlying "business logic"
     * model responsible for knowing how to update its state
     * @return Model
     */
    Model makeModel();

    /**
     * This method returns a title for the application
     * @return String - the application title
     */
    String getTitle();

    /**
     * This method returns an array of Strings, each corresponding
     * to a help message
     * @return String[] - an array of String help messages
     */
    String[] getHelp();

    /**
     * This method returns a brief description of the application
     * @return String - the brief description
     */
    String about();

    /**
     * This method returns an array of Strings that will be used
     * as the edit commands for the application. Note that edit commands
     * are case-sensitive. The edit commands returned here should be the
     * same ones managed in `makeEditCommand`
     * @return String[] - an array of String edit command names
     */
    String[] getEditCommands();

    /**
     * Given the name of the command to create, this method creates
     * the corresponding Command object.
     * @param model - the model for the Command to modify
     * @param name - the name of the command to create; case-sensitive
     * @param source - the object that sent the request for the command to be created;
     *               normally not needed but sometimes useful
     * @return Command - the command to execute, or null if no matching Command exists.
     */
    Command makeEditCommand(Model model, String name, Object source);
}
