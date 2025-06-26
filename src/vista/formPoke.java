package vista;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class formPoke {
    private JPanel main;
    private JTextField nombre1;
    private JTextField vida1;
    private JTextField ataque1;
    private JLabel foto1;
    private JTextField nombre2;
    private JTextField vida2;
    private JTextField ataque2;
    private JLabel foto2;
    private JButton jugarButton;
    private JButton seleccionarButton;
    private JButton selecionarButton;
    private JTextField name1;
    private JTextField name2;
    private JLabel ganadas1;
    private JLabel ganadas2;
    private JButton SALIRButton;

    private int vidaPokemon1 = 0;
    private int ataquePokemon1 = 0;
    private int vidaPokemon2 = 0;
    private int ataquePokemon2 = 0;
    int count1 = 0;
    int count2 = 0;

    private String jugador1Nombre = "Jugador 1";
    private String jugador2Nombre = "Jugador 2";

    private boolean turnoJugador1 = true;





    public formPoke() {
        obtener_api();
        obtener_api2();

        jugador1Nombre = JOptionPane.showInputDialog(null, "Ingrese nombre del Jugador 1:");
        if (jugador1Nombre == null || jugador1Nombre.trim().isEmpty()) {
            jugador1Nombre = "Jugador 1";
        }

        jugador2Nombre = JOptionPane.showInputDialog(null, "Ingrese nombre del Jugador 2:");
        if (jugador2Nombre == null || jugador2Nombre.trim().isEmpty()) {
            jugador2Nombre = "Jugador 2";
        }

        name1.setText(jugador1Nombre);
        name2.setText(jugador2Nombre);


        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugar();
            }
        });
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtener_api();

            }
        });
        selecionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtener_api2();

            }
        });
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void obtener_api()
    {
        try {


            Random rand = new Random();

            int idAleatorio = rand.nextInt(500) + 1;

            String url = "https://pokeapi.co/api/v2/pokemon/" + idAleatorio;



            // String nombrePokemon = "pikachu";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + idAleatorio)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());

                System.out.println("ID: " + json.getInt("id"));
                nombre1.setText(json.getString("name"));


                JSONArray stats = json.getJSONArray("stats");

                int hp = 0;
                int attack = 0;

                for (int i = 0; i < stats.length(); i++) {
                    JSONObject stat = stats.getJSONObject(i);
                    String statName = stat.getJSONObject("stat").getString("name");

                    if (statName.equals("hp")) {
                        hp = stat.getInt("base_stat");
                    } else if (statName.equals("attack")) {
                        attack = stat.getInt("base_stat");
                    }
                }

                vida1.setText(String.valueOf(hp));
                ataque1.setText(String.valueOf(attack));
                vidaPokemon1 = hp;
                ataquePokemon1 = attack;









                String imagenUrl = json.getJSONObject("sprites").getString("front_shiny");

                if (imagenUrl != null && !imagenUrl.isEmpty() && !imagenUrl.equals("null")) {
                    ImageIcon icon = new ImageIcon(new java.net.URL(imagenUrl));
                    Image imagenEscalada = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                    foto1.setIcon(new ImageIcon(imagenEscalada));
                    foto1.setText(""); // Quitar texto si había
                } else {
                    foto1.setText("Sin imagen");
                }





            }
            else
            {
                System.out.println("Error: " + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void obtener_api2()
    {
        try {


            Random rand = new Random();

            int idAleatorio2 = rand.nextInt(898) + 1;

            String url = "https://pokeapi.co/api/v2/pokemon/" + idAleatorio2;



            // String nombrePokemon = "pikachu";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + idAleatorio2)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());

                System.out.println("ID: " + json.getInt("id"));
                nombre2.setText(json.getString("name"));
                JSONArray stats = json.getJSONArray("stats");

                int hp = 0;
                int attack = 0;

                for (int i = 0; i < stats.length(); i++) {
                    JSONObject stat = stats.getJSONObject(i);
                    String statName = stat.getJSONObject("stat").getString("name");

                    if (statName.equals("hp")) {
                        hp = stat.getInt("base_stat");
                    } else if (statName.equals("attack")) {
                        attack = stat.getInt("base_stat");
                    }
                }

                vida2.setText(String.valueOf(hp));
                ataque2.setText(String.valueOf(attack));
                vidaPokemon2 = hp;
                ataquePokemon2 = attack;


                String imagenUrl = json.getJSONObject("sprites").getString("front_default");

                if (imagenUrl != null && !imagenUrl.isEmpty() && !imagenUrl.equals("null")) {
                    ImageIcon icon = new ImageIcon(new java.net.URL(imagenUrl));
                    Image imagenEscalada = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                    foto2.setIcon(new ImageIcon(imagenEscalada));
                    foto2.setText(""); // Quitar texto si había
                } else {
                    foto2.setText("Sin imagen");
                }





            }
            else
            {
                System.out.println("Error: " + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    public void jugar() {

        Random rand = new Random();

        int idAleatorio = rand.nextInt(2) + 1;

        if (idAleatorio == 1) {


            int vidaAntes = vidaPokemon2;
            vidaPokemon2 -= ataquePokemon1;
            if (vidaPokemon2 < 0) vidaPokemon2 = 0;

            JOptionPane.showMessageDialog(null,
                    "Turno de " + jugador1Nombre + "\n" +
                            "Ataca con " + ataquePokemon1 + " de daño\n" +
                            "Vida de " + jugador2Nombre + ": " + vidaAntes + " → " + vidaPokemon2);
        }else {
                int vidaAntes = vidaPokemon1;
                vidaPokemon1 -= ataquePokemon2;
                if (vidaPokemon1 < 0) vidaPokemon1 = 0;

                JOptionPane.showMessageDialog(null,
                        "Turno de " + jugador2Nombre + "\n" +
                                "Ataca con " + ataquePokemon2 + " de daño\n" +
                                "Vida de " + jugador1Nombre + ": " + vidaAntes + " → " + vidaPokemon1);
        }

            turnoJugador1 = !turnoJugador1;

            if (vidaPokemon1 == 0) {

                JOptionPane.showMessageDialog(null, "¡" + jugador2Nombre + " gana!");
                count2 ++;
                ganadas2.setText(String.valueOf(count2));

                int confirm = JOptionPane.showConfirmDialog(null, "¿Jugar otra ronda?", "Fin del juego", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    obtener_api();
                    obtener_api2();

                }
                return;
            } else if (vidaPokemon2 == 0) {
                JOptionPane.showMessageDialog(null, "¡" + jugador1Nombre + " gana!");
                count1 ++;
                ganadas1.setText(String.valueOf(count1));
                int confirm = JOptionPane.showConfirmDialog(null, "¿Jugar otra ronda?", "Fin del juego", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    obtener_api();
                    obtener_api2();
                }
                return;
            }

            vida1.setText(String.valueOf(vidaPokemon1));
            vida2.setText(String.valueOf(vidaPokemon2));
    }






    public static void main(String[] args) {
        JFrame frame = new JFrame("Pokemon ADSO");
        frame.setContentPane(new formPoke().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1006, 650);
        frame.setLocationRelativeTo(null); // 👉 Centrar la ventana
        frame.setResizable(false);
        frame.setVisible(true);
    }


}
