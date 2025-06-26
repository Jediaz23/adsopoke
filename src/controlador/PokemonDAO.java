package controlador;

import modelo.Pokemon;
import persistencia.ConexionBD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;


public class PokemonDAO
{

    private ConexionBD conexionBD = new ConexionBD();


    public void agregar(Pokemon pokemon)
    {
        Connection con = conexionBD.getConnection();

        String query = "INSERT INTO pokemon (nombre, tipo, peso, altura, descripcion, atk, def, foto) VALUES (?,?,?,?,?,?,?,?)";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,pokemon.getNombre());
            pst.setString(2,pokemon.getTipo());
            pst.setDouble(3,pokemon.getPeso());
            pst.setInt(4,pokemon.getAltura());
            pst.setString(5,pokemon.getDescripcion());
            pst.setInt(6,pokemon.getAtk());
            pst.setInt(7,pokemon.getDef());
            pst.setString(8,pokemon.getFoto());

            int resultado = pst.executeUpdate();

            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null,"Pokemon Agregado");
            }else
            {
                JOptionPane.showMessageDialog(null,"Error al Agregar Pokemon ");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }


    public void eliminar(int id_cliente)
    {
        Connection con = conexionBD.getConnection();

        String query = "DELETE FROM pokemon WHERE id_pokedex=?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1,id_cliente);

            int resultado = pst.executeUpdate();

            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null,"Pokemon eliminado ");
            }else
            {
                JOptionPane.showMessageDialog(null,"error al eliminar");
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }



    public void actualizar(Pokemon pokemon)
    {
        Connection con = conexionBD.getConnection();
        String query = "UPDATE pokemon set nombre = ?, tipo = ?, peso = ?, altura = ?, descripcion = ?,atk = ?, def = ?, foto = ? WHERE id_pokedex = ?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,pokemon.getNombre());
            pst.setString(2,pokemon.getTipo());
            pst.setInt(3,pokemon.getPeso());
            pst.setInt(4,pokemon.getAltura());
            pst.setString(5,pokemon.getDescripcion());
            pst.setInt(6,pokemon.getAtk());
            pst.setInt(7,pokemon.getDef());
            pst.setString(8,pokemon.getFoto());
            pst.setInt(9,pokemon.getId_pokedex());


            int resultado = pst.executeUpdate();

            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null,"pOKEMON actualizado");
            }else
            {
                JOptionPane.showMessageDialog(null,"error al modificar");
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
