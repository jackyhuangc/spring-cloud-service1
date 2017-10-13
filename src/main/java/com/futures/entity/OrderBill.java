package com.futures.entity;

import java.sql.Date;

public class OrderBill {
	
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
	public Date getOrderTime() {
		return OrderTime;
	}
	public void setOrderTime(Date orderTime) {
		OrderTime = orderTime;
	}
}