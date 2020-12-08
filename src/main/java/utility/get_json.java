package utility;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class get_json {
	public static String get_json_from_map(Map<String, String> map_data)
	{
		String json = "";
    	try {
    		ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(map_data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
		return json;
	}
	public static boolean isJSONValid(String jsonInString ) {
	    try {
	       final ObjectMapper mapper = new ObjectMapper();
	       mapper.readTree(jsonInString);
	       return true;
	    } catch (IOException e) {
	       return false;
	    }
	  }
}
