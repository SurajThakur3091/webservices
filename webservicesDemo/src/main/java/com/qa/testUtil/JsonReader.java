package com.qa.testUtil;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonReader {

	
		public static String getValueByJpath(JSONObject responseJson, String Jpath){
			Object obj = responseJson;
			for(String s : Jpath.split("/")){
				if(!s.isEmpty())
					if(!(s.contains("[")||s.contains("]")))
						obj = ((JSONObject)obj).get(s);
					else if(s.contains("[")||s.contains("]"))
						obj = ((JSONArray)((JSONObject)obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
				
			}
			return obj.toString();
		}
}
