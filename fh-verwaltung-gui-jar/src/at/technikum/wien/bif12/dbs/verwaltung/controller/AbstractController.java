package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.scene.control.Label;

public class AbstractController {

	protected void handleResult(boolean result, Label labelSave) {
		if (!result) {
			labelSave.setText("Fehler beim Speichern");
		}
		labelSave.setVisible(true);
	}

}
