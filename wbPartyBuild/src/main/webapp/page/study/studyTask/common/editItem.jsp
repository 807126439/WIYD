<%@ page language="java" pageEncoding="utf-8"%>
<input type="hidden" value="${studyTask.stId }" name="stId" />
<input type="hidden" value="${stuId }" id="stuId" />
<div class="row cl">
	<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>任务编号：</label>
	<div class="formControls col-xs-8 col-sm-8">
		<input type="text" class="input-text" name="taskNum" datatype="*2-20"
			value="${studyTask.taskNum}" nullmsg="任务编号不能为空">
	</div>
	<div class="col-xs-2 col-sm-2"></div>
</div>

<div class="row cl">
	<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>任务名称：</label>
	<div class="formControls col-xs-8 col-sm-8">
		<input type="text" class="input-text" name="taskName" datatype="*2-20"
			value="${studyTask.taskName}" nullmsg="任务名称不能为空">
	</div>
	<!-- ==========不知道有什么用？========== -->
	<div class="col-xs-2 col-sm-2"></div>
</div>










<div class="row cl">
	<label class="form-label col-xs-2 col-sm-2">学习资料：</label>
	<div class="formControls col-xs-8 col-sm-8">
		<div id="xuexi">
			<c:forEach items="${studyData}" var="list">
				<div class="divclass">
					<input readonly="readonly" type="text" value="${list.dataName }" />
					<!-- ==========去掉span的style="float: right;"========== -->
					<span onclick="checkSpan(this)"><i
						class='Hui-iconfont Hui-iconfont-close2'></i></span> <input
						class="taskStuids" type="hidden" value="${list.sdId}"
						name='taskStu' id="sdid_${list.sdId }" />
				</div>
			</c:forEach>
		</div>
		<input style="margin-top: 5px;" class="btn btn-primary radius r"
			type="button" value="选择资料" onclick="chooseData()" />
	</div>
	<div class="col-xs-2 col-sm-2"></div>
</div>
<!-- <div class="row cl"> -->
<!-- 	<label class="form-label col-xs-2 col-sm-2">学习资料：</label> -->
<!-- 	<div class="formControls col-xs-8 col-sm-8"> -->
<!-- 		<div id="xuexi"> -->
<!-- 			<c:forEach items="${studyData}" var="list"> -->
<!-- 				<div class="divclass" -->
<!-- 					style="width:180px;margin: 10px;float: left;border: 1px solid #AFD1E3;"> -->
<!-- 					<input style="height: 25px;width: 156px;" readonly="readonly" -->
<!-- 						type="text" value="${list.dataName }" /> <input -->
<!-- 						class="taskStuids" type="hidden" value="${list.sdId}" -->
<!-- 						name='taskStu' id="sdid_${list.sdId }"> <span -->
<!-- 						onclick="checkSpan(this)" style="float: right;"><img -->
<!-- 						src="<%=path%>/plug-in/web/portals/image/cancel.png" width="24px" -->
<!-- 						height="25px" /></span> -->
<!-- 				</div> -->
<!-- 			</c:forEach> -->
<!-- 		</div> -->
<!-- 		<input style="margin-top: 5px;" class="btn btn-primary radius r" -->
<!-- 			type="button" value="选择资料" onclick="chooseData()" /> -->
<!-- 	</div> -->
<!-- 	<div class="col-xs-2 col-sm-2"></div> -->
<!-- </div> -->










<div class="row cl">
	<label class="form-label col-xs-2 col-sm-2">测试试卷：</label>
	<div class="formControls col-xs-8 col-sm-8">
		<div id="shijuan">
			<c:if test="${studyTask.paperid!=null}">
				<div class="divclass">
					<input readonly="readonly" type="text" id="paperName"
						name="paperName" value="${studyTask.paperName}" /><span
						onclick="checkSpan(this)"><i
						class='Hui-iconfont Hui-iconfont-close2'></i></span> <input type="hidden"
						id="paperid" name="paperid" value="${studyTask.paperid}">
				</div>
			</c:if>
		</div>
		<input style="margin-top: 5px;" class="btn btn-primary radius r"
			type="button" value="选择试卷" onclick="choosePaper()" />
	</div>
	<div class="col-xs-2 col-sm-2"></div>
</div>
<!-- <div class="row cl"> -->
<!-- 	<label class="form-label col-xs-2 col-sm-2">测试试卷：</label> -->
<!-- 	<div class="formControls col-xs-8 col-sm-8"> -->
<!-- 		<div id="shijuan"> -->
<!-- 			<c:if test="${studyTask.paperid!=null}"> -->
<!-- 				<div class="divclass"> -->
<!-- 					<input readonly="readonly" type="text" id="paperName" -->
<!-- 						name="paperName" value="${studyTask.paperName}" /> <input -->
<!-- 						type="hidden" id="paperid" name="paperid" -->
<!-- 						value="${studyTask.paperid}"> <span -->
<!-- 						onclick="checkSpan(this)" style="float: right;"><img -->
<!-- 						src="<%=path%>/plug-in/web/portals/image/cancel.png" width="24px" -->
<!-- 						height="25px" /></span> -->
<!-- 				</div> -->
<!-- 			</c:if> -->
<!-- 		</div> -->
<!-- 		<input style="margin-top: 5px;" class="btn btn-primary radius r" -->
<!-- 			type="button" value="选择试卷" onclick="choosePaper()" /> -->
<!-- 	</div> -->
<!-- 	<div class="col-xs-2 col-sm-2"></div> -->
<!-- </div> -->










<div class="row cl">
	<label class="form-label col-xs-2 col-sm-2">发布日期：</label>
	<div class="formControls col-xs-8 col-sm-8">
		<input type="text" placeholder="发布日期" value="${studyTask.startTime}"
			datatype="*8-20" name="startTime"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
			id="datemin" class="input-text Wdate" nullmsg="发布日期不能为空">
	</div>
	<div class="col-xs-2 col-sm-2"></div>
</div>
<div class="row cl">
	<label class="form-label col-xs-2 col-sm-2">终止日期：</label>
	<div class="formControls col-xs-8 col-sm-8">
		<input type="text" placeholder="终止日期" value="${studyTask.endTime }"
			name="endTime" datatype="*8-20"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'datemin\')}'})"
			id="datemax" class="input-text Wdate" nullmsg="终止日期不能为空">
	</div>
	<div class="col-xs-2 col-sm-2"></div>
</div>

<div class="row cl ">
	<label class="form-label col-xs-2 col-sm-2">任务状态：</label>
	<!-- 这里增加类属性 skin-minimal -->
	<div class="formControls col-xs-8 col-sm-8 skin-minimal">
		<!-- 这里增加类属性  class="radio-box" -->
		<div class="radio-box">
			<input type="radio" id="flag-0" class="cl-flag" name="status"
				value="1" checked="checked"
				${studyTask.status == 1? 'checked="checked"':''}> <label
				for="flag-0">正常</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div class="radio-box">
			<input type="radio" id="flag-0" class="cl-flag" name="status"
				value="0" ${studyTask.status == 0? 'checked="checked"':''}><label
				for="flag-0">失效</label>
		</div>
	</div>
	<div class="col-xs-2 col-sm-2"></div>
</div>

<div class="row cl">
	<label class="form-label col-xs-2 col-sm-2">任务备注：</label>
	<div class="formControls col-xs-8 col-sm-8">
		<textarea style="height:200px; resize:none;" name="taskMemo"
			placeholder="说点什么...."
			onchange="if(value.length>200)value=value.substr(0,200)"
			class="input-text" id="dataMemo" cols="30" rows="10">${studyTask.taskMemo }</textarea>
	</div>
</div>
