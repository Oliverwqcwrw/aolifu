package com.aolifu.rocketmq.mybatis.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

public class WriteExcelTest {

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
        String filePrifix = "/Users/wangqiang/Downloads/temp/";
        String fileName = filePrifix + "personalGrade" + System.currentTimeMillis() + ".xls";

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("Sheet1");

        CellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 自动换行
        cellStyle.setWrapText(true);

        sheet.addMergedRegion(new CellRangeAddress(0, 3, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 8));
        final HSSFRow row1 = sheet.createRow(0);
        final HSSFCell row0Cell = row1.createCell(0);
        row0Cell.setCellValue("序号");
        row0Cell.setCellStyle(cellStyle);
        final HSSFCell row1Cell = row1.createCell(1);
        row1Cell.setCellValue("成绩册");
        row1Cell.setCellStyle(cellStyle);

        sheet.addMergedRegion(new CellRangeAddress(2, 3, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 8));
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 6, 8));
        final HSSFRow row3 = sheet.createRow(2);
        final HSSFCell row3Cell1 = row3.createCell(1);
        row3Cell1.setCellValue("级别");
        row3Cell1.setCellStyle(cellStyle);

        final HSSFCell row3Cell3 = row3.createCell(3);
        row3Cell3.setCellValue("名次");
        row3Cell3.setCellStyle(cellStyle);

        final HSSFRow row4 = sheet.createRow(3);
        final HSSFCell row4Cell3 = row4.createCell(3);
        row4Cell3.setCellValue("第一名");
        row4Cell3.setCellStyle(cellStyle);

        final HSSFCell row4Cell4 = row4.createCell(4);
        row4Cell4.setCellValue("第二名");
        row4Cell4.setCellStyle(cellStyle);

        final HSSFCell row4Cell5 = row4.createCell(5);
        row4Cell5.setCellValue("第三名");
        row4Cell5.setCellStyle(cellStyle);

        final HSSFCell row4Cell6 = row4.createCell(6);
        row4Cell6.setCellValue("第五名");
        row4Cell6.setCellStyle(cellStyle);

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

            // 第二列设置级别
            final HSSFCell cell2 = hssfRow.createCell(1);
            cell2.setCellValue(item.getSubGroup());
            cell2.setCellStyle(cellStyle);

            // 第四列设置第一名
            final HSSFCell cell4 = hssfRow.createCell(3);
            cell4.setCellValue(formatRankingStr(item.getOneList()));
            cell4.setCellStyle(cellStyle);
            sheet.setColumnWidth(3, 18 * 256);

            // 第五列设置第二名
            final HSSFCell cell5 = hssfRow.createCell(4);
            cell5.setCellValue(formatRankingStr(item.getTwoList()));
            cell5.setCellStyle(cellStyle);
            sheet.setColumnWidth(4, 18 * 256);

            // 第六列设置第三名
            final HSSFCell cell6 = hssfRow.createCell(5);
            cell6.setCellValue(formatRankingStr(item.getThreeList()));
            cell6.setCellStyle(cellStyle);
            sheet.setColumnWidth(5, 18 * 256);

            // 第七列设置第五名
            final HSSFCell cell7 = hssfRow.createCell(6);
            cell7.setCellValue(formatRankingStr(item.getFiveList()));
            cell7.setCellStyle(cellStyle);
            sheet.setColumnWidth(6, 18 * 256);

            row++;
            seq++;
        }

        sheet.autoSizeColumn(0);

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

}
