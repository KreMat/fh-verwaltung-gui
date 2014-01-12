package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Semester;

public class AddSemesterController extends AbstractController {

	@FXML
	private TextField txtToken;

	@FXML
	private TextField txtStartDatum;

	@FXML
	private Button btnSave;

	@FXML
	private TextField txtEndDatum;

	@FXML
	private Label labelSave;

	@FXML
	void clickSave(ActionEvent event) {
		Semester semester = new Semester();
		semester.setToken(txtToken.getText());
		semester.setStart_day(txtStartDatum.getText());
		semester.setEnd_day(txtEndDatum.getText());
		handleResult(dbHandler.addSemester(semester), labelSave);
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		super.init();
	}

}
