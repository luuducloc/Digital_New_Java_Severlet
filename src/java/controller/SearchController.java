package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Digital;


@WebServlet(name = "SearchController", urlPatterns = {"/search"})
public class SearchController extends HttpServlet {

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
        // get text search jsp về truyền vào biến txt
        String txt = request.getParameter("txtSearch");
        // get index từ phân trang trên jsp truyền vào biến index
        int index = Integer.parseInt(request.getParameter("index"));
        
        // get data từ DAO
        DAO db = new DAO();
        // get số lượng bài mình search được
        int count = db.countSearch(txt);
        int size = 3;
        //last page là số lượng trang
        int lastPage = count/size;
        //nếu count chia size dư thì cộng thêm lastpage
        if(count % size !=0){
            lastPage++;
        }
        ArrayList<Digital> list = db.searchResult(txt,index,size);
        
        Digital top1 = db.getTop();
        ArrayList<Digital> top5 = db.getTop5();
        
        //set data to jsp
        request.setAttribute("top1", top1);
        request.setAttribute("count", count);
        request.setAttribute("top5", top5);        
        request.setAttribute("list", list);
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("index", index);
        request.setAttribute("txt", txt);
        request.getRequestDispatcher("SearchPage.jsp").forward(request, response);
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
        processRequest(request, response);
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
