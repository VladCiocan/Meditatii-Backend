package Apis;

import Controllers.MaterieController;
import Controllers.UserController;
import Entities.Materie;
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
@WebServlet(name = "ServletMaterie")
public class ServletMaterie extends HttpServlet {

    private static final String NEW = "/materie/new";
    private static final String UPDATE = "/materie/update";
    private static final String GETBYID = "/materie/getbyid";
    private static final String GETALL = "/materie/getall";


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
            MaterieController.addNew(request.getParameter("name"),Double.parseDouble(request.getParameter("multiplier")));
        }
        if(path.equals(UPDATE)){
            MaterieController.Update(Integer.parseInt(request.getParameter("id")),request.getParameter("name"),Double.parseDouble(request.getParameter("multiplier")));
        }
        if(path.equals(GETBYID)){
            Materie m=MaterieController.getByID(Integer.parseInt(request.getParameter("id")));
            String json = new Gson().toJson(m);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        if(path.equals(GETALL)){
           ArrayList<Materie> list=MaterieController.getAll();
            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}
