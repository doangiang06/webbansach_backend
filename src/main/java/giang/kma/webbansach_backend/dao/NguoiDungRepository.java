package giang.kma.webbansach_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giang.kma.webbansach_backend.entity.NguoiDung;

@Repository 
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {

}
