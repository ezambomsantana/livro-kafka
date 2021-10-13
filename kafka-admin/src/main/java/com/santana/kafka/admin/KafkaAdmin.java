package com.santana.kafka.admin;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;

public class KafkaAdmin {
	
	public static void createTopic(AdminClient adminClient) 
			throws InterruptedException, ExecutionException {
			
		ListTopicsResult topics = adminClient.listTopics();
		topics.names().get().forEach(System.out::println);
	}

}
