package Controllers;

import java.sql.PreparedStatement;

import Utils.ConnectionController;

public class SubscriberController  extends ConnectionController{
	public static void addNew(String email){
		PreparedStatement pst;
		try {
			pst=conn.prepareStatement("insert into subscribers(email,date) values(?,?)");
			pst.setString(1, email);
			pst.setTimestamp(2, getCurent());
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
