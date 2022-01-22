/**
 * 
 */
package sd.mcc.project.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sd.mcc.project.model.Case;
import sd.mcc.project.model.User;

/**
 * @author ahmedozy
 *
 */
public interface CaseRepository extends JpaRepository<Case, Integer> {
	public List<Case> findAllByAgent(User agent);
}
