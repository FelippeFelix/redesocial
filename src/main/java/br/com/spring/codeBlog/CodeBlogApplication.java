package br.com.spring.codeBlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class CodeBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeBlogApplication.class, args);
	}

}
