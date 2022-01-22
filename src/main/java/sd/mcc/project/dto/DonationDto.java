/**
 * 
 */
package sd.mcc.project.dto;

import java.util.Date;

/**
 * @author MaJiD
 *
 */
public class DonationDto {
	private Integer id;
	private Integer donator_id;
	private Integer case_id;
	private Double amount; 
	private Date start_date;
	private Date end_date;
	private Integer new_case_id;
	private String info;
	private Integer created_by_id;
	private Date donation_date;
	private boolean anonymous;
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
	 * @return the donator_id
	 */
	public Integer getDonator_id() {
		return donator_id;
	}
	/**
	 * @param donator_id the donator_id to set
	 */
	public void setDonator_id(Integer donator_id) {
		this.donator_id = donator_id;
	}
	/**
	 * @return the case_id
	 */
	public Integer getCase_id() {
		return case_id;
	}
	/**
	 * @param case_id the case_id to set
	 */
	public void setCase_id(Integer case_id) {
		this.case_id = case_id;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * @return the start_date
	 */
	public Date getStart_date() {
		return start_date;
	}
	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	/**
	 * @return the end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}
	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	/**
	 * @return the new_case_id
	 */
	public Integer getNew_case_id() {
		return new_case_id;
	}
	/**
	 * @param new_case_id the new_case_id to set
	 */
	public void setNew_case_id(Integer new_case_id) {
		this.new_case_id = new_case_id;
	}
	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * @return the created_by_id
	 */
	public Integer getCreated_by_id() {
		return created_by_id;
	}
	/**
	 * @param created_by_id the created_by_id to set
	 */
	public void setCreated_by_id(Integer created_by_id) {
		this.created_by_id = created_by_id;
	}
	
	public Date getDonation_date() {
		return donation_date;
	}
	public void setDonation_date(Date donation_date) {
		this.donation_date = donation_date;
	}
	
	public boolean isAnonymous() {
		return anonymous;
	}
	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}
	@Override
	public String toString() {
		return "DonationDto [id=" + id + ", donator_id=" + donator_id + ", case_id=" + case_id + ", amount=" + amount
				+ ", start_date=" + start_date + ", end_date=" + end_date + ", new_case_id=" + new_case_id + ", info="
				+ info + ", created_by_id=" + created_by_id + ", donation_date=" + donation_date + ", anonymous="
				+ anonymous + "]";
	}
}
