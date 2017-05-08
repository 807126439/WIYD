package com.spr.web.system.dao;

import com.spr.core.common.dao.IBaseDao;
import com.spr.web.system.dto.log.SysLogsDTO;
import com.spr.web.system.entity.SysLogs;
import java.util.List;
import java.util.Map;

public interface ISysLogsDao extends IBaseDao<Long, SysLogs> {

    Long countByCondition(Map<String, Object> queryMap);

    List<SysLogsDTO> selectListByCondition(Map<String, Object> queryMap);

    SysLogsDTO getDetailById(Long id);
}