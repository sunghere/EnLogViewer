package kr.co.ensof;

/*@SpringBootApplication
@EnableScheduling
public class ProjectApplication extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }
}*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EnsofApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(EnsofApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EnsofApplication.class);
    }
}
