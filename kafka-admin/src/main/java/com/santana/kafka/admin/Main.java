package com.santana.kafka.admin;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Properties properties = new Properties();
		properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		
		AdminClient adminClient = AdminClient.create(properties);
		
	//	KafkaAdmin.createTopic("topico-4", 2, (short) 1, adminClient);
	//	KafkaAdmin.createTopic("topico-5", 2, (short) 1, adminClient);
		
		
		KafkaAdmin.listTopic(adminClient);
		
		KafkaAdmin.describeTopic("topico-1", adminClient);

	}

}
