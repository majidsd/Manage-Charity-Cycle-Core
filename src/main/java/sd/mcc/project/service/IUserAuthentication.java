/**
 * 
 */
package sd.mcc.project.service;

import sd.mcc.project.dto.OtpDto;
import sd.mcc.project.dto.UserProfileDto;
import sd.mcc.project.response.ObjectResponse;

/**
 * @author ahmedozy
 *
 */
public interface IUserAuthentication {

	public ObjectResponse<OtpDto> register(OtpDto otpDto);
	public ObjectResponse<UserProfileDto> verifyAndLogin(OtpDto otpDto);
}
