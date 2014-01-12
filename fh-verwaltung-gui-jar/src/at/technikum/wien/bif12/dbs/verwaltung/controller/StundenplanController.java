package at.technikum.wien.bif12.dbs.verwaltung.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import at.technikum.wien.bif12.dbs.verwaltung.entities.NamedLesson;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Student;

public class StundenplanController extends AbstractController {

	public class Record {

		private SimpleStringProperty lv;
		private SimpleStringProperty room;
		private SimpleStringProperty from;
		private SimpleStringProperty to;

		public Record(String lv, String room, String from, String to) {
			this.lv = new SimpleStringProperty(lv);
			this.room = new SimpleStringProperty(room);
			this.from = new SimpleStringProperty(from);
			this.to = new SimpleStringProperty(to);
		}

		public String getLv() {
			return lv.getValue();
		}

		public void setLv(String lv) {
			this.lv.set(lv);
		}

		public String getRoom() {
			return room.getValue();
		}

		public void setRoom(String room) {
			this.room.set(room);
		}

		public String getFrom() {
			return from.getValue();
		}

		public void setFrom(String from) {
			this.from.set(from);
		}

		public String getTo() {
			return to.getValue();
		}

		public void setTo(String to) {
			this.to.set(to);
		}

	}

	@FXML
	private TextField txtEnde;

	@FXML
	private TableView<Record> tableView;

	@FXML
	private TableColumn<Record, String> fromColumn;

	@FXML
	private ComboBox<Student> dropDownStudent;

	@FXML
	private TableColumn<Record, String> lVColumn;

	@FXML
	private TextField txtStart;

	@FXML
	private Button btnGenerate;

	@FXML
	private Label labelSave;

	@FXML
	private TableColumn<Record, String> roomColumn;

	@FXML
	private TableColumn<Record, String> toColumn;

	@FXML
	void onClickGenerate(ActionEvent event) {
		if (dropDownStudent.getSelectionModel().getSelectedItem() == null) {
			showMessage(labelSave, "Bitte Student wählen!");
			return;
		}
		long studentId = dropDownStudent.getSelectionModel().getSelectedItem()
				.getId();
		String dayStart = txtStart.getText();
		String dayEnd = txtEnde.getText();
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		List<NamedLesson> list;
		try {
			list = dbHandler.ladeStundenplan(studentId, format.parse(dayStart),
					format.parse(dayEnd));
		} catch (ParseException e) {
			showMessage(labelSave, "Datumsforamt beachten!");
			return;
		}
		tableView.setItems(mapRecord(list));
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

		roomColumn
				.setCellValueFactory(new PropertyValueFactory<Record, String>(
						"room"));

		fromColumn
				.setCellValueFactory(new PropertyValueFactory<Record, String>(
						"from"));

		toColumn.setCellValueFactory(new PropertyValueFactory<Record, String>(
				"to"));
	}

	private ObservableList<Record> mapRecord(List<NamedLesson> lessons) {
		ObservableList<Record> result = FXCollections.observableArrayList();
		SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		for (NamedLesson l : lessons) {
			result.add(new Record(l.getCourseName(), l.getRoomName(), formater
					.format(l.getStartTime()), formater.format(l.getEndTime())));
		}
		return result;
	}
}
