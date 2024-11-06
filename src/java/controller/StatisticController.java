/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BrandDAO;
import dal.StatisticDAO;
import dal.StatisticDAO.ProductSales;
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
import model.BrandProductCount;
import model.BrandProductPrice;
import model.RevenueAndQuantitySold;
import model.UserSales;

/**
 *
 * @author LocPham
 */
@WebServlet(name = "StatisticController", urlPatterns = {"/StatisticController"})
public class StatisticController extends HttpServlet {

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
            out.println("<title>Servlet StatisticController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticController at " + request.getContextPath() + "</h1>");
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
        
        if(service.equalsIgnoreCase("listall")){
            response.sendRedirect("statisticdisplay.jsp");
        }

        if (service.equalsIgnoreCase("listallz")) {
            String year_raw =request.getParameter("year");
            int year = Integer.parseInt(year_raw);
            StatisticDAO statisticDAO = new StatisticDAO();
            BrandDAO brandDao = new BrandDAO();
            List<Brand> listBrand = brandDao.getAll();

            // Sử dụng List để lưu trữ số lượng sản phẩm theo từng thương hiệu
            List<BrandProductCount> brandProductCounts = new ArrayList<>();
            List<BrandProductPrice> brandProductPrice = new ArrayList<>();

            for (Brand brand : listBrand) {
                String brandName = brand.getName();
                int productCount = statisticDAO.countProductsByBrandNameAndOrderStatusDone(brandName,year);
                brandProductCounts.add(new BrandProductCount(brandName, productCount));
            }
            
            for (Brand brand : listBrand) {
                String brandName = brand.getName();
                int price = statisticDAO.calculateRevenueByBrandAndYear(brandName,year);
                brandProductPrice.add(new BrandProductPrice(brandName, price));
            }


            // Lưu dữ liệu vào request
            request.setAttribute("year", year);
            request.setAttribute("brandProductCounts", brandProductCounts);
             request.setAttribute("brandProductPrice", brandProductPrice);

            // Chuyển hướng đến JSP
            request.getRequestDispatcher("statisticdisplay.jsp").forward(request, response);
        }
        
        if(service.equalsIgnoreCase("monthlyRevenue")){
            request.setAttribute("action", "action");
            request.getRequestDispatcher("statistic2display.jsp").forward(request, response);
        }
        
        
        if(service.equalsIgnoreCase("listByTime")){
            int year = Integer.parseInt(request.getParameter("year"));
            StatisticDAO statisticDAO = new StatisticDAO();
            
             int[] monthlyRevenue = statisticDAO.calculateMonthlyRevenueForYear(year);
             int[] monthlySold = statisticDAO.calculateMonthlyProductSalesForYear(year);
             List<RevenueAndQuantitySold> list = new ArrayList<>();
             for(int i = 0; i < 12; i++){
                 RevenueAndQuantitySold revenueAndQuantitySold = new RevenueAndQuantitySold(monthlyRevenue[i],monthlySold[i]);
                 list.add(revenueAndQuantitySold);
             }
             request.setAttribute("year", year);
             request.setAttribute("list", list);
             request.setAttribute("action", "action");
             request.getRequestDispatcher("statistic2display.jsp").forward(request, response);
        }
        
        if(service.equalsIgnoreCase("top5Product")){
//            StatisticDAO statisticDAO = new StatisticDAO();
//            List<ProductSales> list = statisticDAO.getTopSellingProducts();
////            list = statisticDAO.getTopSellingProducts();
//            request.setAttribute("list", list);
//            request.setAttribute("action2", "action2");
//            request.getRequestDispatcher("statistic3display.jsp").forward(request, response);
request.setAttribute("action2", "action2");
request.getRequestDispatcher("statistic3display.jsp").forward(request, response);
        }
        
        if(service.equalsIgnoreCase("top5Staff")){
            request.setAttribute("action", "actionz");
            request.getRequestDispatcher("statistic7display.jsp").forward(request, response);
        }
        
        if(service.equalsIgnoreCase("list")){
            int year = Integer.parseInt(request.getParameter("year"));
         StatisticDAO statisticDAO = new StatisticDAO();
            List<ProductSales> list = statisticDAO.getTopSellingProducts(year);
//            list = statisticDAO.getTopSellingProducts();
            request.setAttribute("list", list);
            request.setAttribute("action2", "action2");
                  request.setAttribute("year", year);
            request.getRequestDispatcher("statistic3display.jsp").forward(request, response);
      
        }
        
        if(service.equalsIgnoreCase("top3Staff")){
            String year_raw = request.getParameter("year");
            int year = Integer.parseInt(year_raw);
            String month_raw = request.getParameter("month");
            int month = Integer.parseInt(month_raw);
            StatisticDAO dao = new StatisticDAO();
            List<UserSales> list = new ArrayList<>();
            list = dao.getTop3UsersWithDoneOrdersAndRoleId2(year, month);
            request.setAttribute("year", year);
            request.setAttribute("month", month);
            request.setAttribute("list", list);
            request.setAttribute("action", "actionz");
            request.getRequestDispatcher("statistic7display.jsp").forward(request, response);
        }
    }
    
   
//    public static void main(String[] args) {
//        StatisticDAO statisticDAO = new StatisticDAO();
//        System.out.println(statisticDAO.calculateMonthlyRevenueForYear(2024));
//    }

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
