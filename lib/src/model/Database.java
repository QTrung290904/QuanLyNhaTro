package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/nha_tro_sinh_vien?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "your_password"; // 🔁 Đổi thành mật khẩu thực tế của bạn

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

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

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createUserTable);

            System.out.println("✅ Kết nối CSDL & đảm bảo bảng users đã sẵn sàng.");
        } catch (Exception e) {
            System.out.println("❌ Lỗi khi kết nối CSDL: " + e.getMessage());
        }
        return conn;
    }
}
