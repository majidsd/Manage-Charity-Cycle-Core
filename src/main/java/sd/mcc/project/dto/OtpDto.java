/**
 * 
 */
package sd.mcc.project.dto;

/**
 * @author ahmedozy
 *
 */
public class OtpDto {

	private Integer id;
	private String phoneNumber;
	private String otp;
	private boolean agent;
	
	
	public boolean isAgent() {
		return agent;
	}
	public void setAgent(boolean agent) {
		this.agent = agent;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "OtpDto [id=" + id + ", phoneNumber=" + phoneNumber + ", otp=" + otp + ", agent=" + agent + "]";
	}
	
	
}
