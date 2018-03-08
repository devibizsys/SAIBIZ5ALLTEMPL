var ${view.codeName}ControllerBase = GridView8ControllerBase.extend({
<#SRFINC(CONTROLLER_BASE)>
,onInit:function(){
   arguments.callee.$.onInit.call(this);
<#if view.hasPSControl('searchform')>
   if(true){
   ${searchform.code} 
   }
</#if>
<#if view.hasPSControl('totalsearchform')>
   if(true){
   var form = this.getTotalSearchForm();
   ${totalsearchform.code?replace('var form=this.getSearchForm();','')}
   }
</#if>
}
<#SRFINC(MDVIEWCONTROLLER_BASE_NOINIT)>
<#SRFINC(GRIDVIEW8CONTROLLER_BASE)>     
});