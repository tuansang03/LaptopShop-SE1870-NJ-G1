/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Post;

/**
 *
 * @author kieud
 */
@WebServlet(name="DisplayPostDetail", urlPatterns={"/postdetail"})
public class DisplayPostDetail extends HttpServlet {
   
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
            out.println("<title>Servlet DisplayPostDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DisplayPostDetail at " + request.getContextPath () + "</h1>");
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String idStr = request.getParameter("id");
    if (idStr != null) {
        try {
            int id = Integer.parseInt(idStr); // Chuyển đổi String sang int
            UserDAO dao = new UserDAO();
            Post post = dao.getPostById(id); // Gọi hàm lấy bài viết theo id
            if(post==null){
                post = dao.getPostById(id-1);
                
            }
            String formattedDate = sdf.format(post.getPublishDate());
            request.setAttribute("postDetailTime", formattedDate);
            request.setAttribute("postDetail", post);
            request.getRequestDispatcher("single-blog.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Xử lý lỗi nếu id không phải là số nguyên
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post ID");
        }
    } else {
        // Xử lý lỗi nếu không có id trong request
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing post ID");
    }
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
