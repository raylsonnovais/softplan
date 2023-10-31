package br.com.softplan.repository;

import br.com.softplan.domain.LogDeAcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDeAcaoRepository extends JpaRepository<LogDeAcao, Long> {

}
