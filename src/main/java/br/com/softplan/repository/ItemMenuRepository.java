package br.com.softplan.repository;

import br.com.softplan.domain.ItemMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMenuRepository extends JpaRepository<ItemMenu, Long> {
}
