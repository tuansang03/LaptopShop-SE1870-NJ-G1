/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import static model.PasswordUtil.hashPassword;
import dal.ImageDAOS;
import dal.OderDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import model.Image;
import model.Order;
import model.OrderDetail;
import model.User;

/**
 *
 * @author kieud
 */
@WebServlet(name = "ProfileManage", urlPatterns = {"/profile"})
public class ProfileManage extends HttpServlet {

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
            out.println("<title>Servlet ProfileManage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileManage at " + request.getContextPath() + "</h1>");
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
        User getCurrentUser = (User) session.getAttribute("user");
        OderDAO order = new OderDAO();
        // lay gia tri tu jsp
        String profile = request.getParameter("profile");

        //====================================================
        if (profile.equals("info")) {

            Order getOrder = order.getNewestOrder(getCurrentUser.getId());
            request.setAttribute("orderInfo", getOrder);
            Order newOrder = order.getOneOrderNewest(getCurrentUser.getId());
            request.setAttribute("adress", newOrder.getAddress());
            request.setAttribute("phone", newOrder.getPhone());
            request.getRequestDispatcher("userprofile.jsp?profile=info").forward(request, response);

        }
        ImageDAOS image = new ImageDAOS();
        if (profile.equals("ordermanage")) {

            List<OrderDetail> listFilter = new ArrayList<>();
            List<OrderDetail> list = new ArrayList<>();
            try {
                list = order.getAllOrderDetails();
//                     List<Image> listImage = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    OrderDetail get = list.get(i);
                    if (get.getOrder() != null && get.getOrder().getUser() != null) {
                        if (get.getOrder().getUser().getId() == getCurrentUser.getId()) {
                            listFilter.add(get);
                        }
                    }
                }

//                     }
                request.setAttribute("orderDAO", order);

            } catch (SQLException ex) {
                Logger.getLogger(ProfileManage.class.getName()).log(Level.SEVERE, null, ex);
            }
//                     request.setAttribute("image", listImage);
            request.setAttribute("orderListInfo", listFilter);
            request.getRequestDispatcher("userprofile.jsp?profile=ordermanage").forward(request, response);
        }
        if ("orderdetail".equals(profile)) {
            int getOrderDetailId = Integer.parseInt(request.getParameter("id"));
            try {
                OrderDetail currentOrderDetail = order.getOrderDetailById(getOrderDetailId);
                Order currentOrder = order.getOrderById(getOrderDetailId);

                List<OrderDetail> listCurrentDetails = order.getOrderDetailsByOrderId(getOrderDetailId);
                List<Image> listImage = new ArrayList<>();
                ImageDAOS imgDAOS = new ImageDAOS();

                for (OrderDetail get : listCurrentDetails) {
                    Image getImage = imgDAOS.getOneImageByProductDetailID(get.getProductDetail().getId());
                    listImage.add(getImage);
                }

                request.setAttribute("imamgeList", listImage);
                request.setAttribute("currentOrderDetail", currentOrderDetail);
                request.setAttribute("currentOrder", currentOrder);
                request.setAttribute("currentOrderDetailList", listCurrentDetails);

                request.getRequestDispatcher("userprofile.jsp?profile=orderdetail&id=" + getOrderDetailId).forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ProfileManage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//   
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
        String profile = request.getParameter("profile");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        //========================
        if (action.equalsIgnoreCase("x")) {
            User currentUser = (User) session.getAttribute("user");
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            String hashedPassword = hashPassword(currentPassword);

            // Kiểm tra mật khẩu mới và hiện tại bằng nhau
            if (newPassword.equals(currentPassword)) {
                request.setAttribute("error", "Can't change match password");
                request.setAttribute("currentPassword", currentPassword);
                request.setAttribute("newPassword", newPassword);
                request.setAttribute("confirmPassword", confirmPassword);
                request.getRequestDispatcher("userprofile.jsp?profile=info&action=x").forward(request, response);
                return;
            }
//
            // Kiểm tra mật khẩu hiện tại
            UserDAO userDAO = new UserDAO();
            if (!userDAO.checkPassword(currentUser.getUserName(), hashedPassword)) {
                request.setAttribute("error", "Current password not correct");
                request.setAttribute("currentPassword", currentPassword);
                request.setAttribute("newPassword", newPassword);
                request.setAttribute("confirmPassword", confirmPassword);
                request.getRequestDispatcher("userprofile.jsp?profile=info&action=x").forward(request, response);
                return;
            }

            // Kiểm tra mật khẩu mới phải lớn hơn 8 ký tự
            if (newPassword.length() < 8) {
                request.setAttribute("error", "New password must be at least 8 characters long");
                request.setAttribute("currentPassword", currentPassword);
                request.setAttribute("newPassword", newPassword);
                request.setAttribute("confirmPassword", confirmPassword);
                request.getRequestDispatcher("userprofile.jsp?profile=info&action=x").forward(request, response);
                return;
            }
//
            // Kiểm tra mật khẩu mới và xác nhận mật khẩu
            if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("error", "New password and confirm password do not match");
                request.setAttribute("currentPassword", currentPassword);
                request.setAttribute("newPassword", newPassword);
                request.setAttribute("confirmPassword", confirmPassword);
                request.getRequestDispatcher("userprofile.jsp?profile=info&action=x").forward(request, response);
                return;
            }
            // Băm mật khẩu mới
            String hashedNewPassword = hashPassword(newPassword);

            // Cập nhật mật khẩu mới
            userDAO.updatePassword(currentUser.getUserName(), hashedNewPassword);
//
            // Thông báo thành công
            request.setAttribute("message", "Password change successful");
            request.getRequestDispatcher("userprofile.jsp?profile=info&action=x").forward(request, response);
//        }
        }       // DOI THONG TIN NGUOI DUNG
        if (action.equalsIgnoreCase("change")) {
            String adress = request.getParameter("adress");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String username = request.getParameter("username");
            UserDAO userDAO = new UserDAO();
            OderDAO order = new OderDAO();
            User getCurrentUser = (User) session.getAttribute("user");
            getCurrentUser.setEmail(email);
            getCurrentUser.setFullName(name);
            userDAO.updateUser2(getCurrentUser);

            order.updateNewestOrderContactInfo(getCurrentUser.getId(), phone, adress, username);
            Order newOrder = order.getOneOrderNewest(getCurrentUser.getId());
            request.setAttribute("adress", newOrder.getAddress());
            request.setAttribute("phone", newOrder.getPhone());
            request.setAttribute("mess", "Update Succesful!");
            request.getRequestDispatcher("userprofile.jsp?profile=info").forward(request, response);
        }

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

    public static void main(String[] args) {
    }

}
