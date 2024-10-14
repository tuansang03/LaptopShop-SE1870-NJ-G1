/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Product;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="searchProductByName", urlPatterns={"/searchProductByName"})
public class searchProductByName extends HttpServlet {
   
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
            out.println("<title>Servlet searchProductByName</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchProductByName at " + request.getContextPath () + "</h1>");
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
        // Khởi tạo đối tượng ProductDAO
        ProductDAO p = new ProductDAO();

        // Lấy giá trị từ trường tìm kiếm 'name' từ request
        String searchName = request.getParameter("name");

        // Lấy giá trị trang hiện tại từ request
        String index = request.getParameter("index");
        if (index == null) {
            index = "1"; // Mặc định trang đầu tiên nếu không có giá trị
        }
        int index1 = Integer.parseInt(index); // Chuyển đổi index thành số nguyên

        // Số sản phẩm trên mỗi trang
        int rowsPerPage = 9;

        // Gọi phương thức searchProductByName với tên tìm kiếm, trang hiện tại và số sản phẩm trên mỗi trang
        ArrayList<Product> pList = p.searchProductByName(searchName, index1, rowsPerPage);

        // Tính tổng số sản phẩm tìm được dựa trên tên tìm kiếm
        int count = p.countProductByName(searchName);

        // Tính số trang kết thúc (endPage)
        int endPage = count / rowsPerPage;
        if (count % rowsPerPage != 0) {
            endPage++; // Nếu còn dư sản phẩm, tăng thêm 1 trang
        }

        // Set các thuộc tính cho request để hiển thị trên trang JSP
        request.setAttribute("tag", index); // Đánh dấu trang hiện tại
        request.setAttribute("endPage", endPage); // Tổng số trang
        request.setAttribute("pList", pList); // Danh sách sản phẩm cho trang hiện tại
        request.setAttribute("searchName", searchName); // Lưu lại giá trị tên tìm kiếm

        // Chuyển hướng tới trang manageProduct.jsp để hiển thị kết quả
        request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
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
