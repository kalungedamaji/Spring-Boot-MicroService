package guru.springframework.sfgjms.recevier;

import guru.springframework.sfgjms.config.JMSConfig;
import guru.springframework.sfgjms.model.HellowWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;


@RequiredArgsConstructor
@Component
public class MessageListener {

    private final JmsTemplate  jmsTemplate;

     @JmsListener(destination= JMSConfig.MY_QUEUE)
    public void listen(@Payload HellowWorldMessage hellowWorldMessage,@Headers MessageHeaders messageHeader){
       /*  System.out.println("Get the message ...");
         System.out.println(hellowWorldMessage);
        //// throw new RuntimeException("Run time exception .....");*/

    }


    @JmsListener(destination= JMSConfig.MY_SEND_RECEIVE_QUEUE)
    public void listenForHello(@Payload HellowWorldMessage hellowWorldMessage,@Headers MessageHeaders messageHeader, Message message) throws JMSException {

        HellowWorldMessage repMessage=HellowWorldMessage.builder().uid(UUID.randomUUID()).mesasge(" Reply JMS Message").build();
        jmsTemplate.convertAndSend((Destination) message.getJMSReplyTo(),repMessage);

    }
}
