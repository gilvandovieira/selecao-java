package gilvando.vieira.selecao.repository;

import gilvando.vieira.selecao.model.MediaPorMunicipio;
import gilvando.vieira.selecao.model.Regiao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegiaoRepository extends CrudRepository<Regiao, Long>, RegiaoRepositoryCustom {

    Regiao findByMunicipioAndSiglaEstado(String municipio, String siglaEstado);
    List<Regiao> finAllBySiglaRegiao(String siglaRegiao);
}
