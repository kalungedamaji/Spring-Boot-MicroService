package guru.springframework.sfgjms.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.sfgjms.config.JMSConfig;
import guru.springframework.sfgjms.model.HellowWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class sender {
  private  final JmsTemplate  jmsTemplate;
  private final ObjectMapper objectMapper;

     @Scheduled(fixedRate = 2000)
    public void sendMessage(){

         HellowWorldMessage message=HellowWorldMessage.builder().uid(UUID.randomUUID()).mesasge(" JMSMessage").build();
         jmsTemplate.convertAndSend(JMSConfig.MY_QUEUE,message);
         System.out.println("Message Has been sent");

    }

    @Scheduled(fixedRate = 2000)
    public void sendReciveMessage() throws JMSException {

        HellowWorldMessage message=HellowWorldMessage.builder().uid(UUID.randomUUID()).mesasge(" JMS Send receive Message").build();
        Message reciveMessage=jmsTemplate.sendAndReceive(JMSConfig.MY_SEND_RECEIVE_QUEUE,new MessageCreator() {
            @SneakyThrows
            @Override
            public Message createMessage(Session session) throws JMSException {
               Message txtMessage= session.createTextMessage(objectMapper.writeValueAsString(message));
                txtMessage.setStringProperty("_type","guru.springframework.sfgjms.model.HellowWorldMessage");
                 System.out.println("Send Hellow");
                     return txtMessage;
            }
        });
        System.out.println("Message Has been sent");

        System.out.println(reciveMessage.getBody(String.class));

    }



}
