package hello;

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
public class HelloWorldController
{
	
	@Autowired
	APIConsumerService apiConsumerService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody WebhookResponse webhook(@RequestBody String obj, Model model, HttpSession httpSession) {
		String speech = null;
		try {
			// if(action.equals("PolicyNumberValidation")){
			// System.out.println("input request --Query param :$$$$$$$$$:
			// "+input);

			String serviceResp = null;

			System.out.println(obj);

			// Map resjson = Commons.getGsonData(obj);
			Map requestJsonObj = Commons.getGsonData(obj);
			Map result = (Map) requestJsonObj.get("result");
			String action = result.get("action").toString();
			if (action.equals("PolicyNumberValidation")) {
				Map parameters = (Map) result.get("parameters");
				Map PolicyNumber = (Map) parameters.get("PolicyNumber");
				String G_PolicyNumber = PolicyNumber.get("Given-PolicyNumber").toString();
				System.out.println(G_PolicyNumber);

				//CTPServiceAction ctpserviceAction = new CTPServiceAction();
				serviceResp = apiConsumerService.getPolicyOtp(G_PolicyNumber);
				if (serviceResp.equals("")) {
					speech = "Policy Number seems to be incorrect.Please provide valid Policy Number";
				} else {
					speech = "OTP is sent to your Registered Mobile Number. Please provide your OTP for verification";
					System.out.println("OTP IS-------"+serviceResp);
					/*if (httpSession.getAttribute("CACHEOTP_" + G_PolicyNumber) == null)
						httpSession.setAttribute("CACHEOTP_" + G_PolicyNumber, serviceResp);*/
				}
			} else if (action.equals("OTPValidation")) {
				speech="I am in OTPValidationAction";
				String otp_session = null;
				Map parameters = (Map) result.get("parameters");
				//Map PolicyNumber = (Map) parameters.get("PolicyNumber");

				//String G_PolicyNumber = PolicyNumber.get("Given-PolicyNumber").toString();
				Map OTP_Number = (Map) parameters.get("OTP");
				//String resultdata="{\"id\":\"c70014bb-ea19-4e2b-a70f-fadb9dcc649a\",\"timestamp\":\"2017-03-31T12:15:46.041Z\",\"lang\":\"en\",\"result\":{\"source\":\"agent\",\"resolvedQuery\":\"1452\",\"action\":\"OTPValidation\",\"actionIncomplete\":false,\"parameters\":{\"OTP\":{\"Provided-OTP\":\"1452\"}},\"contexts\":[{\"name\":\"policynumber\",\"parameters\":{\"CustomerRequest\":[],\"CustomerRequest.original\":\"\",\"PolicyNumber\":{\"Given-PolicyNumber.original\":\"719438228\",\"Given-PolicyNumber\":\"719438228\"},\"PolicyNumber.original\":\"719438228\",\"OTP.original\":\"1452\",\"OTP\":{\"Provided-OTP.original\":\"1452\",\"Provided-OTP\":\"1452\"}},\"lifespan\":5},{\"name\":\"otp\",\"parameters\":{\"OTP.original\":\"1452\",\"OTP\":{\"Provided-OTP.original\":\"1452\",\"Provided-OTP\":\"1452\"}},\"lifespan\":5}],\"metadata\":{\"intentId\":\"6ae37867-7943-452b-8bf8-1788ade079d5\",\"webhookUsed\":\"true\",\"webhookForSlotFillingUsed\":\"false\",\"intentName\":\"Policy.OTP.Validation\"},\"fulfillment\":{\"speech\":\"OTP not found in session\",\"source\":\"java-webhook\",\"displayText\":\"OTP not found in session\",\"messages\":[{\"type\":0,\"speech\":\"OTP not found in session\"}]},\"score\":0.75124997},\"status\":{\"code\":200,\"errorType\":\"success\"},\"sessionId\":\"ebdf7299-4e10-43bd-8bc9-193ec0b294a4\"}";
				JSONObject resultdataJson=new JSONObject(obj);
			    String requiredadata=resultdataJson.getJSONObject("result").getJSONArray("contexts").getJSONObject(0).getJSONObject("parameters").getJSONObject("PolicyNumber").get("Given-PolicyNumber.original")+"";
				System.out.println("policynumber is:*******"+requiredadata);
				String OTP_request = OTP_Number.get("Provided-OTP").toString();
				speech="I am in OTPValidationAction. User agve this OTP-"+OTP_request;
				otp_session=apiConsumerService.getPolicyOtp(requiredadata);
				if(otp_session!=null){
					if (otp_session.equals(OTP_request)) {
						speech = "OTP Validated";
					} else {
						speech = "OTP did not match";
					}
				} else {
					speech = "OTP not found in session";

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(speech);
		return new WebhookResponse(speech, speech);
	}
}






