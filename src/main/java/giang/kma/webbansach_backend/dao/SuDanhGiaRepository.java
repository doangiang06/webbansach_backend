package giang.kma.webbansach_backend.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import giang.kma.webbansach_backend.entity.SuDanhGia;


@RepositoryRestResource (path = "su-danh-gia")
public interface SuDanhGiaRepository extends JpaRepository<SuDanhGia, Integer> {
  Page<SuDanhGia> findBySach_MaSach(
            @Param("maSach") int maSach,
            Pageable pageable
    );
}
