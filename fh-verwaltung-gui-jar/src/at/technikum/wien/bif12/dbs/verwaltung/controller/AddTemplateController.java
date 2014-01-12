package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Studiengang;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Template;

public class AddTemplateController extends AbstractController {

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
	private TextField txtSemester;

	@FXML
	private Label labelSave;

	@FXML
	void clickSave(ActionEvent event) {
		try {
			Template t = new Template();
			t.setName(txtName.getText());
			if (dropDownStudiengang.getSelectionModel().getSelectedItem() == null) {
				showMessage(labelSave, "Bitte Studiengang wählen!");
				return;
			}
			t.setCourseOfStudiesId(dropDownStudiengang.getSelectionModel()
					.getSelectedItem().getId());
			t.setToken(txtToken.getText());
			t.setEcts(Double.parseDouble(txtECTS.getText()));
			t.setParticipants(Long.parseLong(txtParticipants.getText()));
			t.setSemester(Long.parseLong(txtSemester.getText()));
			handleResult(dbHandler.addTemplate(t), labelSave);
		} catch (Exception e) {
			showMessage(labelSave, "Bitte Formate beachten!");
		}
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		super.init();
		dropDownStudiengang.getItems().addAll(dbHandler.ladeStudiengaenge());
	}

}
