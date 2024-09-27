/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Brand;
import model.Color;
/**
 *
 * @author ADMIN
 */
public class ColorDAO extends DBContext {
    public ArrayList<Color> getAllColor(){
        ArrayList<Color> cList=new ArrayList<>();
        String sql="select * from Color";
        try {
            PreparedStatement pre=connection.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                Color c =new Color();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                cList.add(c);
            }
            return cList;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
