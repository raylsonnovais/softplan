package br.com.softplan.web.rest;

import br.com.softplan.domain.ItensDoPedido;
import br.com.softplan.domain.Mesa;
import br.com.softplan.domain.Pedido;
import br.com.softplan.dto.requests.PedidoRequest;
import br.com.softplan.dto.responses.PedidoResponse;
import br.com.softplan.service.MesaService;
import br.com.softplan.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Pedido", description = "Crud de Pedido")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoResource {

    private final PedidoService pedidoService;
    private final MesaService mesaService;

    @Autowired
    public PedidoResource(PedidoService pedidoService, MesaService mesaService) {
        this.pedidoService = pedidoService;
        this.mesaService = mesaService;
    }

    @Operation(summary = "Criar um novo pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso", content = {
                    @Content(schema = @Schema(implementation = PedidoResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/criar")
    public ResponseEntity<PedidoResponse> criarPedido(@RequestBody PedidoRequest pedidoRequest, @RequestParam("mesaId") Long mesaId) {
        Mesa mesa = mesaService.obterMesaPorId(mesaId);
        List<ItensDoPedido> itens = pedidoRequest.getItensDoPedido();
        Pedido novoPedido = pedidoService.criarPedido(mesa, itens);
        PedidoResponse pedidoResponse = new PedidoResponse(novoPedido);
        return new ResponseEntity<>(pedidoResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um pedido existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso", content = {
                    @Content(schema = @Schema(implementation = PedidoResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PedidoResponse> atualizarPedido(@PathVariable Long id, @RequestBody PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoService.obterPedidoPorId(id);
        if (pedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ItensDoPedido> itens = pedidoRequest.getItensDoPedido();
        Pedido pedidoAtualizado = pedidoService.atualizarPedido(pedido, itens);
        PedidoResponse pedidoResponse = new PedidoResponse(pedidoAtualizado); // Suponha que você tenha uma classe PedidoResponse para mapear o resultado
        return new ResponseEntity<>(pedidoResponse, HttpStatus.OK);
    }

    @Operation(summary = "Deletar um pedido existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.obterPedidoPorId(id);
        if (pedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pedidoService.deletarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Listar todos os pedidos de uma mesa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pedidos recuperada com sucesso", content = {
                    @Content(schema = @Schema(implementation = PedidoResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/mesa/{mesaId}")
    public ResponseEntity<List<PedidoResponse>> listarPedidosPorMesa(@PathVariable Long mesaId) {
        List<Pedido> pedidos = pedidoService.listarPedidosPorMesa(mesaId);
        List<PedidoResponse> pedidoResponses = pedidos.stream()
                .map(PedidoResponse::new) // Suponha que você tenha uma classe PedidoResponse para mapear o resultado
                .collect(Collectors.toList());
        return new ResponseEntity<>(pedidoResponses, HttpStatus.OK);
    }

    @Operation(summary = "Obter um pedido por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido recuperado com sucesso", content = {
                    @Content(schema = @Schema(implementation = PedidoResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> obterPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.obterPedidoPorId(id);
        if (pedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PedidoResponse pedidoResponse = new PedidoResponse(pedido); // Suponha que você tenha uma classe PedidoResponse para mapear o resultado
        return new ResponseEntity<>(pedidoResponse, HttpStatus.OK);
    }

    // Endpoints anteriores...

    @Operation(summary = "Marcar um pedido como concluído")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido marcado como concluído com sucesso", content = {
                    @Content(schema = @Schema(implementation = PedidoResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Pedido não pode ser concluído", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("/concluir/{id}")
    public ResponseEntity<PedidoResponse> marcarPedidoComoConcluido(@PathVariable Long id) {
        Pedido pedido = pedidoService.obterPedidoPorId(id);
        if (pedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            Pedido pedidoConcluido = pedidoService.marcarPedidoComoConcluido(pedido);
            PedidoResponse pedidoResponse = new PedidoResponse(pedidoConcluido); // Suponha que você tenha uma classe PedidoResponse para mapear o resultado
            return new ResponseEntity<>(pedidoResponse, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

