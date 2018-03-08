package ${pub.getPKGCodeName()}.srv.counter;


import net.ibizsys.paas.demodel.IDataEntityModel;
import net.ibizsys.paas.demodel.DEModelGlobal;
import net.ibizsys.paas.ctrlhandler.CounterGlobal;





/**
 * ${item.name}计数器处理基类
 */
public class ${item.codeName}CounterHandler extends ${item.getBaseClass('HANDLER.${pub.getSFStyle()}')}{

   public ${item.codeName}CounterHandler(){
        super();
        
        this.setId("${item.id}");
        this.setName("${item.name}");

        CounterGlobal.registerCounterHandler("${pub.getPKGCodeName()}.srv.counter.${item.codeName}CounterHandler", this);
        CounterGlobal.registerCounterHandler("${item.id}", this);
   }

   protected void onInit() throws Exception{
<#if item.getPSSysCounterItems()??>
<#list item.getPSSysCounterItems() as counterItem>
        registerCounterItem("${counterItem.name}","");
</#list> 
</#if>        
        super.onInit();         
   }
   
   
<#if item.getCounterType()=='DEDR'>   
   <#if item.getPSDataEntity()??>
   <#assign de=item.getPSDataEntity()>
        private ${de.getClassOrPkgName('DEMODEL',pub)} ${srfparamname('${de.codeName}')}DEModel;
        protected  ${de.getClassOrPkgName('DEMODEL',pub)} get${de.codeName}DEModel() throws Exception{
               if(this.${srfparamname('${de.codeName}')}DEModel==null){
            	   this.${srfparamname('${de.codeName}')}DEModel = (${de.getClassOrPkgName('DEMODEL',pub)})DEModelGlobal.getDEModel("${de.getClassOrPkgName('DEMODEL',pub)}");
               }
              return this.${srfparamname('${de.codeName}')}DEModel;
         }

         @Override
         protected IDataEntityModel getDEModel() throws Exception{
             return this.get${de.codeName}DEModel();
         }
   </#if>
   </#if>
}