package giang.kma.webbansach_backend.entity;

import java.util.List;

import lombok.Data;

/**
 * HinhThucGiaoHang
 */
@Data

public class HinhThucGiaoHang {
      private int maHinhThucGiaoHang;
    private String tenHinhThucGiaoHang;
    private String moTa;
    private double chiPhiGiaoHang;
    private List<DonHang> danhSachDonHang;

}
