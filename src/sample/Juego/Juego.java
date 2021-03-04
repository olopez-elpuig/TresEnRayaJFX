package sample.Juego;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    List<String> botonesPulsados = new ArrayList<>();
    private Turnos tablero[][] = new Turnos[3][3];
    private int elegirModo;
    private Turnos turno = Turnos.X;
    private Button buttonClick;
    List<Button> buttons = new ArrayList<>();
    private JuegoController gameController;
    private String[] posicionesGanadoras;

    private enum Turnos {
        X, O;
    }

    public Juego(int modoJuego, JuegoController gameController) {
        this.elegirModo = modoJuego;
        this.gameController = gameController;
    }

    void clickButton(Button button) {
        this.buttonClick = button;
        if (elegirModo == 1) {
            playerVsPc();
        } else if (elegirModo == 2) {
            pcVsPc();
        } else if (elegirModo == 3) {
            jugarVSjugador();
        }

        if (botonesPulsados.size() >= buttons.size() && posicionesGanadoras == null) {
            gameController.reiniciarJuego();
            if (elegirModo != 2) {
                JuegoController.playerList.get(JuegoController.playerList.size() - 1).addResultado(1);
            }
        }
    }

    private void playerVsPc() {
        if (turno.equals(Turnos.X)) {
            jugarVSjugador();
            if (!comprobarResultado()) {
                playerVsPc();
            }
        }
        else {
            if (botonesPulsados.size() < buttons.size()) {
                String idButton = null;
                do {
                    int randomX = (int) (Math.random() * 3 + 0);
                    int randomXX = (int) (Math.random() * 3 + 0);
                    idButton = "B" + randomX + randomXX;
                } while (botonClicado(idButton));
                for (Button button : buttons) {
                    if (button.getId().equals(idButton)) {
                        button.setText(String.valueOf(turno));
                        break;
                    }
                }
                if (comprobarResultado()) {
                    gameController.reiniciarJuego();
                    mostrarLineaGanadora(posicionesGanadoras);
                }
                else {
                    cambiarTurno();
                }
            }
        }
    }

    private void pcVsPc() {
        if (botonesPulsados.size() < buttons.size()) {
            String idButton = null;
            do {
                int randomX = (int) (Math.random() * 3 + 0);
                int randomXX = (int) (Math.random() * 3 + 0);
                idButton = "B" + randomX + randomXX;
            } while (botonClicado(idButton));
            for (Button button : buttons) {
                if (button.getId().equals(idButton)) {
                    button.setText(String.valueOf(turno));
                    break;
                }
            }
            if (!comprobarResultado()) {
                cambiarTurno();
                pcVsPc();
            }
            else {
                gameController.reiniciarJuego();
                mostrarLineaGanadora(posicionesGanadoras);
            }
        }
    }

    private void jugarVSjugador() {
        if (!botonClicado(buttonClick.getId())) {
            buttonClick.setText(String.valueOf(turno));
        }
        if (comprobarResultado()) {
            gameController.reiniciarJuego();
            mostrarLineaGanadora(posicionesGanadoras);
        }
        else {
            cambiarTurno();
        }
    }

    boolean botonClicado(String id) {
        boolean pulsado = false;
        for (String boton : botonesPulsados) {
            if (boton.equals(id)) {
                pulsado = true;
                break;
            }
        }
        if (!pulsado) {
            botonesPulsados.add(id);
            id = id.replace("B", "");
            tablero[Integer.parseInt(String.valueOf(id.charAt(0)))][Integer.parseInt(String.valueOf(id.charAt(1)))] = turno;
        }
        return pulsado;
    }

    boolean comprobarBotonPulsado(Button buttonPulsado) {
        boolean pulsado = false;
        for (String idButton : botonesPulsados) {
            if (idButton.equals(buttonPulsado.getId())) {
                pulsado = true;
                break;
            }
        }
        return pulsado;
    }

    boolean comprobarResultado() {
        boolean ganador = false;

        if ( (tablero[0][0] != (null)) && (tablero[0][0].equals(tablero[0][1]) && tablero[0][1].equals(tablero[0][2]))) {
            posicionesGanadoras = new String[]{"00", "01", "02"};
            ganador = true;
        }
        else if ( (tablero[1][0] != (null)) && (tablero[1][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[1][2]) )) {
            posicionesGanadoras = new String[]{"10", "11", "12"};
            ganador = true;
        }
        else if ( (tablero[2][0] != (null)) && (tablero[2][0].equals(tablero[2][1]) && tablero[2][1].equals(tablero[2][2]) )) {
            posicionesGanadoras = new String[]{"20", "21", "22"};
            ganador = true;
        }

        else if ( (tablero[0][0] != (null)) && (tablero[0][0].equals(tablero[1][0]) && tablero[1][0].equals(tablero[2][0]) )) {
            posicionesGanadoras = new String[]{"00", "10", "20"};
            ganador = true;
        }
        else if ( (tablero[0][1] != (null)) && (tablero[0][1].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][1]) )) {
            posicionesGanadoras = new String[]{"01", "11", "21"};
            ganador = true;
        }
        else if ( (tablero[0][2] != (null)) && (tablero[0][2].equals(tablero[1][2]) && tablero[1][2].equals(tablero[2][2]) )) {
            posicionesGanadoras = new String[]{"02", "12", "22"};
            ganador = true;
        }
        else if ( (tablero[0][0] != (null)) && (tablero[0][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][2]) )) {
            posicionesGanadoras = new String[]{"00", "11", "22"};
            ganador = true;
        }
        else if ( (tablero[0][2] != (null)) && (tablero[0][2].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][0]) )) {
            posicionesGanadoras = new String[]{"02", "11", "20"};
            ganador = true;
        }
        else {
            posicionesGanadoras = null;
        }

        return ganador;
    }

    void cambiarTurno() {
        if (turno.equals(Turnos.X)) {
            turno = Turnos.O;
        } else {
            turno = Turnos.X;
        }
    }


    private void mostrarLineaGanadora(String[] idButtons) {
        for (String id : idButtons) {
            for (Button button : buttons) {
                if (button.getId().equals("B" + id)) {
                    button.getStyleClass().add("buttonsGanadores");
                }
            }
        }
    }

    void deshabilitarBotones() {
        for (Button button : buttons) {
            button.setDisable(true);
            button.getStyleClass().removeAll("buttonsGanadores");
        }
    }

    void habilitarBotones() {
        for (Button button : buttons) {
            button.setDisable(false);
        }
    }
}
