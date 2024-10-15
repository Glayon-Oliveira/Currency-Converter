package com.example.conversor;

import java.util.List;
import java.util.ArrayList;

import com.example.conversor.json.Code;
import com.example.conversor.request.RequestAPI;

public interface ExampleCodes {		
	
	public static List<Code> getMoedas(){
		
		List<Code> moedas = new ArrayList<Code>();
		
		moedas.add(new Code("USD", "Dólar Americano - Estados Unidos"));
		moedas.add(new Code("BRL", "Real - Brasil"));
		moedas.add(new Code("EUR", "Euro - União Europeia"));
		moedas.add(new Code("JPY", "Iene - Japão"));
		moedas.add(new Code("GBP", "Libra Esterlina - Reino Unido"));
		moedas.add(new Code("CNY", "Yuan Renminbi - China"));
		
		return moedas;
		
	}	
	
}
