import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    /*------------------------------------------------------Hàm kiểm tra định dạng số dương --------------------------------------------------*/
    public static boolean kiemTraGiaTri(String str) {
        // Kiểm tra xem chuỗi str có phải là số nguyên dương hay không
        if (!str.matches("\\d+")) {
            System.out.println("Gia tri nhap vao khong phai so duong.");
            return false;
        }
        return true;
    }

    /*------------------------------------------------------Hàm kiểm tra định dạng ngày tháng --------------------------------------------------*/
    public static boolean kiemTraNgayThang(String ngaySinh) {
        // Loại bỏ các khoảng trắng không cần thiết từ chuỗi ngaySinh
        ngaySinh = ngaySinh.trim();

        // Định nghĩa định dạng cho DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Parse chuỗi ngày tháng thành đối tượng LocalDate
            LocalDate date = LocalDate.parse(ngaySinh, formatter);

            // Kiểm tra năm của ngày sinh
            int year = date.getYear();

            // Kiểm tra năm sinh không hợp lệ nếu nhỏ hơn 2006
            if (year >= 1956 && year <= 2006) {
                return true;
            } else {
                System.out.println("Nam sinh khong hop le.");
                System.out.print("Vui long nhap lai! ");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Ngay sinh nhap vao khong dung dinh dang dd/MM/yyyy.");
            System.out.print("Vui long nhap lai! ");
            return false;
        }
    }

    /*------------------------------------------------------Hàm kiểm tra chuoi rong--------------------------------------------------*/

    // kiểm tra sao cho các chuỗi không được để trống hoặc bị rỗng
    public static boolean kiemTraRong(String str) {
        return str == null || str.trim().isEmpty();
    }

}
