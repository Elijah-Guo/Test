package cn.itcast.core.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 点对点模式中的消息发送方
 *
 *
 * 当消息发送了之后，对应的接收方也就是某一台tomcat会返回一个接收成功的响应，
 * 如果，某个时候，接收方并没有返回对应的响应，可能是接收服务器宕机了，这个时候
 * 点对点模式的消息发送，也就是消息数据会自动保存到硬盘中,当接收服务器能够成功运行的时候
 *
 * 消息发送方，也就是active服务器会把保存在硬盘的消息数据，重新发送至相应的接收方服务器中
 *
 */
public class QueueProducer {

    public static void main(String[] args) throws Exception{
        //1.创建连接工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.200.128:61616");
        //2.获取连接
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建队列对象, test-queue是我们自己起的发送到activeMq中队列的名称, 可以随意起名
        //但是我们将消息发送到哪个队列, 接收方在接收的时候就要从哪个队列中去接收消息
        Queue queue = session.createQueue("test-queue");
        //6.创建消息生产者
        MessageProducer producer = session.createProducer(queue);
        //7.创建消息
        TextMessage textMessage = session.createTextMessage("欢迎来到神奇的品优购世界");
        //8.发送消息
        producer.send(textMessage);
        //9.关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}
