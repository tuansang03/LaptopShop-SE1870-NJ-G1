/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

<<<<<<<< HEAD:src/java/controller/ChangeStatusOrderDoneAndDelete.java
import dal.OderDAO;
========
import dal.UserDAO;
>>>>>>>> 90b4ba02cfacebebe63f6fbd5b263ba19b2da29d:src/java/controller/VerifyOTPServlet1.java
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
<<<<<<<< HEAD:src/java/controller/ChangeStatusOrderDoneAndDelete.java
========
import jakarta.servlet.http.HttpSession;
import model.User;
>>>>>>>> 90b4ba02cfacebebe63f6fbd5b263ba19b2da29d:src/java/controller/VerifyOTPServlet1.java

/**
 *
 * @author LocPham
 */
<<<<<<<< HEAD:src/java/controller/ChangeStatusOrderDoneAndDelete.java
@WebServlet(name = "ChangeStatusOrderDoneAndDelete", urlPatterns = {"/changeStatusOrderDoneAndDelete"})
public class ChangeStatusOrderDoneAndDelete extends HttpServlet {
========
@WebServlet(name = "VerifyOTPServlet1", urlPatterns = {"/VerifyOTPServlet1"})
public class VerifyOTPServlet1 extends HttpServlet {
>>>>>>>> 90b4ba02cfacebebe63f6fbd5b263ba19b2da29d:src/java/controller/VerifyOTPServlet1.java

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
<<<<<<<< HEAD:src/java/controller/ChangeStatusOrderDoneAndDelete.java
            out.println("<title>Servlet ChangeStatusOrderDoneAndDelete</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeStatusOrderDoneAndDelete at " + request.getContextPath() + "</h1>");
========
            out.println("<title>Servlet VerifyOTPServlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyOTPServlet1 at " + request.getContextPath() + "</h1>");
>>>>>>>> 90b4ba02cfacebebe63f6fbd5b263ba19b2da29d:src/java/controller/VerifyOTPServlet1.java
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
        String oid_raw = request.getParameter("oid");
        String op = request.getParameter("op");

        int oid = Integer.parseInt(oid_raw);
        OderDAO oDAO = new OderDAO();

        oDAO.deleteOrderDetail(oid);
        oDAO.deleteOrder(oid);

        if (op.equals("failed")) {
            String action = "failed";
            request.setAttribute("action", action);
            response.sendRedirect("selectOrderbyStatus?action=" + action);
        } else if (op.equals("rejected")) {
            String action = "rejected";
            request.setAttribute("action", action);
            response.sendRedirect("selectOrderbyStatus?action=" + action);
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
<<<<<<<< HEAD:src/java/controller/ChangeStatusOrderDoneAndDelete.java
        String oid_raw = request.getParameter("oid");
        String action = request.getParameter("action");
        OderDAO oDAO = new OderDAO();
        int oid = Integer.parseInt(oid_raw);
        if (action.equals("done")) {
            oDAO.changeOrderStatus(action, oid);
        } else if (action.equals("failed")) {
            oDAO.changeOrderStatus(action, oid);
        }

        action = "intransit";
        request.setAttribute("action", action);
        response.sendRedirect("selectOrderbyStatus?action=" + action);
========
                String otpInput = request.getParameter("otp");
    HttpSession session = request.getSession();
    String otpStored = (String) session.getAttribute("otp");

    if (otpStored != null && otpStored.equals(otpInput)) {
        // OTP hợp lệ, chuyển hướng đến trang tiếp theo
        User u= (User) session.getAttribute("uRegister");
        UserDAO dao=new UserDAO();
        dao.insertUserStaff(u);
        session.removeAttribute("uRegister");
        session.removeAttribute("otp");
        request.setAttribute("success", "Register successfully!");
//        request.getRequestDispatcher("login.jsp").forward(request, response);
    } else {
        // OTP không hợp lệ, trả về trang OTP với thông báo lỗi
        request.setAttribute("error", "Invalid OTP. Please try again.");
        request.getRequestDispatcher("otp_verification1.jsp").forward(request, response);
    }
>>>>>>>> 90b4ba02cfacebebe63f6fbd5b263ba19b2da29d:src/java/controller/VerifyOTPServlet1.java
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
