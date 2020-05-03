package gilvando.vieira.selecao.repository;

import gilvando.vieira.selecao.model.MyString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class RevendaRepositoryCustomImpl implements RevendaRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    public RevendaRepositoryCustomImpl() {
    }

    @Override
    public List<String> findAllRevendas() {
        Query q = entityManager.createNativeQuery("SELECT DISTINCT 1 id ,r.nome s FROM SEL_REVENDA r ORDER BY r.nome", MyString.class);
        List <MyString> myStrings = q.getResultList();
        return myStrings.stream().map(my -> my.getS()).collect(Collectors.toList());
    }

}
