/**
 * 
 */
package sd.mcc.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sd.mcc.project.model.Authority;

/**
 * @author ahmedozy
 *
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

	Authority findByAuthorityName(String authorityName);

}
