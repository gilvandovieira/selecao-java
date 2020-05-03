package gilvando.vieira.selecao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyString {

    @Id
    private Long id;

    private String s;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
