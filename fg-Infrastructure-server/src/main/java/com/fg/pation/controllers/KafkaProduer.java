package com.fg.pation.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j
public class KafkaProduer {
    @Autowired
    private final KafkaTemplate<String, String> kakfaProducer;
    @Autowired
    private final KafkaProperties kafkaProperties;

    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendProjectStatusEmail()  {
        log.info("kafka");
        kakfaProducer.send(
                "quickstart-events", "“很显然，企业客户仍然喜欢拥有集中控制、可预测、可靠的计算系统——这正是IBM专门研究的那种系统。”这是曾在1991年预言IBM大型主机之死的技术专家Stewart Alsop在2002年2月时发表的观点。IBM曾在1960年代初，投入了50亿美元开发大型主机，这相当于今天接近400亿美元的投资。1969年，人类第一位宇航员登陆月球，而在阿波罗登月项目中，IBM的大型主机System360做出了重要贡献。\n" +
                        "\n" +
                        "如果你认为源自1960年的大型主机已经成为老古董，那你就跟Stewart Alsop一样要推翻自己了。2019年初，市场咨询公司IDC与来自IBM系统部门的高管进行了交流，了解到：IBM通常以相当稳定的节奏投资于IBM Z系统的未来两代，一旦推出新一代产品，就意味着已经在下一代产品和下下代产品上投入了至少一年的时间；IBM拥有一个先进的设计团队致力于三到五年之后的技术，以及一个致力于两到三年之后技术的近端设计交付团队；这些技术将包含在下一代处理器、硬件、软件、网络选择、I/O选择和关键功能等选择中。");
    }
}
