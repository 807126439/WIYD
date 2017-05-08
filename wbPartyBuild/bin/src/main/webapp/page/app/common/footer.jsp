<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="footerbar">
	<c:if test = "${index != true}">
	   <a href="javascript:history.back(-1)" class="btn_back"><i class="icon_back"></i>返回上一层</a>
	</c:if>
	   <a href="<%=path %>/appController/index.do" class="btn_home"><i class="icon_home"></i>主 页</a>
</div>