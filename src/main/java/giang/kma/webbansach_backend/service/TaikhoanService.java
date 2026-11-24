package giang.kma.webbansach_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import giang.kma.webbansach_backend.dao.NguoiDungRepository;
import giang.kma.webbansach_backend.entity.NguoiDung;
import giang.kma.webbansach_backend.entity.ThongBaoLoi;

@Service 
public class TaikhoanService {
    @Autowired 
    private NguoiDungRepository nguoiDungRepository;
    public ResponseEntity<?> dangKyNguoiDung(NguoiDung nguoiDung){
        // kiểm tả tên dang nhậpẫn tòn tại chưa
        if(nguoiDungRepository.existsByTenDangNhap(nguoiDung.getTenDangNhap())){
            return ResponseEntity.badRequest().body(new ThongBaoLoi("Tên đăng nhập đã tồn tại ."));
        }
         // kiểm tra email dang nhậpẫn tòn tại chưa
        if(nguoiDungRepository.existsByEmail(nguoiDung.getEmail())){
            return ResponseEntity.badRequest().body(new ThongBaoLoi("Email đã tồn tại ."));
        }
        // lưu người dùng vào cơ sở dũ liệu 
        NguoiDung nguoiDung_daDangKy = nguoiDungRepository.save(nguoiDung);
        return ResponseEntity.ok("Đăng ký thành công");

    }

}
