package controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

import datos.BaseDatos;
import datos.Entrenador;
import datos.Producto;
import datos.Servicio;
import datos.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import load.LoadMenuEntView;

public class EditSerViewController implements Initializable{
    @FXML
    private Button BtnBack;

    @FXML
    private Button BtnBus;
    
    @FXML
    private Button BtnEli;

    @FXML
    private Button BtnGua;

    @FXML
    private TableColumn<Servicio, String> ColEnt;

    @FXML
    private TableColumn<Servicio, String> ColFec;

    @FXML
    private TableColumn<Servicio, Integer> ColHora;

    @FXML
    private TableColumn<Servicio, String> ColHorar;

    @FXML
    private TableColumn<Servicio, String> ColId;

    @FXML
    private TextField TxtBus;

    @FXML
    private BorderPane Vista;

    @FXML
    private ComboBox<String> comEnt;

    @FXML
    private ComboBox<String> comHorar;

    @FXML
    private StackPane productsContent;

    @FXML
    private TableView<Servicio> tableEdit;

    @FXML
    private Label txtError;

    @FXML
    private TextField txtFec;

    @FXML
    private TextField txtHora;

    @FXML
    private Label txtId;

	private BaseDatos dataprovider;
	
	private LoadMenuEntView lmvv;
	
	private ObservableList<Servicio> data = FXCollections.observableArrayList();
	
	

	/**
     * Método llamado cuando se hace clic en el botón de búsqueda.
     * Realiza una búsqueda en la base de datos de servicios que coincidan con el término de búsqueda ingresado.
     * Si se encuentran servicios, se muestran en la tabla; de lo contrario, se muestra un mensaje de error.
     *
     * @param event El evento de acción del botón.
     */
	@FXML
	public void btnBUsOnAction(ActionEvent event) {
		BaseDatos dataprovider = new BaseDatos(); 
	    LinkedList<Servicio> data2 = dataprovider.getSer2(TxtBus.getText());
	    if (!data2.isEmpty()) {
	    	this.ColId.setCellValueFactory(new PropertyValueFactory<Servicio, String>("id"));
	        this.ColFec.setCellValueFactory(new PropertyValueFactory<Servicio, String>("fecha"));
	        this.ColHorar.setCellValueFactory(new PropertyValueFactory<Servicio, String>("horario"));
	        this.ColHora.setCellValueFactory(new PropertyValueFactory<Servicio, Integer>("horas"));
	        this.ColEnt.setCellValueFactory(new PropertyValueFactory<Servicio, String>("nombre"));
	        ObservableList<Servicio> ventaObservableList = FXCollections.observableArrayList(data2);
	        tableEdit.setItems(ventaObservableList);
	        txtError.setText("");
	    } else {
	        txtError.setText("Error en la busqueda");
	    }
	}
	/**
     * Método para cargar los datos de los servicios desde la base de datos y establecerlos en la tabla.
     */
	public void cargar() {
	    LinkedList<Servicio> data1 = dataprovider.getSer(); 
	    data.setAll(data1);
	}
	/**
     * Método para limpiar los campos de texto y los ComboBox.
     */
	private void limpiarCampos(){
		txtId.setText("");
		txtHora.setText("");
        txtFec.clear();
        comEnt.setValue(null);
        comHorar.setValue(null);
    }
	/**
     * Método para llenar el ComboBox comEnt con los nombres de los entrenadores disponibles.
     */
	public void llenarComboBoxEnt() {
	    LinkedList<Entrenador> listaEntrenador = dataprovider.getEnt();
	    comEnt.getItems().clear();
	    for (Entrenador producto : listaEntrenador) {
	    	comEnt.getItems().add(producto.getNombre());
	    }
	}
	/**
     * Método llamado cuando se hace clic en el botón de eliminar.
     * Elimina el servicio seleccionado de la tabla y de la base de datos.
     *
     * @param event El evento de clic del mouse.
     */
	@FXML
	public void onEliminarClicked(MouseEvent event) {
	    Servicio servicioSeleccionado = tableEdit.getSelectionModel().getSelectedItem();
	    if (servicioSeleccionado != null) {
	        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Confirmación");
	        alert.setHeaderText("Eliminar venta");
	        alert.setContentText("¿Estás seguro de que deseas eliminar esta venta?");
	        
	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.isPresent() && result.get() == ButtonType.OK) {
	            boolean eliminado = dataprovider.eliminarSer(servicioSeleccionado.getId());
	            if (eliminado) {
	                tableEdit.getItems().remove(servicioSeleccionado);
	                limpiarCampos();
	            } else {
	                System.out.println("No se pudo eliminar la venta de la base de datos.");
	            }
	        } else {
	            System.out.println("Operación de eliminación cancelada.");
	        }
	    } else {
	        System.out.println("Por favor, selecciona una venta para eliminar.");
	    }
	}
	/**
     * Método llamado cuando se hace clic en el botón de retroceso.
     * Crea una nueva instancia de LoadMenuEntView para cargar la vista del menú de entrenador.
     *
     * @param event El evento de clic del mouse.
     */
	public void onBackClicked(MouseEvent event) {
		lmvv = new LoadMenuEntView((Stage)this.BtnBack.getScene().getWindow());
	}
	/**
	 * Método llamado cuando se selecciona un servicio en la tabla.
	 * Llena los campos de texto y los ComboBox con los detalles del servicio seleccionado.
	 *
	 * @param event El evento de selección del mouse.
	 */
	@FXML
    void onSelection(MouseEvent event) {
		tableEdit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
            	txtId.setText(String.valueOf(newValue.getId()));
            	txtHora.setText(String.valueOf(newValue.getHoras()));
            	txtFec.setText(String.valueOf(newValue.getFecha()));
            	comEnt.setValue(newValue.getNombre());
            	comHorar.setValue(newValue.getHorario());
            	llenarComboBoxEnt();
            }
        });
    }
	/**
	 * Método llamado cuando se hace clic en el botón de guardar.
	 * Actualiza la información del servicio seleccionado con los valores ingresados en los campos de texto y ComboBox.
	 *
	 * @param event El evento de clic del mouse.
	 */
	@FXML
	public void btnGuardarOnAction(MouseEvent event) {
		String nombreEntidad = comEnt.getValue();
        String horario = comHorar.getValue();
        int cantidad = Integer.parseInt(txtHora.getText());
        String fecha = txtFec.getText();
        String id = txtId.getText();
        String idEntidad = dataprovider.buscarIdSerPorNombre(nombreEntidad);
        dataprovider.actualizarVenta(idEntidad, horario, cantidad, fecha, id);
        limpiarCampos();
        cargar();
        llenarComboBoxEnt();
        
	}
	/**
	 * Método para inicializar el controlador.
	 * Establece los valores iniciales de los ComboBox y las celdas de la tabla.
	 * Llama al método cargar() para cargar los datos de los servicios desde la base de datos.
	 *
	 * @param arg0 URL de inicialización
	 * @param arg1 ResourceBundle de inicialización
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		comHorar.setItems(FXCollections.observableArrayList("Mañana","Tarde","Noche"));
		this.dataprovider = new BaseDatos();
		this.ColHora.setCellValueFactory(new PropertyValueFactory<Servicio, Integer>("horas"));
		this.ColId.setCellValueFactory(new PropertyValueFactory<Servicio, String>("id"));
		this.ColHorar.setCellValueFactory(new PropertyValueFactory<Servicio, String>("horario"));
		this.ColEnt.setCellValueFactory(new PropertyValueFactory<Servicio, String>("nombre"));
		this.ColFec.setCellValueFactory(new PropertyValueFactory<Servicio, String>("fecha"));
		tableEdit.setItems(this.data);
		cargar();
		llenarComboBoxEnt();
	}
}
