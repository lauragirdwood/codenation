package com.challenge.desafio;

import com.challenge.interfaces.Calculavel;

import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public int somar(Teste teste) {
        return getClass().getDeclaredFields().length;
    }

    @Override
    public int subtrair(Teste teste) {
        return getClass().getDeclaredFields().length;
    }

    public int totalizar() {
        return 0;
    }
}
