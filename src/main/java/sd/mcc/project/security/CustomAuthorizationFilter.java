/**
 * 
 */
package sd.mcc.project.security;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import sd.mcc.project.model.Authority;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


/**
 * @author ahmedozy
 *
 */
public class CustomAuthorizationFilter extends BasicAuthenticationFilter {


    public CustomAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER_NAME);

        if (header == null) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = authenticate(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_NAME);
        if (token != null) {
            Claims user = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.KEY.getBytes()))
                    .parseClaimsJws(token)
                    .getBody();

            if (user != null) {
            	List<String> grantedAuthorities= (List<String>) user.get("authorities");
            	List<GrantedAuthority> newList = new ArrayList<>();
            	grantedAuthorities.forEach(ga -> newList.add(new GrantedAuthority() {
					
					@Override
					public String getAuthority() {
						// TODO Auto-generated method stub
						return ga;
					}
				}));
                return new UsernamePasswordAuthenticationToken(user, null, newList);
            }else{
                return  null;
            }

        }
        return null;
    }
}
