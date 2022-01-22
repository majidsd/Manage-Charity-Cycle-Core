/**
 * 
 */
package sd.mcc.project.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sd.mcc.project.dto.WalletHistoryDto;
import sd.mcc.project.model.WalletHistory;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;
import sd.mcc.project.service.IWalletHistoryService;
import sd.mcc.project.util.WalletOperations;


/**
 * @author MaJiD
 *
 */
@SpringBootTest
public class WalletHistoryServiceUnitTests {
	/*
	 * @Autowired private IWalletHistoryService walletHistoryService;
	 * 
	 * private WalletHistoryDto walletHistoryDto;
	 * 
	 * @Test public void addWalletHistoryUnitTest() { walletHistoryDto = new
	 * WalletHistoryDto(); walletHistoryDto.setAmount(2000.0);
	 * walletHistoryDto.setCreated_by_id(1);
	 * walletHistoryDto.setDescrtption("This for out new project.");
	 * walletHistoryDto.setWallet_id(1);
	 * walletHistoryDto.setOperation(WalletOperations.DEBIT.getValue());
	 * ObjectResponse<WalletHistory> response =
	 * walletHistoryService.addWalletHistory(walletHistoryDto);
	 * if(response.getResponseCode() == 201) {
	 * System.out.println(response.getDto()); } else { System.out.println(response);
	 * } }
	 * 
	 * @Test public void getWalletHistoryForDebitUnitTest() { walletHistoryDto = new
	 * WalletHistoryDto(); walletHistoryDto.setAmount(12000.0);
	 * walletHistoryDto.setCreated_by_id(1);
	 * walletHistoryDto.setDescrtption("This for out child.");
	 * walletHistoryDto.setWallet_id(1);
	 * walletHistoryDto.setOperation(WalletOperations.DEBIT.getValue());
	 * System.out.println(walletHistoryDto); ListResponse<WalletHistory> response =
	 * walletHistoryService.getOldDebit(walletHistoryDto);
	 * System.out.println(response.getDtos()); }
	 * 
	 * @Test public void getWalletHistoryForCreditUnitTest() { walletHistoryDto =
	 * new WalletHistoryDto(); walletHistoryDto.setAmount(12000.0);
	 * walletHistoryDto.setCreated_by_id(1);
	 * walletHistoryDto.setDescrtption("This for out child.");
	 * walletHistoryDto.setWallet_id(1);
	 * walletHistoryDto.setOperation(WalletOperations.CREDIT.getValue());
	 * System.out.println(walletHistoryDto); ListResponse<WalletHistory> response =
	 * walletHistoryService.getOldCredit(walletHistoryDto);
	 * System.out.println(response.getDtos()); }
	 * 
	 * @Test public void getWalletHistoryForDebitPerPeriodUnitTest() throws
	 * ParseException { walletHistoryDto = new WalletHistoryDto();
	 * walletHistoryDto.setAmount(12000.0); walletHistoryDto.setCreated_by_id(1);
	 * walletHistoryDto.setDescrtption("This for out child.");
	 * walletHistoryDto.setWallet_id(1);
	 * walletHistoryDto.setOperation(WalletOperations.DEBIT.getValue());
	 * walletHistoryDto.setFrom_date(new
	 * SimpleDateFormat("yyyy-MM-dd").parse("2020-5-14"));
	 * walletHistoryDto.setTo_date(new
	 * SimpleDateFormat("yyyy-MM-dd").parse("2020-5-21"));
	 * System.out.println(walletHistoryDto); ListResponse<WalletHistory> response =
	 * walletHistoryService.getOldDebitPerPeriod(walletHistoryDto);
	 * System.out.println(response.getDtos()); }
	 * 
	 * @Test public void getWalletHistoryForCreditPerPeriodUnitTest() throws
	 * ParseException { walletHistoryDto = new WalletHistoryDto();
	 * walletHistoryDto.setAmount(12000.0); walletHistoryDto.setCreated_by_id(1);
	 * walletHistoryDto.setDescrtption("This for out child.");
	 * walletHistoryDto.setWallet_id(1);
	 * walletHistoryDto.setOperation(WalletOperations.CREDIT.getValue());
	 * walletHistoryDto.setFrom_date(new
	 * SimpleDateFormat("yyyy-MM-dd").parse("2020-5-14"));
	 * walletHistoryDto.setTo_date(new
	 * SimpleDateFormat("yyyy-MM-dd").parse("2020-5-21"));
	 * System.out.println(walletHistoryDto); ListResponse<WalletHistory> response =
	 * walletHistoryService.getOldCreditPerPeriod(walletHistoryDto);
	 * System.out.println(response.getDtos()); }
	 * 
	 * @Test public void getWalletHistoryTransactionsUnitTest() throws
	 * ParseException { walletHistoryDto = new WalletHistoryDto();
	 * walletHistoryDto.setAmount(12000.0); walletHistoryDto.setCreated_by_id(1);
	 * walletHistoryDto.setDescrtption("This for out child.");
	 * walletHistoryDto.setWallet_id(1);
	 * walletHistoryDto.setOperation(WalletOperations.DEBIT.getValue());
	 * walletHistoryDto.setFrom_date(new
	 * SimpleDateFormat("yyyy-MM-dd").parse("2020-5-14"));
	 * walletHistoryDto.setTo_date(new
	 * SimpleDateFormat("yyyy-MM-dd").parse("2020-5-21"));
	 * System.out.println(walletHistoryDto); ListResponse<WalletHistory> response =
	 * walletHistoryService.getOldTransactions(walletHistoryDto);
	 * System.out.println(response.getDtos()); }
	 * 
	 * @Test public void getWalletHistoryForTransactionsPerPeriodUnitTest() throws
	 * ParseException { walletHistoryDto = new WalletHistoryDto();
	 * walletHistoryDto.setAmount(12000.0); walletHistoryDto.setCreated_by_id(1);
	 * walletHistoryDto.setDescrtption("This for out child.");
	 * walletHistoryDto.setWallet_id(1);
	 * walletHistoryDto.setOperation(WalletOperations.CREDIT.getValue());
	 * walletHistoryDto.setFrom_date(new
	 * SimpleDateFormat("yyyy-MM-dd").parse("2020-5-14"));
	 * walletHistoryDto.setTo_date(new
	 * SimpleDateFormat("yyyy-MM-dd").parse("2020-5-21"));
	 * System.out.println(walletHistoryDto); ListResponse<WalletHistory> response =
	 * walletHistoryService.getOldTransactionsPerPeriod(walletHistoryDto);
	 * System.out.println(response.getDtos()); }
	 */
}
