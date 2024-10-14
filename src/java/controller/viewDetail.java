/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AttributeDAO;
import dal.ConfigurationDAO;
import dal.ImageDAO;
import dal.ProductAttributeDAO;
import dal.ProductDAO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Attribute;
import model.Configuration;
import model.Image;
import model.Product;
import model.ProductAttribute;
import model.ProductDetail;
import java.io.File;
import java.util.Collection;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
public class viewDetail extends HttpServlet {

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
            out.println("<title>Servlet viewDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet viewDetail at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        int productId = Integer.parseInt(request.getParameter("id")); // Nhận productId từ URL hoặc form

        ProductDetailDAO productDetailDAO = new ProductDetailDAO();

// Lấy danh sách các ProductDetail liên quan đến productId
        ArrayList<ProductDetail> productDetailList = productDetailDAO.getAllProductDetailById(productId);

        String desc = productDetailList.get(0).getDescription(); // Lấy mô tả của sản phẩm

        ProductAttributeDAO productAttributeDAO = new ProductAttributeDAO();
        AttributeDAO att = new AttributeDAO();
        ImageDAO img = new ImageDAO();
// Lấy danh sách các thuộc tính một lần duy nhất
        ArrayList<Attribute> attList = att.getAllAttribute(); // Lấy tất cả thuộc tính
        Map<Integer, ArrayList<ProductAttribute>> attListMap = new HashMap<>(); // Map cho ProductAttribute
        Map<Integer, ArrayList<Image>> imgListMap = new HashMap<>(); // Map cho ảnh

        for (ProductDetail productDetail : productDetailList) {
            ArrayList<ProductAttribute> pAList = productAttributeDAO.getAllProductAttribute(productDetail.getId());
            attListMap.put(productDetail.getId(), pAList);

            // Lấy danh sách ảnh cho từng ProductDetail
            ArrayList<Image> images = img.getAllImageByPDId(productDetail.getId());
            imgListMap.put(productDetail.getId(), images); // Thêm vào map ảnh
        }
        session.setAttribute("imgListMap", imgListMap);
        session.setAttribute("attListMap", attListMap);
// Đẩy thông tin ProductDetail và danh sách Attribute lên JSP để hiển thị
        session.setAttribute("pDList", productDetailList);
        session.setAttribute("attList", attList); // Gán attList sau vòng lặp
        session.setAttribute("desc", desc);

        session.setAttribute("updateProductId", productId);
        request.getRequestDispatcher("viewDetail.jsp").forward(request, response);

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
        int pId = (int) session.getAttribute("updateProductId");

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
                String description = request.getParameter("description");
                // Cập nhật thông tin ProductDetail
                product.setPrice(Integer.parseInt(price)); // Cập nhật giá
                product.setQuantity(Integer.parseInt(quantity)); // Cập nhật số lượng
                product.setShortDescription(shortDescription); // Cập nhật mô tả ngắn
                product.setDescription(description);
                // Cập nhật thông tin sản phẩm vào cơ sở dữ liệu
                productDetailDAO.updateProductDetail(product); // Giả sử phương thức updateProductDetail tồn tại

                Collection<Part> fileParts = request.getParts();
                ImageDAO imgDao = new ImageDAO();

// Lấy danh sách ảnh hiện tại để có ID cho việc cập nhật
                ArrayList<Image> existingImages = imgDao.getAllImageByPDId(product.getId());

                int existingImageIndex = 0; // Biến đếm cho danh sách ảnh hiện tại
                int newImageCount = 0;      // Biến đếm cho số ảnh mới

//xoa all old image
//                boolean isDeleteOldImages = false;
//                for (Part part : fileParts) {
//                    // Kiểm tra nếu part là file hình ảnh và không phải các part khác (ví dụ như form fields)
//                    if (part.getName().equals("imageFiles_" + productDetailId + "[]")) {
//                        String fileName = getFileName(part);
//                        if (fileName != null && part.getSize() > 0) {
//                            isDeleteOldImages = true;
//                            break;
//                        }
//                    }
//                }
//                if (isDeleteOldImages) {
//                    imgDao.deleteImageByProductDetailId(Integer.parseInt(productDetailId));
//                }

// Duyệt qua các phần tệp hình ảnh
                for (Part part : fileParts) {
                    // Kiểm tra nếu part là file hình ảnh và không phải các part khác (ví dụ như form fields)
                    if (part.getName().equals("imageFiles_" + productDetailId + "[]")) {
                        String fileName = getFileName(part);
                        if (fileName != null && part.getSize() > 0) {
                            // Lưu ảnh vào thư mục
                            String path = getServletContext().getRealPath("/images");
                            File imageFile = new File(path, fileName);
                            part.write(imageFile.getAbsolutePath());

                            // Tạo đối tượng Image và liên kết với ProductDetail
                            Image image = new Image();
                            image.setImage(fileName); // Đặt tên file ảnh (URL)
                            image.setProductDetail(product); // Gán ProductDetail cho ảnh

                            imgDao.insertImage(image);

//            if (existingImageIndex < existingImages.size()) {
//                // Cập nhật các ảnh đã tồn tại
//                image.setId(existingImages.get(existingImageIndex).getId()); // Lấy ID của ảnh hiện tại để cập nhật
//                imgDao.updateImage(image); // Cập nhật ảnh trong database
//                existingImageIndex++; // Tăng chỉ số cho ảnh hiện tại
//            } else {
//                // Thêm ảnh mới nếu không còn ảnh hiện có
//                imgDao.insertImage(image); // Thực hiện thêm ảnh mới
//                newImageCount++; // Đếm số ảnh mới thêm
//            }
                        }
                    }
                }

//         Cập nhật các thuộc tính sản phẩm
                AttributeDAO attDao = new AttributeDAO();
                ArrayList<Attribute> attList = attDao.getAllAttribute();

                for (Attribute att : attList) {
                    String paramName = "attribute_" + productDetailId + "_" + att.getId();

                    // Lấy giá trị thuộc tính từ request
                    String attributeValue = request.getParameter(paramName);

                    // Kiểm tra xem giá trị thuộc tính có hợp lệ không
                    if (attributeValue != null && !attributeValue.trim().isEmpty()) {
                        // Tạo đối tượng ProductAttribute
                        ProductAttribute productAttribute = new ProductAttribute();
                        productAttribute.setProductdetail(product); // Gán ProductDetail
                        productAttribute.setAttribute(att); // Gán Attribute
                        productAttribute.setValue(attributeValue); // Gán giá trị thuộc tính

                        // Cập nhật ProductAttribute vào cơ sở dữ liệu
                        productDetailDAO.updateProductDetailAttribute(productAttribute); // Gọi hàm update trong DAO
                    }
                }

            }

            // Sau khi xử lý xong, chuyển hướng hoặc thông báo
            // Điều hướng đến trang danh sách sản phẩm
            session.removeAttribute("updateProductId");
            response.sendRedirect("readProduct");
        }

    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 2, cd.length() - 1);
            }
        }
        return null;
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
