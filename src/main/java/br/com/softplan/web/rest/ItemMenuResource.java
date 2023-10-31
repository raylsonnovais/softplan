package br.com.softplan.web.rest;

import br.com.softplan.domain.ItemMenu;
import br.com.softplan.dto.requests.ItemMenuRequest;
import br.com.softplan.dto.responses.ItemMenuResponse;
import br.com.softplan.service.ItemMenuService;
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

@Tag(name = "Item do Menu", description = "Crud de Item do Menu")
@RestController
@RequestMapping("/api/itens-menu")
public class ItemMenuResource {

    private final ItemMenuService itemMenuService;

    @Autowired
    public ItemMenuResource(ItemMenuService itemMenuService) {
        this.itemMenuService = itemMenuService;
    }

    @Operation(summary = "Criar um novo item do menu")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Item do menu criado com sucesso",
                    content = { @Content(schema = @Schema(implementation = ItemMenuResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<ItemMenuResponse> criarItemMenu(@RequestBody ItemMenuRequest itemMenuRequest) {
        ItemMenu itemMenu = itemMenuService.criarItemMenu(itemMenuRequest.toItemMenu());
        return new ResponseEntity<>(ItemMenuResponse.fromItemMenu(itemMenu), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar um item do menu existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item do menu atualizado com sucesso",
                    content = { @Content(schema = @Schema(implementation = ItemMenuResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Item do menu não encontrado", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("/{id}")
    public ResponseEntity<ItemMenuResponse> atualizarItemMenu(@PathVariable Long id,
                                                              @RequestBody ItemMenuRequest itemMenuRequest) {
        ItemMenu itemMenuAtualizado = itemMenuService.atualizarItemMenu(id, itemMenuRequest.toItemMenu());
        return new ResponseEntity<>(ItemMenuResponse.fromItemMenu(itemMenuAtualizado), HttpStatus.OK);
    }

    @Operation(summary = "Deletar um item do menu existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Item do menu deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item do menu não encontrado", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItemMenu(@PathVariable Long id) {
        itemMenuService.deletarItemMenu(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Listar todos os itens do menu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de itens do menu recuperada com sucesso",
                    content = { @Content(schema = @Schema(implementation = ItemMenuResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) })
    })
    @GetMapping
    public ResponseEntity<List<ItemMenuResponse>> listarItensMenu() {
        List<ItemMenu> itensMenu = itemMenuService.listarItensMenu();
        List<ItemMenuResponse> responses = itensMenu.stream()
                .map(ItemMenuResponse::fromItemMenu)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Operation(summary = "Obter um item do menu por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item do menu recuperado com sucesso",
                    content = { @Content(schema = @Schema(implementation = ItemMenuResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Item do menu não encontrado", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemMenuResponse> obterItemMenuPorId(@PathVariable Long id) {
        ItemMenu itemMenu = itemMenuService.obterItemMenuPorId(id);
        return new ResponseEntity<>(ItemMenuResponse.fromItemMenu(itemMenu), HttpStatus.OK);
    }
}

