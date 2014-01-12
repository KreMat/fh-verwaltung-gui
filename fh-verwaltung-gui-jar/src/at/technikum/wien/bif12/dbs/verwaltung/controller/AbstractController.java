package at.technikum.wien.bif12.dbs.verwaltung.controller;

import at.technikum.wien.bif12.dbs.verwaltung.dao.DatabaseHandler;
import at.technikum.wien.bif12.dbs.verwaltung.factory.DatabaseHandlerFactory;
import javafx.scene.control.Label;

public class AbstractController {

	protected DatabaseHandler dbHandler;

	protected void handleResult(boolean result, Label labelSave) {
		if (!result) {
			labelSave.setText("Fehler beim Speichern");
		}
		labelSave.setVisible(true);
	}

	protected void init() {
		dbHandler = DatabaseHandlerFactory.createDatabaseHandler();
	}

}
