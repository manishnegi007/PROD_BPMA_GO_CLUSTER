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
        public static Map<String, Map<String,String>> sessionMap = new ConcurrentHashMap<String, Map<String,String>>();
	
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
		String sessionId="";
		String cachePeriod=""; String cashplanType=""; String cashchannel=""; String cashproductType="";

		WebhookResponse response = new WebhookResponse();
/*		MliBotControllerRest mliBotControllerRest= new MliBotControllerRest();*/
		try 
		{
			System.out.println("WebhookResponse API START");
			JSONObject object = new JSONObject(obj);
			String actionperformed = object.getJSONObject("result").get("action")+"";
			sessionId = object.get("sessionId")+"";
/*			String ssoId="";
			String password="";
			switch(actionperformed)
			{
			case "SSOValidation":
			{
				String status = mliBotControllerRest.APICallSSOValidation(ssoId, password);
				JSONObject finalStatus = new JSONObject(status);
			}
			break;
			}*/
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
		catch (Exception e)
		{
		 System.out.println("End : Controller: Webhook- Exception");	
		}
		WebhookResponse responseObj = new WebhookResponse(speech, speech);
		System.out.println("End : Controller: Webhook");
		return responseObj;
	}
}




