package edu.jit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication标识springboot的启动类
//运行时，会由main方法来执行run方法
@SpringBootApplication
@MapperScan("edu.jit.mapper")
@ServletComponentScan("edu.jit.filter")
@EnableScheduling
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class,args);
    }
}
