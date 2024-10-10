/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.ProductDAO;
import model.*;
import java.util.List;
/**
 *
 * @author PHONG
 */
@WebServlet(name = "AddWishlist", urlPatterns = {"/addtowishlist"})
public class AddWishlist extends HttpServlet {

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
            out.println("<title>Servlet AddWishlist</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddWishlist at " + request.getContextPath() + "</h1>");
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
        ProductDAO d = new ProductDAO();
        int uid = -1;
        int pid=Integer.parseInt(request.getParameter("pid"));
        try{
            uid = Integer.parseInt(request.getParameter("uid"));
        } catch (Exception e){
        }
        if (uid<0){
            request.getRequestDispatcher("login").forward(request, response);
        } else{
            if(checkExist(d.listFavorite(uid), uid, pid)){
                d.addWishlist(uid, pid);  
            }
            List<Image> listwish = d.listWish(uid);
            request.setAttribute("wishlist", listwish);
            request.getRequestDispatcher("wishlist.jsp").forward(request, response);
        }
    }
    
    protected boolean checkExist(List<Favorite> list, int uid, int pid){
        for(Favorite i : list){
            if (i.getProductDetail().getId()==pid && i.getUser().getId()==uid){
                return false;
            }
        }
        return true;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
