/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.ProductDAO;
import dal.ProductDetailDAO;
import jakarta.servlet.http.HttpSession;
import model.*;
import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author PHONG
 */
@WebServlet(name = "ProductInformation", urlPatterns = {"/information"})
public class ProductInformation extends HttpServlet {

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
            out.println("<title>Servlet ProductInformation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductInformation at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        session.removeAttribute("pId");
        int id = Integer.parseInt(request.getParameter("productId"));
        session.setAttribute("pId", id);
        ProductDetailDAO pDDAO = new ProductDetailDAO();
       int productId = pDDAO.getProductIdByProductDetailId(id);
        List<Image> list = d.getImageById(id);
        ProductDetail pd = d.getProductDetail(id);
        List<Configuration> con = d.listConfigurationById(id);
        List<ProductAttribute> pa = d.getAttributeById(id);
        int selectedConfigId = pd.getConfiguration().getId();
        int selectedColorId = pd.getColor().getId();
        List<Color> col = d.listColorById(id, selectedConfigId);
        List<ProductList> listproduct = d.listProduct("'" + pd.getProduct().getCategory().getName() + "'", null, null, null);
        String price = formatCurrency(d.getProductDetail(id).getPrice());
       
        //handle comment
        CommentDAO c = new CommentDAO();
        ArrayList<Comment> cList = c.getCommentByProductId(productId);
        
        request.setAttribute("commentList", cList);
        request.setAttribute("co", pd.getConfiguration().getName());
        request.setAttribute("col", pd.getColor().getName());
        request.setAttribute("detail", pd);
        request.setAttribute("attribute", pa);
        request.setAttribute("config", con);
        request.setAttribute("selectedConfigId", selectedConfigId);
        request.setAttribute("selectedColorId", selectedColorId);
        request.setAttribute("image", list);
        request.setAttribute("color", col);
        request.setAttribute("price", price);
        double salep = d.getProductDetail(id).getPrice() * 1.2;
        String sale = formatCurrency2(salep);
        request.setAttribute("sale", sale);
        request.setAttribute("listproduct", listproduct);
        request.getRequestDispatcher("single-product.jsp").forward(request, response);
    }

    public static String formatCurrency(int amount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount) + " VNĐ";
    }

    public static String formatCurrency2(double amount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }
}
