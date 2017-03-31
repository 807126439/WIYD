<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link href="${path}/plug-in/web/portals/css/common/reset.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/common.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/base.css" rel="stylesheet" type="text/css" />	
	<link href="${path}/plug-in/web/portals/css/activity/mod-fc_vert.css" rel="stylesheet" type="text/css" />

  </head>
<body>
	<#include "/common/head.ftl"/>
 	
 	<div class="container" class="clearfix">
    <div class="mainplaceholder"></div>
    <div id="main" class="clearfix">
   <#-- 
    <div class="sidemenu">
      <div class="sidemenu-header clearfix">
        <div class="sidemenu-uppercase">D</div>
        <div class="sidemenu-headertitle">
          <p class="sm-ht_zh">发展党员档案</p>
          <p class="sm-ht_en">Development Party Archives</p>
        </div>
      </div>
      <ul class="sidemenu-list">
        <li class="sidemenu-item active"><a href="${path}/introduction.do">发展党员流程图</a></li>
        <li class="sidemenu-item"><a href="#">添加入党申请人</a></li>
        <li class="sidemenu-item"><a href="#">添加入党申请人</a></li>
        <li class="sidemenu-item"><a href="#">添加入党申请人</a></li>
      </ul>
    </div> -->
    <#include "/activity/common/leftMenu.ftl"/>
       
    <div class="mod">
     <#--
      <h2 class="mod-title">
        <i class="icon_mod"></i>
        <div class="mod-loc">
          <div class="loc clearfix">
            <span>当前位置：</span>
            <ol>
              <li class="noactive"><a href="${path}/portals.do">首页</a></li>
              <li class="noactive"><a href="#">发展党员档案</a></li>
              <li class="active">发展党员流程图</li>
            </ol>
          </div>
        </div>
        发展党员流程图 
      </h2>-->
            <#include "/activity/common/nav.ftl"/>
      
      <div class="mod-content">
        <div class="fc_dpm">
          <div class="fc_doshed"></div>
          <div class="fc_dpm-level fc_dpm-level_1">
            <div class="fc_dpm-nodewrapper">
              <div class="fc_dpm-node"><i class="fc_dpm-icon_node"></i>个人申请
              </div>
                <ul class="fc_dpm-node-info">
                  <li><em>申请人</em>向所在党支部递交<strong>入党申请书</strong></li>
                </ul>
              <div class="fc_dpm-branch"></div>
            </div>
          </div>
          <div class="fc_dpm-level fc_dpm-level_2">
            <div class="fc_dpm-nodewrapper">
              <div class="fc_dpm-node"><i class="fc_dpm-icon_node"></i>确定为入党积极分子
              </div>
                <ul class="fc_dpm-node-info">
                  <li><b>党小组</b>向<b>党支部</b>推荐入党积极分子</li>
                  <li><b>支委会</b>研究讨论确定入党积极分子</li>
                  <li><b>党支部</b>指定<b>培养联系人</b></li>
                  <li><b>党支部</b>填写<strong>《入党积极分子名册》</strong>及<strong>《入党积极分子培养教育考察表》</strong></li>
                </ul>
              <div class="fc_dpm-branch"></div>
            </div>
          </div>
          <div class="fc_dpm-level fc_dpm-level_3">
            <div class="fc_dpm-nodewrapper">
              <div class="fc_dpm-node"><i class="fc_dpm-icon_node"></i>进行入党积极分子培训
              </div>
              <ul class="fc_dpm-node-info">
                <li><b>党支部</b>制定培养、教育和考察措施</li>
                <li><em>入党积极分子</em>定期向党支部递交<strong>思想汇报</strong></li>
                <li><b>培养联系人</b>对入党积极分子进行教育考察，并把每半年考察情况填入<strong>《入党积极分子培养考察情况表》</strong></li>
                <li><b>基层党委</b>培训入党积极分子</li>
              </ul>
              <div class="fc_dpm-branch"></div>
            </div>
          </div>
          <div class="fc_dpm-level fc_dpm-level_4">
            <div class="fc_dpm-nodewrapper">
              <div class="fc_dpm-node"><i class="fc_dpm-icon_node"></i>政治审查
              </div>
                <ul class="fc_dpm-node-info">
                  <li>入党积极分子经过<strong>1</strong>年以上的培养教育和考察，支委会征求党内外群众对入党积极分子的意见</li>
                  <li><b>党支部</b>初步确定发展对象。</li>
                  <li><b>党支部</b>对初步确定的发展对象进行<b>政审</b>（包括计划生育执行情况，有无违法违纪行为等）</li>
                  <li><b>党支部</b>写出<strong>政审报告</strong></li>
                  <li><b>支部</b>将发展对象的有关情况在本单位张榜进行<b>公示</b>，公示时间为5个工作日</li>
                  <li><b>支部</b>将发展对象的有关情况在本单位张榜进行<b>公示</b>，公示时间为5个工作日</li>
                  <li><b>支委会</b>综合各方面的情况及意见，研究并提出能否接收入党的意见，形成<strong>党支部综合审查报告</strong></li>
                </ul>
              <div class="fc_dpm-branch"></div>
            </div>
          </div>
          <div class="fc_dpm-level fc_dpm-level_5">
            <div class="fc_dpm-nodewrapper">
              <div class="fc_dpm-node"><i class="fc_dpm-icon_node"></i>列为重点发展对象
              </div>
                <ul class="fc_dpm-node-info">
                  <li><b>支委会</b>确定正式发展对象</li>
                  <li><b>党支部</b>在支委会确定正式发展对象后20日内，将发展对象情况，包括<strong>入党申请书</strong>、<strong>《入党积极分子培养教育考察表》</strong>、<b>培训情况</b>、<strong>思想汇报</strong>、<strong>政审材料</strong>、<strong>党支部综合审查报告</strong>等材料报<b>上级党委</b>审查</li>
                  <li><b>上级党委</b>接下级党支部送审材料后在1个月内审查完毕。审查同意后，下发<strong>《入党志愿书》</strong></li>
                  <li><b>党支部</b>确定<b>入党介绍人</b></li>
                </ul>
              <div class="fc_dpm-branch"></div>
            </div>
          </div>
          <div class="fc_dpm-level fc_dpm-level_6">
            <div class="fc_dpm-nodewrapper">
              <div class="fc_dpm-node"><i class="fc_dpm-icon_node"></i>填写《入党志愿书》
              </div>
              <div class="fc_dpm-branch"></div>
                <ul class="fc_dpm-node-info">
                  <li><b>党支部组织委员</b>对发展对象进行忠诚老实教育，并向其解释填写内容与要求</li>
                  <li><em>发展对象</em>填写<strong>《入党志愿书》</strong>有关部分</li>
                  <li><b>入党介绍人</b>分别填写<strong>《入党介绍人的意见》</strong></li>
                  <li><b>党支部组织委员</b>审查<strong>《入党志愿书》</strong>的填写情况</li>
                </ul>
            </div>
          </div>
          <div class="fc_dpm-level fc_dpm-level_7">
            <div class="fc_dpm-nodewrapper">
              <div class="fc_dpm-node"><i class="fc_dpm-icon_node"></i>召开支部党员大会
              </div>
                <ul class="fc_dpm-node-info">
                  <li><b>党支部</b>自发展对象经上级党委审查同意后1个月内，应召开接收预备党员的<b>支部大会</b></li>
                  <li>及时填写<strong>《入党志愿书》</strong>中“<strong>支部大会通过接收申请人为预备党员的决议</strong>”栏目</li>
                  <li><b>党支部</b>自支部大会通过决议之日起20日内，将所有材料及支部大会会议记录复印件报所属上级党委</li>
                </ul>
              <div class="fc_dpm-branch"></div>
            </div>
          </div>
          <div class="fc_dpm-level fc_dpm-level_8">
            <div class="fc_dpm-nodewrapper">
              <div class="fc_dpm-node"><i class="fc_dpm-icon_node"></i>预备党员审核备案
              </div>
                <ul class="fc_dpm-node-info">
                  <li><b>党委组织委员</b>在审查发展对象材料基础上，对发展对象进行<b>考察</b>并与其进行入党前<b>谈话</b>，填写<strong>《上级党组织指派专人进行谈话情况和对申请人入党的意见》</strong></li>
                  <li><b>党委</b>自支部大会通过决议之日起3个月内审批完毕，向支部下发<strong>《批准入党通知书》</strong></li>
                </ul>
              <div class="fc_dpm-branch"></div>
            </div>
          </div>
          <div class="fc_dpm-level fc_dpm-level_9">
            <div class="fc_dpm-nodewrapper">
              <div class="fc_dpm-node"><i class="fc_dpm-icon_node"></i>预备党员转正
              </div>
                <ul class="fc_dpm-node-info">
                  <li><em>预备党员</em>预备期满提出<strong>转正申请</strong></li>
                  <li><b>支委会</b>征求党小组、党内外群众的意见，并进行公示</li>
                  <li>综合各方面情况和意见，形成<strong>预备期综合审查报告</strong></li>
                  <li><b>党支部</b>在预备党员预备期满1个月内召开党员大会对预备党员进行讨论表决，作出是否按期或延期转正，以及取消预备资格的决议，并按时填写<strong>《支部大会通过预备党员能否转为正式党员的决议》</strong></li>
                  <li><b>党支部</b>自支部大会通过决议之日起20日内，将所有材料报上级党委审批</li>
                  <li><b>上级党委</b>自支部大会通过决议之日起3个月内审批完毕，并向基层支部下发预备党员是否近期转正，或延期转正，以及取消预备资格的<b>通知</b></li>
                  <li><b>党支部</b>通知预备党员是否转正，并与其进行谈话</li>
                  <li><b>材料归档</b></li>
                </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- mod -->
    </div>
     </div> 
 	
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/fc_vert.js"></script> 

</body>
</html>