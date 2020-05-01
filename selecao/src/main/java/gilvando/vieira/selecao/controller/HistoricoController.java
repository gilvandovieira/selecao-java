package gilvando.vieira.selecao.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import gilvando.vieira.selecao.model.Historico;
import gilvando.vieira.selecao.service.HistoricoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping(path = "/api/historico")
public class HistoricoController {

    private HistoricoService historicoService;

    public HistoricoController(HistoricoService hs) {
        this.historicoService = hs;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    Historico
    criaHistorico(@RequestBody Historico historico) {
        historicoService.novoDadoHistorico(historico);
        return historico;


    }

    @PostMapping(produces = "application/json", path = "/csv")
    public @ResponseBody
    List<Historico> enviaCsv(@RequestParam("arquivo") MultipartFile arquivo) {
        if (!arquivo.isEmpty()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()));

                CsvToBean<Historico> csvToBean = new CsvToBeanBuilder(reader).withType(Historico.class).build();

                List<Historico> historicos = csvToBean.parse();

                for (Historico historico: historicos){
                    historicoService.novoDadoHistorico(historico);
                }
                return historicos;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
