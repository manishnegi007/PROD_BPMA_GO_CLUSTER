package hello;

public class WebhookResponse 
{
	private String speech;
	private String displayText;
	private InnerData data;
	

	public WebhookResponse() {
		super();
	}


	public WebhookResponse(String speech, String displayText, InnerData data) {
		super();
		this.speech = speech;
		this.displayText = displayText;
		this.data = data;
		System.out.println("displayText Test:"+displayText);
		System.out.println("speech Test:"+speech);
		System.out.println("Data Test:"+data);
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


	public InnerData getData() {
		return data;
	}


	public void setData(InnerData data) {
		this.data = data;
	}
	
}
