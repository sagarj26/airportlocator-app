/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.util;

import com.saga.airportlocator.model.AirportInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author sjadhav
 */
@Component
public class AirportExcelDataReader implements AirportDataReader {

    List<AirportInfo> airportsInfo;

    @Value("classpath:Airport_details_1.xlsx")
    private Resource res;

    @PostConstruct
    public void init() throws IOException {
        URL resource = res.getURL();
        String fileName = resource.getFile();
        try (FileInputStream inputStream = new FileInputStream(new File(resource.getFile()));
                Workbook workbook = getWorkbook(inputStream, fileName)) {

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();
            Row headerRow = null;
            airportsInfo = new ArrayList<>();
            while (iterator.hasNext()) {
                if (headerRow == null) {
                    headerRow = iterator.next();
                }
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                AirportInfo airport = new AirportInfo();

                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();

                    switch (columnIndex) {
                        case 0:
                            airport.setId(Double.valueOf(getCellValue(nextCell).toString()).intValue());
                            break;
                        case 1:
                            airport.setIdent(getCellValue(nextCell).toString());
                            break;
                        case 2:
                            airport.setType(getCellValue(nextCell).toString());
                            break;
                        case 3:
                            airport.setName(getCellValue(nextCell).toString());
                            break;
                        case 4:
                            airport.setLatitude(Double.parseDouble(getCellValue(nextCell).toString()));
                            break;
                        case 5:
                            airport.setLongitude(Double.parseDouble(getCellValue(nextCell).toString()));
                            break;
                        case 6:
                            airport.setCountryCode(getCellValue(nextCell).toString());
                            break;
                        case 7:
                            airport.setCity(getCellValue(nextCell).toString());
                            break;
                    }

                }
                airportsInfo.add(airport);
            }

        }

    }

    @Override
    public List<AirportInfo> fetchAirPortsInfo() throws Exception {
        return airportsInfo;
    }

    private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING: {
                return cell.getStringCellValue();
            }
            case BOOLEAN: {
                return cell.getBooleanCellValue();
            }
            case NUMERIC: {
                return cell.getNumericCellValue();
            }
        }
        return null;
    }
}
