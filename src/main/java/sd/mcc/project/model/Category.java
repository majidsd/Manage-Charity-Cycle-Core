/**
 * 
 */
package sd.mcc.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author MaJiD
 *
 */
@Entity
@Table(name = "category")
public class Category {
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
		this.created_at = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@JoinColumn(name="name", nullable = false, unique= true)
	private String name;
	
	@JoinColumn(name="name_ar", nullable = false, unique= true)
	private String name_ar;
	
	@JoinColumn(name="type", nullable = false)
	private String type;
	
	@JoinColumn(name="type_ar", nullable = false)
	private String type_ar;
	
	@JoinColumn(name="status", nullable = false)
	private Byte status;
	
	@JoinColumn(name="max_budget", nullable = false)
	private Double max_budget;
	
	@JoinColumn(name="description", nullable = true)
	private String description;
	
	@JoinColumn(name="created_at", nullable = false)
	private Date created_at;
	
	@JoinColumn(name="last_update", nullable = false)
	private Date last_update;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="created_by", nullable = true)
	private User created_by;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="last_update_by", nullable = true)
	private User last_update_by;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name_ar
	 */
	public String getName_ar() {
		return name_ar;
	}

	/**
	 * @param name_ar the name_ar to set
	 */
	public void setName_ar(String name_ar) {
		this.name_ar = name_ar;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the type_ar
	 */
	public String getType_ar() {
		return type_ar;
	}

	/**
	 * @param type_ar the type_ar to set
	 */
	public void setType_ar(String type_ar) {
		this.type_ar = type_ar;
	}

	/**
	 * @return the status
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * @return the max_budget
	 */
	public Double getMax_budget() {
		return max_budget;
	}

	/**
	 * @param max_budget the max_budget to set
	 */
	public void setMax_budget(Double max_budget) {
		this.max_budget = max_budget;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the created_at
	 */
	public Date getCreated_at() {
		return created_at;
	}

	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	/**
	 * @return the last_update
	 */
	public Date getLast_update() {
		return last_update;
	}

	/**
	 * @param last_update the last_update to set
	 */
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", name_ar=" + name_ar + ", type=" + type + ", type_ar="
				+ type_ar + ", status=" + status + ", max_budget=" + max_budget + ", description=" + description
				+ ", created_at=" + created_at + ", last_update=" + last_update + ", created_by=" + created_by
				+ ", last_update_by=" + last_update_by + "]";
	}
}
