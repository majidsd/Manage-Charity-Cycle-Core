/**
 * 
 */
package sd.mcc.project.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sd.mcc.project.dto.CaseDto;
import sd.mcc.project.model.Case;
import sd.mcc.project.model.User;
import sd.mcc.project.repo.CaseRepository;
import sd.mcc.project.repo.UserRepository;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;
import sd.mcc.project.service.ICaseService;
import sd.mcc.project.util.CaseStatus;
import sd.mcc.project.util.ResponseEnum;

/**
 * @author ahmedozy
 *
 */
@Service
public class CaseServiceImp implements ICaseService {

	@Autowired
	private CaseRepository caseRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ListResponse<CaseDto> getCases() {
		List<CaseDto> cases = new ArrayList<CaseDto>();
		caseRepo.findAll().forEach(c -> cases.add(mapper.map(c, CaseDto.class)));
		return new ListResponse<CaseDto>(ResponseEnum.SUCCESS, cases);
	}

	@Override
	public ListResponse<CaseDto> getAgentCases(Integer agentId) {
		List<CaseDto> cases = new ArrayList<CaseDto>();
		User agent = new User();
		agent.setId(agentId);
		caseRepo.findAllByAgent(agent).forEach(c -> cases.add(mapper.map(c, CaseDto.class)));
		return new ListResponse<CaseDto>(ResponseEnum.SUCCESS, cases);
	}

	@Override
	public ObjectResponse<CaseDto> createCase(CaseDto caseDto) {
		ObjectResponse<CaseDto> response;
		Case addedCase = null;
		try {
			addedCase = mapper.map(caseDto, Case.class);
			User agent = userRepo.getOne(caseDto.getAgentId());
			addedCase.setAgent(agent);
			addedCase = caseRepo.save(addedCase);
			caseDto = mapper.map(addedCase, CaseDto.class);
			response = new ObjectResponse<>(ResponseEnum.SUCCESS);
			response = new ObjectResponse<CaseDto>(ResponseEnum.SUCCESS, caseDto);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ObjectResponse<CaseDto>(ResponseEnum.DUPLICATED_ITEM, caseDto);
		}
		
		return response;
	}

	@Override
	public ObjectResponse<CaseDto> cancelCase(CaseDto caseDto) {
		ObjectResponse<CaseDto> response;
		Case caseToCancel = caseRepo.getOne(caseDto.getId());
		if(caseToCancel == null) {
			response = new ObjectResponse<CaseDto>(ResponseEnum.ITEM_NOT_FOUND, caseDto);
		} else {
			caseToCancel.setStatus(CaseStatus.CANCELED);
			caseRepo.save(caseToCancel);
			caseDto.setStatus(CaseStatus.CANCELED);
			response = new ObjectResponse<CaseDto>(ResponseEnum.SUCCESS, caseDto);
		}
		return response;
	}

	@Override
	public ObjectResponse<CaseDto> updatePaidAmount(CaseDto caseDto) {
		ObjectResponse<CaseDto> response;
		Case caseToUpdate = caseRepo.getOne(caseDto.getId());
		if(caseToUpdate == null) {
			response = new ObjectResponse<CaseDto>(ResponseEnum.ITEM_NOT_FOUND, caseDto);
		} else {
			caseToUpdate.setCurrentDonations(caseToUpdate.getCurrentDonations() + caseDto.getCurrentDonations());
			caseDto.setCurrentDonations(caseToUpdate.getCurrentDonations());
			response = new ObjectResponse<CaseDto>(ResponseEnum.SUCCESS, caseDto);
		}
		return response;
	}

	@Override
	public ObjectResponse<CaseDto> completeCase(CaseDto caseDto) {
		ObjectResponse<CaseDto> response;
		Case caseToComplete = caseRepo.getOne(caseDto.getId());
		if(caseToComplete == null) {
			response = new ObjectResponse<CaseDto>(ResponseEnum.ITEM_NOT_FOUND, caseDto);
		} else {
			caseToComplete.setStatus(CaseStatus.COMPLETED);
			caseRepo.save(caseToComplete);
			caseDto.setStatus(CaseStatus.COMPLETED);
			response = new ObjectResponse<CaseDto>(ResponseEnum.SUCCESS, caseDto);
		}
		return response;
	}

	@Override
	public ObjectResponse<CaseDto> getCaseById(Integer caseId) {
		ObjectResponse<CaseDto> response;
		Case c = caseRepo.getOne(caseId);
		CaseDto caseDto = mapper.map(c, CaseDto.class);
		caseDto.setAgentId(c.getAgent().getId());
		//caseDto.setDate(c.getCreatedAt().toString());
		response = new ObjectResponse<CaseDto>(ResponseEnum.SUCCESS, caseDto);
		return response;
	}

}
