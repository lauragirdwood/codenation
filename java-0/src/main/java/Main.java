import br.com.codenation.desafioexe.DesafioApplication;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DesafioApplication.fibonacci();
        DesafioApplication.exibeSequenciaFibonacci();
        System.out.println(DesafioApplication.isFibonacci(3));
        System.out.println(DesafioApplication.isFibonacci(4));
        System.out.println(DesafioApplication.isFibonacci(99));
        System.out.println(DesafioApplication.isFibonacci(1));

    }
}
