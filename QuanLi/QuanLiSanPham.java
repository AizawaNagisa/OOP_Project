package DichVu.QuanLi;

import DichVu.SanPham;

import java.util.ArrayList;
import java.util.List;

public class QuanLiSanPham extends QuanLiChung {
    private int tongSoSP;
    private String hanSD;
    List<SanPham> dsSanPham = new ArrayList<>();
    public double tinhGiaTriTonKho(){
        double giaTriTonKho = 0;
        for(SanPham sp : dsSanPham){
            giaTriTonKho += sp.getGiaThanh();
        }return giaTriTonKho;
    }
}
