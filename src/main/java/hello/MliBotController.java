package hello;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.Commons;
import  service.APIConsumerService;
import  common.XTrustProvider;
import java.net.URL;
import java.net.Proxy;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Controller
@RequestMapping("/webhook")
public class MliBotController{

	public static ResourceBundle resProp = ResourceBundle.getBundle("errorMessages");
        public static Map<String, Map<String,String>> sessionMap = new ConcurrentHashMap<String, Map<String,String>>();
	public static Map<String, Map<String,String>> sessionMapcontainssoinfo = new ConcurrentHashMap<String, Map<String,String>>();

	@Autowired
	APIConsumerService aPIConsumerService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody WebhookResponse webhook(@RequestBody String obj, Model model, HttpSession httpSession) {
		//WebhookResponse response = new WebhookResponse();
		System.out.println("CameInside :- Controller: Webhook");
		
		String productType="";
		String planType="";
		String period="";
		String channel="";
		String sessionId="";
		String userOTP="";
		String speech="";
		String cachePeriod=""; String cashplanType=""; String cashchannel=""; String cashproductType="";

		WebhookResponse response = new WebhookResponse();
		MliBotController mliBotController= new MliBotController();
		try 
		{
			System.out.println("WebhookResponse API START");
			JSONObject object = new JSONObject(obj);
			String actionperformed = object.getJSONObject("result").get("action")+"";
			sessionId = object.get("sessionId")+"";
			try{
				userOTP = object.getJSONObject("result").getJSONObject("parameters").get("number")+"";
			}catch(Exception e){System.out.println("Not getting OTP");}
			String ssoId="";
		        
			if("SSO.Validation".equalsIgnoreCase(actionperformed))
			{
				System.out.println("SSOValidation API START");
				ssoId = object.getJSONObject("result").getJSONObject("parameters").get("SSOID")+"";
				sessionId=object.get("sessionId")+"";
				System.out.println("APICallSSOValidation API CALL START");
				Map<String, Map<String,String>> returnmap = mliBotController.APICallSSOValidation(ssoId, sessionId );
				System.out.println("APICallSSOValidation API CALL END");
				String SoaStatus="";
				String PhoneStatus="";
				Map<String,String> cashMap= returnmap.get(sessionId);
				SoaStatus=cashMap.get("SoaStatus");
				PhoneStatus=cashMap.get("PhoneStatus");
				System.out.println("SSO Validation API END");
				if("success".equalsIgnoreCase(SoaStatus))
				{
				speech="I need to verify the OTP which was sent on your registered mobile number. Please enter it here";
				}
				else if("NotAvail".equalsIgnoreCase(PhoneStatus))
				{
					speech="Your PhoneNo. is not registered with us! Please Enter a registered PhoneNo.";
				}
				else if("Failure_API_1".equalsIgnoreCase(SoaStatus) || "Failure_API_2".equalsIgnoreCase("SoaStatus"))
				{
					speech="Invalid Credentials! Please Enter a Valid Credentials";
				}
				else
				{
					speech="Oops! I could not find any registered mobile number for this SSO";
				}
			}
			else if("nb.OTP.Validation".equalsIgnoreCase(actionperformed))
			{
				System.out.println("OTP Validation API START");
				String AgentName="";
				String cashOTP="";
				if(sessionMapcontainssoinfo.containsKey(sessionId))
				{
					Map<String,String> cashMap= sessionMapcontainssoinfo.get(sessionId);
					cashOTP=cashMap.get("otp");
					System.out.println(cashOTP);
					AgentName=cashMap.get("AgentName");
					if(cashOTP.equalsIgnoreCase(userOTP))
					{
						speech="Hi "+AgentName+" How can i help you";
						cashMap.put("Validation", "success");
						System.out.println("OTP Validation API END");
					}
					else
					{
						speech="Invalid OTP Entered! "
								+ "The OTP you have provided does not match. Please provide valid OTP that has been sent to your registered mobile number.";
					}
				}
				else
				{
					speech="Please Validate SSO Credentials For Further Process";
				}
			}
			else if("close.conversation".equalsIgnoreCase(actionperformed))
			{
				
				if(sessionMapcontainssoinfo.containsKey(sessionId))
				{
					sessionMapcontainssoinfo.remove(sessionId);
					System.out.println("close conversation");
					speech = "Thank you for contacting Max Life. Have a great day!";
				}
				else{
					speech = "Thank you for contacting Max Life. Have a great day!";
				}
			}
			else
			{
				if(sessionMapcontainssoinfo.containsKey(sessionId))
				{
					String Validation="";
					Map<String,String> cashMap= sessionMapcontainssoinfo.get(sessionId);
					Validation=cashMap.get("Validation");
					if("success".equalsIgnoreCase(Validation))
					{
						try{
							channel = object.getJSONObject("result").getJSONObject("parameters").getString("Channel")+"";
						}catch(Exception e)
						{
							
							channel = "";
						}
						try{
							productType = object.getJSONObject("result").getJSONObject("parameters").getString("ProductType")+"";
						}catch(Exception e)
						{
							
							productType="";
						}
						try{
							period =  object.getJSONObject("result").getJSONObject("parameters").getString("Period")+""; 
						}catch(Exception e)
						{
							
							period="";
						}
						try{
							planType = object.getJSONObject("result").getJSONObject("parameters").getString("planType")+"";
						}catch(Exception e)
						{
							
							planType="";
						}
						if(sessionMap.containsKey(sessionId))
						{
							if(period.equalsIgnoreCase("")){
								cachePeriod=sessionMap.get(sessionId).get("period")+"";
							}else
							{
								cachePeriod=period;
							}
							if(planType.equalsIgnoreCase("")){	
								cashplanType= sessionMap.get(sessionId).get("planType")+"";
							}else
							{
								cashplanType=planType;
							}
							if(channel.equalsIgnoreCase("")){
								cashchannel= sessionMap.get(sessionId).get("channel")+"";
							}else{
								cashchannel=channel;
							}
							if(productType.equalsIgnoreCase("")){	
								cashproductType= sessionMap.get(sessionId).get("productType")+"";
							}else{
								cashproductType=productType;
							}
							if(!actionperformed.equalsIgnoreCase("") && actionperformed!=null)
							{
								return aPIConsumerService.getWipDataAll(actionperformed, cashchannel, cachePeriod, cashproductType, cashplanType);
							}
						}
						else
						{
							Map<String, String> map = new HashMap<String, String>();
							map.put("channel", channel);
							map.put("productType", productType);
							map.put("period", period);
							map.put("planType", planType);
							sessionMap.put(sessionId, map);
							if(!actionperformed.equalsIgnoreCase("") && actionperformed!=null)
							{
								return aPIConsumerService.getWipDataAll(actionperformed, channel, period, productType, planType);
							}
						}
					}
					else
					{
						speech="I need to verify the OTP which was sent on your registered mobile number. Please enter it here";
					}
				} 
				else
				{
					speech="Please Validate SSO Credentials For Further Process";
				}
			}
		}
		catch (Exception e)
		{
		 System.out.println("End : Controller: Webhook- Exception");	
		}
		WebhookResponse responseObj = new WebhookResponse(speech, speech);
		System.out.println("End : Controller: Webhook");
		return responseObj;
	}

	public Map<String, Map<String,String>> APICallSSOValidation(String ssoId, String sessionId)
	{
		String phoneNo="";	String agentName="";
		String DevMode = "N";
		HttpURLConnection conn = null;
		String output = new String();
		StringBuilder result = new StringBuilder();
		Map<String, Map<String,String>> cashData=null;
		Map<String,String> blankmessage= new HashMap<String,String>();
		try 
		{
			MliBotController mliBotController= new MliBotController();
			XTrustProvider trustProvider=new XTrustProvider();
			trustProvider.install();
			StringBuilder requestdata=new StringBuilder();
			String serviceurl3 = resProp.getString("servicephoneno");
			URL url3 = new URL(serviceurl3);
			if(DevMode!=null && !"".equalsIgnoreCase(DevMode) && "Y".equalsIgnoreCase(DevMode))
			{
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
				conn = (HttpURLConnection) url3.openConnection(proxy);
			}
			else
			{
				conn = (HttpURLConnection) url3.openConnection();
			}
			HttpsURLConnection.setFollowRedirects(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			requestdata.append("	{	");
			requestdata.append("	    \"request\": {	");
			requestdata.append("	        \"header\": {	");
			requestdata.append("	            \"soaCorrelationId\": \"2569887\",	");
			requestdata.append("	            \"soaMsgVersion\": \"1.0\",	");
			requestdata.append("	            \"soaAppId\": \"BPMA_BOT\",	");
			requestdata.append("	            \"soaUserId\": \"BPMA_BOT123\",	");
			requestdata.append("	            \"soaPassword\": \"dHJsdfjTIz\"	");
			requestdata.append("	        },	");
			requestdata.append("	        \"requestData\": {	");
			requestdata.append("	            \"requestPayload\": {	");
			requestdata.append("	                \"transactions\": [	");
			requestdata.append("	                    {	");
			requestdata.append("	                       \"ssoId\": \""+ssoId+"\"");
			requestdata.append("	                    }	");
			requestdata.append("	                ]	");
			requestdata.append("	            }	");
			requestdata.append("	        }	");
			requestdata.append("	    }	");
			requestdata.append("	}	");

			System.out.println("External API Call : START");
			OutputStreamWriter writer3 = new OutputStreamWriter(conn.getOutputStream());
			writer3.write(requestdata.toString());
			writer3.flush();
			try {writer3.close(); } catch (Exception e1) {}
			int apiResponseCode3 = conn.getResponseCode();
			if(apiResponseCode3 == 200)
			{
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) 
				{
					result.append(output);
				}
				conn.disconnect();
				br.close();
				JSONObject object = new JSONObject(result.toString());
				try{
					phoneNo	 = object.getJSONObject("response").getJSONObject("responseData").getJSONArray("Transactions").getJSONObject(0).get("mnylpreferredmobile")+"";
					phoneNo="7503170576";
					agentName = object.getJSONObject("response").getJSONObject("responseData").getJSONArray("Transactions").getJSONObject(0).get("mnyldisplayname")+"";
					if(phoneNo!=null && !"".equalsIgnoreCase(phoneNo))
					{
						cashData = mliBotController.OTPVarification(sessionId, phoneNo, agentName, ssoId);
					}
					else
					{
						blankmessage.put("PhoneStatus", "NotAvail");
						sessionMapcontainssoinfo.put(sessionId, blankmessage);
						cashData=sessionMapcontainssoinfo;
					}
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				System.out.println("External API Call : END");

			}
			else
			{
				blankmessage.put("SoaStatus", "Failure_API_1");
				sessionMapcontainssoinfo.put(sessionId, blankmessage);
				cashData=sessionMapcontainssoinfo;
			}
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
		return cashData;
	}
	public Map<String,Map<String,String>> OTPVarification(String sessionId, String phoneno, String agentName, String ssoId)
	{
		String DevMode = "N";
		HttpURLConnection conn = null;
		String output = new String();
		String status="";
		String randomotp="";
		StringBuilder result = new StringBuilder();
		Map<String,String> otpsession= new HashMap<String,String>();
		try 
		{
			XTrustProvider trustProvider=new XTrustProvider();
			trustProvider.install();
			StringBuilder requestdata=new StringBuilder();
			String serviceurl = resProp.getString("servicesendotp");
			URL url = new URL(serviceurl);
			if(DevMode!=null && !"".equalsIgnoreCase(DevMode) && "Y".equalsIgnoreCase(DevMode))
			{
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
				conn = (HttpURLConnection) url.openConnection(proxy);
			}
			else{
				conn = (HttpURLConnection) url.openConnection();
			}
			randomotp =randomNumber();
			HttpsURLConnection.setFollowRedirects(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			requestdata.append("	{	");
			requestdata.append("	   \"MliSmsService\": {	");
			requestdata.append("	      \"requestHeader\": {	");
			requestdata.append("	         \"generalConsumerInformation\": {	");
			requestdata.append("	            \"messageVersion\": \"1.0\",	");
			requestdata.append("	            \"consumerId\": \"BPMA_BOT\",	");
			requestdata.append("	            \"correlationId\": \"123459999\"	");
			requestdata.append("	         }	");
			requestdata.append("	      },	");
			requestdata.append("	      \"requestBody\": {	");
			requestdata.append("	         \"appAccId\": \"PROMO_SOA\",	");
			requestdata.append("	         \"appAccPass\": \"y!7Ej@9C\",	");
			requestdata.append("	         \"appId\": \"MAXLIT\",	");
			requestdata.append("	         \"msgTo\": \""+phoneno+"\",	");
			requestdata.append("	         \"msgText\": \""+randomotp+"\"	");
			requestdata.append("	      }	");
			requestdata.append("	   }	");
			requestdata.append("	}	");

			System.out.println("External API Call : START");
			OutputStreamWriter writer3 = new OutputStreamWriter(conn.getOutputStream());
			writer3.write(requestdata.toString());
			writer3.flush();
			try {writer3.close(); } catch (Exception e1) {}
			int apiResponseCode3 = conn.getResponseCode();

			if(apiResponseCode3 == 200)
			{
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) 
				{
					result.append(output);
				}
				conn.disconnect();
				br.close();
				JSONObject object = new JSONObject(result.toString());
				try{
					status = object.getJSONObject("MliSmsServiceResponse").getJSONObject("responseHeader")
							.getJSONObject("generalResponse").get("status")+"";

					otpsession.put("Status", status);
					otpsession.put("AgentName", agentName);
					otpsession.put( "otp", randomotp);
					System.out.println("valid otp");
					otpsession.put("SoaStatus", "success");
					otpsession.put("validSSOID", ssoId);
					sessionMapcontainssoinfo.put(sessionId, otpsession);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			else
			{
				otpsession.put("SoaStatus", "Failure_API_2");
				sessionMapcontainssoinfo.put(sessionId, otpsession);
			}
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
		return sessionMapcontainssoinfo;
	}
	public static String randomNumber()
	{
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i<6; i++) {
			bf =bf.append(getRandomNumberInRange(1, 9));
		}
		return bf.toString();

	}
	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}




