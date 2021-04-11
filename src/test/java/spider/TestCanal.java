package spider;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * canal記錄mysql的操作記錄(為實現數據備份)
 * @author Ablett_Chen
 *
 */
public class TestCanal {

    private static Queue<String> SQL_QUEUE = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        //获取canalServer连接：本机地址,端口号
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1",
                11111), "example", "", "");
        int batchSize = 1000;
        try {
            //连接canalServer
            connector.connect();
            //订阅Desctinstion
            connector.subscribe();
            connector.rollback();
            try {
                while (true) {
                    //尝试从master那边拉去数据batchSize条记录，有多少取多少
                    //轮询拉取数据   上面的where
                    Message message = connector.getWithoutAck(batchSize);
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    if (size == 0) {
                        //睡眠
                        Thread.sleep(1000);
                        System.out.println("aa"+size);
                    } else {
                        dataHandle(message.getEntries());
                    }
                    connector.ack(batchId);
//                    System.out.println("aa"+size);
                    //当队列里面堆积的sql大于一定数值的时候就模拟执行
                        executeQueueSql();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        } finally {
            connector.disconnect();
        }


    }




    /**
     * 模拟执行队列里面的sql语句
     */
    public static void executeQueueSql() {
        int size = SQL_QUEUE.size();
        for (int i = 0; i < size; i++) {
            String sql = SQL_QUEUE.poll();
            System.out.println("[sql]----> " + sql);
        }
    }

    /**
     * 数据处理
     *
     * @param entrys
     */
    private static void dataHandle(List<CanalEntry.Entry> entrys) throws InvalidProtocolBufferException {
        for (CanalEntry.Entry entry : entrys) {
            if (EntryType.ROWDATA == entry.getEntryType()) {
                RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());
                CanalEntry.EventType eventType = rowChange.getEventType();
                if (eventType == EventType.DELETE) {
                	SQL_QUEUE.add(rowChange.getSql());
                } else if (eventType == EventType.UPDATE) {
                	SQL_QUEUE.add(rowChange.getSql());
                } else if (eventType == CanalEntry.EventType.INSERT) {
                	SQL_QUEUE.add(rowChange.getSql());
                }
            }
        }
    }

}