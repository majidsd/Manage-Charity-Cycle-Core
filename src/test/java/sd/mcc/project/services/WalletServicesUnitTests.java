/**
 * 
 */
package sd.mcc.project.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sd.mcc.project.dto.WalletDto;
import sd.mcc.project.model.Wallet;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;
import sd.mcc.project.service.IWalletService;

/**
 * @author MaJiD
 *
 */
@SpringBootTest
public class WalletServicesUnitTests {
	/*
	 * @Autowired private IWalletService walletService;
	 * 
	 * private WalletDto walletDto;
	 * 
	 * @Test public void getBalanceUnitTest() { walletDto = new WalletDto();
	 * walletDto.setId(1); System.out.println(walletService.getBalance(walletDto));
	 * }
	 * 
	 * @Test public void addWalletUnitTests() { walletDto = new WalletDto();
	 * walletDto.setAmount(58000.0); walletDto.setOwner_id(3);
	 * walletDto.setCreated_by_id(1); ObjectResponse<Wallet> response =
	 * walletService.addWallet(walletDto);
	 * System.out.println(response.getResponseMessage() + " - " +
	 * response.getDto()); }
	 * 
	 * @Test public void debitUnitTests() { walletDto = new WalletDto();
	 * walletDto.setId(2); walletDto.setAmount(400.0); walletDto.setOwner_id(1);
	 * ObjectResponse<Wallet> response = walletService.debit(walletDto);
	 * System.out.println(response); }
	 * 
	 * @Test public void creditUnitTests() { walletDto = new WalletDto();
	 * walletDto.setId(1); walletDto.setAmount(5000.0); walletDto.setOwner_id(1);
	 * ObjectResponse<Wallet> response = walletService.credit(walletDto);
	 * System.out.println(response); }
	 * 
	 * @Test public void getAllWalletsUnitTests() { ListResponse<Wallet> response =
	 * walletService.getAllWallets(); System.out.println(response.getDtos()); }
	 */	
}
