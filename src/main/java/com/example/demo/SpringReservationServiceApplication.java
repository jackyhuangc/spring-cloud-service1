package com.example.demo;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futures.entity.AccountSettle;
import com.futures.entity.AccountSettleMapper;
import com.futures.entity.EMP;
import com.futures.entity.empmapper;
import com.futures.entity.OrderBillEntity;
import com.futures.entity.OrderBillMapper;
import com.futures.entity.RegionDistribution;
import com.futures.entity.RegionDistributionMapper;
import com.futures.entity.TerminalDistribution;
import com.futures.entity.TerminalDistributionMapper;

@RestController
// ttt
//@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class SpringReservationServiceApplication {

	@RequestMapping("/hello")
	String index() {
		return "Hello Spring Boot";
	}

	@RequestMapping("/hello2")
	String index2(String str) {
		return "Hello Spring Boot" + str;
	}

	@GET
	@RequestMapping("/GETALL")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EMP> getListEMP() {
		try {
			Reader reader = Resources.getResourceAsReader("mybatisconfig.xml");

			// 创建
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

			// 解析资源
			SqlSessionFactory factory = builder.build(reader);

			// 打开session
			SqlSession session = factory.openSession();

			// 用接口映射的方式进行CURD操作，官方推荐
			empmapper empmapper = session.getMapper(empmapper.class);

			List<EMP> list = empmapper.queryall();

			System.out.println(String.format("共查询到%s条数据！", list.size()));

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@GET
	@RequestMapping("/GetOrderBillByTime")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderBillEntity> GetOrderBillByTime(String begin, String end) {
		try {

			System.out.println(begin);
			System.out.println(end);

			Reader reader = Resources.getResourceAsReader("mybatisconfig.xml");

			// 创建
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

			// 解析资源
			SqlSessionFactory factory = builder.build(reader);

			// 打开session
			SqlSession session = factory.openSession();

			// 用接口映射的方式进行CURD操作，官方推荐
			OrderBillMapper empmapper = session.getMapper(OrderBillMapper.class);

			Map<String, Object> map = new HashMap<String, Object>();

			// map.put("BGTM", "2014-11-11 00:00:00");
			map.put("BGTM", begin);
			map.put("EDTM", end);
			// 时间格式化
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss");
			// map.put("endTime",sdf.format(new Date()) );

			List<OrderBillEntity> list = empmapper.selectOrderBillByOrderTime(map);

			System.out.println(String.format("共查询到%s条数据！", list.size()));

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping("/GetAccountSettle")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AccountSettle> GetAccountSettle() {
		try {

			Reader reader = Resources.getResourceAsReader("mybatisconfig.xml");

			// 创建
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

			// 解析资源
			SqlSessionFactory factory = builder.build(reader);

			// 打开session
			SqlSession session = factory.openSession();

			// 用接口映射的方式进行CURD操作，官方推荐
			AccountSettleMapper empmapper = session.getMapper(AccountSettleMapper.class);

			// 定义查询参数
			Map<String, Object> map = new HashMap<String, Object>();

			List<AccountSettle> list = empmapper.queryall();

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping("/GetRegionDistribution")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RegionDistribution> GetRegionDistribution() {
		try {

			Reader reader = Resources.getResourceAsReader("mybatisconfig.xml");

			// 创建
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

			// 解析资源
			SqlSessionFactory factory = builder.build(reader);

			// 打开session
			SqlSession session = factory.openSession();

			// 用接口映射的方式进行CURD操作，官方推荐
			RegionDistributionMapper empmapper = session.getMapper(RegionDistributionMapper.class);

			// 定义查询参数
			Map<String, Object> map = new HashMap<String, Object>();

			List<RegionDistribution> list = empmapper.queryall();

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping("/GetTerminalDistribution")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TerminalDistribution> GetTerminalDistribution() {
		try {

			Reader reader = Resources.getResourceAsReader("mybatisconfig.xml");

			// 创建
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

			// 解析资源
			SqlSessionFactory factory = builder.build(reader);

			// 打开session
			SqlSession session = factory.openSession();

			// 用接口映射的方式进行CURD操作，官方推荐
			TerminalDistributionMapper empmapper = session.getMapper(TerminalDistributionMapper.class);

			// 定义查询参数
			Map<String, Object> map = new HashMap<String, Object>();

			List<TerminalDistribution> list = empmapper.queryall();

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		// SpringApplication.run(SpringReservationServiceApplication.class,
		// args);

		new SpringApplicationBuilder(SpringReservationServiceApplication.class).run(args);
	}
}
