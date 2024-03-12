package CALabTests;

import CALab.GridFactory;
import CALab.GridPanel;
import mvc.Model;


public class ConcreteFactory extends GridFactory {
    @Override
    public Model makeModel() {
        return new ConcreteGrid();
    }

    public static void main(String[] args) {
        GridFactory factory = new ConcreteFactory();
        GridPanel panel = new GridPanel(factory);
        panel.display();
    }
}

