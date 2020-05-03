package gilvando.vieira.selecao.controller;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import gilvando.vieira.selecao.model.Historico;
import gilvando.vieira.selecao.service.HistoricoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream(), StandardCharsets.UTF_16));

                CsvToBean<Historico> csvToBean = new CsvToBeanBuilder<Historico>(reader).withType(Historico.class).withSeparator('\t').withIgnoreLeadingWhiteSpace(true).build();

                List<Historico> historicos = csvToBean.parse();

//                for (Historico historico: historicos){
//                    historicoService.novoDadoHistorico(historico);
//                }
                historicos.stream().parallel().forEach(historico -> historicoService.novoDadoHistorico(historico));
                return historicos.subList(0,10);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @GetMapping(produces = "application/json", path = "/sigla/{sigla}")
    public @ResponseBody List<Historico> listaHistoricoPorSiglaRegiao(@PathVariable String sigla){

        return historicoService.listaHistoricoPorSiglaRegiao(sigla);
    }

    @GetMapping(produces = "application/json", path = "/distribuidora/{distribuidora}")
    public @ResponseBody List<Historico> listaHistoricoPorDistribuidora(@PathVariable String distribuidora){

        return historicoService.listaHistoricoPorDistribuidora(distribuidora);
    }

    @GetMapping(produces = "application/json", path = "/data-coleta")
    public @ResponseBody List<Historico> listaHistoricoPorDataColeta(@RequestParam("data")String data){
        LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return historicoService.listaHistoricoPorDataDaColeta(localDate);
    }
}
