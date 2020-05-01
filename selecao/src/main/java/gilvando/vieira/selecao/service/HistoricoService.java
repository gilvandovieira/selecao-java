package gilvando.vieira.selecao.service;

import gilvando.vieira.selecao.model.Historico;

import java.util.Date;
import java.util.List;

public interface HistoricoService {

    Historico novoDadoHistorico(Historico historico);

    void atualizaDadoHistorico(Historico historico, Long id);

    void deletaHistorico(Long id);

    List<Historico> listaHistorico();

    List<Historico> listaHistoricoPorSiglaRegiao(String siglaRegiao);

    List<Historico> listaHistoricoPorDistribuidora(String nome);

    List<Historico> listaHistoricoPorDataDaColeta(Date date);
}
