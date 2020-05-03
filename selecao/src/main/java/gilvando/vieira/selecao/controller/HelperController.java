package gilvando.vieira.selecao.controller;

import gilvando.vieira.selecao.repository.RegiaoRepository;
import gilvando.vieira.selecao.repository.RevendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/helpers")
public class HelperController {

    @Autowired
    RegiaoRepository regiaoRepository;

    @Autowired
    RevendaRepository revendaRepository;

    public HelperController() {
    }

    @GetMapping(path = "/siglas", produces = "application/json")
    public List<String> listaSiglasRegiao(){
        return regiaoRepository.findAllSiglas();

    }

    @GetMapping(path = "/revendas", produces = "application/json")
    public List<String> listaRevendas(){

        return revendaRepository.findAllRevendas();
    }

    @GetMapping(path = "/municipios", produces = "application/json")
    public List<String> listaMuncipios(){
        return regiaoRepository.findAllMunicipios();
    }

//    @GetMapping(path = "/distribuidoras", produces = "application/json")
//    public List<String> listaDistribuidoras(){
//        return null;
//    }
}
