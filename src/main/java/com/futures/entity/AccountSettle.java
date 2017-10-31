package com.futures.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountSettle {
	public float getFee() {
		return Fee;
	}
	public void setFee(float fee) {
		Fee = fee;
	}
	public float getRecharge() {
		return Recharge;
	}
	public void setRecharge(float recharge) {
		Recharge = recharge;
	}
	public float getWithdraw() {
		return Withdraw;
	}
	public void setWithdraw(float withdraw) {
		Withdraw = withdraw;
	}
	public String getSettleDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(SettleDate);
	}
	public void setSettleDate(Date settleDate) {
		SettleDate = settleDate;
	}
	
	private float Fee;
	private float Recharge;
	private float Withdraw;
	private Date SettleDate;
}