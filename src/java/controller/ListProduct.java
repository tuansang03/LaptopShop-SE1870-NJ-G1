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
import java.util.ArrayList;
import model.*;
import java.util.List;

/**
 *
 * @author PHONG
 */
@WebServlet(name = "ListProduct", urlPatterns = {"/listproduct"})
public class ListProduct extends HttpServlet {

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
            out.println("<title>Servlet ListProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListProduct at " + request.getContextPath() + "</h1>");
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

        String price = request.getParameter("price");
        String name = request.getParameter("name");
        // Get selected brands and categories as arrays
        String[] selectedBrands = request.getParameterValues("brand[]");
        String[] selectedCategories = request.getParameterValues("category[]");
        List<Brand> brandlist = d.listBrand();
        List<Category> categorylist = d.listCategory();
        List<ProductList> list = d.listProduct(convert(selectedCategories), convert(selectedBrands), price, name);


        request.setAttribute("selectedBrands", selectedBrands);
        request.setAttribute("selectedCategories", selectedCategories);

        request.setAttribute("a", convert(selectedCategories));
        request.setAttribute("b", convert(selectedBrands));

        request.setAttribute("brandlist", brandlist);
        request.setAttribute("categorylist", categorylist);
        request.setAttribute("price", price);
        request.setAttribute("productlist", list);
        request.getRequestDispatcher("category.jsp").forward(request, response);
    }

    public String convert(String[] array) {
        if (array == null || array.length == 0) {
            return null; // Trả về chuỗi rỗng nếu mảng null hoặc trống
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.append("'").append(array[i]).append("'"); // Thêm dấu ngoặc đơn
            if (i < array.length - 1) {
                result.append(","); // Thêm dấu phẩy giữa các phần tử
            }
        }
        return result.toString();
    }

}
