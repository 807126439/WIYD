package com.wb.web.study.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.study.dao.IStudyCategoryDao;
import com.wb.web.study.dto.studycategory.StudyCategoryDTO;
import com.wb.web.study.dto.studycategory.StudyCategoryQueryDTO;
import com.wb.web.study.entity.StudyCategory;
import com.wb.web.study.service.IStudyCategoryService;
import com.wb.web.study.service.IStudyDataService;
import com.wb.web.study.service.ITopicService;



@Service("StudyCategoryService")
@Transactional
public class StudyCategoryServiceImpl extends BaseService implements IStudyCategoryService {

	@Resource
	private IStudyCategoryDao studyCategoryDao;

	@Resource
	private IStudyDataService studyDataService;
	
	@Resource
	private ITopicService topicService;
	
	@Override
	public Page<StudyCategoryDTO> searchStudyCategoryPage(
			StudyCategoryQueryDTO queryDTO) {
		
		return studyCategoryDao.searchStudyCategoryPage(queryDTO);
	}

	@Override
	public void addStudyCategory(StudyCategoryDTO dto) {
		Assert.hasText(dto.getCateName(), "类别名称不能为空!");
		Assert.hasText(dto.getCateNum(), "类别编号不能为空");

		StudyCategory scCategory = new StudyCategory(
				dto.getCateName(),
				dto.getCateNum(), 
				this.getNowUser().getUsername()
				);
		this.studyCategoryDao.save(scCategory);
	}

	@Override
	public void updateStudyCategory(StudyCategoryDTO dto) {
		
		Assert.notNull(dto.getId(), "Id不能为空!");
		Assert.hasText(dto.getCateName(), "类别名称不能为空!");
		Assert.hasText(dto.getCateNum(), "类别编号不能为空");
		
		StudyCategory studyCategory = this.studyCategoryDao.getById(dto.getId());
		studyCategory.setLastOperatorTime(new Date());
		studyCategory.setCateName(dto.getCateName());
		studyCategory.setCateNum(dto.getCateNum());
		studyCategory.setUpdateBy(this.getNowUser().getUsername());
		
		this.studyCategoryDao.update(studyCategory);

	}

	@Override
	public AjaxJson deleteStudyCategory(Long[] ids) {
		StringBuilder stuBuilder=new StringBuilder();
		for (int i = 0; i < ids.length; i++) {
			StudyCategory as = this.studyCategoryDao.getById(ids[i]);
			if (as != null) {
				Long sdCount = studyDataService.getStuDataByIdFindCateName(as.getId());
				if (sdCount<=0) {
					Long topicCount=topicService.getTopicByIdFindcateName(as.getId());
					if (topicCount<=0) {
						this.studyCategoryDao.delete(as);
						if (i % 20 == 0) {
							this.studyCategoryDao.flush();
							this.studyCategoryDao.clear();
						}
					}else{
						stuBuilder.append("编号为:"+as.getCateNum()+"的类别被题目管理引用!删除失败...<br>");
					}
				}else {
					stuBuilder.append("编号为:"+as.getCateNum()+"的类别被资料管理引用!删除失败...<br>");
					
				}
			}
		}
			if (stuBuilder.length()>0) {
				return new AjaxJson(stuBuilder.toString(),AjaxJson.error);
				
			}else {
				return new AjaxJson("删除 成功！",AjaxJson.success);
			}
	}
	@Override
	public StudyCategoryDTO getStudyCategoryById(Long id) {
		Assert.notNull(id, "Id不能为空!");
		if(id == null){
			throw new NullPointerException("id must not be null");
		}
		return studyCategoryDao.getStudyCategoryById(id);
	}
	/**
	 * 查询类别ID与类别名称
	 * @return
	 */
	@Override
	public List<StudyCategory> getStudyCategoriesAndIdAndName(){
		return studyCategoryDao.getStudyCategoriesAndIdAndName();
	}

}
