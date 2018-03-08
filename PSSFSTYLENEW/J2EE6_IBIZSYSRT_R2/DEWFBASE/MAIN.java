package ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.demodel;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.entity.${de.codeName};
import ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.service.${de.codeName}Service;

/**
 * 实体[${de.name}]工作流[${item.name}]模型基类
 */
public class ${de.codeName}${item.codeName}DEWFModel extends ${pub.getBaseClassPKGCodeName()}.pswf.core.DEWFModelBase{
  
   private static final Log log = LogFactory.getLog(${de.codeName}${item.codeName}DEWFModel.class);

   public ${de.codeName}${item.codeName}DEWFModel(){
        super();

         //设置标识
         this.setId("${item.getId()}"); 
         //设置工作流标识
         this.setWorkflowId("${item.getWorkflowId()}"); 
         //设置实体流程名称
         this.setName("${item.codeName}"); 
          
<#if item.getWFStepPSDEField()??>
         //设置流程步骤属性 ${item.getWFStepPSDEField().logicName} 
         this.setWFStepField("${item.getWFStepPSDEField().name}");
</#if> 
<#if item.getWFStatePSDEField()??>
         //设置流程状态属性 ${item.getWFStatePSDEField().logicName} 
         this.setWFStateField("${item.getWFStatePSDEField().name}");
</#if> 
<#if item.getUDStatePSDEField()??>
         //设置用户数据状态属性 ${item.getUDStatePSDEField().logicName}  
         this.setUDStateField("${item.getUDStatePSDEField().name}");
</#if>
<#if item.getWFInstPSDEField()??>
         //设置流程实例属性 ${item.getWFInstPSDEField().logicName}  
         this.setWFInstField("${item.getWFInstPSDEField().name}");
</#if>
<#if item.getWFActorsPSDEField()??>
         //设置流程操作者属性 ${item.getWFActorsPSDEField().logicName}  
         this.setWFActorsField("${item.getWFActorsPSDEField().name}");
</#if>
<#if item.getEntityWFState()??>
         //设置流程状态值 
         this.setEntityWFState("${item.getEntityWFState()}");
</#if>
<#if item.getWFRetPSDEField()??>
         //设置流程返回值属性 ${item.getWFRetPSDEField().logicName}  
         this.setWFRetField("${item.getWFRetPSDEField().name}");
</#if>
<#if item.getWFVerPSDEField()??>
         //设置流程版本属性 ${item.getWFVerPSDEField().logicName}  
         this.setWFVerField("${item.getWFVerPSDEField().name}");
</#if>
<#if item.getWorkflowPSDEField()??>
		//设置流程标识属性 ${item.getWorkflowPSDEField().logicName}  
		this.setWorkflowField("${item.getWorkflowPSDEField().name}");
</#if>

    }
   
   
}