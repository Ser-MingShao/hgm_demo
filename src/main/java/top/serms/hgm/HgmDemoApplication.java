package top.serms.hgm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("top.serms.hgm.mapper")
public class HgmDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HgmDemoApplication.class, args);
    }
}
