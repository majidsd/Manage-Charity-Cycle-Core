/**
 * 
 */
package sd.mcc.project.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sd.mcc.project.model.PendingWalletCredit;
import sd.mcc.project.model.Wallet;

/**
 * @author MaJiD
 *
 */
public interface PendingWalletCreditRepository extends JpaRepository<PendingWalletCredit, Integer> {
	
	@Query("FROM PendingWalletCredit where wallet = ?1 and status = ?2")
	public List<PendingWalletCredit> getWalletPendingCredit(Wallet wallet, Byte status);
	
	@Query("FROM PendingWalletCredit where status = ?1")
	public List<PendingWalletCredit> getWalletsPendingCredit(Byte status);
	
}
