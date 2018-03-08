<#list formdetails as formdetail>
<#if formdetail.code2?length gt 0>
var _${formdetail.obj.name} = ${formdetail.code2};
form.register(_${formdetail.obj.name});
<#if formdetail.obj.getDetailType()=='FORMITEM'>
<#if formdetail.obj.getPSCodeList()??>
<#assign codelist=formdetail.obj.getPSCodeList()>
_${formdetail.obj.name}.setAsyncConfig({items:[<#if codelist.getPSCodeItems()??><#list codelist.getPSCodeItems() as codeitem><#if (codeitem_index>0)>,</#if>{text:'${codeitem.text}',value:'${codeitem.value}',realtext:'${codeitem.realText}'<#if codeitem.getParentValue()??>,parentvalue='${codeitem.getParentValue()}'</#if><#if codeitem.getColor()??>,color:'${codeitem.getColor()}'</#if><#if codeitem.getTextCls()??>,textcls:'${codeitem.getTextCls()}'</#if><#if codeitem.getIconCls()??>,iconcls:'${codeitem.getIconCls()}'</#if>}</#list></#if> ]});
</#if>
<#if formdetail.obj.isAllowEmpty()>_${formdetail.obj.name}.setAllowBlank(true);<#else>_${formdetail.obj.name}.setAllowBlank(false);</#if>
</#if>
</#if>
</#list>