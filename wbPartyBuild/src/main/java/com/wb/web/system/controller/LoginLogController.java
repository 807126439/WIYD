package com.wb.web.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.system.dto.log.LoginCountDTO;
import com.wb.web.system.dto.log.LoginLogDTO;
import com.wb.web.system.dto.log.LoginLogQueryDTO;
import com.wb.web.system.service.ILoginLogService;

@Controller
@Scope("prototype")
@RequestMapping("/loginLogController")
public class LoginLogController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2486002775559927563L;
	@Resource
	private ILoginLogService loginLogService;

	@RequestMapping(params = MyRequestParam.viewPage)
	public String viewLogPage(HttpServletRequest request) {
		this.wrapMenuTitle(request);
		return "system/log/logList.jsp";
	}

	/**
	 * 2017.02.13
	 * 
	 * 导出全部
	 */
	@RequestMapping(value = "/exportAllLoginCount")
	public void exportAllLoginCount(HttpServletRequest request,
			HttpServletResponse response) {
		this.createDownLoadStream(request, response,
				this.loginLogService.exportExcel());
	}

	/**
	 * 2017.02.10
	 * 
	 * 分页统计
	 * 
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("countList")
	@ResponseBody
	public Map<String, Object> countLogByPage(HttpServletRequest request,
			LoginLogQueryDTO queryDTO) {

		this.wrapTableQueryParams(request, queryDTO);
		Page<LoginCountDTO> pageResult = this.loginLogService
				.countLoginFrequencyByPage(queryDTO);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());// 总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize()); // 当前查询数量
		jsonMap.put("sEcho", queryDTO.getsEcho());

		return jsonMap;
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadLogByPage(HttpServletRequest request,
			LoginLogQueryDTO queryDTO) {

		this.wrapTableQueryParams(request, queryDTO);
		Page<LoginLogDTO> pageResult = this.loginLogService
				.searchLoginLogByPage(queryDTO);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());// 总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize()); // 当前查询数量
		jsonMap.put("sEcho", queryDTO.getsEcho());

		return jsonMap;
	}

	/**
	 * 删除日志
	 * 
	 * @param rids
	 * @return
	 */
	@RequestMapping("/delLoginLog")
	@ResponseBody
	public AjaxJson deleteLog(Long[] ids) {

		this.loginLogService.deleteLogs(ids);

		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}

}
