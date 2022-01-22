/**
 * 
 */
package sd.mcc.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sd.mcc.project.model.Otp;

/**
 * @author ahmedozy
 *
 */
public interface OtpRepository extends JpaRepository<Otp, Integer> {

	boolean existsByPhoneNumber(Integer phoneNumber);
	Otp findByOtp(Integer otp);
	Otp findByPhoneNumber(Integer phoneNumber);

}
