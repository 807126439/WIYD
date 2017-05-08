package com.spr.core.gobal;

public class GobalVal {
	/** 系统配置文件 */
	public final static String SYSTEM_PROPERTIES = "systemConfig.properties";

	/** 审核状态值 */
	public final static Short AUDIT_DRAFT = 0; // 未提交
	public final static Short AUDIT_NOW = 1; // 正在审核
	public final static Short ADUIT_PASS = 2; // 通过审核
	public final static Short ADUIT_FAIL = 3; // 不通过审核
	public final static Short ADUIT_ACCEPT = 4; // 验收
	public final static Short ADUIT_REPLY = 11; // 重新提交审核

	// 积分变动记录操作标志
	public final static Short OPER_INC = 1; // 积分增加标志
	public final static Short OPER_DEC = 0; // 积分减少标志
	// 积分变动记录积分来源
	public final static Short SOURCE_LOGIN = 0; // 登录
	public final static Short SOURCE_VIEW = 1; // 文件查看
	public final static Short SOURCE_UPLOAD = 2; // 文件上传
	public final static Short SOURCE_DOWNLOAD = 3; // 文件下载
	public final static Short SOURCE_COMMENT = 4; // 评论
	public final static Short SOURCE_RECOMMEND = 5; // 推荐
	// 操作积分
	public final static Integer SCORE_BASIC = 100; // 基础分
	public final static Integer SCORE_RECOMMEND = 1;// 推荐分
	// 其他
	public final static Byte CANCEL_RECOMMEND = 0;// 取消推荐
	public final static Byte IS_RECOMMEND = 1;// 设定推荐
	//文档类别
	public final static Short CATEGORY_FILE = 1;// 文档类别
	public final static Short CATEGORY_QUESTION = 2;// 问答类别
	//文档状态
	public final static Short ALLOW_READING = 0;// 允许阅读
	public final static Short PROHIBIT_READING = 1;// 禁止阅读
}
