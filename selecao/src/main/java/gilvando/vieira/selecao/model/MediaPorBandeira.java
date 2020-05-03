package gilvando.vieira.selecao.model;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MediaPorBandeira {

    @Id
    private String bandeira;

    private Double mediaCompra;

    private Double mediaVenda;

    @Override
    public String toString() {
        return "MediaPorBandeira{" +
                "bandeira='" + bandeira + '\'' +
                ", mediaCompra=" + mediaCompra +
                ", mediaVenda=" + mediaVenda +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaPorBandeira that = (MediaPorBandeira) o;
        return Objects.equal(bandeira, that.bandeira) &&
                Objects.equal(mediaCompra, that.mediaCompra) &&
                Objects.equal(mediaVenda, that.mediaVenda);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bandeira, mediaCompra, mediaVenda);
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
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
