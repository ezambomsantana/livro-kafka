package com.santana.streams.serializer;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ShopSerde implements Serde<Object> {

    private ShopSerializer shopSerializer = new ShopSerializer();
    private ShopDeserializer shopDeserializer = new ShopDeserializer();

    @Override
    public void close() {
    	shopSerializer.close();
    	shopDeserializer.close();

    }

    @Override
    public Serializer serializer() {
        return shopSerializer;
    }

    @Override
    public Deserializer deserializer() {
        return shopDeserializer;
    }
}