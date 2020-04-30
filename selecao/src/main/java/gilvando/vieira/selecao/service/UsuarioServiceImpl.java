package gilvando.vieira.selecao.service;

import gilvando.vieira.selecao.model.Usuario;
import gilvando.vieira.selecao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository ur;

    public UsuarioServiceImpl() {
    }

    @Override
    public void criaUsuario(String nome, String email) {
        ur.save(new Usuario(nome,email));
    }

    @Override
    public List<Usuario> usuarioPorNome(String nome) {
        List<Usuario> usuarios = ur.findAllByNome(nome);
        return usuarios;
    }

    @Override
    public Usuario usuarioPorEmail(String email) {
        Usuario usuario = ur.findByEmail(email);
        return usuario;
    }

    @Override
    public List<Usuario> todosUsuario() {
        List<Usuario> usuarios = (List<Usuario>) ur.findAll();

        return usuarios;
    }

    @Override
    public void atualizaUsuario(Usuario usuario) {
        Usuario u = ur.findById(usuario.getId()).get();
        u.setEmail(usuario.getEmail());
        u.setNome(usuario.getNome());
        ur.save(u);
    }

    @Override
    public void deletaUsuario(Long id) {
        ur.deleteById(id);
    }
}
