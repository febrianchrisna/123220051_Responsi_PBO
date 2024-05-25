/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author febri
 */
import View.Dosen.ViewDataDosen;
import View.Mahasiswa.ViewDataMahasiswa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author febri
 */
public class HalamanUtama extends JFrame implements ActionListener {

    JLabel judul = new JLabel();
    JLabel judul2 = new JLabel("Silahkan pilih menu dibawah ini");

    JButton dataDosen = new JButton("Data Dosen");
    JButton dataMahasiswa = new JButton("Data Mahasiswa");
    JButton pilihLogout = new JButton("Logout");
    
    String username;

    public HalamanUtama(String username) {
        this.username = username;
        setVisible(true);
        setSize(480, 520);
        setTitle("Halaman Utama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        add(judul);
        judul.setText("Selamat Datang, " + username);
        judul.setBounds(50, 20, 300, 30);
        judul.setFont(judul.getFont().deriveFont(20f));

        add(judul2);
        judul2.setBounds(50, 40, 400, 30);

        add(dataDosen);
        dataDosen.setBounds(150, 140, 150, 35);
        dataDosen.addActionListener(this);

        add(dataMahasiswa);
        dataMahasiswa.setBounds(150, 200, 150, 35);
        dataMahasiswa.addActionListener(this);

        
        add(pilihLogout);
        pilihLogout.setBounds(150, 260, 150, 35);
        pilihLogout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == dataMahasiswa){
            new ViewDataMahasiswa(username);
            this.dispose();
        }else if(e.getSource() == dataDosen){
            new ViewDataDosen(username);
            this.dispose();
        }else if(e.getSource() == pilihLogout){
            exit(0);
        }
    }

}
