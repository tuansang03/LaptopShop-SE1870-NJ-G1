/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.BrandDAO;
import dal.ColorDAO;
import dal.ConfigurationDAO;
import dal.ProductDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Color;
import model.User;

/**
 *
 * @author kieud
 */
@WebServlet(name="ColorController", urlPatterns={"/ColorController"})
public class ColorController extends HttpServlet {
   
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
            out.println("<title>Servlet ColorController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ColorController at " + request.getContextPath () + "</h1>");
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
        String service = request.getParameter("service");
        if (service == null) {
            service = "listall";
        }

        if (service.equalsIgnoreCase("listall")) {
            UserDAO user = new UserDAO();
            ColorDAO color = new ColorDAO();
            List<Color> listColor = new ArrayList<>();
            listColor = color.getAllColor();
            request.setAttribute("listColor", listColor);
            request.getRequestDispatcher("managecolordisplay.jsp").forward(request, response);
        }
        if (service.equalsIgnoreCase("updateBrandRequest")) {
            int id = Integer.parseInt(request.getParameter("id"));
            BrandDAO brandDAO = new BrandDAO();
            Brand brand = brandDAO.getBrandById(id);
            request.setAttribute("updatebranddisplay", brand);
            request.getRequestDispatcher("updatebranddisplay.jsp").forward(request, response);
        }
        if (service.equalsIgnoreCase("updatedone")) {
            BrandDAO brandDAO = new BrandDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Brand brand = new Brand(id, name);
            boolean checkUpdate = brandDAO.updateBrand(brand);
//            Brand brand1 = brandDAO.getBrandById(id);
//            request.setAttribute("updatebranddisplay", brand1);
            if (checkUpdate == true) {
                Brand brandAfterUpdate = brandDAO.getBrandById(id);
                String mess = "Update successfull!!";
                request.setAttribute("mess", mess);
                request.setAttribute("updatebranddisplay", brandAfterUpdate);
            } else {
                Brand brandAfterUpdate = brandDAO.getBrandById(id);
                String mess = "Update failed!";
                request.setAttribute("mess", mess);
                request.setAttribute("updatebranddisplay", brandAfterUpdate);
            }

            request.getRequestDispatcher("updatebranddisplay.jsp").forward(request, response);

        }

        if (service.equalsIgnoreCase("deleteColor")) {
            // Lấy id của brand từ request
            int id = Integer.parseInt(request.getParameter("id"));

            // Gọi DAO để xóa brand
             ColorDAO colorDAO = new ColorDAO();
            boolean isDeleted = colorDAO.deleteColor(id);

            // Kiểm tra xem xóa thành công hay không
            if (isDeleted) {
                request.setAttribute("mess", "Delete successfull!"); 
            } 

            // Sau khi xóa, điều hướng về trang danh sách brand hoặc trang bạn muốn
            request.getRequestDispatcher("managecolordisplay.jsp?service=listall").forward(request, response);
        }
        if (service.equalsIgnoreCase("addColorRequest")) {
            // Chỉ hiển thị trang thêm cấu hình
            request.getRequestDispatcher("insertcolor.jsp").forward(request, response);
        }
         
        else if (service.equalsIgnoreCase("addColor")) {
            String name = request.getParameter("color");
           
            ColorDAO colorDAO = new ColorDAO();
                boolean check = colorDAO.insertColor(name);

                if (check) {
                    request.setAttribute("mess", "Add Color successful!");
                     request.getRequestDispatcher("insertcolor.jsp").forward(request, response);
                } 
            } 
            // Chuyển tiếp đến trang để hiển thị thông báo
           
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