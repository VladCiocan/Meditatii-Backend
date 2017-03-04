package Controllers;

import Entities.Meditatie;
import Utils.ConnectionController;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by dioni on 2/22/2017.
 */
public class MeditatieController extends ConnectionController{
    public static int AddNew(int id_elev,int id_prof){
        int last_entry=0;
        int room=0;
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select id from meditatii order by id ASC ");
            rs=pst.executeQuery();
            while(rs.next()){
                last_entry=rs.getInt("id");

            }
            System.out.println(last_entry);
        }catch(Exception ex){
            System.out.println(ex);
        }
        try {
            pst=conn.prepareStatement("insert into meditatii(room,id_elev,id_prof,status) values(?,?,?,?)");
            pst.setInt(1,last_entry+1);
            room=last_entry+1;
            pst.setInt(2,id_elev);
            pst.setInt(3,id_prof);
            pst.setInt(4,0);
            pst.executeUpdate();
            System.out.println(room);
        }catch (Exception ex){
            System.out.println(ex);
        }
        return room;
    }
    public static void joinStud(int room,String camKey ,int duration){
        int roomReady=0;
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from meditatii where room="+room);
            rs=pst.executeQuery();
            while(rs.next()){
                if(rs.getString("prof_key")!=null){
                    roomReady=1;
                }
            }
            if (roomReady>0){
                pst=conn.prepareStatement("update meditatii set start_time=?,end_time=?,student_key=?,status=? where room=?");
                pst.setTimestamp(1,getCurent());
                pst.setTimestamp(2,endSessionTime(getCurent(),duration));
                pst.setString(3,camKey);
                pst.setInt(4,4);
                pst.setInt(5,room);
                pst.executeUpdate();

            }else{
                pst=conn.prepareStatement("update meditatii set student_key=?,status=? where room=?");
                pst.setString(1,camKey);
                pst.setInt(2,2);
                pst.setInt(3,room);
                pst.executeUpdate();
            }
        }catch(Exception ex){

        }
    }
    public static void joinProf(int room,String camKey ,int duration){
        int roomReady=0;
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from meditatii where room="+room);
            rs=pst.executeQuery();
            while(rs.next()){
                if(rs.getString("student_key")!=null){
                    roomReady=1;
                }
            }
            if (roomReady>0){
                pst=conn.prepareStatement("update meditatii set start_time=?,end_time=?,prof_key=?,status=? where room=?");
                pst.setTimestamp(1,getCurent());
                pst.setTimestamp(2,endSessionTime(getCurent(),duration));
                pst.setString(3,camKey);
                pst.setInt(4,4);
                pst.setInt(5,room);
                pst.executeUpdate();

            }else{
                pst=conn.prepareStatement("update meditatii set prof_key=?,status=? where room=?");
                pst.setString(1,camKey);
                pst.setInt(2,3);
                pst.setInt(3,room);
                pst.executeUpdate();
            }
        }catch(Exception ex){

        }
    }
    public static void endSession(int room){
        try{
            pst=conn.prepareStatement("update meditatii set status=? where room =?");
            pst.setInt(1,5);
            pst.setInt(2,room);
            pst.executeUpdate();
        }catch(Exception ex){

        }
    }
    public static Meditatie getbyid(int id){
        Meditatie room=new Meditatie();
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from meditatii where id=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while(rs.next()){
                room.setId(rs.getInt("id"));
                room.setId_elev(rs.getInt("id_elev"));
                room.setId_prof(rs.getInt("id_prof"));
                room.setRoom(rs.getInt("room"));
                room.setStart_time(rs.getTimestamp("start_time"));
                room.setEnd_time(rs.getTimestamp("end_time"));
                room.setStatus(rs.getInt("status"));
                room.setStudent_key(rs.getString("student_key"));
                room.setProf_key(rs.getString("prof_key"));

            }
        }catch (Exception ex){

        }
        return room;
    }
    public static ArrayList<Meditatie> getByProf(int id){
        ArrayList<Meditatie> list= new ArrayList<Meditatie>();
        ResultSet rs;
        try{
            pst= conn.prepareStatement("select * from meditatii where id_prof=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while(rs.next()){
                Meditatie m=new Meditatie();
                m.setId(rs.getInt("id"));
                m.setId_elev(rs.getInt("id_elev"));
                m.setId_prof(rs.getInt("id_prof"));
                m.setRoom(rs.getInt("room"));
                m.setStart_time(rs.getTimestamp("start_time"));
                m.setEnd_time(rs.getTimestamp("end_time"));
                m.setStatus(rs.getInt("status"));
                m.setStudent_key(rs.getString("student_key"));
                m.setProf_key(rs.getString("prof_key"));
                list.add(m);
            }
        }catch (Exception ex){

        }
        return list;
    }
    public static ArrayList<Meditatie> getByStud(int id){
        ArrayList<Meditatie> list= new ArrayList<Meditatie>();
        ResultSet rs;
        try{
            pst= conn.prepareStatement("select * from meditatii where id_elev=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while(rs.next()){
                Meditatie m=new Meditatie();
                m.setId(rs.getInt("id"));
                m.setId_elev(rs.getInt("id_elev"));
                m.setId_prof(rs.getInt("id_prof"));
                m.setRoom(rs.getInt("room"));
                m.setStart_time(rs.getTimestamp("start_time"));
                m.setEnd_time(rs.getTimestamp("end_time"));
                m.setStatus(rs.getInt("status"));
                m.setStudent_key(rs.getString("student_key"));
                m.setProf_key(rs.getString("prof_key"));
                list.add(m);
            }
        }catch (Exception ex){

        }
        return list;
    }
    public static ArrayList<Meditatie> getAll(){
        ArrayList<Meditatie> list= new ArrayList<Meditatie>();
        ResultSet rs;
        try{
            pst= conn.prepareStatement("select * from meditatii");

            rs=pst.executeQuery();
            while(rs.next()){
                Meditatie m=new Meditatie();
                m.setId(rs.getInt("id"));
                m.setId_elev(rs.getInt("id_elev"));
                m.setId_prof(rs.getInt("id_prof"));
                m.setRoom(rs.getInt("room"));
                m.setStart_time(rs.getTimestamp("start_time"));
                m.setEnd_time(rs.getTimestamp("end_time"));
                m.setStatus(rs.getInt("status"));
                m.setStudent_key(rs.getString("student_key"));
                m.setProf_key(rs.getString("prof_key"));
                list.add(m);
            }
        }catch (Exception ex){

        }
        return list;
    }
    public static Meditatie getByRoom(int room){
        Meditatie m=new Meditatie();
        ResultSet rs;

        try{
            pst=conn.prepareStatement("select * from meditatii where room=?");
            pst.setInt(1,room);
            rs=pst.executeQuery();
            while(rs.next()){
                m.setId(rs.getInt("id"));
                m.setRoom(rs.getInt("room"));
                m.setProf_key(rs.getString("prof_key"));
                m.setStudent_key(rs.getString("student_key"));
                m.setStatus(rs.getInt("status"));
                m.setId_elev(rs.getInt("id_elev"));
                m.setId(rs.getInt("id_prof"));
                m.setStart_time(rs.getTimestamp("start_time"));
                m.setEnd_time(rs.getTimestamp("end_time"));

            }
        }catch (Exception ex){
            System.out.println(ex);
        }

        return m;
    }



}
