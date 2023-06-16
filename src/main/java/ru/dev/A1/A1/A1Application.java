package ru.dev.A1.A1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.dev.A1.A1.data.UserRepository;
import ru.dev.A1.A1.models.User;

@SpringBootApplication
public class A1Application implements ApplicationContextAware {
	@Autowired
	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}
	public static void main(String[] args) {
		SpringApplication.run(A1Application.class, args);
	}
	@Bean
	public CommandLineRunner dataLoader(UserRepository repo, PasswordEncoder encoder){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repo.save(new User(Long.valueOf(1L),"user",encoder.encode("1"),"",
						"","","","",""));
			}
		};
	}


}
