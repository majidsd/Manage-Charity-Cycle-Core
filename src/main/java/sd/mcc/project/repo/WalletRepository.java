/**
 * 
 */
package sd.mcc.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sd.mcc.project.model.Wallet;

/**
 * @author MaJiD
 *
 */
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

}
