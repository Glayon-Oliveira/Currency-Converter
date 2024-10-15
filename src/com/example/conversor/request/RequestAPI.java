package com.example.conversor.request;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.conversor.ExampleCodes;
import com.example.conversor.json.AllCodes;
import com.example.conversor.json.Code;
import com.example.conversor.json.PairCode;
import com.example.conversor.json.LatestCode;
import com.google.gson.Gson;

public class RequestAPI {
		
	private String uriModel = "https://v%s.exchangerate-api.com/v%s/%s/";
	private HttpClient client;
	
	public RequestAPI(String key, String version) {
				
		this.uriModel = String.format(this.uriModel, version, version, key);
		this.client = HttpClient.newHttpClient();
		
	}
	
	public PairCode requestByResultConvert(String ofCode, String toCode) throws IOException, InterruptedException {		
		
		ofCode = ofCode.toUpperCase();
		toCode = toCode.toUpperCase();
		
		String uri = this.uriModel + "pair/" + ofCode + "/" + toCode;
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		String jsonResponse = response.body();
		
		Gson gson = new Gson();
		
		PairCode result = gson.fromJson(jsonResponse, PairCode.class);
		
		return result;
		
	}
		
	public LatestCode requestByResultGeneric(String code) throws IOException, InterruptedException {
		
		code = code.toUpperCase();
		
		String uri = this.uriModel + "latest/" + code;
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		String jsonResponse = response.body();
		
		Gson gson = new Gson();
		
		LatestCode result = gson.fromJson(jsonResponse, LatestCode.class);
		
		return result;
		
	}
		
	public AllCodes requestAllCodes() throws IOException, InterruptedException {
				
		String uri = this.uriModel + "codes";
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		String jsonResponse = response.body();
		
		Gson gson = new Gson();
		
		AllCodes result = gson.fromJson(jsonResponse, AllCodes.class);
		
		return result;
		
	}

}
