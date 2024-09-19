/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static model.PasswordUtil.hashPassword;
import model.User;

/**
 *
 * @author ADMIN
 */
public class login extends HttpServlet {

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
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
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
Cookie arr[] = request.getCookies();
        if (arr != null) {
            for (Cookie cookie : arr) {
                if (cookie.getName().equals("userC")) {
                    request.setAttribute("usernameC", cookie.getValue());

                }
                if (cookie.getName().equals("passC")) {
                    request.setAttribute("passC", cookie.getValue());
                }
            }

        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("rememberMe");
        UserDAO dao = new UserDAO();
        String hashedPassword = hashPassword(password);
        User u = dao.UserLogin(username, hashedPassword);
        HttpSession session = request.getSession();
        if ("true".equals(remember)) {
            Cookie uC = new Cookie("userC", username);
            Cookie p = new Cookie("passC", password);
            uC.setMaxAge(60 * 60);
            p.setMaxAge(60 * 60);
            response.addCookie(uC);
            response.addCookie(p);
        }
        if (u != null) {
            if (u.getRole().getId() == 3) {
                session.setAttribute("user", u);
            } else if (u.getRole().getId() == 2) {
                session.setAttribute("sale", u);
            } else if (u.getRole().getId() == 1) {
                session.setAttribute("admin", u);
            }
            response.sendRedirect("index.jsp");
        }else{
            request.setAttribute("error", "Incorrect username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
