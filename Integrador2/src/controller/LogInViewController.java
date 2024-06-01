package controller;

import datos.BaseDatos;
import datos.LogIn;
import datos.Session;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import load.LoadMenuEntView;
import load.LoadMenuVentaView;
import load.LoadUsuarioView;

public class LogInViewController {
	@FXML
    private AnchorPane Vista;
	@FXML
	private TextField txtFieldUser;
	@FXML
	private Button btnIng;
	@FXML
	private TextField txtFieldPass;
	
    @FXML
    private Label txtError;
	
    private LoadUsuarioView luv;
    
	private BaseDatos dataprovider = new BaseDatos();

	/**
	 * Método llamado cuando se hace clic en el botón de inicio de sesión.
	 * Verifica las credenciales ingresadas y carga la vista de usuario correspondiente si las credenciales son válidas.
	 *
	 * @param event El evento de clic del mouse.
	 */
	@FXML
	public void onLoginClicked(MouseEvent event) {
        LogIn user = dataprovider.login(txtFieldUser.getText(), txtFieldPass.getText());
        if (user != null) {
            Session.setUsuario(user);
            Session.setAdmin(false);

            String profesion = user.getProfesion();
            Stage stage = (Stage) this.txtFieldUser.getScene().getWindow();

            switch (profesion) {
                case "vendedor":
                    new LoadMenuVentaView(stage);
                    break;
                case "entrenador":
                    new LoadMenuEntView(stage);
                    break;
                case "administrativo":
                    new LoadUsuarioView(stage);
                    break;
                default:
                    txtError.setText("Profesion no reconocida");
                    Alert alt = new Alert(Alert.AlertType.ERROR);
                    alt.setContentText("Profesion no reconocida");
                    alt.setHeaderText("Error");
                    alt.show();
                    break;
            }
        } else {
            txtError.setText("Usuario o contraseña incorrecta");
            Alert alt = new Alert(Alert.AlertType.ERROR);
            alt.setContentText("Usuario o contraseña incorrecta");
            alt.setHeaderText("Error");
            alt.show();
        }
    }
	
	
	
}
