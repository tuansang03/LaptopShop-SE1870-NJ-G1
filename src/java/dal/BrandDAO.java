/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Brand;
import model.Category;
/**
 *
 * @author ADMIN
 */
public class BrandDAO extends DBContext{
     public ArrayList<Brand> getAllBrand(){
        ArrayList<Brand> bList=new ArrayList<>();
        String sql="select * from Brand";
        try {
            PreparedStatement pre=connection.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                Brand b=new Brand();
                b.setId(rs.getInt(1));
                b.setName(rs.getString(2));
                bList.add(b);
            }
            return bList;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
