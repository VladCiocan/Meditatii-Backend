package Controllers;

import Entities.UniqueID;
import Utils.ConnectionController;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

/**
 * Created by dioni on 3/8/2017.
 */
public class UniqueIDController extends ConnectionController{
    public static UniqueID New( int id){
        UniqueID u=new UniqueID();
        if(checkUUID(getUUid(id))<1){
            u.setUuid(UUID.randomUUID().toString());
            u.setUid(UserController.GetByID(id));
            Date d=new Date(getCurent().getTime());
            u.setExp(d);
            saveUUID(u);
        }else{
            u=getByUid(id);
        }




        return u;
    }
    public static int checkUUID(String uuid){
        int check=0;
        PreparedStatement pst;
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from login where uuid=?");
            pst.setString(1,uuid);
            rs=pst.executeQuery();

            Date d=new Date(getCurent().getTime());
            while(rs.next()){
                if(d.toString().equals(rs.getDate("exp").toString())){
                    check=1;
                }else{

                }
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return check;
    }
    public static String getUUid(int id){
        String c="";
        PreparedStatement pst;
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from login where uid=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while(rs.next()){
                c=rs.getString("uuid");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }

        return c;
    }
    public static void saveUUID(UniqueID u){
        PreparedStatement pst;
        try{
            pst=conn.prepareStatement("insert into login(uuid,uid,exp) values (?,?,?)");
            pst.setString(1,u.getUuid());
            pst.setInt(2,u.getUid().getId());
            pst.setDate(3,u.getExp());
            pst.executeUpdate();
        }catch(Exception ex){

        }
    }
    public static UniqueID getByUid(int id){
        UniqueID u= new UniqueID();
        PreparedStatement pst;
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from  login where uid=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while(rs.next()){
                u.setId(rs.getInt("id"));
                u.setExp(rs.getDate("exp"));
                u.setUuid(rs.getString("uuid"));
                u.setUid(UserController.GetByID(id));
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return u;
    }

}
