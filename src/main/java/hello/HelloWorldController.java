package hello;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Commons;
import service.APIConsumerService;

@Controller
@RequestMapping("/webhook")
public class HelloWorldController {
	private static HashMap mainHashMap = new HashMap();
	private static final String VALID_POL = "ValidPol";
	private static final String VALID_OTP = "ValidOTP";
	private static final String SESSION = "SessionID";
	private static final String CACHE_OTP = "CacheOTP";
	private static final String POL_DATA = "PolData";
	private static final String OTP_UA = "OTPUnavailable";
	@Autowired
	APIConsumerService apiConsumerService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody WebhookResponse webhook(@RequestBody String obj, Model model, HttpSession httpSession) {
		String speech = null;
		try {
			Map<String, String> serviceResp = null;
			HashMap menuHashMap = null;
			String G_PolicyNumber = null;
			Map parameters = null;
			Map policyNumber = null;

			
			System.out.println(obj);
			Map requestJsonObj = Commons.getGsonData(obj);
			Map result = (Map) requestJsonObj.get("result");
			String action = result.get("action").toString();

			String sessionId = requestJsonObj.get("sessionId").toString();
			
			if (mainHashMap.get(sessionId) == null) {
				System.out.println("Start of new session Id " + sessionId);
				menuHashMap = new HashMap();
				mainHashMap.put(sessionId, menuHashMap);
			} else {
				System.out.println("Existing session found " + sessionId);
				menuHashMap = (HashMap)mainHashMap.get(sessionId);
			}

			/*if (menuHashMap.get(SESSION) == null
					|| !menuHashMap.get(SESSION).equals(requestJsonObj.get("sessionId").toString())) {
				menuHashMap.clear();
				menuHashMap.put(SESSION, requestJsonObj.get("sessionId").toString());
			}*/
			
			if ("OTP.NotAvailable".equas(action)) {
				//Regenrate OTP and send to customer
				menuHashMap.put(OTP_UA, "Y");
				menuHashMap.remove(VALID_OTP);
				
				parameters = (Map) result.get("parameters");
					policyNumber = (Map) parameters.get("PolicyNumber");
					G_PolicyNumber = policyNumber.get("Given-PolicyNumber").toString();
					System.out.println(G_PolicyNumber);
					serviceResp = apiConsumerService.getPolicyOtp(G_PolicyNumber);
					speech = serviceResp.get("Message");
					if (serviceResp.get("policyotp") != null) {
						menuHashMap.put(CACHE_OTP, serviceResp.get("policyotp"));
						menuHashMap.put(VALID_POL, G_PolicyNumber);
						System.out.println("OTP is **** " + serviceResp.get("policyotp"));
					}
				
			}
			if ("PolicyNumberValidation".equals(action)) {

				parameters = (Map) result.get("parameters");
				policyNumber = (Map) parameters.get("PolicyNumber");
				G_PolicyNumber = policyNumber.get("Given-PolicyNumber").toString();

			
				if (menuHashMap.get(VALID_POL) != null && menuHashMap.get(VALID_OTP) != null) {
			
					String policyNumberInSession = (String)menuHashMap.get(VALID_POL);
					
					System.out.println("Policy number in session " + policyNumberInSession);
					System.out.println("Policy number from parameter " + G_PolicyNumber);
					if (policyNumberInSession != null) {
						if (!(G_PolicyNumber.equals(policyNumberInSession))) {
							System.out.println("A new policy number entered by the customer " + G_PolicyNumber);
							System.out.println("Clearing Map");
							menuHashMap.clear();
							//menuHashMap.put(VALID_POL, G_PolicyNumber);
						}
					}

				}	


							
				
				if (menuHashMap.get(VALID_POL) != null && menuHashMap.get(VALID_OTP) != null) {
					speech = "OTP Verification is completed for Policy Number " + menuHashMap.get(VALID_POL)
							+ ", please tell what you want to know about policy";
				} else if (menuHashMap.get(VALID_POL) != null) {
					String otp_session = null;
					parameters = (Map) result.get("parameters");
					policyNumber = (Map) parameters.get("PolicyNumber");
					G_PolicyNumber = policyNumber.get("Given-PolicyNumber").toString();
					String cache_otp = (String)menuHashMap.get(CACHE_OTP);
					if (cache_otp != null) {
						otp_session = (String)menuHashMap.get(CACHE_OTP);
					}
					if (otp_session != null) {
						
						System.out.println("In loop " + G_PolicyNumber);
						System.out.println("In loop " + otp_session);

						if (otp_session.equals(G_PolicyNumber)) {
							speech = "Mr. Arun. What information you want to know about your policy";
							Map data = apiConsumerService.getPolicyInfo(menuHashMap.get(VALID_POL).toString());
							System.out.println("data----------" + data.toString());
							menuHashMap.put(VALID_OTP, G_PolicyNumber);
							menuHashMap.put(POL_DATA, data);
							menuHashMap.remove(CACHE_OTP);
							// resultdataJson.getJSONObject("result").getJSONArray("contexts").getJSONObject(0).getJSONObject

							//("parameters").put("validOTP.original",
							// otp_session.toString());
							// resultdataJson.getJSONObject("result").getJSONObject("fulfillment").put("speech",
							// speech);
							// System.out.println(resultdataJson);
						} else {
							speech = "OTP did not match.Please provide correct OTP.";
						}
					} else {
						speech = "You have not generated OTP.Please provide valid policy to generate OTP";
					}

					/*
					 * speech =
					 * "OTP has been sent to your registered mobile number for policy number "
					 * + menuHashMap.get(VALID_POL) +
					 * ", please provide the same for verification OR write reset to start new session"
					 * ;
					 */
				} else {
					parameters = (Map) result.get("parameters");
					policyNumber = (Map) parameters.get("PolicyNumber");
					G_PolicyNumber = policyNumber.get("Given-PolicyNumber").toString();
					System.out.println(G_PolicyNumber);
					serviceResp = apiConsumerService.getPolicyOtp(G_PolicyNumber);
					speech = serviceResp.get("Message");
					if (serviceResp.get("policyotp") != null) {
						menuHashMap.put(CACHE_OTP, serviceResp.get("policyotp"));
						menuHashMap.put(VALID_POL, G_PolicyNumber);
						System.out.println("OTP is **** " + serviceResp.get("policyotp"));
					}
				}
			} else if (action.equals("OTPValidation")) {
				if (menuHashMap.get(VALID_POL) == null) {
					speech = "Please Validate Customer identity first by giving correct Policy Number.";
				} else if (menuHashMap.get(VALID_OTP) != null) {
					speech = "OTP Verification is completed for Policy Number " + menuHashMap.get(VALID_POL)
							+ ", please tell what you want to know about policy  OR write reset to start new session";
				} else {
					String otp_session = null;
					parameters = (Map) result.get("parameters");
					Map OTP_Number = (Map) parameters.get("OTP");
					String OTP_request = OTP_Number.get("Provided-OTP").toString();
					JSONObject resultdataJson = new JSONObject(obj);
					String requiredadata = resultdataJson.getJSONObject("result").getJSONArray("contexts")
							.getJSONObject(0).getJSONObject("parameters").getJSONObject("PolicyNumber")
							.get("Given-PolicyNumber.original") + "";

					System.out.println("policynumber is:*******" + requiredadata);
					// speech = "I am in OTPValidationAction. User agve this
					// OTP-" + OTP_request;
					otp_session = menuHashMap.get(CACHE_OTP).toString();
					if (otp_session != null) {
						if (otp_session.equals(OTP_request)) {
							speech = "Mr. Arun. What information you want to know about your policy";
							Map data = apiConsumerService.getPolicyInfo(menuHashMap.get(VALID_POL).toString());
							System.out.println("data----------" + data.toString());
							menuHashMap.put(VALID_OTP, OTP_request);
							menuHashMap.put(POL_DATA, data);
							menuHashMap.remove(CACHE_OTP);
							// resultdataJson.getJSONObject("result").getJSONArray("contexts").getJSONObject(0).getJSONObject

							//("parameters").put("validOTP.original",
							// otp_session.toString());
							// resultdataJson.getJSONObject("result").getJSONObject("fulfillment").put("speech",
							// speech);
							// System.out.println(resultdataJson);
						} else {
							speech = "OTP did not match.Please provide correct OTP.";
						}
					} else {
						speech = "You have not generated OTP.Please provide valid policy to generate OTP";
					}
				}
			} else if (action.equals("input.CTP")) {
				System.out.println("i am in ctp action");
				if (menuHashMap.get(VALID_POL) == null) {
					speech = "Please Validate Customer identity first by giving correct Policy Number.";
				} else if (menuHashMap.get(VALID_OTP) == null) {
					speech = "Please Validate OTP by giving correct OTP for Policy Number" + menuHashMap.get(VALID_POL);
				} else {
					System.out.println("i am in ctp action 1");
					Map data = (Map) menuHashMap.get(POL_DATA);
					if (data.get("CTP") == null) {
						System.out.println("i am in ctp action 2");
						speech = ((Map) data.get("ErrorMessage")).get("Message").toString();
					} else {
						System.out.println("i am in ctp action 3");
						Map ctp = (Map) data.get("CTP");
						speech = ctp.get("Message").toString();
					}
				}
			} else if (action.equals("input.Maturity")) {
				speech = "Maturity is 1234";
			} else if (action.equals("input.Surrender")) {
				if (menuHashMap.get(VALID_POL) == null) {
					speech = "Please Validate Customer identity first by giving correct Policy Number.";
				} else if (menuHashMap.get(VALID_OTP) == null) {
					speech = "Please Validate OTP by giving correct OTP for Policy Number" + menuHashMap.get(VALID_POL);
				} else {
					Map data = (Map) menuHashMap.get(POL_DATA);
					if (data.get("CSV") == null) {
						if (data.get("ErrorMessage") == null) {
							speech = "API need to be integrated";
						}
					} else {
						Map csv = (Map) data.get("CSV");
						speech = csv.get("Message").toString();
					}
				}
			} else if (action.equals("input.Receipt")) {
				speech = "payment reciept is sent to your email";
			} else if (action.equals("input.Fund")) {
				if (menuHashMap.get(VALID_POL) == null) {
					speech = "Please Validate Customer identity first by giving correct Policy Number.";
				} else if (menuHashMap.get(VALID_OTP) == null) {
					speech = "Please Validate OTP by giving correct OTP for Policy Number" + menuHashMap.get(VALID_POL);
				} else {
					Map data = (Map) menuHashMap.get(POL_DATA);
					if (data.get("FV") == null) {
						speech = ((Map) data.get("ErrorMessage")).get("Message").toString();
					} else {
						Map fv = (Map) data.get("FV");
						if (fv.get("fundValAsonDate") != null) {
							String fvdata = fv.get("fundValAsonDate").toString();
							speech = fv.get("Message").toString() + fvdata;
						} else {
							speech = fv.get("Message").toString();
						}

					}
				}
			} else if (action.equals("close.conversation")) {
				menuHashMap.clear();
				speech = "You can now start new conversation!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(speech);
		/*
		 * Context context = new Context(); context.setLifespan("life ");
		 * context.setName("Name"); Map parameters = new HashMap<String,
		 * String>(); parameters.put("", ""); parameters.put("", "");
		 * ArrayList<Map> parameter = new ArrayList<>();
		 * parameter.add(parameters); context.setParameters(parameter);
		 * List<Context> contextList = new ArrayList<Context>();
		 * responseObj.setContextOut(contextList);
		 */
		WebhookResponse responseObj = new WebhookResponse(speech, speech);
		return responseObj;
	}
}



