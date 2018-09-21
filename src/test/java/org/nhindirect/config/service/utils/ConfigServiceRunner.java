package org.nhindirect.config.service.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.mina.util.AvailablePortFinder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class ConfigServiceRunner 
{
	private static Server server;
	private static int HTTPPort;
	private static String serviceURL;
	
	public static void main(String[] args)
	{
		try
		{
			startConfigService();
			
			System.out.println("\r\nServer running....  Press Enter or Return to stop.");
			
			InputStreamReader input = new InputStreamReader(System.in);
			BufferedReader reader = new BufferedReader(input);
			
			try
			{
				reader.readLine();
				
				System.out.println("Shutting down server.  Wait 5 seconds for cleanup.");
				
				
				shutDownService();
			
				Thread.sleep(5000);
				
				System.out.println("Server stopped");
			}
			catch (Exception e)
			{
				
			}
		}
		catch (Exception e)
		{
			
		}
	}
	
	public synchronized static void startConfigService() throws Exception
	{
		
		if (server == null)
		{
			/*
			 * Setup the configuration service server
			 */
			HTTPPort = AvailablePortFinder.getNextAvailable( 8090 );
			server = new Server(HTTPPort);

			// certificate service
			new WebAppContext(server, "src/main/webapp", "/");
	    	
			server.start();
			
			serviceURL = "http://localhost:" + HTTPPort + "/";
			
			System.out.println("Config service running at " + serviceURL);
		
		}
	}
	
	public synchronized static boolean isServiceRunning()
	{
		return (server != null && server.isRunning());
	}
	
	public synchronized static void shutDownService() throws Exception
	{
		if (isServiceRunning())
		{
			server.stop();
			server = null;
		}
	}
	
	public synchronized static String getHPDServiceURL()
	{
		return serviceURL;
	}
}
