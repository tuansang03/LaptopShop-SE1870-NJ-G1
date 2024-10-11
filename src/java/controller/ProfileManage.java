/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ImageDAOS;
import dal.OderDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import model.Image;
import model.Order;
import model.OrderDetail;
import model.User;

/**
 *
 * @author kieud
 */
@WebServlet(name="ProfileManage", urlPatterns={"/profile"})
public class ProfileManage extends HttpServlet {
   
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
            out.println("<title>Servlet ProfileManage</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileManage at " + request.getContextPath () + "</h1>");
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
                 HttpSession session = request.getSession();
                 User getCurrentUser = (User)session.getAttribute("user");
                 OderDAO order = new OderDAO();
                 
                 String profile = request.getParameter("profile");
                 
                  if(profile.equals("info")){
                     
                 
                 Order getOrder = order.getNewestOrder(getCurrentUser.getId());
                 request.setAttribute("orderInfo",getOrder);
                 request.getRequestDispatcher("userprofile.jsp?profile=info").forward(request, response);
                 
                  }
                  ImageDAOS image = new ImageDAOS();
                 if(profile.equals("ordermanage")){
                     List<OrderDetail> list = null;
                     List<Image> listImage = null;
                     for (int i = 0; i < list.size(); i++) {
                         OrderDetail get = list.get(i);
                         Image getImage = image.getOneImageByProductDetailID(get.getProductDetail().getId());
                         listImage.add(getImage);
                     }
                     
                     try {
                         list = order.getAllOrderDetails();
                     } catch (SQLException ex) {
                         Logger.getLogger(ProfileManage.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     request.setAttribute("listimage", listImage);
                      request.setAttribute("orderListInfo",list);
                 request.getRequestDispatcher("userprofile.jsp?profile=ordermanage").forward(request, response);
                 }
                 if(profile.equals("orderdetail")){
//                     try {
                         int getOrderDetailId = Integer.parseInt(request.getParameter("id"));
                     try {
                         OrderDetail currentOrderDetail = order.getOrderDetailById(getOrderDetailId);
                         request.setAttribute("currentOrderDetail", currentOrderDetail);
                     } catch (SQLException ex) {
                         Logger.getLogger(ProfileManage.class.getName()).log(Level.SEVERE, null, ex);
                     }
                         
                         request.getRequestDispatcher("userprofile.jsp?profile=orderdetail&id=" + getOrderDetailId).forward(request, response);
//                     } catch (SQLException ex) {
//                         Logger.getLogger(ProfileManage.class.getName()).log(Level.SEVERE, null, ex);
//                     }
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
    public static void main(String[] args) {
        
    }

}
