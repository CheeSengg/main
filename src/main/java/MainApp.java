import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import optix.Optix;
import optix.ui.windows.MainWindow;

import java.io.File;

public class MainApp extends Application {

    private Optix optix;

    @Override
    public void init() throws Exception {
        super.init();

        File currentDir = new File(System.getProperty("user.dir"));
        File filePath = new File(currentDir.toString() + "\\src\\main\\data");

        optix = new Optix(filePath);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);

        primaryStage.setTitle("Optix");
        primaryStage.setScene(scene);
        fxmlLoader.<MainWindow>getController().setOptix(optix);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
