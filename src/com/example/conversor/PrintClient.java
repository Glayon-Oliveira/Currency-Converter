package com.example.conversor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import com.example.conversor.json.AllCodes;
import com.example.conversor.json.Code;
import com.example.conversor.json.PairCode;
import com.example.conversor.request.RequestAPI;

public class PrintClient {

	private RequestAPI request;
	private List<Code> codeList;

	public PrintClient(RequestAPI request) throws IOException, InterruptedException {

		AllCodes allCodes = request.requestAllCodes();

		this.codeList = allCodes.getListCode();
		this.request = request;

	}

	public PrintClient(RequestAPI request, AllCodes allCodes) {

		this.codeList = allCodes.getListCode();
		this.request = request;

	}

	public PrintClient(RequestAPI request, List<Code> codeList) {

		this.codeList = codeList;
		this.request = request;

	}

	private void printSeparator() {

		String separator = "******************************";

		System.out.println(separator);

	}

	private void printIntroduct() {

		printSeparator();

		String introduct = "Seja bem vindo(a) ao Conversor de Moeadas";

		System.out.println(introduct);

	}

	private int printOptions() {

		String out = "\n";

		String moedaOptionModel = "%d) (%s) %s\n";
		String outherOptionModel = "%d) %s";

		for (Code code : codeList) {

			out += String.format(moedaOptionModel, codeList.indexOf(code) + 1, code.getCode(), code.getName());

		}

		out += String.format(outherOptionModel, codeList.size() + 1, "Sair\n");
		out += String.format(outherOptionModel, codeList.size() + 2, "Ajuda");

		System.out.println(out);

		System.out.println(
				"\nNota: Para realizar a conversão das moedas utilize os números ou os códigos com \"->\" ou \"<-\" entre eles.");
		System.out.println("Exemplo: 1 -> 5. Nesse exemplo a moeda 1 será convertida para (->) a moeda 5");
		System.out.println("Exemplo: usd -> BRL. Nesse exemplo a moeda USD será convertida para (->) a moeda BRL");
		System.out.println("Para saber mais, digite " + (codeList.size() + 2) + ".\"\n");

		return 0;

	}

	private int printEnterOption() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("\nO que deseja fazer?");

		String option = "";
		
		while(option.isEmpty()) {
			option = scanner.nextLine().toUpperCase();
		}

		String[] optionComponents = verifyOption(option);

		if (optionComponents != null) {

			int result = enterOption(optionComponents);
			
			if(result == 0) {
				
				System.out.println("\nTem certeza que deseja encerrar?");
				System.out.print("Caso não deseje sair digite Y, do contrario, basta dá enter:");
				
				String resposta = scanner.nextLine().toUpperCase();
				resposta = resposta.replace(" ", "");
				
				if(resposta.equals("Y")) {
					System.out.println("Espero que tenha gostado da experiência :)");
					result = 1;
				}
				
			}else {
				System.out.print("\nEnter para continuar: ");
				scanner.nextLine();
			}										
			
			return result;

		} else {
			return -1;
		}

	}

	private String[] verifyOption(String option) {

		String optionFormated = option.replace(" ", "\n");
		Stream<String> linesStream = optionFormated.lines();
		List<String> linesList = linesStream.toList();

		for (String line : linesList) {
			if (line.isEmpty()) {
				linesList.remove(line);
			}
		}

		if (linesList.size() == 3) {

			for (int cc = 0; cc < linesList.size() - 1; cc++) {

				if (isInteger(linesList.get(cc * 2))) {

					int value = Integer.parseInt(linesList.get(cc * 2));

					if (value < 0 || value > codeList.size()) {
						System.out.println("Não há uma moeda referente ao número " + value);
						return null;
					} else {
						String[] array = linesList.toArray(String[]::new);
						array[cc * 2] = codeList.get(value).getCode();
						linesList = Arrays.asList(array);
					}

				} else {

					String codeValue = linesList.get(cc * 2);
					boolean exist = false;

					for (Code code : codeList) {

						if (code.getCode().equals(codeValue)) {
							exist = true;
							break;
						}

					}

					if (!exist) {
						System.out.println("O código " + codeValue + " não é reconhecido");
						return null;
					}

				}

			}

		} else if (linesList.size() == 1) {

			String value = linesList.get(0);

			if (value.equals(Integer.toString(codeList.size() + 1)) || value.equals("SAIR")) {

				String[] array = linesList.toArray(String[]::new);
				array[0] = Integer.toString(codeList.size() + 1);
				linesList = Arrays.asList(array);

			} else if (value.equals(Integer.toString(codeList.size() + 2)) || value.equals("AJUDA")) {

				String[] array = linesList.toArray(String[]::new);
				array[0] = Integer.toString(codeList.size() + 2);
				linesList = Arrays.asList(array);

			} else {
				System.out.println(
						"Descupa, não conheço esse comando. Verifique se o comando segue o padrão descrito em ajuda");
				return null;
			}

		} else {
			System.out.println(
					"Descupa, não conheço esse comando. Verifique se o comando segue o padrão descrito em ajuda");
			return null;
		}

		return linesList.toArray(String[]::new);

	}

	private int enterOption(String[] option) {

		if (option.length == 3) {
			
			boolean reverse = (option[1] == "<-");					
			
			return printConvertion(option[0], option[2], reverse);

		} else if (option.length == 1) {

			String exit = Integer.toString(codeList.size() + 1);
			String help = Integer.toString(codeList.size() + 2);			

			if (option[0].equals(help)) {

				printHelp();						
				
				return 1;

			} else if (option[0].equals(exit)) {
				return 0;
			}

		}
		
		return -1;

	}

	private void printHelp() {
		
		String help = """
				1# Para converter as moedas deve-se ser usado os números referentes ou o código entre parenteses.
				1.1#Entre ambos os valores deve-se ser colocado os sinais "->" ou "<-" entre eles.
				1.2#O comando deve seguir esse formato:
				-(Número ou código) ("<-" ou "->") (Número ou código).
				1.3#O sinal usado entre os valores indica o sentido da conversão.						

				Nesse exemplo a moeda 1 será convertida para a moeda 2:						
				Exemplo 1: 1 -> 2
			
				Nesse exemplo a moeda 2 será convertida para a moeda 1:
				Exemplo 2: 1 <- 2
				
				2# Para outros comandos simples usa-se somente o número do comando;
				""";

		System.out.println(help);
		
	}

	private int printConvertion(String code1, String code2, boolean reverseConversion) {
		
		Scanner scanner = new Scanner(System.in);
		
		if(reverseConversion) {
			String code = code1;
			
			code1 = code2;
			code2 = code;
		}			
		
		try {
			
			String modelo1 = "\n1 (%s) -> %.2f (%s). ";
			String modelo2 = "Resultado da conversão %.2f (%s) -> %.2f (%s).";
			
			PairCode pairCode = request.requestByResultConvert(code1, code2);
			
			System.out.println("\nNota: \".\" no lugar de \",\"");
			System.out.print("Digite o valor a ser convertido: ");
			double value = scanner.nextDouble();
			
			String baseCode = pairCode.getBaseCode();
			String targetCode = pairCode.getTargetCode();
			double rate = pairCode.getConversionRate();
			
			modelo1 = String.format(modelo1, baseCode, rate, targetCode);
			modelo2 = String.format(modelo2, value, baseCode, rate*value, targetCode);
			
			System.out.println(modelo1 + modelo2);
			
			return 1;
			
			
		}catch(Exception e) {
			System.out.println("Ocorreu um erro, por favor tente novamente.");
			return -1;
		}			
		
	}

	private boolean isInteger(String value) {

		try {

			Integer.parseInt(value);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public void print() {

		printIntroduct();			
		
		printOptions();
		
		int cond = -1;
		
		while(cond != 0) {
			
			if(cond == 1) {
				printSeparator();
				printOptions();
				cond = -1;
			}else {
				cond = printEnterOption();
			}				

		}		

	}

}
