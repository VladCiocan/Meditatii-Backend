package Entities;

import Utils.ConnectionController;

import java.sql.PreparedStatement;

/**
 * Created by dioni on 3/6/2017.
 */
public class SubscriberController extends ConnectionController {
    public static void New(String email){
        PreparedStatement pst;
        try{
            pst=conn.prepareStatement("insert into subscribers(email,timp) values(?,?)");
            pst.setString(1,email);
            pst.setTimestamp(2,getCurent());
            pst.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
