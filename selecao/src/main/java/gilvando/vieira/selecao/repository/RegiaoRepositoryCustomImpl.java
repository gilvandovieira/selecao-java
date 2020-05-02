package gilvando.vieira.selecao.repository;

import gilvando.vieira.selecao.model.MediaPorMunicipio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class RegiaoRepositoryCustomImpl implements RegiaoRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MediaPorMunicipio> getMediaPorMunicipio() {
        Query q = entityManager.createNativeQuery("SELECT r.municipio, t.produto, avg(t.valor_Compra) as media_Compra, avg(t.valor_Venda) as media_Venda " +
                "from sel_Regiao r join sel_Revenda re on r.id = re.regiao_id join sel_Transacao t on re.id = t.revenda_id " +
                        "where  t.valor_Compra is not null and t.valor_Venda is not null group by r.municipio, t.produto", MediaPorMunicipio.class);

        List<MediaPorMunicipio> mediaPorMunicipio =  q.getResultList();
        return mediaPorMunicipio;
    }
}
