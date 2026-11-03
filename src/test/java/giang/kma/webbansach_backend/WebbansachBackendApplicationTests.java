package giang.kma.webbansach_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import giang.kma.webbansach_backend.entity.TheLoai;

@SpringBootTest
class WebbansachBackendApplicationTests {

	@Test
	void contextLoads() {
		TheLoai theLoai = new TheLoai();
		theLoai.setMaTheLoai(1);
		theLoai.setTenTheLoai("Kinh tế");
		System.out.println("Mã thể loại: " + theLoai.getMaTheLoai());
		System.out.println("Tên thể loại: " + theLoai.getTenTheLoai());

	}

}
