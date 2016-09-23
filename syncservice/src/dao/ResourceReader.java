package dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;



/**
 * @author Shawn
 * 取得properties
 */
public class ResourceReader {
	
	
	
	//private static Map map = new HashMap();	
	
	/**
	 * 取得properties相關資訊
	 * @return
	 * @throws IOException
	 */
	public static Map getMap(String file) throws IOException {
		Map map=new HashMap();
		StringBuilder path=new StringBuilder(System.getProperty("user.dir"));
		File f = new File(path+"/"+file);
		InputStream in = new BufferedInputStream(new FileInputStream(f)); 
		ResourceBundle bundle = new PropertyResourceBundle(in); 
		Enumeration enumeration = bundle.getKeys();		
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			map.put(key, bundle.getString(key));
		}
		return map;
	}	
}