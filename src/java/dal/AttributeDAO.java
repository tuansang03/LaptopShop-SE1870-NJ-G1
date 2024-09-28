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
import model.Attribute;
import model.Brand;
import model.ProductAttribute;

/**
 *
 * @author ADMIN
 */
public class AttributeDAO extends DBContext {
         public ArrayList<Attribute> getAllAttribute(){
        ArrayList<Attribute> attList=new ArrayList<>();
        String sql="select * from Attribute";
        try {
            PreparedStatement pre=connection.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                Attribute att =new Attribute();
                att.setId(rs.getInt(1));
                att.setName(rs.getString(2));
                attList.add(att);
            }
            return attList;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
         

}
