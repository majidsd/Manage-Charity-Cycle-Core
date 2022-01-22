/**
 * 
 */
package sd.mcc.project.service;

import sd.mcc.project.dto.WalletDto;
import sd.mcc.project.model.Wallet;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;

/**
 * @author MaJiD
 *
 */
public interface IWalletService {
	
	public ObjectResponse<Wallet> addWallet(WalletDto walletDto);
	
	public ObjectResponse<Wallet> getWallet(Integer id);
	
	public ObjectResponse<Double> getBalance(WalletDto walletDto);
	
	public ObjectResponse<WalletDto> credit(WalletDto walletDto);
	
	public ObjectResponse<Wallet> debit(WalletDto walletDto);
	
	public ListResponse<Wallet> getAllWallets();

}
