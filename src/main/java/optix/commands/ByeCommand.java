package optix.commands;

import optix.commons.Model;
import optix.commons.Storage;
import optix.ui.Ui;

public class ByeCommand extends Command {
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!\n";

    @Override
    public String execute(Model model, Ui ui, Storage storage) {
        storage.write(model.getShows());
        ui.setMessage(MESSAGE_BYE);
        return "bye";
    }

    @Override
    public String[] parseDetails(String details) {
        return new String[0];
    }
}
