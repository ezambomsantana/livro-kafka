package com.santana.streams.serializer;

import java.nio.charset.Charset;

import org.apache.kafka.common.serialization.Serializer;

import com.google.gson.Gson;

public class ShopSerializer implements Serializer {
	
    private static final Charset CHARSET = Charset.forName("UTF-8");
    static private Gson gson = new Gson();

    @Override
    public byte[] serialize(String s, Object o) {
        String line = gson.toJson(o);
        // Return the bytes from the String 'line'
        return line.getBytes(CHARSET);
    }

}