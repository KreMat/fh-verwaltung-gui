package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import at.technikum.wien.bif12.dbs.verwaltung.dao.DatabaseHandler;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Studiengang;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Template;
import at.technikum.wien.bif12.dbs.verwaltung.factory.DatabaseHandlerFactory;

public class AddTemplateController extends AbstractController {

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
	private ComboBox<Studiengang> dropDownStudiengang;

	@FXML
	private TextField txtParticipants;

	@FXML
	void clickSave(ActionEvent event) {
		Template t = new Template();
		t.setName(txtName.getText());
		t.setCourseOfStudiesId(dropDownStudiengang.getSelectionModel()
				.getSelectedItem().getId());
		t.setToken(txtToken.getText());
		t.setEcts(Double.parseDouble(txtECTS.getText()));
		t.setParticipants(Long.parseLong(txtParticipants.getText()));
		handleResult(dbHandler.addTemplate(t));
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		dbHandler = DatabaseHandlerFactory.createDatabaseHandler();
		dropDownStudiengang.getItems().addAll(dbHandler.ladeStudiengaenge());
	}

}
