package gilvando.vieira.selecao.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class Historico {


    @Max(value = 2, message = "Região com dois caracteres. Ex: CO <- Centro Oeste")
    @CsvBindByName(column = "Região - Sigla", required = false)
    private String siglaRegiao;
    @CsvBindByName(column = "Estado - Sigla", required = false)
    @Max(value = 2, message = "Estado com duas letras. Ex: DF <- Distrito Federal")
    private String siglaEstado;
    @CsvBindByName(column = "Município", required = false)
    private String municipio;
    @CsvBindByName(column = "Revenda", required = false)
    private String nomeRevenda;
    @CsvBindByName(column = "CNPJ da Revenda", required = false)
    private String CNPJ;
    @CsvBindByName(column = "Produto", required = false)
    private String produto;
    @CsvBindByName(column = "Data da Coleta", required = false)
    private String dataDaColeta;

    @CsvBindByName(column = "Valor da Venda", required = false)
    @NotNull(message = "Exemplo de valor: '2.999'")
    private String valorDeVenda;

    @CsvBindByName(column = "Valor de Compra", required = false)
    @NotNull(message = "Exemplo de valor: '3.999'")
    private String valorDeCompra;
    @CsvBindByName(column = "Unidade de Medida", required = false)
    private String unidadeDeMedida;
    @CsvBindByName(column = "Bandeira", required = false)
    private String bandeira;

    @Override
    public String toString() {
        return "Historico{" +
                "siglaRegiao='" + siglaRegiao + '\'' +
                ", siglaEstado='" + siglaEstado + '\'' +
                ", municipio='" + municipio + '\'' +
                ", nomeRevenda='" + nomeRevenda + '\'' +
                ", CNPJ='" + CNPJ + '\'' +
                ", produto='" + produto + '\'' +
                ", dataDacoleta='" + dataDaColeta + '\'' +
                ", valorDeVenda='" + valorDeVenda + '\'' +
                ", valorDeCompra='" + valorDeCompra + '\'' +
                ", unidadeDeMedida='" + unidadeDeMedida + '\'' +
                ", bandeira='" + bandeira + '\'' +
                '}';
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

    public String getDataDaColeta() {
        return dataDaColeta;
    }

    public void setDataDaColeta(String dataDacoleta) {
        this.dataDaColeta = dataDacoleta;
    }

    public String getValorDeVenda() {
        return valorDeVenda;
    }

    public void setValorDeVenda(String valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

    public String getValorDeCompra() {
        return valorDeCompra;
    }

    public void setValorDeCompra(String valorDeCompra) {
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
