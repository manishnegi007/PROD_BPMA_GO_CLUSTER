package service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import common.Commons;
import common.XTrustProvider;

@Component
public class APIConsumerService
{
	public static ResourceBundle resProp = ResourceBundle.getBundle("errorMessages");
	//@Cacheable(value="maxserviceCache", key="#policyNo.concat('_OTP')", unless="#result == null" )
	public Map<String,String> getPolicyOtp(String policyNo)
	{
		String output = new String();
		StringBuilder result = new StringBuilder();
		//String DevMode = "Y";
		String pUrl = "https://gatewayuat.maxlifeinsurance.com/apimgm/dev/soa/policyotp/v1/real";
		String soaCorrelationId="ApiConsumer-"+policyNo+"-"+System.currentTimeMillis();
		String soaMsgVersion="1.0";
		String soaAppID = "BOT";
		String soaUserID = "BOTDEV123";
		String soaUserPswd = "Qk9UMTIzREVW";
		Map<String,String> otpDescMap=new HashMap<String, String>();
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
			try {
				writer.close();
			} catch (Exception e1) {
			}

			int apiResponseCode = conn.getResponseCode();
			
			System.out.println("apiResponseCode is : " + apiResponseCode + " Output is : " + result.toString());
			if (apiResponseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
				conn.disconnect();
				br.close();
				Map resultData = Commons.getGsonData(result.toString());
				String soaStatusCode = ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("soaStatusCode").toString();
				if (soaStatusCode != null && !soaStatusCode.equalsIgnoreCase("")
						&& soaStatusCode.equalsIgnoreCase("200")) {
					policyOtp = ((Map) ((Map) resultData.get("response")).get("responseData")).get("otp").toString();
					otpDescMap.put("policyotp", policyOtp);
					otpDescMap.put("Message",resProp.getString("getOtpSuccessfully").concat(" "+policyOtp));
				} 
				else if(soaStatusCode != null && !soaStatusCode.equalsIgnoreCase("")
						&& soaStatusCode.equalsIgnoreCase("999")) 
				{
					String soaMessage=((Map) ((Map) resultData.get("response")).get("responseData"))
							.get("soaMessage").toString();
					if("Unable to fetch client Id from Policy Info backend service.".equals(soaMessage))
					{
						otpDescMap.put("Message","Policy number "+ policyNo+" "+resProp.getString("PolicyNumberNotFound"));
					}
					else if("Unable to fetch Mobile number from Client Info backend service.".equals(soaMessage))
					{
						otpDescMap.put("Message",resProp.getString("MobileNumberRegardingPolicy"));
					}
					// Set message if required
					System.out.println("soaStatusCode is : " + soaStatusCode);
				}
				else
				{
					System.out.println("soaStatusCode is : " + soaStatusCode);
				}
				
			} 
			else 
			{
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
				conn.disconnect();
				br.close();
				Map resultData = Commons.getGsonData(result.toString());
				String soaStatusCode = ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("soaStatusCode").toString();
				if(soaStatusCode != null && !soaStatusCode.equalsIgnoreCase("")
						&& soaStatusCode.equalsIgnoreCase("999")) 
				{
					String soaMessage=((Map) ((Map) resultData.get("response")).get("responseData"))
							.get("soaMessage").toString();
					if("Unable to fetch client Id from Policy Info backend service.".equals(soaMessage))
					{
						otpDescMap.put("Message","Policy number "+ policyNo+" "+resProp.getString("PolicyNumberNotFound"));
					}
					else if("Unable to fetch Mobile number from Client Info backend service.".equals(soaMessage))
					{
						otpDescMap.put("Message",resProp.getString("MobileNumberRegardingPolicy"));
					}
					// Set message if required
					System.out.println("soaStatusCode is : " + soaStatusCode);
				}
				
				// Set message if required
			}
		} catch (Exception e) {
			System.out.println("We are in exception while calling API : " + soaCorrelationId + e);
		}
		
		
		return otpDescMap;
	}
	
	public Map getPolicyInfo(String policyNo) {
		String output = new String();
		StringBuilder result = new StringBuilder();
		//String DevMode = "Y";
		String pUrl = "https://gatewayuat.maxlifeinsurance.com/apimgm/dev/soa/policy360/policy360/policyctp/v1";
		String soaCorrelationId = "ApiConsumer-" + policyNo + "-" + System.currentTimeMillis();
		String soaMsgVersion = "1.0";
		String soaAppID = "BOT";
		String soaUserID = "BOTDEV123";
		String soaUserPswd = "Qk9UMTIzREVW";
		Map<String, String> map = new HashMap();
		String policyOtp = "";
		Map<String,Map> returnMap=new HashMap<String,Map>();
		HttpURLConnection conn = null;
		try {
			XTrustProvider trustProvider = new XTrustProvider();
			trustProvider.install();
			URL url = new URL(pUrl);

			/*if (DevMode != null && !DevMode.equalsIgnoreCase("") && DevMode.equalsIgnoreCase("Y")) {
				Proxy proxy = new Proxy(Proxy.Type.HTTP,
						new InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
				conn = (HttpURLConnection) url.openConnection(proxy);
			} else {
				
			}*/
			conn = (HttpURLConnection) url.openConnection();
			HttpsURLConnection.setFollowRedirects(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			StringBuilder requestdata = new StringBuilder();
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
			try {
				writer.close();
			} catch (Exception e1) {
			}

			int apiResponseCode = conn.getResponseCode();
			System.out.println("apiResponseCode is : " + apiResponseCode + " Outpot is : " + result.toString());
			String responseString = result.toString();
			if (apiResponseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
				conn.disconnect();
				br.close();
				Map resultData = Commons.getGsonData(result.toString());
				String policyBasePlanIdDesc = ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("BasicDetails")).get("policyBasePlanIdDesc").toString();
				String ctpAmt = ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("BasicDetails")).get("ctpAmt").toString();
				String polDueDate = ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("BasicDetails")).get("polDueDate").toString();
				map.put("policyBasePlanIdDesc", policyBasePlanIdDesc);
				map.put("ctpAmt", ctpAmt);
				map.put("polDueDate", convertDateFormat(polDueDate));
				
				//////////////////////*******************************////////////////////////////////////
				String policyInsuranceTypeCd=((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("BasicDetails")).get("policyInsuranceTypeCd").toString();
				
				if("N".equals(policyInsuranceTypeCd)||"F".equals(policyInsuranceTypeCd)||"D".equals(policyInsuranceTypeCd)||"C".equals(policyInsuranceTypeCd))
				{
					Map<String,String> fvMap=new HashMap();
					fvMap.put("fundValAsonDate", ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
							.get("PolicyMeasures")).get("fundValAsonDate").toString());
					fvMap.put("discontinuanceFund", ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
							.get("BasicDetails")).get("discontinuanceFund").toString());
					fvMap.put("Message",resProp.getString("InquiringFVTrue"));
					returnMap.put("FV", fvMap);
				}
				else if("8".equals("policyInsuranceTypeCd")||"1".equals(policyInsuranceTypeCd))
				{
					Map<String,String> fvMap=new HashMap();
					 fvMap.put("Message",resProp.getString("InquiringFVfalse"));
					 returnMap.put("FV", fvMap);
				}
				else
				{    Map<String,String> fvMap=new HashMap();
					 fvMap.put("Message",resProp.getString("InquiringFVfalse"));
					 returnMap.put("FV", fvMap);
				}
				
				if("8".equals("policyInsuranceTypeCd")||"1".equals(policyInsuranceTypeCd))
				{
					Map<String,String> csv=new HashMap();
					csv.put("Message",resProp.getString("cashSurrenderNotApplicable") );
					returnMap.put("CSV",csv);
					
				}
				
				try
				{
				if(Double.parseDouble(ctpAmt)==0)
				{
					Map<String,String> fvMap=new HashMap();
					fvMap.put("Message",resProp.getString("nextPremium1")+" "+polDueDate+" "+resProp.getString("nextPremium2"));
					returnMap.put("CTP",fvMap);
				}
				else
				{
					Map<String,String> fvMap=new HashMap();
					
					fvMap.put("Message",resProp.getString("dueAmountPolicy1")+" "+policyNo+" "+resProp.getString("dueAmountPolicy2")+" "+ctpAmt+" "+resProp.getString("dueAmountPolicy3")+" "+polDueDate);
					fvMap.put("ctpAmt",ctpAmt);
					fvMap.put("polDueDate",polDueDate);
					returnMap.put("CTP",fvMap);
				}
				
				
				
				
				}
				catch(Exception ec)
				{
					System.out.println(ec.getMessage());
				}
			    //////////////////////*******************************////////////////////////////////////
			} else 
			{
				
				Map<String,String> fvMap=new HashMap();
				fvMap.put("Message", "Getting error :"+apiResponseCode+" while calling backend service");
				returnMap.put("ErrorMessage",fvMap);
			}
		} catch (Exception e) {
			System.out.println("We are in exception while calling API : " + soaCorrelationId + e);
		}
		return returnMap;
	}
	
	private String convertDateFormat(String sourceFormat) {
		String formattedDate = null;
		try {
		DateFormat originalFormat = new SimpleDateFormat("yyyy MM, dd", Locale.ENGLISH);
		DateFormat targetFormat = new SimpleDateFormat("ddMMyyyy");
		Date date = originalFormat.parse(sourceFormat);
		formattedDate = targetFormat.format(date);  // 20120821
		return formattedDate;
		} catch (java.text.ParseException ex) {
		}
		return formattedDate;
	}
}






