package com.futures.entity;

import java.util.List;
import java.util.Map;

public interface OrderBillMapper {
	void add(OrderBillEntity order);

	List<OrderBillEntity> queryall();

	List<OrderBillEntity> selectOrderBillByOrderTime(Map<String,Object> map);

	void delete(int id);

	void update(OrderBillEntity order);
}