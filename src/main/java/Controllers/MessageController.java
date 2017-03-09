package Controllers;

import Utils.ConnectionController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Entities.Messsage;

/**
 * Created by dioni on 3/6/2017.
 */
public class MessageController extends ConnectionController {

    public static void newMesssage(int room,int user,String message){
        PreparedStatement pst;
        ResultSet rs;
        try{
            pst=conn.prepareStatement("insert into messages(room,user_id,timp,mesaj,status) values(?,?,?,?,?)");
            pst.setInt(1,room);
            pst.setInt(2,user);
            pst.setTimestamp(3,getCurent());
            pst.setString(4,message);
            pst.setInt(5,0);
            pst.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    public static void update(int id){
        PreparedStatement pst;
        try{
pst=conn.prepareStatement("update messages set status=? where id=?");
            pst.setInt(1,1);
            pst.setInt(2,id);
            pst.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    public static ArrayList<Messsage> getUnread(int room,int id){
        ArrayList<Messsage> list=new ArrayList<Messsage>();
        PreparedStatement pst;
        ResultSet rs;
        try{

            pst=conn.prepareStatement("select * from messages where room=? and status=? and user_id=?");
            pst.setInt(1,room);
            pst.setInt(2,0);
            pst.setInt(3,id);
            rs=pst.executeQuery();
            while(rs.next()){
                Messsage m =new Messsage();
                m.setId(rs.getInt("id"));
                m.setRoom(rs.getInt("room"));
                m.setDate(rs.getTimestamp("timp"));
                m.setMessage(rs.getString("mesaj"));
                m.setUser(rs.getInt("user_id"));
                m.setU(UserController.GetByID(rs.getInt("user_id")));
                list.add(m);

            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        for(Messsage m:list){
            update(m.getId());
        }
        return list;
    }
}
