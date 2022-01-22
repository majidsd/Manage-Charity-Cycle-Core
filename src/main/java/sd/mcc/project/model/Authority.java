/**
 * 
 */
package sd.mcc.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author ahmedozy
 *
@ */
@Entity(name = "authority")
public class Authority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4172587922218857845L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "authority_name")
	private String authorityName;
	
	@Column(name = "authority_description")
	private String authorityDescription;
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getAuthorityName() {
		return authorityName;
	}



	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}



	public String getAuthorityDescription() {
		return authorityDescription;
	}



	public void setAuthorityDescription(String authorityDescription) {
		this.authorityDescription = authorityDescription;
	}



	@Override
	public String getAuthority() {
		return authorityName;
	}



	@Override
	public String toString() {
		return "Authority [id=" + id + ", authorityName=" + authorityName + ", authorityDescription="
				+ authorityDescription + "]";
	}

	
}
