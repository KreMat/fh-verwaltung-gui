package at.technikum.wien.bif12.dbs.verwaltung.controller;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Course;
import at.technikum.wien.bif12.dbs.verwaltung.entities.GradedStudent;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Semester;

public class AddGradeController extends AbstractController {

	public class Record {

		private long studentId;
		private SimpleStringProperty firstname;
		private SimpleStringProperty lastname;
		private SimpleIntegerProperty grade;

		public Record(long studentId, String firstname, String lastname,
				Integer grade) {
			this.studentId = studentId;
			this.firstname = new SimpleStringProperty(firstname);
			this.lastname = new SimpleStringProperty(lastname);
			this.grade = new SimpleIntegerProperty(grade);
		}

		public String getFirstname() {
			return firstname.getValue();
		}

		public void setFirstname(String firstname) {
			this.firstname.set(firstname);
		}

		public String getLastname() {
			return lastname.getValue();
		}

		public void setLastname(String lastname) {
			this.lastname.set(lastname);
		}

		public Integer getGrade() {
			return grade.getValue();
		}

		public void setGrade(Integer grade) {
			this.grade.set(grade);
		}

		public long getStudentId() {
			return studentId;
		}

		public void setStudentId(long studentId) {
			this.studentId = studentId;
		}

	}

	@FXML
	private TableColumn<Record, String> firstNameColumn;

	@FXML
	private Button btnSave;

	@FXML
	private Label labelSave;

	@FXML
	private ComboBox<Course> dropDownLV;

	@FXML
	private TableColumn<Record, Integer> gradeColumn;

	@FXML
	private TableView<Record> tableView;

	@FXML
	private TableColumn<Record, String> lastNameColumn;

	@FXML
	private ComboBox<Semester> dropDownSemester;

	@FXML
	void onSemesterChanged(ActionEvent event) {
		dropDownLV.getItems().clear();
		dropDownLV.getItems().addAll(
				dbHandler.ladeLvs(dropDownSemester.getSelectionModel()
						.getSelectedItem().getId()));
	}

	@FXML
	void clickSave(ActionEvent event) {
		if (dropDownLV.getSelectionModel().getSelectedItem() == null) {
			showMessage(labelSave, "Bitte LV wählen");
			return;
		}
		long courseId = dropDownLV.getSelectionModel().getSelectedItem()
				.getId();
		boolean success = true;
		for (Record r : tableView.getItems()) {
			if (r.getGrade() != null) {
				if (!dbHandler.registerGrade(r.getStudentId(), courseId,
						r.getGrade())) {
					success = false;
					break;
				}
			}
		}
		handleResult(success, labelSave);

	}

	@FXML
	void onLVchanged(ActionEvent event) {
		if (dropDownLV.getSelectionModel().getSelectedItem() == null) {
			showMessage(labelSave, "Bitte LV wählen");
			return;
		}
		long courseId = dropDownLV.getSelectionModel().getSelectedItem()
				.getId();
		tableView.setItems(mapRecord(dbHandler.ladeStudenten(courseId)));
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		super.init();
		dropDownLV.getItems().addAll(dbHandler.ladeAlleLvs());
		dropDownSemester.getItems().addAll(dbHandler.ladeAlleSemester());

		tableView.setEditable(true);
		Callback<TableColumn<Record, Integer>, TableCell<Record, Integer>> cellFactory = new Callback<TableColumn<Record, Integer>, TableCell<Record, Integer>>() {
			public TableCell<Record, Integer> call(
					TableColumn<Record, Integer> p) {
				return new EditingCell();
			}
		};

		firstNameColumn
				.setCellValueFactory(new PropertyValueFactory<Record, String>(
						"firstname"));

		lastNameColumn
				.setCellValueFactory(new PropertyValueFactory<Record, String>(
						"lastname"));

		gradeColumn
				.setCellValueFactory(new PropertyValueFactory<Record, Integer>(
						"grade"));

		gradeColumn.setCellFactory(cellFactory);
		gradeColumn
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Record, Integer>>() {
					@Override
					public void handle(
							TableColumn.CellEditEvent<Record, Integer> t) {
						((Record) t.getTableView().getItems()
								.get(t.getTablePosition().getRow())).setGrade(t
								.getNewValue());
					}
				});

	}

	private ObservableList<Record> mapRecord(List<GradedStudent> studenten) {
		ObservableList<Record> result = FXCollections.observableArrayList();
		for (GradedStudent student : studenten) {
			result.add(new Record(student.getId(), student.getFirstname(),
					student.getLastname(), student.getGrade()));
		}
		return result;
	}

	class EditingCell extends TableCell<Record, Integer> {

		private TextField textField;

		public EditingCell() {
		}

		@Override
		public void startEdit() {
			super.startEdit();

			if (textField == null) {
				createTextField();
			}

			setGraphic(textField);
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			textField.selectAll();
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();

			setText(String.valueOf(getItem()));
			setContentDisplay(ContentDisplay.TEXT_ONLY);
		}

		@Override
		public void updateItem(Integer item, boolean empty) {
			super.updateItem(item, empty);

			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (textField != null) {
						textField.setText(getString());
					}
					setGraphic(textField);
					setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				} else {
					setText(getString());
					setContentDisplay(ContentDisplay.TEXT_ONLY);
				}
			}
		}

		private void createTextField() {
			textField = new TextField(getString());
			textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()
					* 2);
			textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent t) {
					if (t.getCode() == KeyCode.ENTER) {
						commitEdit(Integer.parseInt(textField.getText()));
					} else if (t.getCode() == KeyCode.ESCAPE) {
						cancelEdit();
					}
				}
			});
		}

		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}

}
