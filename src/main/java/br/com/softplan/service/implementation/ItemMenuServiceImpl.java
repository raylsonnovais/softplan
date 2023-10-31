package br.com.softplan.service.implementation;

import br.com.softplan.domain.ItemMenu;
import br.com.softplan.repository.ItemMenuRepository;
import br.com.softplan.service.ItemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemMenuServiceImpl implements ItemMenuService {

    private final ItemMenuRepository itemMenuRepository;

    @Autowired
    public ItemMenuServiceImpl(ItemMenuRepository itemMenuRepository) {
        this.itemMenuRepository = itemMenuRepository;
    }

    @Override
    @Transactional
    public ItemMenu criarItemMenu(ItemMenu itemMenu) {
        return itemMenuRepository.save(itemMenu);
    }

    @Override
    @Transactional
    public ItemMenu atualizarItemMenu(Long id, ItemMenu itemAtualizado) {
        if(!itemMenuRepository.existsById(id)) {
            throw new IllegalArgumentException("Item do menu com ID " + id + " não encontrado.");
        }
        itemAtualizado.setId(id);
        return itemMenuRepository.save(itemAtualizado);
    }

    @Override
    @Transactional
    public void deletarItemMenu(Long id) {
        if(!itemMenuRepository.existsById(id)) {
            throw new IllegalArgumentException("Item do menu com ID " + id + " não encontrado.");
        }
        itemMenuRepository.deleteById(id);
    }

    @Override
    public List<ItemMenu> listarItensMenu() {
        return itemMenuRepository.findAll();
    }

    @Override
    public ItemMenu obterItemMenuPorId(Long id) {
        Optional<ItemMenu> optionalItemMenu = itemMenuRepository.findById(id);
        return optionalItemMenu.orElseThrow(() -> new IllegalArgumentException("Item do menu com ID " + id + " não encontrado."));
    }
}
