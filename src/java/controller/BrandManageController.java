/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import dal.BrandDAO;
import dal.ProductDAO;
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
import model.Brand;

/**
 *
 * @author LOC
 */
@WebServlet(name = "BrandManageController", urlPatterns = {"/BrandController"})
public class BrandManageController extends HttpServlet {

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
            out.println("<title>Servlet BrandController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BrandController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    private final String MAIN_URL = "managebranddisplay.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service");

        if (service == null) {
            service = "listall";
        }

        if (service.equalsIgnoreCase("listall")) {
            BrandDAO brandDAO = new BrandDAO();
            List<Brand> listBrand = new ArrayList<>();
            listBrand = brandDAO.getAll();
            request.setAttribute("listBrand", listBrand);
            request.getRequestDispatcher(MAIN_URL).forward(request, response);
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

        if (service.equalsIgnoreCase("deleteBrand")) {
            // Lấy id của brand từ request
            int id = Integer.parseInt(request.getParameter("id"));

            // Gọi DAO để xóa brand
            BrandDAO brandDAO = new BrandDAO();
            ProductDAO productDAO = new ProductDAO();
            boolean isDeleted = brandDAO.deleteBrandById(id);

            // Kiểm tra xem xóa thành công hay không
            if (isDeleted && productDAO.getProductsByBrand(id) == null) {

                request.setAttribute("mess", "Delete successfull!");
                List<Brand> listBrand = new ArrayList<>();
                listBrand = brandDAO.getAll();
                request.setAttribute("listBrand", listBrand);
            } else {
                List<Brand> listBrand = new ArrayList<>();
                listBrand = brandDAO.getAll();
                request.setAttribute("listBrand", listBrand);
                request.setAttribute("mess", "Delete failed, exist product have this brand!");
            }

            // Sau khi xóa, điều hướng về trang danh sách brand hoặc trang bạn muốn
            request.getRequestDispatcher("managebranddisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("searchBrand")) {
            String key = request.getParameter("search");
            BrandDAO brandDAO = new BrandDAO();
            List<Brand> listBrand = new ArrayList<>();
            listBrand = brandDAO.getBrandsByKeyword(key);
            request.setAttribute("listBrand", listBrand);
            request.getRequestDispatcher(MAIN_URL).forward(request, response);
        }

        if (service.equalsIgnoreCase("addBrandRequest")) {
            response.sendRedirect("insertbranddisplay.jsp");
        }
        
        if(service.equalsIgnoreCase("addBrand")){
            String name = request.getParameter("name");
            BrandDAO brandDAO = new BrandDAO();
            List<Brand> listBrand = new ArrayList<>();
            boolean check = brandDAO.insertBrand(name);
            if(check==true){
                listBrand = brandDAO.getAll();
                request.setAttribute("listBrand", listBrand);
                request.setAttribute("mess", "Add Brand successful!");
                request.getRequestDispatcher("insertbranddisplay.jsp").forward(request, response);
            }
            else{
                               request.setAttribute("mess", "Add Brand Failed!");
                request.getRequestDispatcher("insertbranddisplay.jsp").forward(request, response); 
            }
            
        }

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
        processRequest(request, response);
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
