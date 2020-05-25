package com.challenge.interfaces;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.desafio.CalculadorDeClasses;
import com.challenge.desafio.Teste;

import java.math.BigDecimal;

public interface Calculavel {

    @Somar
    public int somar(Teste teste);

    @Subtrair
    public int subtrair(Teste teste);

    public int totalizar();

}
