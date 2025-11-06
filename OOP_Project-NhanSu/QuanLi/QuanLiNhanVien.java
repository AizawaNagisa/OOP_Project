package DichVu.QuanLi;

import DichVu.NhanSu.NhanVien;
import DichVu.NhanSu.NhanVienBanHang;
import DichVu.NhanSu.NhanVienDungBep;
import DichVu.NhanSu.QuanLi;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class QuanLiNhanVien extends QuanLiChung {
    private int tongSoNhanVien;
    private List<NhanVien> dsNhanVien = new ArrayList<>();

    public QuanLiNhanVien() {
        super();
        tongSoNhanVien = 0;
    }

    @Override
    public void them(Object obj) {
        if (obj instanceof NhanVien) {
            dsNhanVien.add((NhanVien) obj);
            tongSoNhanVien++;
        }
    }

    @Override
    public void xoa(String maNV) {
        dsNhanVien.removeIf(nv -> nv.getMaNV().equalsIgnoreCase(maNV));
    }

    @Override
    public void sua(String maNV, Object newInfo) {
        for (NhanVien nv : dsNhanVien) {
            if (nv.getMaNV().equalsIgnoreCase(maNV) && newInfo instanceof NhanVien) {
                NhanVien nvMoi = (NhanVien) newInfo;
                nv.setHoTen(nvMoi.getHoTen());
                nv.setLoaiCongViec(nvMoi.getLoaiCongViec());
                nv.setCmnd(nvMoi.getCmnd());
                nv.setSoNgayNghi(nvMoi.getSoNgayNghi());
                nv.setNamVaoLam(nvMoi.getNamVaoLam());
                nv.setHeSoLuong(nvMoi.getHeSoLuong());
            }
        }
    }

    @Override
    public Object timKiem(String maNV) {
        for (NhanVien nv : dsNhanVien) {
            if (nv.getMaNV().equalsIgnoreCase(maNV)) {
                return nv;
            }
        }
        return null;
    }

    public void sapXepNVTheoLuong() {
        dsNhanVien.sort(Comparator.comparingDouble(NhanVien::tinhLuong));
    }

    public void hienThi() {
        if (dsNhanVien.isEmpty()) {
            System.out.println("Danh sach rong!");
            return;
        }
        for (NhanVien nv : dsNhanVien) {
            nv.xuatNhanVien();
        }
    }

    // ===============================
    // MENU chính (switch-case truyền thống)
    // ===============================
    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== MENU QUAN LI NHAN VIEN =====");
            System.out.println("1. Them nhan vien");
            System.out.println("2. Xoa nhan vien");
            System.out.println("3. Sua thong tin nhan vien");
            System.out.println("4. Tim kiem nhan vien");
            System.out.println("5. Sap xep nhan vien theo luong");
            System.out.println("6. Hien thi danh sach nhan vien");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            choice = sc.nextInt();
            sc.nextLine(); // bỏ ký tự \n

            switch (choice) {
                case 1:
                    themNhanVien();
                    break;
                case 2:
                    xoaNhanVien();
                    break;
                case 3:
                    suaNhanVien();
                    break;
                case 4:
                    timNhanVien();
                    break;
                case 5:
                    sapXepNVTheoLuong();
                    System.out.println("✅ Da sap xep nhan vien theo luong!");
                    break;
                case 6:
                    hienThi();
                    break;
                case 0:
                    System.out.println("👋 Thoat khoi menu quan li nhan vien.");
                    break;
                default:
                    System.out.println("❌ Lua chon khong hop le!");
                    break;
            }

        } while (choice != 0);
    }

    // ===============================
    // Các phương thức con
    // ===============================
    private void themNhanVien() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nChon loai nhan vien muon them:");
        System.out.println("1. Nhan vien ban hang");
        System.out.println("2. Nhan vien dung bep");
        System.out.println("3. Nhan vien quan li");
        System.out.print("Nhap lua chon: ");
        int loaiNV = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhap ma NV: ");
        String maNV = sc.nextLine();
        System.out.print("Nhap ho ten: ");
        String hoTen = sc.nextLine();
        System.out.print("Nhap loai cong viec: ");
        String loaiCV = sc.nextLine();
        System.out.print("Nhap CMND: ");
        String cmnd = sc.nextLine();
        System.out.print("Nhap so ngay nghi: ");
        int ngayNghi = sc.nextInt();
        System.out.print("Nhap nam vao lam: ");
        int thamNien = sc.nextInt();
        System.out.print("Nhap he so luong: ");
        double heSo = sc.nextDouble();

        NhanVien nv = null;
        switch (loaiNV) {
            case 1:
                System.out.print("Nhap doanh thu: ");
                double doanhThu = sc.nextDouble();
                System.out.print("Nhap doanh thu toi thieu: ");
                double doanhThuMin = sc.nextDouble();
                nv = new NhanVienBanHang(maNV, hoTen, loaiCV, cmnd, ngayNghi, thamNien, doanhThu ,doanhThuMin, heSo);
                break;
            case 2:
                System.out.print("Nhap so luong order: ");
                int soLuongOrder = sc.nextInt();
                System.out.print("Nhap quay phu trach: ");
                String quayPhuTrach = sc.nextLine();
                nv = new NhanVienDungBep(maNV, hoTen, loaiCV, cmnd, ngayNghi, thamNien, heSo, soLuongOrder, quayPhuTrach);
                break;
            case 3:
                System.out.print("Nhap phong ban: ");
                String phongBan = sc.nextLine();
                System.out.print("Nhap chi nhanh quan ly: ");
                String chiNhanhQL = sc.nextLine();
                System.out.print("Nhap phu cap quan ly: ");
                double phuCapQL = sc.nextDouble();
                nv = new QuanLi(maNV, hoTen, loaiCV, cmnd, ngayNghi, thamNien, heSo, phongBan, chiNhanhQL, phuCapQL);
                break;
            default:
                System.out.println("❌ Loai nhan vien khong hop le!");
                return;
        }

        them(nv);
        System.out.println("✅ Da them nhan vien!");
    }

    private void xoaNhanVien() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nhan vien can xoa: ");
        String ma = sc.nextLine();
        xoa(ma);
        System.out.println("✅ Da xoa nhan vien neu ton tai.");
    }

    private void suaNhanVien() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ma nhan vien can sua: ");
        String maNV = sc.nextLine();

        // Xác định loại nhân viên muốn sửa
        System.out.println("\nChon loai nhan vien muon sua:");
        System.out.println("1. Nhan vien ban hang");
        System.out.println("2. Nhan vien dung bep");
        System.out.println("3. Nhan vien quan li");
        System.out.print("Nhap lua chon: ");
        int loaiNV = sc.nextInt();
        sc.nextLine();

        // Nhập thông tin cơ bản
        System.out.print("Nhap ho ten moi: ");
        String hoTen = sc.nextLine();
        System.out.print("Nhap loai cong viec moi: ");
        String loaiCV = sc.nextLine();
        System.out.print("Nhap CMND moi: ");
        String cmnd = sc.nextLine();
        System.out.print("Nhap so ngay nghi moi: ");
        int ngayNghi = sc.nextInt();
        System.out.print("Nhap nam vao lam moi: ");
        int thamNien = sc.nextInt();
        System.out.print("Nhap he so luong moi: ");
        double heSo = sc.nextDouble();

        NhanVien nvMoi = null;

        switch (loaiNV) {
            case 1 -> {
                System.out.print("Nhap doanh thu moi: ");
                double doanhThu = sc.nextDouble();
                System.out.print("Nhap doanh thu toi thieu moi: ");
                double doanhThuMin = sc.nextDouble();
                nvMoi = new NhanVienBanHang(maNV, hoTen, loaiCV, cmnd, ngayNghi, thamNien, doanhThu, doanhThuMin, heSo);
            }
            case 2 -> {
                System.out.print("Nhap so luong order moi: ");
                int soLuongOrder = sc.nextInt();
                sc.nextLine(); // tránh lỗi trôi dòng
                System.out.print("Nhap quay phu trach moi: ");
                String quayPhuTrach = sc.nextLine();
                nvMoi = new NhanVienDungBep(maNV, hoTen, loaiCV, cmnd, ngayNghi, thamNien, heSo, soLuongOrder, quayPhuTrach);
            }
            case 3 -> {
                sc.nextLine(); // tránh lỗi trôi dòng
                System.out.print("Nhap phong ban moi: ");
                String phongBan = sc.nextLine();
                System.out.print("Nhap chi nhanh quan ly moi: ");
                String chiNhanhQL = sc.nextLine();
                System.out.print("Nhap phu cap quan ly moi: ");
                double phuCapQL = sc.nextDouble();
                nvMoi = new QuanLi(maNV, hoTen, loaiCV, cmnd, ngayNghi, thamNien, heSo, phongBan, chiNhanhQL, phuCapQL);
            }
            default -> {
                System.out.println("❌ Loai nhan vien khong hop le!");
                return;
            }
        }

        sua(maNV, nvMoi);
        System.out.println("✅ Da sua thong tin nhan vien thanh cong!");
    }



    private void timNhanVien() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nhan vien can tim: ");
        String ma = sc.nextLine();
        Object obj = timKiem(ma);
        if (obj instanceof NhanVien nv) {
            System.out.println("🔍 Tim thay nhan vien:");
            nv.xuatNhanVien();
        } else {
            System.out.println("❌ Khong tim thay nhan vien co ma " + ma);
        }
    }
}
