package io;

import java.io.FileReader;


import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.Conn;







import javax.sql.DataSource;

public class ImportData {
	
	private Connection con = null;
	private PreparedStatement ps = null;	

	public ImportData(String id, String sql, String uri, String user, String pass) {
		System.out.print(id);
		int cnt=0;
		try {
			con = Conn.getConnection(uri, user, pass);
			ps = con.prepareStatement(sql);
			java.io.File file = new java.io.File(System.getProperty("user.dir")+"/files/"+id+".csv");
			java.io.BufferedReader reader = new java.io.BufferedReader(new FileReader(file));
			String str = "";
			String arr[];
			while ((str = reader.readLine()) != null) {				
				arr= str.split(",");
				cnt++;
				for(int i=0; i<arr.length; i++) {
					ps.setString(i+1, arr[i]);
				}
				ps.executeUpdate();
			}
			Conn.DisConDB(ps, con);
			System.out.println(", 匯入"+cnt+"筆資料");
		} catch (java.sql.SQLException ex) {
			ex.printStackTrace();;
		} catch (java.io.IOException ex) {
			ex.printStackTrace();;
		}
	}	
}