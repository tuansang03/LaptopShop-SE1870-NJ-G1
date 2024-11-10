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

    String query = request.getParameter("query");
    String error = "";
    UserDAO dao = new UserDAO();
    String errorProduct = "";
    String errorPost = "";
    
    List<Integer> listId = new ArrayList<>();
    List<Post> listP = new ArrayList<>();
    List<Image> list = new ArrayList<>();

    // Kiểm tra nếu query không trống
    if (query != null && !query.trim().isEmpty()) {
        listP = dao.getPostsByTitleOrContent(query);
        for (Post post : listP) {
            listId.add(post.getId());
        }

        list = dao.getPictureListByProductName(query);
    }

      if (list.isEmpty()) {
        errorProduct = " There is no product that match your Keyword";
    } if (listP.isEmpty()) {
        errorPost = "There is no Post that match your Keyword";
    }

    request.setAttribute("listId", listId);
    request.setAttribute("pop_size", list.size());
    request.setAttribute("errorProduct", errorProduct);
    request.setAttribute("errorPost", errorPost);
    request.setAttribute("listP", listP);
    request.setAttribute("listPSize", listP.size());
    request.setAttribute("pop", list);

    request.getRequestDispatcher("SearchOverviewPage.jsp").forward(request, response);
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
