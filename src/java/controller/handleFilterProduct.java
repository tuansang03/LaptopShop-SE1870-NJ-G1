/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CommentDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Comment;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "handleFilterProduct", urlPatterns = {"/handleFilterProduct"})
public class handleFilterProduct extends HttpServlet {

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
            out.println("<title>Servlet handleFilterProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet handleFilterProduct at " + request.getContextPath() + "</h1>");
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
        String productId = request.getParameter("productId");

        // Tạo một danh sách để chứa các comment
        ArrayList<Comment> filteredComments = new ArrayList<>();

        // Tương tác với database để lấy comment theo productId
        CommentDAO commentDAO = new CommentDAO();
        if (productId == null || productId.isEmpty()) {
            // Nếu không chọn productId, lấy toàn bộ comment
            filteredComments = commentDAO.readAllComment();
        } else {
            // Nếu có productId, lấy các comment theo productId
            int prodId = Integer.parseInt(productId);
            filteredComments = commentDAO.getCommentByProductId(prodId);
        }
        ProductDAO p = new ProductDAO();
        ArrayList pList = p.getAllProduct();

        request.setAttribute("pList", pList);
        // Đưa kết quả vào request attribute
        request.setAttribute("commentList", filteredComments);

        // Chuyển tiếp đến trang JSP hiển thị kết quả
        request.getRequestDispatcher("manageComment.jsp").forward(request, response);

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
