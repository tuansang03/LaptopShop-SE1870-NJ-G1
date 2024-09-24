/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AttributeDAO;
import dal.ImageDAO;
import dal.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Attribute;
import model.Image;
import model.ProductAttribute;
import model.ProductDetail;

/**
 *
 * @author ADMIN
 */
public class inputProductDetail extends HttpServlet {

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
            out.println("<title>Servlet inputProductDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet inputProductDetail at " + request.getContextPath() + "</h1>");
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
// Lấy productId từ session
        int pId = (int) session.getAttribute("productId");

        ProductDetailDAO productDetailDAO = new ProductDetailDAO();
// Lấy tất cả ProductDetail theo productId
        ArrayList<ProductDetail> productList = productDetailDAO.getAllProductDetailById(pId);

// Lặp qua từng ProductDetail
        if (productList != null && !productList.isEmpty()) {
            for (ProductDetail product : productList) {
                if (product == null) {
                    System.err.println("Found a null product in the productList.");
                    continue; // Bỏ qua vòng lặp nếu product là null
                }
                // Lấy ID của từng ProductDetail
                String productDetailId = String.valueOf(product.getId());

                // Lấy các giá trị price, quantity, shortDescription từ form
                String price = request.getParameter("price_" + productDetailId);
                String quantity = request.getParameter("quantity_" + productDetailId);
                String shortDescription = request.getParameter("shortDescription_" + productDetailId);

                // Cập nhật thông tin ProductDetail
                product.setId(product.getId());
                product.setPrice(Integer.parseInt(price));
                product.setQuantity(Integer.parseInt(quantity));
                product.setShortDescription(shortDescription);
                productDetailDAO.insertDetail(product);

                // Lấy danh sách URL của ảnh từ form
                String[] imageUrls = request.getParameterValues("imageUrls_" + productDetailId + "[]");
                if (imageUrls != null) {
                    // Tạo danh sách đối tượng Image
                    List<Image> images = new ArrayList<>();
                    for (String url : imageUrls) {
                        if (url != null && !url.trim().isEmpty()) {
                            // Tạo đối tượng Image và đặt các giá trị
                            Image image = new Image();
                            image.setImage(url);  // Đặt URL của ảnh
                            image.setProductDetail(product);  // Liên kết ảnh với ProductDetail (sử dụng ProductDetail đã có)
                            images.add(image);  // Thêm ảnh vào danh sách
                        }
                    }

                    // Cập nhật danh sách ảnh vào cơ sở dữ liệu
                    ImageDAO imageDAO = new ImageDAO();  // Tạo DAO cho Image
                    for (Image img : images) {
                        imageDAO.insertImage(img);  // Thực hiện insert từng ảnh vào cơ sở dữ liệu
                    }
                }

                AttributeDAO attDao = new AttributeDAO();
                ArrayList<Attribute> attList = attDao.getAllAttribute();
                for (Attribute att : attList) {
                    String paramName = "attribute_" + productDetailId + "_" + att.getId();

                    // Lấy giá trị của thuộc tính từ request
                    String attributeValue = request.getParameter(paramName);

                    // Kiểm tra xem giá trị thuộc tính có hợp lệ không
                    if (attributeValue != null && !attributeValue.trim().isEmpty()) {
                        // Tạo đối tượng ProductAttribute
                        ProductAttribute productAttribute = new ProductAttribute();
                        ProductDetail productDetail = new ProductDetail();
                        productDetail.setId(product.getId());
                        productAttribute.setProductdetail(productDetail); // Gán ProductDetail
                        productAttribute.setAttribute(att); // Gán Attribute
                        productAttribute.setValue(attributeValue); // Gán giá trị thuộc tính

                        // Cập nhật hoặc chèn ProductAttribute vào cơ sở dữ liệu
                        productDetailDAO.addProductDetailAttribute(productAttribute);
                    }
                }

            }

// Sau khi xử lý xong, chuyển hướng hoặc thông báo
            // Điều hướng đến trang danh sách sản phẩm
        }
        session.removeAttribute("productId");
        response.sendRedirect(
                "readProduct");
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
