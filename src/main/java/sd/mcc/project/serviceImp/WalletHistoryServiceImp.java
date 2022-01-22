/**
 * 
 */
package sd.mcc.project.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sd.mcc.project.dto.WalletHistoryDto;
import sd.mcc.project.model.User;
import sd.mcc.project.model.Wallet;
import sd.mcc.project.model.WalletHistory;
import sd.mcc.project.repo.UserRepository;
import sd.mcc.project.repo.WalletHistoryRepository;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;
import sd.mcc.project.service.IWalletHistoryService;
import sd.mcc.project.service.IWalletService;
import sd.mcc.project.util.ResponseEnum;
import sd.mcc.project.util.WalletOperations;

/**
 * @author MaJiD
 *
 */
@Service
public class WalletHistoryServiceImp implements IWalletHistoryService  {
	
	@Autowired
	private IWalletService walletService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WalletHistoryRepository walletHistoryRepository;
	
	@Override
	public ObjectResponse<WalletHistory> addWalletHistory(WalletHistoryDto walletHistoryDto) {
		
		ObjectResponse<WalletHistory> response;
		WalletHistory walletHistory = new WalletHistory();
		try {
			ObjectResponse<Wallet> walletRespone = walletService.getWallet(walletHistoryDto.getWallet_id());
			if(walletRespone.getResponseCode() == ResponseEnum.SUCCESS.getResponseCode() && walletRespone.getDto() != null) {
				User user = userRepository.getOne(walletHistoryDto.getCreated_by_id());
				walletHistory.setAmount(walletHistoryDto.getAmount());
				walletHistory.setWallet(walletRespone.getDto());
				walletHistory.setCreated_at(new Date());
				walletHistory.setCreated_by(user);
				walletHistory.setDescription(walletHistoryDto.getDescrtption());
				walletHistory.setOperation(walletHistoryDto.getOperation());
				walletHistory.setLast_update(new Date());
				walletHistory.setLast_update_by(user);
				WalletHistory addedWalletHistory = walletHistoryRepository.save(walletHistory);
				response = new ObjectResponse<WalletHistory>(ResponseEnum.SUCCESS, addedWalletHistory);
			} else {
				response = new ObjectResponse<WalletHistory>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ObjectResponse<WalletHistory>(ResponseEnum.DUPLICATED_ITEM, null);
		}
		return response;
	}

	@Override
	public ListResponse<WalletHistory> getOldCredit(WalletHistoryDto walletHistoryDto) {
		ListResponse<WalletHistory> response;
		try {
			Wallet wallet = walletService.getWallet(walletHistoryDto.getWallet_id()).getDto();
			if(wallet != null) {
				response = new ListResponse<WalletHistory>(ResponseEnum.SUCCESS, walletHistoryRepository.getWalletOperationsHistory(wallet, WalletOperations.CREDIT.getValue()));
			} else {
				response = new ListResponse<WalletHistory>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			response = new ListResponse<WalletHistory>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	@Override
	public ListResponse<WalletHistory> getOldCreditPerPeriod(WalletHistoryDto walletHistoryDto) {
		ListResponse<WalletHistory> response;
		try {
			Wallet wallet = walletService.getWallet(walletHistoryDto.getWallet_id()).getDto();
			if(wallet != null) {
				response = new ListResponse<WalletHistory>(ResponseEnum.SUCCESS, walletHistoryRepository.getWalletOperationsHistoryPerPeriod(wallet, WalletOperations.CREDIT.getValue(), walletHistoryDto.getFrom_date(), walletHistoryDto.getTo_date()));
			} else {
				response = new ListResponse<WalletHistory>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			response = new ListResponse<WalletHistory>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	@Override
	public ListResponse<WalletHistory> getOldDebit(WalletHistoryDto walletHistoryDto) {
		ListResponse<WalletHistory> response;
		try {
			Wallet wallet = walletService.getWallet(walletHistoryDto.getWallet_id()).getDto();
			if(wallet != null) {
				response = new ListResponse<WalletHistory>(ResponseEnum.SUCCESS, walletHistoryRepository.getWalletOperationsHistory(wallet, WalletOperations.DEBIT.getValue()));
			} else {
				response = new ListResponse<WalletHistory>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			response = new ListResponse<WalletHistory>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	@Override
	public ListResponse<WalletHistory> getOldDebitPerPeriod(WalletHistoryDto walletHistoryDto) {
		ListResponse<WalletHistory> response;
		try {
			Wallet wallet = walletService.getWallet(walletHistoryDto.getWallet_id()).getDto();
			if(wallet != null) {
				response = new ListResponse<WalletHistory>(ResponseEnum.SUCCESS, walletHistoryRepository.getWalletOperationsHistoryPerPeriod(wallet, WalletOperations.DEBIT.getValue(), walletHistoryDto.getFrom_date(), walletHistoryDto.getTo_date()));
			} else {
				response = new ListResponse<WalletHistory>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			response = new ListResponse<WalletHistory>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	@Override
	public ListResponse<WalletHistoryDto> getOldTransactions(WalletHistoryDto walletHistoryDto) {
		ListResponse<WalletHistoryDto> response;
		List<WalletHistoryDto> walletHistoryDtos = new ArrayList<WalletHistoryDto>();
		try {
			Wallet wallet = walletService.getWallet(walletHistoryDto.getWallet_id()).getDto();
			if(wallet != null) {
				List<WalletHistory> walletHistoryList = walletHistoryRepository.getWalletTransactions(wallet);
				walletHistoryList.forEach(wh -> walletHistoryDtos.add(toDto(wh)));
				response = new ListResponse<WalletHistoryDto>(ResponseEnum.SUCCESS, walletHistoryDtos);
			} else {
				response = new ListResponse<WalletHistoryDto>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			response = new ListResponse<WalletHistoryDto>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	private WalletHistoryDto toDto(WalletHistory wh) {
		WalletHistoryDto whd = new WalletHistoryDto();
		whd.setId(wh.getId());
		whd.setAmount(wh.getAmount());
		whd.setCreated_by_id(wh.getCreated_by()==null?null:wh.getCreated_by().getId());
		whd.setDescrtption(wh.getDescription());
		whd.setWallet_id(wh.getWallet().getId());
		whd.setOperation(wh.getOperation());
		whd.setCreated_at(wh.getCreated_at());
		return whd;
	}

	@Override
	public ListResponse<WalletHistory> getOldTransactionsPerPeriod(WalletHistoryDto walletHistoryDto) {
		ListResponse<WalletHistory> response;
		try {
			Wallet wallet = walletService.getWallet(walletHistoryDto.getWallet_id()).getDto();
			if(wallet != null) {
				response = new ListResponse<WalletHistory>(ResponseEnum.SUCCESS, walletHistoryRepository.getWalletTransactionsPerPeriod(wallet, walletHistoryDto.getFrom_date(), walletHistoryDto.getTo_date()));
			} else {
				response = new ListResponse<WalletHistory>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			response = new ListResponse<WalletHistory>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

}
