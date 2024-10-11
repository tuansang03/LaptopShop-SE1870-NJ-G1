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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
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
@MultipartConfig
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
        //Tạo đường dẫn lưu trữ 
        String path = getServletContext().getRealPath("/images");
   
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }

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
                for (Part part : request.getParts()) {
                    // Kiểm tra xem tên phần có bắt đầu bằng "imageFiles_" cộng với productDetailId không
                    if (part.getName().equals("imageFiles_" + productDetailId + "[]")) {
                        String fileName = getFileName(part); // Lấy tên file
                        if (fileName != null && part.getSize() > 0) {
                            File imageFile = new File(path, fileName);
                            part.write(imageFile.getAbsolutePath()); // Lưu ảnh vào thư mục

                            // Tạo đối tượng Image và lưu vào database
                            Image image = new Image();
                            image.setImage(fileName); // Lưu tên file vào database
                            image.setProductDetail(product); // Liên kết ảnh với ProductDetail
                            new ImageDAO().insertImage(image); // Chèn ảnh vào cơ sở dữ liệu
                        }
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

    private String getFileName(Part part) {
        // Lấy tiêu đề "content-disposition" từ phần
        String contentDisposition = part.getHeader("content-disposition");
        // Tìm kiếm tên tệp trong tiêu đề
        for (String item : contentDisposition.split(";")) {
            if (item.trim().startsWith("filename")) {
                // Tách tên tệp và loại bỏ các dấu nháy kép
                return item.substring(item.indexOf('=') + 2, item.length() - 1);
            }
        }
        return null; // Nếu không tìm thấy tên tệp, trả về null
    }

}
