package com.fg.drools.config;

import com.fg.drools.until.DroolsUntils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsSpringConfiguration {
    @Bean
    public DroolsUntils droolsUntils()
    {
        return new DroolsUntils();
    }


/*    @Bean
    public KieContainer getKieContainer() {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(
                ResourceFactory.newClassPathResource("VipDiscountPrice.drl")
        );
       
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
    */
}
