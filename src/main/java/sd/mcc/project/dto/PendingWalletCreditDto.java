/**
 * 
 */
package sd.mcc.project.dto;

/**
 * @author MaJiD
 *
 */
public class PendingWalletCreditDto {
	private Integer id;
	private Integer wallet_id;
	private Double amount;
	private Byte   status;
	private String bank;
	private String branch;
	private String receipt_no;
	private String receipt_imgUrl;
	private String description;
	private Integer created_by_id;
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
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}
	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	/**
	 * @return the receipt_no
	 */
	public String getReceipt_no() {
		return receipt_no;
	}
	/**
	 * @param receipt_no the receipt_no to set
	 */
	public void setReceipt_no(String receipt_no) {
		this.receipt_no = receipt_no;
	}
	/**
	 * @return the receipt_imgUrl
	 */
	public String getReceipt_imgUrl() {
		return receipt_imgUrl;
	}
	/**
	 * @param receipt_imgUrl the receipt_imgUrl to set
	 */
	public void setReceipt_imgUrl(String receipt_imgUrl) {
		this.receipt_imgUrl = receipt_imgUrl;
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
		return "PendingWalletCreditDto [id=" + id + ", wallet_id=" + wallet_id + ", amount=" + amount + ", status="
				+ status + ", bank=" + bank + ", branch=" + branch + ", receipt_no=" + receipt_no + ", receipt_imgUrl="
				+ receipt_imgUrl + ", description=" + description + ", created_by_id=" + created_by_id + "]";
	}
}
