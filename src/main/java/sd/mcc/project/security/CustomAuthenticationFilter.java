/**
 * 
 */
package sd.mcc.project.security;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import sd.mcc.project.dto.OtpDto;
import sd.mcc.project.dto.UserProfileDto;
import sd.mcc.project.response.ObjectResponse;
import sd.mcc.project.service.IUserAuthentication;
import sd.mcc.project.util.ResponseEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author ahmedozy
 *
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	private IUserAuthentication userAuthenticationService;
	
	private PasswordEncoder encoder;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, IUserAuthentication userAuthenticationService, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.userAuthenticationService = userAuthenticationService;
        this.encoder = encoder;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
        	OtpDto otpDto = new ObjectMapper().readValue(req.getInputStream(), OtpDto.class);

        	ObjectResponse<UserProfileDto> userProfileResponse = userAuthenticationService.verifyAndLogin(otpDto);
        	if(userProfileResponse.getResponseCode() != ResponseEnum.SUCCESS.getResponseCode()) {
        		throw new RuntimeException(userProfileResponse.getResponseMessage()) ;
        	}
        	UserProfileDto upd = userProfileResponse.getDto();
        	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(upd.getPhoneNumber()+"",
            		SecurityConstants.FIXED_PASSWORD,  upd.getAuthorities());
            Authentication authentication = authenticationManager.authenticate(token);
            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

    	Date exp = new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME);
        Key key = Keys.hmacShaKeyFor(SecurityConstants.KEY.getBytes());
        Claims claims = Jwts.claims().setSubject(((User) auth.getPrincipal()).getUsername());
        claims.put("authorities", AuthorityUtils.authorityListToSet(auth.getAuthorities()));
        String token = Jwts.builder()
        		.setClaims(claims)
        		.signWith(SignatureAlgorithm.HS512, key)
        		.setExpiration(exp)
        		.compact();
        res.addHeader("token", token);


    }
}
