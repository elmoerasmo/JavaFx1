package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Kontak {
    private final StringProperty nama;
    private final StringProperty email;
    private final StringProperty nomorTelepon;
    private final StringProperty alamat;

    // Constructor
    public Kontak(String nama, String email, String nomorTelepon, String alamat) {
        this.nama = new SimpleStringProperty(nama);
        this.email = new SimpleStringProperty(email);
        this.nomorTelepon = new SimpleStringProperty(nomorTelepon);
        this.alamat = new SimpleStringProperty(alamat);
    }

    // Getter dan Setter untuk Nama
    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public StringProperty namaProperty() {
        return nama;
    }

    // Getter dan Setter untuk Email
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    // Getter dan Setter untuk Nomor Telepon
    public String getNomorTelepon() {
        return nomorTelepon.get();
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon.set(nomorTelepon);
    }

    public StringProperty nomorTeleponProperty() {
        return nomorTelepon;
    }

    // Getter dan Setter untuk Alamat
    public String getAlamat() {
        return alamat.get();
    }

    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }

    public StringProperty alamatProperty() {
        return alamat;
    }
}