package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringReservationServiceApplicationTests {
	// @Autowired
	private InstrumentMarketDataDao instrumentMDRepository;

	// @Autowired
	private MTestDataDao mTestDataDao;

	// @Test
	public void contextLoads() {
		String str = "c";
		PageRequest pageRequest = new PageRequest(0, 999999, new Sort(Direction.ASC, "id"));
		Page<InstrumentMarketData> list = instrumentMDRepository.findByInstrumentIDLike(str, pageRequest);
		System.out.println(str + " " + list.getContent().size());
		if (list != null && list.getContent().size() > 0) {

			InstrumentMarketData data = list.getContent().get(0);
			System.out.println(data.getInstrumentID() + data.getLastPrice());
		}

		// List<InstrumentMarketData> list2 = instrumentMDRepository.findAll();

		// 根据指定格式,将时间字符串转换成DateTime对象,这里的格式和上面的输出格式是一样的
		// DateTime beginTime = DateTimeFormat.forPattern("yyyy-MM-dd
		// HH:mm:ss").parseDateTime("2017-06-22 21:02:00");
		// DateTime endTime = DateTimeFormat.forPattern("yyyy-MM-dd
		// HH:mm:ss").parseDateTime("2017-06-22 21:02:00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date begin = new Date();
		try {
			begin = sdf.parse("2017-06-22 21:02:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		list = instrumentMDRepository.findByModifyTimeBetween(begin, begin, pageRequest);

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

			System.out.println(list2.get(0).getInstrumentID() + " " + list2.get(0).getLastPrice());
		}
		System.out.println("count " + instrumentMDRepository.count());
		System.out.println("mTestDataDao " + mTestDataDao.count());
	}
}
