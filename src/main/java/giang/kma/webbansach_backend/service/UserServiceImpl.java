package giang.kma.webbansach_backend.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import giang.kma.webbansach_backend.dao.NguoiDungRepository;
import giang.kma.webbansach_backend.dao.QuyenRepository;
import giang.kma.webbansach_backend.entity.NguoiDung;
import giang.kma.webbansach_backend.entity.Quyen;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private QuyenRepository quyenRepository;
    @Autowired
public UserServiceImpl(NguoiDungRepository nguoiDungRepository, QuyenRepository quyenRepository) {
    this.nguoiDungRepository = nguoiDungRepository;
    this.quyenRepository = quyenRepository;
}

    /**
     * Spring Security gọi hàm này khi người dùng đăng nhập.
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // Tìm người dùng theo tên đăng nhập
        NguoiDung nguoiDung =
                nguoiDungRepository.findByTenDangNhap(username);

        // Không tìm thấy tài khoản
        if (nguoiDung == null) {
            throw new UsernameNotFoundException(
                    "Tài khoản không tồn tại");
        }

        // Trả về User cho Spring Security
        return new User(
                nguoiDung.getTenDangNhap(),
                nguoiDung.getMatKhau(),
                rolesToAuthorities(nguoiDung.getDanhSachQuyen())
        );
    }

    /**
     * Chuyển danh sách quyền trong DB
     * thành danh sách GrantedAuthority.
     */
    private Collection<? extends GrantedAuthority> rolesToAuthorities(
            Collection<Quyen> quyens) {

        return quyens.stream()
                .map(quyen ->
                        new SimpleGrantedAuthority(
                                quyen.getTenQuyen()))
                .collect(Collectors.toList());
    }

    /**
     * Tìm người dùng theo tên đăng nhập.
     */
    @Override
    public NguoiDung findByUsername(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }
}