public class Student {
    private Integer mssv;
    private String hoTen;
    private String ngaySinh;
    private Integer khoa;
    private String vien;
    private String lop;

    public Student() {
    }

    public Student(Integer mssv, String hoTen, String ngaySinh, Integer khoa, String vien, String lop) {
        this.mssv = mssv;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.khoa = khoa;
        this.vien = vien;
        this.lop = lop;
    }

    public Integer getMssv() {
        return mssv;
    }

    public void setMssv(Integer mssv) {
        this.mssv = mssv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Integer getKhoa() {
        return khoa;
    }

    public void setKhoa(Integer khoa) {
        this.khoa = khoa;
    }

    public String getVien() {
        return vien;
    }

    public void setVien(String vien) {
        this.vien = vien;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
    @Override
    public String toString() {
        String separator = "----------------------------------------------------------------------------------------------------------------------------------------";
        String row = String.format("| %-10s | %-30s | %-12s | %-5s | %-40s | %-20s |",
                mssv,
                hoTen,
                ngaySinh,
                khoa,
                vien,
                lop);
        return  "\n" + row + "\n" + separator ;
    }
}
