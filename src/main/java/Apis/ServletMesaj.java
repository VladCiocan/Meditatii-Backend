package Apis;

import Controllers.MessageController;
import Controllers.TransactionController;
import Entities.Messsage;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dioni on 3/6/2017.
 */
@WebServlet(name = "ServletMesaj")
public class ServletMesaj extends HttpServlet {
    private static final String NEW = "/mesaj/new";
    private static final String GET = "/mesaj/get";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    private void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)  throws javax.servlet.ServletException, IOException {
        String path = request.getServletPath();
        System.out.println(path);
        if (path.equals(NEW)) {
           MessageController.newMesssage(Integer.parseInt(request.getParameter("room")),Integer.parseInt(request.getParameter("id")),request.getParameter("message"));
        }
        if (path.equals(NEW)) {
            ArrayList<Messsage> messages= MessageController.getUnread(Integer.parseInt(request.getParameter("room")));
            String json = new Gson().toJson(messages);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }

    }
}
