package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

import datos.BaseDatos;
import datos.LogIn;
import datos.Producto;
import datos.Session;
import datos.Vendedor;
import datos.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import load.LoadMenuVentaView;
import load.LoadUsuarioView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EditVentasViewController implements Initializable {
	
    @FXML
    private BorderPane Vista;
	
	@FXML
    private TableColumn<Venta, Integer> ColCan;

    @FXML
    private TableColumn<Venta, String> ColId;

    @FXML
    private TableColumn<Venta, String> ColFec;

    @FXML
    private TableColumn<Venta, String> ColNom;

    @FXML
    private TableColumn<Venta, Integer> ColPre;

    @FXML
    private TableColumn<Venta, String> ColVen;
    
    @FXML
    private TableView<Venta> tableEdit;
    
    @FXML
    private TextField TxtBus;

    @FXML
    private TextField TxtCan;

    @FXML
    private TextField TxtFec;

    @FXML
    private Label TxtId;

    @FXML
    private ComboBox<String> comnom;

    @FXML
    private ComboBox<String> comven;

    @FXML
    private StackPane productsContent;

	private BaseDatos dataprovider;
	
	private LoadMenuVentaView lmvv;
	
	@FXML
    private Label txtError;
	
	@FXML
    private TextField TxtBack;
	
	@FXML
    private Button BtnBack;

    @FXML
    private Button BtnBus;

    @FXML
    private Button BtnEli;

    @FXML
    private Button BtnGua;
	
	private ObservableList<Venta> data = FXCollections.observableArrayList();
	/**
	 * Limpia los campos de entrada de datos.
	 */
	private void limpiarCampos(){
        TxtCan.clear();
        TxtId.setText("");
        TxtFec.clear();
        comnom.setValue(null);
        comven.setValue(null);
    }
	/**
	 * Método llamado cuando se hace clic en el botón de eliminación de una venta.
	 * Elimina la venta seleccionada de la base de datos y de la tabla de ventas.
	 *
	 * @param event El evento de clic del mouse.
	 */
	@FXML
	public void onEliminarClicked(MouseEvent event) {
	    Venta ventaSeleccionada = tableEdit.getSelectionModel().getSelectedItem();
	    if (ventaSeleccionada != null) {
	        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Confirmación");
	        alert.setHeaderText("Eliminar venta");
	        alert.setContentText("¿Estás seguro de que deseas eliminar esta venta?");
	        
	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.isPresent() && result.get() == ButtonType.OK) {
	            boolean eliminado = dataprovider.eliminarVenta(ventaSeleccionada.getId());
	            if (eliminado) {
	                tableEdit.getItems().remove(ventaSeleccionada);
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
	 * Crea una nueva instancia de LoadMenuVentaView para cargar la vista del menú de venta.
	 *
	 * @param event El evento de clic del mouse.
	 */
	public void onBackClicked(MouseEvent event) {
		lmvv = new LoadMenuVentaView((Stage)this.BtnBack.getScene().getWindow());
	}
	/**
	 * Carga las ventas desde la base de datos y las muestra en la tabla al inicializar el controlador.
	 */
	public void cargar() {
	    LinkedList<Venta> data1 = dataprovider.getVenta(); 
	    data.setAll(data1);
	}
	/**
	 * Método llamado cuando se hace clic en el botón de búsqueda.
	 * Busca ventas según el término ingresado en el campo de búsqueda y las muestra en la tabla.
	 *
	 * @param event El evento de clic del mouse.
	 */
	@FXML
	public void btnBUsOnAction(MouseEvent event) {
	    BaseDatos dataprovider = new BaseDatos(); 
	    LinkedList<Venta> data2 = dataprovider.getVenta2(TxtBus.getText());
	    if (!data2.isEmpty()) {
	        this.ColCan.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("cantidad"));
	        this.ColId.setCellValueFactory(new PropertyValueFactory<Venta, String>("id"));
	        this.ColFec.setCellValueFactory(new PropertyValueFactory<Venta, String>("fecha"));
	        this.ColNom.setCellValueFactory(new PropertyValueFactory<Venta, String>("nombre"));
	        this.ColPre.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("precio"));
	        this.ColVen.setCellValueFactory(new PropertyValueFactory<Venta, String>("vendedor"));
	        ObservableList<Venta> ventaObservableList = FXCollections.observableArrayList(data2);
	        tableEdit.setItems(ventaObservableList);
	        txtError.setText("");
	    } else {
	        txtError.setText("Error en la busqueda");
	    }
	}
	/**
	 * Método llamado cuando se selecciona una venta en la tabla.
	 * Carga los datos de la venta seleccionada en los campos de entrada de datos.
	 *
	 * @param event El evento de clic del mouse.
	 */
	@FXML
    void onSelection(MouseEvent event) {
		tableEdit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
            	TxtId.setText(String.valueOf(newValue.getId()));
            	TxtCan.setText(String.valueOf(newValue.getCantidad()));
            	TxtFec.setText(String.valueOf(newValue.getFecha()));
            	comnom.setValue(newValue.getNombre());
                comven.setValue(newValue.getVendedor());
            }
        });
    }
	/**
	 * Método de inicialización del controlador.
	 * Configura las celdas de la tabla y carga las ventas al iniciar la vista.
	 *
	 * @param arg0 URL de inicialización
	 * @param arg1 ResourceBundle de inicialización
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.dataprovider = new BaseDatos();
		this.ColCan.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("cantidad"));
		this.ColId.setCellValueFactory(new PropertyValueFactory<Venta, String>("id"));
		this.ColFec.setCellValueFactory(new PropertyValueFactory<Venta, String>("fecha"));
		this.ColNom.setCellValueFactory(new PropertyValueFactory<Venta, String>("nombre"));
		this.ColPre.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("precio"));
		this.ColVen.setCellValueFactory(new PropertyValueFactory<Venta, String>("vendedor"));
		tableEdit.setItems(this.data);
		cargar();
		llenarComboBoxVend();
		llenarComboBoxProd();
	}
	/**
	 * Llena el ComboBox de nombres de los vendedores con los datos de la base de datos.
	 */
	public void llenarComboBoxVend() {
	    LinkedList<Vendedor> listaVendedor = dataprovider.getVend();
	    comven.getItems().clear();
	    for (Vendedor producto : listaVendedor) {
	    	comven.getItems().add(producto.getNombre());
	    }
	}
	/**
	 * Llena el ComboBox de nombres de los productos con los datos de la base de datos.
	 */
	public void llenarComboBoxProd() {
	    LinkedList<Producto> listaProductos = dataprovider.getProd();
	    comnom.getItems().clear();
	    for (Producto producto : listaProductos) {
	    	comnom.getItems().add(producto.getNombre());
	    }
	}
	/**
	 * Método llamado cuando se hace clic en el botón de guardar cambios.
	 * Actualiza los datos de la venta seleccionada en la base de datos con los datos ingresados en los campos de entrada.
	 *
	 * @param event El evento de clic del mouse.
	 */
	public void btnGuardarOnAction(MouseEvent event) {
		String nombreProducto = comnom.getValue();
        String nombreVendedor = comven.getValue();
        int cantidad = Integer.parseInt(TxtCan.getText());
        String fecha = TxtFec.getText();
        String id = TxtId.getText();
        String idProducto = dataprovider.buscarIdProductoPorNombre(nombreProducto);
        String cedVendedor = dataprovider.buscarIdVendedorPorNombre(nombreVendedor);
        dataprovider.actualizarVenta(idProducto, cedVendedor, cantidad, fecha, id);
        limpiarCampos();
        cargar();
        llenarComboBoxVend();
		llenarComboBoxProd();
        
	}

	

}
