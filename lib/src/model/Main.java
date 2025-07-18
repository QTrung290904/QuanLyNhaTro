package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Kết nối MySQL
            Database.connect();

            // Load giao diện đăng nhập
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));

            primaryStage.setTitle("Đăng nhập hệ thống");
            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi tải login.fxml: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Lỗi khởi động ứng dụng: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
