package at.technikum.wien.bif12.dbs.verwaltung.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			semester.setStartDay(format.parse(txtStartDatum.getText()));
			semester.setEndDay(format.parse(txtEndDatum.getText()));
		} catch (ParseException e) {
			showMessage(labelSave, "Bitte Datumsformat beachten!");
			return;
		}
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
