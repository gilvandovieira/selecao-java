package gilvando.vieira.selecao.service;

import gilvando.vieira.selecao.model.Historico;

import java.util.Date;
import java.util.List;

public interface HistoricoService {

    void novoDadoHistorico(Historico historico);

    void atualizaDadoHistorico(Historico historico);

    void deletaHistorico(Historico historico);

    List<Historico> listaHistorico();

    List<Historico> listaHistoricoPorSiglaRegiao(String siglaRegiao);

    List<Historico> listaHistoricoPorDistribuidora(String nome);

    List<Historico> listaHistoricoPorDataDaColeta(Date date);
}
