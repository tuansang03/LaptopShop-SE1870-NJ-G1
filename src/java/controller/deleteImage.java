/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ImageDAO;
import dal.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Image;
import model.ProductDetail;

/**
 *
 * @author ADMIN
 */
public class deleteImage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet deleteImage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteImage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageId = request.getParameter("imageId");

try {
    // Chuyển đổi imageId từ String sang int
    int imageIdInt = Integer.parseInt(imageId);

    // Gọi DAO để xóa ảnh theo imageId
    ImageDAO imageDAO = new ImageDAO();
    boolean isDeleted = imageDAO.deleteImageById(imageIdInt);

    if (isDeleted) {
        // Lấy session hiện tại
        HttpSession session = request.getSession();

        // Lấy productId từ session
        int productId = (int) session.getAttribute("updateProductId");

        // Lấy danh sách ProductDetail hiện tại
        ProductDetailDAO pD = new ProductDetailDAO();
        ArrayList<ProductDetail> pDList = pD.getAllProductDetailById(productId); // Giả sử bạn có phương thức này

        // Cập nhật danh sách ảnh cho từng ProductDetail
        Map<Integer, ArrayList<Image>> imgListMap = new HashMap<>();
        for (ProductDetail productDetail : pDList) {
            ArrayList<Image> images = imageDAO.getAllImageByPDId(productDetail.getId());
            imgListMap.put(productDetail.getId(), images); // Cập nhật danh sách ảnh
        }

        // Cập nhật lại vào session
        session.setAttribute("imgListMap", imgListMap);
        session.setAttribute("pDList", pDList);

        // Chuyển hướng lại trang chi tiết sản phẩm
        response.sendRedirect("viewDetail.jsp");
    } else {
        // Xử lý khi không xóa thành công, có thể gửi thông báo lỗi
        request.setAttribute("errorMessage", "Failed to delete the image.");
        request.getRequestDispatcher("viewDetail.jsp").forward(request, response);
    }
} catch (NumberFormatException e) {
    // Xử lý lỗi khi không thể chuyển đổi imageId sang int
    request.setAttribute("errorMessage", "Invalid image ID.");
    request.getRequestDispatcher("viewDetail.jsp").forward(request, response);
} catch (Exception e) {
    // Xử lý lỗi khác (nếu cần)
    request.setAttribute("errorMessage", "An error occurred while deleting the image.");
    request.getRequestDispatcher("viewDetail.jsp").forward(request, response);
}

        

    

}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
@Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
