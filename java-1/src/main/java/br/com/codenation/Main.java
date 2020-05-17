package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DesafioMeuTimeApplication application = new DesafioMeuTimeApplication();

        //Inclui Time
        application.incluirTime(1L, "Barcelona", LocalDate.now(), "Verde e Amarelo", "Laranja");
        System.out.println(application.times);
        application.incluirTime(2L, "Brasil", LocalDate.now(), "Verde e Amarelo", "Azul e Branco");
        System.out.println(application.times);


        //Inclui Jogador
        application.incluirJogador(1L, 1L, "Messi", LocalDate.of(1956, 10, 15), 1, new BigDecimal(50));
        application.incluirJogador(2L, 1L, "Joao", LocalDate.of(1917, 10, 12), 20, new BigDecimal(5));
        application.incluirJogador(3L, 2L, "Neymar", LocalDate.of(1998, 10, 12), 100, new BigDecimal(1000000000));
        application.incluirJogador(4L, 1L, "Neymar Jr", LocalDate.of(1917, 10, 12), 80, new BigDecimal(500));
        System.out.println(application.jogadores);


//        System.out.println(application.buscarJogadorPorId(1L));
//        System.out.println(application.buscarJogadorPorId(2L));
//        application.definirCapitao(1L);
//        System.out.println(application.buscarJogadorPorId(1L));
//        System.out.println(application.buscarTimePorId(1L));
//        application.definirCapitao(2L);
//        System.out.println(application.buscarJogadorPorId(1L));
//        application.definirCapitao(1L);
//        System.out.println(application.buscarTimePorId(1L));
//        System.out.println(application.buscarTimePorId(1L).getJogadores());
//
//        System.out.println(application.jogadores);
//
////        System.out.println("nome do jogador capitão do time 1: " + application.buscarNomeJogador(application.buscarCapitaoDoTime(1L)));
////        System.out.println("id do jogador capitão do time 1: " + application.buscarCapitaoDoTime(1L));
////
////        System.out.println("nome do time 1: " + application.buscarNomeTime(1L));
////        System.out.println("nome do time 2: " + application.buscarNomeTime(2L));
////
////        //System.out.println(application.buscarCapitaoDoTime(3L));
////        System.out.println("nome do jogador 1: " + application.buscarNomeJogador(1L));
////        System.out.println("nome do jogador 2: " + application.buscarNomeJogador(2L));
//////
        System.out.println("melhor jogador: " + application.buscarMelhorJogadorDoTime(1L));
////
        System.out.println("mais velho do time 1: " + application.buscarJogadorMaisVelho(1L));
////
//        System.out.println("mais rico do time 1: " + application.buscarJogadorMaiorSalario(1L));

       // System.out.println("top 2 jogadores: " + application.buscarTopJogadores(2));

        //System.out.println("camisa time de fora: " + application.buscarCorCamisaTimeDeFora(1L, 2L));
    }
}
