package gilvando.vieira.selecao.controller;

import gilvando.vieira.selecao.model.MediaPorMunicipio;
import gilvando.vieira.selecao.service.EstatisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estatiscas")
public class EstatisticaController {

    @Autowired
    EstatisticaService estatisticaService;

    public EstatisticaController() {
    }

    public EstatisticaController(EstatisticaService estatisticaService) {
        this.estatisticaService = estatisticaService;
    }


    @GetMapping(path = "/media-municipios")
    public List<MediaPorMunicipio> getMediaPrecoCombustivelPorMunicipio(){
        return estatisticaService.listaValorMedioPorMunicipio();

    }
}
