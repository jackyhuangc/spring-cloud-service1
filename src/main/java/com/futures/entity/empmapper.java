package com.futures.entity;

import java.util.List;
public interface empmapper {

	void add(EMP emp);

	List<EMP> queryall();

	void delete(int id);

	void update(EMP emp);
}