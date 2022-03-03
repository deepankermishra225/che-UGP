package com.che.scheduler.configuration;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

@Service
public class ImapConnection {

    public boolean login(String username, String password){

        Properties properties = new Properties() ;
        properties.put(String.format("mail.%s.host", "imap"), "qasid.iitk.ac.in") ;
        properties.put(String.format("mail.%s.port", "imap"), "143") ;

        Session session = Session.getDefaultInstance(properties);

        try {
            Store store = session.getStore("imap");
            store.connect(username, password);
            store.close() ;
            return true ;
        } catch (MessagingException e) {
               return false ;
        }


    }
}
