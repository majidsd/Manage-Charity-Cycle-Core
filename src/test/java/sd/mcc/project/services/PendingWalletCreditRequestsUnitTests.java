/**
 * 
 */
package sd.mcc.project.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sd.mcc.project.dto.PendingWalletCreditDto;
import sd.mcc.project.model.PendingWalletCredit;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;
import sd.mcc.project.service.IPendingWalletCreditService;

/**
 * @author MaJiD
 *
 */
@SpringBootTest
public class PendingWalletCreditRequestsUnitTests {
	
	@Autowired
	private IPendingWalletCreditService pendingWalletCreditService;

	/*
	 * @Test public void addPendingWalletRequestUnitTests() { PendingWalletCreditDto
	 * pendingWalletCreditDto = new PendingWalletCreditDto();
	 * pendingWalletCreditDto.setAmount(10000.0);
	 * pendingWalletCreditDto.setBank("BoK");
	 * pendingWalletCreditDto.setBranch("Abid Khatim");
	 * pendingWalletCreditDto.setCreated_by_id(1);
	 * pendingWalletCreditDto.setDescription("I need to credit my wallet.");
	 * pendingWalletCreditDto.setReceipt_imgUrl("C://test/test.img");
	 * pendingWalletCreditDto.setReceipt_no("1232434323");
	 * pendingWalletCreditDto.setWallet_id(2);
	 * 
	 * ObjectResponse<PendingWalletCreditDto> response =
	 * pendingWalletCreditService.addPendingWalletCredit(pendingWalletCreditDto);
	 * System.out.println(response.getResponseMessage() + " - " +
	 * response.getDto()); }
	 */
	
	/*
	 * @Test public void getPendingWalletCreditUnitTest() { PendingWalletCreditDto
	 * pendingWalletCreditDto = new PendingWalletCreditDto();
	 * pendingWalletCreditDto.setId(1); ObjectResponse<PendingWalletCredit> response
	 * = pendingWalletCreditService.getPendingWalletCredit(pendingWalletCreditDto);
	 * System.out.println(response.getResponseMessage() + " - " +
	 * response.getDto()); }
	 * 
	 * @Test public void getWalletPendingWalletCreditUnitTest() {
	 * PendingWalletCreditDto pendingWalletCreditDto = new PendingWalletCreditDto();
	 * pendingWalletCreditDto.setWallet_id(2); ListResponse<PendingWalletCredit>
	 * response =
	 * pendingWalletCreditService.getUserPendingWalletCredits(pendingWalletCreditDto
	 * ); System.out.println(response.getResponseMessage() + " - " +
	 * response.getDtos()); }
	 * 
	 * @Test public void getWalletsPendingWalletCreditUnitTest() {
	 * ListResponse<PendingWalletCredit> response =
	 * pendingWalletCreditService.getAllPendingWalletCredits();
	 * System.out.println(response.getResponseMessage() + " - " +
	 * response.getDtos()); }
	 * 
	 * @Test public void approvePendingWalletCreditUnitTest() {
	 * PendingWalletCreditDto pendingWalletCreditDto = new PendingWalletCreditDto();
	 * pendingWalletCreditDto.setCreated_by_id(1); pendingWalletCreditDto.setId(1);
	 * ObjectResponse<PendingWalletCredit> response =
	 * pendingWalletCreditService.approvePendingWalletCredit(pendingWalletCreditDto)
	 * ; System.out.println(response.getResponseMessage() + " - " +
	 * response.getDto()); }
	 * 
	 * @Test public void rejectPendingWalletCreditUnitTest() {
	 * PendingWalletCreditDto pendingWalletCreditDto = new PendingWalletCreditDto();
	 * pendingWalletCreditDto.setCreated_by_id(1); pendingWalletCreditDto.setId(2);
	 * ObjectResponse<PendingWalletCredit> response =
	 * pendingWalletCreditService.rejectPendingWalletCredit(pendingWalletCreditDto);
	 * System.out.println(response.getResponseMessage() + " - " +
	 * response.getDto()); }
	 */
}
