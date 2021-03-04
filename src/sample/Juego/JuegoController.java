package sample.Juego;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Jugador.Player;
import sample.Controller.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class JuegoController implements Initializable {
    @FXML
    private Label labelJugador;
    @FXML
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;
    @FXML
    private RadioButton radioButton3;
    @FXML
    private GridPane buttonsPanel;
    @FXML
    private Button startButton;
    @FXML
    private ToggleGroup escoger;

    Juego juego;
    static public List<Player> playerList = new ArrayList<>();
    public static Player userPlayer;
    private final String[] textRadioButton = {"vs Ordenador", "Ordenador vs Ordenador", "vs Jugador 2"};
    private RadioButton[] radioButtons;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerList.add(userPlayer);
        labelJugador.setText(labelJugador.getText() + userPlayer.getNombre());
        textRadioButton[0] = userPlayer.getNombre() + " " + textRadioButton[0];
        textRadioButton[2] = userPlayer.getNombre() + " " + textRadioButton[2];
        radioButtons = new RadioButton[] {radioButton1, radioButton2, radioButton3};
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i].setText(textRadioButton[i]);
        }
    }


    public void start(ActionEvent actionEvent) {
        RadioButton selectedRadioButton = (RadioButton) escoger.getSelectedToggle();

        if (selectedRadioButton != null) {
            String id = selectedRadioButton.getId();
            int elegirModo = 0;
            for (int i = 0; i < radioButtons.length; i++) {
                if (id.equals(radioButtons[i].getId())) {
                    elegirModo = i + 1;
                }
            }

            juego = new Juego(elegirModo, this);
            for (Node button : buttonsPanel.getChildren()) {
                juego.buttons.add((Button) button);
            }
            juego.habilitarBotones();
            restartButtons();
            startButton.setVisible(false);
            if (elegirModo == 2) {
                juego.clickButton(null);
            }
        }
    }

    public void clickButton(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (!juego.comprobarBotonPulsado(button)) {
            juego.clickButton(button);
        }
    }

    private void restartButtons() {
        for (Button button : juego.buttons) {
            button.setText(null);
            button.getStyleClass().removeAll("buttonsGanadores");
        }
    }

    void reiniciarJuego() {
        juego.deshabilitarBotones();
        startButton.setVisible(true);
    }

    public void cambiarUsuario(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Menu/sample.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Controller.stage.setScene(scene);
        Controller.stage.setTitle("Tres en raya");
        Controller.stage.setResizable(false);
    }

    public void exit(ActionEvent actionEvent) {
        new Controller().salir(actionEvent);
    }

}

