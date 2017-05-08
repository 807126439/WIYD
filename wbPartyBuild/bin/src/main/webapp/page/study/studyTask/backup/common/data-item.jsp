
<%@ page language="java" pageEncoding="utf-8"%>
<div id="hide-StudyData-area"
	style="display: none;width:720px;margin:0 auto">

	<!-- ==========这个hidden的input有什么用？========== -->
	<input type="hidden" id="path" value="0">
	<!-- ==========修改这个div的top: 8px;？========== -->
	<div class="Hui-article"
		style="width: 950px;margin: 0px auto;top: 0px;z-index: 1">
		<!-- 		<div class="pd-20 clearfix"> -->
		<!-- 将span移到下面的div -->
		<!-- 			<span class="l"> <a class="btn btn-primary radius" -->
		<!-- 				onclick="subStudyDataChoice()" href="javascript:;"><i -->
		<!-- 					class="Hui-iconfont">&#xe600;</i> 确定</a> -->
		<!-- 			</span> -->
		<!-- 		</div> -->
		<div class="pd-20">
			<div class="item-contrl">
				<span class="checksAll"> <!-- 从上面移下来 --> <a
					class="btn btn-primary radius" style="margin: 8px;"
					onclick="subStudyDataChoice()" href="javascript:;"><i
						class="Hui-iconfont">&#xe600;</i> 确定</a> <a
					href="javascript:selectAll()" class="selectAll">全选</a>|<a
					href="javascript:cancelAll()" class="cancelAll">全不选</a>
				</span>
			</div>
			<!-- ==========缩略图列表========== -->
			<div class="item-content">
				<ul class="cl item-area" id="show-list">
				</ul>
			</div>
			<div id="item-page" class="pt-20 pr-10 r"></div>
		</div>
	</div>
</div>
