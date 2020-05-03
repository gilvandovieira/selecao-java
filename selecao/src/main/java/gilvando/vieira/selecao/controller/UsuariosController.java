package gilvando.vieira.selecao.controller;

import gilvando.vieira.selecao.model.Usuario;
import gilvando.vieira.selecao.service.UsuarioService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/usuarios")
public class UsuariosController {

    private final UsuarioService usuarioService;

    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(produces = "application/json")
    public Usuario criaUsuario(@RequestBody Usuario usuario) {

        return this.usuarioService.criaUsuario(usuario.getNome(), usuario.getEmail());

    }

    @GetMapping(produces = "application/json")
    public List<Usuario> listaUsuarios() {

        return this.usuarioService.todosUsuario();
    }

    @DeleteMapping(path = "/{id}")
    public void deletaUsuario(@PathVariable("id") Long id) {

        this.usuarioService.deletaUsuario(id);
    }
}
