package br.com.softplan.web.rest;

import br.com.softplan.domain.Mesa;
import br.com.softplan.service.MesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Mesa", description = "Crud de Mesa")
@RestController
@RequestMapping("/api/mesas")
public class MesaResource {

    private final MesaService mesaService;

    @Autowired
    public MesaResource(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @Operation(summary = "Criar uma nova mesa")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Mesa criada com sucesso", content = {
                    @Content(schema = @Schema(implementation = Mesa.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Mesa> criarMesa(@RequestBody Mesa mesa) {
        Mesa novaMesa = mesaService.criarMesa(mesa);
        return new ResponseEntity<>(novaMesa, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar uma mesa existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mesa atualizada com sucesso", content = {
                    @Content(schema = @Schema(implementation = Mesa.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Mesa não encontrada", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public ResponseEntity<Mesa> atualizarMesa(@PathVariable Long id, @RequestBody Mesa mesaAtualizada) {
        Mesa mesa = mesaService.atualizarMesa(id, mesaAtualizada);
        return new ResponseEntity<>(mesa, HttpStatus.OK);
    }

    @Operation(summary = "Deletar uma mesa existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Mesa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mesa não encontrada", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMesa(@PathVariable Long id) {
        mesaService.deletarMesa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Listar todas as mesas de uma filial")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de mesas recuperada com sucesso", content = {
                    @Content(schema = @Schema(implementation = Mesa.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/filial/{filialId}")
    public ResponseEntity<List<Mesa>> listarMesasPorFilial(@PathVariable Long filialId) {
        List<Mesa> mesas = mesaService.listarMesasPorFilial(filialId);
        return new ResponseEntity<>(mesas, HttpStatus.OK);
    }

    @Operation(summary = "Obter uma mesa por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mesa recuperada com sucesso", content = {
                    @Content(schema = @Schema(implementation = Mesa.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Mesa não encontrada", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<Mesa> obterMesaPorId(@PathVariable Long id) {
        Mesa mesa = mesaService.obterMesaPorId(id);
        return new ResponseEntity<>(mesa, HttpStatus.OK);
    }
}

