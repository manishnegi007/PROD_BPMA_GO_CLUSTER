package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.APIConsumerService;
import java.util.Map;

@Component
public class SurrenderValueHandler {
	
	@Autowired
	APIConsumerService apiConsumerService;
	
	public String getSurrenderValue(Map<String, Map> map, String policyNumber){
		//apiConsumerService.getPolicyDetails(policyNumber);
		
		String str = (String)(apiConsumerService.getPolicyDetails(map, policyNumber).get("Message"));
		System.out.println("Speech in SurrenderHandler " + str);
		return str;
		
	}
}
