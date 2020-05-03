package gilvando.vieira.selecao.repository;

import gilvando.vieira.selecao.model.Revenda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevendaRepository extends CrudRepository<Revenda, Long>, RevendaRepositoryCustom {

    Revenda findByCNPJ(String cnpj);
    List<Revenda> findAllByNome(String nome);
}
