package com.kkwrite.gallery.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static void main(String[] args) throws Exception {
        /*Map map = new HashMap();
        map.put("name", "wangke");
        System.out.println(generateJson(map));*/
		
		String json = "{\"access_token\":\"5_MeMH7uiLtvoQy43c2y-LOX0OBYpg_ZoquuoJrZ4reF0YhIqPuR71sxxghbfuQ6bgQB-DJjHUbGO0ecnLpGkfxhZjnyKN4F-GMp7DIV4ihmdHfnFImaypF6fqpXEKzuF6nh9d_-wTOKLaIVwwNUNhAGAHOH\",\"expires_in\":7200}";
		Map map = generateBean(json);
		System.out.println(map);
	}
	
	public static String generateJson(Map map) {
		try {
			ObjectMapper mapper = new ObjectMapper();  
			return mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map generateBean(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();  
			return mapper.readValue(json, HashMap.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return null;
	}
	
}
