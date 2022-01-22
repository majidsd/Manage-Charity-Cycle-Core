/**
 * 
 */
package sd.mcc.project.serviceImp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sd.mcc.project.dto.UserDto;
import sd.mcc.project.model.Authority;
import sd.mcc.project.model.User;
import sd.mcc.project.repo.AuthorityRepository;
import sd.mcc.project.repo.UserRepository;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;
import sd.mcc.project.service.IUserService;
import sd.mcc.project.util.ResponseEnum;

/**
 * @author ahmedozy
 *
 */
@Service
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityRepository authRepo;
	
	@Autowired
	private ModelMapper mm;
	
	@Override
	public ObjectResponse<UserDto> addUser(UserDto userDto) {
		User user = mm.map(userDto, User.class);
		Collection<Authority> userAuths = user.getAuths();
		userAuths.forEach(a -> a = authRepo.save(a));
		user.setAuthorities(userAuths);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepo.save(user);
		userDto = mm.map(user, UserDto.class);
		return new ObjectResponse<UserDto>(100, "Success", userDto);
	}

	@Override
	public ObjectResponse<UserDto> updateUser(UserDto userDto) {
		ObjectResponse<UserDto> response = new ObjectResponse<UserDto>();
		User user = userRepo.getOne(userDto.getId());
		if (user == null) {
			response.setResponseCode(400);
			response.setResponseMessage("User not Exist");
		} else {
			User updatedUser = mm.map(userDto, User.class);
			user.setAuthorities(updatedUser.getAuths());
			user.setFullName(updatedUser.getFullName());
			response.setResponseCode(100);
			response.setResponseMessage("Success");
		}
		return response;
	}

	@Override
	public ObjectResponse<UserDto> deleteUser(UserDto userDto) {
		userRepo.delete(mm.map(userDto, User.class));
		ObjectResponse<UserDto> response = new ObjectResponse<UserDto>();
		response.setResponseCode(100);
		response.setResponseMessage("Success");
		return response;
	}

	@Override
	public ObjectResponse<UserDto> getUser(Integer userId) {
		User user = userRepo.getOne(userId);
		UserDto userDto = mm.map(user, UserDto.class);
		return new ObjectResponse<UserDto>(100, "Success", userDto);
	}

	@Override
	public ListResponse<UserDto> getAllUsers() {
		List<UserDto> userDtos = new ArrayList<UserDto>();
		List<User> users = userRepo.findAll();
		users.forEach(user -> userDtos.add(mm.map(user, UserDto.class)));
		ListResponse<UserDto> response = new ListResponse<UserDto>();
		response.setDtos(userDtos);
		response.setResponseCode(100);
		response.setResponseMessage("Success");
		return response;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
	}

	@Override
	public ObjectResponse<UserDto> getUser(String username) {
		ObjectResponse<UserDto> response;
		User user = userRepo.findByUsername(username);
		if(user == null)
			return new ObjectResponse<>(ResponseEnum.USER_NOT_EXIST);
		UserDto userDto = mm.map(user,UserDto.class);
		response = new ObjectResponse<UserDto>(ResponseEnum.SUCCESS, userDto);
		return response;
	}

}
