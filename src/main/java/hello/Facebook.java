package hello;
import java.util.List;

public class Facebook 
{
	private String type;
	private String platform;
	private String title;
	private String imageUrl;
	private List<InnerButton> buttons;
	
	public Facebook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Facebook(String type, String platform, String title, String imageUrl, List<InnerButton> buttons) {
		super();
		this.type = type;
		this.platform = platform;
		this.title = title;
		this.imageUrl = imageUrl;
		this.buttons = buttons;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<InnerButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<InnerButton> buttons) {
		this.buttons = buttons;
	}

}
