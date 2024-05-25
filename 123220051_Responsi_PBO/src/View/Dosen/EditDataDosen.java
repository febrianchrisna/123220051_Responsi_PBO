package View.Dosen;

import View.Dosen.*;
import Controller.ControllerDosen;
import Model.Dosen.ModelDosen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EditDataDosen extends JFrame {
    
    ControllerDosen controller;
    
    JLabel header = new JLabel("Edit Dosen");
    JLabel labelInputNama = new JLabel("Nama");
    JLabel labelInputNoHp = new JLabel("No Hp");
    JLabel labelInputEmail = new JLabel("Email");
    JTextField inputNama = new JTextField();
    JTextField inputNoHp = new JTextField();
    JTextField inputEmail = new JTextField();
    JButton tombolEdit = new JButton("Edit Dosen");
    JButton tombolKembali = new JButton("Kembali");
    String username;

    public EditDataDosen(ModelDosen dosen, String username) {
        this.username = username;
        setTitle("Edit Dosen");
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
        add(tombolEdit);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        labelInputNama.setBounds(20, 32, 440, 24);
        inputNama.setBounds(18, 56, 440, 36);
        labelInputNoHp.setBounds(20, 96, 440, 24);
        inputNoHp.setBounds(18, 120, 440, 36);
        labelInputEmail.setBounds(20, 160, 440, 24);
        inputEmail.setBounds(18, 184, 440, 36);
        tombolKembali.setBounds(20, 224, 215, 40);
        tombolEdit.setBounds(240, 224, 215, 40);
        
        inputNama.setText(dosen.getNama());
        inputNoHp.setText(dosen.getNoHp());
        inputEmail.setText(dosen.getEmail());

        controller = new ControllerDosen(this);
       
        
        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewDataDosen(username);
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               controller.editDosen(dosen.getId());
               new ViewDataDosen(username);
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
