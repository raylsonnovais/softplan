package br.com.softplan.web.rest;

import br.com.softplan.domain.Pedido;
import br.com.softplan.service.PagamentoService;
import com.mercadopago.exceptions.MPException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Tag(name = "Pagamento", description = "Pagamento de um pedido")
@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoResource {

    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoResource(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @Operation(summary = "Realizar pagamento de um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pagamento realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/realizar-pagamento")
    public ResponseEntity<String> realizarPagamento(@RequestBody Pedido pedido) {
        try {

            BigDecimal valorTotal = pedido.getTotal();

            pagamentoService.realizarPagamento(pedido, valorTotal);
            return new ResponseEntity<>("Pagamento realizado com sucesso", HttpStatus.OK);
        } catch (Exception ex) {

            return new ResponseEntity<>("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

