package com.wytlab.hbase;

import org.apache.commons.collections.CollectionUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HbaseUtil {
    HBaseAdmin admin = null;
    Connection connection  = null;
    Configuration conf = null;

    private HbaseUtil()   {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.31.25:2181");
        try {
            connection = ConnectionFactory.createConnection(conf);
            admin = (HBaseAdmin) connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static HbaseUtil instance = null;
    public static synchronized HbaseUtil getInstance(){
        if (instance == null){
            instance = new HbaseUtil();
        }
        return instance;
    }

    public void addRowData(String tableName, String rowKey,
                                  String columnFamily, String
                                          column, String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
//向表中插入数据
        Put put = new Put(Bytes.toBytes(rowKey));
//向 Put 对象中组装数据
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column),
                Bytes.toBytes(value));
        table.put(put);
        table.close();
        System.out.println("插入数据成功");
    }

    public void deleteMultiRow(String tableName, String... rows)
            throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        List<Delete> deleteList = new ArrayList<Delete>();
        for (String row : rows) {
            Delete delete = new Delete(Bytes.toBytes(row));
            deleteList.add(delete);
        }
        table.delete(deleteList);
        table.close();
    }

    public void getAllRows(String tableName) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
//得到用于扫描 region 的对象
        Scan scan = new Scan();
//使用 HTable 得到 resultcanner 实现类的对象
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
//得到 rowkey
                System.out.println(" 行 键 :" +
                        Bytes.toString(CellUtil.cloneRow(cell)));
//得到列族
                System.out.println(" 列 族 " +
                        Bytes.toString(CellUtil.cloneFamily(cell)));
                System.out.println(" 列 :" +
                        Bytes.toString(CellUtil.cloneQualifier(cell)));
                System.out.println(" 值 :" +
                        Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
    }

    public Map<String, String> getRow(String tableName, String rowKey) throws
            IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
//get.setMaxVersions();显示所有版本
        //get.setTimeStamp();显示指定时间戳的版本
        Result result = table.get(get);
        List<Cell> ceList = result.listCells();
        return getStringObjectMap(ceList);
    }

    public void getRowQualifier(String tableName, String rowKey,
                                       String family, String
                                               qualifier) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(family),
                Bytes.toBytes(qualifier));
        Result result = table.get(get);
        for (Cell cell : result.rawCells()) {
            System.out.println(" 行 键 :" +
                    Bytes.toString(result.getRow()));
            System.out.println(" 列 族 " +
                    Bytes.toString(CellUtil.cloneFamily(cell)));
            System.out.println(" 列 :" +
                    Bytes.toString(CellUtil.cloneQualifier(cell)));
            System.out.println(" 值 :" +
                    Bytes.toString(CellUtil.cloneValue(cell)));
        }
    }

    private Map<String, String> getStringObjectMap(List<Cell> ceList) {

        if (CollectionUtils.isEmpty(ceList)) {
            return Collections.emptyMap();
        }

        Map<String, String> map = new ConcurrentHashMap<>(ceList.size());
        if (!ceList.isEmpty()) {
            for (Cell cell : ceList) {
                String key = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                map.put(key, Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            }
        }
        return map;
    }

    public static void main(String[] args) throws IOException {
        HbaseUtil hbaseUtil = HbaseUtil.getInstance();
        hbaseUtil.addRowData("student",
                "1003",
                "info",
                "aihao",
                "唱歌");
        hbaseUtil.getRow("student", "1003");
    }
}
