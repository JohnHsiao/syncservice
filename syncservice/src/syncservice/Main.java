package syncservice;

import io.Execute;
import io.ExportData;
import io.ImportData;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import dao.ResourceReader;

/**
 * 主程式
 * @author Shawn
 *
 */
public class Main {
	
	final static String uri="jdbc:mysql://192.192.230.161/CIS?useUnicode=true&characterEncoding=utf8";
	final static String user="root";
	final static String pass="spring";
	
	public static void main(String args[]) throws IOException {
		Date d1, d2;
		d1=new Date();
		Map<String, String>config=ResourceReader.getMap("conf.properties");		
		Map<String, String>sqlServer=ResourceReader.getMap("sqlServer.properties");
		Map<String, String>sqlClientInit=ResourceReader.getMap("sqlClientInit.properties");
		Map<String, String>sqlClient=ResourceReader.getMap("sqlClient.properties");
		
		Execute e;
		ExportData o;
		ImportData i;			
		
		//server端讀檔
		System.out.println("讀取conf.properties");
		Iterator iter = config.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry<String, String>entry = (Map.Entry) iter.next();
		    System.out.println(entry.getKey()+":"+entry.getValue());
		}
		System.out.println("------------------------");
		
		System.out.println("讀取sqlServer.properties");
		iter = sqlServer.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry<String, String>entry = (Map.Entry) iter.next();
		    o=new ExportData(entry.getKey(), entry.getValue(), uri, user, pass);
		} 
		System.out.println("------------------------");
		
		//client端初始
		System.out.println("讀取sqlClientInit.properties");
		iter = sqlClientInit.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry<String, String>entry = (Map.Entry) iter.next();
		    e=new Execute(entry.getKey(), entry.getValue(), config.get("uri"), config.get("user"), config.get("pass"));
		}
		System.out.println("------------------------");
		
		//client端匯入
		System.out.println("讀取sqlClient.properties");
		iter = sqlClient.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry<String, String>entry = (Map.Entry) iter.next();
		    i=new ImportData(entry.getKey(), entry.getValue(), config.get("uri"), config.get("user"), config.get("pass"));
		}	
		System.out.println("------------------------");
		d2=new Date();
		System.out.println("本次排程工作費時 "+(d2.getTime()-d1.getTime())+"毫秒");
		
		/*Timer timer=new Timer();
		timer.schedule(new TimerTask(){
		public void run(){		
		this.cancel();}},5000);*/
	}

}
