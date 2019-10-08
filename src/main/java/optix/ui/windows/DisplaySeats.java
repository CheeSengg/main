package optix.ui.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import optix.commons.model.Seat;
import optix.commons.model.Theatre;

import java.io.IOException;

public class DisplaySeats extends VBox {
    private Theatre show;

    @FXML
    private HBox rowA;

    @FXML
    private HBox rowB;

    @FXML
    private HBox rowC;

    @FXML
    private HBox rowD;

    @FXML
    private HBox rowE;

    @FXML
    private HBox rowF;

    private DisplaySeats(Theatre show) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DisplaySeats.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.show = show;

        fillSeats(show.getSeats());
    }

    private void fillSeats(Seat[][] seats) {
        for (int i = 0; i < seats[0].length; i++) {
            rowA.getChildren().add(SeatDesign.createSeat(0, i, seats[0][i]));
            rowB.getChildren().add(SeatDesign.createSeat(1, i, seats[1][i]));
            rowC.getChildren().add(SeatDesign.createSeat(2, i, seats[2][i]));
            rowD.getChildren().add(SeatDesign.createSeat(3, i, seats[3][i]));
            rowE.getChildren().add(SeatDesign.createSeat(4, i, seats[4][i]));
            rowF.getChildren().add(SeatDesign.createSeat(5, i, seats[5][i]));
        }
    }

    public static DisplaySeats getShow(Theatre show) {
        return new DisplaySeats(show);
    }
}
