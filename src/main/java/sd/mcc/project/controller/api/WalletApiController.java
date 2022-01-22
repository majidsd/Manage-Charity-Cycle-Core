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
import sd.mcc.project.dto.PendingWalletCreditDto;
import sd.mcc.project.dto.UserDto;
import sd.mcc.project.dto.WalletDto;
import sd.mcc.project.dto.WalletHistoryDto;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;
import sd.mcc.project.service.IPendingWalletCreditService;
import sd.mcc.project.service.IUserService;
import sd.mcc.project.service.IWalletHistoryService;
import sd.mcc.project.service.IWalletService;
import sd.mcc.project.util.ResponseEnum;

/**
 * @author MaJiD
 *
 */
@RestController
@RequestMapping("/api/wallet")
public class WalletApiController {
	@Autowired
	IWalletService walletService;
	
	@Autowired
	private IPendingWalletCreditService walletCreditService;
	
	@Autowired
	private IWalletHistoryService walletHistoryService;
	
	@Autowired
	private IUserService userService;

	@GetMapping("/getBalance/{walletId}")
	public ObjectResponse<Double> getBalance(@PathVariable Integer walletId){
		WalletDto walletDto = new WalletDto();
		walletDto.setId(walletId);
		ObjectResponse<Double> response = walletService.getBalance(walletDto); 
		return response;
	}
	
	@PostMapping("/requestCredit")
	public ObjectResponse<PendingWalletCreditDto> creditWallet(Authentication authentication, @RequestBody PendingWalletCreditDto pendingWalletCreditDto){
		Claims claims = (Claims) authentication.getPrincipal();
		ObjectResponse<?> userResponse = userService.getUser(claims.getSubject());
		if(userResponse.getResponseCode() != ResponseEnum.SUCCESS.getResponseCode()) {
			return (ObjectResponse<PendingWalletCreditDto>) userResponse;
		} else {
			Integer userId = ((UserDto) userResponse.getDto()).getId();
			Integer walletId = ((UserDto) userResponse.getDto()).getWalletId();
			pendingWalletCreditDto.setCreated_by_id(userId);
			pendingWalletCreditDto.setWallet_id(walletId);
		}
		ObjectResponse<PendingWalletCreditDto> response = walletCreditService.addPendingWalletCredit(pendingWalletCreditDto);
		return response;
	}
	
	//accounts admin
	@PostMapping("/approveCredit")
	public ObjectResponse<PendingWalletCreditDto> approveCredit(Authentication authentication, @RequestBody PendingWalletCreditDto pendingWalletCreditDto){
		Claims claims = (Claims) authentication.getPrincipal();
		ObjectResponse<?> userResponse = userService.getUser(claims.getSubject());
		if(userResponse.getResponseCode() != ResponseEnum.SUCCESS.getResponseCode()) {
			return (ObjectResponse<PendingWalletCreditDto>) userResponse;
		} else {
			Integer userId = ((UserDto) userResponse.getDto()).getId();
			pendingWalletCreditDto.setCreated_by_id(userId);
		}
		ObjectResponse<PendingWalletCreditDto> response = walletCreditService.approvePendingWalletCredit(pendingWalletCreditDto);
		return response;
	}
	
	//accounts admin
	@PostMapping("/rejectCredit")
	public ObjectResponse<PendingWalletCreditDto> rejectCredit(Authentication authentication, @RequestBody PendingWalletCreditDto pendingWalletCreditDto){
		Claims claims = (Claims) authentication.getPrincipal();
		ObjectResponse<?> userResponse = userService.getUser(claims.getSubject());
		if(userResponse.getResponseCode() != ResponseEnum.SUCCESS.getResponseCode()) {
			return (ObjectResponse<PendingWalletCreditDto>) userResponse;
		} else {
			Integer userId = ((UserDto) userResponse.getDto()).getId();
			pendingWalletCreditDto.setCreated_by_id(userId);
		}
		ObjectResponse<PendingWalletCreditDto> response = walletCreditService.rejectPendingWalletCredit(pendingWalletCreditDto);
		return response;
	}
	
	@GetMapping("/getTransactions")
	public ListResponse<WalletHistoryDto> getTransactions(Authentication authentication){
		WalletHistoryDto whd = new WalletHistoryDto();
		Claims claims = (Claims) authentication.getPrincipal();
		ObjectResponse<UserDto> userResponse = userService.getUser(claims.getSubject());
		if(userResponse.getResponseCode() != ResponseEnum.SUCCESS.getResponseCode()) {
			return new ListResponse<WalletHistoryDto>(userResponse.getResponseCode(),userResponse.getResponseMessage(), null) ;
		} else {
			Integer walletId =  userResponse.getDto().getWalletId();
			whd.setWallet_id(walletId);
		}
		ListResponse<WalletHistoryDto> response = walletHistoryService.getOldTransactions(whd);
		return response;
	}
}
