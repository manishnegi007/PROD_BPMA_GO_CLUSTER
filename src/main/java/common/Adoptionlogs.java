package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.stereotype.Service;

@Service
public class Adoptionlogs 
{
	public String adoptionlogsCall(String sessionId, String ssoId, String action, String resolvedQuery)
	{
		String platform="BPMABOTPROD";
		String speech="NOTREQUIRED";
		ResourceBundle res = ResourceBundle.getBundle("errorMessages");
		HttpURLConnection conn = null;
		StringBuilder result = new StringBuilder();
		String output = ""; 
		String DevMode="N";
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(new Date()); // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, 5); // adds one hour
		cal.add(Calendar.MINUTE, 30);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		System.out.println(dateFormat.format(cal.getTime()));

		try
		{

			XTrustProvider trustProvider=new XTrustProvider();
			trustProvider.install();
			String serviceurl = res.getString("adoptionlogs");
			URL url = new URL(serviceurl);
			if(DevMode!=null && !"".equalsIgnoreCase(DevMode) && "Y".equalsIgnoreCase(DevMode))
			{
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
				conn = (HttpURLConnection) url.openConnection(proxy);
			}else{
				conn = (HttpURLConnection) url.openConnection();
			}
			HttpsURLConnection.setFollowRedirects(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			StringBuilder requestdata=new StringBuilder();
			requestdata.append("	{				");
			requestdata.append("		\"header\": {		");
			requestdata.append("		\"uniqueId\": \"\",		");
			requestdata.append("		\"creationTime\": \"\",	");
			requestdata.append("		\"userId\": \"\",	");
			requestdata.append("		\"password\": \"\"	");
			requestdata.append("	},				");
			requestdata.append("		\"payload\": {			");
			requestdata.append("	     \"transaction\": [{	");
			requestdata.append("			\"session_Id\": \""+sessionId+"\",");
			requestdata.append("			\"ssoId\": \""+ssoId+"\",");
			requestdata.append("			\"kpiAsked\": \""+action+"\",		");
			requestdata.append("			\"intentCalled\": \""+resolvedQuery+"\",");
			requestdata.append("			\"platform\": \""+platform+"\",		");
			requestdata.append("			\"loginTime\": \""+dateFormat.format(cal.getTime())+"\",");
			requestdata.append("			\"apiResponse\": \""+speech+"\" ");
			requestdata.append("		     }]			");
			requestdata.append("		}	");
			requestdata.append("	}	");
			System.out.println("External API Call FOR CALLING Adoptionlogs URL: START");
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(requestdata.toString());
			writer.flush();
			try {writer.close(); } 
			catch (Exception e1) 
			{
				System.out.println(e1);
			}
			int apiResponseCode = conn.getResponseCode();
			System.out.println("API Response Code Adoption logs"+apiResponseCode);
			if(apiResponseCode == 200)
			{
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) 
				{
					result.append(output);
				}
				conn.disconnect();
				br.close();
				
				System.out.println("External API Call FOR CALLING Adoptionlogs URL:: END");
				return "success";
			}
			else
			{
				System.out.println("something went worong to communicate Adoptionlogs and inset data in it");
				return "failure";
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return "Success";
	}

}
