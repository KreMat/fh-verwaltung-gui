package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import at.technikum.wien.bif12.dbs.verwaltung.entities.NamedCourse;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Semester;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Student;

public class AssignFreifachController extends AbstractController {

	@FXML
	private Button btnSave;

	@FXML
	private Label labelSave;

	@FXML
	private ComboBox<Student> dropDownStudent;

	@FXML
	private ComboBox<NamedCourse> dropDownFreifach;

	@FXML
	private ComboBox<Semester> dropDownSemester;

	@FXML
	void clickSave(ActionEvent event) {
		if (dropDownStudent.getSelectionModel().getSelectedItem() == null
				|| dropDownFreifach.getSelectionModel().getSelectedItem() == null) {
			showMessage(labelSave, "Bitte alle DropDown befüllen!");
			return;
		}
		long studentId = dropDownStudent.getSelectionModel().getSelectedItem()
				.getId();
		long courseId = dropDownFreifach.getSelectionModel().getSelectedItem()
				.getId();
		handleResult(dbHandler.assignStudentToCourse(studentId, courseId),
				labelSave);
	}

	@FXML
	void semesterSelected(ActionEvent event) {
		dropDownFreifach.setItems(FXCollections.observableArrayList(dbHandler
				.ladeFreifacher(dropDownSemester.getSelectionModel()
						.getSelectedItem().getId())));

	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		super.init();
		dropDownSemester.getItems().addAll(dbHandler.ladeAlleSemester());
		dropDownStudent.getItems().addAll(dbHandler.ladeAlleStudenten());
	}

}
