package sample.Partida;

public class Partida {

    private static String ia;
    private static String modo;
    private static int vjugador1;
    private static int vjugador2;
    private static int djugador1;
    private static int djugador2;
    private int turno;
    private char[] cuadricula = new char[9];
    private static boolean  estado = false;
    private String value;
    private String color;


    public String getColor() { return color; }

    public String getValue() { return value; }

    public char[] getCuadricula() { return cuadricula; }

    public void setModo(String modo) {this.modo = modo;}

    public String getModo() {return modo;}

    public String getIa() { return ia; }

    public void setIa(String dificultad) { ia=dificultad;}

    public int getVjugador1() { return vjugador1; }

    public int getVjugador2() { return vjugador2; }

    public int getDjugador1() { return djugador1; }

    public int getDjugador2() { return djugador2; }

    public boolean getEstado() { return estado; }

    public void setTurno(int turno) { this.turno = turno;}

    public int getTurno() {return turno;}

    public int Eleccion_Primer_Turno() {
        int eleccion = (int) Math.random()*10;
        return eleccion;
    }

    public boolean ComprobarVictoria(char value) {
        boolean victoria = false;
        if(cuadricula[0] == value && cuadricula[1] == value && cuadricula[2] == value) {
            victoria = true;
        }else if(cuadricula[3] == value && cuadricula[4] == value && cuadricula[5] == value) {
            victoria = true;
        }else if(cuadricula[6] == value && cuadricula[7] == value && cuadricula[8] == value) {
            victoria = true;
        }else if(cuadricula[0] == value && cuadricula[3] == value && cuadricula[6] == value) {
            victoria = true;
        }else if(cuadricula[1] == value && cuadricula[4] == value && cuadricula[7] == value) {
            victoria = true;
        }else if(cuadricula[2] == value && cuadricula[5] == value && cuadricula[8] == value) {
            victoria = true;
        }else if(cuadricula[0] == value && cuadricula[4] == value && cuadricula[8] == value) {
            victoria = true;
        }else if(cuadricula[2] == value && cuadricula[4] == value && cuadricula[6] == value) {
            victoria = true;
        }
        return victoria;
    }

    public void EmpezarPartida() {
        estado = true;
        if(Eleccion_Primer_Turno()<5) {
            turno = 0;
        }else {
            turno =1;
        }
    }

    public void FinalizarPartida() {
        estado = false;
        VaciarCuadricula();
    }

    public static void AbandonarPartida() {
        djugador1 = djugador1 +1;
        vjugador2 = vjugador2+1;
        estado = false;
    }

    //Método que comprueba si a cuadrícula está llena.
    public boolean CuadriculaLLena() {
        boolean resultado = true;
        for(int i=0;i<cuadricula.length;i++) {
            if(cuadricula[i] != 'X' && cuadricula[i] != 'O') {
                resultado = false;
                break;
            }
        }
        return resultado;
    }

    public void PropiedadesTurno() {
        if(turno==0) {
            value = "X";
            color = "blue";
        }else {
            value = "O";
            color = "red";
        }
    }

    public void setPosCuadricula(int index,char value) {

        cuadricula[index] = value;
    }

    public void VaciarCuadricula() {

        for (int i=0;i<cuadricula.length;i++) {
            cuadricula[i] = ' ';
        }
    }

    public boolean EstadoCuadricula(char value) {
        boolean result;
        if(value != 'X' && value != 'O') {
            result = true;
        }else {
            result = false;
        }
        return result;
    }

}

