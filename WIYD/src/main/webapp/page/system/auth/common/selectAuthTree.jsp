<%@ page language="java"  pageEncoding="utf-8"%>

 <div >
    	<div class="panel panel-default" style="border-bottom:none; border-left: none;border-right: none;">
	    	<div class="panel-header" >选择父节点	
	    	 <a href="javascript:;" class="r pr-10 c-primary" onclick="expandNode(this,'collapseAll')" >折叠</a>
	    	 <a href="javascript:;" class="r pr-10 c-primary" onclick="expandNode(this,'expandAll')" >展开</a>
	    	</div>
	    	<div id="show-auths" class="panel-body" style="position: absolute;top: 40px;left:0;bottom: 0;right: 0;
	    		overflow: auto;z-index: 1; overflow-y: scroll;">
	    		<ul id="tree" class="ztree"></ul>
	    	
	    	</div>
	  </div>
    	
  </div>
  
  


