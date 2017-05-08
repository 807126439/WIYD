package com.wb.web.system.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.utils.ReflectUtil;
import com.wb.web.system.dao.ILoginLogDao;
import com.wb.web.system.dto.log.LoginCountDTO;
import com.wb.web.system.dto.log.LoginLogDTO;
import com.wb.web.system.dto.log.LoginLogQueryDTO;
import com.wb.web.system.entity.LoginLog;

@Repository("loginLogDao")
public class LoginLogDaoImpl extends BaseDaoImpl<Long, LoginLog> implements
		ILoginLogDao {

	/**
	 * 获取全部导出数据
	 */
	public List<LoginCountDTO> findAllForExport() {
		// 构造sql语句：登录次数统计，以天为单位（一天登录N次都算一次）
		// SELECT u.id AS id, max(l.last_operator_time) AS lastOperatorTime,
		// u.user_name AS username, count(distinct
		// DATE_FORMAT(l.login_date,'%Y-%m-%d')) AS num, max(l.login_date) AS
		// loginDate FROM c_user u LEFT JOIN c_login_log l ON
		// u.user_name=l.user_name GROUP BY u.user_name ORDER BY num DESC
		StringBuilder listBuilder = new StringBuilder();
		listBuilder
				.append("SELECT u.id AS id, max(l.last_operator_time) AS lastOperatorTime, ");
		listBuilder
				.append("u.user_name AS username, count(distinct DATE_FORMAT(l.login_date,'%Y-%m-%d')) AS num, ");
		listBuilder
				.append("max(l.login_date) AS loginDate FROM c_user u LEFT JOIN c_login_log l ON u.user_name=l.user_name ");
		listBuilder.append("GROUP BY u.user_name ORDER BY num DESC");
		// 构造query查询条件，并映射成LoginCountDTO对象
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("id", StringType.INSTANCE)
				.addScalar("username", StringType.INSTANCE)
				.addScalar("loginDate", TimestampType.INSTANCE)
				.addScalar("num", IntegerType.INSTANCE)
				.addScalar("lastOperatorTime", TimestampType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(LoginCountDTO.class));
		// 获取记录列表
		List<LoginCountDTO> list = queryList.list();
		// 设置序号
		int sort = 1;
		for (LoginCountDTO dto : list) {
			dto.setSort(sort++);
		}
		return list;
	}

	/**
	 * 分页统计登录次数
	 */
	public Page<LoginCountDTO> countLoginFrequencyByPage(
			LoginLogQueryDTO queryDTO) {
		// 构造sql语句：获取总用户数
		// SELECT COUNT(*) as num FROM (SELECT u.user_name FROM c_user u LEFT
		// JOIN c_login_log l ON u.user_name=l.user_name LEFT JOIN
		// c_job_depart_relation jdr ON u.id=jdr.user_id WHERE is_del=0 and
		// is_ignore_stat=0 GROUP BY u.user_name) t_derived
		StringBuilder countBuilder = new StringBuilder();
		countBuilder
				.append("SELECT COUNT(*) as num FROM (SELECT u.user_name FROM c_user u ");
		countBuilder
				.append("LEFT JOIN c_login_log l ON u.user_name=l.user_name ");
		countBuilder
				.append("LEFT JOIN c_job_depart_relation jdr ON u.id=jdr.user_id WHERE is_del=0 and is_ignore_stat=0 ");
		// 构造sql语句：登录次数统计，以天为单位（一天登录N次都算一次）
		// SELECT u.id AS id, max(l.last_operator_time) AS lastOperatorTime,
		// u.user_name AS username, count(distinct
		// DATE_FORMAT(l.login_date,'%Y-%m-%d')) AS num, max(l.login_date) AS
		// loginDate FROM c_user u LEFT JOIN c_login_log l ON
		// u.user_name=l.user_name LEFT JOIN c_job_depart_relation jdr ON u.id =
		// jdr.user_id WHERE is_del=0 and is_ignore_stat=0 GROUP BY u.user_name
		// ORDER BY num DESC 
		StringBuilder listBuilder = new StringBuilder();
		listBuilder
				.append("SELECT u.id AS id, max(l.last_operator_time) AS lastOperatorTime, ");
		listBuilder
				.append("u.user_name AS username, count(distinct DATE_FORMAT(l.login_date,'%Y-%m-%d')) AS num, ");
		listBuilder
				.append("max(l.login_date) AS loginDate FROM c_user u LEFT JOIN c_login_log l ON u.user_name=l.user_name ");
		listBuilder
				.append("LEFT JOIN c_job_depart_relation jdr ON u.id = jdr.user_id WHERE is_del=0 and is_ignore_stat=0 ");
		// 根据用户名模糊搜索
		if (queryDTO.getUserName() != null
				&& !StringUtils.isBlank(queryDTO.getUserName())) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND u.user_name LIKE :name ");
			// countBuilder.append("AND user_name LIKE :name ");
			// listBuilder.append("AND u.user_name LIKE :name ");
		}
		// 指定起始时间
		if (queryDTO.getBeginTime() != null
				&& !StringUtils.isBlank(queryDTO.getBeginTime())) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND UNIX_TIMESTAMP(l.login_date) >= UNIX_TIMESTAMP(:l_begin) ");
			// listBuilder
			// .append("AND UNIX_TIMESTAMP(l.login_date) >= UNIX_TIMESTAMP(:l_begin) ");
		}
		// 指定终止时间
		if (queryDTO.getEndTime() != null
				&& !StringUtils.isBlank(queryDTO.getEndTime())) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND UNIX_TIMESTAMP(l.login_date) <= UNIX_TIMESTAMP(:l_end) ");
			// listBuilder
			// .append("AND UNIX_TIMESTAMP(l.login_date) <= UNIX_TIMESTAMP(:l_end) ");
		}
		// 部门查询条件相关
		// 获取ID下自身以及后代部门ID
		if (queryDTO.getDepartmentIds() != null
				&& !StringUtils.isBlank(queryDTO.getDepartmentIds())) {
			String[] ids = queryDTO.getDepartmentIds().split(",");
			// List<String> allIds=new LinkedList<String>();
			// List<String>
			// departmentIdList=this.departmentDao.getSelfAndOffspringId(ids,allIds);
			StringBuilder departmentIds = new StringBuilder();
			for (String o : ids) {
				departmentIds.append("'" + o + "',");
			}
			departmentIds.deleteCharAt(departmentIds.lastIndexOf(","));
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND jdr.department_id IN (" + departmentIds + ") ");
		}
		countBuilder.append("GROUP BY u.user_name) t_derived ");
		listBuilder.append("GROUP BY u.user_name ORDER BY num DESC ");
		// TODO 按列排序方法
		// if (!StringUtils.isBlank(queryDTO.getSidx())
		// && !StringUtils.isBlank(queryDTO.getSord())) {
		// String filedName = ReflectUtil.getFiledValueByAnnotation(
		// getInheritClass(), queryDTO.getSidx());
		// listBuilder.append(generateOrderSql(queryDTO.getSord(), filedName,
		// null));
		// } else {
		// listBuilder.append("ORDER BY num DESC ");
		// }
		// 构造query查询条件，并映射成LoginCountDTO对象
		Query queryCount = this.getSession()
				.createSQLQuery(countBuilder.toString())
				.addScalar("num", LongType.INSTANCE);
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("id", StringType.INSTANCE)
				.addScalar("username", StringType.INSTANCE)
				.addScalar("loginDate", TimestampType.INSTANCE)
				.addScalar("num", IntegerType.INSTANCE)
				.addScalar("lastOperatorTime", TimestampType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(LoginCountDTO.class));
		// 替换sql语句的参数
		if (queryDTO.getUserName() != null
				&& !StringUtils.isBlank(queryDTO.getUserName())) {
			this.setQueryParamsVal(queryCount, queryList, "name", "%"
					+ queryDTO.getUserName() + "%");
		}
		if (queryDTO.getBeginTime() != null
				&& !StringUtils.isBlank(queryDTO.getBeginTime())) {
			this.setQueryParamsVal(queryCount, queryList, "l_begin",
					queryDTO.getBeginTime());
			// queryList.setParameter("l_begin", queryDTO.getBeginTime());
		}
		if (queryDTO.getEndTime() != null
				&& !StringUtils.isBlank(queryDTO.getEndTime())) {
			this.setQueryParamsVal(queryCount, queryList, "l_end",
					queryDTO.getEndTime());
			// queryList.setParameter("l_end", queryDTO.getEndTime());
		}
		// 获取总记录数
		Long recTotal = (Long) queryCount.uniqueResult();
		// 设置分页查询参数
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		// 获取记录列表
		List<LoginCountDTO> list = queryList.list();
		// 设置序号
		int sort = 1;
		for (LoginCountDTO dto : list) {
			dto.setSort(sort++);
		}
		return new Page<LoginCountDTO>(queryDTO.getCurrentPage(),
				queryDTO.getPageSize(), list, recTotal);
	}

	/**
	 * 分页查询
	 * 
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<LoginLogDTO> searchLoginLogByPage(LoginLogQueryDTO queryDTO) {
		StringBuilder countBuilder = new StringBuilder(
				"SELECT COUNT(*) as num  FROM c_login_log WHERE 1=1 ");

		StringBuilder listBuilder = new StringBuilder();
		listBuilder
				.append("SELECT id as id,user_name as username,INET_NTOA(login_ip) as convertIP,");
		listBuilder
				.append("login_date as loginDate,log_details as logDetails,last_operator_time as lastOperatorTime ");
		listBuilder.append("FROM c_login_log WHERE 1=1 ");

		if (queryDTO.getUserName() != null
				&& !StringUtils.isBlank(queryDTO.getUserName())) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND user_name LIKE :name ");
		}

		if (queryDTO.getBeginTime() != null
				&& !StringUtils.isBlank(queryDTO.getBeginTime())) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND UNIX_TIMESTAMP(login_date) >= UNIX_TIMESTAMP(:l_begin) ");
		}

		if (queryDTO.getEndTime() != null
				&& !StringUtils.isBlank(queryDTO.getEndTime())) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND UNIX_TIMESTAMP(login_date) <= UNIX_TIMESTAMP(:l_end) ");
		}

		if (!StringUtils.isBlank(queryDTO.getSidx())
				&& !StringUtils.isBlank(queryDTO.getSord())) {

			String filedName = ReflectUtil.getFiledValueByAnnotation(
					getInheritClass(), queryDTO.getSidx());
			listBuilder.append(generateOrderSql(queryDTO.getSord(), filedName,
					null));
		} else {
			listBuilder.append("ORDER BY id ASC ");
		}

		Query queryCount = this.getSession()
				.createSQLQuery(countBuilder.toString())
				.addScalar("num", LongType.INSTANCE);
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("id", LongType.INSTANCE)
				.addScalar("username", StringType.INSTANCE)
				.addScalar("convertIP", StringType.INSTANCE)
				.addScalar("loginDate", TimestampType.INSTANCE)
				.addScalar("logDetails", StringType.INSTANCE)
				.addScalar("lastOperatorTime", TimestampType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(LoginLogDTO.class));

		if (queryDTO.getUserName() != null
				&& !StringUtils.isBlank(queryDTO.getUserName())) {
			this.setQueryParamsVal(queryCount, queryList, "name", "%"
					+ queryDTO.getUserName() + "%");
		}
		if (queryDTO.getBeginTime() != null
				&& !StringUtils.isBlank(queryDTO.getBeginTime())) {
			this.setQueryParamsVal(queryCount, queryList, "l_begin",
					queryDTO.getBeginTime());
		}
		if (queryDTO.getEndTime() != null
				&& !StringUtils.isBlank(queryDTO.getEndTime())) {
			this.setQueryParamsVal(queryCount, queryList, "l_end",
					queryDTO.getEndTime());
		}

		Long recTotal = (Long) queryCount.uniqueResult();

		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());

		List<LoginLogDTO> list = queryList.list();

		return new Page<LoginLogDTO>(queryDTO.getCurrentPage(),
				queryDTO.getPageSize(), list, recTotal);
	}

	@Override
	public Long searchCountsAfterDate(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String dateString = sf.format(date);
		String hqlString = "SELECT COUNT(*) from LoginLog l WHERE l.loginDate > '"
				+ dateString + "' group BY l.userName ";
		Session session = this.getSession();
		Long size = new Long((long) session.createQuery(hqlString).list()
				.size());
		return size;
	}

}
