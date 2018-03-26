package com.eric.monitoringserverjava.dashboard;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@RequestMapping("api/rest-ruleresults")
@RestController
public class RuleResultController {
    private RuleResultService ruleResultService;

    @Autowired
    public RuleResultController (RuleResultService ruleResultService) {
        this.ruleResultService = ruleResultService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
    @RequestMapping(method = RequestMethod.GET)
    Flux<RuleResult> getAllRuleResults () {
        return ruleResultService.getAllRuleResults();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Mono<RuleResult> getRuleResultById (@PathVariable Publisher<String> id) {
        return ruleResultService.getRuleResultById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(method = RequestMethod.POST)
    Mono<ResponseEntity<RuleResult>> createRuleResult (@RequestBody RuleResult ruleResult) {
        return ruleResultService.createRuleResult(ruleResult)
                                .map(
                                        (rr) -> new ResponseEntity<>(rr, HttpStatus.CREATED)
                                );
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(method = RequestMethod.PUT)
    Mono<ResponseEntity<RuleResult>> updateEndpointConfig (@RequestBody RuleResult ruleResult) {
        return ruleResultService.updateRuleResult(ruleResult)
                                .map(
                                        (rec) -> new ResponseEntity<>(rec, HttpStatus.OK)
                                )
                                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT)
                                                              .build());
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(method = RequestMethod.DELETE)
    Mono<Void> deleteRuleResult (@RequestBody RuleResult ruleResult) {
        return ruleResultService.deleteRuleResult(ruleResult);
    }
}
