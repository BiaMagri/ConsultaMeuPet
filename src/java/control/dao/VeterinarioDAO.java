package control.dao;

import com.sun.corba.se.spi.presentation.rmi.StubAdapter;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import model.Veterinario;

/**
 *
 * @author bianca
 */

public class VeterinarioDAO {

    private static Connection connection = null;
    private static Statement statement = null;
    private ResultSet resultSet = null;
    private ArrayList<String> vets = new ArrayList<String>();

    public void conectar() {
        String servidor = "jdbc:mysql://127.0.0.1:3306/veterinarioschema";
        String usuario = "root";
        String senha = "rootroot";
        String driver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(servidor, usuario, senha);
            this.statement = this.connection.createStatement();
        } catch (Exception e) {
            System.out.println("Erro0: " + e.getMessage());
        }
    }

    public boolean isConnected() {
        if (this.connection != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean add(Veterinario veterinario) {
        this.conectar();

        try {
            String query = "INSERT INTO veterinario (nome, crmv, senha) "
                    + "VALUES ('" + veterinario.getNome() + "', '"
                    + veterinario.getCrmv() + "', '"
                    + veterinario.getSenha() + "')";

            this.statement.executeUpdate(query);

        } catch (Exception e) {
            System.out.println("Erro2: " + e.getMessage());
        }
        this.desconectar();
        return isConnected();
    }

    public boolean update(Veterinario veterinario) {
        this.conectar();
        try {
            String query = "UPDATE veterinario SET nome= '" + veterinario.getNome() 
                    + "', crmv='" + veterinario.getCrmv() 
                    + "', senha='" + veterinario.getSenha()
                    + "' WHERE idVeterinario = " + veterinario.getId();
            this.statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Erro2: " + e.getMessage());
        }
        this.desconectar();
        return isConnected();
    }

    public void remove(Veterinario veterinario) {
        this.conectar();
        String query = null;
        try {
            query = "DELETE FROM veterinario WHERE idVeterinario = " + veterinario.getId() + ";";
            this.statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Erro4: " + e.getMessage());
        }
        this.desconectar();
        //return query;
    }

    public ArrayList<String> readAll() {
        this.conectar();
        vets.clear();
        try {
            String query = "SELECT nome, crmv FROM veterinario ORDER BY nome;";
            this.resultSet = this.statement.executeQuery(query);
            this.statement = this.connection.createStatement();
            while (this.resultSet.next()) {
                //Veterinario veterinario = new Veterinario(this.resultSet.getString("nome"), this.resultSet.getString("crmv"));
                vets.add(this.resultSet.getString("nome"));
            }
        } catch (Exception e) {
            System.out.println("Erro1: " + e.getMessage());
        }
        this.desconectar();
        return vets;
    }

    public Veterinario find(String nomeLocalizado) {
        this.conectar();
        Veterinario veterinario = null;
        try {
            String query = "SELECT idVeterinario, nome, crmv, senha FROM veterinario WHERE nome like '" + nomeLocalizado + "'ORDER BY nome;";
            this.resultSet = this.statement.executeQuery(query);
            this.statement = this.connection.createStatement();
            while (this.resultSet.next()) {
                veterinario = new Veterinario(this.resultSet.getInt("idVeterinario"), this.resultSet.getString("nome"), this.resultSet.getString("crmv"), this.resultSet.getString("senha"));
            }
        } catch (Exception e) {
            System.out.println("Erro1: " + e.getMessage());
        }
        this.desconectar();
        return veterinario;
    }

    public void desconectar() {
        try {
            this.connection.close();
        } catch (Exception e) {
            System.out.println("Erro5: " + e.getMessage());
        }
    }

    public ArrayList<String> getVets() {
        return vets;
    }

    public void setVets(ArrayList<String> vets) {
        this.vets = vets;
    }

}
