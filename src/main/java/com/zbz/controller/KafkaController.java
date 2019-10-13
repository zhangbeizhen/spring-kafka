package com.zbz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zbz.service.KafkaProducerService;

/**
 * http://127.0.0.1:8080/spring-kafka/service/kafka?str=test 
 * @ClassName: KafkaController
 * @author: ag
 * @date: 2019年9月25日 上午11:29:20
 */
@Controller
@RequestMapping("/service")
public class KafkaController {
	
	@Autowired
	private KafkaProducerService producerService;
	@ResponseBody
	@RequestMapping(value = "kafka" ,method = RequestMethod.GET )
	public String service( String str) throws Exception{
		String result ="{\"result\" : \"success\"}";
		for(int i=0;i<20;i++){
			String kaf = "Hello Kafka,Welcome to the world." +"This is Kafka_"+ i ;
			producerService.sendMessage("helloTopic",0,null,"key",kaf);
		}
		return result;
	}

}
