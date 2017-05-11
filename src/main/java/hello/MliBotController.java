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

import common.Commons;
import service.APIConsumerService;

@Controller
@RequestMapping("/webhook")
public class MliBotController{

	public static ResourceBundle resProp = ResourceBundle.getBundle("errorMessages");
	@Autowired
	APIConsumerService apiConsumerService;
	
	//SurrenderValueHandler surrenderValueHandler;
	//PaymentRecieptHandler paymentRecieptHandler;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody WebhookResponse webhook(@RequestBody String obj, Model model, HttpSession httpSession) {
		//WebhookResponse response = new WebhookResponse();
		String speech="";
		try 
		{
			JSONObject object = new JSONObject(obj.toString());
			String actionperformed = object.getJSONObject("result").get("action")+"";
			String channel = object.getJSONObject("result").getJSONObject("parameters").getString("Channel")+"";
			
			if(actionperformed.equalsIgnoreCase("NUMBERS") && channel.equalsIgnoreCase("MLI"))
			{
				return apiConsumerService.getWipDataAll(actionperformed, channel);
			}
			else
			{
				//response.setSpeech("Inappropriate Action Get from Json");
				//response.setDisplayText("Inappropriate Action Get from Json");
				speech="Inappropriate Action Get from Json";
			}
		} 
		catch (Exception e)
		{
			System.out.println("error occured during calling GSTDetail Service" + e);
		}
		WebhookResponse responseObj = new WebhookResponse(speech, speech);
		
		return responseObj;
	}

}


