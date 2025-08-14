package kz.edu.iitu.module_main;

import kz.edu.iitu.NewsService;
import kz.edu.iitu.module_web2.NewsController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Config {


    @Bean
    public NewsService newsService() {
        return new NewsService();
    }

    @Bean
    public NewsController newsController(NewsService newsService) {
        return new NewsController(newsService);
    }


}
