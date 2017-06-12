package hello;

public class WebhookResponse {
    private final String speech;
    private final String displayText;

    private final String source = "java-webhook";
    
    private String speech;
	 private String displayText;
	 
	 
	 
	public WebhookResponse() {
		super();
	}
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
