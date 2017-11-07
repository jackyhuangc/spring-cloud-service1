package com.example.demo;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface InstrumentMarketDataDao extends MongoRepository<InstrumentMarketData, ObjectId> {

	// 自定义分页模糊查询
	Page<InstrumentMarketData> findByInstrumentIDLike(String instrumentID, Pageable pageable);

	// $gte 大于等于，$lte小于等于，请不要加''，否则无法识别
	@Query("{'LastUpdateTime':{$gte:?0,$lte:?1}}")
	Page<InstrumentMarketData> findByModifyTimeBetween(DateTime beginTime, DateTime endTime, Pageable pageable);
}