/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Mahasiswa;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author febri
 */
public class ModelTableMahasiswa extends AbstractTableModel {
    
    List<ModelMahasiswa> daftarMahasiswa;
    
    String kolom[] = {"ID","Nama","NIM","Email","Password","Angkatan"};
    
    public ModelTableMahasiswa(List<ModelMahasiswa> daftarMahasiswa){
        this.daftarMahasiswa = daftarMahasiswa;
    }

    @Override
    public int getRowCount() {
        return daftarMahasiswa.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return daftarMahasiswa.get(rowIndex).getId();
            case 1:
                return daftarMahasiswa.get(rowIndex).getNama();
            case 2:
                return daftarMahasiswa.get(rowIndex).getNim();
            case 3:
                return daftarMahasiswa.get(rowIndex).getEmail();
            case 4:
                return daftarMahasiswa.get(rowIndex).getPassword();
            case 5:
                return daftarMahasiswa.get(rowIndex).getAngkatan();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return kolom[columnIndex];
    }
    
}
