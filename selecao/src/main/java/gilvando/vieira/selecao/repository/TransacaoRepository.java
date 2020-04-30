package gilvando.vieira.selecao.repository;

import gilvando.vieira.selecao.model.Transacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

    List<Transacao> findAllByDataDaColeta(Date date);
}
