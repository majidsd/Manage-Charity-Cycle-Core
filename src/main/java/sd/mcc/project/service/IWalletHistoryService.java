/**
 * 
 */
package sd.mcc.project.service;


import sd.mcc.project.dto.WalletHistoryDto;
import sd.mcc.project.model.WalletHistory;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;

/**
 * @author MaJiD
 *
 */
public interface IWalletHistoryService {
	
	public ObjectResponse<WalletHistory> addWalletHistory(WalletHistoryDto walletHistoryDto);
	
	public ListResponse<WalletHistory> getOldCredit(WalletHistoryDto walletHistoryDto);
	
	public ListResponse<WalletHistory> getOldCreditPerPeriod(WalletHistoryDto walletHistoryDto);
	
	public ListResponse<WalletHistory> getOldDebit(WalletHistoryDto walletHistoryDto);
	
	public ListResponse<WalletHistory> getOldDebitPerPeriod(WalletHistoryDto walletHistoryDto);
	
	public ListResponse<WalletHistoryDto> getOldTransactions(WalletHistoryDto walletHistoryDto);
	
	public ListResponse<WalletHistory> getOldTransactionsPerPeriod(WalletHistoryDto walletHistoryDto);
	
}
