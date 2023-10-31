package br.com.softplan.service;

import br.com.softplan.domain.Pedido;
import com.mercadopago.exceptions.MPException;

import java.math.BigDecimal;

public interface PagamentoService {
    void realizarPagamento(Pedido pedido, BigDecimal valorTotal) throws MPException;
}
