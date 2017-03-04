package Apis;

import Controllers.MaterieController;
import Controllers.ProfesorController;
import Entities.Profesor;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dioni on 2/23/2017.
 */
@WebServlet(name = "ServletProfesor")
public class ServletProfesor extends HttpServlet {
    private static final String NEW = "/profesor/new";
    private static final String UPDATE = "/profesor/update";
    private static final String DELETE = "/profesor/delete";
    private static final String GET = "/profesor/get";
    private static final String GETMAT = "/profesor/getbymat";
    private static final String GETUSER = "/profesor/getbyuser";
    private static final String GETALL = "/profesor/getall";


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
            ProfesorController.AddNew(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("mat")),request.getParameter("desc"));
        }
        if (path.equals(UPDATE)) {
            ProfesorController.Update(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("mat")),request.getParameter("desc"));
        }
        if (path.equals(DELETE)) {
            ProfesorController.Delete(Integer.parseInt(request.getParameter("id")));
        }
        if (path.equals(GET)) {
            Profesor p=ProfesorController.getById(Integer.parseInt(request.getParameter("id")));
            String json = new Gson().toJson(p);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }
        if (path.equals(GETMAT)) {
            ArrayList<Profesor> list=ProfesorController.GetByMat(Integer.parseInt(request.getParameter("mat")));

            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        if (path.equals(GETUSER)) {
            ArrayList<Profesor> list=ProfesorController.GetByUser(Integer.parseInt(request.getParameter("id_u")));
            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }
        if (path.equals(GETALL)) {

            ArrayList<Profesor> list =ProfesorController.GetAll();

            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }

    }
}
