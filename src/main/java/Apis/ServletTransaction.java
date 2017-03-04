package Apis;

import Controllers.ProgramareController;
import Controllers.TransactionController;
import Entities.Programare;
import Entities.Transactions;
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
@WebServlet(name = "ServletTransaction")
public class ServletTransaction extends HttpServlet {
    private static final String NEW = "/transaction/sendf";
    private static final String UPDATE = "/transaction/update";
    private static final String GETALL = "/transaction/getall";
    private static final String GETBYID = "/transaction/getbyid";



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
String message= TransactionController.SendFunds(Integer.parseInt(request.getParameter("id_e")),Integer.parseInt(request.getParameter("credits")),request.getParameter("desc"),Integer.parseInt(request.getParameter("id_p")));

            String json = new Gson().toJson(message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }
        if (path.equals(UPDATE)) {
TransactionController.UpdateStatus(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("status")));

        }
        if (path.equals(GETALL)) {
ArrayList<Transactions> list=TransactionController.getAll();

            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }
        if (path.equals(GETBYID)) {
Transactions t=TransactionController.getByID(Integer.parseInt(request.getParameter("id")));

            String json = new Gson().toJson(t);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }

    }
}
