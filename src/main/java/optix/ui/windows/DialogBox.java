package optix.ui.windows;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setWrapText(true);
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Creates a new DialogBox object that contains user input and user image.
     * @param text User input command.
     * @param img User image for the chat box.
     * @return New DialogBox object that contains user input and user image for chat box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    public static DialogBox getOptixDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
}