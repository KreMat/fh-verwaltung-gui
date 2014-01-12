package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Course;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Lesson;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Room;

public class AddLessonController extends AbstractController {

	@FXML
	private ComboBox<Room> dropDownRoom;

	@FXML
	private Button btnSave;

	@FXML
	private Label labelSave;

	@FXML
	private ComboBox<Course> dropDownLV;

	@FXML
	private TextField txtEnd;

	@FXML
	private TextField txtStart;

	@FXML
	void clickSave(ActionEvent event) {
		Lesson l = new Lesson();
		l.setStart_time(txtStart.getText());
		l.setEnd_time(txtEnd.getText());
		if (dropDownLV.getSelectionModel().getSelectedItem() == null) {
			showMessage(labelSave, "Bitte LV wählen");
			return;
		}
		if (dropDownRoom.getSelectionModel().getSelectedItem() == null) {
			showMessage(labelSave, "Bitte Raum wählen");
			return;
		}
		l.setCourse_id(dropDownLV.getSelectionModel().getSelectedItem().getId());
		l.setRoom_id(dropDownRoom.getSelectionModel().getSelectedItem().getId());
		handleResult(dbHandler.addLesson(l), labelSave);
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		super.init();
		dropDownLV.getItems().addAll(dbHandler.ladeAlleLvs());
		dropDownRoom.getItems().addAll(dbHandler.ladeAlleRaeume());
	}

}
