/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.beans.Statement;
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
import model.Image;
import static model.PasswordUtil.hashPassword;
import model.Post;
import model.Product;
import model.ProductDetail;
import model.Role;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext {

    public void updatePassword(String username, String newPassword) {
        String sql = "UPDATE [User] SET password = ? WHERE userName = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, newPassword); // Gán mật khẩu mới (nên mã hóa trước khi lưu)
            pre.setString(2, username); // Gán tên người dùng

            pre.executeUpdate(); // Thực thi câu lệnh UPDATE

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkPassword(String username, String password) {
        String sql = "SELECT COUNT(Id) FROM [User] WHERE Username = ? AND [Password] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password); // Bạn nên mã hóa mật khẩu ở đây trước khi so sánh!

            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                // Nếu có ít nhất 1 dòng được trả về, mật khẩu là đúng
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // Nếu không tìm thấy hoặc có lỗi xảy ra
    }

    public boolean updateUser(User u) {
        String sql = "UPDATE [User] SET fullName = ?, email = ? WHERE id = ?";
        boolean isUpdated = false;

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, u.getFullName()); // Cập nhật fullName
            pre.setString(2, u.getEmail());     // Cập nhật email
            pre.setInt(3, u.getId());  // Sử dụng username làm điều kiện để cập nhật

            int rowsAffected = pre.executeUpdate();
            isUpdated = (rowsAffected > 0); // Nếu có ít nhất 1 hàng bị ảnh hưởng thì cập nhật thành công
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isUpdated; // Trả về kết quả cập nhật
    }
        public boolean updateUser2(User u) {
        String sql = "UPDATE [User] SET fullName = ?, email = ?, Username = ? WHERE id = ?";
        boolean isUpdated = false;

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, u.getFullName()); // Cập nhật fullName
            pre.setString(2, u.getEmail());     // Cập nhật email
            pre.setString(3, u.getUserName());     // Cập nhật email
            pre.setInt(4, u.getId());  // Sử dụng username làm điều kiện để cập nhật

            int rowsAffected = pre.executeUpdate();
            isUpdated = (rowsAffected > 0); // Nếu có ít nhất 1 hàng bị ảnh hưởng thì cập nhật thành công
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isUpdated; // Trả về kết quả cập nhật
    }

    public void insertUser(User user) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([Username]\n"
                + "          ,[Password]\n"
                + "          ,[Fullname]\n"
                + "           \n"
                + "           ,[Email]\n"
                + "           \n"
                + "           ,[RoleId],\n"
                + "		   [Status]\n"
                + "           )\n"
                + "    VALUES\n"
                + "          (?,?,?,?,3,'active')";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user.getUserName());
            pre.setString(2, user.getPassword());
            pre.setString(3, user.getFullName());
            pre.setString(4, user.getEmail());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public int insertUser2(User user) {
    int userId = -1;  // Biến lưu userId mới
     String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([Username]\n"
                + "          ,[Password]\n"
                + "          ,[Fullname]\n"
                + "           \n"
                + "           ,[Email]\n"
                + "           \n"
                + "           ,[RoleId],\n"
                + "		   [Status]\n"
                + "           )\n"
                + "    VALUES\n"
                + "          (?,?,?,?,3,'active')";
    
    try {
        // Sử dụng RETURN_GENERATED_KEYS để lấy userId vừa thêm
        PreparedStatement pre = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        // Thiết lập các giá trị cho câu lệnh SQL
        pre.setString(1, user.getUserName());
            pre.setString(2, user.getPassword());
            pre.setString(3, user.getFullName());
            pre.setString(4, user.getEmail());
        
        // Thực thi câu lệnh
        pre.executeUpdate();
        
        // Lấy userId vừa được thêm
        ResultSet rs = pre.getGeneratedKeys();
        if (rs.next()) {
            userId = rs.getInt(1);  // Lấy giá trị khóa chính
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return userId;  // Trả về userId hoặc -1 nếu thất bại
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

    public boolean isMailDuplicate2(String email) {
        String sql = "SELECT COUNT(*) FROM [User] WHERE email = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
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
                u.setEmail(rs.getString(5));
                Role role = new Role();
                role.setId(rs.getInt(6));
                u.setRole(role);
                u.setStatus(rs.getString(7));
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
        String sql = "SELECT * FROM [User] where id = ?";

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
                        rs.getString("Email"),
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
        String sql = "SELECT TOP 6 [Id], [UserId], [BrandId], [CategoryId], [Title], [ShortContent], [FullContent], [Thumbnail], [PublishDate] "
                + "FROM [dbo].[Post] "
                + "ORDER BY [Id] DESC";

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


    public List<Post> getNewestPostListD() {

        List<Post> list = new ArrayList<>();
        String sql = "SELECT TOP 5 [Id], [UserId], [BrandId], [CategoryId], [Title], [ShortContent], [FullContent], [Thumbnail], [PublishDate] "
                + "FROM [dbo].[Post] "
                + "ORDER BY [PublishDate] DESC"; // Sắp xếp theo PublishDate
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

    public List<Post> getAllPostListD() {

        List<Post> list = new ArrayList<>();
        String sql = "SELECT  [Id], [UserId], [BrandId], [CategoryId], [Title], [ShortContent], [FullContent], [Thumbnail], [PublishDate] "
                + "FROM [dbo].[Post] "
                + "ORDER BY [Id] DESC";

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

    public String getImageById(int id) {
        String sql = "select * from Image where ProductDetailId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String c = rs.getString("Image");
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<ProductDetail> getListProductDetailD() {
        List<ProductDetail> list = new ArrayList<>();
        String sql = "SELECT [Id],\n"
                + "      [ProductId],\n"
                + "      [ColorId],\n"
                + "      [ConfigurationId],\n"
                + "      [Price],\n"
                + "      [Quantity],\n"
                + "      [ShortDescription],\n"
                + "      [Description],\n"
                + "      [Status]\n"
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

                // Chỉnh sửa phần này
                newProduct.setPrice(rs.getInt("Price")); // Đặt giá trị cho Price
                newProduct.setQuantity(rs.getInt("Quantity")); // Đặt giá trị cho Quantity

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

    public ProductDetail getProductDetailById(int id) {
        ProductDetail newProduct = null;
        String sql = "SELECT [Id],\n"
                + "      [ProductId],\n"
                + "      [ColorId],\n"
                + "      [ConfigurationId],\n"
                + "      [Price],\n"
                + "      [Quantity],\n"
                + "      [ShortDescription],\n"
                + "      [Description],\n"
                + "      [Status]\n"
                + "  FROM [dbo].[ProductDetail] WHERE [Id] = ?"; // Thêm điều kiện WHERE

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id); // Thiết lập giá trị cho tham số `id`
            ResultSet rs = st.executeQuery();

            // Nếu có kết quả từ ResultSet
            if (rs.next()) {
                newProduct = new ProductDetail();
                newProduct.setId(rs.getInt("Id"));
                Product product = getProductByIdD(rs.getInt("ProductId"));
                newProduct.setProduct(product);
                Color c = getColorById(rs.getInt("ColorId"));
                newProduct.setColor(c);
                Configuration c1 = getConfigurationById(rs.getInt("ConfigurationId"));
                newProduct.setConfiguration(c1);

                // Đặt giá trị cho Price và Quantity
                newProduct.setPrice(rs.getInt("Price"));
                newProduct.setQuantity(rs.getInt("Quantity"));
                // Đặt giá trị cho Price và Quantity
                newProduct.setPrice(rs.getInt("Price"));
                newProduct.setQuantity(rs.getInt("Quantity"));

            newProduct.setShortDescription(rs.getString("ShortDescription"));
            newProduct.setDescription(rs.getString("Description"));
            newProduct.setStatus(rs.getString("Status"));
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return newProduct;
}


    public List<Image> getPictureList() {
        List<Image> list = new ArrayList<>();
        String sql = "SELECT [Id]\n"
                + "      ,[ProductDetailId]\n"
                + "      ,[Image]\n"
                + "  FROM [dbo].[Image]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Image image = new Image();
                image.setId(rs.getInt("Id"));

                // Lấy ProductDetail từ ProductDetailId
                ProductDetail productDetail = getProductDetailById(rs.getInt("ProductDetailId"));
                image.setProductDetail(productDetail);

                // Đặt giá trị cho trường Image
                image.setImage(rs.getString("Image"));

                // Thêm đối tượng Image vào danh sách
                list.add(image);  // Bạn đã bỏ qua dòng này
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Image> getPictureListByProductName(String productName) {
        List<Image> list = new ArrayList<>();
        String sql = "SELECT i.Id, i.ProductDetailId, i.Image "
                + "FROM Image i "
                + "JOIN ProductDetail pd ON i.ProductDetailId = pd.Id "
                + "JOIN Product p ON pd.ProductId = p.Id "
                + "WHERE p.Name LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + productName + "%"); // Tìm kiếm theo tên sản phẩm
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Image image = new Image();
                image.setId(rs.getInt("Id"));

                // Lấy ProductDetail từ ProductDetailId
                ProductDetail productDetail = getProductDetailById(rs.getInt("ProductDetailId"));
                image.setProductDetail(productDetail);

                // Đặt giá trị cho trường Image
                image.setImage(rs.getString("Image"));

                // Thêm đối tượng Image vào danh sách
                list.add(image);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
public Post getPostById(int id) {

        String sql = "SELECT [Id], [UserId], [BrandId], [CategoryId], [Title], "
                + "[ShortContent], [FullContent], [Thumbnail], [PublishDate] "
                + "FROM [dbo].[Post] WHERE [Id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
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
                return p; // Trả về bài viết tìm thấy
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public List<Post> getPostsByTitleOrContent(String searchString) {
        List<Post> postList = new ArrayList<>();
        String sql = "SELECT [Id], [UserId], [BrandId], [CategoryId], [Title], "
                + "[ShortContent], [FullContent], [Thumbnail], [PublishDate] "
                + "FROM [dbo].[Post] "
                + "WHERE [Title] LIKE ? OR [ShortContent] LIKE ?"; // So sánh tiêu đề hoặc nội dung ngắn

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            String searchPattern = "%" + searchString + "%"; // Tạo mẫu tìm kiếm
            st.setString(1, searchPattern);
            st.setString(2, searchPattern);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt("Id"));

                User u = getUserByIdD(rs.getInt("UserId"));  // Lấy thông tin người dùng
                p.setUser(u);

                Brand b = getBrandByIdD(rs.getInt("BrandId")); // Lấy thông tin thương hiệu
                p.setBrand(b);

                Category c = getCategoryByIdD(rs.getInt("CategoryId")); // Lấy thông tin danh mục
                p.setCategory(c);

                p.setTittle(rs.getString("Title")); // Đặt tiêu đề
                p.setShortContent(rs.getString("ShortContent")); // Đặt nội dung ngắn
                p.setFullContent(rs.getString("FullContent")); // Đặt nội dung đầy đủ
                p.setThumbnail(rs.getString("Thumbnail")); // Đặt thumbnail
                p.setPublishDate(rs.getDate("PublishDate")); // Đặt ngày xuất bản

                postList.add(p); // Thêm bài viết vào danh sách
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return postList; // Trả về danh sách bài viết tìm thấy
    }

    public List<User> getAll() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.Id, u.Username, u.Password, u.Fullname, u.Email, u.RoleId, u.Status "
                + "FROM [User] u";

        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String fullname = rs.getString("Fullname");
                String email = rs.getString("Email");
//            int roleId = rs.getInt("RoleId");
                String status = rs.getString("Status");

                // Tạo đối tượng User và thêm vào danh sách
                User user = new User(id, username, password, fullname, email, status);
                users.add(user);
            }
            return users;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng các tài nguyên
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (stm != null) try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public void banAnUser(int userId) {
        PreparedStatement stm = null;

        String sql = "UPDATE [dbo].[user] SET [status] = 'ban' WHERE id = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close(); // Đóng PreparedStatement
                }
                // Không đóng connection ở đây
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void unBanAnUser(int userId) {
        PreparedStatement stm = null;

        String sql = "UPDATE [dbo].[user] SET [status] = 'unban' WHERE id = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close(); // Đóng PreparedStatement
                }
                // Không đóng connection ở đây
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
// private Connection connection; // Biến kết nối tới database

    // Hàm tìm kiếm user theo từ khóa
    public List<User> getUsersByKeyword(String keyword) {
        List<User> users = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        // Sử dụng tên bảng đầy đủ với schema nếu cần (ví dụ: dbo.User)
        String sql = "SELECT * FROM dbo.[User]  WHERE userName LIKE ? OR email LIKE ?";

        try {
            stm = connection.prepareStatement(sql);
            String searchPattern = "%" + keyword + "%";  // Sử dụng wildcard cho tìm kiếm
            stm.setString(1, searchPattern);
            stm.setString(2, searchPattern);
            rs = stm.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setFullName(rs.getString("fullName"));
                user.setEmail(rs.getString("email"));
                user.setStatus(rs.getString("status"));
                // Thêm user vào danh sách
                users.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                // Không đóng connection ở đây
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return users;
    }
public int[] getMinMaxPostId() {
    int[] minMax = new int[2];
    String sql = "SELECT MIN([Id]) AS minId, MAX([Id]) AS maxId FROM [dbo].[Post]";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            minMax[0] = rs.getInt("minId"); // Lấy id nhỏ nhất
            minMax[1] = rs.getInt("maxId"); // Lấy id lớn nhất
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return minMax;
}


    public List<User> getUserByRoleId(int roleId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.Id, u.Username, u.Password, u.Fullname, u.Email, u.RoleId, u.Status "
                + "FROM [User] u WHERE u.RoleId = ?";

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, roleId);  // Gán giá trị roleId vào câu truy vấn
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String fullname = rs.getString("Fullname");
                String email = rs.getString("Email");
                String status = rs.getString("Status");

                // Tạo đối tượng User và thêm vào danh sách
                User user = new User(id, username, password, fullname, email, status);
                users.add(user);
            }
            return users;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng các tài nguyên
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (stm != null) try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public boolean deleteUserById(int userId) {
        String deleteCartItemSql = "DELETE FROM CartItem WHERE CartId IN (SELECT Id FROM Cart WHERE UserId = ?)";
        String deleteCartSql = "DELETE FROM Cart WHERE UserId = ?";
        String deleteUserSql = "DELETE FROM [User] WHERE Id = ?";

        try (
                PreparedStatement cartItemStmt = connection.prepareStatement(deleteCartItemSql); PreparedStatement cartStmt = connection.prepareStatement(deleteCartSql); PreparedStatement userStmt = connection.prepareStatement(deleteUserSql)) {
            // 1. Xóa các CartItem liên quan đến Cart của User
            cartItemStmt.setInt(1, userId);
            cartItemStmt.executeUpdate();

            // 2. Xóa các Cart liên quan đến User
            cartStmt.setInt(1, userId);
            cartStmt.executeUpdate();

            // 3. Xóa User
            userStmt.setInt(1, userId);
            int rowsAffected = userStmt.executeUpdate();

            return rowsAffected > 0; // Trả về true nếu xóa thành công

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    public List<User> getUsersByKeywordAndRoleId(String keyword, int roleId) {
        List<User> users = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        // Sử dụng tên bảng đầy đủ với schema nếu cần (ví dụ: dbo.User)
        String sql = "SELECT * FROM dbo.[User] WHERE (userName LIKE ? OR email LIKE ?) AND RoleId = ?";

        try {
            stm = connection.prepareStatement(sql);
            String searchPattern = "%" + keyword + "%";  // Sử dụng wildcard cho tìm kiếm
            stm.setString(1, searchPattern);
            stm.setString(2, searchPattern);
            stm.setInt(3, roleId);  // Gán RoleId

            rs = stm.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setFullName(rs.getString("fullName"));
                user.setEmail(rs.getString("email"));
                user.setStatus(rs.getString("status"));
                // Thêm user vào danh sách
                users.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                // Không đóng connection ở đây
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return users;
    }

   public boolean isEmailMatchUsername(String email, String username) {
    String sql = "SELECT * FROM [User] WHERE email = ? AND username = ?";
    try {
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, email);    // Đặt email vào vị trí dấu ? đầu tiên
        pre.setString(2, username); // Đặt username vào vị trí dấu ? thứ hai

        ResultSet rs = pre.executeQuery();
        // Nếu tìm thấy một hàng, nghĩa là email và username khớp nhau
        if (rs.next()) {
            return true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false; // Trả về false nếu không tìm thấy
}

   
 public boolean resetPassword(String username, String email , String newPassword) {
    // Câu lệnh SQL để cập nhật mật khẩu mới
    String sql = "UPDATE [User] SET [Password] = ? WHERE username = ? AND Email = ?";
    
    try {
        PreparedStatement pre = connection.prepareStatement(sql);
        
        // Mã hóa mật khẩu mới (nếu cần), ví dụ: mã hóa bằng BCrypt hoặc bất kỳ phương pháp nào bạn muốn
        // Ở đây mình sẽ sử dụng mật khẩu trực tiếp, nhưng bạn có thể thay đổi nếu cần
        String hashedPassword = hashPassword(newPassword);  
        
        // Thiết lập giá trị cho câu lệnh SQL
        pre.setString(1, hashedPassword);
        pre.setString(2, username);
        pre.setString(3, email);
        // Thực thi cập nhật
        int result = pre.executeUpdate();
        
        // Nếu cập nhật thành công, trả về true
        return result > 0;
        
    } catch (SQLException ex) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    // Nếu có lỗi xảy ra, trả về false
    return false;
}

public User getUserByEmail(String email) {
    String sql = "SELECT * FROM [User] WHERE email = ?";  // Truy vấn SQL lấy user dựa trên email
    
    
    try {
        // Chuẩn bị câu lệnh SQL
        PreparedStatement pre = connection.prepareStatement(sql);
        
        // Gán giá trị cho biến email trong câu lệnh SQL
        pre.setString(1, email);
        
        // Thực thi câu lệnh và nhận kết quả
        ResultSet rs = pre.executeQuery();
        
        // Xử lý kết quả trả về từ ResultSet
        if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUserName(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setFullName(rs.getString(4));
                u.setEmail(rs.getString(5));
                Role role = new Role();
                role.setId(rs.getInt(6));
                u.setRole(role);
                u.setStatus(rs.getString(7));
                return u;
            }
    } catch (SQLException ex) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return null;  // Trả về đối tượng User hoặc null nếu không tìm thấy
}


 
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        System.out.println(dao.getUserByEmail("trantienminh204@gmail.com"));
    }

}
//================================================================================================================

