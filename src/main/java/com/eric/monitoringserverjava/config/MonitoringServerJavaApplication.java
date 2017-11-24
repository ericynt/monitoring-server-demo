package com.eric.monitoringserverjava.config;

import com.eric.monitoringserverjava.endpoints.EndpointConfig;
import com.eric.monitoringserverjava.endpoints.EndpointRepository;
import com.eric.monitoringserverjava.jobs.Job;
import com.eric.monitoringserverjava.jobs.JobRepository;
import com.eric.monitoringserverjava.rules.Rule;
import com.eric.monitoringserverjava.rules.RuleRepository;
import com.eric.monitoringserverjava.users.User;
import com.eric.monitoringserverjava.users.UserRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
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

import java.util.Collections;

@SpringBootApplication
@ComponentScan("com.eric.monitoringserverjava")
@EnableReactiveMongoRepositories("com.eric.monitoringserverjava")
public class MonitoringServerJavaApplication extends AbstractReactiveMongoConfiguration {
	private static final String DB_NAME = "test";

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
	CommandLineRunner init (RuleRepository ruleRepository, UserRepository userRepository, JobRepository jobRepository, EndpointRepository endpointRepository) {
		return args -> {
			ruleRepository.deleteAll().subscribe();
			Rule rule = new Rule(
			  null,
			  RequestMethod.GET,
			  HttpStatus.OK,
			  "{\"bronhouders\":[{\"bronhouderType\":\"GEMEENTE\",\"objectId\":\"0351\",\"naam\":\"Woudenberg\",\"hasLevenscyclus\":false}]}",
			  "Check municipality",
			  30
			);

			endpointRepository.deleteAll().subscribe();
			EndpointConfig endpointConfig = new EndpointConfig(
			  null,
			  "my-config",
			  EndpointConfig.EndpointType.REST,
			  EndpointConfig.Protocol.HTTPS,
			  "bagviewer.kadaster.nl",
			  443,
			  "/lvbag/bag-viewer/api/getGemeenteByCoordinates/160000.000/455000.000"
			);

			userRepository.deleteAll().subscribe();
			userRepository.saveAll(Flux.just(
			  new User(null, "eric", "asdf", new User.Role[]{User.Role.ADMIN}, "")
			)).subscribe();
			userRepository.findByName("eric").subscribe(u -> {
				jobRepository.deleteAll().subscribe();
				jobRepository.saveAll(Flux.just(
				  new Job(null, Collections.singletonList(rule), endpointConfig, 2, true, u)
				)).subscribe();
			});
		};
	}
}
