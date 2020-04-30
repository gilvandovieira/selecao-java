package gilvando.vieira.selecao.repository;

import gilvando.vieira.selecao.model.MediaPorMunicipio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class RegiaoRepositoryCustomImpl implements RegiaoRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public MediaPorMunicipio getMediaPorMunicipio(String municipio) {
        Query q = entityManager.createNativeQuery("SELECT r.municipio, avg(t.valor_Compra) as media_Compra, avg(t.valor_Venda) as media_Venda " +
                "from sel_Regiao r join sel_Revenda re on r.id = re.regiao_id join sel_Transacao t on re.id = t.revenda_id " +
                        "where r.municipio = ?1 and t.valor_Compra is not null and t.valor_Venda is not null group by r.municipio", MediaPorMunicipio.class);

        q.setParameter(1,municipio);
        MediaPorMunicipio mediaPorMunicipio = (MediaPorMunicipio) q.getSingleResult();
        return mediaPorMunicipio;
    }
}
