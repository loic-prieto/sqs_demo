package test.chavos.consumer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

public class ConsumerMain {
	public static final String queue_url = "https://sqs.eu-west-1.amazonaws.com/932151951643/bananas.fifo";

	public static void main(String[] args) throws Throwable {
		final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

		while(true) {
			ReceiveMessageRequest request = new ReceiveMessageRequest()
			  .withQueueUrl(queue_url);

			List<Message> messages = sqs.receiveMessage(queue_url).getMessages();
			System.out.println("I've fetched "+messages.size()+" messages");

			for (Message m: messages) {
				System.out.println("Received messages: "+m.getBody());

				// ACK
				sqs.deleteMessage(queue_url,m.getReceiptHandle());
			}
		}
	}
}
