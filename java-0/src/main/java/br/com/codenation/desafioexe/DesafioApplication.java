package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static void exibeSequenciaFibonacci(){
		List<Integer> sequenciaFibonacci = fibonacci();
		for (Integer i : sequenciaFibonacci) {
			System.out.println("índice: " + sequenciaFibonacci.indexOf(i) + " valor: " + i);
		}
	}

	public static List<Integer> fibonacci() {
		// criando a lista
		List<Integer> sequenciaFibonacci = new ArrayList<Integer>();;

		// definindo condição inicial
		int anterior = 0;
		sequenciaFibonacci.add(0);
		int atual = 1;
		sequenciaFibonacci.add(1);
		int proximo = 1;

		// atribuindo numeros à lista
		for(int i = 0; atual <= 350; ++i){
			proximo = atual + anterior;
			anterior = atual;
			atual = proximo;
			sequenciaFibonacci.add(proximo);
		}

		return sequenciaFibonacci;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}