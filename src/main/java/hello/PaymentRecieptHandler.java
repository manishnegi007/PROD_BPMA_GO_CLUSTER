package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.APIConsumerService;

@Component
public class PaymentRecieptHandler {
	
	@Autowired
	APIConsumerService apiConsumerService;
	
	public String sendPayReciept(String policyNo){
		
		return apiConsumerService.getMliDocService(policyNo).get("Message");
		
	}
}

