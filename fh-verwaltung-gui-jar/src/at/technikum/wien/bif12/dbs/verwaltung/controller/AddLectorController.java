package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import at.technikum.wien.bif12.dbs.verwaltung.dao.DatabaseHandler;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Lektor;
import at.technikum.wien.bif12.dbs.verwaltung.factory.DatabaseHandlerFactory;

public class AddLectorController {

	private DatabaseHandler dbHandler;

	@FXML
	private TextField txtZIP;

	@FXML
	private TextField txtToken;

	@FXML
	private Button btnSave;

	@FXML
	private TextField txtAddress;

	@FXML
	private Label labelSave;

	@FXML
	private TextField txtLastname;

	@FXML
	private TextField txtTel;

	@FXML
	private TextField txtEmail;

	@FXML
	private ComboBox<String> dropDownGehaltsklasse;

	@FXML
	private TextField txtFirstname;

	@FXML
	void clickSave(ActionEvent event) {
		Lektor lektor = new Lektor();
		lektor.setFirstname(txtFirstname.getText());
		lektor.setLastname(txtLastname.getText());
		lektor.setAdress(txtAddress.getText());
		lektor.setZip(txtZIP.getText());
		lektor.setTelefon(txtTel.getText());
		lektor.setEmail(txtEmail.getText());
		lektor.setToken(txtToken.getText());
		lektor.setGehaltsklasse(dropDownGehaltsklasse.getSelectionModel()
				.getSelectedItem());
		if (!dbHandler.addLektor(lektor)) {
			labelSave.setText("Fehler beim Speichern");
		}
		labelSave.setVisible(true);
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		dbHandler = DatabaseHandlerFactory.createDatabaseHandler();
		dropDownGehaltsklasse.getItems().addAll(dbHandler.ladeGehaltsklassen());
	}

}
