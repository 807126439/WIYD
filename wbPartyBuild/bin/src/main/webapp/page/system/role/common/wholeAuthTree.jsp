<%@ page language="java"  pageEncoding="utf-8"%>

 <div style="margin-left:450px;">
    	<div class="panel panel-default" style="border-bottom:none; border-left: none;border-right: none;">
    	<div class="panel-header">设置权限
    	
    	 <a href="javascript:;" class="r c-primary" onclick="showCheckAttr()">勾选属性</a>
    	 <a href="javascript:;" class="r pr-10 c-primary" onclick="checkAllNodes(this)" singal="0">全选</a>
    	 <a href="javascript:;" class="r pr-10 c-primary" onclick="expandNode(this,'collapseAll')" >折叠</a>
    	 <a href="javascript:;" class="r pr-10 c-primary" onclick="expandNode(this,'expandAll')" >展开</a>
    	</div>
    	<div class="panel-body">
    		<ul id="tree" class="ztree"></ul>
    	
    	</div>
	  </div>
    	
  </div>
  
  
  <div id="checkAttrs" style="display: none;">
    <div class="pd-20">
	  	父子关联关系：<br/>
		被勾选时：<input type="checkbox" id="py" class="checkbox first" checked /><span>关联父</span>
		<input type="checkbox" id="sy" class="checkbox first" checked /><span>关联子</span><br/>
		取消勾选时：<input type="checkbox" id="pn" class="checkbox first" checked /><span>关联父</span>
		<input type="checkbox" id="sn" class="checkbox first" checked /><span>关联子</span><br/>

		
  	</div>
  </div>


