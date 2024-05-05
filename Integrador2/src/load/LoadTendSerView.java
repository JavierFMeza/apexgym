package load;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadTendSerView {
	/**
	 * Constructor de la clase LoadTendSerView que carga la vista de tendencias de servicios en la aplicación.
	 * 
	 * @param st El objeto Stage en el que se establecerá la escena.
	 */
		public LoadTendSerView(Stage st) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/vistaentrenador/TendSerVista.fxml"));
				Scene sc = new Scene(root);
				st.setScene(sc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
		
	
