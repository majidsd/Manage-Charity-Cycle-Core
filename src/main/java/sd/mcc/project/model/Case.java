/**
 * 
 */
package sd.mcc.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import sd.mcc.project.util.CaseStatus;

/**
 * @author ahmedozy
 *
 */
@Entity(name = "case_table")
public class Case {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String imgUrl;
	@Column
	private Double targetDonations;
	@Column
	private Double currentDonations;
	@Column
	private Integer donorsCount;
	@Column
	private Integer shareCount;
	@ManyToOne
	private User agent;
	@Column
	private CaseStatus status;
	@Column
	private Date createdAt;
	@Column
	private Date updatedAt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Double getTargetDonations() {
		return targetDonations;
	}
	public void setTargetDonations(Double targetDonations) {
		this.targetDonations = targetDonations;
	}
	public Double getCurrentDonations() {
		return currentDonations;
	}
	public void setCurrentDonations(Double currentDonations) {
		this.currentDonations = currentDonations;
	}
	public Integer getDonorsCount() {
		return donorsCount;
	}
	public void setDonorsCount(Integer donorsCount) {
		this.donorsCount = donorsCount;
	}
	public Integer getShareCount() {
		return shareCount;
	}
	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}
	public User getAgent() {
		return agent;
	}
	public void setAgent(User agent) {
		this.agent = agent;
	}
	public CaseStatus getStatus() {
		return status;
	}
	public void setStatus(CaseStatus status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "Case [id=" + id + ", title=" + title + ", description=" + description + ", imgUrl=" + imgUrl
				+ ", targetDonations=" + targetDonations + ", currentDonations=" + currentDonations + ", donorsCount="
				+ donorsCount + ", shareCount=" + shareCount + ", agent=" + agent + ", status=" + status
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}


	
}
