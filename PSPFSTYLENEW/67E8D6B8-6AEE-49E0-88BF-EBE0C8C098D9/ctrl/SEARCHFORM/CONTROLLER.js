var form=this.getSearchForm();
<#list formdetails as formdetail>
<#if formdetail.code?length gt 0>
form.register(${formdetail.code});
</#if>
</#list>