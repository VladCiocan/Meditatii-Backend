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

/**
 * Created by Ciocan Vlad Florin on 2/22/2017.
 */
@WebServlet(name = "ServletLogin")
public class ServletLogin extends HttpServlet {
    private static final String NEW = "/user/login/new";
    private static final String LOGIN = "/user/login/login";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)  throws javax.servlet.ServletException, IOException  {
        String path = request.getServletPath();
        System.out.println(path);
        if(path.equals(NEW)) {
            UserController.AddNew(request.getParameter("name"),request.getParameter("email"),request.getParameter("pass"),Integer.parseInt(request.getParameter("prof")));

//modif test
        }
        if(path.equals(LOGIN)){
            User u=UserController.login(request.getParameter("email"),request.getParameter("pass"));
            if(u.getId()>0){
                String json = new Gson().toJson(u);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);

            }

        }


    }
}
