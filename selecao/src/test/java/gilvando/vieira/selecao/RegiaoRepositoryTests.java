package gilvando.vieira.selecao;

import gilvando.vieira.selecao.model.Historico;
import gilvando.vieira.selecao.model.MediaPorMunicipio;
import gilvando.vieira.selecao.repository.RegiaoRepository;
import gilvando.vieira.selecao.repository.RevendaRepository;
import gilvando.vieira.selecao.repository.TransacaoRepository;
import gilvando.vieira.selecao.service.HistoricoService;
import gilvando.vieira.selecao.service.HistoricoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RegiaoRepositoryTests {


    HistoricoService historicoService;

    @Autowired
    RegiaoRepository regiaoRepository;

    @Autowired
    RevendaRepository revendaRepository;

    @Autowired
    TransacaoRepository transacaoRepository;

    @Before
    public void setUp(){
        historicoService = new HistoricoServiceImpl(transacaoRepository,regiaoRepository,revendaRepository);
    }

    @Test
    public void getMediaPorMunicipio_Test(){
        Historico historico = new Historico();
        historico.setSiglaRegiao("CO");
        historico.setSiglaEstado("DF");
        historico.setMunicipio("BRASILIA");
        historico.setNomeRevenda("ABRITTA POSTOS DE SERVIÇOS LTDA");
        historico.setCNPJ("10668863000195");
        historico.setDataDaColeta("01/02/1990");
        historico.setProduto("DIESEL");
        historico.setValorDeVenda(Double.toString(3.799));
        historico.setValorDeCompra(Double.toString(3.2166));
        historico.setUnidadeDeMedida("R$ / litro");
        historico.setBandeira("RAIZEN");
        historicoService.novoDadoHistorico(historico);

        MediaPorMunicipio mediaPorMunicipio = regiaoRepository.getMediaPorMunicipio("BRASILIA");
        System.out.println(mediaPorMunicipio.getMunicipio());
        System.out.println(mediaPorMunicipio.getMediaCompra());
        System.out.println(mediaPorMunicipio.getMediaVenda());
    }

}
