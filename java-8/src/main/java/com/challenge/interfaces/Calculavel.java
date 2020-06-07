package com.challenge.interfaces;

import java.math.BigDecimal;

public interface Calculavel {

    BigDecimal somar(Object object) throws NoSuchFieldException, IllegalAccessException;

    BigDecimal subtrair(Object object) throws IllegalAccessException;

    BigDecimal totalizar(Object object) throws IllegalAccessException;

}
