package gilvando.vieira.selecao.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "sel_transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bandeira;
    private String produto;
    private Double valorVenda;
    private Double valorCompra;
    private String unidadeDeMedida;
    private LocalDate dataDaColeta;

    @ManyToOne
    @JoinColumn(name = "revenda_id")
    private Revenda revenda;

    public Revenda getRevenda() {
        return revenda;
    }

    public void setRevenda(Revenda revenda) {
        this.revenda = revenda;
    }

    public Transacao() {
    }

    public Transacao(String bandeira, Double valorVenda, Double valorCompra, String unidadeDeMedida, LocalDate dataDaColeta, String produto) {
        this.bandeira = bandeira;
        this.valorVenda = valorVenda;
        this.valorCompra = valorCompra;
        this.unidadeDeMedida = unidadeDeMedida;
        this.dataDaColeta = dataDaColeta;
        this.produto = produto;
    }

    public Transacao(Long id, String bandeira, Double valorVenda, Double valorCompra, String unidadeDeMedida, LocalDate dataDaColeta, String produto) {
        this.id = id;
        this.bandeira = bandeira;
        this.valorVenda = valorVenda;
        this.valorCompra = valorCompra;
        this.unidadeDeMedida = unidadeDeMedida;
        this.dataDaColeta = dataDaColeta;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(String unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public LocalDate getDataDaColeta() {
        return dataDaColeta;
    }

    public void setDataDaColeta(LocalDate dataDaColeta) {
        this.dataDaColeta = dataDaColeta;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equal(id, transacao.id) &&
                Objects.equal(bandeira, transacao.bandeira) &&
                Objects.equal(produto, transacao.produto) &&
                Objects.equal(valorVenda, transacao.valorVenda) &&
                Objects.equal(valorCompra, transacao.valorCompra) &&
                Objects.equal(unidadeDeMedida, transacao.unidadeDeMedida) &&
                Objects.equal(dataDaColeta, transacao.dataDaColeta) &&
                Objects.equal(revenda, transacao.revenda);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, bandeira, produto, valorVenda, valorCompra, unidadeDeMedida, dataDaColeta, revenda);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", bandeira='" + bandeira + '\'' +
                ", produto='" + produto + '\'' +
                ", valorVenda=" + valorVenda +
                ", valorCompra=" + valorCompra +
                ", unidadeDeMedida='" + unidadeDeMedida + '\'' +
                ", dataDaColeta=" + dataDaColeta +
                ", revenda=" + revenda +
                '}';
    }
}
