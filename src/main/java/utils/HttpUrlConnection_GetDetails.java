package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.net.URL;
import common.XTrustProvider;
import java.util.ResourceBundle;
import javax.net.ssl.HttpsURLConnection;
import java.util.*;	

public class HttpUrlConnection_GetDetails 
{
  public String getUserDetail(String segment, String ssoId)
	{
    ResourceBundle res = ResourceBundle.getBundle("errorMessages");
		HttpURLConnection conn = null;
		StringBuilder result = new StringBuilder();
		String output = ""; 
		String DevMode="N";
    try
		{

			XTrustProvider trustProvider=new XTrustProvider();
			trustProvider.install();
			String serviceurl = res.getString("servicegetUserDetail");
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
			StringBuilder requestdata = new StringBuilder();
			requestdata.append("	{	");
			requestdata.append("	  \"header\": {	");
			requestdata.append("	    \"correlationId\": \""+uniqueId+"\",	");
			requestdata.append("	    \"msgVersion\": \"\",	");
			requestdata.append("	    \"appId\": \"\",	");
			requestdata.append("	    \"userId\": \"\",	");
			requestdata.append("	    \"password\": \"\",	");
			requestdata.append("	    \"rollId\":\"\"	");
			requestdata.append("	  },	");
			requestdata.append("	  \"payload\": {	");
			requestdata.append("	    \"segment\": \""+segment+"\",	");
			requestdata.append("	    \"ssoid\": \""+ssoId+"\"");
			requestdata.append("	  }	");
			requestdata.append("	}	");
			System.out.println("External API Call : START : SSO_Validation");
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(requestdata.toString());
			writer.flush();
			try {writer.close(); } 
			catch (Exception e1) 
			{
				System.out.println(e1);
			}
			int apiResponseCode = conn.getResponseCode();
			if(apiResponseCode == 200)
			{
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) 
				{
					result.append(output);
				}
				conn.disconnect();
				br.close();
				System.out.println("httpConnection_response_mlab_Insert Result:- " +result.toString());
				System.out.println("External API Call : START : SSO_Validation");
				return "success";
			}
			else
			{
				System.out.println("something went worong to communicate mlab and inset data in it");
				return "failure";
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
    return result.toString();
  }
}
