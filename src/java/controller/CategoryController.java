/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BrandDAO;
import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Category;

/**
 *
 * @author LOC
 */
public class CategoryController extends HttpServlet {

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
            out.println("<title>Servlet CategoryController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryController at " + request.getContextPath() + "</h1>");
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
        String service = request.getParameter("service");
        if (service == null) {
            service = "listall";
        }

        if (service.equalsIgnoreCase("listall")) {
            CategoryDAO categoryDAO = new CategoryDAO();
            List<Category> listCategory = new ArrayList<>();
            listCategory = categoryDAO.getAll();
            request.setAttribute("listCategory", listCategory);
            request.getRequestDispatcher("managecategorydisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("updateCategoryRequest")) {
            int id = Integer.parseInt(request.getParameter("id"));
            CategoryDAO categoryDAO = new CategoryDAO();
            Category category = new Category();
            category = categoryDAO.getCategoryById(id);
            request.setAttribute("updatecategorydisplay", category);
            request.getRequestDispatcher("updatecategorydisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("updatedone")) {
            String name = request.getParameter("name");
            int id = Integer.parseInt(request.getParameter("id"));
            CategoryDAO categoryDAO = new CategoryDAO();
            Category category = new Category(id, name);
            boolean check = categoryDAO.updateCategory(category);
            if (check == true) {
                Category categoryAfterUpdate = categoryDAO.getCategoryById(id);
                String mess = "Update successfull!!";
                request.setAttribute("mess", mess);
                request.setAttribute("updatecategorydisplay", categoryAfterUpdate);
            } else {
                Category categoryAfterUpdate = categoryDAO.getCategoryById(id);
                String mess = "Update failed!";
                request.setAttribute("mess", mess);
                request.setAttribute("updatecategorydisplay", categoryAfterUpdate);
            }
            request.getRequestDispatcher("updatecategorydisplay.jsp").forward(request, response);

        }

        if (service.equalsIgnoreCase("deleteCategory")) {
            int id = Integer.parseInt(request.getParameter("id"));
            CategoryDAO categoryDAO = new CategoryDAO();
            boolean check = categoryDAO.deleteCategoryById(id);
            if (check == true) {
                List<Category> listCategory = new ArrayList<>();
                listCategory = categoryDAO.getAll();
                request.setAttribute("listCategory", listCategory);
                request.setAttribute("mess", "Delete successfull");
            } else {
                List<Category> listCategory = new ArrayList<>();
                listCategory = categoryDAO.getAll();
                request.setAttribute("listCategory", listCategory);
                request.setAttribute("mess", "Delete failed, Exist product have this category!");
            }
            request.getRequestDispatcher("managecategorydisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("addCategoryRequest")) {
            response.sendRedirect("insertcategory.jsp");
        }

        if (service.equalsIgnoreCase("addCategory")) {
            String name = request.getParameter("name");
            List<Category> listCategory = new ArrayList<>();
            CategoryDAO categoryDAO = new CategoryDAO();
            boolean check = categoryDAO.insertCategory(name);
            if (check == true) {
                listCategory = categoryDAO.getAll();
                request.setAttribute("listCategory", listCategory);
                request.setAttribute("mess", "Add Brand successful!");
                request.getRequestDispatcher("insertcategory.jsp").forward(request, response);
            } else {
                request.setAttribute("mess", "Add Brand Failed!");
                request.getRequestDispatcher("insertcategory.jsp").forward(request, response);
            }
        }

        if (service.equalsIgnoreCase("searchCategory")) {
            CategoryDAO categoryDAO = new CategoryDAO();
            String key = request.getParameter("search");
            List<Category> listCategory = new ArrayList<>();
            listCategory = categoryDAO.getCategoriesByKeyword(key);
            request.setAttribute("listCategory", listCategory);
            request.getRequestDispatcher("managecategorydisplay.jsp").forward(request, response);
        }

        /**
         * Handles the HTTP <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
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
