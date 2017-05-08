package com.wb.web.study.service.impl;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.wb.web.base.service.IBaseFileService;
import com.wb.web.study.dao.IExamScoreDao;
import com.wb.web.study.dao.IStudyTaskDao;
import com.wb.web.study.dto.examScore.ExamScoreDTO;
import com.wb.web.study.dto.examScore.ExamScoreQueryDTO;
import com.wb.web.study.dto.examScore.ExaminationCountDTO;
import com.wb.web.study.dto.examScore.ExaminationCountQueryDTO;
import com.wb.web.study.entity.ExamScore;
import com.wb.web.study.entity.StudyTask;
import com.wb.web.study.service.IExamScoreService;
import com.wb.web.system.dao.IDepartmentDao;
import com.wb.web.system.dto.department.DepartmentDTO3;
import com.wb.web.system.entity.User;
import com.wb.web.system.service.IBaseDictService;
import com.wb.web.system.service.IZonePathService;

@Service("examScoreService")
@Transactional
public class ExamScoreServiceImpl extends BaseService implements
		IExamScoreService {

	@Resource
	private IExamScoreDao examScoreDao;
	@Resource
	private IStudyTaskDao studyTaksDao;
	@Resource
	private IDepartmentDao depatDao;
	@Resource
	private IBaseDictService baseDictService;
	@Resource
	private IBaseFileService baseFileService;
	@Resource
	private IZonePathService zonePathService;

	public Page<ExaminationCountDTO> countExaminationPage(
			ExaminationCountQueryDTO queryDTO) {
		return this.examScoreDao.countExaminationPage(queryDTO);
	}

	@Override
	public Page<ExamScoreDTO> searchExamScorePage(ExamScoreQueryDTO queryDTO) {

		Page<ExamScoreDTO> page = this.examScoreDao
				.searchExamScorePage(queryDTO);
		for (ExamScoreDTO dto : page.getList()) {
			DepartmentDTO3 depart = this.depatDao.getDangInfo(dto.getUserId());
			if (depart != null) {
				dto.setDeptName(depart.getDeptName());
				dto.setParentDeptName(depart.getParentDept());
			}

		}
		return page;
	}

	@Override
	public void deleteExamScore(Long[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addExamScore(ExamScoreDTO dto) {
		Assert.notNull(dto.getTaskId(), "学习任务数据异常!");
		StudyTask st = this.studyTaksDao.getById(dto.getTaskId());
		if (st != null) {
			ExamScore ex = new ExamScore(new User(getNowUser().getId()),
					new Date(), 20l, st, dto.getScore());

			this.examScoreDao.save(ex);

			Set<User> finishUers = new HashSet<User>();
			finishUers.add(new User(getNowUser().getId()));
			st.setFinishUers(finishUers);
			this.studyTaksDao.save(st);
		}

		// this.user = user;
		// this.finishTime = finishTime;
		// this.spendTime = spendTime;
		// this.studyTask = studyTask;
		// this.score = score;

	}

	@Override
	public void editExamScore(ExamScoreDTO dto) {
		Assert.notNull(dto.getTaskId(), "学习任务数据异常!");
		StudyTask st = this.studyTaksDao.getById(dto.getTaskId());
		ExamScore ex = this.examScoreDao.getES(dto.getTaskId(), getNowUserId());
		ex.setScore(dto.getScore());
		ex.setSpendTime(25l);
		ex.setFinishTime(new Date());
		ex.setLastOperatorTime(new Date());
		this.examScoreDao.update(ex);
	}

	@Override
	public ExamScoreDTO getExamScoreDTOById(Long id) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Boolean checkIsExist(Long taskId) {
		Assert.notNull(taskId, "学习任务数据异常!");

		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("user.id", getNowUserId());
		queryMap.put("studyTask.id", taskId);

		Boolean isExist = examScoreDao.checkWheatherExistByCondition(queryMap);

		if (isExist) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public DownLoadDTO createXlsFile() {
		List<ExamScoreDTO> result = this.examScoreDao.getExamScoreList();

		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String fileName = "在线学习分数统计" + "(截至" + sdf.format(dt) + ")";

		int currRow = 1;
		HSSFWorkbook wb = this.createGroupWorkbook();
		HSSFSheet sheet = wb.getSheet("sheet1");

		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat("yyyy-m-d"));

		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 10);// 设置字体大小
		style.setFont(font);

		// 填充表格
		for (ExamScoreDTO dto : result) {
			HSSFRow row = sheet.createRow((int) currRow);
			currRow++;

			HSSFCell cell1 = row.createCell(0);
			cell1.setCellValue(dto.getTaskName());
			cell1.setCellStyle(style);

			HSSFCell cell2 = row.createCell(1);
			cell2.setCellValue(dto.getPaperName());
			cell2.setCellStyle(style);

			HSSFCell cell3 = row.createCell(2);
			cell3.setCellValue(dto.getUsername());
			cell3.setCellStyle(style);

			HSSFCell cell4 = row.createCell(3);
			cell4.setCellValue(dto.getParentDeptName());
			cell4.setCellStyle(style);

			HSSFCell cell5 = row.createCell(4);
			cell5.setCellValue(dto.getDeptName());
			cell5.setCellStyle(style);

			HSSFCell cell6 = row.createCell(5);
			cell6.setCellValue(dto.getScore().toString());
			cell6.setCellStyle(style);

			HSSFCell cell7 = row.createCell(6);
			cell7.setCellValue(dto.getUpdateTime());
			cell7.setCellStyle(style);

		}

		// 将文件存到指定位置
		try {
			String path = this.zonePathService.getTempFileWholePath("xls");
			UUID uuid = UUID.randomUUID();
			FileOutputStream fout = new FileOutputStream(path + uuid.toString()
					+ ".xls");
			wb.write(fout);
			fout.close();
			DownLoadDTO dto = new DownLoadDTO(fileName, path + uuid.toString()
					+ ".xls", "xls");
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public HSSFWorkbook createGroupWorkbook() {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		sheet.setColumnWidth(0, 20 * 256);
		sheet.setColumnWidth(1, 20 * 256);
		sheet.setColumnWidth(2, 10 * 256);
		sheet.setColumnWidth(3, 20 * 256);
		sheet.setColumnWidth(4, 20 * 256);
		sheet.setColumnWidth(5, 20 * 256);
		sheet.setColumnWidth(6, 20 * 256);

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		style.setFont(font);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("学习任务");
		cell.setCellStyle(style);

		HSSFCell cell2 = row.createCell(1);
		cell2.setCellValue("试卷名称");
		cell2.setCellStyle(style);

		HSSFCell cell3 = row.createCell(2);
		cell3.setCellValue("考生姓名");
		cell3.setCellStyle(style);

		HSSFCell cell4 = row.createCell(3);
		cell4.setCellValue("党支部");
		cell4.setCellStyle(style);

		HSSFCell cell5 = row.createCell(4);
		cell5.setCellValue("党小组");
		cell5.setCellStyle(style);

		HSSFCell cell6 = row.createCell(5);
		cell6.setCellValue("分数");
		cell6.setCellStyle(style);

		HSSFCell cell7 = row.createCell(6);
		cell7.setCellValue("更新时间");
		cell7.setCellStyle(style);

		return wb;
	}

	/**
	 * 导出excel
	 */
	public DownLoadDTO exportExcel() {
		// 整理导出数据
		List<ExaminationCountDTO> list = this.getAllForExport();
		Assert.notEmpty(list, "无导出的数据！");
		// 导出文件名
		String fileName = "测试次数统计表";
		// 导出文件表头
		TitleSet[] titleArray = new TitleSet[] { new TitleSet("序号", 30),
				new TitleSet("用户名", 30), new TitleSet("测试次数", 30),
				new TitleSet("最后一次测试时间", 30) };
		// 整理excel格式
		WrapExcelData<ExaminationCountDTO> wrapExcelData = new WrapExcelData<ExaminationCountDTO>() {
			public void wrapData(ExaminationCountDTO obj, HSSFRow row) {
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
		ExcelUtils<ExaminationCountDTO> excelUtils = new ExcelUtils<ExaminationCountDTO>(
				titleArray, savePath, list, wrapExcelData);
		if (excelUtils.generateExecl()) {
			return new DownLoadDTO(fileName + ".xls", savePath);
		} else {
			throw new MyException("导出失败！");
		}
	}

	// 整理导出数据
	private List<ExaminationCountDTO> getAllForExport() {
		return this.examScoreDao.findAllForExport();
	}
}
