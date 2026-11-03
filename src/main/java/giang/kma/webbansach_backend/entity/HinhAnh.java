package giang.kma.webbansach_backend.entity;

import lombok.Data;

/**
 * HinhAnh
 */
@Data 
public class HinhAnh {
    private int maHinhAnh;
    private String tenHinhAnh;
    private boolean laIcon;
    private String duongDan;
    private String duLieuAnh;
    private Sach sach;
    

}
