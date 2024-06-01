package controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import datos.BaseDatos;
import datos.Entrenador;
import datos.Producto;
import datos.Servicio;
import datos.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import load.LoadMenuEntView;

public class AddSerViewController implements Initializable{

	@FXML
    private ComboBox<String> BoxEnt;
	@FXML
    private ComboBox<String> BoxTip;
    @FXML
    private TextField BoxHora;
    @FXML
    private ComboBox<String> Boxhorar;
    @FXML
    private Button BtnBack;
    @FXML
    private Button BtnCrear;
    @FXML
    private Label TxtError;
    @FXML
    private TextField TxtFecha;
    @FXML
    private AnchorPane Vista;
    @FXML
    private TextField txtId;

	private LoadMenuEntView lmvv;
	
	private BaseDatos dataprovider = new BaseDatos();
	/**
     * Método llamado cuando se hace clic en el botón de retroceso.
     * Crea una nueva instancia de LoadMenuEntView para cargar la vista del menú de entrenador.
     *
     * @param event El evento de clic del mouse.
     */
	@FXML
	public void onBackClicked(MouseEvent event) {
		lmvv = new LoadMenuEntView((Stage)this.BtnBack.getScene().getWindow());
	}
	
	private ObservableList<Entrenador> data1  = FXCollections.observableArrayList();
	/**
     * Método para llenar el ComboBox con los nombres de los entrenadores.
     */
	public void llenarComboBoxEnt() {
		Boxhorar.setItems(FXCollections.observableArrayList("6-12 mañana","1-6 tarde","6-12 noche"));
	    LinkedList<Entrenador> listaEntrenador = dataprovider.getEnt();
	    BoxEnt.getItems().clear();
	    for (Entrenador producto : listaEntrenador) {
	    	BoxEnt.getItems().add(producto.getNombre());
	    }
	}
	/**
     * Método para obtener la lista de entrenadores desde la base de datos.
     */
	public void getEnt() {
	    LinkedList<Entrenador> data2 = dataprovider.getEnt(); 
	    data1.setAll(data2);
	}

	/**
     * Método llamado cuando se hace clic en el botón de crear.
     * Obtiene los datos del nuevo servicio de los campos de entrada y llama al método guardarSer del proveedor de datos.
     * Muestra un mensaje de éxito o error en la etiqueta TxtError.
     *
     * @param event El evento de clic del mouse.
     */
	public void onCrearClicked(MouseEvent event) {
	    String nombreEnt = BoxEnt.getValue();
	    String horario = Boxhorar.getValue();
	    int hora = Integer.parseInt(BoxHora.getText());
	    String fecha = TxtFecha.getText();
	    String id = txtId.getText();
	    String nombreServicio = BoxTip.getValue();
        String tipId = dataprovider.buscarIdTiposervicioPorNombre(nombreServicio);
	    String idEnt = dataprovider.buscarIdEntPorNombre(nombreEnt);
	    boolean exito = dataprovider.guardarSer(idEnt, horario, hora, fecha, id, tipId);
	    if (exito) {
	    	TxtError.setText("Completado");
	    } else {
	        TxtError.setText("Error en guardar");
	    }
	}
	
	public void llenarComboBoxSer() {
	    LinkedList<Servicio> listaServicio = dataprovider.getSer();
	    BoxTip.getItems().clear();
	    for (Servicio servicio : listaServicio) {
	    	BoxTip.getItems().add(servicio.getNombreTipoServicio());
	    }
	}	
	/**
     * Método para inicializar el controlador.
     * Llama a los métodos getEnt() y llenarComboBoxEnt() para obtener la lista de entrenadores y llenar el ComboBox.
     *
     * @param arg0 URL
     * @param arg1 ResourceBundle
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getEnt();
		llenarComboBoxEnt();
		llenarComboBoxSer();
	}


}
