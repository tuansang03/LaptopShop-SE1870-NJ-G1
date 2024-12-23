/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CartDAOS;
import dal.ImageDAOS;
import dal.OderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.CartItem;
import model.Image;
import model.Order;
import model.OrderDetail;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="ViewOrderDetail", urlPatterns={"/viewOrderDetail"})
public class ViewOrderDetail extends HttpServlet {
   
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
        String id_raw = request.getParameter("id");
        int id = Integer.parseInt(id_raw);
        
        OderDAO oDAO = new OderDAO();
        List<OrderDetail> listOrderDetail = oDAO.getAllOrdetailByID(id);
        
      
        ImageDAOS iDAO = new ImageDAOS();
        List<Image> listImages = new ArrayList<>();

        for (int i = 0; i < listOrderDetail.size(); i++) {
            int productDetailId = listOrderDetail.get(i).getProductDetail().getId();
            Image image = iDAO.getOneImageByProductDetailID(productDetailId);
            listImages.add(image); // Thêm hình ảnh vào danh sách
        }
        
        request.setAttribute("listImages", listImages);
        request.setAttribute("listOrderDetail", listOrderDetail);
        request.getRequestDispatcher("vieworderdetail.jsp").forward(request, response);
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
        processRequest(request, response);
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
