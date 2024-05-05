package controller;

import datos.BaseDatos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import load.LoadMenuVentaView;
import javafx.scene.control.Label;

public class AddProdViewController {
	@FXML
    private Button BtnBack;
    @FXML
    private Button BtnCrear;
    @FXML
    private Label LbError;
    @FXML
    private TextField TxtDet;
    @FXML
    private TextField TxtId;
    @FXML
    private TextField TxtNom;
    @FXML
    private TextField TxtTipo;
    @FXML
    private Label TxtError;
    @FXML
    private TextField Txtprecio;
    @FXML
    private AnchorPane Vista;
	
	private LoadMenuVentaView lmvv;
	
	private BaseDatos dataprovider = new BaseDatos();
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
     * Método llamado cuando se hace clic en el botón de crear.
     * Obtiene los datos del nuevo producto de los campos de texto y llama al método guardarProd del proveedor de datos.
     * Muestra un mensaje de éxito o error en la etiqueta TxtError.
     * 
     * @param event El evento de clic del mouse.
     */
	@FXML
    void onCrearClicked(MouseEvent event) {
		String id = TxtId.getText();
		String nombre = TxtNom.getText();
		String tipo = TxtTipo.getText();
		int precio = Integer.parseInt(Txtprecio.getText());
		String detalles = TxtDet.getText();
		boolean exito = dataprovider.guardarProd(id, nombre, precio, tipo, detalles);
        if (exito) {
        	TxtError.setText("Completado");
        } 
        else {
        	TxtError.setText("Error en guardar");
        }
	}
}
