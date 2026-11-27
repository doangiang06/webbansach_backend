package giang.kma.webbansach_backend.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import giang.kma.webbansach_backend.entity.NguoiDung;
import giang.kma.webbansach_backend.entity.TheLoai;

@Configuration
public class MethodRestConfig implements RepositoryRestConfigurer {

    private String url = "http://localhost:3000";

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // CORS cho phép frontend ở localhost:3000 gọi API và gửi preflight OPTIONS
        cors.addMapping("/**")
            .allowedOrigins(url)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*");

        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        // Tự động expose ID cho toàn bộ Entity trong dự án
        config.exposeIdsFor(
            entityManager.getMetamodel()
                         .getEntities()
                         .stream()
                         .map(Type::getJavaType)
                         .toArray(Class[]::new)
        );

        // Chặn các phương thức ghi/sửa/xóa trên Spring Data REST của TheLoai
        HttpMethod[] chanCacPhuongThuc = {
            HttpMethod.POST,
            HttpMethod.PUT,
            HttpMethod.PATCH,
            HttpMethod.DELETE
        };
        chanHttpMethods(TheLoai.class, config, chanCacPhuongThuc);

        // Chặn riêng phương thức DELETE trên Spring Data REST của NguoiDung
        HttpMethod[] phuongThucDelete = {
            HttpMethod.DELETE
        };
        chanHttpMethods(NguoiDung.class, config, phuongThucDelete);
    }

    public void chanHttpMethods(Class c, RepositoryRestConfiguration config, HttpMethod[] methods) {
        config.getExposureConfiguration()
              .forDomainType(c)
              .withItemExposure((metdata, httpMethods) -> httpMethods.disable(methods))
              .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(methods));
    }
}