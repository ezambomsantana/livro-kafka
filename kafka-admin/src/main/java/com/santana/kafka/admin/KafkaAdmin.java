package com.santana.kafka.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

public class KafkaAdmin {
	
	public static void listTopic(AdminClient adminClient) 
			throws InterruptedException, ExecutionException {
			
		ListTopicsResult topics = adminClient.listTopics();
		topics.names().get().forEach(System.out::println);
	}
	
	public static void createTopic(String topicName, int partitions, short replications, AdminClient adminClient) {

		final NewTopic newTopic = new NewTopic(topicName, partitions, replications);
		List<NewTopic> topics = new ArrayList<NewTopic>();
		topics.add(newTopic);
		try {
			final CreateTopicsResult result = adminClient.createTopics(topics);
			result.all().get();
		} catch (final Exception e) {
			throw new RuntimeException("Failed to create topic:" + topicName, e);
		}

	}

	public static void describeTopic(String topicName, AdminClient adminClient) 
			throws InterruptedException, ExecutionException {
			
		List<String> topicNames = new ArrayList();
		topicNames.add(topicName);
		
		DescribeTopicsResult topics = adminClient.describeTopics(topicNames);
		topics.all().get().forEach((x, y) -> System.out.println(x + " " + y.topicId() + " " + y.partitions()));
	}
	

}
