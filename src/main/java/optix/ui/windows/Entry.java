package optix.ui.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import optix.commons.model.Theatre;

import java.io.IOException;
import java.time.LocalDate;

public class Entry extends VBox {

    @FXML
    private HBox showDetails;

    @FXML
    private HBox showStats;

    @FXML
    private Label showTitle;

    @FXML
    private Label showDate;

    @FXML
    private Label showRevenue;

    @FXML
    private Label showCost;

    private Entry(LocalDate date, Theatre show) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Entry.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        showTitle.setText(show.getShowName());
        showDate.setText(date.toString());
        showRevenue.setText("Revenue parameter");
        showCost.setText("Cost Parameter if we have one.");

    }

    public static Entry getEntry(LocalDate showDate, Theatre show) {
        return new Entry(showDate, show);
    }
}
