package com.mycompany.baithi;



public class ThongBaoPM implements QuanSat {
    @Override
    public void capNhat(String thongBao, NV nv) {
        System.out.println("[PM] " + thongBao + ": " + nv);
    }
}
