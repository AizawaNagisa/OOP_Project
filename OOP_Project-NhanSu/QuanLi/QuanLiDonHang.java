package DichVu.QuanLi;

import DichVu.DichVu.DonHang;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLiDonHang extends QuanLiChung {
    private int tongSoDH;
    private List<DonHang> dsDonHang = new ArrayList<>();

    @Override
    public void them(Object obj) {
        if (obj instanceof DonHang dh) {
            dsDonHang.add(dh);
            tongSoDH++;
        }
    }

    @Override
    public void xoa(String maDH) {
        dsDonHang.removeIf(dh -> dh.getMaSanPham().equalsIgnoreCase(maDH));
    }

    @Override
    public void sua(String maDH, Object newInfo) {
        for (DonHang dh : dsDonHang) {
            if (dh.getMaSanPham().equalsIgnoreCase(maDH) && newInfo instanceof DonHang dhMoi) {
                dh.setTenSanPham(dhMoi.getTenSanPham());
                dh.setMaSanPham(dhMoi.getMaSanPham());
                dh.setSoLuong(dhMoi.getSoLuong());
                dh.setNgayLap(dhMoi.getNgayLap());
            }
        }
    }

    @Override
    public Object timKiem(String maDH) {
        for (DonHang dh : dsDonHang) {
            if (dh.getMaSanPham().equalsIgnoreCase(maDH)) return dh;
        }
        return null;
    }

    public double tinhDoanhThuTheoNgay(String ngay) {
        return dsDonHang.stream()
                .filter(dh -> dh.getNgayLap().equalsIgnoreCase(ngay))
                .mapToDouble(DonHang::getTongTien)
                .sum();
    }

    public void hienThi() {
        if (dsDonHang.isEmpty()) {
            System.out.println("Danh sach don hang rong!");
            return;
        }
        for (DonHang dh : dsDonHang) {
            dh.xuat();
        }
    }

    // 🟩 MENU TRUYỀN THỐNG VỚI SWITCH-CASE
    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MENU QUAN LI DON HANG =====");
            System.out.println("1. Them don hang");
            System.out.println("2. Xoa don hang");
            System.out.println("3. Sua don hang");
            System.out.println("4. Tim kiem don hang");
            System.out.println("5. Hien thi danh sach don hang");
            System.out.println("6. Tinh doanh thu theo ngay");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhap ten san pham: ");
                    String tenSP = sc.nextLine();
                    System.out.print("Nhap ma san pham: ");
                    String maSP = sc.nextLine();
                    System.out.print("Nhap so luong: ");
                    int soLuong = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nhap ngay lap: ");
                    String ngayLap = sc.nextLine();
                    DonHang dhMoi = new DonHang(tenSP, maSP, soLuong, ngayLap);
                    Them(dhMoi);
                    System.out.println("✅ Da them don hang!");
                    break;

                case 2:
                    System.out.print("Nhap ma san pham can xoa: ");
                    String maXoa = sc.nextLine();
                    Xoa(maXoa);
                    System.out.println("✅ Da xoa don hang neu ton tai!");
                    break;

                case 3:
                    System.out.print("Nhap ma san pham can sua: ");
                    String maSua = sc.nextLine();
                    System.out.print("Nhap ten moi: ");
                    String tenMoi = sc.nextLine();
                    System.out.print("Nhap ma moi: ");
                    String maMoi = sc.nextLine();
                    System.out.print("Nhap so luong moi: ");
                    int slMoi = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nhap ngay lap moi: ");
                    String ngayMoi = sc.nextLine();
                    DonHang dhSua = new DonHang(tenMoi, maMoi, slMoi, ngayMoi);
                    Sua(maSua, dhSua);
                    System.out.println("✅ Da sua don hang!");
                    break;

                case 4:
                    System.out.print("Nhap ma san pham can tim: ");
                    String maTim = sc.nextLine();
                    Object kq = TimKiem(maTim);
                    if (kq instanceof DonHang dh) {
                        System.out.println("🔍 Tim thay don hang:");
                        dh.xuatDonHang();
                    } else {
                        System.out.println("❌ Khong tim thay don hang co ma " + maTim);
                    }
                    break;

                case 5:
                    hienThi();
                    break;

                case 6:
                    System.out.print("Nhap ngay can tinh doanh thu: ");
                    String ngay = sc.nextLine();
                    System.out.printf("💰 Tong doanh thu ngay %s: %.2f\n", ngay, tinhDoanhThuTheoNgay(ngay));
                    break;

                case 0:
                    System.out.println("👋 Thoat khoi quan li don hang!");
                    break;

                default:
                    System.out.println("❌ Lua chon khong hop le!");
            }

        } while (choice != 0);
    }
}
