/**
 * 
 */
package sd.mcc.project.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import sd.mcc.project.dto.DonationDto;
import sd.mcc.project.dto.PendingWalletCreditDto;
import sd.mcc.project.dto.UserDto;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;
import sd.mcc.project.service.IDonationService;
import sd.mcc.project.service.IUserService;
import sd.mcc.project.util.ResponseEnum;

/**
 * @author MaJiD
 *
 */
@RestController
@RequestMapping("/api/donation")
public class DonationApiController {

	@Autowired
	IDonationService donationService;
	
	@Autowired
	IUserService userService;
	
	@PostMapping("/donate")
	public ObjectResponse<DonationDto> donate(Authentication authentication, @RequestBody DonationDto donation){
		Claims claims = (Claims) authentication.getPrincipal();
		ObjectResponse<?> userResponse = userService.getUser(claims.getSubject());
		if(userResponse.getResponseCode() != ResponseEnum.SUCCESS.getResponseCode()) {
			return (ObjectResponse<DonationDto>) userResponse;
		} else {
			Integer userId = ((UserDto) userResponse.getDto()).getId();
			donation.setCreated_by_id(userId);
			donation.setDonator_id(userId);
		}
		ObjectResponse<DonationDto> response = donationService.donate(donation);
		return response;
	}
	
	@GetMapping("/all")
	public ListResponse<DonationDto> getDonations(Authentication authentication){
		DonationDto donationDto = new DonationDto();
		Claims claims = (Claims) authentication.getPrincipal();
		ObjectResponse<?> userResponse = userService.getUser(claims.getSubject());
		Integer userId;
		if(userResponse.getResponseCode() != ResponseEnum.SUCCESS.getResponseCode()) {
			return new ListResponse<>(userResponse.getResponseCode(), userResponse.getResponseMessage(), null);
		} else {
			userId = ((UserDto) userResponse.getDto()).getId();
		}
		donationDto.setDonator_id(userId);
		ListResponse<DonationDto> response = donationService.getUserDonations(donationDto);
		return response;
	}
}
