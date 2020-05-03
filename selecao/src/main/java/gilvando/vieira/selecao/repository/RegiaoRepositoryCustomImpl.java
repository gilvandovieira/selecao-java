package gilvando.vieira.selecao.repository;

import gilvando.vieira.selecao.model.MediaPorMunicipio;
import gilvando.vieira.selecao.model.MyString;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class RegiaoRepositoryCustomImpl implements RegiaoRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MediaPorMunicipio> listMediaPorMunicipio() {
        Query q = entityManager.createNativeQuery("SELECT DISTINCT r.municipio, t.produto, avg(t.valor_Compra) as media_Compra, avg(t.valor_Venda) as media_Venda " +
                "from sel_Regiao r join sel_Revenda re on r.id = re.regiao_id join sel_Transacao t on re.id = t.revenda_id " +
                        "group by r.municipio, t.produto order by r.municipio, t.produto", MediaPorMunicipio.class);

        List<MediaPorMunicipio> mediaPorMunicipio = (List<MediaPorMunicipio>) q.getResultList();
        return mediaPorMunicipio;
    }

    @Override
    public List<MediaPorMunicipio> getMediaPorMunicipio(String municipio) {
        Query q = entityManager.createNativeQuery("SELECT DISTINCT r.municipio, t.produto, avg(t.valor_Compra) as media_Compra, avg(t.valor_Venda) as media_Venda " +
                "from sel_Regiao r join sel_Revenda re on r.id = re.regiao_id join sel_Transacao t on re.id = t.revenda_id " +
                "where r.municipio = ?1 group by r.municipio, t.produto order by r.municipio, t.produto", MediaPorMunicipio.class);
        q.setParameter(1,municipio);
        return q.getResultList();
    }

    @Override
    public List<String> findAllSiglas() {
        Query q = entityManager.createNativeQuery("SELECT DISTINCT 1 id, r.sigla_regiao s FROM SEL_REGIAO r ORDER BY r.sigla_regiao", MyString.class);
        List<MyString> myStrings = q.getResultList();
        return myStrings.stream().map(my -> my.getS()).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllMunicipios() {
        Query q = entityManager.createNativeQuery("SELECT DISTINCT 1 id, r.municipio s FROM SEL_REGIAO r ORDER BY r.municipio", MyString.class);
        List<MyString> myStrings = q.getResultList();
        return myStrings.stream().map(myString -> myString.getS()).collect(Collectors.toList());
    }
}
