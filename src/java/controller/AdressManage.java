/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AddressDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Address;
import model.User;

/**
 *
 * @author kieud
 */
@WebServlet(name = "AdressManage", urlPatterns = {"/address"})
public class AdressManage extends HttpServlet {

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
            out.println("<title>Servlet AdressManage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdressManage at " + request.getContextPath() + "</h1>");
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
        String mess = "";
        String action = request.getParameter("action");
        AddressDAO address = new AddressDAO();
        if ("default".equals(action)) {
            List<Address> list = address.getAddressesByUserId(getCurrentUser.getId());
            request.setAttribute("addresses", list);
            request.getRequestDispatcher("adressmanage.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Address adresss = new Address();
            adresss = address.getAddressById(id);
            request.setAttribute("address", adresss);
            request.getRequestDispatcher("updateadress.jsp").forward(request, response);
            
        } 
        

        
        
        
        else if ("addNew".equals(action)) {
            response.sendRedirect("addnewadress.jsp");
        }  else if ("delete".equals(action)) {
            int addressId = Integer.parseInt(request.getParameter("id"));
            address.deleteAddress(addressId);
            mess = "Delete succesful !";
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("address?id=" + getCurrentUser.getId() + "&action=default").forward(request, response);
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
         HttpSession session = request.getSession();
        User getCurrentUser = (User) session.getAttribute("user");
        String mess = "";
        String action = request.getParameter("action");
        AddressDAO address = new AddressDAO();
  if ("add".equals(action)) {
    List<Address> list = address.getAddressesByUserId(getCurrentUser.getId());
    String name = request.getParameter("name");
    String phone = request.getParameter("phone");
    String address2 = request.getParameter("address");
    String isdefault = request.getParameter("default");
    
    if (!(phone.matches("^\\d{10}$"))) {
        mess = "Phone number is a number and must have 10 digits!";
        request.setAttribute("mess", mess);
        request.getRequestDispatcher("updateadress.jsp").forward(request, response);
        return; // Ngừng xử lý sau khi forward
    }
    
    boolean temp = false;
    if (isdefault != null) {
        for (Address get : list) {
            if (get.isIsdefault()) {
                get.setIsdefault(false);
                address.updateAddress(get);
            }
        }
        
        // Thêm địa chỉ mới với isDefault = true
        Address address1 = new Address(list.size() + 1, name, phone, address2, true);
        address.insertAddress(address1, getCurrentUser.getId());
        mess = "Insert Successful!";
    } else {
        // Nếu không có địa chỉ mặc định, thêm địa chỉ mới với isDefault = false
        Address address1 = new Address(list.size() + 1, name, phone, address2, false);
        address.insertAddress(address1, getCurrentUser.getId());
        mess = "Insert Successful!";
    }

    request.setAttribute("mess", mess);
    response.sendRedirect("address?id=" + getCurrentUser.getId() + "&action=default");
}

        else if("updateDone".equalsIgnoreCase(action)){
            List<Address> list = address.getAddressesByUserId(getCurrentUser.getId());

            String isdefault = request.getParameter("default");
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address_raw = request.getParameter("address");
            
            if(!(phone.matches("^\\d{10}$"))){
                mess = "Phone number is a number and must have 10 number !";
                request.setAttribute("mess", mess);
                Address adresss = new Address();
                adresss = address.getAddressById(id);
            request.setAttribute("address", adresss);
            request.getRequestDispatcher("updateadress.jsp").forward(request, response);
            }
            else{
                mess = "";
                 boolean temp = false;
                if(isdefault!=null){
                for (int i = 0; i < list.size(); i++) {
                    Address get = list.get(i);
                    if(get.isIsdefault()==true){
                        get.setIsdefault(false);
                        address.updateAddress(get);
                    }
                }
                temp = true; 
            }
                 address.updateAddress(new Address(id, name,
                    phone, address_raw, 
                    temp));

                 request.setAttribute("mess", mess);
            response.sendRedirect("address?id="+ id+"&action=default");
            }
            
            
            
            
            
           
            
        }

    }
//        processRequest(request, response);

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
