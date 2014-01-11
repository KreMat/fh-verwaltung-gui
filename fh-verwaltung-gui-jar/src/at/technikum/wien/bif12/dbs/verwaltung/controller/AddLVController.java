package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import at.technikum.wien.bif12.dbs.verwaltung.dao.DatabaseHandler;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Course;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Lektor;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Semester;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Studiengang;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Template;
import at.technikum.wien.bif12.dbs.verwaltung.factory.DatabaseHandlerFactory;

public class AddLVController extends AbstractController {

	private DatabaseHandler dbHandler;

	@FXML
	private Button btnSave;

	@FXML
	private ComboBox<Semester> dropDownSemester;

	@FXML
	private ComboBox<Studiengang> dropDownStudiengang;

	@FXML
	private ComboBox<Lektor> dropDoanLektor;

	@FXML
	private ComboBox<Template> dropDownTemplate;

	@FXML
	private Label labelSave;

	@FXML
	void clickSave(ActionEvent event) {
		Course c = new Course();
		c.setCourseOfStudiesId(dropDownStudiengang.getSelectionModel()
				.getSelectedItem().getId());
		c.setCourseTemplateId(dropDownTemplate.getSelectionModel()
				.getSelectedItem().getId());
		c.setLektorId(dropDoanLektor.getSelectionModel().getSelectedItem()
				.getId());
		c.setSemesterId(dropDownSemester.getSelectionModel().getSelectedItem()
				.getId());
		handleResult(dbHandler.addCourse(c), labelSave);
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		dbHandler = DatabaseHandlerFactory.createDatabaseHandler();
		dropDownSemester.getItems().addAll(dbHandler.ladeAlleSemester());
		dropDownStudiengang.getItems().addAll(dbHandler.ladeStudiengaenge());
		dropDoanLektor.getItems().addAll(dbHandler.ladeAlleLektoren());
		dropDownTemplate.getItems().addAll(dbHandler.ladeAlleTemplate());
	}

}
