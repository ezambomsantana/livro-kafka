package com.santana.streams;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;

import com.santana.dto.ShopDTO;
import com.santana.streams.serializer.ShopSerde;

public class CountShops {

	private static final String SHOP_TOPIC_EVENT = "SHOP_TOPIC";

	public static void main(String [] args) {
	       Properties props = new Properties();
	       props.put(StreamsConfig.APPLICATION_ID_CONFIG, 
	    		   "sum-shops-312312321");
	       props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, 
	    		   "localhost:9092");
	       props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, 
	    		   Serdes.String().getClass());
	       props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, 
	    		   ShopSerde.class.getName());

	       StreamsBuilder builder = new StreamsBuilder();
	       KStream<String, ShopDTO> inputTopic = 
	    		   builder.stream(SHOP_TOPIC_EVENT);

	       KTable<String, Long>  teste = inputTopic.groupByKey().count(Materialized.as("count-store"));

	       teste.toStream()
	       		.print(Printed.toSysOut());

	       KafkaStreams streams = new KafkaStreams(builder.build(), props);
	       streams.start();
	}
	
	

}
