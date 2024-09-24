/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AttributeDAO;
import dal.BrandDAO;
import dal.CategoryDAO;
import dal.ColorDAO;
import dal.ConfigurationDAO;
import dal.ProductDAO;
import dal.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Attribute;
import model.Brand;
import model.Category;
import model.Color;
import model.Configuration;
import model.Product;
import model.ProductDetail;

/**
 *
 * @author ADMIN
 */
public class addProduct extends HttpServlet {

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
            out.println("<title>Servlet addProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addProduct at " + request.getContextPath() + "</h1>");
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

        CategoryDAO c = new CategoryDAO();
        BrandDAO b = new BrandDAO();
        ColorDAO color = new ColorDAO();
        ConfigurationDAO config = new ConfigurationDAO();
        ArrayList<Category> cList = c.getAllCategory();
        ArrayList<Brand> bList = b.getAllBrand();
        ArrayList<Color> colorList = color.getAllColor();
        ArrayList<Configuration> configList = config.getAllConfig();

        request.setAttribute("configList", configList);
        request.setAttribute("colorList", colorList);
        request.setAttribute("cList", cList);
        request.setAttribute("bList", bList);
        request.getRequestDispatcher("addProduct.jsp").forward(request, response);
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
        String productName = request.getParameter("productName");
        String cId = request.getParameter("category");
        String bId = request.getParameter("brand");
        String[] colors = request.getParameterValues("colors");
        String[] configurations = request.getParameterValues("configurations");
        String description = request.getParameter("description");

        int cId1 = 0;
        int bId1 = 0;
        int[] colorIds = null; // Mảng để lưu color IDs
        int[] configIds = null; // Mảng để lưu configuration IDs

        try {
            cId1 = Integer.parseInt(cId);
            bId1 = Integer.parseInt(bId);

            // Parse colors thành int[]
            if (colors != null) {
                colorIds = new int[colors.length];
                for (int i = 0; i < colors.length; i++) {
                    colorIds[i] = Integer.parseInt(colors[i]);
                }
            }

            // Parse configurations thành int[]
            if (configurations != null) {
                configIds = new int[configurations.length];
                for (int i = 0; i < configurations.length; i++) {
                    configIds[i] = Integer.parseInt(configurations[i]);
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("msg", e);
            request.getRequestDispatcher("addProduct").forward(request, response);
            return;
        }

        ProductDAO p = new ProductDAO();
        int productId = p.insertProduct(bId1, cId1, productName);
        ProductDetailDAO pD = new ProductDetailDAO();
        pD.insertMultipleProductDetails(productId, colorIds, configIds, description);
        ArrayList<ProductDetail> pDList = pD.getAllProductDetailById(productId);
        AttributeDAO att = new AttributeDAO();
        ArrayList<Attribute> attList = att.getAllAttribute();
        HttpSession session = request.getSession();
        session.setAttribute("productId", productId);
        session.setAttribute("attList", attList);
        session.setAttribute("pDList", pDList);
        request.getRequestDispatcher("inputProductDetail.jsp").forward(request, response);
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
