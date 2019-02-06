package test.chavos.publisher;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class PublisherMain {
	public static final String queue_url = "https://sqs.eu-west-1.amazonaws.com/932151951643/bananas.fifo";

	public static void main(String[] args) throws Throwable {
		final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

		int counter =0;
		while(true) {

			SendMessageRequest send_msg_request = new SendMessageRequest()
			  .withMessageGroupId("test")
			  .withQueueUrl(queue_url)
			  .withMessageBody("hello world "+counter++);

			sqs.sendMessage(send_msg_request);
			System.out.println("I'm sending a message "+ counter);

			Thread.sleep(4000);
		}
	}
}
