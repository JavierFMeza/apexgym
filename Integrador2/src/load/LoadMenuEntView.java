package load;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Constructor de la clase LoadMenuEntView que carga la vista del menú de entrenador en la aplicación.
 * 
 * @param st El objeto Stage en el que se establecerá la escena.
 */
public class LoadMenuEntView {
	public LoadMenuEntView(Stage st) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/vistaentrenador/MenuEntView.fxml"));
			Scene sc = new Scene(root);
			st.setScene(sc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
