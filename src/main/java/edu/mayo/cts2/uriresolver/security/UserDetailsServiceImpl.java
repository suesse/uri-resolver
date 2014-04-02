/*
* Copyright: (c) Mayo Foundation for Medical Education and
* Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
* triple-shield Mayo logo are trademarks and service marks of MFMER.
*
* Distributed under the OSI-approved BSD 3-Clause License.
* See http://ncip.github.com/URI_Resolver/LICENSE.txt for details.
*/
package edu.mayo.cts2.uriresolver.security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.mayo.cts2.uriresolver.logging.URILogger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Service("myUserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {
	private static URILogger logger = new URILogger(UserDetailsServiceImpl.class);

	// just to emulate user data and credentials retrieval from a DB, or
	// whatsoever authentication service
	private static Map<String, UserDetails> userRepository = new HashMap<String, UserDetails>();
    private static Context context;

	static {
        try {
            context = (Context) new InitialContext().lookup("java:/comp/env");
            if (isDatabaseEditable()) {
                logger.info("Database IS enabled to edit");
                importUser();
            }
            else {
                logger.info("Database is not enabled to edit");
            }
        } catch (NamingException ne) {
            logger.warn("Unable to read admin context");
        }
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails matchingUser = userRepository.get(username);

		if (matchingUser == null) {
			throw new UsernameNotFoundException("Wrong username or password");
		}

		return matchingUser;
	}

	private static boolean importUser() throws NamingException {
		boolean importedUsers = false;

        String creds = (String) context.lookup("uriResolverDatabaseCredentials");
        if (creds != null) {
            String[] arr = creds.split("\\s");
            String username = arr[0].trim();
            String password = arr[1].trim();

            logger.info("ACCOUNT: " + username + "\t" + password);
            Set<GrantedAuthority> authList = new HashSet<GrantedAuthority>();
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
            UserDetails user = new UserDetailsImpl(username, password, authList);
            userRepository.put(username, user);
            importedUsers = true;
        }

		return importedUsers;
	}

    private static boolean isDatabaseEditable() throws NamingException {
        return (boolean) context.lookup("uriResolverDatabaseEditable");
    }

}