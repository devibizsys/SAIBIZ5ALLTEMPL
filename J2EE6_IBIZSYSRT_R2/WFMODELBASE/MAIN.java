package ${pub.getPKGCodeName()}.srv.workflow;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.lang.annotation.Annotation;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import net.ibizsys.paas.codelist.CodeItem;
import net.ibizsys.paas.codelist.CodeItems;
import net.ibizsys.paas.codelist.CodeList;
import net.ibizsys.paas.sysmodel.DynamicCodeListModelBase;
import net.ibizsys.paas.sysmodel.StaticCodeListModelBase;
import net.ibizsys.paas.sysmodel.CodeListGlobal;
import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.sysmodel.SysModelGlobal;
import net.ibizsys.paas.sysmodel.ISystemModel;
import net.ibizsys.pswf.core.IWFService;
import net.ibizsys.pswf.core.IWFVersionModel;
import ${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel;


/**
 * ${item.name} 流程模型
 */
public class ${item.codeName}WFModel extends ${pub.getBaseClassPKGCodeName()}.pswf.core.WFModelBase{

    public ${item.codeName}WFModel() throws Exception{
       
        super();
        this.setId("${item.id}");
        this.setName("${item.name}");
<#if item.getRemindMsgTemplId()??>
        this.setRemindMsgTemplId("${item.getRemindMsgTemplId()}");
</#if>
        //注册流程 
        get${sys.codeName}SysModel().registerWFModel(this);

        //设置相关代码表
        this.setWFStepCodeList(CodeListGlobal.getCodeList("${pub.getPKGCodeName()}.srv.codelist.${item.getWFStepPSCodeList().codeName}CodeListModel"));
        this.setEntityStateCodeList(CodeListGlobal.getCodeList("${pub.getPKGCodeName()}.srv.codelist.${item.getEntityStatePSCodeList().codeName}CodeListModel"));
 
        //设置业务状态中的流程状态
<#if item.getEntityWFStates()??>
<#list  item.getEntityWFStates() as wfState>
       this.registerEntityWFState("${wfState}");
</#list>
</#if>      
         //注册流程版本
	prepareWFVersionModels();

        //准备流程服务
	prepareWFService();
     }

     /**
      * 准备流程版本
      * @throws Exception
      */
	protected void prepareWFVersionModels() throws Exception
	{
<#if item.getPSWFVersions()??>
<#list item.getPSWFVersions() as wfversion>
            IWFVersionModel ${srfparamname('${wfversion.codeName}')}WFVersionModel = (IWFVersionModel)get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.workflow.${wfversion.codeName}WFVersionModel");
            ${srfparamname('${wfversion.codeName}')}WFVersionModel.init(this);
            this.registerWFVersionModel(${srfparamname('${wfversion.codeName}')}WFVersionModel);
</#list>
</#if>
	}
 
       /**
	 * 准备流程服务
	 * @throws Exception
	 */
	protected void prepareWFService() throws Exception
	{
		IWFService iWFService = (IWFService)get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.workflow.${item.codeName}WFService");
		iWFService.init(this);
		this.setWFService(iWFService);
	}


    private ${sys.codeName}SysModel ${srfparamname('${sys.codeName}')}SysModel;
      public  ${sys.codeName}SysModel get${sys.codeName}SysModel() {
           if(this.${srfparamname('${sys.codeName}')}SysModel==null)
           {
               try
                {
                      this.${srfparamname('${sys.codeName}')}SysModel = (${sys.codeName}SysModel)SysModelGlobal.getSystem("${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel");
                }
                catch(Exception ex)
                {
                }
           }       
           return this.${srfparamname('${sys.codeName}')}SysModel;
      }

       @Override
       public  ISystemModel getSystemModel() {
        return this.get${sys.codeName}SysModel();
     }


 
}