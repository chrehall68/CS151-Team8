package mvc;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

public class Utilities {

    // asks user a yes/no question
    public static boolean confirm(String query) {
        int result = JOptionPane.showConfirmDialog(null, query, "choose one", JOptionPane.YES_NO_OPTION);
        return result == 0;
    }

    // asks user for info
    public static String ask(String query) {
        return JOptionPane.showInputDialog(null, query);
    }

    // tells user some info
    public static void inform(String info) {
        JOptionPane.showMessageDialog(null, info);
    }

    // tells user a lot of info
    public static void inform(String[] items) {
        StringBuilder helpString = new StringBuilder();
        for (String item : items) {
            helpString.append("\n").append(item);
        }
        inform(helpString.toString());
    }

    // tells user about an error
    public static void error(String gripe) {
        JOptionPane.showMessageDialog(null, gripe, "OOPS!", JOptionPane.ERROR_MESSAGE);
    }

    // tells user about an exception
    public static void error(Exception gripe) {
        gripe.printStackTrace();
        JOptionPane.showMessageDialog(null, gripe.getMessage(), "OOPS!", JOptionPane.ERROR_MESSAGE);
    }

    // asks user to save changes
    public static void saveChanges(Model model) throws Exception {
        if (model.hasUnsavedChanges() && Utilities.confirm("Current model has unsaved changes. Would you like to save them?"))
            Utilities.save(model, false);
    }

    // asks user for a file name
    public static String getFileName(String fName, Boolean open) {
        JFileChooser chooser = new JFileChooser();
        String result = null;
        if (fName != null) {
            // open chooser in directory of fName
            chooser.setCurrentDirectory(new File(fName));
        }
        if (open) {
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                result = chooser.getSelectedFile().getPath();
            }
        } else {
            int returnVal = chooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                result = chooser.getSelectedFile().getPath();
            }
        }
        return result;
    }

    // save model
    public static void save(Model model, Boolean saveAs) throws Exception {
        String fName = model.getFileName();
        if (fName == null || saveAs) {
            fName = getFileName(fName, false);
        }
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
        model.setFileName(fName);
        model.setUnsavedChanges(false);
        os.writeObject(model);
        os.close();
    }

    public static Model open() throws Exception {
        String fName = Utilities.getFileName(null, true);
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
        Model model = (Model) (is.readObject());
        is.close();
        return model;
    }

    // simple menu maker
    public static JMenu makeMenu(String name, String[] items, ActionListener handler) {
        JMenu result = new JMenu(name);
        for (String s : items) {
            JMenuItem item = new JMenuItem(s);
            item.addActionListener(handler);
            result.add(item);
        }
        return result;
    }

    // random number generator
    public static Random rng = new Random(System.currentTimeMillis());

    public static void log(String msg) {
        System.out.println(msg); // for now
    }

    private static int nextID = 100;

    public static int getID() {
        return nextID++;
    }

}