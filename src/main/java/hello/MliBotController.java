package hello;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
import service.APIConsumerService;

@Controller
@RequestMapping("/webhook")
public class MliBotController{

	public static ResourceBundle resProp = ResourceBundle.getBundle("errorMessages");
	
	@Autowired
	APIConsumerService aPIConsumerService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody WebhookResponse webhook(@RequestBody String obj, Model model, HttpSession httpSession) {
		//WebhookResponse response = new WebhookResponse();
		System.out.println("CameInside :- Controller: Webhook");
		String speech="";
		String productType="";
		String planType="";
		String period="";
		String channel="";
		
		WebhookResponse response = new WebhookResponse();
		try 
		{
			System.out.println("WebhookResponse API START");
			JSONObject object = new JSONObject(obj);
			String actionperformed = object.getJSONObject("result").get("action")+"";
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
				period = object.getJSONObject("result").getJSONArray("contexts").getJSONObject(0).getJSONObject("parameters").getString("Period")+"";
			}catch(Exception e)
			{
				
				period="";
			}
			try{
				planType = object.getJSONObject("result").getJSONObject("parameters").getString("planType")+"";
			}catch(Exception e)
			{
				
				productType="";
			}
			if(actionperformed.equalsIgnoreCase(actionperformed) && channel.equalsIgnoreCase(channel))
			{
				return = aPIConsumerService.getWipDataAll(actionperformed, channel, period, productType, planType);
			}
		} 
		catch (Exception e)
		{
			
		}
		WebhookResponse responseObj = new WebhookResponse(speech, speech);
		System.out.println("End : Controller: Webhook");
		return responseObj;
	}

}


