package giang.kma.webbansach_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giang.kma.webbansach_backend.entity.TheLoai;

@Repository 
public interface TheLoaiRepository extends JpaRepository<TheLoai, Integer> {

}
