package com.aolifu.iotdb;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.iotdb.rpc.IoTDBConnectionException;
import org.apache.iotdb.rpc.StatementExecutionException;
import org.apache.iotdb.session.Session;
import org.apache.iotdb.session.SessionDataSet;
import org.apache.iotdb.tsfile.file.metadata.enums.CompressionType;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.apache.iotdb.tsfile.file.metadata.enums.TSEncoding;
import org.apache.iotdb.tsfile.read.common.RowRecord;
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
        session.setFetchSize(1000);
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

    @Test
    public void executeQueryStatement2Test() throws IoTDBConnectionException, StatementExecutionException {
        final SessionDataSet dataSet = session.executeQueryStatement("select * from root.ln.wf01.wt01");
        // columnNames columnTypes + next()
        final List<String> names = dataSet.getColumnNames();
        final List<String> types = dataSet.getColumnTypes();
        while (dataSet.hasNext()) {
            final RowRecord rowRecord = dataSet.next();
            System.out.println(rowRecord);
        }
    }

    @Test
    public void executeRawDataQueryTest() throws IoTDBConnectionException, StatementExecutionException {
        List<String> paths = new ArrayList<>();
        paths.add("root.ln.wf01.wt01");
        final SessionDataSet set = session.executeRawDataQuery(paths, 10L, 500L);
        while (set.hasNext()) {
            final RowRecord next = set.next();
            System.out.println(next);
        }
    }

    @Test
    public void executeLastDataQueryTest() throws IoTDBConnectionException, StatementExecutionException {
        List<String> paths = new ArrayList<>();
        paths.add("root.ln.wf01.wt01");
        final SessionDataSet set = session.executeLastDataQuery(paths, 1670924805000L);
        while (set.hasNext()) {
            final RowRecord next = set.next();
            System.out.println(next);
        }
    }


}
