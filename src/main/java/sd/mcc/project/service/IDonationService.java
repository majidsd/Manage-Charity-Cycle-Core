/**
 * 
 */
package sd.mcc.project.service;

import sd.mcc.project.dto.DonationDto;
import sd.mcc.project.model.Donation;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;

/**
 * @author MaJiD
 *
 */
public interface IDonationService {
	
	public ObjectResponse<DonationDto> donate(DonationDto donationDto);
	
	public ObjectResponse<Donation> transferDonation(DonationDto donationDto);
	
	public ListResponse<DonationDto> getCaseDonations(DonationDto donationDto);
	
	public ListResponse<Donation> getCaseDonationsPerPeriod(DonationDto donationDto);
	
	public ListResponse<DonationDto> getUserDonations(DonationDto donationDto);
	
	public ListResponse<Donation> getUserDonationsPerPeriod(DonationDto donationDto);
	
}
