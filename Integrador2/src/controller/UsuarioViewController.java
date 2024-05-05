package controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import datos.BaseDatos;
import datos.LogIn;
import datos.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import load.LoadMenuEntView;
import load.LoadMenuVentaView;

public class UsuarioViewController implements Initializable{
	@FXML
    private Button btnDesco;
	@FXML
    private AnchorPane Vista;
	@FXML
	private Button BtnVen;
	@FXML
	private Button BtnEnt;
	@FXML
	private Label LabelNom;
	@FXML
	private Label LabelCed;
	@FXML
	private Label Labelcar;
	
	private LoadMenuVentaView lmv;
	
	private LoadMenuEntView lmr;
	
	private BaseDatos dataprovider;
	
	
	private ObservableList<LogIn> data = FXCollections.observableArrayList();
	
	/**
	 * Método llamado cuando se hace clic en el botón de ventas.
	 * Carga la vista del menú de ventas correspondiente.
	 *
	 * @param event El evento de clic del mouse.
	 */
	@FXML
	public void onVentaClicked(MouseEvent event) {
		lmv = new LoadMenuVentaView((Stage)this.BtnVen.getScene().getWindow());
	}
	/**
	 * Método llamado cuando se hace clic en el botón de entrenamientos.
	 * Carga la vista del menú de entrenamientos correspondiente.
	 *
	 * @param event El evento de clic del mouse.
	 */
	@FXML
	public void onEntClicked(MouseEvent event) {
		lmr = new LoadMenuEntView((Stage)this.BtnVen.getScene().getWindow());
	}
	/**
	 * Método llamado cuando se hace clic en el botón de desconexión.
	 * Cierra la ventana actual.
	 *
	 * @param event El evento de clic del mouse.
	 */
	@FXML
    void onDescoClicked(MouseEvent event) {
		Stage stage = (Stage) btnDesco.getScene().getWindow();
        stage.close();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.dataprovider = new BaseDatos();
		LinkedList<Vendedor> listaVendedor = dataprovider.getVend();
		for (Vendedor producto : listaVendedor) {
			LabelNom.setText(producto.getNombre());
			LabelCed.setText(producto.getCedula());
	    }
		
	}
}
