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
@Table(name="Wallet_history")
public class WalletHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@JoinColumn(name="amount", nullable = false)
	private Double amount;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="wallet", nullable = true)
	private Wallet wallet;
	
	@JoinColumn(name="description", nullable = true)
	private String description;
	
	@JoinColumn(name="operation", nullable = true)
	private Byte operation;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="created_by", nullable = true)
	private User created_by;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="last_update_by", nullable = true)
	private User last_update_by;
	
	@JoinColumn(name="created_at", nullable = false)
	private Date created_at;
	
	@JoinColumn(name="last_update", nullable = false)
	private Date last_update;

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
	 * @return the wallet
	 */
	public Wallet getWallet() {
		return wallet;
	}

	/**
	 * @param wallet the wallet to set
	 */
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
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
	 * @return the created_by
	 */
	public User getCreated_by() {
		return created_by;
	}

	/**
	 * @param created_by the created_by to set
	 */
	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}

	/**
	 * @return the last_update_by
	 */
	public User getLast_update_by() {
		return last_update_by;
	}

	/**
	 * @param last_update_by the last_update_by to set
	 */
	public void setLast_update_by(User last_update_by) {
		this.last_update_by = last_update_by;
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
		return "WalletHistory [id=" + id + ", amount=" + amount + ", wallet=" + wallet + ", description=" + description
				+ ", operation=" + operation + ", created_by=" + created_by + ", last_update_by=" + last_update_by
				+ ", created_at=" + created_at + ", last_update=" + last_update + "]";
	}

}
