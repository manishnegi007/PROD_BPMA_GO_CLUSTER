package hello;
public class InnerButton 
{
	String text;
	String postback;
	
	public InnerButton() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InnerButton(String text, String postback) {
		super();
		this.text = text;
		this.postback = postback;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPostback() {
		return postback;
	}

	public void setPostback(String postback) {
		this.postback = postback;
	}

}
