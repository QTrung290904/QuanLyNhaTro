package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Database {
    // ✅ Cập nhật thông tin kết nối theo cấu hình thật của bạn
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/nha_tro_sinh_vien?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "your_password"; // ← Đổi mật khẩu thật của bạn ở đây

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            ensureUsersTableExists(conn);
            System.out.println("✅ Đã kết nối MySQL & kiểm tra bảng users.");
            return conn;
        } catch (SQLException e) {
            System.err.println("❌ Không thể kết nối đến MySQL: " + e.getMessage());
            return null;
        }
    }

    // ✅ Tạo bảng nếu chưa có
    private static void ensureUsersTableExists(Connection conn) {
        String createUserTable = """
            CREATE TABLE IF NOT EXISTS users (
                user_id INT AUTO_INCREMENT PRIMARY KEY,
                username VARCHAR(50) NOT NULL UNIQUE,
                password_hash VARCHAR(255) NOT NULL,
                full_name VARCHAR(100),
                role ENUM('ADMIN', 'MANAGER', 'STAFF') DEFAULT 'STAFF',
                is_active BOOLEAN DEFAULT TRUE
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createUserTable);
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi tạo bảng users: " + e.getMessage());
        }
    }

    // ✅ Hàm mã hóa mật khẩu SHA-256
    public static String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            System.err.println("❌ Lỗi mã hóa mật khẩu: " + e.getMessage());
            return null;
        }
    }
}
