package gilvando.vieira.selecao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MediaPorMunicipio {

    @Id
    private String municipio;

    private Double mediaCompra;
    private Double mediaVenda;

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Double getMediaCompra() {
        return mediaCompra;
    }

    public void setMediaCompra(Double mediaCompra) {
        this.mediaCompra = mediaCompra;
    }

    public Double getMediaVenda() {
        return mediaVenda;
    }

    public void setMediaVenda(Double mediaVenda) {
        this.mediaVenda = mediaVenda;
    }
}
