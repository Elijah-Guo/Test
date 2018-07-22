package cn.itcast.core.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 发布订阅模式的消息发送方
 *
 *
 * 当发送信息后，并不管接收消息方的服务器收到与否，都会发送，而且接收方接收的成功不成功
 * 都不会发送回返信息---------->发了就是发了，不管成不成功
 *
 * 而且，就算接收方接收不到数据，数据也不会保存到硬盘上，接收不到，就是就收不到了
 *
 * 如果想要保证消息的可靠性，需要自己配置，并需要编写大量的代码实现(不是绝对的消息都会丢失在路上)
 */
public class TopicProducer {

    public static void main(String[] args)throws Exception {
        //1.创建连接工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.200.128:61616");
        //2.获取连接
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.获取session  (参数1：是否启动事务,参数2：消息确认模式)(值为1是自动确认)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建主题对象, test-topic是定义的发送到activeMq中的队列名称
        //可以随意起名
        //但是我们将消息发送到哪个队列, 接收方在接收的时候就要从哪个队列中去接收消息
        Topic topic = session.createTopic("test-topic");
        //6.创建消息生产者
        MessageProducer producer = session.createProducer(topic);
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
