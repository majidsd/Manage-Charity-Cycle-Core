/**
 * 
 */
package sd.mcc.project.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sd.mcc.project.dto.PendingWalletCreditDto;
import sd.mcc.project.dto.WalletDto;
import sd.mcc.project.model.PendingWalletCredit;
import sd.mcc.project.model.User;
import sd.mcc.project.model.Wallet;
import sd.mcc.project.repo.PendingWalletCreditRepository;
import sd.mcc.project.repo.UserRepository;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;
import sd.mcc.project.service.IPendingWalletCreditService;
import sd.mcc.project.service.IWalletService;
import sd.mcc.project.util.PendingWalletCreditStatus;
import sd.mcc.project.util.ResponseEnum;

/**
 * @author MaJiD
 *
 */
@Service
public class PendingWalletCreditServiceImp implements IPendingWalletCreditService {

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	private PendingWalletCreditRepository pendingWalletCreditRepository;
	
	@Autowired
	private IWalletService walletService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ObjectResponse<PendingWalletCreditDto> addPendingWalletCredit(PendingWalletCreditDto pendingWalletCreditDto) {
		ObjectResponse<PendingWalletCreditDto> response;
		PendingWalletCredit pendingWalletCredit;
		Wallet wallet;
		User user;
		try {
			wallet = walletService.getWallet(pendingWalletCreditDto.getWallet_id()).getDto();
			user = userRepository.getOne(pendingWalletCreditDto.getCreated_by_id());
			Double amount = pendingWalletCreditDto.getAmount();
			if(amount > 0) {
				if(wallet != null && user != null) {
					pendingWalletCredit = new PendingWalletCredit();
					pendingWalletCredit.setAmount(amount);
					pendingWalletCredit.setBank(pendingWalletCreditDto.getBank());
					pendingWalletCredit.setBranch(pendingWalletCreditDto.getBranch());
					pendingWalletCredit.setDescription(pendingWalletCreditDto.getDescription());
					pendingWalletCredit.setReceipt_imgUrl(pendingWalletCreditDto.getReceipt_imgUrl());
					pendingWalletCredit.setReceipt_no(pendingWalletCreditDto.getReceipt_no());
					pendingWalletCredit.setWallet(wallet);
					pendingWalletCredit.setStatus(PendingWalletCreditStatus.NEW.getValue());
					pendingWalletCredit.setCreated_at(new Date());
					pendingWalletCredit.setLast_update(new Date());
					pendingWalletCredit.setCreated_by(user);
					pendingWalletCredit.setLast_update_by(user);
					PendingWalletCredit newPendingWalletCredit = pendingWalletCreditRepository.save(pendingWalletCredit);
					PendingWalletCreditDto mappedPendingWalletCreditDto = mapper.map(newPendingWalletCredit, PendingWalletCreditDto.class);
					response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.SUCCESS, mappedPendingWalletCreditDto);
				} else {
					response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.ITEM_NOT_FOUND, null);
				}
			} else {
				response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.NEGATIVE_AMOUNT, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.TRY_AGAIN);
		}
		return response;
	}

	@Override
	public ObjectResponse<PendingWalletCreditDto> approvePendingWalletCredit(
			PendingWalletCreditDto pendingWalletCreditDto) {
		ObjectResponse<PendingWalletCreditDto> response;
		PendingWalletCredit pendingWalletCredit;
		try {
			pendingWalletCredit = pendingWalletCreditRepository.getOne(pendingWalletCreditDto.getId());
			if (pendingWalletCredit != null && pendingWalletCredit.getStatus() == PendingWalletCreditStatus.NEW.getValue()) {
				pendingWalletCredit.setStatus(PendingWalletCreditStatus.DONE.getValue());
				pendingWalletCredit.setLast_update(new Date());
				pendingWalletCredit.setLast_update_by(userRepository.getOne(pendingWalletCreditDto.getCreated_by_id()));
				Wallet wallet = walletService.getWallet(pendingWalletCredit.getWallet().getId()).getDto();
				if (wallet != null) {
					WalletDto walletDto = mapper.map(wallet, WalletDto.class);
					walletDto.setAmount(pendingWalletCredit.getAmount());
					walletDto.setId(wallet.getId());
					walletDto.setCreated_by_id(pendingWalletCreditDto.getCreated_by_id());
					walletDto.setOwner_id(pendingWalletCreditDto.getCreated_by_id());
					if(walletService.credit(walletDto).getResponseCode() == ResponseEnum.SUCCESS.getResponseCode()) {
						response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.SUCCESS, mapper.map(pendingWalletCreditRepository.save(pendingWalletCredit),PendingWalletCreditDto.class));
					} else {
						response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.TRY_AGAIN, null);
					}
				} else {
					response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.ITEM_NOT_FOUND, null);
				}
			} else {
				response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.TRY_AGAIN);
		}
		return response;
	}
	
	@Override
	public ObjectResponse<PendingWalletCreditDto> rejectPendingWalletCredit(
			PendingWalletCreditDto pendingWalletCreditDto) {
		ObjectResponse<PendingWalletCreditDto> response;
		PendingWalletCredit pendingWalletCredit;
		try {
			pendingWalletCredit = pendingWalletCreditRepository.getOne(pendingWalletCreditDto.getId());
			if(pendingWalletCredit != null && pendingWalletCredit.getStatus() == PendingWalletCreditStatus.NEW.getValue()) {
				pendingWalletCredit.setStatus(PendingWalletCreditStatus.REJECTED.getValue());
				pendingWalletCredit.setLast_update(new Date());
				pendingWalletCredit.setLast_update_by(userRepository.getOne(pendingWalletCreditDto.getCreated_by_id()));
				response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.SUCCESS, mapper.map(pendingWalletCreditRepository.save(pendingWalletCredit),PendingWalletCreditDto.class));
			} else {
				response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	@Override
	public ObjectResponse<PendingWalletCreditDto> getPendingWalletCredit(PendingWalletCreditDto pendingWalletCreditDto) {
		ObjectResponse<PendingWalletCreditDto> response;
		try {
			response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.SUCCESS, mapper.map(pendingWalletCreditRepository.getOne(pendingWalletCreditDto.getId()),PendingWalletCreditDto.class));
		} catch (Exception e) {
			e.printStackTrace();
			response = new ObjectResponse<PendingWalletCreditDto>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	@Override
	public ListResponse<PendingWalletCreditDto> getAllPendingWalletCredits() {
		ListResponse<PendingWalletCreditDto> response;
		try {
			List<PendingWalletCreditDto> pwcds = new ArrayList<>();
			pendingWalletCreditRepository.getWalletsPendingCredit( PendingWalletCreditStatus.NEW.getValue()).forEach(p -> pwcds.add(mapper.map(pwcds, PendingWalletCreditDto.class)));
			response = new ListResponse<PendingWalletCreditDto>(ResponseEnum.SUCCESS, pwcds);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ListResponse<PendingWalletCreditDto>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	@Override
	public ListResponse<PendingWalletCreditDto> getUserPendingWalletCredits(
			PendingWalletCreditDto pendingWalletCreditDto) {
		ListResponse<PendingWalletCreditDto> response;
		Wallet wallet;
		try {
			wallet = walletService.getWallet(pendingWalletCreditDto.getWallet_id()).getDto();
			if (wallet != null) {
				List<PendingWalletCreditDto> pwcds = new ArrayList<>();
				pendingWalletCreditRepository.getWalletPendingCredit(wallet, PendingWalletCreditStatus.NEW.getValue()).forEach(p -> pwcds.add(mapper.map(pwcds, PendingWalletCreditDto.class)));
				response = new ListResponse<PendingWalletCreditDto>(ResponseEnum.SUCCESS, pwcds);
			} else {
				response = new ListResponse<PendingWalletCreditDto>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ListResponse<PendingWalletCreditDto>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

}
