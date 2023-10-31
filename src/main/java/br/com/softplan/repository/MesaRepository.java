package br.com.softplan.repository;

import br.com.softplan.domain.Filial;
import br.com.softplan.domain.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
   List<Mesa> findByFilialId(Long filialId);
}
