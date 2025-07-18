package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Database.connect(); // Kết nối trước để chắc chắn

        Parent root = FXMLLoader.load(getClass().getResource("/view/student_form.fxml"));
        primaryStage.setTitle("Quản lý sinh viên");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
