/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Brand;
import model.Category;
import model.Color;
import model.Configuration;
import model.Post;
import model.Product;
import model.ProductDetail;
import model.Role;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext {

    public void insertUser(User user) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([Username]\n"
                + "           ,[Password]\n"
                + "           ,[Fullname]\n"
                + "           \n"
                + "           ,[Email]\n"
                + "           \n"
                + "           ,[RoleId]\n"
                + "           )\n"
                + "     VALUES\n"
                + "           (?,?,?,?,3)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user.getUserName());
            pre.setString(2, user.getPassword());
            pre.setString(3, user.getFullName());
            pre.setString(4, user.getEmail());
            pre.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isMailDuplicate(User user) {
        String sql = "SELECT COUNT(*) FROM [User] WHERE email = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user.getEmail());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean isUserDuplicate(User user) {
        String sql = "SELECT COUNT(*) FROM [User] WHERE Username =?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user.getUserName());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public User UserLogin(String username, String password) {
        String sql = "select * from [User] where Username=? and Password=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUserName(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setFullName(rs.getString(4));
                u.setEmail(rs.getString(6));
                Role role = new Role();
                role.setId(rs.getInt(8));
                u.setRole(role);
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    ------------------------------EDIT NGAY 19/09/2024 -DOANKD -----------------------------------------------
    
       public Product getProductByIdD(int id) {
        String sql = "select * from Product where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                
                Category c = getCategoryByIdD(rs.getInt("CategoryId"));
                Brand b = getBrandByIdD(rs.getInt("BrandId"));
                Product product = new Product(rs.getInt("Id"),
                        b,
                        c,
                        rs.getString("Name"),
                        rs.getString("Status"));
                return product;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    
    public Category getCategoryByIdD(int id) {
        String sql = "select * from Category where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category newCategory = new Category(rs.getInt("Id"),
                        rs.getString("Name"));
                return newCategory;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Brand getBrandByIdD(int id) {
        String sql = "select * from Brand where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Brand newBrand = new Brand(rs.getInt("Id"),
                        rs.getString("Name"));
                return newBrand;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getProductListD() {

        List<Product> list = new ArrayList<>();
        String sql = "SELECT [Id]\n"
                + "      ,[BrandId]\n"
                + "      ,[CategoryId]\n"
                + "      ,[Name]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[Product]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setId(rs.getInt("Id"));
                Category c = getCategoryByIdD(rs.getInt("CategoryId"));
                Brand b = getBrandByIdD(rs.getInt("BrandId"));
                newProduct.setBrand(b);
                newProduct.setCategory(c);
                newProduct.setName(rs.getString("Name"));
                newProduct.setStatus(rs.getString("Status"));
                list.add(newProduct);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Role getRoleByIdD(int id) {
        String sql = "select * from Role where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Role newRole = new Role(rs.getInt("Id"),
                        rs.getString("Name"));
                return newRole;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
        public Color getColorById(int id) {
        String sql = "select * from Color where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Color newColor = new Color(rs.getInt("Id"),
                        rs.getString("Name"));
                return newColor;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getUserByIdD(int id) {
        String sql = "select * from User where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Role role = getRoleByIdD(rs.getInt("RoleId"));
                User u = new User(rs.getInt("id"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Fullname"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        role,
                        rs.getString("status"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Post> getPostListD() {

        List<Post> list = new ArrayList<>();
        String sql = "SELECT [Id]\n"
                + "      ,[UserId]\n"
                + "      ,[BrandId]\n"
                + "      ,[CategoryId]\n"
                + "      ,[Title]\n"
                + "      ,[ShortContent]\n"
                + "      ,[FullContent]\n"
                + "      ,[Thumbnail]\n"
                + "      ,[PublishDate]\n"
                + "  FROM [dbo].[Post]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt("Id"));
                User u = getUserByIdD(rs.getInt("UserId"));
                p.setUser(u);
                Brand b = getBrandByIdD(rs.getInt("BrandId"));
                p.setBrand(b);
                Category c = getCategoryByIdD(rs.getInt("CategoryId"));
                p.setCategory(c);
                p.setTittle(rs.getString("Title"));
                p.setShortContent(rs.getString("ShortContent"));
                p.setFullContent(rs.getString("FullContent"));
                p.setThumbnail(rs.getString("Thumbnail"));
                p.setPublishDate(rs.getDate("PublishDate"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
        public Configuration getConfigurationById(int id) {
        String sql = "select * from Configuration where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Configuration c = new Configuration(rs.getInt("Id"),
                        rs.getString("Name"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<ProductDetail> getListProductDetailD() {
        List<ProductDetail> list = new ArrayList<>();
        String sql = "SELECT [Id]\n"
                + "      ,[ProductId]\n"
                + "      ,[ColorId]\n"
                + "      ,[ConfigurationId]\n"
                + "      ,[Price]\n"
                + "      ,[Quantity]\n"
                + "      ,[ShortDescription]\n"
                + "      ,[Description]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[ProductDetail]";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductDetail newProduct = new ProductDetail();
                newProduct.setId(rs.getInt("Id"));
                Product product = getProductByIdD(rs.getInt("ProductId"));
                newProduct.setProduct(product);
                Color c = getColorById(rs.getInt("ColorId"));
                newProduct.setColor(c);
                Configuration c1 = getConfigurationById(rs.getInt("ConfigurationId"));
                newProduct.setConfiguration(c1);
                newProduct.setPrice(rs.getInt("Price"));
                newProduct.setPrice(rs.getInt("Quantity"));
                newProduct.setShortDescription(rs.getString("ShortDescription"));
                newProduct.setDescription(rs.getString("Description"));
                newProduct.setStatus(rs.getString("Status"));
                
                list.add(newProduct);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        
        UserDAO dao = new UserDAO();
        List<ProductDetail> list = dao.getListProductDetailD();
        for (int i = 0; i < list.size(); i++) {
            ProductDetail get = list.get(i);
            System.out.println(get);
        }
    }
    //----------------------------------------------------------------------------------
}
