package io;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.Conn;

public class Execute {
	private Connection con = null;
	private PreparedStatement ps = null;

	public Execute(String id, String sql, String uri, String user, String pass) {
		System.out.print(id);
		try {
			con = Conn.getConnection(uri, user, pass);
			ps = con.prepareStatement(sql);
			java.io.File file = new java.io.File(System.getProperty("user.dir")+"/files/"+id+".csv");
			java.io.BufferedReader reader = new java.io.BufferedReader(new FileReader(file));			
			ps.execute(sql);			
			Conn.DisConDB(ps, con);
			System.out.println(", 已執行");
		} catch (Exception e) {
			System.out.println(","+e);
		}
	}

	

}
