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

import sd.mcc.project.dto.DonationDto;
import sd.mcc.project.dto.WalletDto;
import sd.mcc.project.model.Case;
import sd.mcc.project.model.Donation;
import sd.mcc.project.model.User;
import sd.mcc.project.model.Wallet;
import sd.mcc.project.repo.AuthorityRepository;
import sd.mcc.project.repo.CaseRepository;
import sd.mcc.project.repo.DonationRepository;
import sd.mcc.project.repo.UserRepository;
import sd.mcc.project.response.ListResponse;
import sd.mcc.project.response.ObjectResponse;
import sd.mcc.project.service.IDonationService;
import sd.mcc.project.service.IWalletService;
import sd.mcc.project.util.ResponseEnum;

/**
 * @author MaJiD
 *
 */
@Service
public class DonationServiceImp implements IDonationService {
	private final String ANONYMOUS_USERNAME = "anonymous";
	private final String AGENT_AUTHORITY = "AGENT";
	@Autowired
	private DonationRepository donationRepository;
	
	@Autowired
	private IWalletService walletService;
	
	@Autowired
	private CaseRepository caseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AuthorityRepository authorityRepo;

	@Override
	public ObjectResponse<DonationDto> donate(DonationDto donationDto) {
		ObjectResponse<DonationDto> response;
		Case the_case;
		Wallet wallet;
		User doner;
		Donation donation;
		User creater;
		try {
			if(donationDto.getAmount() > 0) {
				doner = userRepository.getOne(donationDto.getDonator_id());
				if(doner.getAuthorities().contains(authorityRepo.findByAuthorityName(AGENT_AUTHORITY)) && donationDto.isAnonymous()) {
					doner = userRepository.findByUsername(ANONYMOUS_USERNAME);
				}
				wallet = doner.getWallet();
				the_case = caseRepository.getOne(donationDto.getCase_id());
				creater = userRepository.getOne(donationDto.getCreated_by_id());
				
				if(wallet != null ) {
					if(wallet.getCurrent_balance() >= donationDto.getAmount()) {
						donation = new Donation();
						donation.setAmount(donationDto.getAmount());
						donation.setCreated_at(new Date());
						donation.setLast_update(new Date());
						donation.setDonator(doner);
						donation.setThe_case(the_case);
						donation.setInfo(donationDto.getInfo());
						donation.setCreated_by(creater);
						donation.setLast_update_by(creater);
						
						WalletDto walletDto = new WalletDto();
						walletDto.setAmount(donationDto.getAmount());
						walletDto.setOwner_id(donationDto.getDonator_id());
						walletDto.setCreated_by_id(donationDto.getCreated_by_id());
						walletDto.setId(wallet.getId());
						
						the_case.setCurrentDonations(the_case.getCurrentDonations() + donationDto.getAmount());
						the_case.setDonorsCount(the_case.getDonorsCount() + 1);
						the_case.setUpdatedAt(new Date());
						
						ObjectResponse<Wallet> walletResponse = walletService.debit(walletDto);
						if(walletResponse.getResponseCode() == ResponseEnum.SUCCESS.getResponseCode()) {
							Donation savedDonation = donationRepository.save(donation);
							caseRepository.save(the_case);
							DonationDto responseDonation = mapper.map(savedDonation, DonationDto.class);
							response = new ObjectResponse<DonationDto>(ResponseEnum.SUCCESS, responseDonation);
						} else {
							response = new ObjectResponse<DonationDto>(ResponseEnum.TRY_AGAIN, null);
						}
						
					} else {
						response = new ObjectResponse<DonationDto>(ResponseEnum.INSUFFICIENT, null);
					}
				} else {
					response = new ObjectResponse<DonationDto>(ResponseEnum.ITEM_NOT_FOUND, null);
				}
			} else {
				response = new ObjectResponse<DonationDto>(ResponseEnum.NEGATIVE_AMOUNT, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ObjectResponse<DonationDto>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	@Override
	public ObjectResponse<Donation> transferDonation(DonationDto donationDto) {
		ObjectResponse<Donation> response;
		Case old_case;
		Case new_case;
		Donation donation;
		try {
			donation = donationRepository.getOne(donationDto.getId());
			if(donation != null) {
				old_case = caseRepository.getOne(donationDto.getCase_id());
				new_case = caseRepository.getOne(donationDto.getNew_case_id());
				if(old_case != null && new_case != null) {
					if(donation.getThe_case().getId() == old_case.getId()) {
						new_case.setCurrentDonations(new_case.getCurrentDonations() + donation.getAmount());
						new_case.setDonorsCount(new_case.getDonorsCount() + 1);
						new_case.setUpdatedAt(new Date());
						
						old_case.setCurrentDonations(old_case.getCurrentDonations() - donation.getAmount());
						old_case.setDonorsCount(old_case.getDonorsCount() - 1);
						old_case.setUpdatedAt(new Date());
						
						donation.setThe_case(new_case);
						donation.setLast_update(new Date());
						donation.setLast_update_by(userRepository.getOne(donationDto.getCreated_by_id()));
						
						Donation updatedDonation = donationRepository.save(donation);
						if(updatedDonation != null) {
							caseRepository.save(new_case);
							caseRepository.save(old_case);
							response = new ObjectResponse<Donation>(ResponseEnum.SUCCESS, updatedDonation);
						} else {
							response = new ObjectResponse<Donation>(ResponseEnum.TRY_AGAIN, null);
						}
					} else {
						response = new ObjectResponse<Donation>(ResponseEnum.ITEM_NOT_FOUND, null);
					}
				} else {
					response = new ObjectResponse<Donation>(ResponseEnum.ITEM_NOT_FOUND, null);
				}
			} else {
				response = new ObjectResponse<Donation>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ObjectResponse<Donation>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	@Override
	public ListResponse<DonationDto> getCaseDonations(DonationDto donationDto) {
		ListResponse<DonationDto> response;
		List<DonationDto> donationDtos = new ArrayList<DonationDto>();
		List<Donation> donations;
		try {
			Case the_case = caseRepository.getOne(donationDto.getCase_id());
			if(the_case != null) {
				donations = donationRepository.getCaseDonations(the_case);
				donations.forEach(d -> donationDtos.add(toDto(d)));
				response = new ListResponse<DonationDto>(ResponseEnum.SUCCESS, donationDtos);
			} else {
				response = new ListResponse<DonationDto>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ListResponse<DonationDto>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	private DonationDto toDto(Donation d) {
		// TODO Auto-generated method stub
		DonationDto ddt = new DonationDto();
		ddt.setAmount(d.getAmount());
		ddt.setCase_id(d.getThe_case()==null?null:d.getThe_case().getId());
		ddt.setCreated_by_id(d.getCreated_by()==null?null:d.getCreated_by().getId());
		ddt.setDonator_id(d.getDonator()==null?null:d.getDonator().getId());
		ddt.setId(d.getId());
		ddt.setInfo(d.getInfo());
		ddt.setDonation_date(d.getCreated_at());
		return ddt;
	}

	@Override
	public ListResponse<Donation> getCaseDonationsPerPeriod(DonationDto donationDto) {
		ListResponse<Donation> response;
		try {
			Case the_case = caseRepository.getOne(donationDto.getCase_id());
			if(the_case != null) {
				response = new ListResponse<Donation>(ResponseEnum.SUCCESS, donationRepository.getCaseDonationsPerPeriod(the_case, donationDto.getStart_date(), donationDto.getEnd_date()));
			} else {
				response = new ListResponse<Donation>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ListResponse<Donation>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	@Override
	public ListResponse<DonationDto> getUserDonations(DonationDto donationDto) {
		ListResponse<DonationDto> response;
		List<DonationDto> donationDtos = new ArrayList<DonationDto>();
		List<Donation> donations;
		try {
			User donor = userRepository.getOne(donationDto.getDonator_id());
			if(donor != null) {
				donations = donationRepository.getUserDonations(donor);
				donations.forEach(d -> donationDtos.add(toDto(d)));
				response = new ListResponse<DonationDto>(ResponseEnum.SUCCESS, donationDtos );
			} else {
				response = new ListResponse<DonationDto>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ListResponse<DonationDto>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	@Override
	public ListResponse<Donation> getUserDonationsPerPeriod(DonationDto donationDto) {
		ListResponse<Donation> response;
		try {
			User donor = userRepository.getOne(donationDto.getDonator_id());
			if(donor != null) {
				response = new ListResponse<Donation>(ResponseEnum.SUCCESS, donationRepository.getUserDonationsPerPeriod(donor, donationDto.getStart_date(), donationDto.getEnd_date()));
			} else {
				response = new ListResponse<Donation>(ResponseEnum.ITEM_NOT_FOUND, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ListResponse<Donation>(ResponseEnum.TRY_AGAIN, null);
		}
		return response;
	}

	

}
