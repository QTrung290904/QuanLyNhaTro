package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField fullNameField;
    @FXML private ComboBox<String> roleCombo;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        roleCombo.getItems().addAll("ADMIN", "MANAGER", "STAFF");
    }

    @FXML
    public void handleRegister() {
        String username = usernameField.getText().trim();
        String password = Database.hashPassword(passwordField.getText().trim());
        String fullName = fullNameField.getText().trim();
        String role = roleCombo.getValue();

        if (username.isEmpty() || password.isEmpty() || role == null) {
            messageLabel.setText("❌ Vui lòng nhập đầy đủ thông tin");
            return;
        }

        try (Connection conn = Database.connect()) {
            String sql = "INSERT INTO users (username, password_hash, full_name, role) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, fullName);
            stmt.setString(4, role);
            stmt.executeUpdate();

            messageLabel.setText("✅ Đăng ký thành công!");
        } catch (Exception e) {
            messageLabel.setText("❌ Tên đăng nhập đã tồn tại!");
        }
    }

    @FXML
    public void goToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
