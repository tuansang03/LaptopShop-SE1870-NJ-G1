/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.CartItem;

/**
 *
 * @author ADMIN
 */
public class CartDAOS extends DBContext {

    public void addToCart(int uid) {
        String sql = "INSERT INTO [Cart] VALUES (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void addToCartItem(int id, int pid, int quantity) {
        String sql = "INSERT INTO [dbo].[CartItem] VALUES (?, ?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, pid);
            st.setInt(3, quantity);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Cart getCartByUserID(int uid) {
        String sql = "SELECT * FROM Cart WHERE UserId = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                UserDAO uDAO = new UserDAO();
                Cart cart = new Cart(
                        rs.getInt("Id"),
                        uDAO.getUserByIdD(rs.getInt("UserId")));
                return cart;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Cart getCartID(int uid) {
        String sql = "SELECT * FROM Cart WHERE id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                UserDAO uDAO = new UserDAO();
                Cart cart = new Cart(
                        rs.getInt("Id"),
                        uDAO.getUserByIdD(rs.getInt("UserId")));
                return cart;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<CartItem> getAllProductOfCartItem(int cartID) {
        String sql = "SELECT * FROM CartItem WHERE CartId = ?";
        List<CartItem> listCartItem = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ProductDAO pDAO = new ProductDAO();
                CartItem cart = new CartItem(
                        rs.getInt("Id"),
                        getCartID(rs.getInt("CartId")),
                        pDAO.getProductDetailByID(rs.getInt("ProductDetailId")),
                        rs.getInt("Quantity"));
                listCartItem.add(cart);
            }

        } catch (Exception e) {
        }
        return listCartItem;
    }

    public CartItem getCartItemByCartIdAndProductId(int cid, int pid) {
        String sql = "SELECT * FROM CartItem WHERE CartId = ? AND ProductDetailId = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            st.setInt(2, pid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                ProductDAO pDAO = new ProductDAO();
                CartItem cart = new CartItem(
                        rs.getInt("Id"),
                        getCartID(rs.getInt("CartId")),
                        pDAO.getProductDetailByID(rs.getInt("ProductDetailId")),
                        rs.getInt("Quantity"));
                return cart;
            }

        } catch (Exception e) {
        }
        return null;

    }

    public void updateCartItemQuantity(int cid, int pid, int newQuantity) {
        String sql = "UPDATE [dbo].[CartItem]\n"
                + "   SET [Quantity] = ?"
                + " WHERE CartId = ? AND ProductDetailId = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, newQuantity);
            st.setInt(2, cid);
            st.setInt(3, pid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteCartItem(int cartID, int pID) {
        String sql = "DELETE FROM [CartItem] WHERE CartId = ? AND ProductDetailId = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartID);
            st.setInt(2, pID);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public static void main(String[] args) {
        CartDAOS c = new CartDAOS();
        c.deleteCartItem(1, 1);
        
    }

    

}
