package Controllers;

import Entities.Materie;
import Utils.ConnectionController;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by dioni on 2/17/2017.
 */
public class MaterieController extends ConnectionController {
    public static void addNew(String nume,double multiplier){
        try{
            pst=conn.prepareStatement("insert into materie(name,multiplier) values(?,?)");
            pst.setString(1,nume);
            pst.setDouble(2,multiplier);
            pst.executeUpdate();
        }catch (Exception ex){

        }
    }
    public static void Update(int id,String name,double multiplier){

        try{
            pst=conn.prepareStatement("update materie set name=?,multiplier=? where id=?");
            pst.setString(1,name);
            pst.setDouble(2,multiplier);
            pst.setInt(3,id);
            pst.executeUpdate();
        }catch (Exception ex){

        }
    }
    public static Materie getByID(int id){
        Materie m=new Materie();
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from materie where id=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while(rs.next()){
                m.setId(rs.getInt("id"));
                m.setNume(rs.getString("name"));
                m.setMultiplier(rs.getDouble("multiplier"));
            }
        }catch (Exception ex){

        }
        return m;
    }
    public static ArrayList<Materie> getAll(){
        ArrayList<Materie> list=new ArrayList<Materie>();
        ResultSet rs;
        try{
            pst=conn.prepareStatement("select * from materie");
            rs=pst.executeQuery();
            while(rs.next()){
                Materie m =new Materie();
                m.setId(rs.getInt("id"));
                m.setNume(rs.getString("name"));
                m.setMultiplier(rs.getDouble("multiplier"));
                list.add(m);
            }
        }catch (Exception ex){

        }
        return list;
    }



}
