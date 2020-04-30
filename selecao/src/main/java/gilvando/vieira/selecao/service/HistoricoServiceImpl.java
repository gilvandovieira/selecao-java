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

import java.util.Date;
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
    public void novoDadoHistorico(Historico historico) {
        Regiao regiao = regiaoRepository.findByMunicipioAndSiglaEstado(historico.getMunicipio(), historico.getSiglaEstado());
        if (regiao == null){
            regiao = regiaoRepository.save(new Regiao(historico.getSiglaRegiao(), historico.getSiglaEstado(), historico.getMunicipio()));
        }
        Revenda r = null;
        Revenda revenda = revendaRepository.findByCNPJ(historico.getCNPJ());
        if(revenda == null){
             r = new Revenda(historico.getNomeRevenda(), historico.getCNPJ());
            r.setRegiao(regiao);
            revendaRepository.save(r);
        }

        Transacao transacao = new Transacao(historico.getBandeira(),historico.getValorDeVenda(), historico.getValorDeCompra(),historico.getUnidadeDeMedida(),historico.getDataDacoleta());
        transacao.setRevenda(r);
        transacaoRepository.save(transacao);

    }

    @Override
    public void atualizaDadoHistorico(Historico historico) {
        Transacao transacao = transacaoRepository.findById(historico.getTransacao()).get();
        transacao.setBandeira(historico.getBandeira());
        transacao.setDataDaColeta(historico.getDataDacoleta());
        transacao.setValorVenda(historico.getValorDeVenda());
        transacao.setValorCompra(historico.getValorDeCompra());
        transacao.setUnidadeDeMedida(historico.getUnidadeDeMedida());
        transacaoRepository.save(transacao);
    }

    @Override
    public void deletaHistorico(Historico historico) {
        transacaoRepository.deleteById(historico.getTransacao());
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
        List<Regiao> regioes = regiaoRepository.finAllBySiglaRegiao(siglaRegiao);
        List<Historico> historicos = new LinkedList<>();

        for(Regiao r: regioes){
            for(Revenda revenda: r.getRevendas()){
                for (Transacao transacao: revenda.getTransacoes()){
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

        for (Revenda revenda: revendas){
            for(Transacao transacao: revenda.getTransacoes()){
                appendHistorico(historicos,transacao);
            }
        }
        return historicos;
    }

    @Override
    public List<Historico> listaHistoricoPorDataDaColeta(Date date) {
        List<Transacao> transacoes = transacaoRepository.findAllByDataDaColeta(date);
        List<Historico> historicos = new LinkedList<>();

        for(Transacao transacao: transacoes){
            appendHistorico(historicos,transacao);
        }
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
        historico.setDataDacoleta(transacao.getDataDaColeta());
        historico.setValorDeVenda(transacao.getValorVenda());
        historico.setValorDeCompra(transacao.getValorCompra());
        historico.setBandeira(transacao.getBandeira());
        historicos.add(historico);
    }
}