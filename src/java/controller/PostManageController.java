/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.sql.Date;
import dal.AttributeDAO;
import dal.BrandDAO;
import dal.CategoryDAO;
import dal.PostDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import model.Attribute;
import model.Brand;
import model.Category;
import model.Post;
import model.User;

/**
 *
 * @author kieud
 */
@WebServlet(name = "PostManageController", urlPatterns = {"/postmanage"})
public class PostManageController extends HttpServlet {

    private final String MAIN_URL = "managepostdisplay.jsp";

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
            out.println("<title>Servlet PostManageController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostManageController at " + request.getContextPath() + "</h1>");
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
        doPost(request, response);

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
        String service = request.getParameter("service");

        if (service == null) {
            service = "listall";
        }
        if (service.equalsIgnoreCase("listall")) {
            UserDAO dao = new UserDAO();
            List<Post> postList = dao.getAllPostListD();
            request.setAttribute("listPost", postList);
            request.getRequestDispatcher(MAIN_URL).forward(request, response);
        }
        if (service.equalsIgnoreCase("addPostRequest")) {
            BrandDAO brand = new BrandDAO();
            //lay 2 cai attribute voi brand 
            CategoryDAO atr = new CategoryDAO();
            UserDAO o = new UserDAO();
            List<Brand> listBrand = brand.getAll();
            List<Category> listCategory = atr.getAll();
            List<User> listUser = o.getAll();
            request.setAttribute("brandList", listBrand);
            request.setAttribute("categoryList", listCategory);
            request.setAttribute("userList", listUser);
            request.getRequestDispatcher("insertpostdisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("addPost")) {
            //lay post list de lay size cua list de set id tu tang
            UserDAO dao = new UserDAO();
            List<Post> postList = dao.getAllPostListD();
            HttpSession session = request.getSession();
            User userSession = (User) (session.getAttribute("user"));
            int id = postList.size() + 1;
            int userId = userSession.getId();
            User user = dao.getUserByIdD(userId);

            int brand_request = Integer.parseInt(request.getParameter("brand"));
            Brand getBrand = dao.getBrandByIdD(brand_request);
            int cate_req = Integer.parseInt(request.getParameter("category"));
            Category category = dao.getCategoryByIdD(cate_req);

            String title = request.getParameter("title");
            String thum = request.getParameter("thum");
            String shortx = request.getParameter("short");
            String full = request.getParameter("full");

            PostDAO postx = new PostDAO();
            Post newPost = new Post(id, user, getBrand, category,
                    title, shortx, full, thum, null);

            postx.insertPost(newPost);
            request.setAttribute("mess", "Add Post successful!");
            BrandDAO brand = new BrandDAO();
            //lay 2 cai attribute voi brand 
            CategoryDAO atr = new CategoryDAO();
            UserDAO o = new UserDAO();
            List<Brand> listBrand = brand.getAll();
            List<Category> listCategory = atr.getAll();
            List<User> listUser = o.getAll();
            request.setAttribute("brandList", listBrand);
            request.setAttribute("categoryList", listCategory);
            request.setAttribute("userList", listUser);
            request.getRequestDispatcher("insertpostdisplay.jsp").forward(request, response);
        }
        if (service.equalsIgnoreCase("searchPost")) {
            String key = request.getParameter("search");
            UserDAO post = new UserDAO();
            List<Post> list = post.getPostsByTitleOrContent(key);
            request.setAttribute("listPost", list);
            request.getRequestDispatcher("managepostdisplay.jsp").forward(request, response);
        }

        if (service.equalsIgnoreCase("deletePost")) {
            String mess = "";
            String keyDelete_raw = request.getParameter("id");
            try {
                int keyDelete = Integer.parseInt(keyDelete_raw);
                PostDAO postDAO = new PostDAO();
                if (postDAO.deletePostById(keyDelete)) {
                    mess = "Delete succesful !";
                } else {
                    mess = "Delete fail, loi chua xac dinh";
                }
                UserDAO dao = new UserDAO();
                List<Post> postList = dao.getAllPostListD();
                request.setAttribute("listPost", postList);
                request.getRequestDispatcher(MAIN_URL).forward(request, response);

                request.setAttribute("mess", mess);
                request.getRequestDispatcher("managepostdisplay.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        //==============================Update=============================
        if (service.equalsIgnoreCase("updatePostRequest")) {
            int id = Integer.parseInt(request.getParameter("id"));
            PostDAO post = new PostDAO();
            UserDAO userDAO = new UserDAO();
            Post getPost = userDAO.getPostById(id);
            BrandDAO brand = new BrandDAO();
            //lay 2 cai attribute voi brand 
            CategoryDAO atr = new CategoryDAO();
            List<Brand> listBrand = brand.getAll();
            List<Category> listCategory = atr.getAll();
            request.setAttribute("updatepostdisplay", getPost);
            request.setAttribute("brandList", listBrand);
            request.setAttribute("categoryList", listCategory);
            request.getRequestDispatcher("updatepostdisplay.jsp").forward(request, response);
        }
        if (service.equalsIgnoreCase("updatedone")) {
            int id = Integer.parseInt(request.getParameter("id"));
            UserDAO userDAO = new UserDAO();
            Post getPost = userDAO.getPostById(id);
            //get gia tri thay doi
            String title = request.getParameter("title");
            String thum = request.getParameter("thum");
            int brandId = Integer.parseInt(request.getParameter("brand"));
            int categoryId = Integer.parseInt(request.getParameter("category"));
            String shortContent = request.getParameter("short");
            String fullContent = request.getParameter("full");
            Post post = new Post(id, getPost.getUser(), userDAO.getBrandByIdD(brandId), userDAO.getCategoryByIdD(categoryId), title, shortContent, fullContent, thum, getPost.getPublishDate());
            
            PostDAO postUpdate = new PostDAO();
            postUpdate.updatePost(post);
            String mess = "Update successfull!!";
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("postmanage?service=listall").forward(request, response);

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

}
