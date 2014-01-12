package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Lektor;

public class AddLectorController extends AbstractController {

	@FXML
	private TextField txtZIP;

	@FXML
	private TextField txtToken;

	@FXML
	private Button btnSave;

	@FXML
	private TextField txtAddress;

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
	private Label labelSave;

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
		if (dropDownGehaltsklasse.getSelectionModel().getSelectedItem() == null) {
			showMessage(labelSave, "Bitte Gehaltsklasse wählen");
			return;
		}
		lektor.setGehaltsklasse(dropDownGehaltsklasse.getSelectionModel()
				.getSelectedItem());
		handleResult(dbHandler.addLektor(lektor), labelSave);
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		super.init();
		dropDownGehaltsklasse.getItems().addAll(dbHandler.ladeGehaltsklassen());
	}

}
