package com.example.demo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "InstrumentMDHistory")
public class InstrumentMarketData{

	@Id
	private String id;
	
	// 当自定义findByInstrumentIDLike接口时，InstrumentID参数会被转换为instrumentID,因此需要显示定义instrumentID参数，并通过@Feild指定其对应的列
	@Field("InstrumentID")
	private String instrumentID;
	@Field("LastUpdateTime")
	private String modifyTime;
	
	private float AskPrice1;
	private float BidPrice1;
	private float LastPrice;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInstrumentID() {
		return instrumentID;
	}
	public void setInstrumentID(String instrumentID) {
		this.instrumentID = instrumentID;
	}
	public float getAskPrice1() {
		return AskPrice1;
	}
	public void setAskPrice1(float askPrice1) {
		this.AskPrice1 = askPrice1;
	}
	public float getBidPrice1() {
		return BidPrice1;
	}
	public void setBidPrice1(float bidPrice1) {
		this.BidPrice1 = bidPrice1;
	}
	public float getLastPrice() {
		return LastPrice;
	}
	public void setLastPrice(float lastPrice) {
		this.LastPrice = lastPrice;
	}
	
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
}