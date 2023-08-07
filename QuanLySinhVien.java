//Bùi Minh Châu 20210110
/* Chủ đề 5: Xây dựng chương trình quản lý hồ sơ sinh viên 
- Nhập thông tin sinh viên (mã sinh viên – số, họ tên, ngày sinh, khóa học, khoa viện, lớp) 
- Cập nhật thông tin sinh viên 
- Sắp xếp: theo lớp 
- Tìm và hiển thị thông tin sinh viên: theo mã số, theo họ tên, lớp 
- Lưu ra file, đọc từ file */

import java.io.*;
import java.util.*;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.*;

public class QuanLySinhVien {
    private static List<Student> danhSachSinhVien = new ArrayList<>();
    public List<Student> getDanhSachStudents(){
        return danhSachSinhVien;
    }

    /*------------------------------------------------------Hàm kiểm tra MSSV trùng lặp--------------------------------------------------*/
    private static boolean checkDuplicateStudentID(String mssvInput, List<Student> danhSachSinhVien) {
        int mssv = Integer.parseInt(mssvInput);
        for (Student student : danhSachSinhVien) {
            if (student.getMssv() == mssv) {
                System.out.println("MSSV da ton tai trong danh sach");
                return true;
            }
        }
        return false; // MSSV không tồn tại trong danh sách
    }

    /*------------------------------------------------------ Hàm in thông tin sinh viên ra màn hình Console --------------------------------------------------*/
    public void printStudents(List<Student> students) {
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-30s | %-12s | %-5s | %-40s | %-20s |\n", "MSSV", "Ho ten", "Ngay sinh", "Khoa",
                "Vien", "Lop");
        System.out.print(
                "----------------------------------------------------------------------------------------------------------------------------------------");
        for (Student student : students) {
            System.out.print(student);
        }
    }

    /*------------------------------------------------------ Hàm lưu danh sách vào file----------------------------------------------------*/
    public boolean saveListToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Student student : danhSachSinhVien) {
                writer.println(student.getMssv() + "," +
                        student.getHoTen() + "," +
                        student.getNgaySinh() + "," +
                        student.getKhoa() + "," +
                        student.getVien() + "," +
                        student.getLop());
            }
            // System.out.println("Da luu du lieu vao file " + fileName + " thanh cong.");
            return true;
        } catch (IOException e) {
            // System.out.println("Loi khi ghi du lieu: " + e.getMessage());
            return false;
        }
    }

    /*------------------------------------------------------Hàm thêm sinh viên từ Console --------------------------------------------------*/
    public void addNewStudentFromConsole() {
        Scanner sc = new Scanner(System.in);

        Student newStudent = new Student();

        /*-----------------------------------Nhap MSSV----------------------------------- */

        do {
            System.out.print("Nhap MSSV can them: ");
            String mssvInput = sc.nextLine();

            if (mssvInput.equalsIgnoreCase("back")) {
                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'ESC'
                return;
            }

            mssvInput = mssvInput.trim();

            // Kiểm tra MSSV hợp lệ (định dạng số nguyên dương) và không trùng lặp
            if (Utils.kiemTraGiaTri(mssvInput) && !checkDuplicateStudentID(mssvInput, danhSachSinhVien)) {
                int mssv = Integer.parseInt(mssvInput);
                newStudent.setMssv(mssv);

                // Thoát khỏi vòng lặp khi MSSV hợp lệ và không trùng lặp
                break;
            } else {
                System.out.print("Vui long nhap lai! ");
            }
        } while (true);

        /*------------------------------------Nhập Họ tên-------------------------------- */

        do {
            System.out.print("Nhap ho ten: ");
            String hoTen = sc.nextLine();

            if (hoTen.equalsIgnoreCase("back")) {
                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'ESC'
                return;
            }

            hoTen = hoTen.trim();

            // Kiểm tra chuỗi họ tên và loại bỏ khoảng trắng không cần thiết
            if (!Utils.kiemTraRong(hoTen)) {
                newStudent.setHoTen(hoTen);
                // Kết thúc vòng lặp khi họ tên hợp lệ
                break;
            } else {
                System.out.println("Ho ten khong duoc de trong.");
                System.out.print("Vui long nhap lai! ");
            }
        } while (true);

        /*------------------------------------Nhập Ngày sinh------------------------------- */

        Scanner scanner = new Scanner(System.in);
        String ngaySinh;

        do {
            System.out.print("Nhap ngay sinh (dinh dang dd/MM/yyyy): ");
            ngaySinh = scanner.nextLine();

            if (ngaySinh.equalsIgnoreCase("back")) {
                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                return;
            }
        } while (!Utils.kiemTraNgayThang(ngaySinh));
        newStudent.setNgaySinh(ngaySinh);

        /*-------------------------------------Nhập Khóa học------------------------------------ */
        do {
            System.out.print("Nhap so khoa hoc: ");
            String khoaInput = sc.nextLine();

            if (khoaInput.equalsIgnoreCase("back")) {
                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                return;
            }

            khoaInput = khoaInput.trim();

            if (Utils.kiemTraGiaTri(khoaInput)) {
                int khoa = Integer.parseInt(khoaInput);
                newStudent.setKhoa(khoa);
                break;
            } else {
                System.out.print("Vui long nhap lai! ");
            }

        } while (true);

        /*---------------------------------------Nhập Viện-------------------------------- */
        do {
            System.out.print("Nhap vien: ");
            String vien = sc.nextLine();

            if (vien.equalsIgnoreCase("back")) {
                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                return;
            }

            vien = vien.trim();

            // Kiểm tra chuỗi Viện và loại bỏ khoảng trắng không cần thiết
            if (!Utils.kiemTraRong(vien)) {
                newStudent.setVien(vien);
                // Kết thúc vòng lặp khi họ tên hợp lệ
                break;
            } else {
                System.out.println("Vien khong duoc de trong.");
                System.out.print("Vui long nhap lai! ");
            }
        } while (true);

        /*-----------------------------------------Nhập lớp------------------------------------ */
        do {
            System.out.print("Nhap lop: ");
            String lop = sc.nextLine();

            if (lop.equalsIgnoreCase("back")) {
                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                return;
            }

            lop = lop.trim();

            // Kiểm tra chuỗi Viện và loại bỏ khoảng trắng không cần thiết
            if (!Utils.kiemTraRong(lop)) {
                newStudent.setLop(lop);
                // Kết thúc vòng lặp khi họ tên hợp lệ
                break;
            } else {
                System.out.println("Lop khong duoc de trong.");
                System.out.print("Vui long nhap lai! ");
            }
        } while (true);

        danhSachSinhVien.add(newStudent);

        List<Student> students = new ArrayList<>();
        students.add(newStudent);
        printStudents(students);
        saveListToFile("maindata.txt");
    }

    /*------------------------------------------------------Hàm đọc dữ liệu từ file----------------------------------------------------*/
    public List<Student> readDataFromFile(String fileName) {
        List<Student> sinhVienMoi = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<Integer> errorlines1 = new ArrayList<Integer>();
            List<Integer> errorlines2 = new ArrayList<Integer>();

            // số dòng
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {

                // đếm dòng
                lineNum++;

                // nếu hàng bị trống thì tiếp tục sang hàng tiếp
                if (line.isEmpty()) {
                    continue;
                }

                // phân chia các phần bằng dấu ","
                String[] parts = line.split(",");
                int mssv, soKhoa;
                String hoTen, ngaySinh, vien, lop;
                try {
                    mssv = Integer.parseInt(parts[0]);
                    for (int i = 0; i < parts.length; i++) {
                        parts[i] = parts[i].trim();
                    }
                    hoTen = parts[1];
                    ngaySinh = parts[2];
                    soKhoa = Integer.parseInt(parts[3]);
                    vien = parts[4];
                    lop = parts[5];

                    // Định nghĩa định dạng cho DateTimeFormatter
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(ngaySinh, formatter);

                    // năm sinh lớn hơn 2006 thì thêm dòng hiện tại vào mảng errorLines
                    if (date.getYear() >= 2006) {
                        errorlines1.add(lineNum);
                        continue;
                    }

                } catch (Exception e) {

                    // nếu mssv hoặc soKhoa hoặc ngaySsinh của 1 dòng trong file không đúng định
                    // dạng thì thêm dòng hiện tại vào mảng errorLines
                    errorlines1.add(lineNum);
                    continue;
                }

                // Kiểm tra MSSV trong danh sách có trùng với MSSV trong file vừa nhập không
                boolean mssvExists = false;
                for (Student student : danhSachSinhVien) {
                    if (student.getMssv() == mssv) {
                        mssvExists = true;
                        break;
                    }
                }

                // nếu họ tên hoặc ngày sinh hoặc viện của 1 dòng trong file bị rỗng thì thêm
                // dòng hiện tại vào mảng errorLines errorlines.add(lineNum);

                if (hoTen.isEmpty() || ngaySinh.isEmpty() || vien.isEmpty() || lop.isEmpty()) {
                    continue;
                }
                if (!mssvExists) {
                    Student student = new Student(mssv, hoTen, ngaySinh, soKhoa, vien, lop);
                    sinhVienMoi.add(student);
                    danhSachSinhVien.add(student);

                } else {

                    errorlines2.add(lineNum);
                }
            }


            if (errorlines1.size() > 0) {
                System.out.println("Thong tin khong dung dinh dang hoac bi thieu o cac dong: " + errorlines1);
            }

            if (errorlines2.size() > 0) {
                System.out.println("");
                System.out.println("MSSV o cac dong " + errorlines2+ " trong file bi trung voi MSSV da nhap truoc do trong danh sach");
            }

            if (errorlines1.size() > 0 || errorlines2.size() > 0) {
                System.out.println("");
            }
            if (errorlines1.size() > 0 || errorlines2.size() > 0) {
                System.out.println("Thong tin cua cac dong nay se khong duoc doc vao chuong trinh");
            }
        } catch (IOException e) {
            System.out.println("");
            System.out.println("Loi khi doc du lieu " + e.getMessage());
        }
        return sinhVienMoi;
    }

    /*------------------------------------------------------Hàm cập nhật sinh viên-----------------------------------------------------*/

    public void updateStudent() {
        do {
            System.out.print("Nhap MSSV can cap nhat: ");

            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();

            if (id.equalsIgnoreCase("back")) {
                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                return;
            }
            id = id.trim();

            if (Utils.kiemTraGiaTri(id)) {
                int mssv = Integer.parseInt(id);
                boolean timSinhVien = false;

                // Tìm sinh viên với MSSV tương ứng trong danh sách
                for (Student student : danhSachSinhVien) {
                    if (student.getMssv() == mssv) {
                        timSinhVien = true;
                        Scanner scanner = new Scanner(System.in);

                        /*---------------------------------------Nhập tên mới------------------------------------ */
                        System.out.print(
                                "Nhap thong tin moi hoac nhan Enter neu khong muon thay doi thong tin  \nNhap Ho va Ten moi: ");
                        String fullName = scanner.nextLine();

                        if (fullName.equalsIgnoreCase("back")) {
                            // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                            return;
                        }

                        // Loại bỏ các khoảng trắng không cần thiết từ tên mới
                        fullName = fullName.trim();

                        // Nếu họ tên nhập vào bị rỗng hoặc toàn dấu cách thì giữ nguyên họ tên cũ
                        if (!Utils.kiemTraRong(fullName)) {
                            student.setHoTen(fullName);
                        }

                        /*---------------------------------------Nhập ngày sinh mới------------------------------------ */

                        String ngaySinh;
                        boolean hopLe = false;

                        do {
                            System.out.print("Nhap ngay sinh moi (dinh dang dd/MM/yyyy): ");
                            ngaySinh = scanner.nextLine();

                            if (ngaySinh.equalsIgnoreCase("back")) {
                                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                                return;
                            }

                            ngaySinh = ngaySinh.trim();
                            // Kiểm tra xem khoa nhập vào có phải dạng số hay không
                            if (Utils.kiemTraRong(ngaySinh)) {

                                // Đánh dấu khoa đã hợp lệ
                                hopLe = true;
                            } else if (Utils.kiemTraNgayThang(ngaySinh)) {

                                student.setNgaySinh(ngaySinh);

                                // Đánh dấu khoa đã hợp lệ (rỗng)
                                hopLe = true;

                            } else {
                                System.out.print("Vui long nhap lai! ");
                            }
                        } while (!hopLe);

                        /*---------------------------------------Nhập Khóa mới------------------------------------ */
                        do {
                            System.out.print("Nhap khoa moi (Nhap so): ");
                            String khoaInput = sc.nextLine();

                            if (khoaInput.equalsIgnoreCase("back")) {
                                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                                return;
                            }

                            // Loại bỏ các khoảng trắng không cần thiết từ khóa mới
                            khoaInput = khoaInput.trim();

                            // Kiểm tra xem khoa nhập vào có phải dạng số hay không
                            if (Utils.kiemTraRong(khoaInput)) {

                                // Đánh dấu khoa đã hợp lệ
                                hopLe = true;
                            } else if (Utils.kiemTraGiaTri(khoaInput)) {

                                Integer khoa = Integer.parseInt(khoaInput);
                                student.setKhoa(khoa);

                                // Đánh dấu khoa đã hợp lệ (rỗng)
                                hopLe = true;

                            } else {
                                System.out.print("Vui long nhap lai! ");
                            }
                        } while (!hopLe);

                        /*---------------------------------------Nhập viện mới------------------------------------ */
                        System.out.print("Nhap vien moi: ");
                        String vien = scanner.nextLine();

                        if (vien.equalsIgnoreCase("back")) {
                            // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                            return;
                        }

                        // Loại bỏ các khoảng trắng không cần thiết từ Viện mới
                        vien = vien.trim();

                        // Nếu viện nhập vào bị rỗng hoặc toàn dấu cách thì giữ nguyên họ tên cũ
                        if (!Utils.kiemTraRong(vien)) {
                            student.setVien(vien);
                        }

                        /*---------------------------------------Nhập lớp mới------------------------------------ */
                        System.out.print("Nhap lop moi: ");
                        String className = scanner.nextLine();

                        if (className.equalsIgnoreCase("back")) {
                            // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                            return;
                        }

                        // Loại bỏ các khoảng trắng không cần thiết từ lớp mới
                        className = className.trim();

                        // Nếu lớp nhập vào bị rỗng hoặc toàn dấu cách thì giữ nguyên họ tên cũ
                        if (!Utils.kiemTraRong(className)) {
                            student.setLop(className);
                        }

                        List<Student> students = new ArrayList<>();
                        students.add(student);
                        printStudents(students);

                        saveListToFile("maindata.txt");
                        break;
                    }
                }
                if (timSinhVien) {
                    break;
                } else {
                    System.out.println("MSSV khong ton tai trong danh sach.");
                    return;
                }
            } 
        } while (true);
    }

    /*------------------------------------------------------Hàm xóa sinh viên----------------------------------------------------*/
    public void deleteStudent() {
        do {
            System.out.print("Nhap MSSV can xoa: ");
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();

            if (id.equalsIgnoreCase("back")) {
                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                return;
            }

            id = id.trim();

            if (Utils.kiemTraGiaTri(id)) {
                int mssv = Integer.parseInt(id);

                // Tạo biến để kiểm tra xem có tìm thấy sinh viên cần xóa không
                boolean found = false;

                // Duyệt qua danh sách sinh viên và tìm sinh viên có MSSV tương ứng
                for (Student student : danhSachSinhVien) {
                    if (student.getMssv() == mssv) {
                        found = true;
                        danhSachSinhVien.remove(student); 
                        break; 
                    }
                }

                if (found) {
                    System.out.println("Da xoa sinh vien co MSSV: " + mssv);

                    saveListToFile("maindata.txt");
                    return;
                } else {
                    System.out.println("Khong tim thay sinh vien co MSSV: " + mssv);
                    return;
                }
            } else {
                System.out.println("MSSV khong hop le. Vui long nhap lai.");
            }
        } while (true);
    }

    /*------------------------------------------------------Hàm sắp xếp theo lớp----------------------------------------------------*/
    public static void quickSort(List<Student> danhSachSinhVien, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(danhSachSinhVien, low, high);
            quickSort(danhSachSinhVien, low, pivotIndex - 1);
            quickSort(danhSachSinhVien, pivotIndex + 1, high);
        }
    }

    public void sortByClass() {
        int low = 0;
        int high = danhSachSinhVien.size() - 1;
        quickSort(danhSachSinhVien, low, high);
        System.out.println("Da sap xep danh sach");
        printStudents(danhSachSinhVien);
        saveListToFile("maindata.txt");
    }

    public static int partition(List<Student> danhSachSinhVien, int low, int high) {
        String pivot = danhSachSinhVien.get(high).getLop();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (danhSachSinhVien.get(j).getLop().compareToIgnoreCase(pivot) <= 0) {
                i++;
                swap(danhSachSinhVien, i, j);
            }
        }
        swap(danhSachSinhVien, i + 1, high);
        return i + 1;
    }

    public static void swap(List<Student> danhSachSinhVien, int i, int j) {
        Student temp = danhSachSinhVien.get(i);
        danhSachSinhVien.set(i, danhSachSinhVien.get(j));
        danhSachSinhVien.set(j, temp);
    }

    /*------------------------------------------------------Hàm tìm kiếm theo tên-----------------------------------------------------*/
    public void searchByName() {
        boolean hopLe = false;
        do {
            System.out.print("Nhap ho ten can tim kiem: ");
            Scanner sc = new Scanner(System.in);
            String hoTen = sc.nextLine();

            if (hoTen.equalsIgnoreCase("back")) {
                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                return;
            }

            hoTen = hoTen.trim();
            if (!Utils.kiemTraRong(hoTen)) {
                List<Student> students = new ArrayList<>();
                for (Student student : danhSachSinhVien) {

                    String hoVaTen = student.getHoTen().toLowerCase();
                    if (hoVaTen.contains(hoTen.toLowerCase())) {
                        students.add(student);
                    }
                }
                if (students.size() == 0) {
                    System.out.println("Khong tim thay sinh vien trong ten co chua: " + hoTen);
                    hopLe = true;

                } else {
                    System.out.println("Thong tin sinh vien co chua " + hoTen + " la:");
                    printStudents(students);
                    hopLe = true;
                }
            } else {
                System.out.print("Khong duoc bo trong.Vui long nhap lai! ");
            }
        } while (!hopLe);
    }

    /*------------------------------------------------------Hàm tìm kiếm theo MSSV-----------------------------------------------------*/
    public void searchByStudentID() {
        boolean hopLe = false;
        do {
            System.out.print("Nhap MSSV muon tim kiem:");
            Scanner sc = new Scanner(System.in);
            String mssv = sc.nextLine();

            if (mssv.equalsIgnoreCase("back")) {
                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                return;
            }

            mssv = mssv.trim();

            if (Utils.kiemTraGiaTri(mssv)) {
                int id = Integer.parseInt(mssv);
                List<Student> students = new ArrayList<>();
                for (Student student : danhSachSinhVien) {
                    if (student.getMssv() == id) {
                        students.add(student);
                    }
                }
                if (students.size() == 0) {
                    System.out.println("Khong tim thay sinh vien co MSSV la: " + mssv);
                    hopLe = true;

                } else {
                    System.out.println("Thong tin sinh vien co MSSV " + mssv + " la:");
                    printStudents(students);
                    hopLe = true;
                }

            } else {
                System.out.print("Vui long nhap lai! ");
            }
        } while (!hopLe);
    }

    /*------------------------------------------------------Hàm tìm kiếm theo lớp-----------------------------------------------------*/
    public void searchByClassName(String lop) {

        lop = lop.trim();

        List<Student> students = new ArrayList<>();
        for (Student student : danhSachSinhVien) {
            if (student.getLop().equalsIgnoreCase(lop.toLowerCase())) {
                students.add(student);
            }
        }
        if (students.size() == 0) {
            System.out.println("Khong tim thay sinh vien trong lop : " + lop);
        } else {
            System.out.println("Thong tin sinh vien trong lop " + lop + " la:");
            printStudents(students);
        }
    }
    /*------------------------------------------------------ Hàm hiển thị toàn bộ danh sách sinh viên ra màn hình Console-----------------------------------------------------*/

    public void printStudentList() {
        printStudents(danhSachSinhVien);
    }
}
