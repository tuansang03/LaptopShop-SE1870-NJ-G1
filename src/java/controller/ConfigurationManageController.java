/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BrandDAO;
import dal.ConfigurationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Configuration;

/**
 *
 * @author LOC
 */
public class ConfigurationManageController extends HttpServlet {

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
            out.println("<title>Servlet ConfigurationManageController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfigurationManageController at " + request.getContextPath() + "</h1>");
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
            ConfigurationDAO configurationDAO = new ConfigurationDAO();
            List<Configuration> listConfiguration = new ArrayList<>();
            listConfiguration = configurationDAO.getAll();
            request.setAttribute("listConfiguration", listConfiguration);
            request.getRequestDispatcher("manageconfigurationdisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("editConfig")) {
            int id = Integer.parseInt(request.getParameter("id"));
            ConfigurationDAO configurationDAO = new ConfigurationDAO();
            Configuration configuration = configurationDAO.getConfigurationById(id);
            request.setAttribute("update", configuration);
            request.getRequestDispatcher("updateconfigurationdisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("updatedone")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");

            // Kiểm tra nếu tham số name null hoặc rỗng
            if (name == null || name.trim().isEmpty()) {
                request.setAttribute("mess", "Name cannot be empty");
                Configuration configuration = new Configuration(id, ""); // Để trống name
                request.setAttribute("update", configuration);
                request.getRequestDispatcher("updateconfigurationdisplay.jsp").forward(request, response);
                return;
            }

            Configuration c = new Configuration(id, name);
            ConfigurationDAO configurationDAO = new ConfigurationDAO();
            boolean check = configurationDAO.updateConfiguration(c);

            if (check) {
                request.setAttribute("mess", "Update successful");
            } else {
                request.setAttribute("mess", "Update failed");
            }

            Configuration configuration = configurationDAO.getConfigurationById(id);
            request.setAttribute("update", configuration);
            request.getRequestDispatcher("updateconfigurationdisplay.jsp").forward(request, response);
        }
        
        if(service.equalsIgnoreCase("deleteConfig")){
            int id = Integer.parseInt(request.getParameter("id"));
            ConfigurationDAO configurationDAO = new ConfigurationDAO();
            boolean check = configurationDAO.deleteConfiguration(id);
            List<Configuration> listConfiguration = new ArrayList<>();
            listConfiguration = configurationDAO.getAll();
            if(check == true){
                request.setAttribute("listConfiguration", listConfiguration);
                request.setAttribute("mess", "Delete successfull!");
                request.getRequestDispatcher("manageconfigurationdisplay.jsp").forward(request, response);
            }
            else{
                request.setAttribute("listConfiguration", listConfiguration);
                request.setAttribute("mess", "Delete failed!");
                request.getRequestDispatcher("manageconfigurationdisplay.jsp").forward(request, response);
            }
        }
        
if (service.equalsIgnoreCase("addConfig")) {
    // Chỉ hiển thị trang thêm cấu hình
    request.getRequestDispatcher("insertconfigurationdisplay.jsp").forward(request, response);
} 

if (service.equalsIgnoreCase("addConfigDone")) {
    String name = request.getParameter("name");
    
    if (name != null && !name.trim().isEmpty()) { // Kiểm tra xem name có hợp lệ không
        ConfigurationDAO configurationDAO = new ConfigurationDAO();
        boolean check = configurationDAO.insertConfiguration(name);
        
        if (check) {
            request.setAttribute("mess", "Add configuration successful!");
        } else {
            request.setAttribute("mess", "Add configuration failed!");
        }
    } else {
        request.setAttribute("mess", "Invalid configuration name!");
    }
    
    // Chuyển tiếp đến trang để hiển thị thông báo
    request.getRequestDispatcher("insertconfigurationdisplay.jsp").forward(request, response);
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
