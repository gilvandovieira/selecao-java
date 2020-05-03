package gilvando.vieira.selecao.service;

import gilvando.vieira.selecao.model.Historico;
import gilvando.vieira.selecao.model.Regiao;
import gilvando.vieira.selecao.model.Revenda;
import gilvando.vieira.selecao.model.Transacao;
import gilvando.vieira.selecao.repository.RegiaoRepository;
import gilvando.vieira.selecao.repository.RevendaRepository;
import gilvando.vieira.selecao.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Service
public class HistoricoServiceImpl implements HistoricoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    RegiaoRepository regiaoRepository;

    @Autowired
    RevendaRepository revendaRepository;

    public HistoricoServiceImpl() {
    }

    public HistoricoServiceImpl(TransacaoRepository transacaoRepository, RegiaoRepository regiaoRepository, RevendaRepository revendaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.regiaoRepository = regiaoRepository;
        this.revendaRepository = revendaRepository;
    }

    @Override
    public Historico novoDadoHistorico(Historico historico) {
        //System.out.println(historico);
        if (historico.getValorDeVenda() == null || historico.getValorDeVenda().isEmpty()) {
            historico.setValorDeVenda("0.000");
        }
        if (historico.getValorDeCompra() == null || historico.getValorDeCompra().isEmpty()) {
            historico.setValorDeCompra("0.000");
        }

        historico.setValorDeVenda(historico.getValorDeVenda().replace(",", "."));
        historico.setValorDeCompra(historico.getValorDeCompra().replace(",", "."));
        Regiao regiao = regiaoRepository.findByMunicipioAndSiglaEstado(historico.getMunicipio(), historico.getSiglaEstado());
        if (regiao == null) {
            regiao = regiaoRepository.save(new Regiao(historico.getSiglaRegiao(), historico.getSiglaEstado(), historico.getMunicipio()));
        }

        Revenda revenda = revendaRepository.findByCNPJ(historico.getCNPJ());
        if (revenda == null) {
            revenda = new Revenda(historico.getNomeRevenda(), historico.getCNPJ());
            revenda.setRegiao(regiao);
            revendaRepository.save(revenda);
        }

        Transacao transacao = new Transacao(historico.getBandeira(), Double.valueOf(historico.getValorDeVenda()), Double.valueOf(historico.getValorDeCompra()), historico.getUnidadeDeMedida(), LocalDate.parse(historico.getDataDaColeta(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), historico.getProduto());
        transacao.setRevenda(revenda);
        transacaoRepository.save(transacao);
        return historico;
    }

    @Override
    public void atualizaDadoHistorico(Historico historico, Long id) {
        Transacao transacao = transacaoRepository.findById(id).get();
        transacao.setBandeira(historico.getBandeira());
        transacao.setDataDaColeta(LocalDate.now());
        transacao.setValorVenda(Double.valueOf(historico.getValorDeVenda()));
        transacao.setValorCompra(Double.valueOf(historico.getValorDeCompra()));
        transacao.setUnidadeDeMedida(historico.getUnidadeDeMedida());
        transacaoRepository.save(transacao);

    }

    @Override
    public void deletaHistorico(Long id) {
        transacaoRepository.deleteById(id);
    }

    @Override
    public List<Historico> listaHistorico() {
        List<Historico> historicos = new LinkedList<>();
        transacaoRepository.findAll().forEach(transacao -> {
            appendHistorico(historicos, transacao);
        });

        return historicos;
    }

    @Override
    public List<Historico> listaHistoricoPorSiglaRegiao(String siglaRegiao) {
        List<Regiao> regioes = regiaoRepository.findAllBySiglaRegiao(siglaRegiao);
        List<Historico> historicos = new LinkedList<>();

        for (Regiao r : regioes) {
            for (Revenda revenda : r.getRevendas()) {
                for (Transacao transacao : revenda.getTransacoes()) {
                    appendHistorico(historicos, transacao);
                }
            }
        }

        return historicos;
    }

    @Override
    public List<Historico> listaHistoricoPorDistribuidora(String nome) {
        List<Revenda> revendas = revendaRepository.findAllByNome(nome);
        List<Historico> historicos = new LinkedList<>();

        revendas.stream().map(revenda -> revenda.getTransacoes()).forEach(transacaos -> transacaos.stream().forEach(transacao -> appendHistorico(historicos, transacao)));


        return historicos;
    }

    @Override
    public List<Historico> listaHistoricoPorDataDaColeta(LocalDate date) {
        List<Transacao> transacoes = transacaoRepository.findAllByDataDaColeta(date);
        List<Historico> historicos = new LinkedList<>();

        transacoes.stream().forEach(transacao -> appendHistorico(historicos, transacao));


        return historicos;
    }

    private void appendHistorico(List<Historico> historicos, Transacao transacao) {
        Historico historico = new Historico();
        historico.setSiglaRegiao(transacao.getRevenda().getRegiao().getSiglaRegiao());
        historico.setSiglaEstado(transacao.getRevenda().getRegiao().getSiglaEstado());
        historico.setMunicipio(transacao.getRevenda().getRegiao().getMunicipio());
        historico.setNomeRevenda(transacao.getRevenda().getNome());
        historico.setCNPJ(transacao.getRevenda().getCNPJ());
        historico.setProduto(transacao.getProduto());
        historico.setDataDaColeta(transacao.getDataDaColeta().toString());
        historico.setValorDeVenda(transacao.getValorVenda().toString());
        historico.setValorDeCompra(transacao.getValorCompra().toString());
        historico.setBandeira(transacao.getBandeira());
        historicos.add(historico);
    }
}
