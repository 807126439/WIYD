package com.wb.web.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.core.utils.excel.ExcelUtils;
import com.wb.core.utils.excel.ExcelUtils.TitleSet;
import com.wb.core.utils.excel.WrapExcelData;
import com.wb.web.system.dao.ILoginLogDao;
import com.wb.web.system.dto.log.LoginCountDTO;
import com.wb.web.system.dto.log.LoginLogDTO;
import com.wb.web.system.dto.log.LoginLogQueryDTO;
import com.wb.web.system.entity.LoginLog;
import com.wb.web.system.service.ILoginLogService;
import com.wb.web.system.service.IZonePathService;

@Service("loginLogService")
@Transactional
public class LoginLogServiceImpl extends BaseService implements
		ILoginLogService {
	@Resource
	private ILoginLogDao loginLogDao;
	@Resource
	private IZonePathService zonePathService;

	/**
	 * 保存日志
	 * 
	 * @param dto
	 */
	public void saveLog(LoginLogDTO dto) {
		LoginLog log = new LoginLog();
		this.getMapper().map(dto, log);

		this.loginLogDao.save(log);
	}

	/**
	 * 分页统计登录次数
	 */
	public Page<LoginCountDTO> countLoginFrequencyByPage(
			LoginLogQueryDTO queryDTO) {
		return this.loginLogDao.countLoginFrequencyByPage(queryDTO);
	}

	/**
	 * 分页
	 * 
	 * @param queryDTO
	 * @return
	 */
	public Page<LoginLogDTO> searchLoginLogByPage(LoginLogQueryDTO queryDTO) {

		return this.loginLogDao.searchLoginLogByPage(queryDTO);
	}

	/**
	 * 删除日志
	 * 
	 * @param ids
	 */
	public void deleteLogs(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			LoginLog log = this.loginLogDao.getById(ids[i]);
			if (log != null) {
				this.loginLogDao.delete(log);
			}

			if (i % 20 == 0) {
				this.loginLogDao.flush();
				this.loginLogDao.clear();
			}
		}
	}

	@Override
	public Long searchCountsAfterDate(Date Date) {

		return this.loginLogDao.searchCountsAfterDate(Date);
	}

	/**
	 * 导出excel
	 */
	public DownLoadDTO exportExcel() {
		// 整理导出数据
		List<LoginCountDTO> list = this.getAllForExport();
		Assert.notEmpty(list, "无导出的数据！");
		// 导出文件名
		String fileName = "登录次数统计表";
		// 导出文件表头
		TitleSet[] titleArray = new TitleSet[] { new TitleSet("序号", 30),
				new TitleSet("用户名", 30), new TitleSet("登录次数", 30),
				new TitleSet("最后一次登录时间", 30) };
		// 整理excel格式
		WrapExcelData<LoginCountDTO> wrapExcelData = new WrapExcelData<LoginCountDTO>() {
			public void wrapData(LoginCountDTO obj, HSSFRow row) {
				row.createCell(0).setCellValue(obj.getSort());
				row.createCell(1).setCellValue(obj.getUsername());
				row.createCell(2).setCellValue(obj.getNum());
				row.createCell(3).setCellValue(
						null != obj.getLastOperatorTime() ? obj
								.getLastOperatorTime().toString() : "");
			}
		};
		// 整理文件信息
		String savePath = this.zonePathService.getTempFileWholePath("xls");
		ExcelUtils<LoginCountDTO> excelUtils = new ExcelUtils<LoginCountDTO>(
				titleArray, savePath, list, wrapExcelData);
		if (excelUtils.generateExecl()) {
			return new DownLoadDTO(fileName + ".xls", savePath);
		} else {
			throw new MyException("导出失败！");
		}
	}

	// 整理导出数据
	private List<LoginCountDTO> getAllForExport() {
		return this.loginLogDao.findAllForExport();
	}
}
