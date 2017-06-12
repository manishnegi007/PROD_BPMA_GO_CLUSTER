package hello;

public class WebhookResponse {
   	  String speech;
	  String displayText;
	  
	  
	public WebhookResponse(String speech, String displayText) {
		super();
		this.speech = speech;
		this.displayText = displayText;
	}


	public String getSpeech() {
		return speech;
	}


	public void setSpeech(String speech) {
		this.speech = speech;
	}


	public String getDisplayText() {
		return displayText;
	}


	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	 
	
}
