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
@ManagedBean
public class VeterinarioDAO {

    private static Connection connection = null;
    private static Statement statement = null;
    private ResultSet resultSet = null;
    private ArrayList<Veterinario> vets = new ArrayList<Veterinario>();

    public ArrayList<Veterinario> getVets() {
        return vets;
    }

    public void setVets(ArrayList<Veterinario> vets) {
        this.vets = vets;
    }

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

    public void update(Veterinario veterinario, int idAntigo) {
        try {
            //String query = "UPDATE pedal SET idPedal=" + veterinario.getNome()) + ", nomePedal='" + pedal.getNomePedal() + "', marcaPedal='" + pedal.getMarcaPedal() + "', Categoria_idCategoria= (SELECT idCategoria FROM categoria WHERE nomeCategoria LIKE '" + pedal.getCategoria() + "') WHERE idPedal=" + idAntigo + ";";
            //this.statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Erro2: " + e.getMessage());
        }
    }

    public boolean remove(Veterinario veterinario) {
        try {
            //String query = "DELETE FROM pedal WHERE idPedal = " + veterinario.get() + ";";
            //this.statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Erro4: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Veterinario> read() {
		
                try {
			String query = "SELECT nome, crmv FROM veterinario ORDER BY nome;";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while(this.resultSet.next()) {
				Veterinario veterinario = new Veterinario(this.resultSet.getString("nome"), this.resultSet.getString("crmv"));
				vets.add(veterinario);
			}
		} catch(Exception e) {
			System.out.println("Erro1: " + e.getMessage());
		}
		return vets;
	}

    public void desconectar() {
        try {
            this.connection.close();
        } catch (Exception e) {
            System.out.println("Erro5: " + e.getMessage());
        }
    }

}
