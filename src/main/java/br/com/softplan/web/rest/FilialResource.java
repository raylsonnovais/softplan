package br.com.softplan.web.rest;

import br.com.softplan.domain.Filial;
import br.com.softplan.dto.requests.FilialRequest;
import br.com.softplan.dto.responses.FilialResponse;
import br.com.softplan.service.FilialService;
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
import java.util.stream.Collectors;

@Tag(name = "Filial", description = "Crud de Filial")
@RestController
@RequestMapping("/api/filiais")
public class FilialResource {

    private final FilialService filialService;

    @Autowired
    public FilialResource(FilialService filialService) {
        this.filialService = filialService;
    }

    @Operation(summary = "Criar uma nova filial")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Filial criada com sucesso", content = {
                    @Content(schema = @Schema(implementation = FilialResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<FilialResponse> criarFilial(@RequestBody FilialRequest filialRequest) {
        Filial novaFilial = filialService.criarFilial(filialRequest.toFilial());
        return new ResponseEntity<>(FilialResponse.fromFilial(novaFilial), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar uma filial existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filial atualizada com sucesso", content = {
                    @Content(schema = @Schema(implementation = FilialResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Filial não encontrada", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public ResponseEntity<FilialResponse> atualizarFilial(@PathVariable Long id,
                                                          @RequestBody FilialRequest filialRequest) {
        Filial filialAtualizada = filialService.atualizarFilial(id, filialRequest.toFilial());
        return new ResponseEntity<>(FilialResponse.fromFilial(filialAtualizada), HttpStatus.OK);
    }

    @Operation(summary = "Deletar uma filial existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Filial deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filial não encontrada", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilial(@PathVariable Long id) {
        filialService.deletarFilial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Listar todas as filiais")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de filiais recuperada com sucesso", content = {
                    @Content(schema = @Schema(implementation = FilialResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<FilialResponse>> listarFiliais() {
        List<Filial> filiais = filialService.listarFiliais();
        List<FilialResponse> responses = filiais.stream()
                .map(FilialResponse::fromFilial)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Operation(summary = "Obter uma filial por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filial recuperada com sucesso", content = {
                    @Content(schema = @Schema(implementation = FilialResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Filial não encontrada", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<FilialResponse> obterFilialPorId(@PathVariable Long id) {
        Filial filial = filialService.obterFilialPorId(id);
        return new ResponseEntity<>(FilialResponse.fromFilial(filial), HttpStatus.OK);
    }
}


