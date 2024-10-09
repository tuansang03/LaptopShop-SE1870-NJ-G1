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
@WebServlet(name = "Comparison", urlPatterns = {"/compare"})
public class Comparison extends HttpServlet {

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
        int id1 = -1;
        int id2 = -1;
        int id3 = -1;

        List<ProductAttribute> list1 = null;
        List<ProductAttribute> list2 = null;
        List<ProductAttribute> list3 = null;
        Image img1 = null;
        Image img2 = null;
        Image img3 = null;

        try {
            id1 = Integer.parseInt(request.getParameter("productid"));
            list1 = d.getAttributeById(id1);
            img1 = d.getImage(id1);

        } catch (Exception e) {
        }

        try {
            id2 = Integer.parseInt(request.getParameter("productid2"));
            list2 = d.getAttributeById(id2);
            img2 = d.getImage(id2);
        } catch (Exception e) {
        }

        try {
            id3 = Integer.parseInt(request.getParameter("productid3"));
            list3 = d.getAttributeById(id3);
            img3 = d.getImage(id3);
        } catch (Exception e) {
        }

        if (img2 == null) {
            img2 = img3;
            list2 = list3;
            img3 = null;
            list3 = null;
        }

        String name = null;
        try {
            name = request.getParameter("name");
        } catch (Exception e) {
        }

        List<Image> mlist = d.getMiniImage(id1, id2, id3, name);

        request.setAttribute("mlist", mlist);

        List<ProductList> list = d.listProduct(null, null, null, null);
        request.setAttribute("list", list);

        request.setAttribute("list1", list1);
        request.setAttribute("img1", img1);
        request.setAttribute("list2", list2);
        request.setAttribute("img2", img2);
        request.setAttribute("list3", list3);
        request.setAttribute("img3", img3);

        request.getRequestDispatcher("compare.jsp").forward(request, response);

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
