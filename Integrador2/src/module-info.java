module Integrador {
	
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml, javafx.base;
	opens datos to javafx.graphics, javafx.fxml, javafx.base;
	opens load to javafx.graphics, javafx.fxml, javafx.base;
}
