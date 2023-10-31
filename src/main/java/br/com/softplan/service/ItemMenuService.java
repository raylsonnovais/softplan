package br.com.softplan.service;

import br.com.softplan.domain.ItemMenu;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ItemMenuService {
    @Transactional
    ItemMenu criarItemMenu(ItemMenu itemMenu);
    @Transactional
    ItemMenu atualizarItemMenu(Long id, ItemMenu itemAtualizado);
    @Transactional
    void deletarItemMenu(Long id);
    List<ItemMenu> listarItensMenu();
    ItemMenu obterItemMenuPorId(Long id);
}
