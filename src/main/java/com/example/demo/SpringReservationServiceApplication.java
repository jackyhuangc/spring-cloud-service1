package com.example.demo;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class SpringReservationServiceApplication {

	@Autowired
	private KafkaSender service;

	@Autowired
	private InstrumentMarketDataDao instrumentMDRepository;

	@Autowired
	private MTestDataDao mTestDataDao;

	@RequestMapping("/hello")
	String index() {

		return "Hello Spring Boot";
	}

	/**
	 * * 创建分页请求.
	 */
	// private PageRequest buildPageRequest(int pageNumber, int pageSize,String
	// sortType) {
	// Sort sort = null;
	// if ("auto".equals(sortType)) {
	// sort = new Sort(Direction.DESC, "id");
	// } else if ("birthday".equals(sortType)) {
	// sort = new Sort(Direction.ASC, "birthday");
	// }
	// //参数1表示当前第几页,参数2表示每页的大小,参数3表示排序
	// return new PageRequest(pageNumber-1,pageSize,sort);
	// }

	@RequestMapping("/hello2")
	String index2(String str) {

		PageRequest pageRequest = new PageRequest(0, 999999, new Sort(Direction.ASC, "id"));
		Page<InstrumentMarketData> list = instrumentMDRepository.findByInstrumentIDLike(str, pageRequest);
		System.out.println(str + " " + list.getContent().size());
		if (list != null && list.getContent().size() > 0) {

			InstrumentMarketData data = list.getContent().get(0);
			System.out.println(data.getInstrumentID() + data.getLastPrice());
		}

		// List<InstrumentMarketData> list2 = instrumentMDRepository.findAll();

		DateTime dt1 = new DateTime();// 取得当前时间

		// 根据指定格式,将时间字符串转换成DateTime对象,这里的格式和上面的输出格式是一样的
		DateTime beginTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2017-06-22 21:02:00");
		DateTime endTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2017-06-22 21:02:00");

		list = instrumentMDRepository.findByModifyTimeBetween(beginTime, endTime, pageRequest);

		List<InstrumentMarketData> list2 = list.getContent();

		System.out.println("findAll " + list2.size());
		if (list2.size() > 0) {

			InstrumentMarketData md = list2.get(0);
			md.setInstrumentID(md.getInstrumentID() + new Date().getSeconds());
			md.setId(null);
			InstrumentMarketData tmp = instrumentMDRepository.insert(md);

			md = list2.get(0);
			md.setLastPrice(new Date().getSeconds());
			instrumentMDRepository.save(md);

			instrumentMDRepository.delete(tmp);

			return list2.get(0).getInstrumentID() + " " + list2.get(0).getLastPrice();
		}
		System.out.println("count " + instrumentMDRepository.count());
		System.out.println("mTestDataDao " + mTestDataDao.count());

		return "Hello Spring Boot" + str;
	}

	// 这是rest风格写法，用@PathVariable绑定对应的参数
	@RequestMapping(value = "/send/{msg}", method = RequestMethod.GET)
	public void send(@PathVariable("msg") String msg) {

		System.out.println("message:" + msg);
		service.sendMessage(msg);
		// return "xff";
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
