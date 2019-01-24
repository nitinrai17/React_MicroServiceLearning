package com.learning.rest.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchnageValueRepository extends JpaRepository<ExchangeValue,Long>{
	
	ExchangeValue findByFromAndTo(String from , String to);
}
