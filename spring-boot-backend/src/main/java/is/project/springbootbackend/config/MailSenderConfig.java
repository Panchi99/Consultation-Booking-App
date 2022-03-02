package is.project.springbootbackend.config;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@Component
public class MailSenderConfig {

    private final MailConfig mailConfig;

    private final List<JavaMailSenderImpl> senderList;

    public MailSenderConfig(MailConfig mailConfig, List<JavaMailSenderImpl> senderList) {
        this.mailConfig = mailConfig;
        this.senderList = senderList;
    }

    //@PostConstruct
    public void buildMailSender(){
        List<MailConfig.MailProperties> mailConfigs = mailConfig.getConfigs();

           mailConfigs.forEach(mailProperties -> {

            //Mail sender
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setDefaultEncoding(mailProperties.getDefaultEncoding());
            javaMailSender.setHost(mailProperties.getHost());
            javaMailSender.setPort(mailProperties.getPort());
            javaMailSender.setProtocol(mailProperties.getProtocol());
            javaMailSender.setUsername(mailProperties.getUsername());
            javaMailSender.setPassword(mailProperties.getPassword());

            //Add data
            senderList.add(javaMailSender);
        });
    }


    public JavaMailSenderImpl getSender(){
        if(senderList.isEmpty()){
            buildMailSender();
        }
        //A javamailsender is returned randomly
        return senderList.get(new Random().nextInt(senderList.size()));
    }

    public void clear(){
        senderList.clear();
    }

}
