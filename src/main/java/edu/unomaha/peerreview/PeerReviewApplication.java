package edu.unomaha.peerreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
//public class PeerReviewApplication extends SpringBootServletInitializer {
public class PeerReviewApplication {	

//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(PeerReviewApplication.class);
//    }

	public static void main(String[] args) {
		SpringApplication.run(PeerReviewApplication.class, args);
	}
}
