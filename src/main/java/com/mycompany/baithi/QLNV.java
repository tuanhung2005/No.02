package com.mycompany.baithi;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors; 
import org.hibernate.Session;
import org.hibernate.Transaction;


public class QLNV {

    private static QLNV instance;
    private HashMap<String, NV> dsNV;
    private List<QuanSat> dsQS; 

    public HashMap<String, NV> getDsNV() {
        return dsNV;
    }

    public void setDsNV(HashMap<String, NV> dsNV) {
        this.dsNV = dsNV;
    }

    public QLNV() {
        dsNV = new HashMap<>();
        dsQS = new ArrayList<>();
    }

    public void themQuanSat(QuanSat qs) {
        dsQS.add(qs);
    }
    
    public static QLNV getInstance() {
        if (instance == null) {
            instance = new QLNV();
        }
        return instance;
    }

    public void themNhanVien(NV nv) {
        dsNV.put(nv.getMaNV(), nv);
         
        for (QuanSat observer : dsQS) {
            if (nv.getCachTra().equals("duan") && observer instanceof ThongBaoPM) {
                observer.capNhat(nv.getMaNV(), nv);
            } else if (nv.getCachTra().equals("ngay") && observer instanceof ThongBaoHR) {
                observer.capNhat(nv.getMaNV(), nv);
            }
        }
    } 
    
    public CompletableFuture<List<NV>> sapXepTheoLuongKhoiDiem(HashMap<String, NV> dsNV) {
        return CompletableFuture.supplyAsync(() -> 
            dsNV.values().stream()
                .sorted(Comparator.comparingDouble(NV::getLuongKhoiDiem))
                .collect(Collectors.toList())
        ).thenApply(danhSach -> {
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sắp xếp hoàn thành.");
            return danhSach;
        });
    }

    public CompletableFuture<Void> hienThiDanhSachNhanVien(List<NV> danhSach) {
        return CompletableFuture.runAsync(() -> {
            if (danhSach.isEmpty()) {
                System.out.println("Danh sách nhân viên trống.");
            } else {
                danhSach.forEach(nv -> System.out.println(nv)); // In danh sách nhân viên
            }
            try {
                Thread.sleep(2000); // Delay 2 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hiển thị hoàn thành.");
        });
    }
    
    public List<NV> getNVs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) { 
            return session.createQuery("from NV", NV.class).list(); 
        }
    }

    boolean updateEmployeeInDatabase(NV updatedNV) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
    public boolean insertEmployeeIntoDatabase(NV newNV) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            NV existingEmployee = session.get(NV.class, newNV.getMaNV());

            if (existingEmployee == null) {
                session.save(newNV); 

                transaction.commit();
                return true;
            } else {
                System.out.println("Nhân viên với mã " + newNV.getMaNV() + " đã tồn tại.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }



}
