package at.technikum.wien.bif12.dbs.verwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import at.technikum.wien.bif12.dbs.verwaltung.dao.DatabaseHandler;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Student;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Studiengang;
import at.technikum.wien.bif12.dbs.verwaltung.factory.DatabaseHandlerFactory;

public class AddStudentController extends AbstractController {

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
	private TextField txtLastname;

	@FXML
	private TextField txtTel;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtFirstname;

	@FXML
	private ComboBox<Studiengang> dropDownStudiengang;

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
		student.setStudiengangId(dropDownStudiengang.getSelectionModel()
				.getSelectedItem().getId());
		handleResult(dbHandler.addStudent(student));
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		dbHandler = DatabaseHandlerFactory.createDatabaseHandler();
		dropDownStudiengang.getItems().addAll(dbHandler.ladeStudiengaenge());
	}

}
