package gilvando.vieira.selecao.model;

import java.util.Date;

public class Historico {

    private String siglaRegiao;
    private String siglaEstado;
    private String municipio;
    private String nomeRevenda;
    private String CNPJ;
    private String produto;
    private Date dataDacoleta;
    private Double valorDeVenda;
    private Double valorDeCompra;
    private String unidadeDeMedida;
    private String bandeira;
    private Long transacao;

    public Long getTransacao() {
        return transacao;
    }

    public void setTransacao(Long transacao) {
        this.transacao = transacao;
    }

    public String getSiglaRegiao() {
        return siglaRegiao;
    }

    public void setSiglaRegiao(String siglaRegiao) {
        this.siglaRegiao = siglaRegiao;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNomeRevenda() {
        return nomeRevenda;
    }

    public void setNomeRevenda(String nomeRevenda) {
        this.nomeRevenda = nomeRevenda;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Date getDataDacoleta() {
        return dataDacoleta;
    }

    public void setDataDacoleta(Date dataDacoleta) {
        this.dataDacoleta = dataDacoleta;
    }

    public Double getValorDeVenda() {
        return valorDeVenda;
    }

    public void setValorDeVenda(Double valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

    public Double getValorDeCompra() {
        return valorDeCompra;
    }

    public void setValorDeCompra(Double valorDeCompra) {
        this.valorDeCompra = valorDeCompra;
    }

    public String getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(String unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
}
