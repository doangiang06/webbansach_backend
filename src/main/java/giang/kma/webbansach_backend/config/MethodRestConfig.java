package giang.kma.webbansach_backend.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import giang.kma.webbansach_backend.entity.TheLoai;

@Configuration 
public class MethodRestConfig implements RepositoryRestConfigurer{
private String url = "http://localhost:8080";
    @Autowired
    private EntityManager entityManager;
@Override 
public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    // TODO Auto-generated method stub
    RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
    HttpMethod[] chanCacPhuongThuc={
        HttpMethod.POST,
        HttpMethod.PUT,
        HttpMethod.PATCH,
        HttpMethod.DELETE
    };
            // Tự động expose ID cho toàn bộ các Entity trong dự án
        config.exposeIdsFor(
            entityManager.getMetamodel()
                         .getEntities()
                         .stream()
                         .map(Type::getJavaType)
                         .toArray(Class[]::new));
            // chỉ expose id cho Thể loại
    //  config.exposeIdsFor(TheLoai.class);
    //cấu hình riêng chi thể loại
    chanHttpMethods(TheLoai.class, config, chanCacPhuongThuc);
}
public void chanHttpMethods(Class c,RepositoryRestConfiguration config,HttpMethod[] methods ){
         config.getExposureConfiguration()
         .forDomainType(c)
         .withItemExposure(((metdata,httpMethods) -> httpMethods.disable(methods)))
         .withCollectionExposure(((metdata,httpMethods) -> httpMethods.disable(methods)));
}

}
