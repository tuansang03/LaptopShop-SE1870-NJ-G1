/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.FeedbackDAOS;
import model.*;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author PHONG
 */
@WebServlet(name = "FeedbackManager", urlPatterns = {"/feedbackmanager"})
public class FeedbackManager extends HttpServlet {

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
            out.println("<title>Servlet FeedbackManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackManager at " + request.getContextPath() + "</h1>");
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
        FeedbackDAOS dao = new FeedbackDAOS();
        String rateselect = "all";
        String status = "all";
        try {
            rateselect = request.getParameter("op");
        } catch (Exception e) {
        }
        try {
            status = request.getParameter("status");
        } catch (Exception e) {
        }
        if (rateselect==null){
            rateselect="all";
        }
        if (status==null){
            status="all";
        }
        List<Feedback> feedbacklist = filterFeedback(dao.getAllFeedback(rateselect), dao.getFeedbackStatus(), status);
        List<Feedback> feedbackreply = dao.getAllFeedbackReply();
        request.setAttribute("feedbacklist", feedbacklist);
        request.setAttribute("feedbackreply", feedbackreply);
        request.setAttribute("op", rateselect);
        request.setAttribute("status", status);
        request.setAttribute("total", feedbacklist.size());
        request.getRequestDispatcher("managefeedback.jsp").forward(request, response);
    }

    protected List<Feedback> filterFeedback(List<Feedback> list, List<Integer> index, String status) {
        // Return all feedback if status is "all"
        if (status.equals("all")) {
            return list;
        }

        List<Feedback> fin = new ArrayList<>();

        if (status.equals("replied")) {
            // Add feedbacks that have IDs in the index list
            for (Feedback feedback : list) {
                if (index.contains(feedback.getId())) {
                    fin.add(feedback);
                }
            }
        } else {
            // Add feedbacks that do NOT have IDs in the index list
            for (Feedback feedback : list) {
                if (!index.contains(feedback.getId())) {
                    fin.add(feedback);
                }
            }
        }

        return fin;
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
