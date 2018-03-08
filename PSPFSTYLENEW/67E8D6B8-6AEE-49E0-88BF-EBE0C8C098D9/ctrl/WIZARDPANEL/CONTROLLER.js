//wizardPanel
<#list ctrl.getPSDEEditForms() as form>
if(true){
  var form = wizardPanel.registerForm({id: this.getCId2()+"${form.name}",ctrler:this,srfctrlid:"${form.name}",name:""});
  ${srfctrl('${form.name}').code?replace('var form=this.getForm();','')}
}
</#list>