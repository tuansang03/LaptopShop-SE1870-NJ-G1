/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ImageDAO;
import dal.ProductDAO;
import dal.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import model.Image;
import model.ProductDetail;

/**
 *
 * @author ADMIN
 */
public class deleteProduct extends HttpServlet {
   
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
            out.println("<title>Servlet deleteProduct</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteProduct at " + request.getContextPath () + "</h1>");
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
      String productId = request.getParameter("id");
int productId1 = 0;

try {
    productId1 = Integer.parseInt(productId);
} catch (NumberFormatException e) {
    // Xử lý lỗi nếu cần
}

ProductDetailDAO dao = new ProductDetailDAO();
ArrayList<ProductDetail> pDList = dao.getAllProductDetailById(productId1);

// Khởi tạo ImageDAO để lấy danh sách ảnh
ImageDAO imageDAO = new ImageDAO();

for (ProductDetail productDetail : pDList) {
    // Lấy danh sách ảnh của productDetail
    ArrayList<Image> images = imageDAO.getAllImageByPDId(productDetail.getId());

    // Xóa file ảnh vật lý
    for (Image image : images) {
        String imagePath = getServletContext().getRealPath("/images/" + image.getImage()); // Đường dẫn tới ảnh
        File file = new File(imagePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Deleted file: " + imagePath);
            } else {
                System.out.println("Failed to delete file: " + imagePath);
            }
        }
    }
    
    // Xóa ProductDetail khỏi cơ sở dữ liệu
    dao.deletePDById(productDetail.getId());
}

// Sau khi đã xóa tất cả ProductDetail, xóa sản phẩm
ProductDAO pDao = new ProductDAO();
if(pDao.deleteById(productId1)){
  request.setAttribute("msg", "Delete successful");
}else{
    request.setAttribute("error", "Delete fail");
}

// Chuyển hướng đến trang sản phẩm
request.getRequestDispatcher("readProduct").forward(request, response);
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
