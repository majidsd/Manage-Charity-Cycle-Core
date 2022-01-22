/**
 * 
 */
package sd.mcc.project.service;

import sd.mcc.project.dto.CaseDto;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;

/**
 * @author ahmedozy
 *
 */
public interface ICaseService {

	public ListResponse<CaseDto> getCases();
	public ListResponse<CaseDto> getAgentCases(Integer agentId);
	public ObjectResponse<CaseDto> getCaseById(Integer caseId);
	public ObjectResponse<CaseDto> createCase(CaseDto caseDto);
	public ObjectResponse<CaseDto> cancelCase(CaseDto caseDto);
	public ObjectResponse<CaseDto> updatePaidAmount(CaseDto caseDto);
	public ObjectResponse<CaseDto> completeCase(CaseDto caseDto);
}
