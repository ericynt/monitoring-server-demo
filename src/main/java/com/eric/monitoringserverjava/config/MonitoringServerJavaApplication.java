package com.eric.monitoringserverjava.config;

import com.eric.monitoringserverjava.rules.HttpRule;
import com.eric.monitoringserverjava.rules.RestRule;
import com.eric.monitoringserverjava.rules.RuleRepository;
import com.eric.monitoringserverjava.security.User;
import com.eric.monitoringserverjava.security.UserRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan("com.eric.monitoringserverjava")
@EnableReactiveMongoRepositories("com.eric.monitoringserverjava")
public class MonitoringServerJavaApplication extends AbstractReactiveMongoConfiguration {
	@Bean
	public MongoClient mongoClient () {
		return MongoClients.create();
	}

	public @Bean
	ReactiveMongoTemplate reactiveMongoTemplate () {
		return new ReactiveMongoTemplate(mongoClient(), getDatabaseName());
	}

	@Override
	protected String getDatabaseName () {
		return "test";
	}

	public static void main (String[] args) {
		SpringApplication.run(MonitoringServerJavaApplication.class, args);

	}

	@Bean
	CommandLineRunner init (RuleRepository ruleRepository, UserRepository userRepository) {
		return args -> {
			userRepository.deleteAll().subscribe();
			userRepository.saveAll(Flux.just(
			  new User(null, "eric", "asdf", Arrays.asList(User.Role.ADMIN))
			)).subscribe();
			ruleRepository.deleteAll().subscribe();

			ruleRepository.saveAll(Flux.just(
			  new RestRule(null, "rest rule name", 30, HttpStatus.OK, "{}", RequestMethod.GET),
			  new HttpRule(null, "rule", 30, HttpStatus.OK, "{}")
			)).subscribe();
		};
	}
}
