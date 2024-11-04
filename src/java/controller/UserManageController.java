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
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author LOC
 */
@WebServlet(name = "UserManageController", urlPatterns = {"/CustomerManageController"})
public class UserManageController extends HttpServlet {

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
            out.println("<title>Servlet CustomerManageController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerManageController at " + request.getContextPath() + "</h1>");
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
        String service = request.getParameter("service");

        if (service == null) {
            service = "listall";
        }

        if (service.equalsIgnoreCase("listall")) {
            UserDAO userDAO = new UserDAO();
            List<User> listUser = new ArrayList<>();
            listUser = userDAO.getUserByRoleId(3);
            request.setAttribute("listUser", listUser);
            request.getRequestDispatcher("manageuserdisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("banUser")) {
            UserDAO userDAO = new UserDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            userDAO.banAnUser(id);
            List<User> listUser = new ArrayList<>();
            listUser = userDAO.getUserByRoleId(3);
            request.setAttribute("listUser", listUser);
            request.getRequestDispatcher("manageuserdisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("searchUser")) {
            UserDAO userDAO = new UserDAO();
            List<User> listUser = new ArrayList<>();
            String key = request.getParameter("keyword");
            listUser = userDAO.getUsersByKeywordAndRoleId(key, 3);
            request.setAttribute("listUser", listUser);
            request.getRequestDispatcher("manageuserdisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("unbanUser")) {
            UserDAO userDAO = new UserDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            userDAO.unBanAnUser(id);
            List<User> listUser = new ArrayList<>();
            listUser = userDAO.getUserByRoleId(3);
            request.setAttribute("listUser", listUser);
            request.getRequestDispatcher("manageuserdisplay.jsp").forward(request, response);
        }

        if(service.equalsIgnoreCase("deleteUser")){
            UserDAO userDAO = new UserDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            boolean check = userDAO.deleteUserById(id);
            String mess = "delete failed";
            if(check == true){
                 mess = "delete successfull!";
            }
          
            List<User> listUser = new ArrayList<>();
            request.setAttribute("mess", mess);
            listUser = userDAO.getUserByRoleId(3);
            request.setAttribute("listUser", listUser);
            request.getRequestDispatcher("manageuserdisplay.jsp").forward(request, response);
        }
        
          if(service.equalsIgnoreCase("deleteStaff")){
            UserDAO userDAO = new UserDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            boolean check = userDAO.deleteUserById(id);
            String mess = "delete failed";
            if(check == true){
                 mess = "delete successfull!";
            }
          
            List<User> listUser = new ArrayList<>();
            request.setAttribute("mess", mess);
            listUser = userDAO.getUserByRoleId(2);
            request.setAttribute("listStaff", listUser);
            request.getRequestDispatcher("managestaffdisplay.jsp").forward(request, response);
        }
        
        
        if (service.equalsIgnoreCase("unbanStaff")) {
            UserDAO userDAO = new UserDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            userDAO.unBanAnUser(id);
            List<User> listUser = new ArrayList<>();
            listUser = userDAO.getUserByRoleId(2);
            request.setAttribute("listStaff", listUser);
            request.getRequestDispatcher("managestaffdisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("listallstaff")) {
            UserDAO userDAO = new UserDAO();
            List<User> listUser = new ArrayList<>();
            listUser = userDAO.getUserByRoleId(2);
            request.setAttribute("listStaff", listUser);
            request.getRequestDispatcher("managestaffdisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("searchStaff")) {
            UserDAO userDAO = new UserDAO();
            List<User> listUser = new ArrayList<>();
            String key = request.getParameter("keyword");
            listUser = userDAO.getUsersByKeywordAndRoleId(key, 2);
            request.setAttribute("listStaff", listUser);
            request.getRequestDispatcher("managestaffdisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("banStaff")) {
            UserDAO userDAO = new UserDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            userDAO.banAnUser(id);
            List<User> listUser = new ArrayList<>();
            listUser = userDAO.getUserByRoleId(2);
            request.setAttribute("listStaff", listUser);
            request.getRequestDispatcher("managestaffdisplay.jsp").forward(request, response);
        }
        
        if (service.equalsIgnoreCase("addStaff")){
            response.sendRedirect("addstaff.jsp");
        }
        
        if(service.equalsIgnoreCase("addStaff2")){
            String userName = request.getParameter("userName");
            UserDAO userDao = new UserDAO();
            boolean check = userDao.changeUserRoleId(userName,2);
            if(check == true){
                request.setAttribute("mess", "Add Staff successfull!");
                request.getRequestDispatcher("addstaff.jsp").forward(request, response);
            }
            else{
                request.setAttribute("mess", "User Name not found!");
                request.getRequestDispatcher("addstaff.jsp").forward(request, response);
            }
        }

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
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
}
