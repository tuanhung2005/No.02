package com.mycompany.baithi;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        TableView<NV> tableView = new TableView<>();

        TableColumn<NV, String> idColumn = new TableColumn<>("Mã NV");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("maNV"));

        TableColumn<NV, String> nameColumn = new TableColumn<>("Tên NV");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));

        TableColumn<NV, String> dateColumn = new TableColumn<>("Ngày vào làm");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("ngayVaoLam"));

        TableColumn<NV, String> positionColumn = new TableColumn<>("Cách trả");
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("cachTra"));

        TableColumn<NV, Double> salaryColumn = new TableColumn<>("Lương");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("luongKhoiDiem"));

        tableView.getColumns().addAll(idColumn, nameColumn, dateColumn, positionColumn, salaryColumn);


        var qlnv = new QLNV();
        ObservableList<NV> nvs = FXCollections.observableArrayList();
        var list = qlnv.getNVs();
        for (NV nv : list) {
            nvs.add(nv);
        }
        tableView.setItems(nvs);

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Danh sách nhân viên");
        primaryStage.show();

    }

    public static void main(String[] args) {
        QLNV qlnv = QLNV.getInstance();

        HashMap<String, NV> dsNV = NV.nhapDanhSachNhanVien();
        
        qlnv.themQuanSat(new ThongBaoHR());
        qlnv.themQuanSat(new ThongBaoPM());

        for (NV nv : dsNV.values()) {
            qlnv.themNhanVien(nv);
            boolean result = qlnv.insertEmployeeIntoDatabase(nv);  
        }
        
        
        CompletableFuture<List<NV>> sortedFuture = qlnv.sapXepTheoLuongKhoiDiem(dsNV);

        sortedFuture.thenAccept(danhSach -> {
            System.out.println("Danh sách nhân viên đã sắp xếp theo lương khởi điểm:");
            qlnv.hienThiDanhSachNhanVien(danhSach);
        });
        
        
        
        launch();
    }
    
}
