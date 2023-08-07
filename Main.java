\\Bùi Minh Châu 20210110
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*--------------------------------------------------Hàm main xử lí chính----------------------------------------------------*/
    public static void main(String[] args) {
        QuanLySinhVien app = new QuanLySinhVien();
        Scanner scanner = new Scanner(System.in);

        app.readDataFromFile("maindata.txt");
        

        while (true) {
            System.out.println("\n ========== Chuong trinh quan ly sinh vien ==========");
            System.out.println(" |        1. Them moi sinh vien                      |");
            System.out.println(" |        2. Cap nhat sinh vien                      |");
            System.out.println(" |        3. Xoa sinh vien                           |");
            System.out.println(" |        4. Sap xep theo lop                        |");
            System.out.println(" |        5. Tim kiem sinh vien                      |");
            System.out.println(" |        6. Luu du lieu vao file                    |");
            System.out.println(" |        7. Hien thi danh sach sinh vien            |");
            System.out.println(" |        8. Thoat                                   |");
            System.out.println(" |  Nhap back de quay lai MENU chinh bat cu luc nao  |");
            System.out.println("  ----------------------------------------------------");
            System.out.println("So sinh vien hien co trong danh sach la: " + app.getDanhSachStudents().size());
            System.out.print("Nhap lua chon (Nhap so tu 1 den 8): ");


            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\n  ========= Lua chon theo tac them sinh vien ========");
                    System.out.println(" |        1. Them sinh vien tu Console              |");
                    System.out.println(" |        2. Them sinh vien tu File                 |");
                    System.out.println(" |        3. Quay lai                               |");
                    System.out.println("  ---------------------------------------------------");
                    System.out.print("Nhap lua chon (Nhap 1 hoac 2 hoac 3): ");

                    String choice1 = scanner.nextLine();
                    switch (choice1) {
                        case "1":
                            app.addNewStudentFromConsole();
                            break;
                        case "2":
                            System.out.print("Nhap ten file: ");
                            String docFile = scanner.nextLine();
                            List<Student> sinhVienMoi = app.readDataFromFile(docFile);
                            System.out.println("\n So sinh vien moi da them:" + sinhVienMoi.size());
                            app.printStudents(sinhVienMoi);
                            app.saveListToFile("maindata.txt");
                            break;
                        case "3":
                            break;
                        case "back":
                            break;
                        default:
                            System.out.print("Lua chon khong hop le.");
                    }
                    break;
                case "2":
                    app.updateStudent();
                    break;
                case "3":
                    app.deleteStudent();
                    break;
                case "4":
                    app.sortByClass();
                    break;
                case "5":
                    System.out.println("\n  ========== Lua chon phuong thuc tim kiem ==========");
                    System.out.println(" |        1. Tim kiem theo ten                       |");
                    System.out.println(" |        2. Tim kiem theo MSSV                      |");
                    System.out.println(" |        3. Tim kiem theo lop                       |");
                    System.out.println(" |        4. Quay lai                                |");
                    System.out.println("  ---------------------------------------------------");
                    System.out.print("Nhap lua chon (Nhap so tu 1 den 4): ");

                    String choice2 = scanner.nextLine();
                    switch (choice2) {
                        case "1":
                            app.searchByName();
                            break;
                        case "2":
                            app.searchByStudentID();
                            break;
                        case "3":
                            System.out.print("Nhap lop muon tim kiem: ");
                            String className = scanner.nextLine();
                            if (className.equalsIgnoreCase("back")) {
                                // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                                break;
                            }
                            app.searchByClassName(className);
                            break;
                        case "4":
                            break;
                        default:
                            System.out.print("Lua chon khong hop le.");
                    }
                    break;
                case "6":
                    System.out.print("Nhap ten file: ");
                    String luuFile = scanner.nextLine();

                    if (luuFile.equalsIgnoreCase("back")) {
                        // Thoát khỏi vòng lặp ngoài cùng khi nhấn 'back'
                        break;
                    }

                    boolean success = app.saveListToFile(luuFile);
                    if (success)
                        System.out.println("Da luu du lieu vao file " + luuFile + " thanh cong.");
                    else
                        System.out.println("Loi khi ghi du lieu vao fle" + luuFile);
                    break;
                case "7":
                    app.printStudentList();
                    break;
                case "8":
                    System.out.print("Thoat chuong trinh...");
                    return;
                case "back":
                    break;
                default:
                    System.out.print("Lua chon khong hop le.");
            }
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Nhap phim bat ky de quay lai MENU chinh ");
            System.out.print("Nhap x de thoat chuong trinh:  ");
            String c = scanner.nextLine();
            if (c.equals("x")) {
                System.out.println("Thoat chuong trinh...");
                break;
            }
        }
    }
}
