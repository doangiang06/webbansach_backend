package giang.kma.webbansach_backend.entity;

import java.util.List;

import lombok.Data;

/**
 * Quyen
 */
@Data 
public class Quyen {
    private int maQuyen;
    private String tenQuyen;
    private List<NguoiDung> danhSachNguoiDung;

}
