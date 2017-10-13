package com.example.demo;

import java.io.IOException;
import java.io.Reader;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class SpringReservationServiceApplication {
	
	@RequestMapping("/hello")
	String index() {
		return "Hello Spring Boot";
	}	

	@RequestMapping("/hello2")
	String index2(String str) {
		return "Hello Spring Boot"+str;
	}
	
	@GET
	@RequestMapping("/getorderbill")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloWorld() {
try {
//			// 1、获取资源文件
//			Reader reader = Resources.getResourceAsReader("mybatisconfig.xml");
//
//			// 2、得到SessionFactory,使用类加载获取xml文件
//			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
//
//			// 3、打开session，创建能执行配置文件中的sql:sqlsession
//			SqlSession session = factory.openSession();
//
//			// 4、查询一个对象：session.selectOne(注册过的命名空间sql映射+select的ID)
//			PRODUCT p = session.selectOne("A.B.C.selectPRODUCTById", 25);
//
//			System.out.println(p.getPRODUCTNAME());
//			return p.getPRODUCTCOMPANY();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Hello World!";
	}
	
	public static void main(String[] args) {
		//SpringApplication.run(SpringReservationServiceApplication.class, args);

	    new SpringApplicationBuilder(SpringReservationServiceApplication.class)
	            .run(args);
	}
}
