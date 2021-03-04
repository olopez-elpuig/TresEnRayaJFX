package sample.Jugador;

public class Player {
    private String nombre;
    private int[] resultados = {0,0,0};

    public Player(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addResultado(int posicion) {
        resultados[posicion] += 1;
    }

    public int getResultados(int pos) {
        return resultados[pos];
    }
}

