package giang.kma.webbansach_backend.entity;

import lombok.Data;

/**
 * ChiTietDonHang
 */
@Data 
public class ChiTietDonHang {
    private long chiTietDonHang;
    private int soLuong;
    private double giaBan;
    private Sach sach;
    private DonHang donHang;

}
