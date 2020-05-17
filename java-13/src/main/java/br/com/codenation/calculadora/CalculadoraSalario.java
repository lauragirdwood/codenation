package br.com.codenation.calculadora;


public class CalculadoraSalario {

	private double salarioComDescontoINSS;
	private double salarioComDescontoIRFF;

	public long calcularSalarioLiquido(double salarioBase) {
		double salarioLiquido;
		if (salarioBase < 1039.00){
			return 0L;
		}
		salarioComDescontoINSS = calcularInss(salarioBase);
		salarioComDescontoIRFF = calcularIRRF(salarioComDescontoINSS);
		salarioLiquido = salarioComDescontoIRFF;
		return Math.round(salarioLiquido);
	}

	private double calcularInss(double salarioBase) {
		if(salarioBase <= 1500.00){
			salarioComDescontoINSS = salarioBase - (salarioBase * 0.08);
		} else if (salarioBase > 1500.00 && salarioBase <= 4000.00 ){
			salarioComDescontoINSS = salarioBase - (salarioBase * 0.09);
		} else if (salarioBase > 4000.00){
			salarioComDescontoINSS = salarioBase - (salarioBase * 0.11);
		}
		return salarioComDescontoINSS;
	}

	private double calcularIRRF(double salarioComDescontoINSS) {

		if(salarioComDescontoINSS > 3000.00 && salarioComDescontoINSS <= 6000.00) {
			salarioComDescontoIRFF = salarioComDescontoINSS - (salarioComDescontoINSS * 0.075);
		} else if (salarioComDescontoINSS > 6000.00) {
			salarioComDescontoIRFF = salarioComDescontoINSS - (salarioComDescontoINSS * 0.15);
		} else {
			salarioComDescontoIRFF = salarioComDescontoINSS;
		}
		return salarioComDescontoIRFF;
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/