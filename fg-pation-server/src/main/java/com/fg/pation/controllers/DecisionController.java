package com.fg.pation.controllers;

import com.fg.pation.domain.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DecisionController {
    private final KieContainer kieContainer;

    public DecisionController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @PostMapping("/viprecharge")
    private RechargeOrderRequest getDiscountPercent(@RequestBody RechargeOrderRequest rechargeOrderRequest) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(rechargeOrderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();

        return rechargeOrderRequest;
    }
}
