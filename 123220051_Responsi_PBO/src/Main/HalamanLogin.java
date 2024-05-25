/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Model.Connector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HalamanLogin extends JFrame implements ActionListener {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    JLabel judul = new JLabel("Selamat Datang!");
    JLabel judul2 = new JLabel("Silahkan Login terlebih dahulu");
    JLabel labelUsername = new JLabel("Username");
    JTextField inputUsername = new JTextField();
    JLabel labelPw = new JLabel("Password");
    JPasswordField inputPw = new JPasswordField();

    JButton tombolLogin = new JButton("Login");

    public HalamanLogin() {
        conn = Connector.Koneksi();
        setVisible(true);
        setSize(480, 520);
        setTitle("Halaman Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        add(judul);
        judul.setBounds(30, 10, 200, 40);
        judul.setFont(judul.getFont().deriveFont(20f));

        add(judul2);
        judul2.setBounds(30, 35, 280, 40);

        add(labelUsername);
        labelUsername.setBounds(30, 70, 100, 40);

        add(inputUsername);
        inputUsername.setBounds(30, 105, 300, 25);

        add(labelPw);
        labelPw.setBounds(30, 135, 100, 40);

        add(inputPw);
        inputPw.setBounds(30, 170, 300, 25);

        add(tombolLogin);
        tombolLogin.setBounds(30, 220, 300, 28);
        tombolLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String username = inputUsername.getText();
            String password = new String(inputPw.getPassword());

            // Check if fields are empty
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username dan Password tidak boleh kosong!");
                return;
            }

            // Prepare SQL query
            pstmt = conn.prepareStatement("SELECT * FROM mahasiswa WHERE email = ? AND password = ?");

            // Set parameters
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // Execute query
            rs = pstmt.executeQuery();

            // Check if user exists
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login berhasil!");
                // Redirect to menu
                new HalamanUtama(username);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Username atau Password Salah!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ralat sistem: " + ex.getMessage());
        } finally {
            try {
                // Close resources
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ralat menutup sumber daya: " + ex.getMessage());
            }
        }
    }
}
