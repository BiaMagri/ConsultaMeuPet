/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.bean;

import control.dao.VeterinarioDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import model.Veterinario;

/**
 *
 * @author bianca
 */
@ManagedBean
public class VeterinarioBean {

    private ArrayList<String> vets = new ArrayList<String>();
    private Veterinario selecionado;
    private int idSelecionado;
    private VeterinarioDAO veterinarioDAO;
    private String nomeLocalizado;

    public VeterinarioBean() {
        selecionado = new Veterinario();
        veterinarioDAO = new VeterinarioDAO();
    }
    
    public void inserir(Veterinario veterinario){
        veterinarioDAO.add(veterinario);
    }
    
    public void excluir(Veterinario veterinario){
        veterinarioDAO.remove(veterinario);
    }
    
    public ArrayList<String> listaVeterinarios(){
        return veterinarioDAO.readAll();
    }
    
    public void buscar(){
        selecionado =  veterinarioDAO.find(nomeLocalizado);
    }
    
    public void deletar(){
        veterinarioDAO.remove(selecionado);
    }

    public String getNomeLocalizado() {
        return nomeLocalizado;
    }
    
    public void atualizar(int id){
        selecionado.setId(id);
        veterinarioDAO.update(selecionado);
    }

    public void setNomeLocalizado(String nomeLocalizado) {
        this.nomeLocalizado = nomeLocalizado;
    }

    public Veterinario getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Veterinario selecionado) {
        this.selecionado = selecionado;
    }

    public int getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(int idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    
}
