package application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import views.BxdTestController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("翻译");
            URL location = this.getClass().getClassLoader().getResource("views/BxdTest.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(location);
            Parent root = loader.load();

            BxdTestController controller = loader.getController(); // 获取Controller的实例对象

            // Controller中写的初始化方法
            controller.init();

            Scene scene = new Scene(root, 600, 600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image(this.getClass().getClassLoader().getResourceAsStream("bxd.png")));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
