package View.Mahasiswa;

import Controller.ControllerMahasiswa;
import Main.HalamanUtama;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Model.Mahasiswa.ModelMahasiswa;

public class ViewDataMahasiswa extends JFrame {

    Integer baris;

    ControllerMahasiswa controller;

    List<ModelMahasiswa> daftarMahasiswa = new ArrayList<>();

    JLabel header = new JLabel("Selamat Datang!");
    JTextField searchField = new JTextField();
    JButton searchButton = new JButton("Cari");
    JButton tombolTambah = new JButton("Tambah Mahasiswa");
    JButton tombolEdit = new JButton("Edit Mahasiswa");
    JButton tombolHapus = new JButton("Hapus Mahasiswa");
    JButton tombolKembali = new JButton("Kembali");

    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    String namaKolom[] = {"ID", "Nama", "NIM", "Email", "Password", "Angkatan"};
    String username;

    public ViewDataMahasiswa(String username) {
        this.username = username;
        tableModel = new DefaultTableModel(namaKolom, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        setTitle("Daftar Mahasiswa");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocationRelativeTo(null);
        setLayout(null);

        add(header);
        add(searchField);
        add(searchButton);
        add(scrollPane);
        add(tombolTambah);
        add(tombolEdit);
        add(tombolHapus);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        searchField.setBounds(20, 40, 400, 30);
        searchButton.setBounds(430, 40, 100, 30);
        scrollPane.setBounds(20, 80, 540, 400);
        tombolTambah.setBounds(20, 500, 540, 40);
        tombolEdit.setBounds(20, 550, 540, 40);
        tombolHapus.setBounds(20, 600, 540, 40);
        tombolKembali.setBounds(20, 650, 540, 40);

        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);

        controller = new ControllerMahasiswa(this);
        controller.showAllMahasiswa();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                baris = table.getSelectedRow();
            }
        });

        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new InputDataMahasiswa(username);
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    ModelMahasiswa pilih = new ModelMahasiswa();

                    Integer id = (int) table.getValueAt(baris, 0);
                    String nama = table.getValueAt(baris, 1).toString();
                    String nim = table.getValueAt(baris, 2).toString();
                    String email = table.getValueAt(baris, 3).toString();
                    String password = table.getValueAt(baris, 4).toString();
                    String angkatan = table.getValueAt(baris, 5).toString();

                    pilih.setId(id);
                    pilih.setNama(nama);
                    pilih.setNim(nim);
                    pilih.setEmail(email);
                    pilih.setPassword(password);
                    pilih.setAngkatan(angkatan);

                    dispose();
                    new EditDataMahasiswa(pilih, username);
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });

        tombolHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    controller.deleteMahasiswa(baris);
                    baris = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HalamanUtama(username);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                controller.searchMahasiswa(keyword);
            }
        });
    }

    public JTable getTable() {
        return table;
    }

    public void updateTable(List<ModelMahasiswa> mahasiswaList) {
        tableModel.setRowCount(0);
        for (ModelMahasiswa mhs : mahasiswaList) {
            tableModel.addRow(new Object[]{mhs.getId(), mhs.getNama(), mhs.getNim(), mhs.getEmail(), mhs.getPassword(), mhs.getAngkatan()});
        }
    }
}
