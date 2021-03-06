
package model;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author bianca
 */
@ManagedBean
public class Veterinario {
    
    private int id;
    private String nome;
    private String crmv;
    private String senha;

    public Veterinario() {

    }
    
    public Veterinario(int id, String nome, String crmv, String senha) {
        this.id = id;
        this.nome = nome;
        this.crmv = crmv;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
 
}
