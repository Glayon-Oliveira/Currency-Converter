package com.example.conversor.json;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class LatestCode {
	
	@SerializedName("result")
	private String result;
	@SerializedName("base_code")
	private String baseCode;
	@SerializedName("conversion_rates")
	private Map<String, String> conversionRates;
	
	public LatestCode() {}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getBaseCode() {
		return baseCode;
	}

	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}

	public Map<String, String> getConversionRates() {
		return conversionRates;
	}

	public void setConversionRates(Map<String, String> conversionRates) {
		this.conversionRates = conversionRates;
	}

	@Override
	public String toString() {
		return "result: " + result + ";\nbase_code: " + baseCode + ";\nconversion_rate: " + conversionRates.toString();
	}
	
}
