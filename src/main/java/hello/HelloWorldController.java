package hello;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Commons;
import service.CTPServiceAction;

@Controller
@RequestMapping("/webhook")
public class HelloWorldController {

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

				CTPServiceAction ctpserviceAction = new CTPServiceAction();
				serviceResp = ctpserviceAction.getOTP(G_PolicyNumber);
				if (serviceResp.equals("false")) {
					speech = "Policy Number seems to be incorrect.Please provide valid Policy Number";
				} else {
					speech = "OTP is sent to your Registered Mobile Number. Please provide your OTP for verification";
					if (httpSession.getAttribute("CACHEOTP_" + G_PolicyNumber) == null)
						httpSession.setAttribute("CACHEOTP_" + G_PolicyNumber, serviceResp);
				}
			} else if (action.equals("OTPValidation")) {
				speech="I am in OTPValidationAction";
				String otp_session = null;
				Map parameters = (Map) result.get("parameters");
				//Map PolicyNumber = (Map) parameters.get("PolicyNumber");

				//String G_PolicyNumber = PolicyNumber.get("Given-PolicyNumber").toString();
				Map OTP_Number = (Map) parameters.get("OTP");
				
				String OTP_request = OTP_Number.get("Provided-OTP").toString();
				speech="I am in OTPValidationAction. User agve this OTP-"+OTP_request;
				if (httpSession.getAttribute("CACHEOTP_719438228") != null) {
					otp_session = httpSession.getAttribute("CACHEOTP_719438228").toString();
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




