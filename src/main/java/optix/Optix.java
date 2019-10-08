package optix;

import optix.commands.Command;
import optix.commons.Model;
import optix.commons.Storage;
import optix.exceptions.OptixException;
import optix.ui.Ui;
import optix.util.Parser;

import java.io.File;


/**
 * Software that stores all the finance for the Opera Hall.
 */
public class Optix {
    private Model model;

    private Ui ui;

    private Storage storage;

    public Optix(File filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        model = new Model(storage);
    }

    public static void main(String[] args) {
        File currentDir = new File(System.getProperty("user.dir"));
        File filePath = new File(currentDir.toString() + "\\src\\main\\data");
        new Optix(filePath).runCmdLine();
    }

    /**
     * To boot up the software.
     */
    private void runCmdLine() {

        boolean isExit = false;
        System.out.println(ui.showCommandLine());

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(model, ui, storage);
                isExit = c.isExit();
            } catch (OptixException e) {
                ui.setMessage(e.getMessage());
            } finally {
                System.out.println(ui.showCommandLine());
            }
        }

    }

    public String runGUI(String fullCommand) {
        String commandWord = "";
        try {
            Command c = Parser.parse(fullCommand);
            commandWord = c.execute(model, ui, storage);
        } catch (OptixException e) {
            ui.setMessage(e.getMessage());
        } finally {
            System.out.println(ui.showCommandLine());
        }
        return commandWord;
    }

    public Model getModel() {
        return model;
    }

    public String getResponse() {
        return ui.getMessage();
    }
}

