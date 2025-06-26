package modelo;

public class Pokemon
{
    int id_pokedex;
    String nombre;
    String tipo;
    double peso; // Changed to double
    int altura;
    String descripcion;
    int atk;
    int def;
    String foto;


    public Pokemon(int id_pokedex, String nombre, String tipo, double peso, int altura, String descripcion, int atk, int def, String foto) { // Changed peso to double
        this.id_pokedex = id_pokedex;
        this.nombre = nombre;
        this.tipo = tipo;
        this.peso = peso;
        this.altura = altura;
        this.descripcion = descripcion;
        this.atk = atk;
        this.def = def;
        this.foto = foto;
    }

    public int getId_pokedex() {
        return id_pokedex;
    }

    public void setId_pokedex(int id_pokedex) {
        this.id_pokedex = id_pokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPeso() { // Changed to double
        return peso;
    }

    public void setPeso(double peso) { // Changed to double
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
