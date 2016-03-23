package appliLinkAnalysis;

import java.io.IOException;
import java.util.Properties;

public class ShareVariables {

	public String host ;
	public String user ;
	public String password;
	public int chargementId;
	

	public ShareVariables()
	{
		this.initShareVariable();
	}
	
	public void initShareVariable() {
		
		   
	    try {
	    	
	    	Properties configFile = new Properties();
			configFile.load(this.getClass().getClassLoader().getResourceAsStream("AppliLinkAnalysis.properties"));
			
			this.setHost(configFile.getProperty("HOST"));
			this.setUser(configFile.getProperty("USER"));
			this.setPassword(configFile.getProperty("PASSWORD"));
			
			//System.out.println(this.host);
			//System.out.println(this.user);
			//System.out.println(this.password);
			
		
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getChargementId() {
		return chargementId;
	}
	public void setChargementId(int chargementId) {
		this.chargementId = chargementId;
	}
	

	
}
