<#assign deuiaction=item.getPSUIAction()>
<#if deuiaction.getDataAccessAction()??>
<% if(Page.getCurrent().isShowAction("${deuiaction.getDataAccessAction()}")){ %>
</#if>
<#if deuiaction.getUIActionTag()=='ExportExcel'>
<div class="btn ibiz-toolbar-item ibiz-id-dropdown" data-ibiz-tag="ExportExcel" style="padding:1px;">
	<div class="input-group-btn" style="width:auto;">
		<button type="button" class="btn green dropdown-toggle ibiz-id-dropdown" data-toggle="dropdown"><#if item.getCapPSLanguageRes()??><ibiz5:message code="${item.getCapPSLanguageRes().getLanResTag()}" text="${item.caption}"></ibiz5:message><#else>${item.caption}</#if>
		<i class="fa fa-angle-down"></i></button>
		<ul class="dropdown-menu  ">
			<li>
				<a class="ibiz-id-item" data-ibiz-tag="all" href="javascript:;"><ibiz5:message code="TBB.TEXT.*.EXPORT.ALL" text="导出全部"></ibiz5:message>
				<span style="color: #9eacb4;font-size: 13px;">&nbsp;<ibiz5:message code="TBB.TEXT.*.EXPORT.MAX" text="最大导出1000行"></ibiz5:message></span>
				</a>
			</li>
			<li>
				<a class="ibiz-id-item" data-ibiz-tag="current" href="javascript:;"><ibiz5:message code="TBB.TEXT.*.EXPORT.CURRENT" text="导出当前页"></ibiz5:message></a>
			</li>
			<li>
			<div style="padding:8px 14px">
				<ibiz5:message code="TBB.TEXT.*.EXPORT.SPEC" text="导出第"></ibiz5:message><input class="ibiz-id-start" style="width:35px"/>~<input class="ibiz-id-end" style="width:35px"/><ibiz5:message code="TBB.TEXT.*.EXPORT.UNIT" text="页"></ibiz5:message>
				<button class="btn blue ibiz-id-item" data-ibiz-tag="custom" type="button">Go!</button>
				</div>
			</li>
		</ul>
	</div>
</div>
<#else>
<button title="<#if item.getTooltipPSLanguageRes()??><ibiz5:message code="${item.getTooltipPSLanguageRes().getLanResTag()}" text="${item.tooltip}"></ibiz5:message><#else>${item.tooltip}</#if>"  data-ibiz-tag="${deuiaction.getUIActionTag()}" data-ibiz-target="${deuiaction.getActionTarget()}" <#if deuiaction.getDataAccessAction()??>data-ibiz-priv="${deuiaction.getDataAccessAction()}" </#if> class="btn <#if item.isShowCaption()><#else>btn-icon-only</#if><#if item.getNoPrivDisplayMode() == 6> hide</#if> <#if item.getPSSysCss()??>${item.getPSSysCss().getCssName()}</#if>  ibiz-toolbar-item" >
        <#if item.isShowIcon() && item.getPSSysImage()??><#assign img=item.getPSSysImage()><#if img.getCssClass()?? && (img.getCssClass()?length gt 0)><i class="${img.getCssClass()}"></i></#if></#if>   
        <#if item.isShowCaption()><span ><#if item.getCapPSLanguageRes()??><ibiz5:message code="${item.getCapPSLanguageRes().getLanResTag()}" text="${item.caption}"></ibiz5:message><#else>${item.caption}</#if></span></#if>
</button>
<#if deuiaction.getDataAccessAction()??>
<%} %>
</#if>
</#if>