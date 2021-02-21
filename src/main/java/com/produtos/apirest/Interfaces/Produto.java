package com.produtos.apirest.Interfaces;

import java.math.BigDecimal;

public interface Produto {
    String getNome();
    BigDecimal getQuantidade();
    BigDecimal getValor();
}