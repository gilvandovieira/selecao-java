package gilvando.vieira.selecao.repository;

import gilvando.vieira.selecao.model.MediaPorBandeira;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TransacaoRepositoryCustomImpl implements TransacaoRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    public TransacaoRepositoryCustomImpl() {
    }

    @Override
    public List<MediaPorBandeira> getMediaPorBandeira() {
        Query q = entityManager.createNativeQuery("SELECT t.bandeira, avg(t.valor_compra) media_compra, avg(t.valor_venda) media_venda  FROM SEL_TRANSACAO t group by t.bandeira order by t.bandeira", MediaPorBandeira.class);

        List<MediaPorBandeira> medias = q.getResultList();
        return medias;
    }
}
