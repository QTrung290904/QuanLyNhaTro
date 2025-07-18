package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentFormController {
    @FXML private TextField txtMaSV, txtHoTen, txtNgaySinh, txtKhoa, txtKhoaHoc, txtLop, txtSearchKhoa;
    @FXML private TextArea resultArea;

    @FXML
    private void handleAdd() {
        try (Connection conn = Database.connect()) {
            String sql = "INSERT INTO sinh_vien(ma_sv, ho_ten, ngay_sinh, khoa, khoa_hoc, lop) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtMaSV.getText());
            ps.setString(2, txtHoTen.getText());
            ps.setString(3, txtNgaySinh.getText());
            ps.setString(4, txtKhoa.getText());
            ps.setString(5, txtKhoaHoc.getText());
            ps.setString(6, txtLop.getText());
            ps.executeUpdate();
            resultArea.setText("✅ Thêm sinh viên thành công!");
        } catch (Exception e) {
            resultArea.setText("❌ Lỗi: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearchByKhoa() {
        try (Connection conn = Database.connect()) {
            String sql = "SELECT * FROM sinh_vien WHERE khoa LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txtSearchKhoa.getText() + "%");
            ResultSet rs = ps.executeQuery();
            StringBuilder result = new StringBuilder("📋 Kết quả tìm kiếm:\n");
            while (rs.next()) {
                result.append(rs.getString("ma_sv")).append(" - ").append(rs.getString("ho_ten")).append(" - ").append(rs.getString("lop")).append("\n");
            }
            resultArea.setText(result.toString());
        } catch (Exception e) {
            resultArea.setText("❌ Lỗi: " + e.getMessage());
        }
    }
}
