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
public class HelloWorldController 
{

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody WebhookResponse webhook(@RequestBody String obj, Model model, HttpSession httpSession){
		String speech=null;
		try {
			//if(action.equals("PolicyNumberValidation")){
			//System.out.println("input request --Query param :$$$$$$$$$: "+input);

			String serviceResp = null;

			System.out.println(obj);

			Map resjson=Commons.getGsonData(obj);
			Map requestJsonObj=Commons.getGsonData(obj);
			Map result=(Map)requestJsonObj.get("result");
			Map parameters=(Map)result.get("parameters");
			Map PolicyNumber=(Map)parameters.get("PolicyNumber");
			String G_PolicyNumber=PolicyNumber.get("Given-PolicyNumber").toString();
			System.out.println(G_PolicyNumber);

			CTPServiceAction ctpserviceAction = new CTPServiceAction();			
			serviceResp = ctpserviceAction.getOTP(G_PolicyNumber);	
			httpSession.setAttribute("CACHEOTP", serviceResp);
			speech="OTP is sent to your Registered Mobile Number. Please provide your OTP for verification";
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(obj);
		return new WebhookResponse(speech, speech);
	}
}

