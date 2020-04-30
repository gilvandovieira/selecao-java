package gilvando.vieira.selecao.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sel_revenda")
public class Revenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String CNPJ;

    @OneToMany(mappedBy = "revenda")
    List<Transacao> transacoes;

    @ManyToOne
    @JoinColumn(name = "regiao_id")
    private Regiao regiao;

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public Revenda() {
    }

    public Revenda(String nome, String CNPJ) {
        this.nome = nome;
        this.CNPJ = CNPJ;
    }

    public Revenda(Long id, String nome, String CNPJ) {
        this.id = id;
        this.nome = nome;
        this.CNPJ = CNPJ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revenda revenda = (Revenda) o;
        return Objects.equal(id, revenda.id) &&
                Objects.equal(nome, revenda.nome) &&
                Objects.equal(CNPJ, revenda.CNPJ);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, nome, CNPJ);
    }

    @Override
    public String toString() {
        return "Revenda{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", CNPJ='" + CNPJ + '\'' +
                '}';
    }
}
