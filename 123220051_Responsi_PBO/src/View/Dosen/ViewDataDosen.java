package View.Dosen;

import Controller.ControllerDosen;
import Main.HalamanUtama;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Model.Dosen.ModelDosen;

public class ViewDataDosen extends JFrame {

    Integer baris;

    ControllerDosen controller;

    List<ModelDosen> daftarDosen = new ArrayList<>();

    JLabel header = new JLabel("Selamat Datang!");
    JButton tombolTambah = new JButton("Tambah Dosen");
    JButton tombolEdit = new JButton("Edit Dosen");
    JButton tombolHapus = new JButton("Hapus Dosen");
    JButton tombolKembali = new JButton("Kembali");
    JTextField searchField = new JTextField();
    JButton searchButton = new JButton("Cari");

    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    String namaKolom[] = {"ID", "Nama", "No Hp", "Email"};
    String username;

    public ViewDataDosen(String username) {
        this.username = username;
        
        tableModel = new DefaultTableModel(namaKolom, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        setTitle("Daftar Dosen");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(552, 800);
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
        searchField.setBounds(20, 36, 400, 30);
        searchButton.setBounds(430, 36, 100, 30);
        scrollPane.setBounds(20, 80, 512, 320);
        tombolTambah.setBounds(20, 420, 512, 40);
        tombolEdit.setBounds(20, 464, 512, 40);
        tombolHapus.setBounds(20, 508, 512, 40);
        tombolKembali.setBounds(20, 552, 512, 40);

        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);

        controller = new ControllerDosen(this);
        controller.showAllDosen();

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
                new InputDataDosen(username);
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    ModelDosen pilih = new ModelDosen();
                    Integer id = (int) table.getValueAt(baris, 0);
                    String nama = table.getValueAt(baris, 1).toString();
                    String noHp = table.getValueAt(baris, 2).toString();
                    String email = table.getValueAt(baris, 3).toString();
                    pilih.setId(id);
                    pilih.setNama(nama);
                    pilih.setNoHp(noHp);
                    pilih.setEmail(email);
                    dispose();
                    new EditDataDosen(pilih, username);
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });

        tombolHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    controller.deleteDosen(baris);
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
                controller.searchDosen(keyword);
            }
        });
    }

    public JTable getTable() {
        return table;
    }

    public void updateTable(List<ModelDosen> dosenList) {
        tableModel.setRowCount(0);
        for (ModelDosen dosen : dosenList) {
            tableModel.addRow(new Object[]{dosen.getId(), dosen.getNama(), dosen.getNoHp(), dosen.getEmail()});
        }
    }
}
