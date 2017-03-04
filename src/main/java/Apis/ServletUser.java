package Apis;

import Controllers.UserController;
import Entities.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dioni on 2/22/2017.
 */
@WebServlet(name = "ServletUser")
public class ServletUser extends HttpServlet {
    private static final String UPDATE = "/user/update";
    private static final String STATUS = "/user/status";
    private static final String GETUSER = "/user/getuser";
    private static final String GETUBYTYPE = "/user/getusersbytype";
    private static final String GETUBYSTATUS = "/user/getusersbystatus";



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)  throws javax.servlet.ServletException, IOException {
        String path = request.getServletPath();
        System.out.println(path);
        if (path.equals(UPDATE)) {
            UserController.UpdateUser(Integer.parseInt(request.getParameter("id")),request.getParameter("name"),request.getParameter("email"),request.getParameter("pass"),Integer.parseInt(request.getParameter("prof")),Integer.parseInt(request.getParameter("rate")));

            /*String json = new Gson().toJson(u);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            */
        }
        if(path.equals(STATUS)){
            UserController.UserStatus(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("status")));

        }
        if(path.equals(GETUSER)){
            User u=UserController.GetByID(Integer.parseInt(request.getParameter("id")));
            String json = new Gson().toJson(u);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }
        if(path.equals(GETUBYTYPE)){
            ArrayList<User> list=UserController.GetByType(Integer.parseInt(request.getParameter("type")));
            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        if(path.equals(GETUBYSTATUS)){
                ArrayList<User> list=UserController.GetByStatus(Integer.parseInt(request.getParameter("status")));
                String json = new Gson().toJson(list);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
        }


    }
}
