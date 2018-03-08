<div id="<%=strCId%>${ctrl.name}"  class="tab-content">
<#list ctrl.getPSDEEditForms() as form>
<div class="tab-pane">
${srfctrl('${form.name}').code}
</div>
</#list>
</div>