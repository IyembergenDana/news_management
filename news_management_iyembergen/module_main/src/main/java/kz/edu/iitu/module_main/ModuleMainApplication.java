package kz.edu.iitu.module_main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
//@Import(Config.class)
//@ComponentScan
//@EnableConfigurationProperties
@EntityScan(basePackages = {"kz.edu.iitu.models"})
@EnableJpaRepositories(basePackages = {"kz.edu.iitu"})
public class ModuleMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleMainApplication.class, args);
//        Repository r = new Repository();
//        System.out.println(r.authors);
//        System.out.println(r.createAuthor("Test User"));
//        System.out.println(r.authors);
    }

}
