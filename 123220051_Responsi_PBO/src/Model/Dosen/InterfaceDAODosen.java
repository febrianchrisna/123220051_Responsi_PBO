/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Dosen;

import java.util.List;

/**
 *
 * @author febri
 */
public interface InterfaceDAODosen {
    public void insert(ModelDosen Dosen);
    
    public void update(ModelDosen Dosen);
    
    public void delete(int id);
    
    public List<ModelDosen> getAll();
}
