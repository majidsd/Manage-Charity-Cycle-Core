/**
 * 
 */
package sd.mcc.project.dto;

import java.util.Collection;
import java.util.Date;

import sd.mcc.project.model.Authority;
import sd.mcc.project.util.UserStatus;

/**
 * @author ahmedozy
 *
 */
public class UserDto {

	private Integer id;
	private String fullName;
	private Integer phoneNumber;
	private Integer walletId;
	private String username;
	private String password;
	private String password2;
	private boolean enabled;
	private UserStatus status;
	private Date created_at;
	private Date updated_at;
	private Collection<Authority> authorities;
	
	public Integer getWalletId() {
		return walletId;
	}
	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Collection<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
		this.enabled = true;
		this.status = UserStatus.ACTIVE;
		this.created_at = new Date();
		this.updated_at = new Date();
	}
	
	
	public UserDto(String fullName, String username, String password, String password2,
			Collection<Authority> authorities) {
		this();
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.password2 = password2;
		this.authorities = authorities;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", walletId="
				+ walletId + ", username=" + username + ", enabled=" + enabled + ", status=" + status + ", created_at="
				+ created_at + ", updated_at=" + updated_at + ", authorities=" + authorities + "]";
	}
	
	
}
