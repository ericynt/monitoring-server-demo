package com.eric.monitoringserverjava.config;

import com.eric.monitoringserverjava.jobs.JobSyncer;
import com.eric.monitoringserverjava.rules.Rule;
import com.eric.monitoringserverjava.rules.RuleRepository;
import com.eric.monitoringserverjava.users.User;
import com.eric.monitoringserverjava.users.UserRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@ComponentScan("com.eric.monitoringserverjava")
@EnableReactiveMongoRepositories("com.eric.monitoringserverjava")
public class MonitoringServerJavaApplication extends AbstractReactiveMongoConfiguration {
	private static final String DB_NAME = "test";

//	@Autowired
//	private JobSyncer jobSyncer;

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
		return DB_NAME;
	}

	public static void main (String[] args) {
		SpringApplication.run(MonitoringServerJavaApplication.class, args);

	}

	@Bean
	public RestTemplate restTemplate (RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	CommandLineRunner init (RuleRepository ruleRepository, UserRepository userRepository) {
		return args -> {
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run () {
//					jobSyncer.run();
				}
			};

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, 0, 60 * 10 * 1000);

			userRepository.deleteAll().subscribe();
			userRepository.saveAll(Flux.just(
			  new User(null, "eric", "asdf", new User.Role[]{User.Role.ADMIN}, "")
			)).subscribe();

			ruleRepository.deleteAll().subscribe();
			ruleRepository.saveAll(Flux.just(
			  new Rule(null, RequestMethod.GET, HttpStatus.OK, "{}",  "rule name", 30)
			)).subscribe();
		};
	}
}
