package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.APIConsumerService;

@Component
public class SurrenderValueHandler {
	
	@Autowired
	APIConsumerService apiConsumerService;
	
	public String getSurrenderValue(String policyNumber){
		//apiConsumerService.getPolicyDetails(policyNumber);
		return apiConsumerService.getPolicyDetails(policyNumber).get("Message");
		
	}
}
