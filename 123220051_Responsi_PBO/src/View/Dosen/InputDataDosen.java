package View.Dosen;

import Controller.ControllerDosen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InputDataDosen extends JFrame {
    
    ControllerDosen controller;

    JLabel header = new JLabel("Input Dosen");
    JLabel labelInputNama = new JLabel("Nama");
    JLabel labelInputNoHp = new JLabel("No Hp");
    JLabel labelInputEmail = new JLabel("Email");
    JTextField inputNama = new JTextField();
    JTextField inputNoHp = new JTextField();
    JTextField inputEmail = new JTextField();
    JButton tombolTambah = new JButton("Tambah Dosen");
    JButton tombolKembali = new JButton("Kembali");
    String username;

    public InputDataDosen(String username) {
        this.username = username;
        setTitle("Daftar Dosen");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 550);
        setLocationRelativeTo(null);
        setLayout(null);

        add(header);
        add(labelInputNama);
        add(labelInputNoHp);
        add(labelInputEmail);
        add(inputNama);
        add(inputNoHp);
        add(inputEmail);
        add(tombolTambah);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        labelInputNama.setBounds(20, 32, 440, 24);
        inputNama.setBounds(18, 56, 440, 36);
        labelInputNoHp.setBounds(20, 96, 440, 24);
        inputNoHp.setBounds(18, 120, 440, 36);
        labelInputEmail.setBounds(20, 160, 440, 24);
        inputEmail.setBounds(18, 184, 440, 36);
        tombolKembali.setBounds(20, 224, 215, 40);
        tombolTambah.setBounds(240, 224, 215, 40);
        
        controller = new ControllerDosen(this);

        
        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewDataDosen(username);
            }
        });

        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              controller.insertDosen();
            }
        });
    }

    public String getInputNama() {
        return inputNama.getText();
    }

    public String getInputNoHp() {
        return inputNoHp.getText();
    }

    public String getInputEmail() {
        return inputEmail.getText();
    }


    
    
}
