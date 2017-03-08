package Controllers;

import Entities.UniqueID;
import Entities.User;
import Utils.ConnectionController;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by dioni on 2/15/2017.
 */
public class UserController extends ConnectionController {
    public static void AddNew(String name,String email, String pass,int prof){
        if(prof>0){
            try{
                pst=conn.prepareStatement("insert into Users(name,email,pass,prof,credits,rate,status) values(?,?,?,?,?,?,?)");
                pst.setString(1,name);
                pst.setString(2,email);
                pst.setString(3,pass);
                pst.setInt(4,1);
                pst.setInt(5,0);
                pst.setInt(6,10);
                pst.setInt(7,0);
                pst.executeUpdate();
            }catch (Exception ex){
                logger.warn(ex.toString());
            }
        }
        if(prof==0){
            try{
                pst=conn.prepareStatement("insert into Users(name,email,pass,prof,credits,rate,status) values(?,?,?,?,?,?,?)");
                pst.setString(1,name);
                pst.setString(2,email);
                pst.setString(3,pass);
                pst.setInt(4,0);
                pst.setInt(5,0);
                pst.setInt(6,0);
                pst.setInt(7,0);
                pst.executeUpdate();
            }catch (Exception ex){
                logger.warn(ex.toString());
            }
        }

    }
    public static void UpdateUser(int id,String name,String email, String pass,int prof,int rate){
        try{
            pst=conn.prepareStatement("update Users set name=?,email=?,pass=?,prof=?,rate=? where id=?");
            pst.setString(1,name);
            pst.setString(2,email);
            pst.setString(3,pass);
            pst.setInt(4,prof);
            pst.setInt(5,rate);
            pst.setInt(6,id);
           pst.executeUpdate();
        }catch(Exception ex){
            logger.warn(ex.toString());
        }
    }
    public static void UserStatus(int id,int status){
        try{
            pst=conn.prepareStatement("update Users set status=? where id=?");
            pst.setInt(1,status);
            pst.setInt(2,id);
            pst.executeUpdate();
        }catch(Exception ex){
            logger.warn(ex.toString());
        }
    }
    public static UniqueID login(String email, String pass){
        User u =new User();
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from Users where email=? AND pass=?");
            pst.setString(1,email);
            pst.setString(2,pass);
            rs=pst.executeQuery();
            while(rs.next()){
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPass(rs.getString("pass"));
                u.setProf(rs.getInt("prof"));
                u.setCredits(rs.getInt("credits"));
                u.setRate(rs.getInt("rate"));
                u.setStatus(rs.getInt("status"));
            }
        }catch (Exception ex){
            logger.warn(ex.toString());
        }
        return UniqueIDController.New(u.getId());

    }
    public static User GetByID(int id){
        User user =new User();
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from Users where id=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPass(rs.getString("pass"));
                user.setProf(rs.getInt("prof"));
                user.setCredits(rs.getInt("credits"));
                user.setRate(rs.getInt("rate"));
                user.setStatus(rs.getInt("status"));
            }
        }catch (Exception ex){
            logger.warn(ex.toString());
        }

        return user;
    }
    public static ArrayList<User> GetByType(int type){
        ArrayList<User> list= new ArrayList<User>();
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from Users where prof=?");
            pst.setInt(1,type);
            rs=pst.executeQuery();
            while(rs.next()){
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPass(rs.getString("pass"));
                user.setProf(rs.getInt("prof"));
                user.setCredits(rs.getInt("credits"));
                user.setRate(rs.getInt("rate"));
                user.setStatus(rs.getInt("status"));
                list.add(user);
            }
        }catch (Exception ex){
            logger.warn(ex.toString());
        }
        return list;
    }
    public static ArrayList<User> GetByStatus(int status){
        ArrayList<User> list= new ArrayList<User>();
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from Users where status=?");
            pst.setInt(1,status);
            rs=pst.executeQuery();
            while(rs.next()){
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPass(rs.getString("pass"));
                user.setProf(rs.getInt("prof"));
                user.setCredits(rs.getInt("credits"));
                user.setRate(rs.getInt("rate"));
                user.setStatus(rs.getInt("status"));
                list.add(user);
            }
        }catch (Exception ex){
            logger.warn(ex.toString());
        }
        return list;
    }



}
