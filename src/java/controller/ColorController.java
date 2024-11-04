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
@WebServlet(name = "ColorController", urlPatterns = {"/ColorController"})
public class ColorController extends HttpServlet {

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
            out.println("<title>Servlet ColorController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ColorController at " + request.getContextPath() + "</h1>");
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
            UserDAO user = new UserDAO();
            ColorDAO color = new ColorDAO();
            List<Color> listColor = new ArrayList<>();
            listColor = color.getAllColor();
            request.setAttribute("listColor", listColor);
            request.getRequestDispatcher("managecolordisplay.jsp").forward(request, response);
        }
        if (service.equalsIgnoreCase("updateColorRequest")) {
            int id = Integer.parseInt(request.getParameter("id"));
            ColorDAO color = new ColorDAO();
            Color col = color.findColorById(id);
            request.setAttribute("updateColordisplay", col);
            request.getRequestDispatcher("updatecolordisplay.jsp").forward(request, response);
        }
        if (service.equalsIgnoreCase("updatedone")) {
            ColorDAO color = new ColorDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");

            boolean checkUpdate = color.updateColor(id, name);
//            Brand brand1 = brandDAO.getBrandById(id);
//            request.setAttribute("updatebranddisplay", brand1);
            if (checkUpdate == true) {
                Color colorAfterUpdate = color.findColorById(id);
                String mess = "Update successfull!!";
                request.setAttribute("mess", mess);
                request.setAttribute("ColorController?service=listall", colorAfterUpdate);
            } else {
                Color colorAfterUpdate = color.findColorById(id);
                String mess = "Update failed!";
                request.setAttribute("mess", mess);
                request.setAttribute("ColorController?service=listall", colorAfterUpdate);
            }

            request.getRequestDispatcher("ColorController?service=listall").forward(request, response);

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
            if (!isDeleted) {
                request.setAttribute("mess", "You can't delete this color because there is a product using this color ");
            }

            // Sau khi xóa, điều hướng về trang danh sách brand hoặc trang bạn muốn
            request.getRequestDispatcher("ColorController?service=listall").forward(request, response);
        }
        if (service.equalsIgnoreCase("addColorRequest")) {
            // Chỉ hiển thị trang thêm cấu hình
            request.getRequestDispatcher("insertcolor.jsp").forward(request, response);
        } else if (service.equalsIgnoreCase("addColor")) {
            String name = request.getParameter("color");

            ColorDAO colorDAO = new ColorDAO();
            List<Color> listColor = colorDAO.getAllColor();
            boolean colorExists = false;

            // Kiểm tra xem màu đã tồn tại chưa
            for (Color color : listColor) {
                if (color.getName().equalsIgnoreCase(name)) {
                    colorExists = true;
                    break;
                }
            }

            if (colorExists) {
                // Nếu màu đã tồn tại, hiển thị thông báo
                request.setAttribute("mess", "Color is available");
            } else {
                // Nếu màu không tồn tại, thêm màu và hiển thị thông báo thành công
                boolean check = colorDAO.insertColor(name);
                if (check) {
                    request.setAttribute("mess", "Add Color successful!");
                } else {
                    request.setAttribute("mess", "Add Color failed!");
                }
            }

            // Chuyển hướng về trang danh sách màu
            request.getRequestDispatcher("ColorController?service=listall").forward(request, response);
        }

        // Chuyển tiếp đến trang để hiển thị thông báo
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
