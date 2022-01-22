/**
 * 
 */
package sd.mcc.project.util;

/**
 * @author MaJiD
 *
 */
public enum WalletOperations {
	
	DEBIT("debit", (byte) 0),
	CREDIT("credit", (byte) 1);
	
	private final String name;
    private final Byte value;
	
	private WalletOperations(String name, byte value) {
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
	 * @return the value
	 */
	public Byte getValue() {
		return value;
	}
}
