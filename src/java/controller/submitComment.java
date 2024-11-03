/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import static controller.ProductInformation.formatCurrency;
import static controller.ProductInformation.formatCurrency2;
import dal.CommentDAO;
import dal.ProductDAO;
import dal.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Color;
import model.Comment;
import model.Configuration;
import model.Image;
import model.ProductAttribute;
import model.ProductDetail;
import model.ProductList;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "submitComment", urlPatterns = {"/submitComment"})
public class submitComment extends HttpServlet {

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
            out.println("<title>Servlet submitComment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet submitComment at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        HttpSession session = request.getSession();
String commentContent = request.getParameter("commentContent");

int productDetailId = (int) session.getAttribute("pId");
User u = (User) session.getAttribute("user");
ProductDetailDAO pDao = new ProductDetailDAO();
int productId = pDao.getProductIdByProductDetailId(productDetailId);

if (!isCommentLengthValid(commentContent)) {
    request.setAttribute("msg", "Please enter comment length < 200 character");
    ProductDAO pDAO = new ProductDAO();
    CommentDAO c = new CommentDAO();
    ArrayList<Comment> cList = c.readAllComment();
    ProductDAO p = new ProductDAO();
    ArrayList pList = p.getAllProduct();

    request.setAttribute("pList", pList);
    request.setAttribute("commentList", cList);
    request.getRequestDispatcher("manageComment.jsp").forward(request, response);
    return;
}

CommentDAO c = new CommentDAO();
boolean flag = c.insertComment(u, commentContent, productId);

if (flag) {
    // Thay vì forward, chúng ta sử dụng sendRedirect
    
    response.sendRedirect("information?productId=" + productDetailId);
}

    }

    private void setProductDetails(HttpServletRequest request, int productDetailId) {
        ProductDAO d = new ProductDAO();
        ProductDetail pd = d.getProductDetail(productDetailId);
        List<Image> list = d.getImageById(productDetailId);
        List<Configuration> con = d.listConfigurationById(productDetailId);
        List<ProductAttribute> pa = d.getAttributeById(productDetailId);

        int selectedConfigId = pd.getConfiguration().getId();
        int selectedColorId = pd.getColor().getId();
        List<Color> col = d.listColorById(productDetailId, selectedConfigId);
        List<ProductList> listproduct = d.listProduct("'" + pd.getProduct().getCategory().getName() + "'", null, null, null);
        String price = formatCurrency(pd.getPrice());
        ProductDetailDAO pDDAO = new ProductDetailDAO();
        int productId = pDDAO.getProductIdByProductDetailId(productDetailId);
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

        double salep = pd.getPrice() * 1.2;
        String sale = formatCurrency2(salep);
        request.setAttribute("sale", sale);
        request.setAttribute("listproduct", listproduct);
    }

    public boolean isCommentLengthValid(String commentContent) {
        if (commentContent != null) {
            int length = commentContent.length();
            return length > 0 && length <= 200;
        }
        return false;
    }

    public boolean isCommentContentValid(String commentContent) {
        String regex = "^[a-zA-Z0-9\\s.,?!'\"-]*$"; // Chỉ cho phép chữ cái, số, khoảng trắng và một số ký tự đặc biệt
        return commentContent.matches(regex);
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
