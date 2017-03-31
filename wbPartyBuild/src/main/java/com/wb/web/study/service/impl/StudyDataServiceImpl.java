package com.wb.web.study.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.base.dto.result.SaveResult;
import com.wb.web.base.entity.BaseFile;
import com.wb.web.base.service.IBaseFileService;
import com.wb.web.study.dao.IStudyDataDao;
import com.wb.web.study.dto.studyData.StudyDataDTO;
import com.wb.web.study.dto.studyData.StudyDataQueryDTO;
import com.wb.web.study.entity.StudyCategory;
import com.wb.web.study.entity.StudyData;
import com.wb.web.study.service.IStudyDataService;
import com.wb.web.study.service.IStudyTaskService;
import com.wb.web.system.dto.zone.ZonePathDTO;
import com.wb.web.system.service.IZonePathService;

@Service("studyDataService")
@Transactional
public class StudyDataServiceImpl extends BaseService implements
		IStudyDataService {
	@Resource
	private IStudyDataDao studyDataDao;
	@Resource
	private IZonePathService zonePathService;
	@Resource
	private IStudyTaskService studyTaskService;
	@Resource
	private IBaseFileService baseFileService;

	/**
	 * 分页查询学习资料
	 * 
	 * @param queryDTO
	 * @return
	 */
	@Override
	public Page<StudyDataDTO> searchStudyDataPage(StudyDataQueryDTO queryDTO) {
		Map<Long, String> cache = new HashMap<Long, String>();
		Page<StudyDataDTO> page = this.studyDataDao
				.searchStudyDataPage(queryDTO);
		for (StudyDataDTO dto : page.getList()) {
			if (dto.getZonePathId() != null) {
				String prefixPath = null;
				if (cache.containsKey(dto.getZonePathId())) {
					prefixPath = cache.get(dto.getZonePathId());
				} else {
					ZonePathDTO zp = this.zonePathService.getZonePathById(dto
							.getZonePathId());
					cache.put(dto.getZonePathId(), zp.getVirtualPath());
					prefixPath = zp.getVirtualPath();
				}

				if (dto.getFileKind() == BaseFile.FILE_KIND_PICTURE) {
					dto.setPattern(prefixPath + dto.getPattern());
				}
			}

			if (!StringUtils.isBlank(queryDTO.getStuIds())) {
				String[] id = queryDTO.getStuIds().split(",");
				for (String ids : id) {
					Long idLong = Long.valueOf(ids).longValue();
					if (dto.getSdId().equals(idLong)) {
						dto.setCheck(true);
					}
				}

			}

		}

		return page;
	}

	/***
	 * 查询试卷类别的cateNum（编号）在学习资料里存不存在
	 */
	@Override
	public Long getStuDataByIdFindCateName(Long id) {
		return this.studyDataDao.getStuDataByIdFindCateName(id);
	}

	/***
	 * 删除
	 */
	@Override
	public AjaxJson deleteStuData(Long[] sdId) {
		StringBuilder stuBuilder = new StringBuilder();
		for (int i = 0; i < sdId.length; i++) {
			StudyData as = this.studyDataDao.getById(sdId[i]);
			if (as != null) {
				Long stsCount = studyTaskService.getStudyTaskByIdExists(as
						.getId());
				if (stsCount <= 0) {

					if (as.getBaseFileId() != null) {
						baseFileService.deleteBaseFile(as.getBaseFileId());
					}
					this.studyDataDao.delete(as);
					if (i % 20 == 0) {
						this.studyDataDao.flush();
						this.studyDataDao.clear();
					}

				} else {
					stuBuilder.append("资料名称为:" + as.getDataName()
							+ "的资料被学习任务管理引用!删除失败...<br>");
				}
			}
		}
		if (stuBuilder.length() > 0) {
			return new AjaxJson(stuBuilder.toString(), AjaxJson.error);
		} else {
			return new AjaxJson("删除 成功！", AjaxJson.success);
		}
	}

	@Override
	public void addStudyData(StudyDataDTO dto, CommonsMultipartFile uploadFile) {
		Assert.hasText(dto.getDataNum(), "资料编号不能为空!");
		Assert.hasText(dto.getDataName(), "资料名称不能为空!");
		StudyData studyData = new StudyData(dto.getDataNum(),
				dto.getDataName(), dto.getDataMemo(), new StudyCategory(
						dto.getCateId()), null,
				this.getNowUser().getUsername(), this.getNowUser()
						.getUsername());
		if (uploadFile != null && !uploadFile.isEmpty()) {
			SaveResult aResult = this.baseFileService
					.addPublicBaseFile(uploadFile);
			studyData.setBaseFileId(aResult.getId());
		}
		this.studyDataDao.save(studyData);
	}

	@Override
	public StudyDataDTO getStudyDataById(Long id) {
		return studyDataDao.getStudyDataById(id);
	}

	@Override
	public void updateStudyData(StudyDataDTO dto) {
		Assert.notNull(dto.getSdId(), "Id不能为空!");
		Assert.hasText(dto.getDataNum(), "资料编号不能为空!");
		Assert.hasText(dto.getDataName(), "资料名称不能为空!");
		StudyData studyData = this.studyDataDao.getById(dto.getSdId());
		studyData.setLastOperatorTime(new Date());
		studyData.setStudyCategory(new StudyCategory(dto.getCateId()));
		studyData.setDataMemo(dto.getDataMemo());
		studyData.setDataName(dto.getDataName());
		studyData.setDataNum(dto.getDataNum());
		studyData.setUpdateBy(this.getNowUser().getUsername());
		this.studyDataDao.update(studyData);
	}

	@Override
	public void updateStudyData(StudyDataDTO dto,
			CommonsMultipartFile uploadFile) {
		Assert.notNull(dto.getSdId(), "Id不能为空!");
		Assert.hasText(dto.getDataNum(), "资料编号不能为空!");
		Assert.hasText(dto.getDataName(), "资料名称不能为空!");
		// 根据学习资料id获取修改的学习资料记录，并修改相关信息
		StudyData studyData = this.studyDataDao.getById(dto.getSdId());
		studyData.setLastOperatorTime(new Date());
		studyData.setStudyCategory(new StudyCategory(dto.getCateId()));
		studyData.setDataMemo(dto.getDataMemo());
		studyData.setDataName(dto.getDataName());
		studyData.setDataNum(dto.getDataNum());
		studyData.setUpdateBy(this.getNowUser().getUsername());
		// 如果存在上传文件，则添加文件并进行关联
		if (uploadFile != null && !uploadFile.isEmpty()) {
			this.baseFileService.deleteBaseFile(studyData.getBaseFileId());
			SaveResult aResult = this.baseFileService
					.addPublicBaseFile(uploadFile);
			studyData.setBaseFileId(aResult.getId());
		}
		this.studyDataDao.update(studyData);
	}

	/**
	 * 下载学习资料
	 * 
	 * @param dto
	 */
	public DownLoadDTO downloadStudyData(Long id) {
		Assert.notNull(id, "id could not be null");

		StudyData entity = this.studyDataDao.getById(id);
		if (entity != null) {
			DownLoadDTO result = this.baseFileService
					.getDownLoadInfoById(entity.getBaseFileId());
			result.setFileName(entity.getDataName());
			return result;
		} else {
			throw new MyException("Counld not find the record!");
		}
	}

	@Override
	public List<StudyDataDTO> getStudydataByIdAndName(Long id) {
		List<StudyDataDTO> studyDataDTO = studyDataDao
				.getStudydataByIdAndName(id);
		return studyDataDTO;
	}

	@Override
	public StudyDataDTO getFileStudyDataById(Long sdId) {

		return studyDataDao.getFileStudyDataById(sdId);
	}

}
