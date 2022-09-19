package spring.crud.constant;

public class AlertMessage {
	
	private String content;
	private String type;
	
	
	public AlertMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AlertMessage(String content,String type)
	{
		this.content = content;
		this.type = type;
		
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
