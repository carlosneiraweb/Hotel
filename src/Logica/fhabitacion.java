/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import Datos.vhabitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlos
 */
public class fhabitacion {
 
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSql = "";
    public Integer totalregistros;
    
    
    public DefaultTableModel mostrar (String buscar){
      DefaultTableModel modelo;
      
      String [] titulos = {"ID", "Numero","Piso", "Descripcion", "Caracteristicas", "Precio", "Estado", "Tipo habitacion"};
      String [] registro = new String [8];
      totalregistros = 0;
      modelo = new DefaultTableModel(null,titulos);
        
      
      sSql = "select * habitacion where piso like '%"+buscar+"%' order by idhabitacion;";
    
        try{

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSql);

            while(rs.next()){

                registro[0] = rs.getString("idhabitacion");
                registro[0] = rs.getString("numero");
                registro[0] = rs.getString("piso");
                registro[0] = rs.getString("descripcion");
                registro[0] = rs.getString("caracteristicas");
                registro[0] = rs.getString("precio_diario");
                registro[0] = rs.getString("estado");
                registro[0] = rs.getString("tipo_habitacion");

                    totalregistros = totalregistros+1;
                    modelo.addRow(registro);    

            }

            return modelo;

        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    
    }
    
    
    public boolean insertar (vhabitacion dts){
        sSql = "Insert into habitacion (numero, piso, descripcion,caracteristicas,precio_diario,estado,tipo_habitacion) values (?,?,?,?,?,?,?);";
        try {
         
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_habitacion());
             
                int n = pst.executeUpdate();
                    
            return n != 0;
             
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
        
       
    }
    
    public boolean editar (vhabitacion dts){
        sSql = "update habitacion set numero =?, piso=?,"
                + "descripcion=?, caracteristicas=?,precio_diario=?,estado=?,tipo_habitacion=?"
                + "where idhabitacion=?;";

        try {
            
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_habitacion());
            pst.setInt(7, dts.getIdhabitacion());
             
                int n = pst.executeUpdate();
                    
            return n != 0;
             
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        
        return true;
    }
    
    
    public boolean eliminar(vhabitacion dts){
        
        sSql= "Delete from habitacion where idhabitacion = ?;";
        
        try {
            
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setInt(1, dts.getIdhabitacion());
           
                int n = pst.executeUpdate();
                    
            return n != 0;
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
 
        
    }
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 //   
}
