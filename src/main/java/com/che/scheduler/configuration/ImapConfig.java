package com.che.scheduler.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.mail.support.DefaultMailHeaderMapper;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class ImapConfig {


    private final IntegrationFlowContext flowContext;

    @Autowired
    public ImapConfig(IntegrationFlowContext flowContext) {
        this.flowContext = flowContext;
    }

    private String imapUrl(String user, String pwd) {
        return "imap://"
                + user + ":" + pwd
                + "@qasid.iitk.ac.in:143/INBOX";
    }

    public String connect(String user, String pwd) {


            IntegrationFlow flow = IntegrationFlows
                    .from(Mail.imapIdleAdapter(imapUrl(user, pwd))
                            .javaMailProperties(p -> p.put("mail.debug", "false"))
                            .userFlag("testSIUserFlag") // needed by the SI test server - not needed if server supports /SEEN
                            .headerMapper(new DefaultMailHeaderMapper()))
                    .handle(System.out::println)
                    .get();

        this.flowContext.registration(flow).register();
        return "Connected" ;


    }

}
