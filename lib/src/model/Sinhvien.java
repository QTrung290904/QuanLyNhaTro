package model;

public class SinhVien {
    private int id;
    private String maSV;
    private String hoTen;
    private String ngaySinh;
    private String khoa;
    private String khoaHoc;
    private String lop;

    public SinhVien(int id, String maSV, String hoTen, String ngaySinh, String khoa, String khoaHoc, String lop) {
        this.id = id;
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.khoa = khoa;
        this.khoaHoc = khoaHoc;
        this.lop = lop;
    }

    // Getters & setters
    public int getId() { return id; }
    public String getMaSV() { return maSV; }
    public String getHoTen() { return hoTen; }
    public String getNgaySinh() { return ngaySinh; }
    public String getKhoa() { return khoa; }
    public String getKhoaHoc() { return khoaHoc; }
    public String getLop() { return lop; }

    public void setId(int id) { this.id = id; }
    public void setMaSV(String maSV) { this.maSV = maSV; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }
    public void setKhoa(String khoa) { this.khoa = khoa; }
    public void setKhoaHoc(String khoaHoc) { this.khoaHoc = khoaHoc; }
    public void setLop(String lop) { this.lop = lop; }
}
