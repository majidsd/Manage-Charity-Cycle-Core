/**
 * 
 */
package sd.mcc.project.service;

import sd.mcc.project.dto.PendingWalletCreditDto;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;

/**
 * @author MaJiD
 *
 */
public interface IPendingWalletCreditService {
	
	public ObjectResponse<PendingWalletCreditDto> addPendingWalletCredit(PendingWalletCreditDto pendingWalletCreditDto);
	
	public ObjectResponse<PendingWalletCreditDto> approvePendingWalletCredit(PendingWalletCreditDto pendingWalletCreditDto);
	
	public ObjectResponse<PendingWalletCreditDto> rejectPendingWalletCredit(PendingWalletCreditDto pendingWalletCreditDto);
	
	public ObjectResponse<PendingWalletCreditDto> getPendingWalletCredit(PendingWalletCreditDto pendingWalletCreditDto);
	
	public ListResponse<PendingWalletCreditDto> getAllPendingWalletCredits();
	
	public ListResponse<PendingWalletCreditDto> getUserPendingWalletCredits(PendingWalletCreditDto pendingWalletCreditDto);

}
