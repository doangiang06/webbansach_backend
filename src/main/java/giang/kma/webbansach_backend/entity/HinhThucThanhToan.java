package giang.kma.webbansach_backend.entity;

import java.util.List;

import lombok.Data;

/**
 * HinhThucThanhToan
 */
@Data 
public class HinhThucThanhToan {
    private int maHinhThucThanhToan;
    private String tenHinhThucThanhToan;
    private String moTa;
    private double chiPhiThanhToan;
    private List<DonHang> danhSachDonHang;
    

}
