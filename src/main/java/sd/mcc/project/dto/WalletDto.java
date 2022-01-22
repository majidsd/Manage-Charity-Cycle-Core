/**
 * 
 */
package sd.mcc.project.dto;

/**
 * @author MaJiD
 *
 */
public class WalletDto {
	
	private Integer id;
	private Integer owner_id;
	private Integer created_by_id;
	private Double amount;
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
	 * @return the owner_id
	 */
	public Integer getOwner_id() {
		return owner_id;
	}
	/**
	 * @param owner_id the owner_id to set
	 */
	public void setOwner_id(Integer owner_id) {
		this.owner_id = owner_id;
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

}
