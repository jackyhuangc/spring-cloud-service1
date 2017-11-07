package com.futures.entity;

//import java.sql.Date;// 不带时分秒，即默认为00:00:00
import java.util.Date;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;

public class OrderBillEntity {
	private String InstrumentID;
	private String InvestorID;
	private int Direction;
	private int TradeType;
	private double OrderPrice;
	private double OrderVolume;
	private Date OrderTime;

	public String getInstrumentID() {
		return InstrumentID;
	}

	public void setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
	}

	public String getInvestorID() {
		return InvestorID;
	}

	public void setInvestorID(String investorID) {
		InvestorID = investorID;
	}

	public int getDirection() {
		return Direction;
	}

	public void setDirection(int direction) {
		Direction = direction;
	}

	public int getTradeType() {
		return TradeType;
	}

	public void setTradeType(int tradeType) {
		TradeType = tradeType;
	}

	public double getOrderPrice() {
		return OrderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		OrderPrice = orderPrice;
	}

	public double getOrderVolume() {
		return OrderVolume;
	}

	public void setOrderVolume(double orderVolume) {
		OrderVolume = orderVolume;
	}

	// 单独处理返回的日期类型 需在配置中设置日期格式(spring.jackson.date-format=yyyy-MM-dd HH:mm:ss)，否则返回时间戳
	public Date getOrderTime() {
		return OrderTime;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		return sdf.format(OrderTime);
	}

	public void setOrderTime(Date orderTime) {

		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd
		// hh:mm:ss") ;
		// String a1 = formatter.format(orderTime);
		// Date date = sdf.parse(dateString);
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");
		// String dateString = formatter.format(orderTime);
		// ParsePosition pos = new ParsePosition(8);
		// Date currentTime_2 = formatter.parse(dateString);

		// //joda-time与JDK日期对象的转换
		// DateTime dt = new DateTime();
		// //转换成java.util.Date对象
		// Date d1 = new Date(dt.getMillis());
		// Date d2 = dt.toDate();
		// System.out.println(dt);
		// System.out.println(d1);
		// System.out.println(d2);
		System.out.println(orderTime);
		this.OrderTime = orderTime;
	}
}