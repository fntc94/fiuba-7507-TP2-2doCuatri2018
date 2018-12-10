package vista.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import modelo.IAtacante;
import modelo.IPosicionable;
import modelo.posicion.Posicion;
import modelo.unidades.Espadachin;
import vista.controles.EspadachinBotonera;
import vista.controles.MapaControl;

import java.net.URL;
import java.util.ResourceBundle;

public class EspadachinController implements IPosicionableController, Initializable {

    @FXML
    private GridPane root;
    @FXML private ImageView imageView;


    private final EspadachinBotonera botonera;
    private Espadachin espadachin;
    private String color;
    private MapaControl mapaControl;
    private IJuegoController juegoController;
    private IAtacante atacante;

    private String estado = "seleccionable";

    public EspadachinController(Espadachin espadachin, String color, MapaControl mapaControl, IJuegoController juegoController){
        this.espadachin = espadachin;
        this.color = color;
        this.mapaControl = mapaControl;
        this.juegoController = juegoController;


        this.botonera = new EspadachinBotonera(espadachin, mapaControl);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.root.getStylesheets().add(this.getClass().getResource("/vista/css/Espadachin.css").toExternalForm());
        this.imageView.getStyleClass().add(color);
    }

    @Override
    public IPosicionable getPosicionable() {
        return this.espadachin;
    }

    @Override
    public Posicion getPosicion() {
        return espadachin.getPosicion();
    }

    @Override
    public String getColor() {
        return this.color;
    }

    public void handleClick(MouseEvent mouseEvent) {
        if(this.estado.equals("seleccionable")){
            this.juegoController.setBotonera(botonera);
        }

        if(this.estado.equals("ataquePotencial")){

            try {
                this.atacante.atacar(this.espadachin);
                new Alert(Alert.AlertType.INFORMATION, "Ataque concretado").show();
                this.playSound();
                this.botonera.actualizarUI();
            }
            catch (Exception e){
                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();

            }

            finally {
                this.mapaControl.estadoSeleccionable();
            }
        }
    }

    @Override
    public void estadoAtaquePotencial(IAtacante atacante) {
        this.estado = "ataquePotencial";
        this.atacante = atacante;
    }

    public void estadoSeleccionable(){
        this.estado = "seleccionable";
    }

    private void playSound(){

        try
        {

            String file = "/vista/sonidos/recibir_ataque_asedio.wav";
            URL path = getClass().getResource(file);
            AudioClip ac = new AudioClip(path.toString());
            ac.play();

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}