package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DesafioMeuTimeApplication application = new DesafioMeuTimeApplication();

        //Ao chamar método sem times incluídos deve mostrar lista vazia
        System.out.println("lista de times vazia: " + application.buscarTimes());

        //Inclui Time
        application.incluirTime(1L, "Barcelona", LocalDate.now(), "Verde e Amarelo", "Laranja");
        System.out.println(application.times);
        application.incluirTime(2L, "Brasil", LocalDate.now(), "Verde e Amarelo", "Azul e Branco");
        System.out.println(application.times);

        //Ao chamar método com times incluídos, exibir lista dos ids encontrados
        System.out.println("lista com id de times: " + application.buscarTimes());
        System.out.println(" lista vazia top 2 jogadores: " + application.buscarTopJogadores(2));

        //Inclui Jogador
        application.incluirJogador(1L, 1L, "Messi", LocalDate.of(1956, 10, 15), 1, new BigDecimal(50));
        application.incluirJogador(2L, 1L, "Joao", LocalDate.of(1917, 10, 12), 20, new BigDecimal(5));
        application.incluirJogador(3L, 2L, "Neymar", LocalDate.of(1998, 10, 12), 100, new BigDecimal(1000000000));
        application.incluirJogador(4L, 1L, "Neymar Jr", LocalDate.of(1917, 10, 12), 80, new BigDecimal(500));
        System.out.println(application.jogadores);

        //Buscar salario de um jogador
        System.out.println("salario do jogador com id 1: " + application.buscarSalarioDoJogador(1L));
        System.out.println("salario do jogador com id 4: " + application.buscarSalarioDoJogador(4L));

        //Buscar maior salário do time 1
        System.out.println("id do jogador c maior salario: " + application.buscarJogadorMaiorSalario(1L));

        //Buscar melhor jogador do time 1
        System.out.println("melhor jogador do time 1: " + application.buscarMelhorJogadorDoTime(1L));

        //Buscar jogador + velho do time 1
        System.out.println("mais velho do time 1: " + application.buscarJogadorMaisVelho(1L));

        //Listar todos os ids dos jogadores do time 1
        System.out.println("lista com id de jogadores do time 1: " + application.buscarJogadoresDoTime(1L));

        //Listar top 2 jogadores
        System.out.println("top 2 jogadores: " + application.buscarTopJogadores(2));

        //cores camisa
        System.out.println("camisa time de fora: " + application.buscarCorCamisaTimeDeFora(2L, 1L));

        //Nomes jogadores
        System.out.println("nome do jogador 1: " + application.buscarNomeJogador(1L));
        System.out.println("nome do jogador 2: " + application.buscarNomeJogador(2L));

        //Nomes times
        System.out.println("nome do time 1: " + application.buscarNomeTime(1L));
        System.out.println("nome do time 2: " + application.buscarNomeTime(2L));

        //Definir capitão
        application.definirCapitao(2L);

        //Exibir times
        System.out.println(application.times);

        //Buscar capitao
        System.out.println("capitão do time 1: " + application.buscarCapitaoDoTime(1L));
    }
}
