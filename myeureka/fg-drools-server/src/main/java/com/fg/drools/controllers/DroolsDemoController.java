package com.fg.drools.controllers;

import com.fg.drools.domain.Product;
import com.fg.drools.domain.VipDiscountPrice;
import com.fg.drools.until.DroolsUntils;
import org.kie.api.KieServices;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.io.ResourceFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DroolsDemoController {
    private DroolsUntils droolsUntils;
    public DroolsDemoController(DroolsUntils droolsUntils) {
        this.droolsUntils = droolsUntils;
    }

    /**
     * 通过drl规则文件方式
     * @return
     */
    @GetMapping("/vipdiscountprice")
    public ResponseEntity<VipDiscountPrice> getDiscountPercent() {
        Product product = new Product();
        product.setProductType(Product.ProductType.General);
        product.setRetailPrice(100d);
        product.setProductName("手机");
        VipDiscountPrice vipDiscountPrice = new VipDiscountPrice();
        vipDiscountPrice.setVipLevel(VipDiscountPrice.VipLevel.Platinum);
        vipDiscountPrice.setProduct(product);

       Resource resource = ResourceFactory.newClassPathResource("VipDiscountPrice.drl");
        KieSession  kieSession = droolsUntils.getKieSession(resource);
        kieSession.insert(vipDiscountPrice);
        kieSession.fireAllRules();
        kieSession.dispose();
        return ResponseEntity.ok(vipDiscountPrice);
    }

    /**
     * 通过Excel文件的方式配置规则
     * 读取方式1
     * @return
     */
    @GetMapping("/vipdiscountpricebyxls_1")
    public  ResponseEntity<VipDiscountPrice> get()
    {
        Product product = new Product();
        product.setProductType(Product.ProductType.General);
        product.setRetailPrice(100d);
        product.setProductName("手机");
        VipDiscountPrice vipDiscountPrice = new VipDiscountPrice();
        vipDiscountPrice.setVipLevel(VipDiscountPrice.VipLevel.Platinum);
        vipDiscountPrice.setProduct(product);

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("ksession-rule");
        vipDiscountPrice.setPayPrice(99999);
        FactHandle fact1=   kieSession.insert(vipDiscountPrice);
        kieSession.fireAllRules();
        kieSession.dispose();

        return ResponseEntity.ok(vipDiscountPrice);
    }
    /**
     * 通过Excel文件的方式配置规则
     * 读取方式2
     * @return
     */
    @GetMapping("/vipdiscountpricebyxls_2")
    public  ResponseEntity<VipDiscountPrice> get1()
    {
        Product product = new Product();
        product.setProductType(Product.ProductType.Brand);
        product.setRetailPrice(100d);
        product.setProductName("手机");
        VipDiscountPrice vipDiscountPrice = new VipDiscountPrice();
        vipDiscountPrice.setVipLevel(VipDiscountPrice.VipLevel.Platinum);
        vipDiscountPrice.setProduct(product);
        Resource resource = ResourceFactory
                .newClassPathResource("rules/rules.xls");
        KieSession  kieSession  = droolsUntils.getKieSession(resource);
        FactHandle fact1=   kieSession.insert(vipDiscountPrice);
        kieSession.fireAllRules();
        kieSession.dispose();
        kieSession.destroy();
        return ResponseEntity.ok(vipDiscountPrice);
    }
}
