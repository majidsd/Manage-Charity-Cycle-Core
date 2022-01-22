/**
 * 
 */
package sd.mcc.project.dto;

import java.util.Date;

/**
 * @author MaJiD
 *
 */
public class WalletHistoryDto {
	
	private Integer id;
	private Integer wallet_id;
	private Double amount;
	private Byte operation;
	private String descrtption;
	private Date from_date;
	private Date to_date;
	private Integer created_by_id;
	private Date created_at;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	/**
	 * @return the wallet_id
	 */
	public Integer getWallet_id() {
		return wallet_id;
	}
	/**
	 * @param wallet_id the wallet_id to set
	 */
	public void setWallet_id(Integer wallet_id) {
		this.wallet_id = wallet_id;
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
	 * @return the operation
	 */
	public Byte getOperation() {
		return operation;
	}
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(Byte operation) {
		this.operation = operation;
	}
	/**
	 * @return the descrtption
	 */
	public String getDescrtption() {
		return descrtption;
	}
	/**
	 * @param descrtption the descrtption to set
	 */
	public void setDescrtption(String descrtption) {
		this.descrtption = descrtption;
	}
	/**
	 * @return the from_date
	 */
	public Date getFrom_date() {
		return from_date;
	}
	/**
	 * @param from_date the from_date to set
	 */
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	/**
	 * @return the to_date
	 */
	public Date getTo_date() {
		return to_date;
	}
	/**
	 * @param to_date the to_date to set
	 */
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
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
	@Override
	public String toString() {
		return "WalletHistoryDto [id=" + id + ", wallet_id=" + wallet_id + ", amount=" + amount + ", operation="
				+ operation + ", descrtption=" + descrtption + ", from_date=" + from_date + ", to_date=" + to_date
				+ ", created_by_id=" + created_by_id + ", created_at=" + created_at + "]";
	}
	
}
