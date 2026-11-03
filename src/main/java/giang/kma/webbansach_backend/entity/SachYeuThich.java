package giang.kma.webbansach_backend.entity;

import lombok.Data;

/**
 * SachYeuThich
 */
@Data 
public class SachYeuThich {
    private int maSachYeuThich;
    private Sach sach;
    private NguoiDung nguoiDung;

}
