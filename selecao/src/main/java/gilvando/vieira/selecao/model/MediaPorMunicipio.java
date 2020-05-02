package gilvando.vieira.selecao.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class MediaPorMunicipio {

    @Id
    private String municipio;

    private String produto;

    private Double mediaVenda;

    private Double mediaCompra;


    @Override
    public String toString() {
        return "MediaPorMunicipio{" +
                "municipio='" + municipio + '\'' +
                ", produto='" + produto + '\'' +
                ", mediaVenda=" + mediaVenda +
                ", mediaCompra=" + mediaCompra +
                '}';
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getMediaVenda() {
        return mediaVenda;
    }

    public void setMediaVenda(Double mediaVenda) {
        this.mediaVenda = mediaVenda;
    }

    public Double getMediaCompra() {
        return mediaCompra;
    }

    public void setMediaCompra(Double mediaCompra) {
        this.mediaCompra = mediaCompra;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }


}
