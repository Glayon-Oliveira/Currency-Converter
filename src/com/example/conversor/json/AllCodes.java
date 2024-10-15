package com.example.conversor.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class AllCodes {
		
	@SerializedName("result")
	private String result;
	
	@SerializedName("supported_codes")
	private Map<String, String> allCodes;
	
	public AllCodes() {
		this.allCodes = new HashMap<String, String>();		
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Map<String, String> getAllCodes() {
		return allCodes;
	}

	public void setAllCodes(Map<String, String> allCodes) {
		this.allCodes = allCodes;
	}
	
	public List<Code> getListCode(){
		
		List<Code> list = new ArrayList<Code>();
		
		for(String key: allCodes.keySet()) {
			
			Code code = new Code(key, allCodes.get(key));
			
			list.add(code);
			
		}
		
		return list;
		
	}
	
	@Override
	public String toString() {
		return this.allCodes.toString();
	}	

}
