/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CartDAOS;
import dal.ImageDAOS;
import dal.ProductDAOS;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import model.Cart;
import model.CartItem;
import model.Image;
import model.Post;
import model.ProductDetail;
import model.User;

/**
 *
 * @author kieud
 */
@WebServlet(name="ProductDisplayHome", urlPatterns={"/home"})
public class DisplayHome extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductDisplayHome</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductDisplayHome at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    UserDAO dao = new UserDAO();
    
    List<Post> listP = dao.getPostListD();
    List<Image> listProduct = dao.getPictureList();
    
  ProductDAOS pDao = new ProductDAOS();
List<ProductDetail> listProductDetails = pDao.getTop16ProductDetail();
ImageDAOS iDAO = new ImageDAOS();
List<Image> listImages = new ArrayList<>();

// Lặp qua listProductDetails để lấy hình ảnh và thêm vào listImages
for (ProductDetail productDetail : listProductDetails) {
    Image image = iDAO.getOneImageByProductDetailID(productDetail.getId());
    if (image != null) { // Kiểm tra nếu image không null
        listImages.add(image);
    }
}

// Lọc lại các ProductDetail mà có hình ảnh và chỉ hiển thị một lần cho mỗi Product
Set<Integer> productIds = new HashSet<>(); // Set để lưu trữ ID của Product đã thêm
List<ProductDetail> ListDaLoc = new ArrayList<>();

for (Image image : listImages) {
    int productId = image.getProductDetail().getId(); // Lấy ID của ProductDetail
    ProductDetail productDetail = pDao.getProductDetailByID(productId);
    
    if (productDetail != null && !productIds.contains(productDetail.getProduct().getId())) {
        ListDaLoc.add(productDetail); // Thêm vào danh sách kết quả
        productIds.add(productDetail.getProduct().getId()); // Đánh dấu Product này đã được thêm
    }
}

    
    // Tạo danh sách ảnh ngẫu nhiên không trùng lặp
    List<Image> randomlist = new ArrayList<>();
    int desiredSize = 3;
    Random random = new Random();
    
    while (randomlist.size() < desiredSize && randomlist.size() < listImages.size()) {
        int rand = random.nextInt(listImages.size());
        Image selectedImage = listImages.get(rand);
        
        // Kiểm tra xem hình ảnh đã được chọn chưa
        if (!randomlist.contains(selectedImage)) {
            randomlist.add(selectedImage);
        }
    }

        
//    HttpSession session = request.getSession();
//    User user = (User) session.getAttribute("user");
//    Cart cartUser = cartDAO.getCartByUserID(user.getId());    
//    List<CartItem> listCartItem = cartDAO.getAllProductOfCartItem(cartUser.getId());
    
 request.setAttribute("listProduct", listProduct);
    request.setAttribute("listR", randomlist);
    request.setAttribute("ListPics", listImages);
    request.setAttribute("ListDetail", ListDaLoc); // Sử dụng ListDaLoc đã được lọc
    request.setAttribute("listP", listP);
    request.getRequestDispatcher("index.jsp").forward(request, response);
}





    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    }
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
  

    public static void main(String[] args) {
         UserDAO dao = new UserDAO();
        List<Image> listProduct = dao.getPictureList();
        List<Image> filteredList = new ArrayList<>(); // Danh sách để lưu sản phẩm duy nhất

        
        for (int i = 0; i < listProduct.size(); i++) {
            Image get = listProduct.get(i);
            System.out.println(get);
        }

        // filteredList giờ chứa các sản phẩm không trùng với giá cao nhất cho mỗi ID
        // Thực hiện các hành động khác với filteredList
    }
}



