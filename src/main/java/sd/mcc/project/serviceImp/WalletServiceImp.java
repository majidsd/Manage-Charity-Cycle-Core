/**
 * 
 */
package sd.mcc.project.serviceImp;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sd.mcc.project.dto.WalletDto;
import sd.mcc.project.dto.WalletHistoryDto;
import sd.mcc.project.model.Wallet;
import sd.mcc.project.repo.WalletRepository;
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
public class WalletServiceImp implements IWalletService {
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private IWalletHistoryService walletHistoryService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ObjectResponse<Wallet> addWallet(WalletDto walletDto) {
		ObjectResponse<Wallet> response;
		Wallet wallet;
		try {
			wallet = new Wallet();
			try {
				wallet.setCreated_at(new Date());
				wallet.setLast_update(new Date());
				wallet.setCurrent_balance(walletDto.getAmount());
				Wallet savedWallet = walletRepository.save(wallet);
				response = new ObjectResponse<Wallet>(ResponseEnum.SUCCESS, savedWallet);
			} catch (Exception e) {
				e.printStackTrace();
				response = new ObjectResponse<Wallet>(ResponseEnum.DUPLICATED_ITEM, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ObjectResponse<Wallet>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}
	
	
	@Override
	public ObjectResponse<Wallet> getWallet(Integer id) {
		ObjectResponse<Wallet> response;
		Wallet wallet = walletRepository.getOne(id);
		if(wallet != null) {
			response = new ObjectResponse<Wallet>(ResponseEnum.SUCCESS, wallet);
		} else {
			response = new ObjectResponse<Wallet>(ResponseEnum.ITEM_NOT_FOUND, null);
		}
		return response;
	}

	@Override
	public ObjectResponse<Double> getBalance(WalletDto walletDto) {
		ObjectResponse<Double> response;
		Wallet wallet = walletRepository.getOne(walletDto.getId());
		if(wallet != null) {
			Double currentBalance = wallet.getCurrent_balance();
			response = new ObjectResponse<Double>(ResponseEnum.SUCCESS, currentBalance);
		} else {
			response = new ObjectResponse<Double>(ResponseEnum.ITEM_NOT_FOUND, null);
		}
		return response;
	}

	@Override
	public ObjectResponse<WalletDto> credit(WalletDto walletDto) {
		ObjectResponse<WalletDto> response;
		Wallet wallet = walletRepository.getOne(walletDto.getId());
		WalletDto mappedWallet = null;
		WalletHistoryDto walletHistoryDto;
		
		if(wallet != null) {
			if(walletDto.getAmount() > 0) {
				walletHistoryDto = new WalletHistoryDto();
				walletHistoryDto.setOperation(WalletOperations.CREDIT.getValue());
				walletHistoryDto.setAmount(walletDto.getAmount());
				walletHistoryDto.setCreated_by_id(walletDto.getOwner_id());
				walletHistoryDto.setWallet_id(wallet.getId());
				Double newBalance = wallet.getCurrent_balance() + walletDto.getAmount();
				wallet.setCurrent_balance(newBalance);
				wallet.setLast_update(new Date());
				Wallet updatedWallet = walletRepository.save(wallet);
				mappedWallet = mapper.map(updatedWallet, WalletDto.class);
				mappedWallet.setAmount(updatedWallet.getCurrent_balance());
				walletHistoryDto.setDescrtption("Credit " + walletDto.getAmount() + " And new balance is "+updatedWallet.getCurrent_balance());
				walletHistoryService.addWalletHistory(walletHistoryDto);
				response = new ObjectResponse<WalletDto>(ResponseEnum.SUCCESS, mappedWallet);
			} else {
				response = new ObjectResponse<WalletDto>(ResponseEnum.NEGATIVE_AMOUNT, null);
			}
			
		}else {
			response = new ObjectResponse<WalletDto>(ResponseEnum.ITEM_NOT_FOUND, null);
		}
		return response;
	}

	@Override
	public ObjectResponse<Wallet> debit(WalletDto walletDto) {
		ObjectResponse<Wallet> response;
		Wallet wallet = walletRepository.getOne(walletDto.getId());
		WalletHistoryDto walletHistoryDto;
		
		if(wallet != null) {
			if(walletDto.getAmount() > 0) {
				walletHistoryDto = new WalletHistoryDto();
				walletHistoryDto.setOperation(WalletOperations.DEBIT.getValue());
				walletHistoryDto.setAmount(walletDto.getAmount());
				walletHistoryDto.setCreated_by_id(walletDto.getOwner_id());
				walletHistoryDto.setWallet_id(wallet.getId());
				Double newBalance = wallet.getCurrent_balance() - walletDto.getAmount();
				if(newBalance >= 0) {
					wallet.setCurrent_balance(newBalance);
					wallet.setLast_update(new Date());
					Wallet updatedWallet = walletRepository.save(wallet);
					walletHistoryDto.setDescrtption("Debit " + walletDto.getAmount() + " And new balance is "+updatedWallet.getCurrent_balance());
					walletHistoryService.addWalletHistory(walletHistoryDto);
					response = new ObjectResponse<Wallet>(ResponseEnum.SUCCESS, updatedWallet);
				} else {
					response = new ObjectResponse<Wallet>(ResponseEnum.INSUFFICIENT, wallet);
				}
			} else {
				response = new ObjectResponse<Wallet>(ResponseEnum.NEGATIVE_AMOUNT, null);
			}
		}else {
			response = new ObjectResponse<Wallet>(ResponseEnum.ITEM_NOT_FOUND, null);
		}
		return response;
	}


	@Override
	public ListResponse<Wallet> getAllWallets() {
		ListResponse<Wallet> response;
		try {
			response = new ListResponse<Wallet>(ResponseEnum.SUCCESS, walletRepository.findAll());
		} catch (Exception e) {
			response = new ListResponse<Wallet>(ResponseEnum.TRY_AGAIN, null); 
		}
		return response;
	}

}
