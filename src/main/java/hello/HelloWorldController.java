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
	private static HashMap menuHashMap = new HashMap();
	private static final String VALID_POL = "ValidPol";
	private static final String VALID_OTP = "ValidOTP";
	private static final String SESSION = "SessionID";
	private static final String CACHE_OTP = "CacheOTP";
	private static final String POL_DATA = "PolData";
	@Autowired
	APIConsumerService apiConsumerService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody WebhookResponse webhook(@RequestBody String obj, Model model, HttpSession httpSession) {
		String speech = null;
		try {
			Map<String, String> serviceResp = null;
			System.out.println(obj);
			Map requestJsonObj = Commons.getGsonData(obj);
			Map result = (Map) requestJsonObj.get("result");
			String action = result.get("action").toString();

			if (menuHashMap.get(SESSION) == null
					|| !menuHashMap.get(SESSION).equals(requestJsonObj.get("sessionId").toString())) {
				menuHashMap.clear();
				menuHashMap.put(SESSION, requestJsonObj.get("sessionId").toString());
			}
			if ("PolicyNumberValidation".equals(action)) {
				if (menuHashMap.get(VALID_POL) != null) {
					speech = "OTP has been sent to your registered mobile number for policy number "
							+ menuHashMap.get(VALID_POL) + ", please provide the same for verification";
				} else {
					Map parameters = (Map) result.get("parameters");
					Map policyNumber = (Map) parameters.get("PolicyNumber");
					String G_PolicyNumber = policyNumber.get("Given-PolicyNumber").toString();
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
				if (menuHashMap.get(VALID_OTP) != null) {
					speech = "OTP Verification is completed for Policy Number " + menuHashMap.get(VALID_POL)
							+ ", please tell what you want to know about policy";
				} else {
					String otp_session = null;
					Map parameters = (Map) result.get("parameters");
					Map OTP_Number = (Map) parameters.get("OTP");
					String OTP_request = OTP_Number.get("Provided-OTP").toString();
					JSONObject resultdataJson = new JSONObject(obj);
					String requiredadata = resultdataJson.getJSONObject("result").getJSONArray("contexts")
							.getJSONObject(0).getJSONObject("parameters").getJSONObject("PolicyNumber")
							.get("Given-PolicyNumber.original") + "";

					System.out.println("policynumber is:*******" + requiredadata);
					speech = "I am in OTPValidationAction. User agve this OTP-" + OTP_request;
					otp_session = menuHashMap.get(CACHE_OTP).toString();
					if (otp_session != null) {
						if (otp_session.equals(OTP_request)) {
							speech = "Mr. Arun. What information you want to know about your policy";
							Map data = apiConsumerService.getPolicyInfo(menuHashMap.get(VALID_POL).toString());
							System.out.println("data----------"+data.toString());
							menuHashMap.put(VALID_OTP, OTP_request);
							menuHashMap.put(POL_DATA, data);
							menuHashMap.remove(CACHE_OTP);
							// resultdataJson.getJSONObject("result").getJSONArray("contexts").getJSONObject(0).getJSONObject("parameters").put("validOTP.original",
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



