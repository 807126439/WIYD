package com.wb.web.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.star.bridge.oleautomation.Date;
import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.system.dao.IEduDegreeDao;
import com.wb.web.system.dto.EduDegree.EduDegreeDTO;
import com.wb.web.system.dto.EduDegree.EduDegreeQueryDTO;
import com.wb.web.system.dto.department.JobDepartRelationDTO;
import com.wb.web.system.entity.Department;
import com.wb.web.system.entity.EduDegree;
import com.wb.web.system.entity.JobDepartRelation;
import com.wb.web.system.entity.JobDuty;
import com.wb.web.system.entity.User;
import com.wb.web.system.service.IEduDegreeService;

@Service("eduDegreeService")
@Transactional
public class EduDegreeServiceImpl extends BaseService implements IEduDegreeService {

	@Resource
	private IEduDegreeDao eduDegreeDao;
	
	public Page<EduDegreeDTO> searchEduDegreeByPage(EduDegreeQueryDTO queryDto) {
		Page<EduDegreeDTO> pageResult = this.eduDegreeDao.searchEduDegreeByPage(queryDto);
		List<EduDegreeDTO> dtoList = new ArrayList<EduDegreeDTO>();
		for (EduDegreeDTO dt : pageResult.getList()) {
			EduDegreeDTO dto = new EduDegreeDTO();
			this.getMapper().map(dt, dto);
			dtoList.add(dto);
		}
		return new Page<EduDegreeDTO>(pageResult.getCurrentPage(), pageResult.getPageSize(), dtoList, pageResult.getRecTotal());
	}

	@Override
	public void addEduDegree(EduDegreeDTO dto) {
		
		Assert.hasText(dto.getEduName(), "学历等级不能为空");
		EduDegree edudegree = new EduDegree(
			dto.getEduName(), 
			dto.getEduText(),
			dto.getSort()
		);
		this.eduDegreeDao.save(edudegree);
	}

	@Override
	public void updateEduDegree(EduDegreeDTO dto) {
		Assert.hasText(dto.getEduName(), "学历等级不能为空");
		EduDegree eduDegree = this.eduDegreeDao.getById(dto.getId());
		eduDegree.setEduName(dto.getEduName());
		eduDegree.setEduText(dto.getEduText());
		eduDegree.setSort(dto.getSort());
		this.eduDegreeDao.update(eduDegree);
		
	}

	@Override
	public void deleteEduDegree(String[] ids) {
		if (ids!=null) {
			for (int i = 0; i < ids.length; i++) {
				EduDegree jdr = this.eduDegreeDao.getById(ids[i]);
				if(jdr!=null){
					this.eduDegreeDao.delete(jdr);
				}
			}
		}
	}

	@Override
	public EduDegree getEduDegreeById(String id) {
		Assert.notNull(id, "id must not be null");
		return this.eduDegreeDao.getById(id);
	}
	/**
	 * 获取所有等级记录
	 */
	@Override
	public List<EduDegreeDTO> getAll() {
		List<EduDegreeDTO> eduList=this.eduDegreeDao.getAll();
		return eduList;
	}

}
