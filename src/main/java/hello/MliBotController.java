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
import utils.HttpUrlConnectionMlabInsert;
import utils.HttpUrlConnection_GetDetails;
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
	public static Map<String, Map<String, String>> extraMap = new ConcurrentHashMap<String, Map<String, String>>();
	public static Map<String, Map<String,String>> sessionMapcontainssoinfo = new ConcurrentHashMap<String, Map<String,String>>();
	public static Map<String, Map<String,String>> kpilevel = new HashMap<String, Map<String,String>>();
	Map<String, String> innermap = new HashMap<String,String>();
	
	
	@Autowired
	APIConsumerService aPIConsumerService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody WebhookResponse webhook(@RequestBody String obj, Model model, HttpSession httpSession) 
	{
		//WebhookResponse response = new WebhookResponse();
		System.out.println("CameInside :- Controller: Webhook");
		//	------------------------For Development Purpose Only--------------------------------
		System.out.println("Size of the CashHashMap:Internal Data Cash: "+ sessionMap.size());
		System.out.println("Size of the CashHashMap: No.of User Enter: "+ sessionMapcontainssoinfo.size());
		//	------------------------For Development Purpose Only--------------------------------
		
		String productType = ""; String planType = ""; String period = ""; String channel = "";	String sessionId = "";
		String userOTP = ""; String speech = ""; String cachePeriod = ""; String cashplanType = ""; String cashchannel = "";
		String cashproductType = ""; String ssoId = "";	String cashCircle = "";	String cashRegion=""; String cashZone="";
		String circle=""; String region=""; String zone=""; String subChannel=""; String cash_Sub_Channel="";

		WebhookResponse response = new WebhookResponse();
		MliBotController mliBotController= new MliBotController();
		try 
		{
			System.out.println("WebhookResponse API START");
			JSONObject object = new JSONObject(obj);
			String actionperformed = object.getJSONObject("result").get("action")+"";
			String resolvedQuery = object.getJSONObject("result").get("resolvedQuery") + "";
			sessionId = object.get("sessionId")+"";
			try{
				userOTP = object.getJSONObject("result").getJSONObject("parameters").get("number")+"";
			}catch(Exception e){System.out.println("Not getting OTP");}
			if("SSO.Validation".equalsIgnoreCase(actionperformed))
			{
				System.out.println("SSOValidation API START");
				ssoId = object.getJSONObject("result").getJSONObject("parameters").get("SSOID")+"";
				sessionId=object.get("sessionId")+"";
				Map otpsessionMap = sessionMapcontainssoinfo.get(sessionId);
				if (otpsessionMap == null) {
					Map<String, Map<String, String>> returnmap = mliBotController.APICallSSOValidation(ssoId, sessionId);
					String SoaStatus = "";
					String PhoneStatus = "";
					String mnylstatus="";
					Map<String, String> cashMap = returnmap.get(sessionId);
					SoaStatus = cashMap.get("SoaStatus");
					PhoneStatus = cashMap.get("PhoneStatus");
					mnylstatus=cashMap.get("mnylStatus");
					if("N".equalsIgnoreCase(mnylstatus)){
						speech="This UserID Is InActive";
					}
					else if ("success".equalsIgnoreCase(SoaStatus)) 
					{
						speech = "I need to verify the OTP which was sent on your registered mobile number. Please enter it here. OTP for Testing :" 
							+ cashMap.get("otp")+ "";
					} else if ("NotAvail".equalsIgnoreCase(PhoneStatus)) {
						speech = "Your PhoneNo. is not registered with us! Please Enter a registered PhoneNo.";
					} else if ("Failure_API_1".equalsIgnoreCase(SoaStatus)
							|| "Failure_API_2".equalsIgnoreCase("SoaStatus")) {
						speech = "Invalid Credentials! Please Enter a Valid Credentials";
					} else {
						speech = "Oops! I could not find any registered mobile number for this SSO";
					}
				} else {

					if (otpsessionMap.get("validSSOID") != null
							&& otpsessionMap.get("validSSOID").toString().equalsIgnoreCase(ssoId)) {
						speech = "Already validated";
					}

					if (!otpsessionMap.get("validSSOID").toString().equalsIgnoreCase(ssoId)) {
						speech = "You are trying to be login as different user. Please close earlier conversation to proceed";
					}
				}
			}
			else if ("nb.OTP.Validation".equalsIgnoreCase(actionperformed)) {
				String AgentName = "";
				String cashOTP = "";
				if (sessionMapcontainssoinfo.containsKey(sessionId))
				{
					Map<String, String> cashMap = sessionMapcontainssoinfo.get(sessionId);
					String validSSOID = cashMap.get("validSSOID");
					if(cashMap.containsKey(validSSOID+"_VERIFY"))
					{
						speech = "Already validated!";
					}
					else
					{
						cashOTP = cashMap.get("otp");
						AgentName = cashMap.get("AgentName");
						if (cashOTP.equalsIgnoreCase(userOTP))
						{
							speech = "Hi " + AgentName + ", How can i help you today?";
							cashMap.put("Validation", "success");
							cashMap.put(validSSOID+"_VERIFY", cashOTP);
							sessionMapcontainssoinfo.put(sessionId, cashMap);
						}
						else
						{
							speech = "Invalid OTP Entered! "
									+ "The OTP you have provided does not match. Please provide valid OTP that has been sent to your registered mobile number.";
						}
					}
				} 
				else{
					speech = "Please Validate SSO Credentials For Further Process";
				}
			}else if("close.conversation".equalsIgnoreCase(actionperformed))
			{			
				if (sessionMapcontainssoinfo.containsKey(sessionId)) {
					sessionMapcontainssoinfo.remove(sessionId);
					sessionMap.remove(sessionId);
					extraMap.remove(sessionId);
					kpilevel.remove(sessionId);
					innermap.remove("cachePeriod_"+sessionId);
					innermap.remove("cashplanType_"+sessionId);
					innermap.remove("cashCircle_"+sessionId);
					innermap.remove("cashRegion_"+sessionId);
					innermap.remove("cashZone_"+sessionId);
					innermap.remove("cash_Sub_Channel_"+sessionId);
					innermap.remove("cashproductType_"+sessionId);
					speech = "Thank you for contacting Max Life. Have a great day!";
				}
				else{
					speech = "Thank you for contacting Max Life. Have a great day!";
				}
			}else
			{
				if(sessionMapcontainssoinfo.containsKey(sessionId)){
					String user_ssoid="", user_channel="", user_sub_channel="", user_designation_desc="", user_getzone="", user_region="", user_circle="", 
							user_clusters="", user_go="", user_cmo="", user_amo="";
					String Validation="";
					Map<String,String> cashMap= sessionMapcontainssoinfo.get(sessionId);
					Validation=cashMap.get("Validation");
					ssoId = cashMap.get("validSSOID");
					if("success".equalsIgnoreCase(Validation))
					{
						try {
							channel = object.getJSONObject("result").getJSONObject("parameters").getString("Channel");
						} catch (Exception e) {
							channel = "";
						}
						try {
							productType = object.getJSONObject("result").getJSONObject("parameters").getString("ProductType");
							if (!"".equalsIgnoreCase(productType)) {
								innermap.put("cashproductType_"+sessionId, productType);
								kpilevel.put(sessionId, innermap);
							}
						} catch (Exception e) {
							productType = "";
						}
						try {
							period = object.getJSONObject("result").getJSONObject("parameters").getString("Period");
							if (!"".equalsIgnoreCase(period)) {
								innermap.put("cachePeriod_"+sessionId, period);
								kpilevel.put(sessionId, innermap);
							}
						} catch (Exception e) {
							period = "";
						}
						try {
							planType = object.getJSONObject("result").getJSONObject("parameters").getString("planType");
							if (!"".equalsIgnoreCase(planType)) {
								innermap.put("cashplanType_"+sessionId, planType);
								kpilevel.put(sessionId, innermap);
							}
						} catch (Exception e) {
							planType = "";
						}
						try {
							circle = object.getJSONObject("result").getJSONObject("parameters").getString("Circle");
							if (!"".equalsIgnoreCase(circle)) {
								innermap.put("cashCircle_"+sessionId, circle);
								kpilevel.put(sessionId, innermap);
							}
						} catch (Exception e) {
							circle = "";
						}
						try {
							region = object.getJSONObject("result").getJSONObject("parameters").getString("Region");
							if (!"".equalsIgnoreCase(region)) {
								innermap.put("cashRegion_"+sessionId, region);
								kpilevel.put(sessionId, innermap);
							}
						} catch (Exception e) {
							region = "";
						}
						try {
							subChannel = object.getJSONObject("result").getJSONObject("parameters").getString("SubChannel");
							if (!"".equalsIgnoreCase(subChannel)) {
								innermap.put("cash_Sub_Channel_"+sessionId, subChannel);
								kpilevel.put(sessionId, innermap);
							}
						} catch (Exception e) {
							subChannel = "";
						}
						try {
							zone = object.getJSONObject("result").getJSONObject("parameters").getString("Zone");
							if (!"".equalsIgnoreCase(zone)) {
								innermap.put("cashZone_"+sessionId, zone);
								kpilevel.put(sessionId, innermap);
							}
						} catch (Exception e) {
							zone = "";
						}
/*------------Second Time when user comes with same SessionId------------------------------------------------------------------*/
						if (sessionMap.containsKey(sessionId)) 
						{
							String checkChannel="";
							String extraChannel="";
							String extraSSOId="";
							String extraproductType="";
							String checkZone="";
							String checkSubChannel="";
							String extraPeriod ="";
							String extraRegion=""; String extraZone=""; String extraCircle="";
							String diffIntent=""; String extrasubchannel="";
							user_ssoid=sessionMap.get(sessionId).get("user_ssoid") + "";
							checkChannel = sessionMap.get(sessionId).get("channel") + "";
							checkZone = sessionMap.get(sessionId).get("zone") + "";
							checkSubChannel=sessionMap.get(sessionId).get("subChannel") + "";
							cachePeriod = sessionMap.get(sessionId).get("period") + "";
							if(!"".equalsIgnoreCase(user_ssoid) && !user_ssoid.equalsIgnoreCase("null"))
							{
								user_ssoid=sessionMap.get(sessionId).get("user_ssoid") + "";
								checkChannel = sessionMap.get(sessionId).get("channel") + "";
								extraChannel = extraMap.get(user_ssoid).get("channel") + "";
								extraSSOId = extraMap.get(user_ssoid).get("user_ssoid") + "";
								extraRegion =extraMap.get(user_ssoid).get("region") + "";
								extraZone = extraMap.get(user_ssoid).get("zone") + "";
								extraCircle = extraMap.get(user_ssoid).get("circle") + "";
								extraproductType=extraMap.get(user_ssoid).get("productType");
								extraPeriod= extraMap.get(user_ssoid).get("period");
								extrasubchannel= extraMap.get(user_ssoid).get("subChannel");
								if (actionperformed.equalsIgnoreCase("nb.channel")|| actionperformed.equalsIgnoreCase("nb.period")
										|| actionperformed.equalsIgnoreCase("nb.circle")|| actionperformed.equalsIgnoreCase("nb.zone")
										|| actionperformed.equalsIgnoreCase("nb.region")|| actionperformed.equalsIgnoreCase("nb.channelnzone")
										|| actionperformed.equalsIgnoreCase("nb.zonenregion")|| actionperformed.equalsIgnoreCase("nb.subchannel")
										|| actionperformed.equalsIgnoreCase("nb.channelncircle")|| actionperformed.equalsIgnoreCase("nb.subchannelnregion")	
										|| actionperformed.equalsIgnoreCase("nb.zonencircle")|| actionperformed.equalsIgnoreCase("nb.channelnregion")
										|| actionperformed.equalsIgnoreCase("nb.channelnzonenregion") || actionperformed.equalsIgnoreCase("NB.Producttype")
										|| actionperformed.equalsIgnoreCase("nb.channelnzonencircle")) 
								{
									diffIntent=actionperformed;
									actionperformed = sessionMap.get(sessionId).get("action") + "";
								} else {
									diffIntent = sessionMap.get(sessionId).get("action") + "";
									Map map = sessionMap.get(sessionId);
									map.put("action", actionperformed);	
								}
								if(!"".equalsIgnoreCase(extraSSOId) && !extraSSOId.equalsIgnoreCase("null"))
								{
		//----------------------------------------Region Level ------------------------------------------------------------------------------
									if (sessionMap.get(sessionId).get("employeeIdentification").equalsIgnoreCase("RE")
											|| sessionMap.get(sessionId).get("employeeIdentification").equalsIgnoreCase("CR"))
									{
										if (!"".equalsIgnoreCase(channel) || !"".equalsIgnoreCase(zone)
												|| !"".equalsIgnoreCase(subChannel)	|| (!extraRegion.equalsIgnoreCase(region)
														&& !"".equalsIgnoreCase(region))|| !extraCircle.equalsIgnoreCase(circle)
												&& !"".equalsIgnoreCase(circle))
										{
											speech = "You are not authorized to see different channel, zone, region/circle data";
										}
										else
										{
											cashchannel = extraChannel;
											cashZone = extraZone;
											cashRegion = extraRegion;
											cashCircle = extraCircle;
											cash_Sub_Channel = extrasubchannel;
											if ("".equalsIgnoreCase(productType))
											{
												try {
													cashproductType = kpilevel.get(sessionId).get("cashproductType_"+sessionId);
												}
												catch (Exception ex)
												{
													cashproductType = "";
												}
												if (cashproductType == null)
												{
													cashproductType = "";
												}
											} else
											{
												innermap.put("cashproductType_"+sessionId, productType);
												kpilevel.put(sessionId, innermap);
												cashproductType = productType;
											}
											if ("".equalsIgnoreCase(period))
											{
												try {
													cachePeriod = kpilevel.get(sessionId).get("cachePeriod_"+sessionId);
												}
												catch (Exception ex)
												{
													cachePeriod = "";
												}
												if (cachePeriod == null)
												{
													cachePeriod = "";
												}
											}
											else
											{
												innermap.put("cachePeriod_"+sessionId, period);
												kpilevel.put(sessionId, innermap);
												cachePeriod = period;
											}
										}
									}
					//----------------------------Zone Level ----------------------------------------------------------------------------------------
									else if (sessionMap.get(sessionId).get("employeeIdentification")
											.equalsIgnoreCase("ZN"))
									{
										if (!diffIntent.equalsIgnoreCase(actionperformed))
										{
											if (!"".equalsIgnoreCase(channel) || !"".equalsIgnoreCase(subChannel)
													|| (!"".equalsIgnoreCase(zone)&& !zone.equalsIgnoreCase(extraZone)))
											{
												speech = "You are not authorized to see different channel & zone data";
											}
											else
											{
												// START---------Cannot change its channel, subchannel and zone but put its same zone
												cashchannel = extraChannel;
												cash_Sub_Channel = extrasubchannel;
												cashZone = extraZone;
												// END---------Cannot change its channel, subchannel and zone but put its same zone
												if ("".equalsIgnoreCase(productType))
												{
													try {
														cashproductType = kpilevel.get(sessionId).get("cashproductType_"+sessionId);
													}
													catch (Exception ex)
													{
														cashproductType = "";
													}
													if (cashproductType == null)
													{
														cashproductType = "";
													}
												} else
												{
													innermap.put("cashproductType_"+sessionId, productType);
													kpilevel.put(sessionId, innermap);
													cashproductType = productType;
												}
												if ("".equalsIgnoreCase(period))
												{
													try {
														cachePeriod = kpilevel.get(sessionId).get("cachePeriod_"+sessionId);
													}
													catch (Exception ex)
													{
														cachePeriod = "";
													}
													if (cachePeriod == null)
													{
														cachePeriod = "";
													}
												}
												else
												{
													innermap.put("cachePeriod_"+sessionId, period);
													kpilevel.put(sessionId, innermap);
													cachePeriod = period;
												}
												if ("".equalsIgnoreCase(region))
												{
													if ("".equalsIgnoreCase(zone))
													{
														try {
															cashRegion = kpilevel.get(sessionId).get("cashRegion_"+sessionId);
														}
														catch (Exception ex)
														{
															cashRegion = "";
														}
														if (cashRegion == null)
														{
															cashRegion = "";
														}
													}
													else
													{
														cashZone = extraZone;
														cashRegion = "";
														innermap.put("cashRegion_"+sessionId, cashRegion);
														kpilevel.put(sessionId, innermap);
													}
												} else {
													innermap.put("cashRegion_"+sessionId, region);
													kpilevel.put(sessionId, innermap);
													cashRegion = region;
												}
												if ("".equalsIgnoreCase(circle))
												{
													if ("".equalsIgnoreCase(zone))
													{
														try {
															cashCircle = kpilevel.get(sessionId).get("cashCircle_"+sessionId);
														}
														catch (Exception ex)
														{cashCircle = "";}
														if (cashCircle == null)
														{
															cashCircle = "";
														}
													}
													else
													{
														cashZone = extraZone;
														cashCircle = "";
														innermap.put("cashCircle_"+sessionId, cashCircle);
														kpilevel.put(sessionId, innermap);
													}
												} else {
													innermap.put("cashCircle_"+sessionId, circle);
													kpilevel.put(sessionId, innermap);
													cashCircle = circle;
												}
											}
										} else
										{
											cashZone = extraZone;
											cashRegion = "";
											innermap.put("cashRegion_"+sessionId, cashRegion);
											cashCircle = "";
											innermap.put("cashCircle_"+sessionId, cashCircle);
											kpilevel.put(sessionId, innermap);
										}
									}
					//-----------------------------------------SubChannel-----------------------------------------------------------------------
									else if (sessionMap.get(sessionId).get("employeeIdentification").equalsIgnoreCase("SB"))
									{
										if (!"".equalsIgnoreCase(channel) || (!"".equalsIgnoreCase(subChannel)
												&& !subChannel.equalsIgnoreCase(extrasubchannel)))
										{
											speech = "You are not authorized to see different channel data";
										}
										else
										{
											if (!diffIntent.equalsIgnoreCase(actionperformed))
											{
												cashchannel = extraChannel;
												cash_Sub_Channel = extrasubchannel;
												if ("".equalsIgnoreCase(productType))
												{
													try {
														cashproductType = kpilevel.get(sessionId).get("cashproductType_"+sessionId);
													}
													catch (Exception ex)
													{
														cashproductType = "";
													}
													if (cashproductType == null)
													{
														cashproductType = "";
													}
												} else
												{
													innermap.put("cashproductType_"+sessionId, productType);
													kpilevel.put(sessionId, innermap);
													cashproductType = productType;
												}
												if ("".equalsIgnoreCase(period))
												{
													try {
														cachePeriod = kpilevel.get(sessionId).get("cachePeriod_"+sessionId);
													}
													catch (Exception ex)
													{
														cachePeriod = "";
													}
													if (cachePeriod == null)
													{
														cachePeriod = "";
													}
												}
												else
												{
													innermap.put("cachePeriod_"+sessionId, period);
													kpilevel.put(sessionId, innermap);
													cachePeriod = period;
												}
			// START----------------------------if User enter a same subchannel again then blank rest of the items--------------
												if ("".equalsIgnoreCase(subChannel))
												{
													cash_Sub_Channel = extrasubchannel;
												}
												else
												{
													cash_Sub_Channel = subChannel;
													cashZone = "";
													innermap.put("cashZone_"+sessionId, cashZone);
													cashRegion = "";
													innermap.put("cashRegion_"+sessionId, cashRegion);
													cashCircle = "";
													innermap.put("cashCircle_"+sessionId, cashCircle);
													kpilevel.put(sessionId, innermap);
												}
			// END----------------------------if// User enter a same subchannel again then blank rest of the items------------------
												if ("".equalsIgnoreCase(zone))
												{
													try {
														cashZone = kpilevel.get(sessionId).get("cashZone_"+sessionId);
													}
													catch (Exception ex)
													{
														cashZone = "";
													}
													if (cashZone == null)
													{
														cashZone = "";
													}
												} else {
													cashZone = zone;
													innermap.put("cashZone_"+sessionId, zone);
													cashRegion = "";
													innermap.put("cashRegion_"+sessionId, cashRegion);
													cashCircle = "";
													innermap.put("cashCircle_"+sessionId, cashCircle);
													kpilevel.put(sessionId, innermap);
												}
												if ("".equalsIgnoreCase(region))
												{
													try {
														cashRegion = kpilevel.get(sessionId).get("cashRegion_"+sessionId);
													}
													catch (Exception ex)
													{
														cashRegion = "";
													}
													if (cashRegion == null)
													{
														cashRegion = "";
													}
												} else {
													innermap.put("cashRegion_"+sessionId, region);
													kpilevel.put(sessionId, innermap);
													cashRegion = region;
												}
												if ("".equalsIgnoreCase(circle))
												{
													try {
														cashCircle = kpilevel.get(sessionId).get("cashCircle_"+sessionId);
													}
													catch (Exception ex)
													{
														cashCircle = "";
													}
													if (cashCircle == null)
													{
														cashCircle = "";
													}
												} else {
													innermap.put("cashCircle_"+sessionId, circle);
													kpilevel.put(sessionId, innermap);
													cashCircle = circle;
												}
											} else
											{
												cashchannel = extraChannel;
												cash_Sub_Channel = extrasubchannel;
												cashZone = "";
												innermap.put("cashZone_"+sessionId, cashZone);
												cashRegion = "";
												innermap.put("cashRegion_"+sessionId, cashRegion);
												cashCircle = "";
												innermap.put("cashCircle_"+sessionId, cashCircle);
												kpilevel.put(sessionId, innermap);
											}
										}
									}
							//---------------------------------Channel Level----------------------------------------------------------------------------
									else {
										if (!extraChannel.equalsIgnoreCase(channel) && !"".equalsIgnoreCase(channel))
										{
											speech = "You are not authorized to see different channel data";
										}
										else
										{
											if (!diffIntent.equalsIgnoreCase(actionperformed))
											{
												cashchannel = extraChannel;
												// START--------- Product and Period level Handling-----------------------------
												if ("".equalsIgnoreCase(productType))
												{
													try {
														cashproductType = kpilevel.get(sessionId).get("cashproductType_"+sessionId);
													}
													catch (Exception ex)
													{cashproductType = "";}
													if (cashproductType == null)
													{
														cashproductType = "";
													}
												} else
												{
													innermap.put("cashproductType_"+sessionId, productType);
													kpilevel.put(sessionId, innermap);
													cashproductType = productType;
												}
												if ("".equalsIgnoreCase(period))
												{
													try {
														cachePeriod = kpilevel.get(sessionId).get("cachePeriod_"+sessionId);
													}
													catch (Exception ex)
													{
														cachePeriod = "";
													}
													if (cachePeriod == null)
													{
														cachePeriod = "";
													}
												}
												else
												{
													innermap.put("cachePeriod_"+sessionId, period);
													kpilevel.put(sessionId, innermap);
													cachePeriod = period;
												}
												// END Product and Period levelHandling---------------------------------------------------------
												// START----------------------------if// User enter a same channel again then blank rest of the items
												if (!"".equalsIgnoreCase(channel))
												{
													cashchannel = channel;
													cash_Sub_Channel = "";
													innermap.put("cash_Sub_Channel_"+sessionId, cash_Sub_Channel);
													cashZone = "";
													innermap.put("cashZone_"+sessionId, cashZone);
													cashRegion = "";
													innermap.put("cashRegion_"+sessionId, cashRegion);
													cashCircle = "";
													innermap.put("cashCircle_"+sessionId, cashCircle);
													kpilevel.put(sessionId, innermap);
												}
												// END-----------------if User enter a same channelagain then blank rest of the items
												// ------------------------------------------------------------------------------------------
												// START----------if User enter a same subchannel again then blank rest of the items--------
												if ("".equalsIgnoreCase(subChannel))
												{
													try {
														cash_Sub_Channel = kpilevel.get(sessionId).get("cash_Sub_Channel_"+sessionId);
													}
													catch (Exception ex)
													{
														cash_Sub_Channel = "";
													}
													if (cash_Sub_Channel == null)
													{
														cash_Sub_Channel = "";
													}
												}
												else
												{
													innermap.put("cash_Sub_Channel_"+sessionId, subChannel);
													cash_Sub_Channel = subChannel;
													cashZone = "";
													innermap.put("cashZone_"+sessionId, cashZone);
													cashRegion = "";
													innermap.put("cashRegion_"+sessionId, cashRegion);
													cashCircle = "";
													innermap.put("cashCircle_"+sessionId, cashCircle);
													kpilevel.put(sessionId, innermap);
												}
						// END-------------if User enter a same subchannel again then blank rest of the items-----------
												if ("".equalsIgnoreCase(zone))
												{
													try {
														cashZone = kpilevel.get(sessionId).get("cashZone_"+sessionId);
													}
													catch (Exception ex)
													{
														cashZone = "";
													}
													if (cashZone == null)
													{
														cashZone = "";
													}
												} else {
													innermap.put("cashZone_"+sessionId, zone);
													cashRegion = "";
													innermap.put("cashRegion_"+sessionId, cashRegion);
													cashCircle = "";
													innermap.put("cashCircle_"+sessionId, cashCircle);
													kpilevel.put(sessionId, innermap);
													cashZone = zone;
												}
												if ("".equalsIgnoreCase(region))
												{
													try {
														cashRegion = kpilevel.get(sessionId).get("cashRegion_"+sessionId);
													}
													catch (Exception ex)
													{
														cashRegion = "";
													}
													if (cashRegion == null)
													{
														cashRegion = "";
													}
												} else {
													innermap.put("cashRegion_"+sessionId, region);
													kpilevel.put(sessionId, innermap);
													cashRegion = region;
												}
												if ("".equalsIgnoreCase(circle))
												{
													try {
														cashCircle = kpilevel.get(sessionId).get("cashCircle_"+sessionId);
													}
													catch (Exception ex)
													{
														cashCircle = "";
													}
													if (cashCircle == null)
													{
														cashCircle = "";
													}
												} else {
													innermap.put("cashCircle_"+sessionId, circle);
													kpilevel.put(sessionId, innermap);
													cashCircle = circle;
												}
											}
											else
											{
			// --------------if user enter the same intent/action again then blank rest of the item and show on intent level
												cashchannel = extraChannel;
												cash_Sub_Channel = "";
												innermap.put("cash_Sub_Channel_"+sessionId, cash_Sub_Channel);
												cashZone = "";
												innermap.put("cashZone_"+sessionId, cashZone);
												cashRegion = "";
												innermap.put("cashRegion_"+sessionId, cashRegion);
												cashCircle = "";
												innermap.put("cashCircle_"+sessionId, cashCircle);
												kpilevel.put(sessionId, innermap);
											}
										}
									}
								}
							}
							else
							{
								if (period.equalsIgnoreCase("")) {
									cachePeriod = sessionMap.get(sessionId).get("period") + "";

								} else {
									cachePeriod = period;
									Map<String,String> map = sessionMap.get(sessionId);
									map.put("period", period);

								}
								if (planType.equalsIgnoreCase("")) {
									cashplanType = sessionMap.get(sessionId).get("planType") + "";
								} else {
									cashplanType = planType;
								}

								if (channel.equalsIgnoreCase("")) {
									cashchannel = sessionMap.get(sessionId).get("channel") + "";
								} else {
									cashchannel = channel;
									Map map = sessionMap.get(sessionId);
									map.put("channel", channel);
								}
								if (subChannel.equalsIgnoreCase("")) {
									cash_Sub_Channel = sessionMap.get(sessionId).get("subChannel") + "";
								} else {
									cash_Sub_Channel = subChannel;
									Map map = sessionMap.get(sessionId);
									map.put("subChannel", subChannel);
								}
								if (productType.equalsIgnoreCase("")) {
									cashproductType = sessionMap.get(sessionId).get("productType") + "";
								} else {
									cashproductType = productType;
									Map map = sessionMap.get(sessionId);
									map.put("productType", productType);
								}
								if (actionperformed.equalsIgnoreCase("nb.channel")
									|| actionperformed.equalsIgnoreCase("nb.period")
									|| actionperformed.equalsIgnoreCase("nb.circle")
									|| actionperformed.equalsIgnoreCase("nb.zone")
									|| actionperformed.equalsIgnoreCase("nb.region")
									|| actionperformed.equalsIgnoreCase("nb.channelnzone")
									|| actionperformed.equalsIgnoreCase("nb.zonenregion")
									|| actionperformed.equalsIgnoreCase("nb.subchannel")
									|| actionperformed.equalsIgnoreCase("nb.channelncircle")
									|| actionperformed.equalsIgnoreCase("nb.subchannelnregion")
									|| actionperformed.equalsIgnoreCase("nb.zonencircle")
									|| actionperformed.equalsIgnoreCase("nb.channelnregion")
									|| actionperformed.equalsIgnoreCase("nb.channelnzonenregion")
									|| actionperformed.equalsIgnoreCase("nb.channelnzonencircle")
								   	|| actionperformed.equalsIgnoreCase("NB.Producttype"))	
									{
									actionperformed = sessionMap.get(sessionId).get("action") + "";
								} else {
									Map map = sessionMap.get(sessionId);
									map.put("action", actionperformed);
								}
								if (circle.equalsIgnoreCase("")) 
								{
									String user_ssoId=sessionMap.get(sessionId).get("user_ssoid")+"";
									if(user_ssoId.equalsIgnoreCase("") || user_ssoId.equalsIgnoreCase("null"))
									{
										if(!checkChannel.equalsIgnoreCase(channel) && !"".equalsIgnoreCase(channel))
										{
											cashCircle= sessionMap.get(sessionId).get("circle") + "";
											if("".equalsIgnoreCase(cashCircle))
											{
												cashCircle= sessionMap.get(sessionId).get("circle") + "";
											}else{
												cashCircle="";
												Map map = sessionMap.get(sessionId);
												map.put("circle", cashCircle);
											}
										}
										else{

											cashCircle= sessionMap.get(sessionId).get("circle") + "";
											if (!checkSubChannel.equalsIgnoreCase(subChannel) && !"".equalsIgnoreCase(subChannel))
											{
												cashCircle="";
											}
											if(!checkZone.equalsIgnoreCase(zone) && !"".equalsIgnoreCase(zone))
											{
												cashCircle="";
												Map map = sessionMap.get(sessionId);
												map.put("circle", cashCircle);
											}
										}
									}
									else{
										cashCircle = sessionMap.get(sessionId).get("circle") + "";
									}
								} else {
									cashCircle = circle;
									Map map = sessionMap.get(sessionId);
									map.put("circle", circle);
								}
								if (region.equalsIgnoreCase("")) 
								{
									String user_ssoId=sessionMap.get(sessionId).get("user_ssoid")+"";
									if(user_ssoId.equalsIgnoreCase("") || user_ssoId.equalsIgnoreCase("null"))
									{
										if(!checkChannel.equalsIgnoreCase(channel) && !"".equalsIgnoreCase(channel))
										{
											cashRegion= sessionMap.get(sessionId).get("region") + "";
											if("".equalsIgnoreCase(cashRegion))
											{
												cashRegion= sessionMap.get(sessionId).get("region") + "";
											}else{
												cashRegion="";
												Map map = sessionMap.get(sessionId);
												map.put("region", cashRegion);
											}
										}
										else{
											cashRegion= sessionMap.get(sessionId).get("region") + "";
											if (!checkSubChannel.equalsIgnoreCase(subChannel) && !"".equalsIgnoreCase(subChannel))
											{
												cashRegion="";
											}
											if(!checkZone.equalsIgnoreCase(zone) && !"".equalsIgnoreCase(zone))
											{
												cashRegion="";
												Map map = sessionMap.get(sessionId);
												map.put("region", cashRegion);
											}
										}
									}
									else{
										cashRegion = sessionMap.get(sessionId).get("region") + "";
									}

								} else {
									cashRegion = region;
									Map map = sessionMap.get(sessionId);
									map.put("region", region);
								}
								if (zone.equalsIgnoreCase(""))
								{
									String user_ssoId2=sessionMap.get(sessionId).get("user_ssoid")+"";
									if(user_ssoId2.equalsIgnoreCase("") || user_ssoId2.equalsIgnoreCase("null"))
									{
										if(!checkChannel.equalsIgnoreCase(channel) && !"".equalsIgnoreCase(channel))
										{
											cashZone= sessionMap.get(sessionId).get("zone")+"";
											if("".equalsIgnoreCase(cashZone))
											{
												cashZone= sessionMap.get(sessionId).get("zone")+"";
											}
											else
											{
												cashZone="";
												Map map = sessionMap.get(sessionId);
												map.put("zone", cashZone);
											}
										}
										else{
											cashZone= sessionMap.get(sessionId).get("zone")+"";
											if (!checkSubChannel.equalsIgnoreCase(subChannel) && !"".equalsIgnoreCase(subChannel))
											{
												cashZone="";
											}
										}
									}
									else{
										cashZone = sessionMap.get(sessionId).get("zone")+"";
									}
								} else {
									cashZone = zone;
									Map map = sessionMap.get(sessionId);
									map.put("zone", zone);
								}
								if (subChannel.equalsIgnoreCase(""))
								{
									String user_ssoId3=sessionMap.get(sessionId).get("user_ssoid")+"";
									if(user_ssoId3.equalsIgnoreCase("") || user_ssoId3.equalsIgnoreCase("null"))
									{
										if(!checkChannel.equalsIgnoreCase(channel) && !"".equalsIgnoreCase(channel))
										{
											cash_Sub_Channel= sessionMap.get(sessionId).get("subChannel") + "";;
											if("".equalsIgnoreCase(cash_Sub_Channel))
											{
												cash_Sub_Channel= sessionMap.get(sessionId).get("subChannel") + "";;
											}
											else
											{
												cash_Sub_Channel="";
												Map map = sessionMap.get(sessionId);
												map.put("subChannel", cash_Sub_Channel);
											}
										}
										else{
											cash_Sub_Channel= sessionMap.get(sessionId).get("subChannel") + "";;
										}
									}
									else{
										cash_Sub_Channel = sessionMap.get(sessionId).get("subChannel") + "";
									}
								} else {
									cash_Sub_Channel = subChannel;
									Map map = sessionMap.get(sessionId);
									map.put("subChannel", subChannel);
								}
							}
							if (!actionperformed.equalsIgnoreCase("") && actionperformed != null && "".equalsIgnoreCase(speech)) 
							{
								try
								{
									HttpUrlConnectionMlabInsert mlab = new HttpUrlConnectionMlabInsert();
									String status=mlab.httpConnection_response_mlab_Insert(sessionId, ssoId, actionperformed, resolvedQuery, cachePeriod, 
															       cashproductType, cashplanType);
									System.out.println(status);
								}catch(Exception ex)
								{
									System.out.println("Something goes wrong to connect Mlab:MongoDb");
								}
								return aPIConsumerService.getWipDataAll(actionperformed, cashchannel, cachePeriod, cashproductType, cashplanType,
										user_ssoid, cash_Sub_Channel, user_designation_desc, cashZone, cashRegion, cashCircle, 
										user_clusters, user_go, user_cmo, user_amo);
							}
						}
						/*------------First Time when user comes with SessionId---------------------------------------------------------------------------------*/
						else 
						{
							try
							{
								String segment="SSO_VALIDATION";
								HttpUrlConnection_GetDetails getdetail = new HttpUrlConnection_GetDetails();
								//String ssoId2="vsbby0105";
								String getdetailresult = getdetail.getUserDetail(segment, ssoId);
								//String getdetailresult = getdetail.getUserDetail(segment, ssoId2);
								JSONObject getUserDetailObject = new JSONObject(getdetailresult);
								try{
									user_ssoid=getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("ssoid")+"";
								}catch(Exception ex){user_ssoid="";}
								try{
									user_channel=getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("channel")+"";
								}catch(Exception ex){user_channel="";}
								try{
									user_sub_channel=getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("sub_channel")+"";
								}catch(Exception ex){user_sub_channel="";}
								try{
									user_designation_desc=getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("designation_desc")+"";
								}catch(Exception ex){user_designation_desc="";}
								try{
									user_getzone=getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("zone")+"";
								}catch(Exception ex){user_getzone="";}
								try{
									user_region=getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("region")+"";
								}catch(Exception ex){user_region="";}
								try{
									user_circle=getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("circle")+"";
								}catch(Exception ex){user_circle="";}
								try{
									user_clusters=getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("clusters")+"";
								}catch(Exception ex){user_clusters="";}
								try{
									user_go=getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("go")+"";
								}catch(Exception ex){user_go="";}
								try{
									user_cmo=getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("cmo")+"";
								}catch(Exception ex){user_cmo="";}
								try{
									user_amo=getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("amo")+"";
								}catch(Exception ex){user_amo="";}
							}catch(Exception ex)
							{
								System.out.println("Exception Occured while while calling External API GetUserDetail");
							}
							Map<String, String> map = new HashMap<String, String>();
							if(!user_ssoid.equalsIgnoreCase(""))
							{
								map.put("user_ssoid", user_ssoid);
							}
							if(user_channel.equalsIgnoreCase(""))
							{
								map.put("channel", channel);
								channel=channel;

							}else
							{
								map.put("channel", user_channel);
								channel=user_channel;
							}
							if (productType.equalsIgnoreCase(""))
							{
								productType = "";
								map.put("productType", productType);
							}else {
								map.put("productType", productType);
							}
							if (period.equalsIgnoreCase("")) 
							{
								period = "";
								map.put("period", period);
							}else {
								map.put("period", period);
							}
							map.put("planType", planType);
							if(user_circle.equalsIgnoreCase(""))
							{
								map.put("circle", circle);
								circle=circle;

							}else{
								map.put("circle", user_circle);
								circle=user_circle;
							}
							if(user_region.equalsIgnoreCase(""))
							{
								map.put("region", region);
								region=region;

							}else{
								map.put("region", user_region);
								region=user_region;
							}
							if(user_getzone.equalsIgnoreCase(""))
							{
								map.put("zone", zone);
								zone=zone;
							}
							else{
								map.put("zone", user_getzone);
								zone=user_getzone;
							}
							if(user_sub_channel.equalsIgnoreCase(""))
							{
								map.put("subChannel", subChannel);
								subChannel=subChannel;

							}
							else{
								map.put("subChannel", user_sub_channel);
								subChannel=user_sub_channel;
							}
							for(int i=0; i<=0; i++)
							{
								if(!"".equalsIgnoreCase(user_circle))
								{
									map.put("employeeIdentification", "CR");
									break;
								}
								else if(!"".equalsIgnoreCase(user_region))
								{
									map.put("employeeIdentification", "RE");
									break;
								}
								else if(!"".equalsIgnoreCase(user_getzone))
								{
									map.put("employeeIdentification", "ZN");
									break;
								}
								else if(!"".equalsIgnoreCase(user_sub_channel))
								{
									map.put("employeeIdentification", "SB");
									break;
								}
								else
								{
									map.put("employeeIdentification", "CH");
								}
							}
							if (actionperformed.equalsIgnoreCase("nb.channel")
									|| actionperformed.equalsIgnoreCase("nb.period")
									|| actionperformed.equalsIgnoreCase("nb.circle")
									|| actionperformed.equalsIgnoreCase("nb.zone")
									|| actionperformed.equalsIgnoreCase("nb.region")
									|| actionperformed.equalsIgnoreCase("nb.channelnzone")
									|| actionperformed.equalsIgnoreCase("nb.zonenregion")
									|| actionperformed.equalsIgnoreCase("nb.subchannel")
									|| actionperformed.equalsIgnoreCase("nb.channelncircle")
									|| actionperformed.equalsIgnoreCase("nb.subchannelnregion")
									|| actionperformed.equalsIgnoreCase("nb.zonencircle")
									|| actionperformed.equalsIgnoreCase("nb.channelnregion")
									|| actionperformed.equalsIgnoreCase("nb.channelnzonenregion")
									|| actionperformed.equalsIgnoreCase("nb.channelnzonencircle"))
							{
								actionperformed = "NUMBERS";
							}
							map.put("action", actionperformed);
							sessionMap.put(sessionId, map);
							if(!"".equalsIgnoreCase(user_ssoid) || !user_ssoid.equalsIgnoreCase("null"))
							{
								extraMap.put(user_ssoid, map);
							}
							if(!actionperformed.equalsIgnoreCase("") && actionperformed!=null)
							{
								try
									{
										HttpUrlConnectionMlabInsert mlab = new HttpUrlConnectionMlabInsert();
										String status=mlab.httpConnection_response_mlab_Insert(sessionId, ssoId, actionperformed,
																       resolvedQuery, period, productType, planType);
										System.out.println(status);
									}catch(Exception ex)
									{
										System.out.println("Something goes wrong to connect Mlab:MongoDb");
									}
								return aPIConsumerService.getWipDataAll(actionperformed, channel, period, productType, planType, user_ssoid, 
										subChannel, user_designation_desc, zone, region, circle, user_clusters, user_go, user_cmo, user_amo);
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
		WebhookResponse responseObj = new WebhookResponse(speech, speech, null);
		System.out.println("End : Controller: Webhook");
		return responseObj;
	}

	public Map<String, Map<String,String>> APICallSSOValidation(String ssoId, String sessionId)
	{
		String phoneNo="";	String agentName="";
		String DevMode = "N";
		String mnylstatus="";
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
			//String serviceurl3 = resProp.getString("servicephoneno");
                        String serviceurl3 = resProp.getString("serviceproduction");
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
			requestdata.append("	            \"soaAppId\": \"MISBOT\",	");
			requestdata.append("	            \"soaUserId\": \"MISBOTPROD123\",	");
			requestdata.append("	            \"soaPassword\": \"cGFzd29yZDU=\"	");
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
					mnylstatus=object.getJSONObject("response").getJSONObject("responseData").getJSONArray("Transactions").getJSONObject(0).get("mnylstatus")+ "";
					phoneNo="9891596808";
					agentName = object.getJSONObject("response").getJSONObject("responseData").getJSONArray("Transactions").getJSONObject(0).get("givenname")+"";
					if (phoneNo != null && !"".equalsIgnoreCase(phoneNo) && mnylstatus!=null && !"".equalsIgnoreCase(mnylstatus)
							&& "Y".equalsIgnoreCase(mnylstatus)) 
					{
						cashData = mliBotController.OTPVarification(sessionId, phoneNo, agentName, ssoId);
					}
					else
					{
						blankmessage.put("PhoneStatus", "NotAvail");
						blankmessage.put("mnylStatus", "N");
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
			requestdata.append("	         \"appAccId\": \"SRV_BOTS\",	");
			requestdata.append("	         \"appAccPass\": \"t(9Kp~8P\",	");
			requestdata.append("	         \"appId\": \"MAXLIT\",	");
			requestdata.append("	         \"msgTo\": \""+phoneno+"\",	");
			requestdata.append("	         \"msgText\": \"Your Maxlife Assistant OTP is:"+randomotp+"\"	");
			requestdata.append("	      }	");
			requestdata.append("	   }	");
			requestdata.append("	}	");

			System.out.println("External API Call : START");
			OutputStreamWriter writer3 = new OutputStreamWriter(conn.getOutputStream());
			writer3.write(requestdata.toString());
			writer3.flush();
			try {writer3.close(); } catch (Exception e1) {}
			int apiResponseCode3 = conn.getResponseCode();

			if(apiResponseCode3 == 200 || apiResponseCode3 == 201)
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
					System.out.println(randomotp);
					otpsession.put("SoaStatus", "success");
					otpsession.put("validSSOID", ssoId);
					otpsession.put("channel", "MLI");
					otpsession.put("period", "MTD");
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
