package optix.commands.shows;

import optix.commands.Command;
import optix.commons.Model;
import optix.commons.Storage;
import optix.commons.model.ShowMap;
import optix.commons.model.Theatre;
import optix.exceptions.OptixInvalidDateException;
import optix.ui.Ui;
import optix.util.OptixDateFormatter;

import java.time.LocalDate;

public class PostponeCommand extends Command {
    private String showName;
    private String oldDate;
    private String newDate;

    private OptixDateFormatter formatter = new OptixDateFormatter();

    private static final String MESSAGE_DOES_NOT_MATCH = "☹ OOPS!!! Did you get the wrong date or wrong show. \n"
                                                        + "Try again!\n";

    private static final String MESSAGE_SHOW_NOT_FOUND = "☹ OOPS!!! The show cannot be found.\n";

    private static final String MESSAGE_SHOW_CLASH = "☹ OOPS!!! There already exists a show for %1$s.\n";

    private static final String MESSAGE_INVALID_NEW_DATE = "☹ OOPS!!! It is not possible to postpone to the past.\n";

    private static final String MESSAGE_SUCCESSFUL = "%1$s has been postponed from %2$s to %3$s.\n";

    public PostponeCommand(String showName, String oldDate, String newDate) {
        // need to check if both dates are valid if not throw exception
        // need to check if the event was completed in the past. Past event shouldn't be postponed.
        this.showName = showName;
        this.oldDate = oldDate;
        this.newDate = newDate;
    }

    @Override
    public String execute(Model model, Ui ui, Storage storage) {
        ShowMap shows = model.getShows();
        String message = "";
        LocalDate today = storage.getToday();
        
        try {
            if (!formatter.isValidDate(oldDate) || !formatter.isValidDate(newDate)) {
                throw new OptixInvalidDateException();
            }
            
            LocalDate localOldDate = formatter.toLocalDate(oldDate);
            LocalDate localNewDate = formatter.toLocalDate(newDate);

            if (localNewDate.compareTo(today) <= 0) {
                message = MESSAGE_INVALID_NEW_DATE;
            } else {
                if (!shows.containsKey(localOldDate)) {
                    message = MESSAGE_SHOW_NOT_FOUND;
                } else if (shows.containsKey(localNewDate)) {
                    message = String.format(MESSAGE_SHOW_CLASH, newDate);
                } else if (!shows.get(localOldDate).hasSameName(showName)) {
                    message = MESSAGE_DOES_NOT_MATCH;
                } else {
                    Theatre postponedShow = shows.removeShow(localOldDate);
                    shows.put(localNewDate, postponedShow);
                    model.setShows(shows);
                    model.setShowsGUI(shows);
                    message = String.format(MESSAGE_SUCCESSFUL, showName, oldDate, newDate);
                }
            }
        } catch (OptixInvalidDateException e) {
            message = e.getMessage();
        } finally {
            ui.setMessage(message);
        }

        return "postpone";
    }

    @Override
    public boolean isExit() {
        return super.isExit();
    }
}
