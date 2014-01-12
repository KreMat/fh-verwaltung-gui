package at.technikum.wien.bif12.dbs.verwaltung.controller;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Grade;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Student;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Zeugnis;

public class CertificateController extends AbstractController {

	public class Record {

		private SimpleStringProperty lv;
		private SimpleStringProperty ects;
		private SimpleStringProperty grade;

		public Record(String lv, String ects, String grade) {
			this.lv = new SimpleStringProperty(lv);
			this.ects = new SimpleStringProperty(ects);
			this.grade = new SimpleStringProperty(grade);
		}

		public String getLv() {
			return lv.getValue();
		}

		public void setLv(String lv) {
			this.lv.set(lv);
		}

		public String getEcts() {
			return ects.getValue();
		}

		public void setEcts(String ects) {
			this.ects.set(ects);
		}

		public String getGrade() {
			return grade.getValue();
		}

		public void setGrade(String grade) {
			this.grade.set(grade);
		}

	}

	@FXML
	private TextField txtSemester;

	@FXML
	private Label labelStudiengang;

	@FXML
	private TableColumn<Record, String> gradeColumn;

	@FXML
	private TableView<Record> tableView;

	@FXML
	private ComboBox<Student> dropDownStudent;

	@FXML
	private TableColumn<Record, String> lVColumn;

	@FXML
	private Button btnGenerate;

	@FXML
	private Label labelName;

	@FXML
	private Label labelSave;

	@FXML
	private TableColumn<Record, String> eCTSColumn;

	@FXML
	void onClickGenerate(ActionEvent event) {
		if (dropDownStudent.getSelectionModel().getSelectedItem() == null) {
			showMessage(labelSave, "Bitte Student wählen!");
			return;
		}
		Zeugnis z = dbHandler.ladeZeugnis(dropDownStudent.getSelectionModel()
				.getSelectedItem().getId(), txtSemester.getText());
		if (z == null) {
			showMessage(labelSave, "Zeugnis konnte nicht generiert werden!");
			return;
		}
		labelName.setText(z.getFirstname() + " " + z.getLastname());
		labelName.setVisible(true);
		labelStudiengang.setText(z.getStudiengang());
		labelStudiengang.setVisible(true);
		tableView.setItems(mapRecord(z.getGrades()));
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		super.init();
		dropDownStudent.getItems().addAll(dbHandler.ladeAlleStudenten());

		lVColumn.setCellValueFactory(new PropertyValueFactory<Record, String>(
				"lv"));

		eCTSColumn
				.setCellValueFactory(new PropertyValueFactory<Record, String>(
						"ects"));

		gradeColumn
				.setCellValueFactory(new PropertyValueFactory<Record, String>(
						"grade"));

	}

	private ObservableList<Record> mapRecord(List<Grade> grades) {
		ObservableList<Record> result = FXCollections.observableArrayList();
		for (Grade grade : grades) {
			result.add(new Record(grade.getLehrveranstaltung(), grade.getEcts()
					+ "", grade.getGrade() + ""));
		}
		return result;
	}

}
