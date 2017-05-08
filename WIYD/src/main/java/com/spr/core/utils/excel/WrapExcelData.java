package com.spr.core.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;

public interface WrapExcelData<T> {

	/**
	 * 编写 数据写入到excel表格中每一行的规律，例如
	 *  row.createCell(0).setCellValue(obj.getSort());				
		row.createCell(6).setCellValue(DateUtil.Date2Str(obj.getLastOperatorTime()));
		row.createCell(7).setCellValue(obj.getAuditText());
	 * @param obj
	 * @param row
	 */
	void wrapData(T obj, HSSFRow row);
	
	
	
}
