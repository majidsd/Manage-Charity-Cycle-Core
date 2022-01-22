/**
 * 
 */
package sd.mcc.project.service;

import sd.mcc.project.dto.UserDto;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;

/**
 * @author ahmedozy
 *
 */
public interface IUserService {
	public ObjectResponse<UserDto> addUser(UserDto userDto);
	public ObjectResponse<UserDto> updateUser(UserDto userDto);
	public ObjectResponse<UserDto> deleteUser(UserDto userDto);
	public ObjectResponse<UserDto> getUser(Integer userId);
	public ListResponse<UserDto> getAllUsers();
	public ObjectResponse<UserDto> getUser(String username);

}
