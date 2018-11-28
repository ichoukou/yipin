package com.ytoxl.yipin.web.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import com.ytoxl.module.user.security.CustomUserDetailsServiceImpl;

/** 自定义
 * @author user
 *
 */
public class LoginAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
{


 
    // ~ Instance fields
    // ================================================================================================
 
    private PasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();
 
    private SaltSource saltSource;
 
    private CustomUserDetailsServiceImpl userDetailsService;
    
    private String ytoxlSalt;
    
    private String hash;
 
    // ~ Methods
    // ========================================================================================================
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException{
    	
    	CustomUsernamePasswordAuthenticationToken auth = (CustomUsernamePasswordAuthenticationToken)authentication;
        if (authentication.getCredentials() == null)
        {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException("Bad credentials:" + userDetails);
        }
        //org.springframework.security.authentication.encoding.Md5PasswordEncoder
        String presentedPassword = authentication.getCredentials().toString();
        if(hash.equals("md5")){//如果是md5則驗證
        	 passwordEncoder = new Md5PasswordEncoder();
        	 ((Md5PasswordEncoder) passwordEncoder).setEncodeHashAsBase64(true);
        }
        if(!auth.isOpenIdOathLogin()){//微博登录不需要验证密码
        	if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, ytoxlSalt))
            {
                logger.debug("Authentication failed: password does not match stored value");
                throw new BadCredentialsException("Bad credentials:" + userDetails);
            }
        }
    }

    public String getYtoxlSalt() {
		return ytoxlSalt;
	}


	public void setYtoxlSalt(String ytoxlSalt) {
		this.ytoxlSalt = ytoxlSalt;
	}


	protected void doAfterPropertiesSet() throws Exception
    {
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }
	
 
    public String getHash() {
		return hash;
	}


	public void setHash(String hash) {
		this.hash = hash;
	}


	protected PasswordEncoder getPasswordEncoder()
    {
        return passwordEncoder;
    }
 
    protected SaltSource getSaltSource()
    {
        return saltSource;
    }
 
    protected CustomUserDetailsServiceImpl getUserDetailsService()
    {
        return userDetailsService;
    }
 
    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException
    {
        UserDetails loadedUser;
        try
        {
            String password = (String) authentication.getCredentials();
            loadedUser = getUserDetailsService().loadUserByUsername(username);//区别在这里
        }
        catch (UsernameNotFoundException notFound)
        {
            throw notFound;
        }
        catch (Exception repositoryProblem)
        {
            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
 
        if (loadedUser == null)
        {
            throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }
 
    public static void main(String[] args) {
    	Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
    	 passwordEncoder.setEncodeHashAsBase64(true);
		System.out.println(passwordEncoder.encodePassword("123456","yto123456xl"));
	}
     	
    /**
     * Sets the PasswordEncoder instance to be used to encode and validate
     * passwords. If not set, the password will be compared as plain text.
     * <p>
     * For systems which are already using salted password which are encoded
     * with a previous release, the encoder should be of type
     * {@code org.springframework.security.authentication.encoding.PasswordEncoder}
     * . Otherwise, the recommended approach is to use
     * {@code org.springframework.security.crypto.password.PasswordEncoder}.
     *
     * @param passwordEncoder
     *            must be an instance of one of the {@code PasswordEncoder}
     *            types.
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
    	 this.passwordEncoder = passwordEncoder;
    }
 
    /**
     * The source of salts to use when decoding passwords. <code>null</code> is
     * a valid value, meaning the <code>DaoAuthenticationProvider</code> will
     * present <code>null</code> to the relevant <code>PasswordEncoder</code>.
     * <p>
     * Instead, it is recommended that you use an encoder which uses a random
     * salt and combines it with the password field. This is the default
     * approach taken in the
     * {@code org.springframework.security.crypto.password} package.
     *
     * @param saltSource
     *            to use when attempting to decode passwords via the
     *            <code>PasswordEncoder</code>
     */
    public void setSaltSource(SaltSource saltSource)
    {
        this.saltSource = saltSource;
    }
 
    public void setUserDetailsService(CustomUserDetailsServiceImpl userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }
}