/**
 * 
 */
package sd.mcc.project.util;

/**
 * @author ahmedozy
 *
 */
public enum ResponseEnum {

	SUCCESS(201,"Successful"),
	INSUFFICIENT(203, "Insufficient amount"),
	ITEM_NOT_FOUND(404, "Item not found"),
	USER_NOT_EXIST(404, "User not exist"),
	DUPLICATED_ITEM(440, "Duplicated item"),
	TRY_AGAIN(474, "Try again"),
	NEGATIVE_AMOUNT(484,"Negative amount");
	
	private Integer responseCode;
	private String responseMessage;
	
	private ResponseEnum(Integer responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
	
}
