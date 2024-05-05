package load;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadAddVentasView {

	public LoadAddVentasView(Stage st) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/vistaventa/AddVentasView.fxml"));
			Scene sc = new Scene(root);
			st.setScene(sc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
