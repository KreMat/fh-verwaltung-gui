package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import at.technikum.wien.bif12.dbs.verwaltung.dao.DatabaseHandler;
import at.technikum.wien.bif12.dbs.verwaltung.dao.mock.DatabaseHandlerMock;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Student;

public class AddStudentController {

	private DatabaseHandler dbHandler;

	@FXML
	private TextField txtZIP;

	@FXML
	private TextField txtToken;

	@FXML
	private Button btnSave;

	@FXML
	private TextField txtAddress;

	@FXML
	private Label labelSave;

	@FXML
	private TextField txtLastname;

	@FXML
	private TextField txtTel;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtFirstname;

	@FXML
	private ComboBox<String> dropDownStudiengang;

	@FXML
	void clickSave(ActionEvent event) {
		Student student = new Student();
		student.setFirstname(txtFirstname.getText());
		student.setLastname(txtLastname.getText());
		student.setAdress(txtAddress.getText());
		student.setZip(txtZIP.getText());
		student.setTelefon(txtTel.getText());
		student.setEmail(txtEmail.getText());
		student.setToken(txtToken.getText());
		student.setCourse_of_studies_name(dropDownStudiengang
				.getSelectionModel().getSelectedItem());
		if (!dbHandler.addStudent(student)) {
			labelSave.setText("Fehler beim Speichern");
		}
		labelSave.setVisible(true);
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		dbHandler = new DatabaseHandlerMock();
		dropDownStudiengang.getItems().addAll(dbHandler.ladeStudiengaenge());
	}

}
