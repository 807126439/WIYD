<%@ page language="java"  pageEncoding="utf-8"%>

 <div  style="margin-left:480px;">
    	<div class="panel panel-default" style="border-bottom:none; border-left: none;border-right: none;">
	    	<div class="panel-header" >设置权限    	
	    	 <a href="javascript:;" class="r c-primary" onclick="showCheckAttr()">勾选属性</a>
	    	 <a href="javascript:;" class="r pr-10 c-primary" onclick="checkAllNodes(this)" singal="0">全选</a>
	    	 <a href="javascript:;" class="r pr-10 c-primary" onclick="expandNode(this,'collapseAll')" >折叠</a>
	    	 <a href="javascript:;" class="r pr-10 c-primary" onclick="expandNode(this,'expandAll')" >展开</a>
	    	</div>
	    	<div id="show-auths" class="panel-body" style="position: absolute;top: 40px;left:480;bottom: 0;right: 0;
	    		overflow: auto;z-index: 1; overflow-y: scroll;">
	    		<ul id="tree" class="ztree"></ul>
	    	
	    	</div>
	  </div>
    	
  </div>
  
  
  <div id="checkAttrs" style="display: none;">
    <div class="pd-20">
	  	父子关联关系：<br/>
		被勾选时：<input type="checkbox" id="py" class="checkbox first" checked /><span class="pr-10 pl-5">关联父</span>
		<input type="checkbox" id="sy" class="checkbox first" checked /><span class="pr-10 pl-5">关联子</span><br/>
		取消勾选时：<input type="checkbox" id="pn" class="checkbox first" checked /><span class="pr-10 pl-5">关联父</span>
		<input type="checkbox" id="sn" class="checkbox first" checked /><span class="pr-10 pl-5">关联子</span><br/>

		
  	</div>
  </div>

