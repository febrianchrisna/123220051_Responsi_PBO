package Model;

import java.sql.*;

public class Connector {
    /* 
        Menyimpan informasi database ke dalam sebuah variabel.
        Pada contoh ini kita menggunakan database bernama "upnvy".
     */
    private static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private static String nama_db = "university";
    private static String url_db = "jdbc:mysql://localhost:3306/" + nama_db;
    private static String username_db = "root";
    private static String password_db = "";

    static Connection conn;
    
    // Mencoba menghubungkan program kita dengan ke database MySQL.
    public static Connection Koneksi() {
        try {
            // 1. Register driver yang akan dipakai
            Class.forName(jdbc_driver);
            
            // 2. Buat koneksi ke database
            conn = DriverManager.getConnection(url_db, username_db, password_db);

            // 3. Menampilkan pesan "Connection Success" jika berhasil terhubung ke database.
            System.out.println("MySQL Connected");
        } catch (ClassNotFoundException | SQLException exception) {
            // Menampilkan pesan error ketika MySQL gagal terhubung.
            System.out.println("Connection Failed: " + exception.getLocalizedMessage());
        }
        return conn;
    }
    
    
//    // Method untuk mengambil data mahasiswa
//    public List<ModelMahasiswa> getAll() {
//        List<ModelMahasiswa> listMahasiswa = null;
//
//        try {
//            /* 
//              Membuat sebuah variabel bernama "listMahasiswa".
//              Variabel ini memiliki tipe data List karena berfungsi untuk menyimpan banyak data
//              Variabel ini nantinya akan digunakan untuk menyimpan daftar mahasiswa
//              hasil query dari database.
//            */
//            listMahasiswa = new ArrayList<>();
//            
//            // Membuat objek statement yang digunakan untuk melakukan query.
//            Statement statement = conn.createStatement();
//            
//            /* 
//                Menyimpan query database ke dalam varibel "query".
//                Dalam hal ini, kita akan mengambil seluruh data mahasiswa pada tabel "mahasiswa".
//            */
//            String query = "SELECT * FROM mahasiswa;";
//            
//             // Mengeksekusi query dan menyimpannya ke dalam variabel "resultSet".
//            ResultSet resultSet = statement.executeQuery(query);
//            
//            /* 
//                Karena hasil query memiliki tipe data List, supaya dapat mencetak semua data mahasiswa,
//                Kita perlu melakukan looping (perulangan) untuk mencetak tiap-tiap elemen.
//            */
//            while (resultSet.next()) {
//                // Membuat sebuah objek "Mahasiswa" untuk menyimpan data tiap-tiap mahasiswa
//                ModelMahasiswa mhs = new ModelMahasiswa();
//                
//                // Memasukkan hasil query ke objek mahasiswa
//                mhs.id = resultSet.getInt("id");
//                mhs.nama = resultSet.getString("nama");
//                mhs.nim = resultSet.getString("nim");
//                
//                /* 
//                  Menambahkan mahasiswa ke dalam daftar mahasiswa.
//                  Daftar mahasiswa disimpan ke dalam variabel "listMahasiswa"
//                  yang memiliki tipe data List.
//                */
//                listMahasiswa.add(mhs);
//            }
//            
//            // Menutup koneksi untuk menghemat penggunaan memory.
//            statement.close();
//        } catch (SQLException e) {
//            // Menampilkan pesan error ketika gagal mengambil data.
//            System.out.println("Error: " + e.getLocalizedMessage());
//        }
//        return listMahasiswa;
//    }
//    
//    // Method untuk memasukkan suatu data mahasiswa
//    void insert(String nama, String nim){
//        try {
//            // Perintah query disimpan ke dalam variabel "query"
//            String query = "INSERT INTO mahasiswa (nama, nim) VALUES (?, ?);";
//            
//            /* 
//              Memasukkan nama dan nim dari input user ke dalam query untuk 
//              mengisi bagian "?, ?" (dalam hal ini berarti nama dan nim)
//            */
//            PreparedStatement statement;
//            statement = conn.prepareStatement(query);
//            statement.setString(1, nama);
//            statement.setString(2, nim);
//            
//            // Menjalankan query untuk memasukkan data mahasiswa baru
//            statement.executeUpdate();
//            
//            // Menutup koneksi untuk menghemat penggunaan memory.
//            statement.close();
//        } catch (SQLException e) {
//            // Menampilkan pesan error ketika gagal input data.
//            System.out.println("Input Failed: " + e.getLocalizedMessage());
//        }
//    }
//    
//    // Method untuk mengupdate (mengedit) suatu data mahasiswa
//    public void update(int id, String nama, String nim) {
//        try {
//            // Perintah query disimpan ke dalam variabel "query"
//            String query = "UPDATE mahasiswa SET nama=?, nim=? WHERE id=?;";
//            
//            /* 
//              Memasukkan nama dan nim dari input user 
//              beserta id yang didapat dari data yang mau diubah ke dalam query 
//              untuk mengisi bagian "?".
//            */
//            PreparedStatement statement;
//            statement = conn.prepareStatement(query);
//            statement.setString(1, nama);
//            statement.setString(2, nim);
//            statement.setInt(3, id);
//            
//            // Menjalankan query untuk menghapus data mahasiswa yang dipilih
//            statement.executeUpdate();
//            
//            // Menutup koneksi untuk menghemat penggunaan memory.
//            statement.close();
//        } catch (SQLException e) {
//            // Menampilkan pesan error ketika gagal edit data.
//            System.out.println("update Failed! (" + e.getMessage() + ")");
//        }
//    }
//    
//    // Method untuk menghapus suatu data mahasiswa
//    public void delete(int id) {
//        try {
//            // Perintah query disimpan ke dalam variabel "query"
//            String query = "DELETE FROM mahasiswa WHERE id=?;";
//            
//            /* 
//              Memasukkan id berdasarkan data yang mau dihapus ke dalam query 
//              untuk mengisi bagian "?".
//            */
//            PreparedStatement statement;
//            statement = conn.prepareStatement(query);
//            statement.setInt(1, id);
//            
//            // Menjalankan query untuk menghapus data mahasiswa yang dipilih
//            statement.executeUpdate();
//            
//            // Menutup koneksi untuk menghemat penggunaan memory.
//            statement.close();
//        } catch (SQLException e) {
//            // Menampilkan pesan error ketika gagal hapus data.
//            System.out.println("Delete Failed: " + e.getLocalizedMessage());
//        }
//    }
}
