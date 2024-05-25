/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Connector;
import Model.Dosen.InterfaceDAODosen;
import Model.Dosen.ModelDosen;
import Model.Dosen.ModelTableDosen;
import View.Dosen.InputDataDosen;
import java.util.List;
import javax.swing.JOptionPane;
import View.Dosen.EditDataDosen;
import View.Dosen.ViewDataDosen;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author febri
 */
public class ControllerDosen {

    InputDataDosen inputdos;
    EditDataDosen editdos;
    ViewDataDosen viewdos;
    String username;

    InterfaceDAODosen daoDosen;

    List<ModelDosen> daftarDosen;

    public ControllerDosen(InputDataDosen inputdos) {
        this.inputdos = inputdos;
        this.daoDosen = new Model.Dosen.DAODosen();
    }

    public ControllerDosen(EditDataDosen editdos) {
        this.editdos = editdos;
        this.daoDosen = new Model.Dosen.DAODosen();
    }

    public ControllerDosen(ViewDataDosen viewdos) {
        this.viewdos = viewdos;
        this.daoDosen = new Model.Dosen.DAODosen();
    }

    public void showAllDosen() {
        List<ModelDosen> dosenList = new ArrayList<>();
        try {
            String query = "SELECT * FROM dosen";
            PreparedStatement statement;
            statement = Connector.Koneksi().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ModelDosen dosen = new ModelDosen();
                dosen.setId(rs.getInt("id"));
                dosen.setNama(rs.getString("nama"));
                dosen.setNoHp(rs.getString("no_hp"));
                dosen.setEmail(rs.getString("email"));
                dosenList.add(dosen);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewdos.updateTable(dosenList);
    }

    public void insertDosen() {
        try {
            ModelDosen inputDosen = new ModelDosen();

            String nama = inputdos.getInputNama();
            String noHp = inputdos.getInputNoHp();
            String email = inputdos.getInputEmail();

            if ("".equals(nama) || "".equals(noHp) || "".equals(email)) {
                throw new Exception("Data tidak boleh kosong!");
            }

            inputDosen.setNama(nama);
            inputDosen.setNoHp(noHp);
            inputDosen.setEmail(email);

            daoDosen.insert(inputDosen);

            JOptionPane.showMessageDialog(null, "Data Dosen telah ditambahkan");

            inputdos.dispose();
            new ViewDataDosen(username);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }

    public void editDosen(int id) {
        try {
            ModelDosen editDosen = new ModelDosen();

            String nama = editdos.getInputNama();
            String noHp = editdos.getInputNoHp();
            String email = editdos.getInputEmail();

            if ("".equals(nama) || "".equals(noHp) || "".equals(email)) {
                throw new Exception("Data tidak boleh kosong!");
            }

            editDosen.setId(id);
            editDosen.setNama(nama);
            editDosen.setNoHp(noHp);
            editDosen.setEmail(email);

            daoDosen.update(editDosen);

            JOptionPane.showMessageDialog(null, "Data Dosen Berhasil diedit");

            editdos.dispose();
            new ViewDataDosen(username);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void deleteDosen(Integer baris) {
        Integer id = (int) viewdos.getTable().getValueAt(baris, 0);
        String nama = viewdos.getTable().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Dosen",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            daoDosen.delete(id);

            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            showAllDosen();
        }
    }

    public void searchDosen(String keyword) {
        List<ModelDosen> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM dosen WHERE nama LIKE ? OR no_hp LIKE ? OR email LIKE ?";
            PreparedStatement statement;
            statement = Connector.Koneksi().prepareStatement(query);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ModelDosen dosen = new ModelDosen();
                dosen.setId(rs.getInt("id"));
                dosen.setNama(rs.getString("nama"));
                dosen.setNoHp(rs.getString("no_hp"));
                dosen.setEmail(rs.getString("email"));
                result.add(dosen);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewdos.updateTable(result);
    }

}
