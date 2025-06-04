package com.mycompany.baithi;




public class ThongBaoHR implements QuanSat {
    @Override
    public void capNhat(String thongBao, NV nv) {
        System.out.println("[HR] " + thongBao + ": " + nv);
    }
}
