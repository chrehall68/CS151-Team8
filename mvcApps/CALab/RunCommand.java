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
            ((Grid) model).updateLoop(1);
        }
    }

    public static class Run50 extends Command{

        public Run50(Model model) {
            super(model);
        }

        @Override
        public void execute() throws Exception {
                ((Grid) model).updateLoop(50);
        }
    }
}