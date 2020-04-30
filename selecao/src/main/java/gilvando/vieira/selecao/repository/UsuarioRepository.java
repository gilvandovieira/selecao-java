package gilvando.vieira.selecao.repository;

import gilvando.vieira.selecao.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    List<Usuario> findAllByNome(String nome);
    Usuario findByEmail(String email);
}
