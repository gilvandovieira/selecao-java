package gilvando.vieira.selecao;

import gilvando.vieira.selecao.model.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestesDeIntegracao {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void criaUsuario_RetornaOk() {
        ResponseEntity<Usuario> responseEntity = this.testRestTemplate.postForEntity("/api/usuarios", new Usuario("Gilvando", "email@email.com"), Usuario.class);
        Usuario usuario = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(usuario.getNome()).isEqualTo("Gilvando");
    }

    @Test
    public void listaUsuarios_RetornaOk() {
        this.testRestTemplate.postForEntity("/api/usuarios", new Usuario("Gilvando", "email1@email.com"), Usuario.class);
        ResponseEntity<Usuario[]> responseEntity = this.testRestTemplate.getForEntity("/api/usuarios", Usuario[].class);
        Usuario[] usuarios = responseEntity.getBody();
        assertThat(usuarios.length).isEqualTo(1);
//        RequestEntity requestEntity = RequestEntity.get(new URI("/api/usuarios")).accept(MediaType.APPLICATION_JSON).build();
//        ResponseEntity<List<Usuario>> responseEntity = this.testRestTemplate.exchange("/api/usuarios", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Usuario>>() {
//        });
//
//        List<Usuario> usuarios = responseEntity.getBody();
//        assertThat(usuarios.size()).isEqualTo(1);
    }

    @Test
    public void deletaUsuario_retornaOk() {

        ResponseEntity<Usuario> responseEntity = this.testRestTemplate.postForEntity("/api/usuarios", new Usuario("Gilvando", "email2@email.com"), Usuario.class);
        Usuario usuario = responseEntity.getBody();
        HttpEntity entity = HttpEntity.EMPTY;
        this.testRestTemplate.exchange("/api/usuarios/"+usuario.getId(), HttpMethod.DELETE, entity, void.class);
        ResponseEntity<Usuario[]> re = this.testRestTemplate.getForEntity("/api/usuarios", Usuario[].class);
        Usuario[] usuarios = re.getBody();
        assertThat(usuarios.length).isEqualTo(0);

    }
}
