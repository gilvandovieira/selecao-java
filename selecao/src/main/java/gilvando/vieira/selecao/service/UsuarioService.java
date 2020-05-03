package gilvando.vieira.selecao.service;

import gilvando.vieira.selecao.model.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario criaUsuario(String nome, String email);
    List<Usuario> usuarioPorNome(String nome);
    Usuario usuarioPorEmail(String email);
    List<Usuario> todosUsuario();
    void atualizaUsuario(Usuario usuario);
    void deletaUsuario(Long id);
}
