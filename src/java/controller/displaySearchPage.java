/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Image;
import model.Post;
import model.ProductDetail;

/**
 *
 * @author kieud
 */
@WebServlet(name = "displaySearchSuggest", urlPatterns = {"/displaySearchSuggest"})
public class displaySearchPage extends HttpServlet {

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
            out.println("<title>Servlet displaySearchSuggest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet displaySearchSuggest at " + request.getContextPath() + "</h1>");
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
//     String minPost = "1";
//     String maxPost = "5";
//     if(request.getAttribute("min")!=null&&request.getAttribute("max")!=null){
//         minPost = request.getParameter("min");
//         maxPost = request.getParameter("max");
//     }

        String query = request.getParameter("query"); // Lấy giá trị từ tham số request
        String error = "";
        UserDAO dao = new UserDAO();
        List<Post> listP = dao.getPostsByTitleOrContent(query);
        // Gọi phương thức với tham số đúng
        List<Image> list = dao.getPictureListByProductName(query);
        if (list.isEmpty() && listP.isEmpty()) {
            error = " ! ! ! There is no product and Post that match your Keyword";
        } else if (list.isEmpty()) {
            error = " ! ! ! There is no product that match your Keyword";
        } else if (listP.isEmpty()) {
            error = " ! ! ! There is no Post that match your Keyword";
        }

        request.setAttribute("pop_size", list.size());
        request.setAttribute("err", error);
        request.setAttribute("listP", listP);
        request.setAttribute("listPSize", listP.size());
        request.setAttribute("pop", list); // Đặt danh sách hình ảnh vào thuộc tính request
        request.getRequestDispatcher("SearchOverviewPage.jsp").forward(request, response); // Chuyển hướng đến trang JSP
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
        processRequest(request, response);
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
