package DichVu.QuanLi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class QuanLiNhanVien extends QuanLiChung {
    private int tongSoNhanVien;
    List<NhanSu.NhanVien> dsNhanVien = new ArrayList<>();
    public QuanLiNhanVien() {

    }
    public void sortNVTheoLuong(){
        dsNhanVien.sort(Comparator.comparingDouble(NhanSu.NhanVien::tinhLuong));
    }
}
