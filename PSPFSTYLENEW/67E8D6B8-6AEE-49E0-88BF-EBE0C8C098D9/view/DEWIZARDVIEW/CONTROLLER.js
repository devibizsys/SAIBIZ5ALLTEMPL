var ${view.codeName}ControllerBase = WizardViewControllerBase.extend({
        <#SRFINC(CONTROLLER_BASE)>
,getDefaultCfg:function(cfg){
    var me=this;     
    var config={backendurl:cfg.appctx+'/${view.getPSAppModule().getCodeName()}/${view.codeName}.do?'+'srfcid='+cfg.containerid+'&'
                ,viewurl:'/${view.getPSAppModule().getCodeName()?lower_case}/${view.codeName?lower_case}.jsp?'+'srfcid='+cfg.containerid+'&'
               ,ctrls:{form:{}}};
    return  $.extend({},config,cfg);
} 
,onInit: function(){
  arguments.callee.$.onInit.call(this);
  var wizardPanel = this.getWizardPanel();
  ${wizardpanel.code}

<#if wizardpanel.getObj()??>
<#if wizardpanel.getObj().getPSDEWizard()??>
<#assign wizard=wizardpanel.getObj().getPSDEWizard()>
<#if wizard.getPSDEWizardForms()??>
<#list wizard.getPSDEWizardForms() as form>
<#list form.getStepActions() as action>
  wizardPanel.registerformAction(this.getCId2()+"wizardpanel_form_"+"${form.getFormTag()}","${action}");
</#list></#list></#if></#if></#if>
}
,onWizardFormFieldChanged: function(formName, fieldname, sender){
     var me=this;
    if(!formName){
       return;
    }
    <#assign wizardPanel=view.getPSControl('wizardpanel')>
    <#list wizardPanel.getPSDEEditForms() as form>
       if(formName=='${form.name}'){
           var form=me.getWizardPanel().getForm(formName);
           if(form){
 <#comment>              <#assign form_fdlogics=srfpubparam(form.name+'_fdlogics')> </#comment>
             <#if srfpubparam(form.name+'_fdlogics')??>
                   <#list srfpubparam(form.name+'_fdlogics') as fdlogic>
                   ${fdlogic}
                   </#list>
              </#if>
           }
       }
    </#list>
  
   
 <#comment> 
<#if form.getPSDEFormItems()??>
<#list form.getPSDEFormItems() as formitem>
<#if formitem.getPSDEFormItemUpdate()??>
    if(fieldname=='${formitem.name}'){
       form.updateFormItems({srfufimode:'${formitem.getPSDEFormItemUpdate().codeName}'});
    }
</#if>
</#list>
</#if>
 </#comment>
}
});