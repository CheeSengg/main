package optix.ui.windows;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import optix.Optix;
import optix.commons.model.ShowMap;
import optix.commons.model.Theatre;

import java.time.LocalDate;
import java.util.Map;

public class MainWindow extends AnchorPane {

    @FXML
    ScrollPane scrollPane;

    @FXML
    private VBox display;

    @FXML
    private Label optixResponse;

    @FXML
    private JFXTextField userInput;

    private Optix optix;

    public void setOptix(Optix optix) {
        this.optix = optix;

        ShowMap displayShows = optix.getModel().getShowsGUI();

        for (Map.Entry<LocalDate, Theatre> entry : displayShows.entrySet()) {
            display.getChildren().add(Entry.getEntry(entry.getKey(), entry.getValue()));
        }
    }

    @FXML
    private void handleUserInput() {
        String commandWord = optix.runGUI(userInput.getText());

        optixResponse.setText(optix.getResponse());

        switch (commandWord) {
        case "add":
        case "list":
        case "delete":
        case "postpone":
        case "edit":
            display.getChildren().removeAll(display.getChildren());
            showPerformance();
            break;
        case "sell":
        case "view":
            display.getChildren().removeAll(display.getChildren());
            showSeats();
            break;
        case "bye":
            shutDown();
            break;
        default:
            //nothing should happen here
        }

        userInput.clear();
    }

    private void showSeats() {

    }

    private void showPerformance() {
        ShowMap displayShows = optix.getModel().getShowsGUI();

        for (Map.Entry<LocalDate, Theatre> entry : displayShows.entrySet()) {
            display.getChildren().add(Entry.getEntry(entry.getKey(), entry.getValue()));
        }
    }

    private void shutDown() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished( event -> Platform.exit() );
        delay.play();
    }
}
