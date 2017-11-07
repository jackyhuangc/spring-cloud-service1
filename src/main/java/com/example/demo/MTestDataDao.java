package com.example.demo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MTestDataDao extends MongoRepository<MTestData, ObjectId> {

	//Page<InstrumentMarketData> findByInstrumentIDLike(String instrumentID,Pageable pageable);
}