package gilvando.vieira.selecao.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sel_regiao")
public class Regiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String siglaRegiao;
    private String siglaEstado;
    private String municipio;

    @OneToMany(mappedBy = "regiao")
    private List<Revenda> revendas;

    public List<Revenda> getRevendas() {
        return revendas;
    }

    public Regiao() {
    }

    public Regiao(String siglaRegiao, String siglaEstado, String municipio) {
        this.siglaRegiao = siglaRegiao;
        this.siglaEstado = siglaEstado;
        this.municipio = municipio;
    }

    public Regiao(Long id, String siglaRegiao, String siglaEstado, String municipio) {
        this.id = id;
        this.siglaRegiao = siglaRegiao;
        this.siglaEstado = siglaEstado;
        this.municipio = municipio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Regiao regiao = (Regiao) o;
        return Objects.equal(id, regiao.id) &&
                Objects.equal(siglaRegiao, regiao.siglaRegiao) &&
                Objects.equal(siglaEstado, regiao.siglaEstado) &&
                Objects.equal(municipio, regiao.municipio);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, siglaRegiao, siglaEstado, municipio);
    }

    @Override
    public String toString() {
        return "Regiao{" +
                "id=" + id +
                ", siglaRegiao='" + siglaRegiao + '\'' +
                ", siglaEstado='" + siglaEstado + '\'' +
                ", municipio='" + municipio + '\'' +
                '}';
    }
}
