/**
 * 
 */
package sd.mcc.project.serviceImp;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sd.mcc.project.dto.OtpDto;
import sd.mcc.project.dto.UserProfileDto;
import sd.mcc.project.model.Otp;
import sd.mcc.project.model.User;
import sd.mcc.project.model.Wallet;
import sd.mcc.project.repo.AuthorityRepository;
import sd.mcc.project.repo.OtpRepository;
import sd.mcc.project.repo.UserRepository;
import sd.mcc.project.repo.WalletRepository;
import sd.mcc.project.response.ObjectResponse;
import sd.mcc.project.security.SecurityConstants;
import sd.mcc.project.service.IUserAuthentication;
import sd.mcc.project.util.ResponseEnum;
import sd.mcc.project.util.UserStatus;

/**
 * @author ahmedozy
 *
 */
@Service
public class UserAuthentication implements IUserAuthentication {

	@Autowired
	private OtpRepository otpRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthorityRepository authRepo;
	
	@Autowired
	private WalletRepository walletRepo;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Random random;
	

	// generate otp
	// send the otp via sms (in the first version of the app just return it to the sender)
	@Override
	public ObjectResponse<OtpDto> register(OtpDto otpDto) {
		//mapper.addConverter(stringToInteger());
		Otp otp = mapper.map(otpDto, Otp.class);
		System.out.println("after mapping "+ otp);
		Otp newOtp = otpRepo.findByPhoneNumber(otp.getPhoneNumber());
		if (newOtp == null) {
			otp.setOtp(generateOtp());
			otpRepo.save(otp);
			otpDto.setOtp(otp.getOtp()+"");
		}else {
			otpDto.setOtp(newOtp.getOtp()+"");
		}
		ObjectResponse<OtpDto> response = new ObjectResponse<OtpDto>(ResponseEnum.SUCCESS, otpDto);
		return response;
	}


	//if there is no user with phone create a new user
	//after verify delete otp record
	@Override
	public ObjectResponse<UserProfileDto> verifyAndLogin(OtpDto otpDto) {
		ObjectResponse<UserProfileDto> response;
		Otp otp = otpRepo.findByOtp(Integer.valueOf(otpDto.getOtp()));
		if(null != otp && otpDto.getPhoneNumber().equals(otp.getPhoneNumber().toString())) {
			User user = userRepo.findByPhoneNumber(otp.getPhoneNumber());
			user = createUserIfNotExist(otp, user);
			UserProfileDto userProfileDto = mapper.map(user, UserProfileDto.class);
			userProfileDto.setId(user.getId());
			userProfileDto.setStatus(user.getStatus());
			response = new ObjectResponse<UserProfileDto>(ResponseEnum.SUCCESS, userProfileDto);
			otpRepo.delete(otp);
		} else {
			response = new ObjectResponse<>(ResponseEnum.ITEM_NOT_FOUND);
		}
		return response;
	}


	private User createUserIfNotExist(Otp otp, User user) {
		Wallet wallet;
		if(user == null) {
			user = new User();
			user.setUsername(otp.getPhoneNumber()+"");
			user.setPhoneNumber(otp.getPhoneNumber());
			user.setPassword(encoder.encode(SecurityConstants.FIXED_PASSWORD));
			user.setStatus(UserStatus.ACTIVE);
			if(otp.isAgent()) {
				user.setAuthorities(Arrays.asList(authRepo.findByAuthorityName("AGENT")));
			} else {
				user.setAuthorities(Arrays.asList(authRepo.findByAuthorityName("USER")));
			}
			wallet = new Wallet();
			wallet.setCreated_at(new Date());
			wallet.setCurrent_balance(0.0);
			wallet.setLast_update(new Date());
			Wallet savedWallet = walletRepo.save(wallet);
			user.setWallet(savedWallet);
			user = userRepo.save(user);
		}
		return user;
	}
	
	private Integer generateOtp() {
		return random.nextInt(900000)+100000; 
	}

}
