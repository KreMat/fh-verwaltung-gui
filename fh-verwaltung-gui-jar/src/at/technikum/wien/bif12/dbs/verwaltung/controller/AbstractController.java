package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AbstractController {

	@FXML
	private Label labelSave;

	protected void handleResult(boolean result) {
		if (!result) {
			labelSave.setText("Fehler beim Speichern");
		}
		labelSave.setVisible(true);
	}

}
