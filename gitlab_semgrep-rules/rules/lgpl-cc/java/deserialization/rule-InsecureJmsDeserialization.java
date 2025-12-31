// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/java/lang/security/insecure-jms-deserialization.java

import java.util.Date;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import org.apache.log4j.Logger;
import com.rands.couponproject.jpa.Income;

/**
 * Message-Driven Bean implementation class for: IncomeConsumerBean
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/MyQueue")
})
class IncomeConsumerBean implements MessageListener {

    static Logger logger = Logger.getLogger(IncomeConsumerBean.class);

    @EJB
    IncomeServiceBean isb;

    public IncomeConsumerBean() {
        // your implementation
    }

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                logger.info("onMessage received a TextMessage at " + new Date());
                TextMessage msg = (TextMessage) message;
                logger.warn("onMessage ignoring TextMessage : " + msg.getText());
            } else if (message instanceof ObjectMessage) {
                logger.info("onMessage received an ObjectMessage at " + new Date());

                ObjectMessage msg = (ObjectMessage) message;

                // ruleid: java_deserialization_rule-InsecureJmsDeserialization
                Object o = msg.getObject(); // variant 1 : calling getObject method directly on an ObjectMessage object
                logger.info("o=" + o);

                // ruleid: java_deserialization_rule-InsecureJmsDeserialization
                Income income = (Income) msg.getObject(); // variant 2 : calling getObject method and casting to a custom class
                logger.info("Message is : " + income);

                isb.StoreIncome(income);
            } else {
                logger.error("onMessage received an invalid message type");
            }

        } catch (JMSException e) {
            logger.error("onMessage failed : " + e.toString());
        }
    }

}

/**
 * Safe implementation
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/MyQueue")
})
class SafeIncomeConsumerBean implements MessageListener {

    static Logger logger = Logger.getLogger(IncomeConsumerBean.class);

    @EJB
    IncomeServiceBean isb;

    public SafeIncomeConsumerBean() {
        // your implementation
    }

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                logger.info("onMessage received a TextMessage at " + new Date());
                TextMessage msg = (TextMessage) message;
                logger.warn("onMessage ignoring TextMessage : " + msg.getText());
            } else if (message instanceof ObjectMessage) {
                logger.info("onMessage received an ObjectMessage at " + new Date());

                ObjectMessage msg = (ObjectMessage) message;

                // Use custom LookAheadObjectInputStream to safely deserialize the object
                InputStream is = msg.getObjectStream();
                LookAheadObjectInputStream laois = new LookAheadObjectInputStream(is);
                // ok: java_deserialization_rule-InsecureJmsDeserialization
                Object o = laois.readObject();
                logger.info("Deserialized object: " + o);

                // Ensure that the deserialized object is of type Income
                if (o instanceof Income) {
                    Income income = (Income) o;
                    logger.info("Message is : " + income);
                    isb.StoreIncome(income);
                } else {
                    logger.error("Received object is not of type Income");
                }

                isb.StoreIncome(income);
            } else {
                logger.error("onMessage received an invalid message type");
            }

        } catch (JMSException e) {
            logger.error("onMessage failed : " + e.toString());
        }
    }

}

class LookAheadObjectInputStream extends ObjectInputStream {
    public LookAheadObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        if (!desc.getName().equals(Income.class.getName())) {
            throw new InvalidClassException("Unauthorized deserialization attempt", desc.getName());
        }
        return super.resolveClass(desc);
    }
}