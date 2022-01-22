/**
 * 
 */
package sd.mcc.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sd.mcc.project.model.User;

/**
 * @author ahmedozy
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	
	User findByPhoneNumber(Integer phoneNumber);

}
