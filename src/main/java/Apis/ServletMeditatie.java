package Apis;

import Controllers.MaterieController;
import Controllers.MeditatieController;
import Entities.Meditatie;
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
@WebServlet(name = "ServletMeditatie")
public class ServletMeditatie extends HttpServlet {

    private static final String NEW = "/meditatie/new";
    private static final String JOINS = "/meditatie/joins";
    private static final String JOINP = "/meditatie/joinp";
    private static final String ENDSESSION = "/meditatie/ends";
    private static final String GETBYID = "/meditatie/getbyid";
    private static final String GETBYPROF = "/meditatie/getbyp";
    private static final String GETBYSTUD = "/meditatie/getbys";
    private static final String GETALL = "/meditatie/getall";

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
            int room=MeditatieController.AddNew(Integer.parseInt(request.getParameter("id_e")),Integer.parseInt(request.getParameter("id_p")));
            Meditatie m=MeditatieController.getByRoom(room);
            String json = new Gson().toJson(m);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        if(path.equals(JOINS)){
            MeditatieController.joinStud(Integer.parseInt(request.getParameter("room")),request.getParameter("key"),Integer.parseInt(request.getParameter("duration")));

        }
        if(path.equals(JOINP)){
            MeditatieController.joinProf(Integer.parseInt(request.getParameter("room")),request.getParameter("key"),Integer.parseInt(request.getParameter("duration")));

        }
        if(path.equals(ENDSESSION)){
            MeditatieController.endSession(Integer.parseInt(request.getParameter("room")));
        }
        if(path.equals(GETBYID)){
           Meditatie m= MeditatieController.getbyid(Integer.parseInt(request.getParameter("id")));
            String json = new Gson().toJson(m);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        if(path.equals(GETBYPROF)){
            ArrayList<Meditatie> list=MeditatieController.getByProf(Integer.parseInt("id"));
            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        if(path.equals(GETBYSTUD)){
            ArrayList<Meditatie> list=MeditatieController.getByStud(Integer.parseInt("id"));
            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        if(path.equals(GETALL)){
            ArrayList<Meditatie> list=MeditatieController.getAll();
            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}
