package Controllers;

import Entities.Transactions;
import Entities.User;
import Utils.ConnectionController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by dioni on 2/22/2017.
 */
public class TransactionController extends ConnectionController {
    public static int  checkFunds(int idu,int sum){
        int valid=0;
        PreparedStatement pst;
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from Users where id=?");
            pst.setInt(1,idu);
            rs=pst.executeQuery();
            while(rs.next()){
                if((rs.getInt("credits")-sum)>=0){
                    valid=1;

                }
            }

        }catch (Exception ex){

        }

        return valid;
    }
    public static void payUser(int idu,int sum){
        PreparedStatement pst;
        User u=UserController.GetByID(idu);
        try{
            pst=conn.prepareStatement("update Users set credits=? where id=?");
            pst.setInt(1,u.getCredits()+sum);
            pst.setInt(2,idu);
            pst.executeUpdate();
        }catch (Exception ex){

        }
    }
    public static String SendFunds(int id_user,int credits,String desc,int id_p){
        String raspuns="Fonduri insuficiente";
        if(checkFunds(id_user,credits)>0) {
            try {
                pst = conn.prepareStatement("INSERT into transactions(id_user,credits,date,description,status,id_prof) values(?,?,?,?,?,?)");
                pst.setInt(1, id_user);
                pst.setInt(2, credits);
                pst.setTimestamp(3, getCurent());
                pst.setString(4, desc);
                pst.setInt(5, 0);
                pst.setInt(6,id_p);
                pst.executeUpdate();
                pst=conn.prepareStatement("update Users set credits=credits-"+credits+" where id="+id_user);
                pst.executeUpdate();
                raspuns="Operatie reusita";
            } catch (Exception ex) {

            }

        }
        return raspuns;
    }
    public static void UpdateStatus(int id,int status){
        if(status==1) {// 1= procesat
            try {
                pst = conn.prepareStatement("update transactions set status=? where id like %_"+id);
                pst.setInt(1, status);

                pst.executeUpdate();
            } catch (Exception ex) {

            }
            //Transactions t=TransactionController.getByID(id);
           //payUser(t.getId_prof(),t.getCredits());
        }else if(status==2){
            try {
                //2=anulat
                pst = conn.prepareStatement("update transactions set status=? where id=?");
                pst.setInt(1, status);
                pst.setInt(2, id);
                pst.executeUpdate();
            } catch (Exception ex) {

            }
        }
    }
    public static ArrayList<Transactions> getAll(){
        ArrayList<Transactions> list =new ArrayList<Transactions>();
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from transactions");
            rs=pst.executeQuery();
            while(rs.next()){
                Transactions t=new Transactions();
                t.setId(rs.getInt("id"));
                t.setCredits(rs.getInt("credits"));
                t.setId_user(rs.getInt("id_user"));
                t.setDate(rs.getTimestamp("date"));
                t.setDescription(rs.getString("description"));
                t.setStatus(rs.getInt("status"));
                list.add(t);
            }
        }catch (Exception ex){

        }
        return list;
    }
    public static Transactions getByID(int id){
        Transactions t=new Transactions();
        ResultSet rs;
        try{
        pst=conn.prepareStatement("select * from transactions where id=?");
            pst.setInt(1,id);
        rs=pst.executeQuery();
        while(rs.next()){

            t.setId(rs.getInt("id"));
            t.setCredits(rs.getInt("credits"));
            t.setId_user(rs.getInt("id_user"));
            t.setDate(rs.getTimestamp("date"));
            t.setDescription(rs.getString("description"));
            t.setStatus(rs.getInt("status"));

        }
    }catch (Exception ex){

    }

        return t;
    }

}
