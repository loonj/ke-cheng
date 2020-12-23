package cn.zhanx.ke.cheng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.zhanx.ke.cheng.mapper")
@EnableTransactionManagement
public class KeChengApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeChengApplication.class, args);
	}

}
