package com.example.conversor.json;

import com.google.gson.annotations.SerializedName;

public class PairCode {
	
	@SerializedName("result")
	private String result;
	@SerializedName("base_code")
	private String baseCode;
	@SerializedName("target_code")	
	private String targetCode;	
	@SerializedName("conversion_rate")
	private double conversionRate;
	
	public PairCode() {}

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

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	public double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(double conversionRate) {
		this.conversionRate = conversionRate;
	}	
	
	@Override
	public String toString() {
		
		String toString = "";
		
		toString += "result: " + result + ";\n";
		toString += "base_code: " + baseCode + ";\n";
		toString += "target_code: " + targetCode + ";\n";
		toString += "conversion_rate: " + conversionRate + ";\n";
		
		return toString;
		
	}
	
}
