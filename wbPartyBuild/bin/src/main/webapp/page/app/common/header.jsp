<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  <div class="mainbg-0"></div>
  <header class="header">
    <div class="header-logo"><img src="<%=path %>/plug-in/app/images/header-logo.png"></div>
  </header>
    <div class="infobar">
    <span>你好,<sec:authentication property="principal.chineseName" /></span>
    
    
  </div>