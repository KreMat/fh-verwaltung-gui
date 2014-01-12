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
import javafx.scene.control.cell.PropertyValueFactory;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Anwesenheitsliste;
import at.technikum.wien.bif12.dbs.verwaltung.entities.Course;

public class AnwesenheitslisteController extends AbstractController {

	public class Record {

		private SimpleStringProperty name;
		private SimpleStringProperty sign;

		public Record(String name) {
			this.name = new SimpleStringProperty(name);
			this.sign = new SimpleStringProperty();
		}

		public String getName() {
			return name.getValue();
		}

		public void setName(String name) {
			this.name.set(name);
		}

		public String getSign() {
			return sign.getValue();
		}

		public void setSign(String sign) {
			this.sign.set(sign);
		}

	}

	@FXML
	private TableColumn<Record, String> nameColumn;

	@FXML
	private Label labelLV;

	@FXML
	private Label labelSave;

	@FXML
	private ComboBox<Course> dropDownLV;

	@FXML
	private TableView<Record> tableView;

	@FXML
	private TableColumn<Record, String> signColumn;

	@FXML
	private Button btnGenerate;

	@FXML
	void onClickGenerate(ActionEvent event) {
		if (dropDownLV.getSelectionModel().getSelectedItem() == null) {
			showMessage(labelSave, "Bitte LV wählen!");
			return;
		}
		Anwesenheitsliste a = dbHandler.ladeAnwesenheitsliste(dropDownLV
				.getSelectionModel().getSelectedItem().getId());

		if (a == null) {
			showMessage(labelSave,
					"Anwesenheitsliste konnte nicht generiert werden!");
			return;
		}

		tableView.setItems(mapRecord(a.getNames()));
		labelLV.setText("Lehrveranstaltung: " + a.getLehrveranstaltung());
		labelLV.setVisible(true);
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		super.init();
		dropDownLV.getItems().addAll(dbHandler.ladeAlleLvs());

		nameColumn
				.setCellValueFactory(new PropertyValueFactory<Record, String>(
						"name"));

		signColumn
				.setCellValueFactory(new PropertyValueFactory<Record, String>(
						"sign"));

	}

	private ObservableList<Record> mapRecord(List<String> students) {
		ObservableList<Record> result = FXCollections.observableArrayList();
		for (String s : students) {
			result.add(new Record(s));
		}
		return result;
	}

}
