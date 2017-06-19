package org.lanqiao.pay.base.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * @Title: ExcelView.java
 * @Package org.lanqiao.pay.base.util
 * @Description: Excel视图，制作报表
 * @author 西安工业大学蓝桥一期--毋泽航
 * @date 2017年5月5日 下午8:53:27
 */
@Component
public class ExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 创建一个sheet
		HSSFSheet sheet = workbook.createSheet();
		// 创建头行
		HSSFRow head = sheet.createRow(0);
		List<Object> objects = (List<Object>) model.get("beans");
		Field[] fields = null;
		if (objects.size() != 0) {
			Object object = objects.get(0);
			Class objClass = object.getClass();
			fields = objClass.getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				head.createCell(j).setCellValue(fields[j].getName());
			}
		}
		// 将数据写入表中。
		for (int i = 0; i < objects.size(); i++) {
			Object obj = objects.get(i);
			HSSFRow row = sheet.createRow(i + 1);
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				field.setAccessible(true);
				Object value = field.get(obj);
				row.createCell(j).setCellValue(value.toString());
			}
		}
		response.setHeader("Content-Disposition", "attachment;filename=" +"journal sheet"+ new Date() + ".xls");

	}

}
