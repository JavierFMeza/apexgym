package load;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase para cargar la vista de agregar entrenador en la aplicación.
 */
public class LoadAddEntView {
	public LoadAddEntView(Stage st) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/vistaentrenador/AddEntView.fxml"));
			Scene sc = new Scene(root);
			st.setScene(sc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

