package optix.ui.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import optix.commons.model.Seat;

import java.io.IOException;

public class SeatDesign extends StackPane {
    @FXML
    private Rectangle rectangle;

    @FXML
    private Label seatNumber;

    private Seat seat;

    public SeatDesign(int row, int col, Seat seat) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DisplaySeats.class.getResource("/view/SeatDesign.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.seat = seat;
        seatNumber.setText(querySeat(row, col));

        if (seat.isBooked()) {
            rectangle.setFill(Color.RED);
        }
    }

    private String querySeat(int row, int col) {
        assert !getRow(row).equals("empty");
        return getRow(row) + getCol(col);
    }

    private String getRow(int row) {
        switch (row) {
        case 0:
            return "A";
        case 1:
            return "B";
        case 2:
            return "C";
        case 3:
            return "D";
        case 4:
            return "E";
        case 5:
            return "F";
        default:
            return "empty";
        }
    }

    private String getCol(int col) {
        return Integer.toString(col + 1);
    }

    public static SeatDesign createSeat(int row, int col, Seat seat) {
        return new SeatDesign(row, col, seat);
    }
}
