package load;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Constructor de la clase LoadMenuVentaView que carga la vista del menú de venta en la aplicación.
 * 
 * @param st El objeto Stage en el que se establecerá la escena.
 */
public class LoadMenuVentaView {
	
	public LoadMenuVentaView(Stage st) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/vistaventa/MenuVentaView.fxml"));
			Scene sc = new Scene(root);
			st.setScene(sc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}