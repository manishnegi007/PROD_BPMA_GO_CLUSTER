package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.stereotype.Component;
import org.json.JSONObject;
import common.Commons;
import common.XTrustProvider;
import hello.WebhookResponse;
@Component
public class APIConsumerService {
		public WebhookResponse getWipDataAll(String action, String channel, String period)
	{
		    ResourceBundle res = ResourceBundle.getBundle("errorMessages");
			String output = new String();
			StringBuilder result = new StringBuilder();	
			String DevMode = "N";
			HttpURLConnection conn = null;
			String segment="";
			String serviceChannel="";
			String speech="";
		
			try 
			{
				if("NUMBERS".equalsIgnoreCase(action))
				{
					if("".equalsIgnoreCase(channel)|| "MLI".equalsIgnoreCase(channel))
					{
						segment = "paid,wip,applied";
						serviceChannel = "";
					}
					else
					{
						segment = "paid,wip,applied";
						serviceChannel = channel;
					}
				}
				else if("AdjMFYP".equalsIgnoreCase(action))
				{
					if(channel.equalsIgnoreCase("MLI"))
					{
						segment="paid";
						serviceChannel = "";
					}
					else
					{
						segment="paid";
						serviceChannel = channel;
					}
				}
				else if(("WIP".equalsIgnoreCase(action)||"WIP.YES".equalsIgnoreCase(action)) && !channel.equalsIgnoreCase(""))
				{
					if(channel.equalsIgnoreCase("MLI"))
					{
						segment="wip";
						serviceChannel = "";
					}
					else
					{
						segment="wip";
						serviceChannel = channel;
					}
				}
				else if("APPLIED".equalsIgnoreCase(action) && !channel.equalsIgnoreCase(""))
				{
					if(channel.equalsIgnoreCase("MLI"))
					{
						segment="applied";
						serviceChannel = "";
					}
					else
					{
						segment="applied";
						serviceChannel = channel;
					}
				}
				XTrustProvider trustProvider=new XTrustProvider();
				trustProvider.install();
				String serviceurl = res.getString("serviceurl");
				URL url = new URL(serviceurl);
				if(DevMode!=null && !DevMode.equalsIgnoreCase("") && DevMode.equalsIgnoreCase("Y"))
				{
					Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
					conn = (HttpURLConnection) url.openConnection(proxy);
				}
				else
				{
					conn = (HttpURLConnection) url.openConnection();
				}
				HttpsURLConnection.setFollowRedirects(true);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				//			conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-Type", "application/json");
				StringBuilder requestdata=new StringBuilder();

				Long currenttm = System.currentTimeMillis();

				requestdata.append("	{	");
				requestdata.append("	  \"header\": {	");
				requestdata.append("	    \"correlationId\": \"1234567890\",	");
				requestdata.append("	    \"msgVersion\": \"\",	");
				requestdata.append("	    \"appId\": \"\",	");
				requestdata.append("	    \"userId\": \"\",	");
				requestdata.append("	    \"password\": \"\",	");
				requestdata.append("	    \"rollId\":\"\"	");
				requestdata.append("	  },	");
				requestdata.append("	  \"payload\": {	");
				requestdata.append("	    \"segment\": \""+segment+"\",	");
				requestdata.append("	    \"channel\": \""+serviceChannel+"\"	");
				requestdata.append("	  }	");
				requestdata.append("	}	");
				OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
				writer.write(requestdata.toString());
				writer.flush();
				try {writer.close(); } catch (Exception e1) {}

				int apiResponseCode = conn.getResponseCode();
				System.out.println(apiResponseCode);
				if(apiResponseCode == 200)
				{
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					while ((output = br.readLine()) != null) 
					{
						result.append(output);
					}
					conn.disconnect();
					br.close();
					try
					{
						DecimalFormat df = new DecimalFormat("####0.00");
						System.out.println(result.toString());
						JSONObject object = new JSONObject(result.toString());
						String finalresponse="";
						double dailyAdjustMFYP1=0;  double mtdAdjustMFYP1=0;    double dailyAppliedAFYP1=0;
						double mtdAppliedAFYP1=0;	double wipAFYP=0;           double hoWIPAFYP=0;
						double goWIPAFYP=0;			double itWIPAFYP=0;	   	    double finWIPAFYP=0;
						double miscWIPAFYP=0;		double welcomeWIPAFYP=0;	double wip_count=0;
						double ho_wip_count=0;		double go_wip_count=0;		double it_wip_count=0;
						double fin_wip_count=0;		double misc_wip_count=0;	double welcome_wip_count=0;
						try
						{
							dailyAdjustMFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("enforceData").get("daily_adj_mfyp").toString());
						}
						catch(Exception ex)	{}
						String dailyAdjustMFYP =df.format(dailyAdjustMFYP1);
						try
						{
							mtdAdjustMFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("enforceData").get("mtd_adj_mfyp").toString());
						}catch(Exception ex){}
						String mtdAdjustMFYP = df.format(mtdAdjustMFYP1);
						try{
							dailyAppliedAFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("appliedData").get("daily_applied_afyp").toString());
						}catch(Exception e){}
						String dailyAppliedAFYP = df.format(dailyAppliedAFYP1);
						try{
							mtdAppliedAFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("appliedData").get("mtd_applied_afyp").toString());
						}catch(Exception e){}
						String mtdAppliedAFYP = df.format(mtdAppliedAFYP1);

						double sum = 0;
						try{
							wipAFYP = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("wip_afyp").toString());
						}catch(Exception e){}
						try{
							hoWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("ho_wip_afyp").toString());
						}catch(Exception e){}
						try{
							goWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("go_wip_afyp").toString());
						}catch(Exception e){}
						try{
							itWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("it_wip_afyp").toString());
						}catch(Exception e){}
						try{
							finWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("fin_wip_afyp").toString());
						}catch(Exception e){}
						try{
							miscWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("misc_wip_afyp").toString());
						}catch(Exception e){}
						try{
							welcomeWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("welcome_wip_afyp").toString());
						}catch(Exception e){}

						double sum2=0;
						try{
							wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("wip_count").toString());
						}catch(Exception e){}
						try{
							ho_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("ho_wip_count").toString());
						}catch(Exception e){}
						try{
							go_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("go_wip_count").toString());
						}catch(Exception e){}
						try{
							it_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("it_wip_count").toString());
						}catch(Exception e){}
						try{
							fin_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("fin_wip_count").toString());
						}catch(Exception e){}
						try{
							misc_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("misc_wip_count").toString());
						}catch(Exception e){}
						try{
							welcome_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("welcome_wip_count").toString());
						}catch(Exception e){}

						sum = sum+wipAFYP+hoWIPAFYP+goWIPAFYP+itWIPAFYP+finWIPAFYP+miscWIPAFYP+welcomeWIPAFYP;
						String convertsum  =  df.format(sum);

						sum2=sum2+wip_count+ho_wip_count+go_wip_count+it_wip_count+fin_wip_count+misc_wip_count+welcome_wip_count;
						String convertsum2  =  df.format(sum2);

						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();

						if("NUMBERS".equalsIgnoreCase(action))
						{
							if("MONTHLY".equalsIgnoreCase(period))
							{
								finalresponse="Monthly OverAll Business as of "+dtf.format(now)+
										", is Paid :"
										+" Adj MFYP MTD is: "+mtdAdjustMFYP+" Cr ||"
										+" Applied Data: "
										+" Applied AFYP MTD is: " +mtdAppliedAFYP+" Cr"
										+ " || WIP Data"
										+      "  WIP AFYP is: " +convertsum+" Cr."+" Do you want to see the Data Channel Wise like : Agency, Axis, Banca, Cat";
							}
							else if("NUMBERS".equalsIgnoreCase(action) && (channel.equalsIgnoreCase("MLI") || channel.equalsIgnoreCase("")))
							{
								finalresponse="Monthly OverAll Business as of "+dtf.format(now)+
										", is Paid : Adj MFYP FTD is "+dailyAdjustMFYP+" Cr,"
										+" Adj MFYP MTD is: "+mtdAdjustMFYP+" Cr ||"
										+"  Applied Data: "
										+ "				Applied AFYP FTD is: " +dailyAppliedAFYP+" Cr"
										+", Applied AFYP MTD is: " +mtdAppliedAFYP+" Cr"
										+ " || WIP Data"
										+      "  WIP AFYP is: " +convertsum+" Cr."+" Do you want to see the Data Channel Wise like : Agency, Axis, Banca, Cat";
							}
							else
							{
								finalresponse="";
							}

						}
						else if("AdjMFYP".equalsIgnoreCase(action))
						{
							if("MONTHLY".equalsIgnoreCase(period))
							{
								finalresponse="As of "+dtf.format(now)+" paid AdjMFYP Business" +
										" is : "+mtdAdjustMFYP+" Cr";
							}
							else if(channel.equalsIgnoreCase(channel))
							{
								finalresponse="As of "+dtf.format(now)+" paid AdjMFYP Business for <"+channel+">"+" is" +mtdAdjustMFYP+" Cr.";
							}
							else if("MONTHLY".equalsIgnoreCase(period) && channel.equalsIgnoreCase(channel))
							{
								finalresponse="As of "+dtf.format(now)+" paid AdjMFYP Business" +
										" is : "+mtdAdjustMFYP+" Cr";
							}
							else {
								finalresponse="As of "+dtf.format(now)+" paid AdjMFYP Business"+
										" is FTD : " +dailyAdjustMFYP+" Cr,"
										+" MTD is: " +mtdAdjustMFYP+" Cr";
							}
						}

						else if("WIP".equalsIgnoreCase(action))
						{
							if(channel!="")
							{
							finalresponse="Curent WIP as of "+dtf.format(now)+
									"for< "+channel+">"+" is"+convertsum2+" Policies with"+convertsum+" "
									+ "Cr. AFYP. Do you wish to see the stage wise snapshot";
							}
							else
							{
								finalresponse="Curent WIP as of "+dtf.format(now)+
										"for MLI is"+convertsum2+" Policies with"+convertsum+" "
										+ "Cr. AFYP. Do you wish to see the stage wise snapshot";
							}
						}
						else if("WIP.YES".equalsIgnoreCase(action))
						{
							finalresponse="wipAFYP :" +wipAFYP+
									", hoWIPAFYP :"+hoWIPAFYP+
									", goWIPAFYP :"+goWIPAFYP+
									", itWIPAFYP :"+itWIPAFYP+
									", finWIPAFYP :"+finWIPAFYP+
									", miscWIPAFYP :"+miscWIPAFYP+
									", welcomeWIPAFYP :"+welcomeWIPAFYP+"";
						}
						else if("APPLIED".equalsIgnoreCase(action))
						{
							if("MONTHLY".equalsIgnoreCase(period))
							{
								finalresponse="As of "+dtf.format(now)+" Applied AFYP Business" +
										" is : "+mtdAppliedAFYP+" Cr for "+channel+"";
							}

							else if(channel.equalsIgnoreCase(channel))
							{
								finalresponse="As of "+dtf.format(now)+" Applied AFYP Business" +
										" is : "+mtdAppliedAFYP+" Cr for "+channel+"";
							}
							else if("MONTHLY".equalsIgnoreCase(period) && channel.equalsIgnoreCase(channel))
							{
								finalresponse="As of"+dtf.format(now)+" Applied AFYP Business" +
										" is : "+mtdAppliedAFYP+" Cr for "+channel+"";
							}
							else
							{
								finalresponse="As of "+dtf.format(now)+" Applied AFYP"+
										"  is: "
										+ " FTD : " +dailyAppliedAFYP+" Cr"
										+", MTD is: " +mtdAppliedAFYP+" Cr";
							}
						}
						else
						{
							finalresponse="Something gets wrong between action & channel";
						}
//						response.setSpeech(finalresponse);
//						response.setDisplayText(finalresponse);
						speech=finalresponse;
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
			}
			catch(Exception ex)
			{
				System.out.println("Exception>>>>>>>>>>>>"+ex);
			}
			WebhookResponse responseObj = new WebhookResponse(speech, speech);
			System.out.println("*******"+responseObj);
			return responseObj;
		}

}
