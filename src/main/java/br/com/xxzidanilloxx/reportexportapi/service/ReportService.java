package br.com.xxzidanilloxx.reportexportapi.service;

import br.com.xxzidanilloxx.reportexportapi.dto.ProductResponseDTO;
import br.com.xxzidanilloxx.reportexportapi.mapper.ProductMapper;
import br.com.xxzidanilloxx.reportexportapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 15);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);

        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        return headerStyle;
    }

    private CellStyle createDataStyle(Workbook workbook) {
        CellStyle dataStyle = workbook.createCellStyle();

        dataStyle.setAlignment(HorizontalAlignment.LEFT);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);

        dataStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        Font font = workbook.createFont();
        font.setBold(false);
        font.setItalic(false);
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setFontHeightInPoints((short) 14);
        dataStyle.setFont(font);

        dataStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return dataStyle;
    }

    private void createCellStyle(Row row, int columnIndex, String value, CellStyle style) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    public InputStreamResource exportToExcel() {
        List<ProductResponseDTO> products = productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();

        String[] headers = {"Name", "Brand", "Description", "Category",
                "Stock", "Price (US$)", "Active", "Created", "Updated"};

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Report");
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowIndex = 1;
            for (ProductResponseDTO product : products) {
                Row dataRow = sheet.createRow(rowIndex++);
                createCellStyle(dataRow, 0, product.name(), dataStyle);
                createCellStyle(dataRow, 1, product.brand(), dataStyle);
                createCellStyle(dataRow, 2, product.description(), dataStyle);
                createCellStyle(dataRow, 3, product.category().name(), dataStyle);
                createCellStyle(dataRow, 4, String.valueOf(product.stockQuantity()), dataStyle);
                createCellStyle(dataRow, 5, String.valueOf(product.price().doubleValue()), dataStyle);
                createCellStyle(dataRow, 6, String.valueOf(product.isActive()), dataStyle);

                String formattedCreatedAt = product.createdAt() != null ? product.createdAt().format(formatter) : "";
                String formattedUpdatedAt = product.updatedAt() != null ? product.updatedAt().format(formatter) : "";

                createCellStyle(dataRow, 7, formattedCreatedAt, dataStyle);
                createCellStyle(dataRow, 8, formattedUpdatedAt, dataStyle);
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            sheet.createFreezePane(0, 1);

            workbook.write(out);
            return new InputStreamResource(new ByteArrayInputStream(out.toByteArray()));

        } catch (IOException e) {
            throw new RuntimeException("Error generating report", e);
        }
    }
}