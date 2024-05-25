/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Connector;
import Model.Mahasiswa.ModelMahasiswa;
import Model.Mahasiswa.ModelTableMahasiswa;
import View.Mahasiswa.EditDataMahasiswa;
import View.Mahasiswa.ViewDataMahasiswa;
import java.util.List;
import javax.swing.JOptionPane;
import Model.Mahasiswa.InterfaceDAOMahasiswa;
import View.Mahasiswa.InputDataMahasiswa;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author febri
 */
public class ControllerMahasiswa {

    String username;
    InputDataMahasiswa inputmhs;
    EditDataMahasiswa editmhs;
    ViewDataMahasiswa viewmhs;

    InterfaceDAOMahasiswa daoMahasiswa;

    List<ModelMahasiswa> daftarMahasiswa;

    public ControllerMahasiswa(InputDataMahasiswa inputmhs) {
        this.inputmhs = inputmhs;
        this.daoMahasiswa = new Model.Mahasiswa.DAOMahasiswa();
    }

    public ControllerMahasiswa(EditDataMahasiswa editmhs) {
        this.editmhs = editmhs;
        this.daoMahasiswa = new Model.Mahasiswa.DAOMahasiswa();
    }

    public ControllerMahasiswa(ViewDataMahasiswa viewmhs) {
        this.viewmhs = viewmhs;
        this.daoMahasiswa = new Model.Mahasiswa.DAOMahasiswa();
    }

    public void showAllMahasiswa() {
        List<ModelMahasiswa> mahasiswaList = new ArrayList<>();
        try {
            String query = "SELECT * FROM mahasiswa";
            PreparedStatement statement;
            statement = Connector.Koneksi().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ModelMahasiswa mhs = new ModelMahasiswa();
                mhs.setId(rs.getInt("id"));
                mhs.setNama(rs.getString("nama"));
                mhs.setNim(rs.getString("nim"));
                mhs.setEmail(rs.getString("email"));
                mhs.setPassword(rs.getString("password"));
                mhs.setAngkatan(rs.getString("angkatan"));
                mahasiswaList.add(mhs);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewmhs.updateTable(mahasiswaList);
    }

    public void insertMahasiswa() {
        try {
            ModelMahasiswa inputMahasiswa = new ModelMahasiswa();

            String nama = inputmhs.getInputNama();
            String nim = inputmhs.getInputNIM();
            String email = inputmhs.getInputEmail();
            String password = inputmhs.getInputPassword();
            String angkatan = inputmhs.getInputAngkatan();

            if ("".equals(nama) || "".equals(nim) || "".equals(email) || "".equals(password) || "".equals(angkatan)) {
                throw new Exception("Data tidak boleh kosong!");
            }

            inputMahasiswa.setNama(nama);
            inputMahasiswa.setNim(nim);
            inputMahasiswa.setEmail(email);
            inputMahasiswa.setPassword(password);
            inputMahasiswa.setAngkatan(angkatan);

            daoMahasiswa.insert(inputMahasiswa);

            JOptionPane.showMessageDialog(null, "Data Mahasiswa telah ditambahkan");

            inputmhs.dispose();
            new ViewDataMahasiswa(username);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }

    public void editMahasiswa(int id) {
        try {
            ModelMahasiswa editMahasiswa = new ModelMahasiswa();

            String nama = editmhs.getInputNama();
            String nim = editmhs.getInputNIM();
            String email = editmhs.getInputEmail();
            String password = editmhs.getInputPassword();
            String angkatan = editmhs.getInputAngkatan();

            if ("".equals(nama) || "".equals(nim) || "".equals(email) || "".equals(password) || "".equals(angkatan)) {
                throw new Exception("Data tidak boleh kosong!");
            }

            editMahasiswa.setId(id);
            editMahasiswa.setNama(nama);
            editMahasiswa.setNim(nim);
            editMahasiswa.setEmail(email);
            editMahasiswa.setPassword(password);
            editMahasiswa.setAngkatan(angkatan);

            daoMahasiswa.update(editMahasiswa);

            JOptionPane.showMessageDialog(null, "Data Mahasiswa Berhasil diedit");

            editmhs.dispose();
            new ViewDataMahasiswa(username);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void deleteMahasiswa(Integer baris) {
        Integer id = (int) viewmhs.getTable().getValueAt(baris, 0);
        String nama = viewmhs.getTable().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Mahasiswa",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            daoMahasiswa.delete(id);

            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            showAllMahasiswa();
        }
    }

    public void searchMahasiswa(String keyword) {
        List<ModelMahasiswa> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM mahasiswa WHERE nama LIKE ? OR nim LIKE ?";
            PreparedStatement statement;
            statement = Connector.Koneksi().prepareStatement(query);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ModelMahasiswa mhs = new ModelMahasiswa();
                mhs.setId(rs.getInt("id"));
                mhs.setNama(rs.getString("nama"));
                mhs.setNim(rs.getString("nim"));
                mhs.setEmail(rs.getString("email"));
                mhs.setPassword(rs.getString("password"));
                mhs.setAngkatan(rs.getString("angkatan"));
                result.add(mhs);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewmhs.updateTable(result);
    }
}
