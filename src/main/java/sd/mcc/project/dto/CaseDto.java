/**
 * 
 */
package sd.mcc.project.dto;

import java.util.Date;

import javax.validation.constraints.Min;

import sd.mcc.project.util.CaseStatus;

/**
 * @author ahmedozy
 *
 */
public class CaseDto {

	private Integer id;
	private String title;
	private String description;
	private String imgUrl;
	private Double targetDonations;
	private Double currentDonations;
	@Min(message = "{caseDto.donorsCount}", value = 0)
	private Integer donorsCount;
	private Integer shareCount;
	private String date;
	private Integer agentId;
	private CaseStatus status;
	private Date createdAt;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
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
		return "CaseDto [id=" + id + ", title=" + title + ", description=" + description + ", imgUrl=" + imgUrl
				+ ", targetDonations=" + targetDonations + ", currentDonations=" + currentDonations + ", donorsCount="
				+ donorsCount + ", shareCount=" + shareCount + ", date=" + date + ", agentId=" + agentId + ", status="
				+ status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
	
	
}
