package at.technikum.wien.bif12.dbs.verwaltung.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainController {

	private static final String FILENAME_ADD_LECTOR = "AddLector.fxml";
	private static final String FILENAME_ADD_STUDENT = "AddStudent.fxml";
	private static final String FILENAME_ADD_STUDIENGANG = "AddStudiengang.fxml";
	private static final String FILENAME_ADD_TEMPLATE = "AddTemplate.fxml";
	private static final String FILENAME_ADD_SEMESTER = "AddSemester.fxml";
	private static final String FILENAME_ADD_LV = "AddLV.fxml";
	private static final String FILENAME_STUNDEN_EINTRAGEN = null;
	private static final String FILENAME_NOTEN_EINTRAGEN = null;
	private static final String FILENAME_ZEUGNIS_GENERIEREN = null;
	private static final String FILENAME_STUDENPLAN_ANZEIGEN = null;
	private static final String FILENAME_ANWESENHEITSLISTE_ANZEIGEN = null;
	private static final String FILENAME_FREIFACH = null;

	@FXML
	private VBox mainContent;

	@FXML
	private Button buttonGenerateZeugnis;

	@FXML
	private Button buttonNotenEintragen;

	@FXML
	private Button buttonFreifaecher;

	@FXML
	private Button buttonShowStundenplan;

	@FXML
	private Button buttonAddLV;

	@FXML
	private Button buttonAddLector;

	@FXML
	private Button buttonAddStudiengang;

	@FXML
	private Button buttonAddStudent;

	@FXML
	private Button buttonAddTemplate;

	@FXML
	private Button buttonAddSemester;

	@FXML
	private Button buttonStundenEintragen;

	@FXML
	private Button buttonGenerateAnwesenheitsliste;

	@FXML
	void clickAddLektor(ActionEvent event) {
		loadContent(FILENAME_ADD_LECTOR);
	}

	@FXML
	void clickAddLV(ActionEvent event) {
		loadContent(FILENAME_ADD_LV);
	}

	@FXML
	void clickAddSemester(ActionEvent event) {
		loadContent(FILENAME_ADD_SEMESTER);
	}

	@FXML
	void clickAddTemplate(ActionEvent event) {
		loadContent(FILENAME_ADD_TEMPLATE);
	}

	@FXML
	void clickAddStudiengang(ActionEvent event) {
		loadContent(FILENAME_ADD_STUDIENGANG);
	}

	@FXML
	void clickAddStudent(ActionEvent event) {
		loadContent(FILENAME_ADD_STUDENT);
	}

	@FXML
	void clickGenerateZeugnis(ActionEvent event) {
		loadContent(FILENAME_ZEUGNIS_GENERIEREN);
	}

	@FXML
	void clickShowStundenplan(ActionEvent event) {
		loadContent(FILENAME_STUDENPLAN_ANZEIGEN);
	}

	@FXML
	void clickGenerateAnwesenheitsliste(ActionEvent event) {
		loadContent(FILENAME_ANWESENHEITSLISTE_ANZEIGEN);
	}

	@FXML
	void clickFreifaecher(ActionEvent event) {
		loadContent(FILENAME_FREIFACH);
	}

	@FXML
	void clickNotenEintragen(ActionEvent event) {
		loadContent(FILENAME_NOTEN_EINTRAGEN);
	}

	@FXML
	void clickStundenEintragen(ActionEvent event) {
		loadContent(FILENAME_STUNDEN_EINTRAGEN);
	}

	@FXML
	void closeButtonAction(ActionEvent event) {
		System.exit(0);
	}

	private void loadContent(String filename) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/at/technikum/wien/bif12/dbs/verwaltung/gui/" + filename));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		mainContent.getChildren().clear();
		mainContent.getChildren().add(root);
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

}
