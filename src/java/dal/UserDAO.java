/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;
/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext {
    public void insertUser(User user){
        String sql="INSERT INTO [dbo].[User]\n" +
"           ([Username]\n" +
"           ,[Password]\n" +
"           ,[Fullname]\n" +
"           \n" +
"           ,[Email]\n" +
"           \n" +
"           ,[RoleId]\n" +
"           )\n" +
"     VALUES\n" +
"           (?,?,?,?,3)";
        try {
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setString(1, user.getUserName());
            pre.setString(2, user.getPassword());
            pre.setString(3, user.getFullName());
            pre.setString(4, user.getEmail());
            pre.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isMailDuplicate(User user){
        String sql="SELECT COUNT(*) FROM [User] WHERE email = ?";
        try {
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setString(1, user.getEmail());
            ResultSet rs= pre.executeQuery();
            if(rs.next()){
                int count =rs.getInt(1);
                if(count==0){
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean isUserDuplicate(User user){
        String sql="SELECT COUNT(*) FROM [User] WHERE Username =?";
        try {
            PreparedStatement pre =connection.prepareStatement(sql);
            pre.setString(1, user.getUserName());
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
                int count=rs.getInt(1);
                if(count==0){
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public User UserLogin(String username,String password){
        String sql="select * from [User] where Username=? and Password=?";
        try {
            PreparedStatement pre =connection.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
                User u=new User();
                u.setId(rs.getInt(1));
                u.setUserName(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setFullName(rs.getString(4));
                u.setEmail(rs.getString(6));
                Role role =new Role();
                role.setId(rs.getInt(8));
                u.setRole(role);
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void main(String[] args) {
        User user=new User();
        user.setEmail("trantienminh@gmail.com");
        user.setFullName("trantienminh");
        UserDAO dao=new UserDAO();
       User u= dao.UserLogin("huhu", "2cb1f284188f4856aaf4f5a371cbd9ff674171799981ca3046e852587f38e616");
        System.out.println(u);
    }
}
