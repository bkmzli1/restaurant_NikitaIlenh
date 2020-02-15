package ru.name.restaurant.util.stage;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.name.restaurant.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

//тоже самое что и класс StageStandard только он блокирует открывшиеся окна
public class StageDialog {

    private Stage newWindow = new Stage();

    public StageDialog(String name) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml\\" + name + ".fxml")));
        StackPane secondaryLayout = new StackPane();


        Scene secondScene = new Scene(secondaryLayout);
        newWindow.setScene(secondScene);
        //блокировка окна
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.initOwner(Main.stage);


        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        InputStream inputStream = ClassLoader.class.getResourceAsStream("/image/fon icon.png");
        try {
            Image image = new Image(inputStream);
            newWindow.getIcons().add(image);
        } catch (NullPointerException e) {
            System.out.println("icon null");
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("css/main.css")).toExternalForm());
        newWindow.setScene(scene);
        newWindow.setResizable(false);
        newWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {

            }
        });
        newWindow.show();


    }

    public Stage getNewWindow() {
        return newWindow;
    }

    public void setNewWindow(Stage newWindow) {
        this.newWindow = newWindow;
    }
}
