import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PokemonAPI
{
    public void obtener_api()
    {
        try {


            String nombrePokemon = "pikachu";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + nombrePokemon)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());

                System.out.println("ID: " + json.getInt("id"));
                System.out.println("Nombre: " + json.getString("name"));
                System.out.println("Peso: " + json.getInt("weight"));
                System.out.println("Altura: " + json.getInt("height"));

                System.out.println("Habilidades: ");
                json.getJSONArray("abilities").forEach(ability -> {
                    JSONObject abilityObj = (JSONObject) ability;
                    JSONObject abilityInfo = abilityObj.getJSONObject("ability");
                    System.out.println("- " + abilityInfo.getString("name"));
                });




                System.out.println("Estadisticas Base " + json.getString("name") + ": ");
                JSONArray stats = json.getJSONArray("stats");
                for (int i = 0; i < stats.length(); i++) {

                    JSONObject stat = stats.getJSONObject(i);
                    int base = stat.getInt("base_stat");
                    String name = stat.getJSONObject("stat").getString("name");
                    System.out.println("- " + name + " " + base);


                }
                System.out.println("Imagem : " + json.getJSONObject("sprites").getString("back_default") );



            }
            else
            {
                System.out.println("Error: " + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        PokemonAPI pokemonAPI = new PokemonAPI();
        pokemonAPI.obtener_api();
    }
}
