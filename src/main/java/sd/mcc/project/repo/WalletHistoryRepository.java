/**
 * 
 */
package sd.mcc.project.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sd.mcc.project.model.Wallet;
import sd.mcc.project.model.WalletHistory;

/**
 * @author MaJiD
 *
 */
public interface WalletHistoryRepository extends JpaRepository<WalletHistory, Integer> {
	
	@Query("FROM WalletHistory where wallet = ?1 and operation = ?2")
	public List<WalletHistory> getWalletOperationsHistory(Wallet wallet, Byte operation);
	
	@Query("FROM WalletHistory where wallet = ?1 and operation = ?2 and created_at between ?3 and ?4")
	public List<WalletHistory> getWalletOperationsHistoryPerPeriod(Wallet wallet, Byte operation, Date fromDate, Date toDate);

	@Query("FROM WalletHistory where wallet = ?1")
	public List<WalletHistory> getWalletTransactions(Wallet wallet);
	
	@Query("FROM WalletHistory where wallet = ?1 and operation = ?2 and created_at between ?3 and ?4")
	public List<WalletHistory> getWalletTransactionsPerPeriod(Wallet wallet, Date fromDate, Date toDate);

}
