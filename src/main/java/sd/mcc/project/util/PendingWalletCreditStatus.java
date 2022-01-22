/**
 * 
 */
package sd.mcc.project.util;

/**
 * @author MaJiD
 *
 */
public enum PendingWalletCreditStatus {
	NEW("new", (byte) 0),
	DONE("done", (byte) 1),
	REJECTED("rejected", (byte) -1);
	
	private String name;
	private byte value;
	
	private PendingWalletCreditStatus(String name, byte value) {
		this.name = name;
		this.value = value;
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
	 * @return the value
	 */
	public byte getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(byte value) {
		this.value = value;
	}
}
