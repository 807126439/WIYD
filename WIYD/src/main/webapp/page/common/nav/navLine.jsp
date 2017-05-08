<%@ page language="java"  pageEncoding="utf-8"%>
 
  <div class="ui-topnav">
    <span class="ui-topnav-title">${TITLE}</span>
    <span class="ui-location"><span class="ui-location-pre">当前位置：</span><ol class="ui-location-path"><li>首页</li>
    <c:if test="${!empty TOP_TITLE}"><li>${TOP_TITLE}</li></c:if>
    <c:if test="${!empty TITLE}"><li>${TITLE}</li></c:if>
    </ol></span>
  </div>