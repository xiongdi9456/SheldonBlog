package com.sheldon.sheldonblog;

import com.sheldon.sheldonblog.common.MyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.sheldon.sheldonblog.dao",markerInterface = MyMapper.class)
public class SheldonblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SheldonblogApplication.class, args);
	}
}
