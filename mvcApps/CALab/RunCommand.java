package CALab;

import mvc.Command;
import mvc.Model;

public class RunCommand {
    public static class Run1 extends Command{

        public Run1(Model model) {
            super(model);
        }

        @Override
        public void execute() throws Exception {
            // TODO - run 1 update loop
        }
    }

    public static class Run50 extends Command{

        public Run50(Model model) {
            super(model);
        }

        @Override
        public void execute() throws Exception {
            // TODO - run 50 update loops
        }
    }
}
