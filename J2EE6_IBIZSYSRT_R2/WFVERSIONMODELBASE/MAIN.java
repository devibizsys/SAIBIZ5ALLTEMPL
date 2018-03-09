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
import net.ibizsys.pswf.core.IWFLinkSingleCondModel;
import net.ibizsys.pswf.core.WFStartProcessModelBase;
import net.ibizsys.pswf.core.WFEndProcessModelBase;
import net.ibizsys.pswf.core.WFInteractiveProcessModelBase;
import net.ibizsys.pswf.core.WFDEActionProcessModelBase;
import net.ibizsys.pswf.core.WFEmbedWFProcessModelBase;
import net.ibizsys.pswf.core.WFParallelSubWFProcessModelBase;
import net.ibizsys.pswf.core.WFInteractiveLinkModelBase;
import net.ibizsys.pswf.core.WFTimeoutLinkModelBase;
import net.ibizsys.pswf.core.WFRouteLinkModelBase;
import net.ibizsys.pswf.core.WFEmbedWFReturnModelBase;
import net.ibizsys.pswf.core.WFProcRoleModel;
import net.ibizsys.pswf.core.WFProcSysActorRoleModel;
import net.ibizsys.pswf.core.WFProcUDActorRoleModel;
import net.ibizsys.pswf.core.WFDEActionProcessParamModel;
import net.ibizsys.pswf.core.WFLinkGroupCondModel;
import net.ibizsys.pswf.core.WFLinkSingleCondModel;
import net.ibizsys.pswf.core.WFLinkCustomCondModel;
import net.ibizsys.pswf.core.WFProcSubWFModel;


/**
 * ${item.name} 流程模型
 */
public class ${item.codeName}WFVersionModel extends ${pub.getBaseClassPKGCodeName()}.pswf.core.WFVersionModelBase  {

    //定义流程处理
<#if item.getPSWFProcesses()??>
<#list item.getPSWFProcesses() as wfprocess>
<#if wfprocess.getWFProcessType()=='START'>
     /**
      * 开始处理
      */
     class P${wfprocess_index?c}WFProcessModel extends WFStartProcessModelBase{
         public P${wfprocess_index?c}WFProcessModel(){
            super();
            this.setId("${wfprocess.id}");
            this.setName("${wfprocess.name}");
<#if wfprocess.getNamePSLanguageRes()??>
            this.setNameLanResTag("${wfprocess.getNamePSLanguageRes().getLanResTag()}");
</#if>
            this.setLeftPos(${wfprocess.getLeftPos()?c});
            this.setTopPos(${wfprocess.getTopPos()?c});
			this.setThreadSN(${wfprocess.getThreadSN()?c});
			<#if (wfprocess.getThreadShowName()??) && (wfprocess.getThreadShowName()?length gt 0)>
            this.setThreadShowName("${wfprocess.getThreadShowName()}");
</#if>
         }
     }
</#if>
<#if wfprocess.getWFProcessType()=='END'>
     /**
      * 结束处理
      */
     class P${wfprocess_index?c}WFProcessModel extends WFEndProcessModelBase{
         public P${wfprocess_index?c}WFProcessModel(){
            super();
            this.setId("${wfprocess.id}");
            this.setName("${wfprocess.name}");
<#if wfprocess.getNamePSLanguageRes()??>
            this.setNameLanResTag("${wfprocess.getNamePSLanguageRes().getLanResTag()}");
</#if>
            this.setLeftPos(${wfprocess.getLeftPos()?c});
            this.setTopPos(${wfprocess.getTopPos()?c});
			this.setThreadSN(${wfprocess.getThreadSN()?c});
<#if (wfprocess.getThreadShowName()??) && (wfprocess.getThreadShowName()?length gt 0)>
            this.setThreadShowName("${wfprocess.getThreadShowName()}");
</#if>
         }
     }
</#if>
<#if wfprocess.getWFProcessType()=='INTERACTIVE'>
     /**
      * 交互处理
      */
     class P${wfprocess_index?c}WFProcessModel extends WFInteractiveProcessModelBase{
         public P${wfprocess_index?c}WFProcessModel(){
            super();
            this.setId("${wfprocess.id}");
            this.setName("${wfprocess.name}");
<#if wfprocess.getNamePSLanguageRes()??>
            this.setNameLanResTag("${wfprocess.getNamePSLanguageRes().getLanResTag()}");
</#if>
            this.setLeftPos(${wfprocess.getLeftPos()?c});
            this.setTopPos(${wfprocess.getTopPos()?c});
            this.setThreadSN(${wfprocess.getThreadSN()?c});
<#if (wfprocess.getThreadShowName()??) && (wfprocess.getThreadShowName()?length gt 0)>
            this.setThreadShowName("${wfprocess.getThreadShowName()}");
</#if>
            this.setWFStepValue("${wfprocess.getWFStepValue()}");
<#if (wfprocess.isEditable())>
            this.setEditable(true);
</#if>
<#if (wfprocess.getMemoField()??) && (wfprocess.getMemoField()?length gt 0)>
            this.setMemoField("${wfprocess.getMemoField()}");
</#if>
<#if (wfprocess.getUserData()??) && (wfprocess.getUserData()?length gt 0)>
            this.setUserData("${wfprocess.getUserData()}");
</#if>
<#if (wfprocess.getUserData2()??) && (wfprocess.getUserData2()?length gt 0)>
            this.setUserData2("${wfprocess.getUserData2()}");
</#if>
<#if (wfprocess.isSendInform()) >
			this.setSendInform(true);
			this.setMsgTemplateId("${wfprocess.getMsgTemplateId()}");
			this.setMsgType(${wfprocess.getMsgType()?c});
</#if>
<#if (wfprocess.isAsynchronousProcess())>
			this.setAsynchronousProcess(true);
</#if>
<#if (wfprocess.isEnableTimeout()) >
			this.setIsEnableTimeout(true);
<#if (wfprocess.getTimeoutType()?? && wfprocess.getTimeoutType()?length gt 0) >
			this.setTimeoutType("${wfprocess.getTimeoutType()}");
</#if>
			this.setTimeout(${wfprocess.getTimeout()?c});
<#if (wfprocess.getTimeoutNext()?? && wfprocess.getTimeoutNext()?length gt 0) >
			this.setTimeoutNext("${wfprocess.getTimeoutNext()}");
</#if>
<#if (wfprocess.getTimeoutField()?? && wfprocess.getTimeoutField()?length gt 0) >
			this.setTimeoutField("${wfprocess.getTimeoutField()}");
</#if>
<#if (wfprocess.getWorkTimeType()?? && wfprocess.getWorkTimeType()?length gt 0) >
			this.setWorkTimeType("${wfprocess.getWorktimeType()}");
</#if>

</#if>
         }
       
          @Override
        protected void onInit() throws Exception
        {
            super.onInit();
        	
        	 //注册处理角色
<#list wfprocess.getWFProcRoleModels() as procrole>
<#if procrole.getWFProcRoleType()=='WFROLE'>
            WFProcRoleModel procRole${procrole_index?c} = new WFProcRoleModel();
            procRole${procrole_index?c}.setWFRoleId("${procrole.getWFRoleId()}");
<#elseif procrole.getWFProcRoleType()=='UDACTOR'>
			WFProcUDActorRoleModel procRole${procrole_index?c} = new WFProcUDActorRoleModel();
			procRole${procrole_index?c}.setUDField("${procrole.getUDField()}");
<#else>
            WFProcSysActorRoleModel procRole${procrole_index?c} = new WFProcSysActorRoleModel();
</#if>
            procRole${procrole_index?c}.setId("${procrole.id}");
            procRole${procrole_index?c}.setName("${procrole.name}");
            procRole${procrole_index?c}.setWFProcRoleType("${procrole.getWFProcRoleType()}");
            procRole${procrole_index?c}.init(this);
            this.registerWFProcRoleModel(procRole${procrole_index?c});
</#list>
        }
     }
</#if>
<#if wfprocess.getWFProcessType()=='PROCESS'>
     /**
      * 实体操作处理
      */
     class P${wfprocess_index?c}WFProcessModel extends WFDEActionProcessModelBase{
         public P${wfprocess_index?c}WFProcessModel(){
            super();
            this.setId("${wfprocess.id}");
            this.setName("${wfprocess.name}");
<#if wfprocess.getNamePSLanguageRes()??>
            this.setNameLanResTag("${wfprocess.getNamePSLanguageRes().getLanResTag()}");
</#if>
            this.setLeftPos(${wfprocess.getLeftPos()?c});
            this.setTopPos(${wfprocess.getTopPos()?c});
            this.setThreadSN(${wfprocess.getThreadSN()?c});
			<#if (wfprocess.getThreadShowName()??) && (wfprocess.getThreadShowName()?length gt 0)>
            this.setThreadShowName("${wfprocess.getThreadShowName()}");
</#if>
<#if wfprocess.getDEActionName()?length gt 0>
            this.setDEActionName("${wfprocess.getDEActionName()}");
</#if>
<#if (wfprocess.isAsynchronousProcess())>
			this.setAsynchronousProcess(true);
</#if>
         }
  
         @Override
        protected void onInit() throws Exception
        {
            super.onInit();
        	
            //注册处理参数
<#if wfprocess.getPSWFProcessParams()??>
<#list wfprocess.getPSWFProcessParams() as procparam>
            WFDEActionProcessParamModel procParam${procparam_index?c} = new WFDEActionProcessParamModel();
            procParam${procparam_index?c}.setDstField("${procparam.getDstField()}");
            procParam${procparam_index?c}.setSrcValueType("${procparam.getSrcValueType()}");
             procParam${procparam_index?c}.setSrcValue("${procparam.getSrcValue()}");
            this.registerWFDEActionProcessParamModel(procParam${procparam_index?c});
</#list>
</#if>
        }

     }
</#if>
<#if wfprocess.getWFProcessType()=='PARALLEL'>
     /**
      * 并行子流程
      */
     class P${wfprocess_index?c}WFProcessModel extends WFParallelSubWFProcessModelBase{
         public P${wfprocess_index?c}WFProcessModel(){
            super();
            this.setId("${wfprocess.id}");
            this.setName("${wfprocess.name}");
<#if wfprocess.getNamePSLanguageRes()??>
            this.setNameLanResTag("${wfprocess.getNamePSLanguageRes().getLanResTag()}");
</#if>
            this.setLeftPos(${wfprocess.getLeftPos()?c});
            this.setTopPos(${wfprocess.getTopPos()?c});
            this.setThreadSN(${wfprocess.getThreadSN()?c});
			<#if (wfprocess.getThreadShowName()??) && (wfprocess.getThreadShowName()?length gt 0)>
            this.setThreadShowName("${wfprocess.getThreadShowName()}");
</#if>
            this.setWFStepValue("${wfprocess.getWFStepValue()}");
<#if (wfprocess.getUserData()??) && (wfprocess.getUserData()?length gt 0)>
            this.setUserData("${wfprocess.getUserData()}");
</#if>
<#if (wfprocess.getUserData2()??) && (wfprocess.getUserData2()?length gt 0)>
            this.setUserData2("${wfprocess.getUserData2()}");
</#if>
<#if (wfprocess.isAsynchronousProcess())>
			this.setAsynchronousProcess(true);
</#if>
         }
         
         
         @Override
        protected void onInit() throws Exception
        {
            super.onInit();
        	
            //注册子流程
<#if wfprocess.getPSWFProcessSubWFs()??>
<#list wfprocess.getPSWFProcessSubWFs() as procsubwf>
			WFProcSubWFModel procParam${procsubwf_index?c} = new WFProcSubWFModel();
            procParam${procsubwf_index?c}.setId("${procsubwf.getId()}");
            procParam${procsubwf_index?c}.setName("${procsubwf.getName()}");
            procParam${procsubwf_index?c}.setWFId("${procsubwf.getWFId()}");
            procParam${procsubwf_index?c}.setDEName("${procsubwf.getDEName()}");
            procParam${procsubwf_index?c}.setDEDSName("${procsubwf.getDEDSName()}");
             procParam${procsubwf_index?c}.init(this);
            this.registerWFProcSubWFModel(procParam${procsubwf_index?c});
</#list>
</#if>
        }
     }
</#if>
<#if wfprocess.getWFProcessType()=='EMBED'>
     /**
      * 嵌套子流程
      */
     class P${wfprocess_index?c}WFProcessModel extends WFEmbedWFProcessModelBase{
         public P${wfprocess_index?c}WFProcessModel(){
            super();
            this.setId("${wfprocess.id}");
            this.setName("${wfprocess.name}");
<#if wfprocess.getNamePSLanguageRes()??>
            this.setNameLanResTag("${wfprocess.getNamePSLanguageRes().getLanResTag()}");
</#if>
            this.setLeftPos(${wfprocess.getLeftPos()?c});
            this.setTopPos(${wfprocess.getTopPos()?c});
            this.setThreadSN(${wfprocess.getThreadSN()?c});
			<#if (wfprocess.getThreadShowName()??) && (wfprocess.getThreadShowName()?length gt 0)>
            this.setThreadShowName("${wfprocess.getThreadShowName()}");
</#if>
            this.setWFStepValue("${wfprocess.getWFStepValue()}");
<#if (wfprocess.getUserData()??) && (wfprocess.getUserData()?length gt 0)>
            this.setUserData("${wfprocess.getUserData()}");
</#if>
<#if (wfprocess.getUserData2()??) && (wfprocess.getUserData2()?length gt 0)>
            this.setUserData2("${wfprocess.getUserData2()}");
</#if>
<#if (wfprocess.isAsynchronousProcess())>
			this.setAsynchronousProcess(true);
</#if>
         }
         
         @Override
        protected void onInit() throws Exception
        {
            super.onInit();
        	
            //注册子流程
<#if wfprocess.getPSWFProcessSubWFs()??>
<#list wfprocess.getPSWFProcessSubWFs() as procsubwf>
			WFProcSubWFModel procParam${procsubwf_index?c} = new WFProcSubWFModel();
            procParam${procsubwf_index?c}.setId("${procsubwf.getId()}");
            procParam${procsubwf_index?c}.setName("${procsubwf.getName()}");
            procParam${procsubwf_index?c}.setWFId("${procsubwf.getWFId()}");
            procParam${procsubwf_index?c}.setDEName("${procsubwf.getDEName()}");
            procParam${procsubwf_index?c}.setDEDSName("${procsubwf.getDEDSName()}");
             procParam${procsubwf_index?c}.init(this);
            this.registerWFProcSubWFModel(procParam${procsubwf_index?c});
</#list>
</#if>
        }
     }
</#if>
</#list>
</#if>

//定义流程连接
<#if item.getPSWFLinks()??>
<#list item.getPSWFLinks() as wflink>
<#if wflink.getWFLinkType()=='ROUTE'>
   /**
    * 路由处理
    */
   class P${wflink_index?c}WFLinkModel extends WFRouteLinkModelBase{
       public P${wflink_index?c}WFLinkModel(){
          super();
          this.setId("${wflink.id}");
          this.setName("${wflink.name}");
<#if wflink.getLNPSLanguageRes()??>
            this.setLNLanResTag("${wflink.getLNPSLanguageRes()}");
</#if>
          this.setSrcEndPoint("${wflink.getSrcEndPoint()}");
          this.setDstEndPoint("${wflink.getDstEndPoint()}");
          <#if (wflink.getThreadShowName()??) && (wflink.getThreadShowName()?length gt 0)>
            this.setThreadShowName("wflink.getThreadShowName()}");
</#if>
          this.setThreadLinkMode(${wflink.getThreadLinkMode()});
          //${wflink.getToPSWFProcess().name}
          this.setNext("${wflink.getToPSWFProcess().id}");
          //${wflink.getFromPSWFProcess().name}
          this.setFrom("${wflink.getFromPSWFProcess().id}");
          this.setLogicName("${wflink.logicName}");
<#if wflink.isDefault()>
          this.setDefault(true);
</#if>
       }
       
<#if (!wflink.isDefault())>      
       @Override
       protected void onInit() throws Exception
       {
           super.onInit();
		  //注册条件
<#list wflink.getRootWFLinkGroupCondModel().getAllWFLinkCondModels() as linkcond>
           
<#if linkcond.getCondType()=='GROUP'>
			WFLinkGroupCondModel c${linkcond_index?c} = getRootWFLinkGroupCondModel().addGroupCond("${linkcond.getId()}", "${linkcond.getPId()}");
			c${linkcond_index?c}.setGroupOP("${linkcond.getGroupOP()}");
			c${linkcond_index?c}.setNotMode(<#if (linkcond.isNotMode())>true<#else>false</#if>);
</#if>
<#if linkcond.getCondType()=='SINGLE'>
			WFLinkSingleCondModel c${linkcond_index?c} = getRootWFLinkGroupCondModel().addSingleCond("${linkcond.getId()}", "${linkcond.getPId()}");
			c${linkcond_index?c}.setCondOP("${linkcond.getCondOP()}");
			c${linkcond_index?c}.setFieldName("${linkcond.getFieldName()}");
<#if linkcond.getParamType()??>
			c${linkcond_index?c}.setParamType("${linkcond.getParamType()}");
</#if>
<#if linkcond.getParamValue()??>
			c${linkcond_index?c}.setParamValue("${linkcond.getParamValue()}");
</#if>
</#if>
</#list>
       }
</#if>      
   }
</#if>
<#if wflink.getWFLinkType()=='IAACTION'>
	/**
	 * 交互处理
	 */
	class P${wflink_index?c}WFLinkModel extends WFInteractiveLinkModelBase{
		public P${wflink_index?c}WFLinkModel(){
	       super();
	       this.setId("${wflink.id}");
	       this.setName("${wflink.name}");
<#if wflink.getLNPSLanguageRes()??>
           this.setLNLanResTag("${wflink.getLNPSLanguageRes()}");
</#if>
               this.setSrcEndPoint("${wflink.getSrcEndPoint()}");
               this.setDstEndPoint("${wflink.getDstEndPoint()}");
               <#if (wflink.getThreadShowName()??) && (wflink.getThreadShowName()?length gt 0)>
            this.setThreadShowName("wflink.getThreadShowName()}");
</#if>
          this.setThreadLinkMode(${wflink.getThreadLinkMode()});
	       //${wflink.getToPSWFProcess().name}
	       this.setNext("${wflink.getToPSWFProcess().id}");
	       //${wflink.getFromPSWFProcess().name}
	       this.setFrom("${wflink.getFromPSWFProcess().id}");
	       this.setLogicName("${wflink.logicName}");
<#if (wflink.getMemoField()??) && (wflink.getMemoField()?length gt 0)>
               this.setMemoField("${wflink.getMemoField()}");
</#if>
<#if (wflink.getActionField()??) && (wflink.getActionField()?length gt 0)>
               this.setActionField("${wflink.getActionField()}");
</#if>
<#if (wflink.getAddedWFRoleId()??) && (wflink.getAddedWFRoleId()?length gt 0)>
               this.setAddedWFRoleId("${wflink.getAddedWFRoleId()}");
</#if>
<#if (wflink.getUserData()??) && (wflink.getUserData()?length gt 0)>
            this.setUserData("${wflink.getUserData()}");
</#if>
<#if (wflink.getUserData2()??) && (wflink.getUserData2()?length gt 0)>
            this.setUserData2("${wflink.getUserData2()}");
</#if>
<#if ((wflink.getNextCondition()?length) > 0)>
	       this.setNextCondition("${wflink.getNextCondition()}");
</#if>
<#if (wflink.isActorIAActionControl())>
               this.setActorIAActionControl(true);
</#if>
		}
              @Override
              protected void onInit() throws Exception
              {
<#if (wflink.getActionCodeList()??) >
                   this.setActionCodeList(CodeListGlobal.getCodeList("${wflink.getActionCodeList().getClassOrPkgName('CODELIST',pub)}"));
</#if>
                   super.onInit();
              }
	}
</#if>

<#if wflink.getWFLinkType()=='WFRETURN'>
	/**
	 * 嵌入流程返回
	 */
	class P${wflink_index?c}WFLinkModel extends WFEmbedWFReturnModelBase{
		public P${wflink_index?c}WFLinkModel(){
	       super();
	       this.setId("${wflink.id}");
<#if wflink.getLNPSLanguageRes()??>
           this.setLNLanResTag("${wflink.getLNPSLanguageRes()}");
</#if>
	       this.setReturnValue("${wflink.returnValue}");
	       this.setSrcEndPoint("${wflink.getSrcEndPoint()}");
	       this.setDstEndPoint("${wflink.getDstEndPoint()}");
	       <#if (wflink.getThreadShowName()??) && (wflink.getThreadShowName()?length gt 0)>
            this.setThreadShowName("wflink.getThreadShowName()}");
</#if>
          this.setThreadLinkMode(${wflink.getThreadLinkMode()});
	       //${wflink.getToPSWFProcess().name}
	       this.setNext("${wflink.getToPSWFProcess().id}");
	       //${wflink.getFromPSWFProcess().name}
	       this.setFrom("${wflink.getFromPSWFProcess().id}");
	       this.setLogicName("${wflink.logicName}");
	<#if (wflink.getUserData()??) && (wflink.getUserData()?length gt 0)>
	        this.setUserData("${wflink.getUserData()}");
	</#if>
	<#if (wflink.getUserData2()??) && (wflink.getUserData2()?length gt 0)>
	        this.setUserData2("${wflink.getUserData2()}");
	</#if>
	<#if ((wflink.getNextCondition()?length) > 0)>
	       this.setNextCondition("${wflink.getNextCondition()}");
	</#if>
		}
	}
</#if>

<#if wflink.getWFLinkType()=='TIMEOUT'>
	/**
	 * 超时处理
	 */
	class P${wflink_index?c}WFLinkModel extends WFTimeoutLinkModelBase{
		public P${wflink_index?c}WFLinkModel(){
	       super();
	       this.setId("${wflink.id}");
	       this.setName("${wflink.name}");
<#if wflink.getLNPSLanguageRes()??>
           this.setLNLanResTag("${wflink.getLNPSLanguageRes()}");
</#if>
               this.setSrcEndPoint("${wflink.getSrcEndPoint()}");
               this.setDstEndPoint("${wflink.getDstEndPoint()}");
               <#if (wflink.getThreadShowName()??) && (wflink.getThreadShowName()?length gt 0)>
            this.setThreadShowName("wflink.getThreadShowName()}");
</#if>
          this.setThreadLinkMode(${wflink.getThreadLinkMode()});
	       //${wflink.getToPSWFProcess().name}
	       this.setNext("${wflink.getToPSWFProcess().id}");
	       //${wflink.getFromPSWFProcess().name}
	       this.setFrom("${wflink.getFromPSWFProcess().id}");
	       this.setLogicName("${wflink.logicName}");
    	}
	}
</#if>
</#list>
</#if>


    public ${item.codeName}WFVersionModel(){
        super();
        
        this.setId("${item.id}");
        this.setWFVersion(${item.getWFVersion()?c});
<#if item.getWFMode()??>
	//设置流程模式 
	this.setWFMode("${item.getWFMode()}");
</#if>
    }

    @Override
    protected void prepareWFProcessModels() throws Exception
    {
    	<#if item.getPSWFProcesses()??>
<#list item.getPSWFProcesses() as wfprocess>
        //注册处理 ${wfprocess.name}
        P${wfprocess_index?c}WFProcessModel p${wfprocess_index?c} = new P${wfprocess_index?c}WFProcessModel();
        p${wfprocess_index?c}.init(this);
        registerWFProcessModel(p${wfprocess_index?c});

</#list>
</#if>
    	super.prepareWFProcessModels();
    }

    
    @Override
    protected void prepareWFLinkModels() throws Exception
    {
    	<#if item.getPSWFLinks()??>
<#list item.getPSWFLinks() as wflink>
        //注册处理 ${wflink.name}
        P${wflink_index?c}WFLinkModel p${wflink_index?c} = new P${wflink_index?c}WFLinkModel();
        p${wflink_index?c}.init(this);
        registerWFLinkModel(p${wflink_index?c});

</#list>
</#if>
    	super.prepareWFLinkModels();
    }
 
}