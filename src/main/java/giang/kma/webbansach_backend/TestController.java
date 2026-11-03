package giang.kma.webbansach_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Xin chào! Ứng dụng Spring Boot đã kết nối MySQL thành công!";
    }
}