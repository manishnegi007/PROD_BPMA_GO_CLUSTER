package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import common.Commons;
import common.XTrustProvider;

@Component
public class APIConsumerService
{
	//@Cacheable(value="maxserviceCache", key="#policyNo.concat('_OTP')", unless="#result == null")
	public String getPolicyOtp(String policyNo)
	{
		String output = new String();
		StringBuilder result = new StringBuilder();
		String DevMode = "Y";
		String pUrl = "https://gatewayuat.maxlifeinsurance.com/apimgm/dev/soa/policyotp/v1/real";
		String soaCorrelationId="ApiConsumer-"+policyNo+"-"+System.currentTimeMillis();
		String soaMsgVersion="1.0";
		String soaAppID = "BOT";
		String soaUserID = "BOTDEV123";
		String soaUserPswd = "Qk9UMTIzREVW";
		
		String policyOtp="";

		HttpURLConnection conn = null;
		try 
		{
			XTrustProvider trustProvider=new XTrustProvider();
			trustProvider.install();
			URL url = new URL(pUrl);

			/*if(DevMode!=null && !DevMode.equalsIgnoreCase("") && DevMode.equalsIgnoreCase("Y"))
			{
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
				conn = (HttpURLConnection) url.openConnection(proxy);
			}
			else
			{
				
			}*/
			conn = (HttpURLConnection) url.openConnection();
			HttpsURLConnection.setFollowRedirects(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			StringBuilder requestdata=new StringBuilder();
			requestdata.append(" 	{	 ");
			requestdata.append(" 	   \"request\": {	 ");
			requestdata.append(" 	      \"header\": {	 ");
			requestdata.append(" 	         \"soaCorrelationId\": \"").append(soaCorrelationId).append("\",	 ");
			requestdata.append(" 	         \"soaMsgVersion\": \"").append(soaMsgVersion).append("\",	 ");
			requestdata.append(" 	         \"soaAppId\": \"").append(soaAppID).append("\",	 ");
			requestdata.append(" 	         \"soaUserId\": \"").append(soaUserID).append("\",	 ");
			requestdata.append(" 	         \"soaPassword\": \"").append(soaUserPswd).append("\"	 ");
			requestdata.append(" 	      },	 ");
			requestdata.append(" 	      \"requestData\": {	 ");
			requestdata.append(" 	         \"policyNumber\": \"").append(policyNo).append("\"	 ");
			requestdata.append(" 	      }	 ");
			requestdata.append(" 	   }	 ");
			requestdata.append(" 	}	 ");

			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(requestdata.toString());
			writer.flush();
			try {writer.close(); } catch (Exception e1) {}

			int apiResponseCode = conn.getResponseCode();
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while ((output = br.readLine()) != null) 
			{
				result.append(output);
			}
			conn.disconnect();
			br.close();
			//logger.info("apiResponseCode is : "+apiResponseCode+" Outpot is : "+result.toString());
			if(apiResponseCode == 200)
			{
				Map resultData = Commons.getGsonData(result.toString());
				String soaStatusCode = ((Map)((Map)resultData.get("response")).get("responseData")).get("soaStatusCode").toString();
				if(soaStatusCode!=null 
						&& !soaStatusCode.equalsIgnoreCase("") 
						&& soaStatusCode.equalsIgnoreCase("200")
						)
				{
					policyOtp = ((Map)((Map)resultData.get("response")).get("responseData")).get("otp").toString();
				}
				else
				{
					//Set message if required 
					//logger.info("soaStatusCode is : "+soaStatusCode);
				}
			}
			else
			{
				//Set message if required 
			}
		}
		catch (Exception e) 
		{
			System.out.println("We are in exception while calling API : "+soaCorrelationId+e);
		}
		return policyOtp;
	}
}

