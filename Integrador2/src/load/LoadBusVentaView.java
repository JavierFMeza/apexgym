package load;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadBusVentaView {
	/**
	 * Constructor de la clase LoadBusVentaView que carga la vista de búsqueda de venta en la aplicación.
	 * 
	 * @param st El objeto Stage en el que se establecerá la escena.
	 */
	public LoadBusVentaView(Stage st) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/vistaventa/BusVentaView.fxml"));
			Scene sc = new Scene(root);
			st.setScene(sc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
