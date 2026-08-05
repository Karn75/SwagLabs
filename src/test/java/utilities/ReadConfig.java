package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;
	
	public ReadConfig() {
		
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\user\\eclipse-workspace\\SwagLabs\\Configuration\\config.properties");
			prop = new Properties();
			prop.load(fis);
		}catch (Exception e) {
				System.out.println("Exception is "+e.getMessage());
		}
	}
	
	public String getURL() {
		String url = prop.getProperty("baseURL");
		return url;
	}
	
	public String getUsername() {
		String username = prop.getProperty("username");
		return username;
	}
	
	public String getPassword() {
		String password = prop.getProperty("password");
		return password;
	}
		
}
