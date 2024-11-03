/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import model.SendMailSSL;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "reset", urlPatterns = {"/reset"})
public class reset extends HttpServlet {

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
            out.println("<title>Servlet reset</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet reset at " + request.getContextPath() + "</h1>");
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("username", username);
        response.sendRedirect("resetpassword.jsp");
    }

    public String getRandom() {
        Random rd = new Random();

        // Chữ cái hoa từ A đến Z (65 - 90 trong mã ASCII)
        char letter = (char) (rd.nextInt(26) + 'A');
        // Ký tự ngẫu nhiên từ ! đến ~ trong mã ASCII (33 - 126)
        char specialChar = (char) (rd.nextInt(94) + 33);
        // Số từ 0 đến 9
        int number = rd.nextInt(10);

        // Tạo chuỗi ngẫu nhiên ít nhất 8 ký tự
        StringBuilder randomString = new StringBuilder();
        randomString.append(letter).append(specialChar).append(number);

        // Bổ sung thêm các ký tự ngẫu nhiên cho đến khi đủ 8 ký tự
        while (randomString.length() < 8) {
            int choice = rd.nextInt(3);
            switch (choice) {
                case 0:
                    randomString.append((char) (rd.nextInt(26) + 'A')); // Chữ cái hoa
                    break;
                case 1:
                    randomString.append((char) (rd.nextInt(94) + 33)); // Ký tự đặc biệt
                    break;
                case 2:
                    randomString.append(rd.nextInt(10)); // Số
                    break;
            }
        }

        // Trộn ngẫu nhiên các ký tự trong chuỗi
        char[] charArray = randomString.toString().toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            int randomIndex = rd.nextInt(charArray.length);
            char temp = charArray[i];
            charArray[i] = charArray[randomIndex];
            charArray[randomIndex] = temp;
        }

        return new String(charArray);
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
