package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import at.technikum.wien.bif12.dbs.verwaltung.dao.DatabaseHandler;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Lektor;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Studiengang;
import at.technikum.wien.bif12.dbs.verwaltung.factory.DatabaseHandlerFactory;

public class AddStudiengangController extends AbstractController {

	private DatabaseHandler dbHandler;

	@FXML
	private Button btnSave;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtDegree;

	@FXML
	private ComboBox<Lektor> dropDownLektor;

	@FXML
	private TextField txtNr;

	@FXML
	void clickSave(ActionEvent event) {
		Studiengang s = new Studiengang();
		s.setName(txtName.getText());
		s.setDegree(txtDegree.getText());
		s.setNr(Long.parseLong(txtNr.getText()));
		s.setLecturer_id(dropDownLektor.getSelectionModel().getSelectedItem()
				.getId());
		handleResult(dbHandler.addStudiengang(s));
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		dbHandler = DatabaseHandlerFactory.createDatabaseHandler();
		dropDownLektor.getItems().addAll(dbHandler.ladeAlleLektoren());
	}

}
