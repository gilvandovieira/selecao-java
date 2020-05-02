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
import java.time.LocalDate;
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

    @GetMapping(produces = "application/json", path = "/sigla/{sigla}")
    public @ResponseBody List<Historico> listaHistoricoPorSiglaRegiao(@PathVariable("sigla") String sigla){

        return historicoService.listaHistoricoPorSiglaRegiao(sigla);
    }

    @GetMapping(produces = "application/json", path = "/distribuidora/{distribuidora}")
    public @ResponseBody List<Historico> listaHistoricoPorDistribuidora(@PathVariable("distribuidora") String distribuidora){

        return historicoService.listaHistoricoPorDistribuidora(distribuidora);
    }

    @GetMapping(produces = "application/json", path = "/data-coleta")
    public @ResponseBody List<Historico> listaHistoricoPorDataColeta(@RequestParam("data")LocalDate data){

        return historicoService.listaHistoricoPorDataDaColeta(data);
    }
}
