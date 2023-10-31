package br.com.softplan.service.implementation;

import br.com.softplan.client.MercadoPagoPaymentClient;
import br.com.softplan.domain.Mesa;
import br.com.softplan.domain.Pagamento;
import br.com.softplan.domain.Pedido;
import br.com.softplan.domain.Transacao;
import br.com.softplan.enums.MetodoDePagamentoEnum;
import br.com.softplan.enums.StatusMesaEnum;
import br.com.softplan.repository.MesaRepository;
import br.com.softplan.repository.PagamentoRepository;
import br.com.softplan.repository.TransacaoRepository;
import br.com.softplan.service.PagamentoService;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class PagamentoServiceImpl implements PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private MercadoPagoPaymentClient mercadoPagoPaymentClient;

    @Autowired
    private MesaRepository mesaRepository;

    @Transactional
    public void realizarPagamento(Pedido pedido, BigDecimal valorTotal) throws MPException {

        Payment payment = new Payment();
        payment.setTransactionAmount((float) valorTotal.doubleValue());
        payment.setDescription("Pagamento do Pedido #" + pedido.getId());

        Payment respostaDoPagamento = mercadoPagoPaymentClient.createPayment(payment);


        if ("approved".equals(respostaDoPagamento.getStatus())) {

            Pagamento pagamento = new Pagamento();
            pagamento.setStatus(String.valueOf(respostaDoPagamento.getStatus()));
            pagamento.setDescricao(respostaDoPagamento.getDescription());
            pagamento.setDataDeCriacao(LocalDateTime.now());

            Date dateApproved = respostaDoPagamento.getDateApproved();
            LocalDateTime dataDeAprovacao = dateApproved != null ? dateApproved.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
            pagamento.setDataDeAprovacao(dataDeAprovacao);

            pagamento.setValorTotal(BigDecimal.valueOf(respostaDoPagamento.getTransactionAmount()));
            pagamento.setMetodoDePagamentoId(respostaDoPagamento.getPaymentMethodId());
            pagamento.setMetodoDePagamentoNome(null);


            pagamento.setPedido(pedido);


            pagamento = pagamentoRepository.save(pagamento);


            Transacao transacao = new Transacao();
            transacao.setTotal(pagamento.getValorTotal());
            transacao.setMetodoDePagamento(MetodoDePagamentoEnum.valueOf(pagamento.getMetodoDePagamentoNome()));
            transacao.setData(LocalDateTime.now());
            transacao.setPedido(pedido);

            transacaoRepository.save(transacao);
        } else {
            // Lida com o pagamento não aprovado, por exemplo, lançando uma exceção ou registrando um log
            throw new RuntimeException("O pagamento não foi aprovado: " + respostaDoPagamento.getStatus());
        }
    }
}
