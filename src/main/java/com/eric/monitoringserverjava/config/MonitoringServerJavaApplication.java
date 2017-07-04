package com.eric.monitoringserverjava.config;

import com.eric.monitoringserverjava.rules.RestRule;
import com.eric.monitoringserverjava.rules.RestRuleRepository;
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
import reactor.core.publisher.Mono;

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
	CommandLineRunner init (RestRuleRepository restRuleRepository) {
		return args -> {
			Mono<RestRule> body = restRuleRepository.save(new RestRule("rule name", 30, HttpStatus.OK, "body", RequestMethod.GET));
			body.block();
		};
	}
}
