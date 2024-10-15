package com.example.conversor;

import java.io.IOException;
import java.util.List;

import com.example.conversor.json.AllCodes;
import com.example.conversor.json.Code;
import com.example.conversor.json.LatestCode;
import com.example.conversor.request.RequestAPI;

public class MainDd {	

	public static void main(String[] args) {
		
		RequestAPI request = new RequestAPI("24b0b3b4efc4cca32a1d272c", "6");		
		
		List<Code> codes = ExampleCodes.getMoedas();
		
		PrintClient print = new PrintClient(request, codes);
		
		print.print();

	}

}
