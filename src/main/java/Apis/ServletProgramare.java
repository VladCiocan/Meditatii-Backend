package Apis;

import Controllers.ProgramareController;
import Controllers.UserController;
import Entities.Programare;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dioni on 2/27/2017.
 */
@WebServlet(name = "ServletProgramare")
public class ServletProgramare extends HttpServlet{
    private static final String NEW = "/programare/new";
    private static final String CANCEL = "/programare/cancel";
    private static final String APROBA = "/programare/aproba";

    private static final String GETUPPROF = "/programare/profesor/upcoming";
    private static final String GETUPSTUD = "/programare/student/upcoming";

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
            ProgramareController.New(Integer.parseInt(request.getParameter("id_e")),Integer.parseInt(request.getParameter("id_p")),Integer.parseInt(request.getParameter("duration")),request.getParameter("time"),Integer.parseInt(request.getParameter("room")));

        }
        if (path.equals(CANCEL)) {
            ProgramareController.Cancel(Integer.parseInt(request.getParameter("id")));

        }
        if(path.equals(APROBA)){
            ProgramareController.Update(Integer.parseInt(request.getParameter("id")),Integer.parseInt("status"));
        }
        if (path.equals(GETUPPROF)) {
            ArrayList<Programare> list=ProgramareController.getUpcommingP(Integer.parseInt(request.getParameter("id_p")));
            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }
        if (path.equals(GETUPSTUD)) {
            ArrayList<Programare> list=ProgramareController.getUpcommingS(Integer.parseInt(request.getParameter("id_e")));
            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }

    }
}
