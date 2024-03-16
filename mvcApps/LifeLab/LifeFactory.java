package LifeLab;

import CALab.GridFactory;
import mvc.Model;

public class LifeFactory extends GridFactory {

    public Model makeModel() {
       return new Society();
    }

}
