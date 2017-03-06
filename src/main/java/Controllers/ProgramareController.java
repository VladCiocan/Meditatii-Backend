package Controllers;

import Entities.Materie;
import Entities.Meditatie;
import Entities.Programare;
import Entities.User;
import Utils.ConnectionController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;


/**
 * Created by dioni on 2/22/2017.
 */
public class ProgramareController extends ConnectionController {
    public static void New(int id_elev,int id_prof,int duration,String  time,int room){
        System.out.println(id_elev+","+id_prof+","+duration+","+time);
        try{

            pst=conn.prepareStatement("insert into programari(id_elev,id_prof,duration,time,status,room) values(?,?,?,?,?,?)");
            pst.setInt(1,id_elev);
            pst.setInt(2,id_prof);
            pst.setInt(3,duration);
            pst.setString(4,time);
            pst.setInt(5,1);
            pst.setInt(6,room);
            pst.executeUpdate();

        }catch(Exception ex){
System.out.println(ex);
        }
    }
    public static void Cancel(int id){
        try{
            pst=conn.prepareStatement("update programari set status=? where id=?");
            pst.setInt(1,0);
            pst.setInt(2,id);
            pst.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    public static ArrayList<Programare> getUpcommingP(int id_prof){
        ArrayList<Programare> list=new ArrayList<Programare>();
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from programari where id_prof=? ");
            pst.setInt(1,id_prof);

            rs=pst.executeQuery();
            while(rs.next()){
                Timestamp t=getCurent();
                System.out.println(t.toString()+"-"+rs.getString("time"));
                if(t.before(getFromString(rs.getString("time")))) {
                    Programare p = new Programare();
                    p.setId(rs.getInt("id"));
                    p.setId_prof(rs.getInt("id_prof"));
                    p.setId_elev(rs.getInt("id_elev"));
                    p.setDuration(rs.getInt("duration"));
                    p.setTime(rs.getString("time"));
                    p.setStatus(rs.getInt("status"));
                    p.setP(ProfesorController.getById(rs.getInt("id_prof")));
                    p.setRoom(rs.getInt("room"));
                    list.add(p);
                }
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return list;
    }
    public static ArrayList<Programare> getUpcommingS(int id_elev){
        ArrayList<Programare> list=new ArrayList<Programare>();
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from programari where id_elev=?");
            pst.setInt(1,id_elev);
            rs=pst.executeQuery();
            while(rs.next()){
                Timestamp t=getCurent();
System.out.println(t+"----------"+getFromString(rs.getString("time")));
                if(t.before(getFromString(rs.getString("time")))) {
                    Programare pr = new Programare();
                    pr.setId(rs.getInt("id"));
                    pr.setId_prof(rs.getInt("id_prof"));
                    pr.setId_elev(rs.getInt("id_elev"));
                    pr.setDuration(rs.getInt("duration"));
                    pr.setTime(rs.getString("time"));
                    pr.setStatus(rs.getInt("status"));
                    pr.setP(ProfesorController.getById(rs.getInt("id_prof")));
                    pr.setRoom(rs.getInt("room"));
                    list.add(pr);
                }
            }
        }catch(Exception ex){
System.out.println(ex);
        }
        return list;
    }
    public static  Programare getById(int id){
        Programare p=new Programare();
        PreparedStatement pst;
        ResultSet rs;
        try{
        pst=conn.prepareStatement("select * from programari where id=?");
        pst.setInt(1,id);
        rs=pst.executeQuery();
        while(rs.next()){


                p.setId(rs.getInt("id"));
                p.setId_prof(rs.getInt("id_prof"));
                p.setId_elev(rs.getInt("id_elev"));
                p.setDuration(rs.getInt("duration"));
                p.setTime(rs.getString("time"));
                p.setStatus(rs.getInt("status"));
                p.setP(ProfesorController.getById(rs.getInt("id_prof")));
                p.setRoom(rs.getInt("room"));

            }

    }catch(Exception ex){
        System.out.println(ex);
    }


        return p;
    }
    public static int GetPriceByRoom(int room){
        int credits=0;
        PreparedStatement pst;
        ResultSet rs;
        Meditatie m =MeditatieController.getByRoom(room);
        User p=UserController.GetByID(m.getId_prof());
        Programare prog=getById(m.getRoom());
        Materie mat=MaterieController.getByID(Integer.parseInt(m.getStudent_key().split("-")[3]));
credits=(int)mat.getMultiplier()*p.getRate()*(Integer.parseInt(m.getStudent_key().split("-")[3])/60);
        return credits;
    }
    public static void Update(int id,int status){
        try{
            pst=conn.prepareStatement("update programari set status=? where id=?");
            pst.setInt(1,status);
            pst.setInt(2,id);
            pst.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

}
