package com.santana.streams.serializer;

import java.nio.charset.Charset;

import org.apache.kafka.common.serialization.Deserializer;

import com.google.gson.Gson;
import com.santana.dto.ShopDTO;

public class ShopDeserializer implements Deserializer {
	
	private static final Charset CHARSET = Charset.forName("UTF-8");	
    static private Gson gson = new Gson();

    @Override
    public Object deserialize(String s, byte[] bytes) {
        try {
            // Transform the bytes to String
            String person = new String(bytes, CHARSET);
            // Return the Person object created from the String 'person'
            return gson.fromJson(person, ShopDTO.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error reading bytes! Yanlış", e);
        }
    }

}