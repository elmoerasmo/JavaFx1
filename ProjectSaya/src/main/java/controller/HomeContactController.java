package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Kontak;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller untuk Manajemen Kontak
 * File: HomeContactController.java
 * Lokasi: src/controller/HomeContactController.java
 */
public class HomeContactController implements Initializable {

    @FXML
    private TextField tfNama;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNomorTelepon;
    @FXML
    private TextField tfAlamat;
    @FXML
    private Button btnTambah;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnHapus;
    @FXML
    private TableView<Kontak> tvTambah;
    @FXML
    private TableColumn<Kontak, String> tcNama;
    @FXML
    private TableColumn<Kontak, String> tcEmail;
    @FXML
    private TableColumn<Kontak, String> tcNomorTelepon;
    @FXML
    private TableColumn<Kontak, String> tcAlamat;

    private ObservableList<Kontak> kontakList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inisialisasi ObservableList
        kontakList = FXCollections.observableArrayList();

        // Setup kolom TableView
        tcNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcNomorTelepon.setCellValueFactory(new PropertyValueFactory<>("nomorTelepon"));
        tcAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));

        // Set data ke TableView
        tvTambah.setItems(kontakList);

        // Listener untuk memilih row di TableView
        tvTambah.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> tampilkanDetailKontak(newValue)
        );
    }

    @FXML
    private void TambahKontak(ActionEvent event) {
        if (validasiInput()) {
            Kontak kontak = new Kontak(
                tfNama.getText(),
                tfEmail.getText(),
                tfNomorTelepon.getText(),
                tfAlamat.getText()
            );
            
            kontakList.add(kontak);
            bersihkanForm();
            tampilkanAlert("Berhasil", "Kontak berhasil ditambahkan!", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void EditKontak(ActionEvent event) {
        Kontak kontakTerpilih = tvTambah.getSelectionModel().getSelectedItem();
        
        if (kontakTerpilih == null) {
            tampilkanAlert("Peringatan", "Silakan pilih kontak yang ingin diedit!", Alert.AlertType.WARNING);
            return;
        }
        
        if (validasiInput()) {
            kontakTerpilih.setNama(tfNama.getText());
            kontakTerpilih.setEmail(tfEmail.getText());
            kontakTerpilih.setNomorTelepon(tfNomorTelepon.getText());
            kontakTerpilih.setAlamat(tfAlamat.getText());
            
            tvTambah.refresh();
            bersihkanForm();
            tampilkanAlert("Berhasil", "Kontak berhasil diperbarui!", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void HapusKontak(ActionEvent event) {
        Kontak kontakTerpilih = tvTambah.getSelectionModel().getSelectedItem();
        
        if (kontakTerpilih == null) {
            tampilkanAlert("Peringatan", "Silakan pilih kontak yang ingin dihapus!", Alert.AlertType.WARNING);
            return;
        }
        
        kontakList.remove(kontakTerpilih);
        bersihkanForm();
        tampilkanAlert("Berhasil", "Kontak berhasil dihapus!", Alert.AlertType.INFORMATION);
    }

    private void tampilkanDetailKontak(Kontak kontak) {
        if (kontak != null) {
            tfNama.setText(kontak.getNama());
            tfEmail.setText(kontak.getEmail());
            tfNomorTelepon.setText(kontak.getNomorTelepon());
            tfAlamat.setText(kontak.getAlamat());
        }
    }

    private boolean validasiInput() {
        if (tfNama.getText().trim().isEmpty()) {
            tampilkanAlert("Error", "Nama tidak boleh kosong!", Alert.AlertType.ERROR);
            return false;
        }
        if (tfEmail.getText().trim().isEmpty()) {
            tampilkanAlert("Error", "Email tidak boleh kosong!", Alert.AlertType.ERROR);
            return false;
        }
        if (tfNomorTelepon.getText().trim().isEmpty()) {
            tampilkanAlert("Error", "Nomor telepon tidak boleh kosong!", Alert.AlertType.ERROR);
            return false;
        }
        if (tfAlamat.getText().trim().isEmpty()) {
            tampilkanAlert("Error", "Alamat tidak boleh kosong!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void bersihkanForm() {
        tfNama.clear();
        tfEmail.clear();
        tfNomorTelepon.clear();
        tfAlamat.clear();
        tvTambah.getSelectionModel().clearSelection();
    }

    private void tampilkanAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}