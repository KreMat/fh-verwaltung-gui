package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import at.technikum.wien.bif12.dbs.verwaltung.dao.DatabaseHandler;
import at.technikum.wien.bif12.dbs.verwaltung.dao.mock.DatabaseHandlerMock;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Template;

public class AddTemplateController {

	private DatabaseHandler dbHandler;

	@FXML
	private TextField txtToken;

	@FXML
	private Button btnSave;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtECTS;

	@FXML
	private Label labelSave;

	@FXML
	private ComboBox<String> dropDownStudiengang;

	@FXML
	private TextField txtParticipants;

	@FXML
	void clickSave(ActionEvent event) {
		Template t = new Template();
		t.setName(txtName.getText());
		t.setStudiengang(dropDownStudiengang.getSelectionModel()
				.getSelectedItem());
		t.setToken(txtToken.getText());
		t.setEcts(Double.parseDouble(txtECTS.getText()));
		t.setParticipants(Long.parseLong(txtParticipants.getText()));
		if (!dbHandler.addTemplate(t)) {
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
		dbHandler = new DatabaseHandlerMock();
		dropDownStudiengang.getItems().addAll(dbHandler.ladeStudiengaenge());
	}

}
