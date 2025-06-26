package vista;

import controlador.PokemonDAO;
import modelo.Pokemon;
import persistencia.ConexionBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FormPokemon {
    private JPanel main;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton pdfButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTable table1;
    private JTextField textField9;
    private JTextArea textArea1;
    int filas = 0;

    PokemonDAO pokemonDAO = new PokemonDAO();

    public FormPokemon() {
        obtener_datos();
        textField1.setEnabled(false);
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = textField2.getText();
                String tipo = textField3.getText();
                double peso = Double.parseDouble(textField4.getText()); // Changed to double
                int altura = Integer.parseInt(textField6.getText());
                String descripcion = textArea1.getText();
                int apk = Integer.parseInt(textField7.getText());
                int def = Integer.parseInt(textField8.getText());
                String foto = textField9.getText(); // Corrected to textField9 for foto

                Pokemon pokemon = new Pokemon(0,nombre, tipo, peso,altura,descripcion,apk,def,foto);
                pokemonDAO.agregar(pokemon);
                obtener_datos();
                clear();

            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField2.getText();
                String tipo = textField3.getText();
                double peso = Double.parseDouble(textField4.getText()); // Changed to double
                int altura = Integer.parseInt(textField6.getText());
                String descripcion = textArea1.getText();
                int apk = Integer.parseInt(textField7.getText());
                int def = Integer.parseInt(textField8.getText());
                String foto = textField9.getText();
                int id_pokedex = Integer.parseInt(textField1.getText());

                Pokemon pokemon = new Pokemon(id_pokedex,nombre, tipo, peso,altura,descripcion,apk,def,foto);
                pokemonDAO.actualizar(pokemon);
                obtener_datos();
                clear();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_pokedex = Integer.parseInt(textField1.getText());
                pokemonDAO.eliminar(id_pokedex);
                obtener_datos();
                clear();
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectFila = table1.getSelectedRow();

                if (selectFila >= 0) {
                    textField1.setText((String) table1.getValueAt(selectFila, 0));
                    textField2.setText((String) table1.getValueAt(selectFila, 1));
                    textField3.setText((String) table1.getValueAt(selectFila, 2));
                    textField4.setText((String) table1.getValueAt(selectFila, 3));
                    textField6.setText((String) table1.getValueAt(selectFila, 4));
                    textArea1.setText((String) table1.getValueAt(selectFila, 5));
                    textField7.setText((String) table1.getValueAt(selectFila, 6));
                    textField8.setText((String) table1.getValueAt(selectFila, 7));
                    textField9.setText((String) table1.getValueAt(selectFila, 8));

                    filas = selectFila;
                }
            }
        });
        pdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

    public void clear() {
        textField1.setText("");
        textField6.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textArea1.setText("");
        textField7.setText("");
        textField8.setText("");
        textField9.setText("");

    }

   ConexionBD conexionBD = new ConexionBD();


    /**
     * este es el metodo donde obtenemos los datos de la bd y los ponemos en la tabla
     */
    public void obtener_datos() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Pokemon");
        model.addColumn("Nombre");
        model.addColumn("Tipo");
        model.addColumn("Peso");
        model.addColumn("Altura");
        model.addColumn("Descripcion");
        model.addColumn("ATK");
        model.addColumn("DEF");
        model.addColumn("Foto");

        table1.setModel(model);
        String[] dato = new String[9];
        Connection con = conexionBD.getConnection();

        try {
            Statement start = con.createStatement();
            String query = "SELECT * FROM pokemon";
            ResultSet rs = start.executeQuery(query);

            while (rs.next()) {
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);
                dato[5] = rs.getString(6);
                dato[6] = rs.getString(7);
                dato[7] = rs.getString(8);
                dato[8] = rs.getString(9);
                model.addRow(dato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FormPokemon");
        frame.setContentPane(new FormPokemon().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1006, 600);
        frame.setResizable(false);
    }

}
