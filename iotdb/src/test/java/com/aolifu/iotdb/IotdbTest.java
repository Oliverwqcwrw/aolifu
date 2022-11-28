package com.aolifu.iotdb;

import java.util.ArrayList;
import java.util.List;
import org.apache.iotdb.rpc.IoTDBConnectionException;
import org.apache.iotdb.rpc.StatementExecutionException;
import org.apache.iotdb.session.Session;
import org.apache.iotdb.session.SessionDataSet;
import org.apache.iotdb.tsfile.file.metadata.enums.CompressionType;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.apache.iotdb.tsfile.file.metadata.enums.TSEncoding;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class IotdbTest {

    private static Session session;

    @BeforeAll
    public static void before() throws IoTDBConnectionException {
        session =
            new Session.Builder()
                .host("localhost")
                .port(6667)
        .build();
        session.open();
    }

    @AfterAll
    public static void after() throws IoTDBConnectionException {
        session.close();
    }

    @Test
    public void createDatabaseTest() throws IoTDBConnectionException, StatementExecutionException {
        session.setStorageGroup("root.ln");
    }

    @Test
    public void deleteStorageGroupTest() throws IoTDBConnectionException, StatementExecutionException {
        session.deleteStorageGroup("root.ln");
    }

    @Test
    public void createTimeseriesTest() throws IoTDBConnectionException, StatementExecutionException {
        session.createTimeseries("root.ln.wf01.wt01.status", TSDataType.BOOLEAN, TSEncoding.PLAIN, CompressionType.UNCOMPRESSED);
        session.createTimeseries("root.ln.wf01.wt01.temperature", TSDataType.FLOAT, TSEncoding.RLE, CompressionType.UNCOMPRESSED);
    }

    @Test
    public void deleteTimeseriesTest() throws IoTDBConnectionException, StatementExecutionException {
        List<String> pathList = new ArrayList<>();
        pathList.add("root.ln.wf01.wt01.status");
        pathList.add("root.ln.wf01.wt01.temperature");
        session.deleteTimeseries(pathList);
    }

    @Test
    public void checkTimeseriesExistsTest() throws IoTDBConnectionException, StatementExecutionException {
        final boolean exist = session.checkTimeseriesExists("root.ln.wf01.wt01.status");
        Assertions.assertTrue(exist);
    }

    @Test
    public void executeQueryStatementTest() throws IoTDBConnectionException, StatementExecutionException {
        final SessionDataSet timeseries = session.executeQueryStatement("SHOW TIMESERIES");
        while (timeseries.hasNext()) {
            System.out.println(timeseries.next());
        }
    }
}
