package io;

import java.io.*;
import java.sql.*;

import dao.Conn;

public class ExportData {	
	
	public ExportData(String id, String sql, String uri, String user, String pass) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		
		File file = new File(System.getProperty("user.dir")+"/files/"+id+".csv");
		System.out.print(id);
		int cnt=0;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			con = Conn.getConnection(uri, user, pass);
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			ResultSetMetaData rsmd;
			StringBuilder sb;
			
			while (rs.next()) {
				cnt++;
				sb=new StringBuilder();
				rsmd = rs.getMetaData();
				for(int i=0; i<rsmd.getColumnCount(); i++){		
					if(rs.getString(i+1)!=null)
					sb.append(rs.getString(i+1).replace(",", "")+",");
				}				
				sb.delete(sb.length()-1, sb.length());
				writer.write(sb + "\n");
			}
			writer.flush();
			writer.close();
			writer = null;
			System.out.println(", 匯出"+cnt+"筆資料");
		} catch (SQLException e) {
			e.printStackTrace();;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}