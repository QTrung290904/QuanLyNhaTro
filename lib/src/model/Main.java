package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Database;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Kết nối đến CSDL (tạo bảng nếu chưa có)
        Database.connect();

        // Load giao diện login.fxml
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Quản lý nhà trọ");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
