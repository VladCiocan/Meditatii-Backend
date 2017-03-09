package Controllers;

import Entities.Notification;
import Utils.ConnectionController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by dioni on 3/9/2017.
 */
public class NotificationController extends ConnectionController{
    public static void newNot(int id,String message){
        PreparedStatement pst;
        try{
            pst=conn.prepareStatement("insert into notifications(user_id,message,date,status) values(?,?,?,?)");
            pst.setInt(1,id);
            pst.setString(2,message);
            pst.setTimestamp(3,getCurent());
            pst.setInt(1,0);
            pst.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    //stauses 0=unread,1=read,2=deleted
    public static void updateStatusNot(int id,int status){
        PreparedStatement pst;
        try{
            pst=conn.prepareStatement("update notifications set status=? where id=?");
            pst.setInt(1,status);
            pst.setInt(2,id);
            pst.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    public static ArrayList<Notification> getNot(int id){
        ArrayList<Notification> list=new ArrayList<Notification>();
        PreparedStatement pst;
        ResultSet rs;
        try{
            pst=conn.prepareStatement("SELECT * from notifications where user_id=? and status <> 2");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                Notification n=new Notification();
                n.setId(rs.getInt("id"));
                n.setDate(rs.getTimestamp("date"));
                n.setMessage(rs.getString("message"));
                n.setUser(rs.getInt("user_id"));
                list.add(n);
            }

        }catch (Exception ex){
            System.out.println(ex);
        }
        for(Notification n:list){
            updateStatusNot(n.getId(),1);
        }
        return list;
    }
    public static void newToAdmin(int id,String title,String message){
        PreparedStatement pst;
        try{
            pst=conn.prepareStatement("insert into toadmin(user_id,title,message,date) values(?,?,?,?)");
            pst.setInt(1,id);
            pst.setString(2,title);
            pst.setString(3,message);
            pst.setTimestamp(4,getCurent());
            pst.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }





}
