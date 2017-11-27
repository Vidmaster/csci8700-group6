package edu.unomaha.peerreview.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @RequestMapping("/user")
    public Principal user(Principal principal) {
    	logger.debug("user(): principal=" + principal);
        return principal;
    }
    
}