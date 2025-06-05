package com.mycompany.baithi;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;


@Entity
@Table(name="nhanvien")
public class NV {
    @Id  
    private String maNV;          
    private String hoTen;    
    private LocalDate ngayVaoLam; 
    private double luongKhoiDiem;  
    private String cachTra;        

    public NV(String maNV, String hoTen, LocalDate ngayVaoLam, double luongKhoiDiem, String cachTra) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.ngayVaoLam = ngayVaoLam;
        this.luongKhoiDiem = luongKhoiDiem;
        this.cachTra = cachTra;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public double getLuongKhoiDiem() {
        return luongKhoiDiem;
    }

    public void setLuongKhoiDiem(double luongKhoiDiem) {
        this.luongKhoiDiem = luongKhoiDiem;
    }

    public String getCachTra() {
        return cachTra;
    }

    public void setCachTra(String cachTra) {
        this.cachTra = cachTra;
    }

    public static HashMap<String, NV> nhapDanhSachNhanVien() {
        HashMap<String, NV> dsNV = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Nhập mã NV (# để thoát): ");
                String maNV = scanner.nextLine();
                if (maNV.equals("#")) break;

                System.out.print("Nhập họ tên: ");
                String hoTen = scanner.nextLine();

                System.out.print("Nhập ngày vào làm (yyyy-MM-dd): ");
                LocalDate ngayVaoLam = LocalDate.parse(scanner.nextLine());

                System.out.print("Nhập lương khởi điểm: ");
                double luongKhoiDiem = Double.parseDouble(scanner.nextLine());

                System.out.print("Nhập cách trả lương (duan/ngay): ");
                String cachTra = scanner.nextLine();
                if (!cachTra.equals("duan") && !cachTra.equals("ngay")) {
                    throw new IllegalArgumentException("Cách trả lương không hợp lệ!");
                }

                NV nv = new NV(maNV, hoTen, ngayVaoLam, luongKhoiDiem, cachTra);
                dsNV.put(maNV, nv);
            } catch (Exception e) {
                System.out.println("Lỗi nhập dữ liệu: " + e.getMessage());
            }
        }
        return dsNV;
    }
    
    public NV() {
    }

    @Override
    public String toString() {
        return "NV{" +
                "maNV='" + maNV + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", ngayVaoLam=" + ngayVaoLam +
                ", luongKhoiDiem=" + luongKhoiDiem +
                ", cachTra='" + cachTra + '\'' +
                '}';
    }
}
