package com.aolifu.rocketmq.mybatis.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

public class WriteExcelTest {

    private static final String filePrefix = "/Users/wangqiang/Downloads/temp/";

    @Test
    public void testCommonExportExcel() {
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入
        String filePrifix = "/Users/wangqiang/Downloads/temp/";
        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName = filePrifix + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class)
            .sheet("模板")
            .doWrite(() -> {
                // 分页查询数据
                return data();
            });
    }

    @Test
    public void testCommonExportExcel2() {
        String filePrifix = "/Users/wangqiang/Downloads/temp/";
        String fileName = filePrifix + "simpleWrite" + System.currentTimeMillis() + ".xlsx";

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    }

    @Test
    public void testCommonExportExcel3() {
        String filePrifix = "/Users/wangqiang/Downloads/temp/";
        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName = filePrifix + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        }
    }

    private List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(LocalDateTime.now());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    @Test
    public void personalGradeExport1Test() {
        String filePrifix = "/Users/wangqiang/Downloads/temp/";
        String fileName = filePrifix + "personalGrade" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, PersonalGrade.class).sheet("模板").doWrite(buildPersonalGradeList());
    }

    private List<PersonalGrade> buildPersonalGradeList() {
        List<PersonalGrade> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            PersonalGrade data = new PersonalGrade();
            data.setRanking(i);
            data.setName("name" + i);
            data.setTeamName("teamName" + i);
            list.add(data);
        }
        return list;
    }

    @Test
    public void kingTest() throws IOException {
        KingData data = new KingData();
        data.setName("Oliver");
        data.setTeamName("jianghu");
        data.setScore(100);
        data.setLosePoint(10);
        String filePrifix = "/Users/wangqiang/Downloads/temp/";
        String fileName = filePrifix + "personalGrade" + System.currentTimeMillis() + ".xls";
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("Sheet1");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("攻守王");
        final HSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("姓名");
        row1.createCell(1).setCellValue(data.getName());

        final HSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("球队");
        row2.createCell(1).setCellValue(data.getTeamName());

        final HSSFRow row3 = sheet.createRow(3);
        row3.createCell(0).setCellValue("得分");
        row3.createCell(1).setCellValue(data.getScore());

        final HSSFRow row4 = sheet.createRow(4);
        row4.createCell(0).setCellValue("失分");
        row4.createCell(1).setCellValue(data.getLosePoint());


        FileOutputStream file = new FileOutputStream(fileName);
        hssfWorkbook.write(file);
        file.close();

    }

    @Test
    public void personalGradeAllTest() throws IOException {

        String fileName = filePrefix + "personalGrade" + System.currentTimeMillis() + ".xls";

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("成绩册");

        CellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //下边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        //左边框
        cellStyle.setBorderLeft(BorderStyle.THIN);
        //上边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        //右边框
        cellStyle.setBorderRight(BorderStyle.THIN);
        // 自动换行
        cellStyle.setWrapText(true);

        sheet.addMergedRegion(new CellRangeAddress(0, 3, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 8));
        final HSSFRow row1 = sheet.createRow(0);
        final HSSFCell row1Cell1 = row1.createCell(0);
        row1Cell1.setCellValue("序号");
        row1Cell1.setCellStyle(cellStyle);

        // 第一行第3列设置外边框
        final HSSFCell row1Cell3 = row1.createCell(2);
        row1Cell3.setCellStyle(cellStyle);

        // 第一行第4列设置外边框
        final HSSFCell row1Cell4 = row1.createCell(3);
        row1Cell4.setCellStyle(cellStyle);

        // 第一行第5列设置外边框
        final HSSFCell row1Cell5 = row1.createCell(4);
        row1Cell5.setCellStyle(cellStyle);

        // 第一行第6列设置外边框
        final HSSFCell row1Cell6 = row1.createCell(5);
        row1Cell6.setCellStyle(cellStyle);

        // 第一行第7列设置外边框
        final HSSFCell row1Cell7 = row1.createCell(6);
        row1Cell7.setCellStyle(cellStyle);

        // 第一行第8列设置外边框
        final HSSFCell row1Cell8 = row1.createCell(7);
        row1Cell8.setCellStyle(cellStyle);

        // 第一行第9列设置外边框
        final HSSFCell row1Cell9 = row1.createCell(8);
        row1Cell9.setCellStyle(cellStyle);

        final HSSFCell row1Cell = row1.createCell(1);
        row1Cell.setCellValue("成绩册");
        row1Cell.setCellStyle(cellStyle);


        // 第二行第二列 设置外边框
        final HSSFRow row2 = sheet.createRow(1);

        // 第二行第1列 设置外边框
        final HSSFCell row2Cell1 = row2.createCell(0);
        row2Cell1.setCellStyle(cellStyle);

        // 第二行第2列 设置外边框
        final HSSFCell row2Cell2 = row2.createCell(1);
        row2Cell2.setCellStyle(cellStyle);

        // 第二行第三列 设置外边框
        final HSSFCell row2Cell3 = row2.createCell(2);
        row2Cell3.setCellStyle(cellStyle);

        // 第二行第五列 设置外边框
        final HSSFCell row2Cell5 = row2.createCell(4);
        row2Cell5.setCellStyle(cellStyle);

        // 第二行第六列 设置外边框
        final HSSFCell row2Cell6 = row2.createCell(5);
        row2Cell6.setCellStyle(cellStyle);

        // 第二行第九列 设置外边框
        final HSSFCell row2Cell7 = row2.createCell(6);
        row2Cell7.setCellStyle(cellStyle);

        // 第二行第九列 设置外边框
        final HSSFCell row2Cell8 = row2.createCell(7);
        row2Cell8.setCellStyle(cellStyle);

        // 第二行第九列 设置外边框
        final HSSFCell row2Cell9 = row2.createCell(8);
        row2Cell9.setCellStyle(cellStyle);

        sheet.addMergedRegion(new CellRangeAddress(2, 3, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 8));
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 6, 8));
        final HSSFRow row3 = sheet.createRow(2);
        final HSSFCell row3Cell2 = row3.createCell(1);
        row3Cell2.setCellValue("级别");
        row3Cell2.setCellStyle(cellStyle);

        // 第3行第1列设置外边框
        final HSSFCell row3Cell1 = row3.createCell(0);
        row3Cell1.setCellStyle(cellStyle);

        // 第3行第8列设置外边框
        final HSSFCell row3Cell8 = row3.createCell(7);
        row3Cell8.setCellStyle(cellStyle);

        // 第3行第9列设置外边框
        final HSSFCell row3Cell9 = row3.createCell(8);
        row3Cell9.setCellStyle(cellStyle);

        final HSSFCell row3Cell3 = row3.createCell(3);
        row3Cell3.setCellValue("名次");
        row3Cell3.setCellStyle(cellStyle);


        final HSSFRow row4 = sheet.createRow(3);
        final HSSFCell row4Cell4 = row4.createCell(3);
        row4Cell4.setCellValue("第一名");
        row4Cell4.setCellStyle(cellStyle);

        final HSSFCell row4Cell3 = row4.createCell(2);
        row4Cell3.setCellStyle(cellStyle);

        final HSSFCell row4Cell5 = row4.createCell(4);
        row4Cell5.setCellValue("第二名");
        row4Cell5.setCellStyle(cellStyle);

        final HSSFCell row4Cell6 = row4.createCell(5);
        row4Cell6.setCellValue("第三名");
        row4Cell6.setCellStyle(cellStyle);

        final HSSFCell row4Cell7 = row4.createCell(6);
        row4Cell7.setCellValue("第五名");
        row4Cell7.setCellStyle(cellStyle);

        // 第4行第1列设置外边框
        final HSSFCell row4Cell1 = row4.createCell(0);
        row4Cell1.setCellStyle(cellStyle);

        // 第4行第8列设置外边框
        final HSSFCell row4Cell8 = row4.createCell(7);
        row4Cell8.setCellStyle(cellStyle);

        // 第4行第9列设置外边框
        final HSSFCell row4Cell9 = row4.createCell(8);
        row4Cell9.setCellStyle(cellStyle);


        final List<PersonalGradeAll> alls = buildSubGroupData();
        int row = 4;
        int seq = 1;
        for (PersonalGradeAll item : alls) {
            // 第二列和第三列合并
            sheet.addMergedRegion(new CellRangeAddress(row, row, 1, 2));
            sheet.addMergedRegion(new CellRangeAddress(row, row, 6, 8));

            // 第一列设置序号
            final HSSFRow hssfRow = sheet.createRow(row);
            final HSSFCell cell1 = hssfRow.createCell(0);
            cell1.setCellValue(seq);
            cell1.setCellStyle(cellStyle);

            // 第3列设置外边框
            final HSSFCell cell3 = hssfRow.createCell(2);
            cell3.setCellStyle(cellStyle);

            // 第9列设置外边框
            final HSSFCell cell8 = hssfRow.createCell(7);
            cell8.setCellStyle(cellStyle);

            // 第9列设置外边框
            final HSSFCell cell9 = hssfRow.createCell(8);
            cell9.setCellStyle(cellStyle);

            // 第二列设置级别
            final HSSFCell cell2 = hssfRow.createCell(1);
            cell2.setCellValue(item.getSubGroup());
            cell2.setCellStyle(cellStyle);

            // 第四列设置第一名
            final HSSFCell cell4 = hssfRow.createCell(3);
            cell4.setCellValue(formatRankingStr(item.getOneList()));
            cell4.setCellStyle(cellStyle);


            // 第五列设置第二名
            final HSSFCell cell5 = hssfRow.createCell(4);
            cell5.setCellValue(formatRankingStr(item.getTwoList()));
            cell5.setCellStyle(cellStyle);


            // 第六列设置第三名
            final HSSFCell cell6 = hssfRow.createCell(5);
            cell6.setCellValue(formatRankingStr(item.getThreeList()));
            cell6.setCellStyle(cellStyle);


            // 第七列设置第五名
            final HSSFCell cell7 = hssfRow.createCell(6);
            cell7.setCellValue(formatRankingStr(item.getFiveList()));
            cell7.setCellStyle(cellStyle);


            row++;
            seq++;
        }
        // 设置序号列 列宽和列高
        sheet.setColumnWidth(0, 25 * 80);

        sheet.setColumnWidth(3, 18 * 256);
        sheet.setColumnWidth(4, 18 * 256);
        sheet.setColumnWidth(5, 18 * 256);
        sheet.setColumnWidth(6, 18 * 256);
        FileOutputStream file = new FileOutputStream(fileName);
        hssfWorkbook.write(file);
        file.close();
    }

    private String formatRankingStr(List<PersonalGrade> list) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (PersonalGrade grade : list) {
            builder.append(grade.getName()).append("-").append(grade.getTeamName()).append("\r\n");
        }
        return builder.toString();
    }

    private List<PersonalGradeAll> buildSubGroupData(){
        List<PersonalGradeAll> list = ListUtils.newArrayList();
        for (int i = 0; i < 5; i++) {
            PersonalGradeAll data = new PersonalGradeAll();
            data.setSubGroup("级别" + i);
            List<PersonalGrade> oneList = ListUtils.newArrayList();
            for (int j = 0; j < 2; j++) {
                PersonalGrade personalGrade = new PersonalGrade();
                personalGrade.setRanking(1);
                personalGrade.setName("name" + j);
                personalGrade.setTeamName("teamName" + j);
                oneList.add(personalGrade);
            }
            data.setOneList(oneList);

            List<PersonalGrade> twoList = ListUtils.newArrayList();
            for (int j = 0; j < 2; j++) {
                PersonalGrade personalGrade = new PersonalGrade();
                personalGrade.setRanking(2);
                personalGrade.setName("name" + j);
                personalGrade.setTeamName("teamName" + j);
                twoList.add(personalGrade);
            }
            data.setTwoList(twoList);

            List<PersonalGrade> threeList = ListUtils.newArrayList();
            for (int j = 0; j < 2; j++) {
                PersonalGrade personalGrade = new PersonalGrade();
                personalGrade.setRanking(3);
                personalGrade.setName("name" + j);
                personalGrade.setTeamName("teamName" + j);
                threeList.add(personalGrade);
            }
            data.setThreeList(threeList);

            List<PersonalGrade> fiveList = ListUtils.newArrayList();
            for (int j = 0; j < 2; j++) {
                PersonalGrade personalGrade = new PersonalGrade();
                personalGrade.setRanking(3);
                personalGrade.setName("name" + j);
                personalGrade.setTeamName("teamName" + j);
                fiveList.add(personalGrade);
            }
            data.setFiveList(fiveList);

            list.add(data);
        }
        return list;
    }

    /**
     * 复杂头写入 单sheet
     * <p>1. 创建excel对应的实体对象 参照{@link ComplexHeadData}
     * <p>2. 使用{@link ExcelProperty}注解指定复杂的头
     * <p>3. 直接写即可
     */
    @Test
    public void complexHeadWriteTest() {
        String fileName = filePrefix + "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ComplexHeadData.class).sheet("模板1").doWrite(data());
    }

    /**
     * 一个sheet 多次写入
     */
    @Test
    public void complexHeadWriteTest2() {
        String fileName = filePrefix + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, ComplexHeadData.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < 5; i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<DemoData> data = data();
                excelWriter.write(data, writeSheet);
            }
        }
    }

    /**
     * 多个sheet 多次写入 同一个对象
     */
    @Test
    public void complexHeadWriteTest3() {
        String fileName = filePrefix + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 指定文件
        try (ExcelWriter excelWriter = EasyExcel.write(fileName).head(head()).build()) {
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
            for (int i = 0; i < 5; i++) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<DemoData> data = data();
                excelWriter.write(data, writeSheet);
            }
        }
    }
    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("主标题");
        head0.add("字符串" + System.currentTimeMillis());
        List<String> head1 = new ArrayList<String>();
        head1.add("主标题");
        head1.add("数字" + System.currentTimeMillis());
        List<String> head2 = new ArrayList<String>();
        head2.add("主标题");
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    /**
     * 多个sheet 多次写入 不同对象
     */
    @Test
    public void complexHeadWriteTest4() {
        String fileName = filePrefix + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 指定文件
        try (ExcelWriter excelWriter = EasyExcel.write(fileName).build()) {
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
            for (int i = 0; i < 5; i++) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class
                // 实际上可以一直变
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).head(DemoData.class).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<DemoData> data = data();
                excelWriter.write(data, writeSheet);
            }
        }
    }

    @Test
    public void test1() {
        System.out.println(System.getProperty("user.home"));
    }
}
