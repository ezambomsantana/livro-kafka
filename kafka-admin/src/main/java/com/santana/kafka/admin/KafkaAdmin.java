package com.santana.kafka.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteConsumerGroupsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListConsumerGroupsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

public class KafkaAdmin {
	
	public static void listTopic(AdminClient adminClient) 
			throws InterruptedException, ExecutionException {
			
		ListTopicsResult topics = adminClient.listTopics();
		topics.names().get().forEach(System.out::println);
	}
	
	public static void listConsumerGroups(AdminClient adminClient) 
			throws InterruptedException, ExecutionException {
			
		ListConsumerGroupsResult cgs = adminClient.listConsumerGroups();
		cgs.all().get().forEach(cg -> System.out.println(cg.groupId()));
	}
	
	public static void deleteConsumerGroup(String groupId, AdminClient adminClient) 
			throws InterruptedException, ExecutionException {
		
		List<String> groups = new ArrayList<>();
		groups.add(groupId);

		try {
			DeleteConsumerGroupsResult cgs = adminClient.deleteConsumerGroups(groups);
			cgs.all().get();
		} catch (final Exception e) {
			throw new RuntimeException("Failed to delete cg:" 
					+ groupId, e);
		}
	}
	
	public static void createTopic(String topicName, 
			int partitions, 
			short replications, 
			AdminClient adminClient) {

		final NewTopic newTopic = 
				new NewTopic(topicName, partitions, replications);
		List<NewTopic> topics = new ArrayList<NewTopic>();
		topics.add(newTopic);
		try {
			CreateTopicsResult result = 
					adminClient.createTopics(topics);
			result.all().get();
		} catch (final Exception e) {
			throw new RuntimeException("Failed to create topic:" 
					+ topicName, e);
		}

	}

	public static void describeTopic(String topicName, AdminClient adminClient) 
			throws InterruptedException, ExecutionException {
			
		List<String> topicNames = new ArrayList<>();
		topicNames.add(topicName);
		
		DescribeTopicsResult topics = adminClient.describeTopics(topicNames);
		topics.all().get().forEach(
				(x, y) -> System.out.println(x + " " + y.topicId() + " " + y.partitions()));
	}
	

	public static void deleteTopic(String topicName, AdminClient adminClient)  {
			
		List<String> topicNames = new ArrayList<>();
		topicNames.add(topicName);
		try {
			DeleteTopicsResult topics = adminClient.deleteTopics(topicNames);
			topics.all().get();
		} catch (final Exception e) {
			throw new RuntimeException("Failed to delete topic:" 
					+ topicName, e);
		}
	}
	
	public static void describeCluster(AdminClient adminClient) 
		throws InterruptedException, ExecutionException  {

			DescribeClusterResult cluster = adminClient.describeCluster();
			System.out.println(cluster.clusterId().get());
	}
	
}
