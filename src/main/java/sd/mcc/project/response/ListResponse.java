/**
 * 
 */
package sd.mcc.project.response;

import java.util.List;

import sd.mcc.project.util.ResponseEnum;

/**
 * @author ahmedozy
 *
 */

public class ListResponse<T> extends BaseResponse {

	private List<T> dtos;
	
	public ListResponse() {
		super();
	}
	
	public ListResponse(Integer responseCode, String responseMessage, List<T> dtos) {
		super(responseCode, responseMessage);
		this.dtos = dtos;
	}

	public ListResponse(ResponseEnum responseEnum) {
		super(responseEnum);
	}

	public ListResponse(ResponseEnum responseEnum, List<T> dtos) {
		this(responseEnum);
		this.dtos = dtos;
	}

	public List<T> getDtos() {
		return dtos;
	}

	public void setDtos(List<T> dtos) {
		this.dtos = dtos;
	}

	@Override
	public String toString() {
		return "ListResponse [dtos=" + dtos + "]";
	}
	
	
}
