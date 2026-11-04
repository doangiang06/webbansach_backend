package giang.kma.webbansach_backend.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
// mặc định spring boot ẩn id dưới dạng jsion
@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // Tự động expose ID cho toàn bộ các Entity trong dự án
        config.exposeIdsFor(
            entityManager.getMetamodel()
                         .getEntities()
                         .stream()
                         .map(Type::getJavaType)
                         .toArray(Class[]::new)
        );
    }
}