package ${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel;


import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.SessionFactory;
import net.ibizsys.paas.core.DEDataSetCond;
import net.ibizsys.paas.logic.ICondition;
import net.ibizsys.paas.core.IDEDataSetCond;
import net.ibizsys.paas.core.ISystem;
import net.ibizsys.paas.demodel.DEModelGlobal;
import net.ibizsys.paas.sysmodel.SysModelGlobal;
import net.ibizsys.paas.service.IService;
import net.ibizsys.paas.service.ServiceGlobal;
import net.ibizsys.paas.view.IView;
import net.ibizsys.paas.core.IDEFSearchMode;
import net.ibizsys.paas.core.IDEField;
import net.ibizsys.paas.demodel.DEFSearchModeModel;
import net.ibizsys.paas.demodel.DEFieldModel;
import net.ibizsys.paas.demodel.DEMainStateModel;
import net.ibizsys.paas.demodel.DEDataSyncModel;
import net.ibizsys.paas.demodel.DEActionWizardGroupModel;
import net.ibizsys.paas.demodel.DEActionWizardModel;
import net.ibizsys.paas.demodel.DEDataSetDEAWModel;
import net.ibizsys.paas.demodel.DEActionWizardItemModel;
import net.ibizsys.paas.demodel.IDEActionWizardModel;
import net.ibizsys.paas.demodel.DEUniStateModel;
import net.ibizsys.paas.core.IDEDataQuery;
import net.ibizsys.paas.core.IDEDataSet;
import net.ibizsys.paas.core.IDEAction;
import net.ibizsys.paas.core.IDEACMode;
import net.ibizsys.paas.core.IDELogic;
import net.ibizsys.paas.core.IDEUIAction;
import net.ibizsys.paas.core.IDEWF;
import net.ibizsys.paas.demodel.DEOPPrivRoleModel;
import net.ibizsys.paas.demodel.DEUniStateModel;
import net.ibizsys.paas.demodel.DEUserRoleModel;
import net.ibizsys.paas.core.IDEDataImport;
import net.ibizsys.paas.report.IPrintService;
import net.ibizsys.paas.report.IReportService;
import net.ibizsys.paas.demodel.IDEDataExportModel;

import net.ibizsys.paas.demodel.DEBATableModel;
import ${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel;

import ${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.entity.${item.codeName};
import ${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.service.${item.codeName}Service;


/**
 * 实体[${item.name}]模型对象
 */
public class ${item.codeName}DEModel extends ${pub.getBaseClassPKGCodeName()}.paas.demodel.DataEntityModelBase<${item.codeName}>  {

   public ${item.codeName}DEModel() throws Exception {
        super();

        this.setId("${item.getId()}");      
        this.setName("${item.getName()}");      
        this.setTableName("${item.tableName}");     
        this.setViewName("${item.getViewName()}");     
<#if !item.isNoViewMode()>
<#if item.isEnableViewLevel(1)>
        this.setView2Name("${item.getView2Name()}");     
</#if>
<#if item.isEnableViewLevel(2)>
        this.setView3Name("${item.getView3Name()}");     
</#if>
<#if item.isEnableViewLevel(3)>
        this.setView4Name("${item.getView4Name()}");     
</#if>
</#if>
        this.setLogicName("${item.logicName}");     
        <#if item.isLogicValid()>
        this.setLogicValid(true);
        this.setValidValue("${item.getLogicValidStringValue(true)}");
        this.setInvalidValue("${item.getLogicValidStringValue(false)}");
        </#if> 
        <#if item.getDSLink()??>
        this.setDSLink("${item.getDSLink()}");
        </#if>
        <#if item.isEnableMultiDS()>
        this.setEnableMultiDS(true);
        </#if>
        <#if item.isEnableMultiForm()>
        this.setEnableMultiForm(true);
        </#if>
        <#if (item.getIndexDEType()??) && (item.getIndexDEType()?length gt 0)>
        this.setIndexDEType("${item.getIndexDEType()}");
        </#if>
        <#if (item.getInheritPSDataEntity()??)>
        //设置继承实体  ${item.getInheritPSDataEntity().name}
        this.setInheritDEId("${item.getInheritPSDataEntity().id}");
        </#if>
        this.setDataAccCtrlMode(${item.getDataAccCtrlMode()?c});
        <#if (item.getDataAccCtrlArch() != 1)>
        this.setDataAccCtrlArch(${item.getDataAccCtrlArch()?c});
        </#if>
        this.setAuditMode(${item.getAuditMode()?c});
        <#if item.getDataChangeLogMode() != 0>
        this.setDataChangeLogMode(${item.getDataChangeLogMode()?c});
        </#if>
        <#if item.isNoViewMode()>
        this.setNoViewMode(true);
        </#if>
        <#if item.getStorageMode()!=1>
        this.setStorageMode(${item.getStorageMode()?c});
        </#if>
        <#if item.isEnableEntityCache()>
        this.setEnableEntityCache(true);
        <#if item.getEntityCacheTimeout() gt 0>
        this.setEntityCacheTimeout(${item.getEntityCacheTimeout()?c});
        </#if>
        <#if item.getMaxEntityCacheCount() gt 0>
        this.setEntityCacheCount(${item.getMaxEntityCacheCount()?c});
        </#if>
        </#if>  
        <#if (item.getServiceAPIClientId()??) && (item.getServiceAPIClientId()?length gt 0)>
        this.setServiceAPIClientId("${item.getServiceAPIClientId()}");
        </#if>
        <#if (item.getDefaultDEDTSQueueId()??) && (item.getDefaultDEDTSQueueId()?length gt 0)>
        this.setDefaultDEDTSQueueId("${item.getDefaultDEDTSQueueId()}");
        </#if>
        <#if item.getPSDEUtil("DATAAUDIT",true)??>
        <#assign deutil=item.getPSDEUtil("DATAAUDIT",false)>
        <#if deutil.getUtilPSDEName()?? && deutil.getUtilPSDEName()?length gt 0>
        this.setAuditDEName("${deutil.getUtilPSDEName()}");
        </#if>
        <#if deutil.getUtilPSDE2Name()?? && deutil.getUtilPSDE2Name()?length gt 0>
        this.setAuditDetailDEName("${deutil.getUtilPSDE2Name()}");
        </#if>
        </#if>
        DEModelGlobal.registerDEModel("${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel.${item.codeName}DEModel",this);
        <#comment>       
        this.initAnnotation(${item.codeName}DEModelBase.class); 
        </#comment>
        this.prepareModels();
 
       //注册到系统中 
        this.get${sys.codeName}SysModel().registerDataEntityModel(this);
   }
   
	/**
     * 获取实体[${item.codeName}]模型对象
     * @return
     * @throws Exception
     */   
    public static ${item.codeName}DEModel getInstance() throws Exception{
    	return (${item.codeName}DEModel)DEModelGlobal.getDEModel(${item.codeName}DEModel.class);
    } 
     
<#comment>
     @Autowired
     private ${sys.codeName}SysModel ${srfparamname('${sys.codeName}')}SysModel;

      public void set${sys.codeName}SysModel(${sys.codeName}SysModel ${srfparamname('${sys.codeName}')}SysModel) {
         this.${srfparamname('${sys.codeName}')}SysModel = ${srfparamname('${sys.codeName}')}SysModel;
      }

      public  ${sys.codeName}SysModel get${sys.codeName}SysModel() {
        return this.${srfparamname('${sys.codeName}')}SysModel;
    }

       @Override
       public  ISystem getSystem() {
        return this.get${sys.codeName}SysModel();
    }
</#comment>
   
      private ${sys.codeName}SysModel ${srfparamname('${sys.codeName}')}SysModel;
      /**
       * 获取当前系统[${sys.codeName}]模型对象
       * @return
	   */
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

      /* (non-Javadoc)
       * @see net.ibizsys.paas.demodel.DataEntityModelBase#getSystem()
       */
       @Override
       public  ISystem getSystem() {
        return this.get${sys.codeName}SysModel();
       }

       private ${item.codeName}Service ${srfparamname('${item.codeName}')}Service;

       /**
        * 获取实际实体服务对象
        * @return
        */
       public  ${item.codeName}Service getRealService() {
         if(this.${srfparamname('${item.codeName}')}Service==null){
                try
                {
                     this.${srfparamname('${item.codeName}')}Service = (${item.codeName}Service)ServiceGlobal.getService(getServiceId());
                }
                catch(Exception ex)
                {
                }
            }
        return this.${srfparamname('${item.codeName}')}Service;
    }
      
 	/* (non-Javadoc)
 	 * @see net.ibizsys.paas.demodel.IDataEntityModel#getService()
 	 */   
  	@Override
  	public IService getService()
  	{
  		return this.getRealService();
  	}
  
  	/* (non-Javadoc)
  	 * @see net.ibizsys.paas.demodel.IDataEntityModel#getServiceId()
  	 */
  	@Override
  	public String getServiceId()
  	{
  		return "${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.service.${item.codeName}Service";
  	}

       
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.IDataEntityModel#createEntity()
	 */
	@Override
	public ${item.codeName} createEntity()
	{
		return new ${item.codeName}();
	}

	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEFields()
	 */
	@Override
	protected void prepareDEFields() throws Exception
	{
		IDEField iDEField = null;
		IDEFSearchMode iDEFSearchMode = null;
		<#list item.getPSDEFields() as defield>
		//注册属性 "${defield.name}"
		iDEField = this.createDEField("${defield.name}");
		if(iDEField==null)
		{
			DEFieldModel deFieldModel = new DEFieldModel();
			deFieldModel.setDataEntity(this);
			deFieldModel.setId("${defield.id}");
			deFieldModel.setName("${defield.name}");
			deFieldModel.setLogicName("${defield.logicName}");
			deFieldModel.setDataType("${defield.dataType}");
			deFieldModel.setStdDataType(${defield.stdDataType?c});
			<#if (defield.isKeyDEField())>
			deFieldModel.setKeyDEField(true);
			</#if>		
			<#if (defield.isUniTagField())>
			deFieldModel.setUniTagField(true);
			</#if>
			<#if (defield.isEnableDBAutoValue())>
			deFieldModel.setEnableDBAutoValue(true);
			</#if>
			<#if (defield.isMajorDEField())>
			deFieldModel.setMajorDEField(true);
			</#if>	
            <#if (defield.getUnionKeyValue()??) && (defield.getUnionKeyValue()?length gt 0)>
            deFieldModel.setUnionKeyValue("${defield.getUnionKeyValue()}");
			</#if>	
			<#if (defield.isLinkDEField())>
			deFieldModel.setLinkDEField(true);
			</#if>	
			<#if (defield.isInheritDEField())>
			deFieldModel.setInheritDEField(true);
			</#if>	
			<#if (defield.isMultiFormDEField())>
			deFieldModel.setMultiFormDEField(true);
			</#if>	
			<#if (defield.isIndexTypeDEField())>
			deFieldModel.setIndexTypeDEField(true);
			</#if>
			deFieldModel.setImportOrder(${defield.getImportOrder()?c});
			deFieldModel.setImportTag("${defield.getImportTag()}");
			<#if defield.getDERName()??>
			deFieldModel.setDERName("${defield.getDERName()}");
			</#if>
			<#if defield.getLinkDEFName()??>
			deFieldModel.setLinkDEFName("${defield.getLinkDEFName()}");
			</#if>
			<#if (!defield.isPhisicalDEField())>
			deFieldModel.setPhisicalDEField(false);
			</#if>	
			<#if (defield.isFormulaDEField())>
			deFieldModel.setFormulaDEField(true);
			</#if>
			<#if (defield.getPreDefinedType()??) && (defield.getPreDefinedType()?length>0)>
			deFieldModel.setPreDefinedType("${defield.getPreDefinedType()}");
			</#if>
			<#if (defield.getDBValueFunc()??) && (defield.getDBValueFunc()?length>0)>
			deFieldModel.setDBValueFunc("${defield.getDBValueFunc()}");
			</#if>
			<#if (defield.getPSCodeList()??)>
			deFieldModel.setCodeListId("${defield.getPSCodeList().getClassOrPkgName('CODELIST',pub)}");
			</#if>
			<#if (defield.getValueFormat()??)>
			deFieldModel.setValueFormat("${defield.getValueFormat()}");
			</#if>
                        <#if (defield.isEnableAudit())>
			deFieldModel.setEnableAudit(true);
                        <#if ((defield.getAuditInfoFormat()??)&&(defield.getAuditInfoFormat()?length gt 0))>
                        deFieldModel.setAuditInfoFormat("${srfjavastring('${defield.getAuditInfoFormat()}')}");
                        </#if>
			</#if>
                        <#if item.isEnableTempData()&&!defield.isEnableTempData()>
                        deFieldModel.setEnableTempData(false);
                        </#if>
			<#list defield.getAllPSDEFSearchModes() as searchmode>
			iDEFSearchMode = this.createDEFSearchMode(deFieldModel,"${srfxmlvalue('${searchmode.name}')}");
			if(iDEFSearchMode==null)
			{
				DEFSearchModeModel defSearchModeModel = new DEFSearchModeModel();
				defSearchModeModel.setDEField(deFieldModel);
				defSearchModeModel.setName("${srfxmlvalue('${searchmode.name}')}");
				<#if (searchmode.getValueFunc()??) && (searchmode.getValueFunc()?length>0)>
				defSearchModeModel.setValueFunc("${srfxmlvalue('${searchmode.valueFunc}')}");
				</#if>
				defSearchModeModel.setValueOp("${srfxmlvalue('${searchmode.valueOp}')}");
				defSearchModeModel.init();
				deFieldModel.registerDEFSearchMode(defSearchModeModel);
			}
			</#list>
			deFieldModel.init();
			iDEField = deFieldModel;
		}
		this.registerDEField(iDEField);
		</#list>
		
		<#if item.getDEMainStateDEFields()??>
		this.setMainStateFields(new String[] {<#list item.getDEMainStateDEFields() as demsfield><#if (demsfield_index gt 0) >,</#if>"${demsfield.name?lower_case}"</#list>});
		</#if>
	}
	        
  	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEACModes()
	 */
	@Override
	protected void prepareDEACModes() throws Exception
	{
<#list item.getAllPSDEACModes() as deacmode>
               //注册  ${deacmode.name}
               IDEACMode ${srfparamname('${deacmode.codeName}')}ACModel = (IDEACMode)this.get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel.${item.codeName}${deacmode.codeName}ACModel");
               ${srfparamname('${deacmode.codeName}')}ACModel.init(this);
               this.registerDEACMode(${srfparamname('${deacmode.codeName}')}ACModel);
</#list>
	}


   	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEDataSets()
	 */
	@Override
	protected void prepareDEDataSets() throws Exception
	{
<#list item.getAllPSDEDataSets() as dedataset>
               //注册  ${dedataset.name}
               IDEDataSet ${srfparamname('${dedataset.codeName}')}DSModel = (IDEDataSet)this.get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel.${item.codeName}${dedataset.codeName}DSModel");
               ${srfparamname('${dedataset.codeName}')}DSModel.init(this);
               this.registerDEDataSet(${srfparamname('${dedataset.codeName}')}DSModel);
</#list>
	}




	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEDataQueries()
	 */
	@Override
	protected void prepareDEDataQueries() throws Exception
	{
<#list item.getAllPSDEDataQueries() as dedataquery>
               //注册  ${dedataquery.name}
              IDEDataQuery ${srfparamname('${dedataquery.codeName}')}DQModel =(IDEDataQuery)this.get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel.${item.codeName}${dedataquery.codeName}DQModel");
               ${srfparamname('${dedataquery.codeName}')}DQModel.init(this);
               this.registerDEDataQuery(${srfparamname('${dedataquery.codeName}')}DQModel);
</#list>
	}

	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEActions()
	 */
	@Override
	protected void prepareDEActions() throws Exception
	{
<#list item.getAllPSDEActions() as deaction>
<#if (deaction.actionType=='SYSDBPROC')>
               //注册  ${deaction.name}
               ${item.codeName}${deaction.codeName}DBProcModel ${srfparamname('${deaction.codeName}')}DBProcModel = new ${item.codeName}${deaction.codeName}DBProcModel();
               ${srfparamname('${deaction.codeName}')}DBProcModel.init(this);
               this.registerDEAction(${srfparamname('${deaction.codeName}')}DBProcModel);
</#if>
</#list>		
		
	}

    /* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDELogics()
	 */
	@Override
	protected void prepareDELogics() throws Exception
	{
<#list item.getAllPSDELogics() as delogic>
               //注册  ${delogic.name}
               IDELogic ${srfparamname('${delogic.codeName}')}LogicModel = (IDELogic)this.get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel.${item.codeName}${delogic.codeName}LogicModel");
               ${srfparamname('${delogic.codeName}')}LogicModel.init(this);
               this.registerDELogic(${srfparamname('${delogic.codeName}')}LogicModel);
</#list>
<#list item.getAllPSDEActions() as deaction>
<#if deaction.getPSDEActionLogics()??>
<#list deaction.getPSDEActionLogics() as actionlogic>
<#if actionlogic.getModelData().GetParamIntValue("VALIDFLAG",1)==1 && actionlogic.isInternalLogic()>
			this.registerDEActionLogic("${deaction.codeName?upper_case}","${actionlogic.getAttachMode()}","${actionlogic.getPSDELogic().codeName}");
</#if>
</#list>
 </#if>
</#list>
	}

	 /* (non-Javadoc)
	  * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEUIActions()
	 */
	@Override
	protected void prepareDEUIActions() throws Exception
	{
<#list item.getAllPSDEUIActions() as deuiaction>
<#if (deuiaction.getUIActionMode()=='BACKEND')>
       //注册  ${deuiaction.name}
       IDEUIAction ${srfparamname('${deuiaction.codeName}')}UIActionModel =(IDEUIAction)this.get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel.${item.codeName}${deuiaction.codeName}UIActionModel");
       ${srfparamname('${deuiaction.codeName}')}UIActionModel.init(this);
       this.registerDEUIAction(${srfparamname('${deuiaction.codeName}')}UIActionModel);
</#if>
</#list>		
	}
	
    /* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEWFs()
	 */
	@Override
	protected void prepareDEWFs() throws Exception
	{
<#list item.getAllPSDEWFs() as dewf>
               //注册  ${dewf.getPSWorkflow().name}
               IDEWF ${srfparamname('${dewf.codeName}')}DEWFModel =(IDEWF)this.get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel.${item.codeName}${dewf.codeName}DEWFModel");
               ${srfparamname('${dewf.codeName}')}DEWFModel.init(this);
               this.registerDEWF(${srfparamname('${dewf.codeName}')}DEWFModel);

</#list>		
	}
	
    /* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEUniStates()
	 */
	@Override
	protected void prepareDEUniStates() throws Exception
	{
<#list item.getAllPSDEUniStates() as deunistate>
               //注册  ${deunistate.name}
               DEUniStateModel deUniState${deunistate_index?c} = new DEUniStateModel();
               deUniState${deunistate_index?c}.setId("${deunistate.id}");
               <#if deunistate.isDefault() >
               deUniState${deunistate_index?c}.setDefault(true);
               </#if>
               deUniState${deunistate_index?c}.init(this);
               this.registerDEUniState(deUniState${deunistate_index?c});

</#list>		
	}
	
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEMainStates()
	 */
	@Override
	protected void prepareDEMainStates() throws Exception
	{
<#list item.getAllPSDEMainStates() as dems>
               //注册  ${dems.name}
			   DEMainStateModel deMSModel${dems_index?c} = new 	DEMainStateModel();
			   deMSModel${dems_index?c}.setId("${dems.id}");
<#if dems.getMSTag()??>
			   deMSModel${dems_index?c}.setMSTag("${dems.getMSTag()}");
</#if>
			   deMSModel${dems_index?c}.setLogicName("${dems.logicName}");
<#if (dems.isAllowMode())>		
			   deMSModel${dems_index?c}.setAllowMode(true);
<#else>
			   deMSModel${dems_index?c}.setAllowMode(false);
</#if>
<#if (dems.isOPPrivAllowMode())>		
				deMSModel${dems_index?c}.setOPPrivAllowMode(true);
<#else>
				deMSModel${dems_index?c}.setOPPrivAllowMode(false);
</#if>
<#if (dems.isDefault())>
			   deMSModel${dems_index?c}.setDefault(true);
<#else>

</#if>
<#if dems.getPSDEMainStateActions()??>
<#list dems.getPSDEMainStateActions() as demsaction>
			   deMSModel${dems_index?c}.registerDEAction("${demsaction.getPSDEAction().codeName?upper_case}");
</#list>
</#if>
<#if dems.getPSDEMainStateOPPrivs()??>
<#list dems.getPSDEMainStateOPPrivs() as demsoppriv>
			   deMSModel${dems_index?c}.registerDEOPPriv("${demsoppriv.getPSDEOPPriv().getName()?upper_case}");
</#list>
</#if>
               deMSModel${dems_index?c}.init(this);
               this.registerDEMainState( deMSModel${dems_index?c});

</#list>		
	}
	
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEDataSyncs()
	 */
	@Override
	protected void prepareDEDataSyncs() throws Exception
	{
<#list item.getAllPSDEDataSyncs() as dedatasync>
<#if dedatasync.isValid()>
               //注册  ${dedatasync.name}
			   DEDataSyncModel deDataSyncModel${dedatasync_index?c} = new DEDataSyncModel();
			   deDataSyncModel${dedatasync_index?c}.setId("${dedatasync.id}");
			   deDataSyncModel${dedatasync_index?c}.setName("${dedatasync.name}");
			   deDataSyncModel${dedatasync_index?c}.setInMode(<#if dedatasync.isInMode()>true<#else>false</#if>);
			   deDataSyncModel${dedatasync_index?c}.setEventType(${dedatasync.getEventType()?c});
<#if dedatasync.getTestDEActionName()??>
				deDataSyncModel${dedatasync_index?c}.setTestDEActionName("${dedatasync.getTestDEActionName()}");
</#if>
<#if dedatasync.isInMode()>			   
<#if dedatasync.getSyncAgent()??>
				//${dedatasync.getInPSSysDataSyncAgent().name}
			   deDataSyncModel${dedatasync_index?c}.setSyncAgent("${dedatasync.getSyncAgent()}");
</#if>
<#if dedatasync.getImportDEActionName()??>
				deDataSyncModel${dedatasync_index?c}.setImportDEActionName("${dedatasync.getImportDEActionName()}");
</#if>
<#if dedatasync.getDENames()??>
<#list dedatasync.getDENames() as dename>
				deDataSyncModel${dedatasync_index?c}.addDEName("${dename}");
</#list>
</#if>
<#else>
<#if dedatasync.getSyncAgent()??>
				//${dedatasync.getOutPSSysDataSyncAgent().name}
				deDataSyncModel${dedatasync_index?c}.setSyncAgent("${dedatasync.getSyncAgent()}");
</#if>
</#if>
               deDataSyncModel${dedatasync_index?c}.init(this);
               this.registerDEDataSync( deDataSyncModel${dedatasync_index?c});
</#if>
</#list>		
	}

	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#preparePDTDEViews()
	 */
	@Override
	protected void preparePDTDEViews() throws Exception
	{
<#if item.getPDTViewNames()??>
<#list item.getPDTViewNames() as pdtname>
         //注册视图 ${item.getPSDEViewDataByPDT('${pdtname}',false).getPSDEVIEWBASENAME()}
         this.registerPDTDEView("${pdtname}","${item.getPSDEViewIdByPDT('${pdtname}')}");
</#list>
</#if>
	}
	
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEOPPrivTagMaps()
	 */
	@Override
	protected void prepareDEOPPrivTagMaps()throws Exception
	{
<#list sys.getAllPSDEOPPrivs() as deoppriv>
<#if deoppriv.getPSDataEntity()??>
<#if (deoppriv.getPSDataEntity().id == item.id) && deoppriv.getPSDERName()??>
		this.registerMapDEOPPrivTag("${deoppriv.name}","${deoppriv.getPSDERName()}","${deoppriv.getMapPSDEOPPrivName()}");
</#if>
</#if>
</#list>
	}
	
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEPrints()
	 */
	@Override
	protected void prepareDEPrints()throws Exception
	{
<#list item.getAllPSDEPrints()as deprint>
		//注册  ${deprint.name}
		IPrintService ${srfparamname('${deprint.codeName}')}PrintService = (IPrintService)this.get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel.${item.codeName}${deprint.codeName}PrintService");
		${srfparamname('${deprint.codeName}')}PrintService.init(this);
</#list>
	}
	
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEReports()
	 */
	@Override
	protected void prepareDEReports()throws Exception
	{
<#list item.getAllPSDEReports()as dereport>
		//注册  ${dereport.name}
		IReportService ${srfparamname('${dereport.codeName}')}ReportService = (IReportService)this.get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel.${item.codeName}${dereport.codeName}ReportService");
		${srfparamname('${dereport.codeName}')}ReportService.init(this);
</#list>
	}
	
	
   	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEDataExports()
	 */
	@Override
	protected void prepareDEDataExports() throws Exception
	{
		
<#list item.getAllPSDEDataExports() as dedataexport>
		 //注册  ${dedataexport.name}
		 IDEDataExportModel ${srfparamname('${dedataexport.codeName}')}DataExportModel = (IDEDataExportModel)this.get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.demodel.${item.codeName}${dedataexport.codeName}DataExportModel");
		 ${srfparamname('${dedataexport.codeName}')}DataExportModel.init(this);
		 this.registerDEDataExport(${srfparamname('${dedataexport.codeName}')}DataExportModel);
</#list>
	}

	
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEActionWizards()
	 */
	@Override
	protected void prepareDEActionWizards() throws Exception {
<#list item.getAllPSDEActionWizards()as deaw>
		//注册  ${deaw.name}
<#if deaw.getDynamicMode()==0 >
		DEActionWizardModel deActionWizardModel${deaw_index?c}  = (DEActionWizardModel)getSystemModel().createDEActionWizardModel(0,<#if deaw.getUserTag()??>"${srfjavastring('${deaw.getUserTag()}')}"<#else>null</#if>);
</#if>
<#if deaw.getDynamicMode()==1 >
		DEDataSetDEAWModel deActionWizardModel${deaw_index?c}  = (DEDataSetDEAWModel)getSystemModel().createDEActionWizardModel(1,<#if deaw.getUserTag()??>"${srfjavastring('${deaw.getUserTag()}')}"<#else>null</#if>);
</#if>
		deActionWizardModel${deaw_index?c}.setId("${deaw.id}");
		deActionWizardModel${deaw_index?c}.setName("${deaw.name}");
<#if deaw.getDynamicMode()==0 >
		<#if deaw.getKeywords()??>
		deActionWizardModel${deaw_index?c}.setKeywords("${srfjavastring('${deaw.getKeywords()}')}");
		</#if>
		if(true){
		<#list deaw.getPSDEActionWizardItems() as item>
			DEActionWizardItemModel item${item_index?c}  = new DEActionWizardItemModel();
			item${item_index?c}.setName("${item.name}");
			item${item_index?c}.setActionValue("${srfjavastring('${item.getActionValue()}')}");
			item${item_index?c}.setContent("${srfjavastring('${item.getContent()}')}");
			item${item_index?c}.init(deActionWizardModel${deaw_index?c});
			deActionWizardModel${deaw_index?c}.registerDEActionWizardItemModel(item${item_index?c});
		</#list>
		}
</#if>
<#if deaw.getDynamicMode()==1 >
<#if deaw.getAWDEName()?? && deaw.getAWDEName()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWDEName("${srfjavastring('${deaw.getAWDEName()}')}");
</#if>
<#if deaw.getAWDEDataSetName()?? && deaw.getAWDEDataSetName()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWDEDataSetName("${srfjavastring('${deaw.getAWDEDataSetName()}')}");
</#if>
<#if deaw.getAWNameField()?? && deaw.getAWNameField()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWNameField("${srfjavastring('${deaw.getAWNameField()}')}");
</#if>
<#if deaw.getAWKeywordField()?? && deaw.getAWKeywordField()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWKeywordField("${srfjavastring('${deaw.getAWKeywordField()}')}");
</#if>
<#if deaw.getAWSortField()?? && deaw.getAWSortField()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWSortField("${srfjavastring('${deaw.getAWSortField()}')}");
</#if>
<#if deaw.getAWIDEName()?? && deaw.getAWIDEName()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWIDEName("${srfjavastring('${deaw.getAWIDEName()}')}");
</#if>
<#if deaw.getAWIDEDataSetName()?? && deaw.getAWIDEDataSetName()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWIDEDataSetName("${srfjavastring('${deaw.getAWIDEDataSetName()}')}");
</#if>
<#if deaw.getAWINameField()?? && deaw.getAWINameField()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWINameField("${srfjavastring('${deaw.getAWINameField()}')}");
</#if>
<#if deaw.getAWIValueField()?? && deaw.getAWIValueField()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWIValueField("${srfjavastring('${deaw.getAWIValueField()}')}");
</#if>
<#if deaw.getAWIFKeyField()?? && deaw.getAWIFKeyField()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWIFKeyField("${srfjavastring('${deaw.getAWIFKeyField()}')}");
</#if>
<#if deaw.getAWIContentField()?? && deaw.getAWIContentField()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWIContentField("${srfjavastring('${deaw.getAWIContentField()}')}");
</#if>
<#if deaw.getAWIUrlField()?? && deaw.getAWIUrlField()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWIUrlField("${srfjavastring('${deaw.getAWIUrlField()}')}");
</#if>
<#if deaw.getAWISortField()?? && deaw.getAWISortField()?length gt 0>
		deActionWizardModel${deaw_index?c}.setAWISortField("${srfjavastring('${deaw.getAWISortField()}')}");
</#if>
</#if>
<#if deaw.getUserTag()?? && deaw.getUserTag()?length gt 0>
		deActionWizardModel${deaw_index?c}.setUserTag("${srfjavastring('${deaw.getUserTag()}')}");
</#if>
<#if deaw.getUserTag2()?? && deaw.getUserTag2()?length gt 0>
		deActionWizardModel${deaw_index?c}.setUserTag2("${srfjavastring('${deaw.getUserTag2()}')}");
</#if>
		deActionWizardModel${deaw_index?c}.init(this);
		this.registerDEActionWizard(deActionWizardModel${deaw_index?c});
</#list>
	}
	
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEActionWizardGroups()
	 */
	@Override
	protected void prepareDEActionWizardGroups() throws Exception {
		<#list item.getAllPSDEActionWizardGroups()as deawg>
		//注册  ${deawg.name}
		DEActionWizardGroupModel deActionWizardGroupModel${deawg_index?c}  = new DEActionWizardGroupModel();
		deActionWizardGroupModel${deawg_index?c}.setId("${deawg.id}");
		deActionWizardGroupModel${deawg_index?c}.setName("${deawg.name}");
		<#list deawg.getPSDEActionWizardGroupDetails() as item>
		deActionWizardGroupModel${deawg_index?c}.registerDEActionWizardModel((IDEActionWizardModel)this.getDEActionWizard("${item.getPSDEActionWizard().id}"));
		</#list>
		deActionWizardGroupModel${deawg_index?c}.init(this);
		this.registerDEActionWizardGroup(deActionWizardGroupModel${deawg_index?c});
		</#list>
	}
	
   	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEBATables()
	 */
	@Override
	protected void prepareDEBATables() throws Exception
	{
<#list item.getAllPSDEBDTables() as debdtable>
          //注册  ${debdtable.name}
          DEBATableModel deBATableModel${debdtable_index?c} = new DEBATableModel();
          deBATableModel${debdtable_index?c}.setId("${debdtable.id}");
          deBATableModel${debdtable_index?c}.setName("${debdtable.name}");
          deBATableModel${debdtable_index?c}.setBATableDEType(${debdtable.getBATableDEType()?c});
          deBATableModel${debdtable_index?c}.setBAThemeId("${debdtable.getBAThemeId()}");
          deBATableModel${debdtable_index?c}.setBATableName("${debdtable.getBATableName()}");
          deBATableModel${debdtable_index?c}.setBAColSetName("${debdtable.getBAColSetName()}");
          deBATableModel${debdtable_index?c}.init(this);
          this.registerDEBATable(deBATableModel${debdtable_index?c});
</#list>
	}
	
    /* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEUserRoles()
	 */
	@Override
	protected void prepareDEUserRoles() throws Exception
	{
<#list item.getAllPSDEUserRoles() as deuserrole>
               //注册  ${deuserrole.name}
               DEUserRoleModel deUserRole${deuserrole_index?c} = new DEUserRoleModel();
               deUserRole${deuserrole_index?c}.setId("${srfjavastring('${deuserrole.id}')}");
               deUserRole${deuserrole_index?c}.setName("${srfjavastring('${deuserrole.getName()}')}");
               <#if deuserrole.getRoleTag()?? && deuserrole.getRoleTag()?length gt 0 >
               deUserRole${deuserrole_index?c}.setRoleTag("${srfjavastring('${deuserrole.getRoleTag()}')}");
               </#if>
               deUserRole${deuserrole_index?c}.init(this);
               this.registerDEUserRole(deUserRole${deuserrole_index?c});

</#list>		
	}
	
	
    /* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEOPPrivRoles()
	 */
	@Override
	protected void prepareDEOPPrivRoles() throws Exception
	{
<#list item.getAllPSDEOPPrivRoles() as deopprivrole>
               //注册  ${deopprivrole.name}
               DEOPPrivRoleModel deOPPrivRole${deopprivrole_index?c} = new DEOPPrivRoleModel();
               deOPPrivRole${deopprivrole_index?c}.setId("${srfjavastring('${deopprivrole.id}')}");
               deOPPrivRole${deopprivrole_index?c}.setName("${srfjavastring('${deopprivrole.getName()}')}");
               <#if deopprivrole.getDEOPPrivTag()?? && deopprivrole.getDEOPPrivTag()?length gt 0 >
               deOPPrivRole${deopprivrole_index?c}.setDEOPPrivTag("${srfjavastring('${deopprivrole.getDEOPPrivTag()}')}");
               </#if>
               <#if deopprivrole.getRoleType()?? && deopprivrole.getRoleType()?length gt 0 >
               deOPPrivRole${deopprivrole_index?c}.setRoleType("${srfjavastring('${deopprivrole.getRoleType()}')}");
               </#if>
               <#if deopprivrole.getDEUserRoleId()?? && deopprivrole.getDEUserRoleId()?length gt 0 >
               deOPPrivRole${deopprivrole_index?c}.setDEUserRoleId("${srfjavastring('${deopprivrole.getDEUserRoleId()}')}");
               </#if>
               <#if deopprivrole.getDEDataQueryId()?? && deopprivrole.getDEDataQueryId()?length gt 0 >
               deOPPrivRole${deopprivrole_index?c}.setDEDataQueryId("${srfjavastring('${deopprivrole.getDEDataQueryId()}')}");
               </#if>
               <#if deopprivrole.getSysUserRoleId()?? && deopprivrole.getSysUserRoleId()?length gt 0 >
               deOPPrivRole${deopprivrole_index?c}.setSysUserRoleId("${srfjavastring('${deopprivrole.getSysUserRoleId()}')}");
               </#if>
               deOPPrivRole${deopprivrole_index?c}.init(this);
               this.registerDEOPPrivRole(deOPPrivRole${deopprivrole_index?c});

</#list>		
	}	
 
	/* (non-Javadoc)
     * @see net.ibizsys.paas.demodel.DataEntityModelBase#onFillFetchQuickSearchConditions(net.ibizsys.paas.core.DEDataSetCond, java.lang.String)
     */
    @Override
	protected void onFillFetchQuickSearchConditions(DEDataSetCond groupCondImpl,String strQuickSearch)  throws Exception
	{
	    super.onFillFetchQuickSearchConditions(groupCondImpl,strQuickSearch);	

            <#list item.getPSDEFields() as defield>
<#if defield.isEnableQuickSearch()>
            //放入属性 ${defield.name} - ${defield.logicName}
	    if(true)
	    {
		DEDataSetCond deDataSetCondImpl = new DEDataSetCond();
		deDataSetCondImpl.setCondType(IDEDataSetCond.CONDTYPE_DEFIELD);
		deDataSetCondImpl.setCondOp(ICondition.CONDOP_LIKE);
		deDataSetCondImpl.setDEFName(${item.codeName}.FIELD_${defield.codeName?upper_case});
		deDataSetCondImpl.setCondValue(strQuickSearch);
		groupCondImpl.addChildDEDataQueryCond(deDataSetCondImpl);
	   }  
</#if>
            </#list>
	} 
}