<div id="<%=strCId%>${ctrl.name}" class="form-horizontal" style="height:auto; overflow:hidden">
<!-- 表单区:开始-->
 <div class="col-md-10 row">
<#list hiddens as hidden>${hidden.code}</#list>
<#if ctrl.isNoTabHeader() && !ctrl.isEnableAdvanceSearch()>
<#list formpages as formpage>  
  ${formpage.code}  
</#list>
<#else>
<ul class="nav nav-tabs" id="<%=strCId%>${ctrl.name}_tab">
<#list formpages as formpage>
   <li class="<#if (formpage_index ==0)>active</#if>"><a href="#<%=strCId%>${ctrl.name}_${formpage.obj.codeName}_tab" data-toggle="tab">${formpage.obj.caption}</a></li>
</#list>
<#if ctrl.isEnableAdvanceSearch()>
  <li class=""><a href="#<%=strCId%>${ctrl.name}_customsearchpage_tab" data-toggle="tab">自定义搜索</a></li>
</#if>
</ul>
<div class="tab-content">
<#list formpages as formpage>
  <div class="tab-pane <#if (formpage_index ==0)>active</#if>" id="<%=strCId%>${ctrl.name}_${formpage.obj.codeName}_tab" >${formpage.code}</div>
</#list>
<#if ctrl.isEnableAdvanceSearch()>
  <div class="tab-pane " id="<%=strCId%>${ctrl.name}_customsearchpage_tab" >
    <DIV id='<%=strCId%>${ctrl.name}_customsearchpanel' class=" col-md-12 form-group" data-ibiz-allowblank="1">
	<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>
	<td width="130" valign="center"><button class="btn btn-success" id="<%=strCId%>${ctrl.name}_addsearchbutton" >添加条件</button></td>
	<td>
	<div class="col-md-12" >
	  <div class="input-group  ibiz-field-textarea">
	    <textarea id="<%=strCId%>${ctrl.name}_searchtext" type="text"  class="form-control" style="height: 120px; min-height: 20px;max-height:200px;overflow:auto;width:100%"  disabled></textarea>
          </div>
	</div>
	</td>
	</tr>
	</table>
	</DIV>
  </div>
</#if>
</div>
</#if>        
</div>
<!-- 表单区:结束-->
<!-- 按钮区:开始-->
 <div class="col-md-2 row" style="float:right;">
    <button id="<%=strCId%>${ctrl.name}_resetbutton" title="<ibiz5:message code="CONTROL.SPEX.RESET.TIPS" text="重置"></ibiz5:message>"  class=" btn " style="float:right;margin-right:0px;">
     <span ><ibiz5:message code="CONTROL.SPEX.RESET.TEXT" text="重置"></ibiz5:message></span>
   </button>

    <button id="<%=strCId%>${ctrl.name}_searchbutton" title="<ibiz5:message code="CONTROL.SPEX.SEARCH.TIPS" text="搜索"></ibiz5:message>"  class=" btn green " style="float:right;margin-right:8px;">
     <span ><ibiz5:message code="CONTROL.SPEX.SEARCH.TEXT" text="搜索"></ibiz5:message></span>
</button>
</div>
<!-- 按钮区:结束-->
</div>