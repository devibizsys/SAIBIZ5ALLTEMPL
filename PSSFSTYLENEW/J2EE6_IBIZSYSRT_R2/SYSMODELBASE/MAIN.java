package ${pub.getPKGCodeName()}.srv;

import javax.annotation.PostConstruct;

import net.ibizsys.paas.core.DER;
import net.ibizsys.paas.core.DERs;
import net.ibizsys.paas.ctrlhandler.ICounterHandler;
import net.ibizsys.paas.db.IDBDialect;
import net.ibizsys.paas.demodel.DEModelGlobal;
import net.ibizsys.paas.demodel.IDataEntityModel;
import net.ibizsys.paas.service.ActionSessionManager;
import net.ibizsys.paas.service.IService;
import net.ibizsys.paas.service.ServiceGlobal;
import net.ibizsys.paas.sysmodel.SysModelGlobal;
import net.ibizsys.paas.sysmodel.SystemUserRoleModelBase;
import net.ibizsys.paas.sysmodel.DEDataSetSystemUserRoleModel;
import net.ibizsys.paas.util.StringHelper;
import net.ibizsys.paas.view.ViewMsgGroupModel;
import net.ibizsys.paas.view.StaticViewMsgModel;
import net.ibizsys.paas.view.DEDataSetViewMsgModel;
import net.ibizsys.paas.demodel.DEFInputTipSetModel;
import net.ibizsys.paas.cache.DEUniStateModel;
import net.ibizsys.paas.dts.DTSQueueModel;
import net.ibizsys.paas.dts.DTSQueueModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@DERs({
<#list item.getAllPSDERs() as der>
<#if (der_index>0)>,</#if>@DER(id="${der.id}",name="${der.name}",type="${der.getDERType()}",majordeid="${srfxmlvalue('${der.getMajorDEId()}')}",minordeid="${srfxmlvalue('${der.getMinorDEId()}')}" ,majordename="${srfxmlvalue('${der.getMajorDEName()}')}",minordename="${srfxmlvalue('${der.getMinorDEName()}')}"   <#if (der.getDERType()=='DER1N')>,masterrs=${der.getMasterRS()?c},pickupdefname="${srfxmlvalue('${der.getPickupDEFName()}')}"</#if><#if (der.getDERType()=='DERINDEX') || (der.getDERType()=='DERINHERIT')>,indexvalue="${srfxmlvalue('${der.getTypeValue()}')}"</#if>)
</#list>
})
/**
 * 系统[${item.name}]模型对象
 */
@Component
public class ${item.codeName}SysModel extends ${pub.getBaseClassPKGCodeName()}.paas.sysmodel.SystemModelBase  {
	
	 private static final Log log = LogFactory.getLog(${item.codeName}SysModel.class);
<#list item.getAllPSDERs() as der>
   	/**
     * 实体关系[${der.name}]
     */
   public static final String ${der.name} = "${der.name}";
</#list>

<#list item.getAllPSDataEntities() as de>
   	/**
     * 实体名称[${de.name}] -- ${de.logicName}
     */
   public static final String ${de.name?upper_case} = "${de.name}";
</#list>

   public ${item.codeName}SysModel() throws Exception{
        super();
        this.setId("${item.id}");
        this.setName("${item.name}");
        SysModelGlobal.registerSystem("${pub.getPKGCodeName()}.srv.${item.codeName}SysModel",this);
       this.initAnnotation(${item.codeName}SysModel .class); 
    }

    /**
     * 获取系统[${item.codeName}]模型对象
     * @return
     * @throws Exception
     */    
    public static ${item.codeName}SysModel getInstance() throws Exception{
    	return (${item.codeName}SysModel)SysModelGlobal.getSystem(${item.codeName}SysModel.class);
    }    

   @PostConstruct  
   public void postConstruct() throws Exception{    
      super.postConstruct();    
      this.prepareCodeLists();
      this.prepareSysCounters();
      this.prepareSysValueRules();
      this.prepareDataEntities();
      this.prepareSystemUserRoles();
      this.prepareDTSQueues();
      this.prepareUniStates();
      this.prepareServiceAPIClients();
      this.prepareDEFInputTipSets();
      this.prepareViewMsgs();
      this.prepareViewMsgGroups();
      this.prepareWXAccounts();
      this.prepareWorkflows();
      this.prepareBASchemes();
      this.installPlugins(this.get${item.codeName}Plugins());
   } 
 
   	/**
  	 * 手动安装系统
	 * @throws Exception
	 */
   public void install() throws Exception{    
      super.postConstruct();   
      this.prepareCodeLists();
      this.prepareSysCounters();
      this.prepareSysValueRules();
      this.prepareDataEntities();
      this.prepareDAOs(); 
      this.prepareServices();
      this.prepareSystemUserRoles();
      this.prepareDTSQueues();
      this.prepareUniStates();
      this.prepareServiceAPIClients();
      this.prepareDEFInputTipSets();
      this.prepareViewMsgs();
      this.prepareViewMsgGroups();
      this.prepareWXAccounts();
      this.prepareWorkflows();
      this.prepareBASchemes();
      this.installPlugins(this.get${item.codeName}Plugins());
   } 


   @Autowired(required=false)
   @Qualifier("dbDialect${item.codeName}")
    private IDBDialect dbDialect${item.codeName};

    /**
     * 设置当前系统数据设配器
     * @param dbDialect${item.codeName}
     */
    public void setDBDialect${item.codeName}(IDBDialect dbDialect${item.codeName}) {
	       this.dbDialect${item.codeName} = dbDialect${item.codeName};
    }

    /**
     * 获取当前系统数据设配器
     * @return
     */
    public IDBDialect getDBDialect${item.codeName}() {
	return dbDialect${item.codeName};
    }


    @Autowired(required=false)
    @Qualifier("sessionFactory${item.codeName}")
    private SessionFactory sessionFactory${item.codeName};

    /**
     * 设置当前系统会话工厂
     * @param sessionFactory${item.codeName}
     */
    public void setSessionFactory${item.codeName}(SessionFactory sessionFactory${item.codeName}) {
	this.sessionFactory${item.codeName} = sessionFactory${item.codeName};
    }

    /**
     * 获取当前系统会话工厂
     * @return
     */
    public SessionFactory getSessionFactory${item.codeName}() {
    	return sessionFactory${item.codeName};
    }
    
    /* (non-Javadoc)
     * @see net.ibizsys.paas.sysmodel.SystemModelBase#getSessionFactory()
     */
    @Override
    public SessionFactory getSessionFactory() {
    	if(getSessionFactory${item.codeName}()==null)
    		return super.getSessionFactory();
    	return getSessionFactory${item.codeName}();
    }
 
    /* (non-Javadoc)
     * @see net.ibizsys.paas.sysmodel.SystemModelBase#getDBDialect()
     */
    @Override
    public IDBDialect getDBDialect() {
    	if(getDBDialect${item.codeName}()==null)
    		return super.getDBDialect();
    	return getDBDialect${item.codeName}();
    }
    
    /**
     * 准备系统代码表
     */
    protected void prepareCodeLists() throws Exception{
<#list item.getAllPSCodeLists() as codelist>
<#if codelist.isSubSysCodeList() && codelist.getRefFlag() && (codelist.getExtendMode()==2)>
    	 createObject("${pub.getPKGCodeName()}.srv.subsys.codelist.${codelist.codeName}CodeListModel");
</#if>
</#list> 

<#list item.getAllPSCodeLists() as codelist>
<#if !codelist.isSubSysCodeList() && codelist.getRefFlag()>
    	 createObject("${pub.getPKGCodeName()}.srv.codelist.${codelist.codeName}CodeListModel");
</#if>
</#list> 

		//初始化用户词条类别代码表
<#list item.getAllPSSysDictCats() as sysdictcat>
		//类别 ${sysdictcat.name}
		if(true){
			net.ibizsys.paas.sysmodel.GlobalScopeDictCatCodeListModel globalScopeCodeList = new net.ibizsys.paas.sysmodel.GlobalScopeDictCatCodeListModel();
			globalScopeCodeList.setOwnerType("GLOBAL");
			globalScopeCodeList.setCat("${sysdictcat.id}");
			String strId = net.ibizsys.paas.util.KeyValueHelper.genUniqueId(USERDICTCAT,USERDICTCAT_GLOBAL,"${sysdictcat.id}");
			globalScopeCodeList.setId(strId);
			globalScopeCodeList.setName("全局词典分类[${sysdictcat.name}]");
			net.ibizsys.paas.sysmodel.CodeListGlobal.registerCodeList(strId,globalScopeCodeList);
		}
		if(true){
			net.ibizsys.paas.sysmodel.UserScopeDictCatCodeListModel  userScopeCodeList = new net.ibizsys.paas.sysmodel.UserScopeDictCatCodeListModel();
			userScopeCodeList.setCat("${sysdictcat.id}");
			String strId = net.ibizsys.paas.util.KeyValueHelper.genUniqueId(USERDICTCAT,USERDICTCAT_USER,"${sysdictcat.id}");
			userScopeCodeList.setId(strId);
			userScopeCodeList.setName("用户词典分类[${sysdictcat.name}]");
			net.ibizsys.paas.sysmodel.CodeListGlobal.registerCodeList(strId,userScopeCodeList);
		}
</#list>
    }
    
    /**
     * 准备系统属性输入提示集合
     */
    protected void prepareDEFInputTipSets(){
    	try{
<#list item.getAllPSDEFInputTipSets() as definputtipset>
		//注册[${definputtipset.getName()}]
		DEFInputTipSetModel g${definputtipset_index?c} = (DEFInputTipSetModel)createDEFInputTipSetModel(<#if definputtipset.getUserTag()??>"${srfjavastring('${definputtipset.getUserTag()}')}"<#else>null</#if>);
		g${definputtipset_index?c}.setId("${definputtipset.id}");
		g${definputtipset_index?c}.setName("${srfjavastring('${definputtipset.getName()}')}");
<#if definputtipset.getDEName()?? && definputtipset.getDEName()?length gt 0>
		g${definputtipset_index?c}.setDEName("${srfjavastring('${definputtipset.getDEName()}')}");
</#if>
<#if definputtipset.getDEDataSetName()?? && definputtipset.getDEDataSetName()?length gt 0>
		g${definputtipset_index?c}.setDEDataSetName("${srfjavastring('${definputtipset.getDEDataSetName()}')}");
</#if>
<#if definputtipset.getEnableCloseField()?? && definputtipset.getEnableCloseField()?length gt 0>
		g${definputtipset_index?c}.setEnableCloseField("${srfjavastring('${definputtipset.getEnableCloseField()}')}");
</#if>
<#if definputtipset.getContentField()?? && definputtipset.getContentField()?length gt 0>
		g${definputtipset_index?c}.setContentField("${srfjavastring('${definputtipset.getContentField()}')}");
</#if>
<#if definputtipset.getUniqueTagField()?? && definputtipset.getUniqueTagField()?length gt 0>
		g${definputtipset_index?c}.setUniqueTagField("${srfjavastring('${definputtipset.getUniqueTagField()}')}");
</#if>
<#if definputtipset.getLinkField()?? && definputtipset.getLinkField()?length gt 0>
		g${definputtipset_index?c}.setLinkField("${srfjavastring('${definputtipset.getLinkField()}')}");
</#if>
<#if definputtipset.getUserTag()?? && definputtipset.getUserTag()?length gt 0>
		g${definputtipset_index?c}.setUserTag("${srfjavastring('${definputtipset.getUserTag()}')}");
</#if>
<#if definputtipset.getUserTag2()?? && definputtipset.getUserTag2()?length gt 0>
		g${definputtipset_index?c}.setUserTag2("${srfjavastring('${definputtipset.getUserTag2()}')}");
</#if>
		g${definputtipset_index?c}.init(this);
		this.registerDEFInputTipSetModel(g${definputtipset_index?c});
</#list>    	
    	}
    	catch(Exception ex)
    	{
    		log.error(StringHelper.format("初始化属性输入提示集合发生异常，%1$s",ex.getMessage()),ex);
    	}
    }

    /**
     * 准备系统视图消息
     */
    protected void prepareViewMsgs(){
    	try{
<#list item.getAllPSViewMsgs() as viewmsg>
		//注册[${viewmsg.getName()}]
<#if viewmsg.getDynamicMode()==0>
		StaticViewMsgModel g${viewmsg_index?c} = (StaticViewMsgModel)createViewMsgModel(0,<#if viewmsg.getUserTag()??>"${srfjavastring('${viewmsg.getUserTag()}')}"<#else>null</#if>);
</#if>
<#if viewmsg.getDynamicMode()==1>
		DEDataSetViewMsgModel g${viewmsg_index?c} = (DEDataSetViewMsgModel)createViewMsgModel(1,<#if viewmsg.getUserTag()??>"${srfjavastring('${viewmsg.getUserTag()}')}"<#else>null</#if>);
</#if>		
		g${viewmsg_index?c}.setId("${viewmsg.id}");
		g${viewmsg_index?c}.setName("${srfjavastring('${viewmsg.getName()}')}");
<#if viewmsg.getTitle()?? && viewmsg.getTitle()?length gt 0>
		g${viewmsg_index?c}.setTitle("${srfjavastring('${viewmsg.getTitle()}')}");
</#if>	
<#if viewmsg.getPosition()?? && viewmsg.getPosition()?length gt 0>
		g${viewmsg_index?c}.setPosition("${srfjavastring('${viewmsg.getPosition()}')}");
</#if>
<#if viewmsg.getMessage()?? && viewmsg.getMessage()?length gt 0>
		g${viewmsg_index?c}.setMessage("${srfjavastring('${viewmsg.getMessage()}')}");
</#if>
<#if viewmsg.getMessageType()?? && viewmsg.getMessageType()?length gt 0>
		g${viewmsg_index?c}.setMessageType("${srfjavastring('${viewmsg.getMessageType()}')}");
</#if>		
<#if viewmsg.getMessageType()?? && viewmsg.getMessageType()?length gt 0>
		g${viewmsg_index?c}.setMessageType("${srfjavastring('${viewmsg.getMessageType()}')}");
</#if>	
		g${viewmsg_index?c}.setEnableRemove(<#if viewmsg.isEnableRemove()>true<#else>false</#if>);
<#if viewmsg.getMsgTemplateId()?? && viewmsg.getMsgTemplateId()?length gt 0>
		g${viewmsg_index?c}.setMsgTemplateId("${srfjavastring('${viewmsg.getMsgTemplateId()}')}");
</#if>	
<#if viewmsg.getTitleLanResTag()?? && viewmsg.getTitleLanResTag()?length gt 0>
		g${viewmsg_index?c}.setTitleLanResTag("${srfjavastring('${viewmsg.getTitleLanResTag()}')}");
</#if>	
<#if viewmsg.getDynamicMode()==1>
<#if viewmsg.getDEName()?? && viewmsg.getDEName()?length gt 0>
		g${viewmsg_index?c}.setDEName("${srfjavastring('${viewmsg.getDEName()}')}");
</#if>
<#if viewmsg.getDEDataSetName()?? && viewmsg.getDEDataSetName()?length gt 0>
		g${viewmsg_index?c}.setDEDataSetName("${srfjavastring('${viewmsg.getDEDataSetName()}')}");
</#if>
<#if viewmsg.getTitleField()?? && viewmsg.getTitleField()?length gt 0>
		g${viewmsg_index?c}.setTitleField("${srfjavastring('${viewmsg.getTitleField()}')}");
</#if>
<#if viewmsg.getTitleLanResTagField()?? && viewmsg.getTitleLanResTagField()?length gt 0>
		g${viewmsg_index?c}.setTitleLanResTagField("${srfjavastring('${viewmsg.getTitleLanResTagField()}')}");
</#if>
<#if viewmsg.getMsgTypeField()?? && viewmsg.getMsgTypeField()?length gt 0>
		g${viewmsg_index?c}.setMsgTypeField("${srfjavastring('${viewmsg.getMsgTypeField()}')}");
</#if>
<#if viewmsg.getMsgPosField()?? && viewmsg.getMsgPosField()?length gt 0>
		g${viewmsg_index?c}.setMsgPosField("${srfjavastring('${viewmsg.getMsgPosField()}')}");
</#if>
<#if viewmsg.getRemoveFlagField()?? && viewmsg.getRemoveFlagField()?length gt 0>
		g${viewmsg_index?c}.setRemoveFlagField("${srfjavastring('${viewmsg.getRemoveFlagField()}')}");
</#if>
<#if viewmsg.getContentField()?? && viewmsg.getContentField()?length gt 0>
		g${viewmsg_index?c}.setContentField("${srfjavastring('${viewmsg.getContentField()}')}");
</#if>
<#if viewmsg.getDSLink()?? && viewmsg.getDSLink()?length gt 0>
		g${viewmsg_index?c}.setDSLink("${srfjavastring('${viewmsg.getDSLink()}')}");
</#if>
<#if viewmsg.getOrderValueField()?? && viewmsg.getOrderValueField()?length gt 0>
		g${viewmsg_index?c}.setOrderValueField("${srfjavastring('${viewmsg.getOrderValueField()}')}");
</#if>
<#if viewmsg.getActiveDataDELogicId()?? && viewmsg.getActiveDataDELogicId()?length gt 0>
		g${viewmsg_index?c}.setActiveDataDELogicId("${srfjavastring('${viewmsg.getActiveDataDELogicId()}')}");
</#if>
<#if viewmsg.isEnableCache()>
		g${viewmsg_index?c}.setEnableCache(true);
<#if viewmsg.getCacheScope()?? && viewmsg.getCacheScope()?length gt 0>
		g${viewmsg_index?c}.setCacheScope("${srfjavastring('${viewmsg.getCacheScope()}')}");
</#if>
<#if viewmsg.getCacheTimeout() gt 0>
		g${viewmsg_index?c}.setCacheTimeout(${viewmsg.getCacheTimeout()?c});
</#if>
<#if viewmsg.getCacheTagField()?? && viewmsg.getCacheTagField()?length gt 0>
		g${viewmsg_index?c}.setCacheTagField("${srfjavastring('${viewmsg.getCacheTagField()}')}");
</#if>
<#if viewmsg.getCacheTag2Field()?? && viewmsg.getCacheTag2Field()?length gt 0>
		g${viewmsg_index?c}.setCacheTag2Field("${srfjavastring('${viewmsg.getCacheTag2Field()}')}");
</#if>
</#if>
</#if>		
<#if viewmsg.getUserTag()?? && viewmsg.getUserTag()?length gt 0>
		g${viewmsg_index?c}.setUserTag("${srfjavastring('${viewmsg.getUserTag()}')}");
</#if>
<#if viewmsg.getUserTag2()?? && viewmsg.getUserTag2()?length gt 0>
		g${viewmsg_index?c}.setUserTag2("${srfjavastring('${viewmsg.getUserTag2()}')}");
</#if>
		g${viewmsg_index?c}.init(this);
		this.registerViewMsgModel(g${viewmsg_index?c});
</#list>    	
    	}
    	catch(Exception ex)
    	{
    		log.error(StringHelper.format("初始化视图消息发生异常，%1$s",ex.getMessage()),ex);
    	}
    }
    
    /**
     * 准备系统视图消息组
     */
    protected void prepareViewMsgGroups(){
    	try{
<#list item.getAllPSViewMsgGroups() as viewmsggroup>
		//注册[${viewmsggroup.getName()}]
		ViewMsgGroupModel g${viewmsggroup_index?c} = new ViewMsgGroupModel();
		g${viewmsggroup_index?c}.setId("${viewmsggroup.id}");
		g${viewmsggroup_index?c}.setName("${srfjavastring('${viewmsggroup.getName()}')}");
<#if viewmsggroup.getUserTag()?? && viewmsggroup.getUserTag()?length gt 0>
		g${viewmsggroup_index?c}.setUserTag("${srfjavastring('${viewmsggroup.getUserTag()}')}");
</#if>
<#if viewmsggroup.getUserTag2()?? && viewmsggroup.getUserTag2()?length gt 0>
		g${viewmsggroup_index?c}.setUserTag2("${srfjavastring('${viewmsggroup.getUserTag2()}')}");
</#if>
<#if viewmsggroup.getPSViewMsgGroupDetails()??>
<#list viewmsggroup.getPSViewMsgGroupDetails() as detail>
<#if detail.getPSViewMsg()??>
		//注册视图消息[ ${detail.getPSViewMsg().getName()}]
		g${viewmsggroup_index?c}.registerViewMsgModel(this.getViewMsgModel("${srfjavastring('${detail.getPSViewMsg().getId()}')}"));
</#if>
</#list>
</#if>
		g${viewmsggroup_index?c}.init(this);
		this.registerViewMsgGroupModel(g${viewmsggroup_index?c});
</#list>    	
    	}
    	catch(Exception ex)
    	{
    		log.error(StringHelper.format("初始化视图消息组发生异常，%1$s",ex.getMessage()),ex);
    	}
    }

    /**
     * 准备系统统一状态对象
     */
    protected void prepareUniStates(){
    	try{
<#list item.getAllPSSysUniStates() as unistate>
		//注册[${unistate.getName()}]
		<#if unistate.getUniStateType() == 'DE'>
		DEUniStateModel deUniStateModel${unistate_index?c} = (DEUniStateModel)this.createUniStateModel("DE","${srfjavastring('${unistate.getUniqueTag()}')}");
		deUniStateModel${unistate_index?c}.setId("${unistate.id}");
		deUniStateModel${unistate_index?c}.setName("${unistate.name}");
		deUniStateModel${unistate_index?c}.setUniqueTag("${srfjavastring('${unistate.getUniqueTag()}')}");
		<#if unistate.getDEName()??>
		deUniStateModel${unistate_index?c}.setDEName("${srfjavastring('${unistate.getDEName()}')}");
		</#if>
		<#if unistate.getFolderField()??>
		deUniStateModel${unistate_index?c}.setFolderField("${srfjavastring('${unistate.getFolderField()}')}");
		</#if>
		<#if unistate.getFolder2Field()??>
		deUniStateModel${unistate_index?c}.setFolder2Field("${srfjavastring('${unistate.getFolder2Field()}')}");
		</#if>
		<#if unistate.getFolder3Field()??>
		deUniStateModel${unistate_index?c}.setFolder3Field("${srfjavastring('${unistate.getFolder3Field()}')}");
		</#if>
		<#if unistate.getKeyField()??>
		deUniStateModel${unistate_index?c}.setKeyField("${srfjavastring('${unistate.getKeyField()}')}");
		</#if>
		<#if unistate.getStateField()??>
		deUniStateModel${unistate_index?c}.setStateField("${srfjavastring('${unistate.getStateField()}')}");
		</#if>
		<#if unistate.getState2Field()??>
		deUniStateModel${unistate_index?c}.setState2Field("${srfjavastring('${unistate.getState2Field()}')}");
		</#if>
		<#if unistate.getState3Field()??>
		deUniStateModel${unistate_index?c}.setState3Field("${srfjavastring('${unistate.getState3Field()}')}");
		</#if>
		<#if unistate.getState4Field()??>
		deUniStateModel${unistate_index?c}.setState4Field("${srfjavastring('${unistate.getState4Field()}')}");
		</#if>
		<#if unistate.getState5Field()??>
		deUniStateModel${unistate_index?c}.setState5Field("${srfjavastring('${unistate.getState5Field()}')}");
		</#if>
		<#if unistate.getState6Field()??>
		deUniStateModel${unistate_index?c}.setState6Field("${srfjavastring('${unistate.getState6Field()}')}");
		</#if>
		<#if unistate.getState7Field()??>
		deUniStateModel${unistate_index?c}.setState7Field("${srfjavastring('${unistate.getState7Field()}')}");
		</#if>
		<#if unistate.getState8Field()??>
		deUniStateModel${unistate_index?c}.setState8Field("${srfjavastring('${unistate.getState8Field()}')}");
		</#if>
		deUniStateModel${unistate_index?c}.init(this);
		this.registerUniStateModel(deUniStateModel${unistate_index?c});
		</#if>
</#list>    	
    	}
    	catch(Exception ex)
    	{
    		log.error(StringHelper.format("初始化统一状态对象发生异常，%1$s",ex.getMessage()),ex);
    	}
    }

    /**
     * 准备系统用户角色对象
     */
    protected void prepareSystemUserRoles(){
    	try{
<#list item.getAllPSSysUserRoles() as userrole>
		//注册[${userrole.getName()}]
		SystemUserRoleModelBase systemUserRoleModel${userrole_index?c} = (SystemUserRoleModelBase)this.createSystemUserRoleModel("${userrole.getRoleType()}","${srfjavastring('${userrole.getRoleTag()}')}");
		systemUserRoleModel${userrole_index?c}.setId("${userrole.id}");
		systemUserRoleModel${userrole_index?c}.setName("${userrole.name}");
		systemUserRoleModel${userrole_index?c}.setRoleTag("${srfjavastring('${userrole.getRoleTag()}')}");
		<#if userrole.getRoleType() == 'DEDATASET'>
		DEDataSetSystemUserRoleModel deDataSetSystemUserRoleModel${userrole_index?c} = (DEDataSetSystemUserRoleModel)systemUserRoleModel${userrole_index?c};
		<#if userrole.getDEName()??>
		deDataSetSystemUserRoleModel${userrole_index?c}.setDEName("${srfjavastring('${userrole.getDEName()}')}");
		</#if>
		<#if userrole.getDEDataSetName()??>
		deDataSetSystemUserRoleModel${userrole_index?c}.setDEDataSetName("${srfjavastring('${userrole.getDEDataSetName()}')}");
		</#if>
		</#if>
		<#if userrole.getUniResTags()??>
		<#list userrole.getUniResTags() as unirestag>
		systemUserRoleModel${userrole_index?c}.registerUniResTag("${srfjavastring('${unirestag}')}");
		</#list>
		</#if>
		systemUserRoleModel${userrole_index?c}.init(this);
		this.registerSystemUserRoleModel(systemUserRoleModel${userrole_index?c});
</#list>    	
    	}
    	catch(Exception ex)
    	{
    		log.error(StringHelper.format("初始化用户角色对象发生异常，%1$s",ex.getMessage()),ex);
    	}
    }
  
    /**
     * 准备系统分布事务处理队列
     */
    protected void prepareDTSQueues(){
    	try{
<#list item.getAllPSSysDTSQueues() as dtsqueue>
		//注册[${dtsqueue.getName()}]
		DTSQueueModel dtsQueueModel${dtsqueue_index?c} = (DTSQueueModel)this.createDTSQueueModel("","${srfjavastring('${dtsqueue.getUserTag()}')}");
		dtsQueueModel${dtsqueue_index?c}.setId("${dtsqueue.id}");
		dtsQueueModel${dtsqueue_index?c}.setName("${dtsqueue.name}");
		<#if dtsqueue.getDEName()??>
		dtsQueueModel${dtsqueue_index?c}.setDEName("${srfjavastring('${dtsqueue.getDEName()}')}");
		</#if>
		<#if dtsqueue.getHistoryDEName()??>
		dtsQueueModel${dtsqueue_index?c}.setHistoryDEName("${srfjavastring('${dtsqueue.getHistoryDEName()}')}");
		</#if>
		<#if dtsqueue.getCancelTimeout() gt 0>
		dtsQueueModel${dtsqueue_index?c}.setCancelTimeout(${dtsqueue.getCancelTimeout()?c});
		</#if>
		<#if dtsqueue.getRefreshTimer() gt 0>
		dtsQueueModel${dtsqueue_index?c}.setRefreshTimer(${dtsqueue.getRefreshTimer()?c});
		</#if>
		<#if dtsqueue.getErrorField()??>
		dtsQueueModel${dtsqueue_index?c}.setErrorField("${srfjavastring('${dtsqueue.getErrorField()}')}");
		</#if>
		<#if dtsqueue.getStateField()??>
		dtsQueueModel${dtsqueue_index?c}.setStateField("${srfjavastring('${dtsqueue.getStateField()}')}");
		</#if>
		<#if dtsqueue.getTimeField()??>
		dtsQueueModel${dtsqueue_index?c}.setTimeField("${srfjavastring('${dtsqueue.getTimeField()}')}");
		</#if>
		<#if dtsqueue.getPushDEActionName()??>
		dtsQueueModel${dtsqueue_index?c}.setPushDEActionName("${srfjavastring('${dtsqueue.getPushDEActionName()}')}");
		</#if>
		<#if dtsqueue.getRefreshDEActionName()??>
		dtsQueueModel${dtsqueue_index?c}.setRefreshDEActionName("${srfjavastring('${dtsqueue.getRefreshDEActionName()}')}");
		</#if>
		<#if dtsqueue.getCancelDEActionName()??>
		dtsQueueModel${dtsqueue_index?c}.setCancelDEActionName("${srfjavastring('${dtsqueue.getCancelDEActionName()}')}");
		</#if>

		dtsQueueModel${dtsqueue_index?c}.init(this);
		this.registerDTSQueueModel(dtsQueueModel${dtsqueue_index?c});
</#list>    	
    	}
    	catch(Exception ex)
    	{
    		log.error(StringHelper.format("初始化分布事务处理队列发生异常，%1$s",ex.getMessage()),ex);
    	}
    }
    
    /**
     * 准备系统服务接口客户端对象
     */
    protected void prepareServiceAPIClients(){
    	try{
<#list item.getAllPSSubSysServiceAPIs() as api>
		//注册[${api.getName()}]
		${pub.getPKGCodeName()}.srv.api.${api.codeName}APIClientModel api${api_index?c} = new ${pub.getPKGCodeName()}.srv.api.${api.codeName}APIClientModel();
		api${api_index?c}.init(this);
		this.registerServiceAPIClientModel(api${api_index?c});
</#list>    	
    	}
    	catch(Exception ex)
    	{
    		log.error(StringHelper.format("初始化系统服务接口客户端发生异常，%1$s",ex.getMessage()),ex);
    	}
    }
        
    /**
     * 准备系统计数器
     */
    protected void prepareSysCounters() throws Exception{
    	try{
<#list item.getAllPSSysCounters() as syscounter>
		ICounterHandler c${syscounter_index?c} = (ICounterHandler)createObject("${pub.getPKGCodeName()}.srv.counter.${syscounter.codeName}CounterHandler");
		c${syscounter_index?c}.init(this);
</#list>    	
    	}
    	catch(Exception ex)
    	{
    		log.error(StringHelper.format("初始化系统计数器发生异常，%1$s",ex.getMessage()),ex);
    	}
    }
    
    /**
     * 准备系统值规则
     */
    protected void prepareSysValueRules(){
    	try{
<#list item.getAllPSSysValueRules() as sysvaluerule>
<#if sysvaluerule.getRuleType() == 'REG' || sysvaluerule.getRuleType() == 'REGEX'>
        net.ibizsys.paas.sysmodel.util.RegExValueRuleModel valueRuleModel${sysvaluerule_index?c} = new net.ibizsys.paas.sysmodel.util.RegExValueRuleModel();
        valueRuleModel${sysvaluerule_index?c}.setExpression("${srfjavastring('${sysvaluerule.getRegExCode()}')}");
</#if>
<#if sysvaluerule.getRuleType() == 'SCRIPT' >
        net.ibizsys.paas.sysmodel.util.ScriptValueRuleModel valueRuleModel${sysvaluerule_index?c} = new net.ibizsys.paas.sysmodel.util.ScriptValueRuleModel();
        valueRuleModel${sysvaluerule_index?c}.setCode("${srfjavastring('${sysvaluerule.getScriptCode()}')}");
</#if>
<#if sysvaluerule.getRuleType() == 'CUSTOM' >
        ${sysvaluerule.getCustomObject()} valueRuleModel${sysvaluerule_index?c} = new ${sysvaluerule.getCustomObject()}();
        valueRuleModel${sysvaluerule_index?c}.setRuleParams("${srfjavastring('${sysvaluerule.getCustomParams()}')}");
</#if>
        valueRuleModel${sysvaluerule_index?c}.setId("${srfjavastring('${sysvaluerule.getId()}')}");
        valueRuleModel${sysvaluerule_index?c}.setName("${srfjavastring('${sysvaluerule.getName()}')}");
//        valueRuleModel${sysvaluerule_index?c}.setRuleInfo("${srfjavastring('${sysvaluerule.getRuleInfo()}')}");
        valueRuleModel${sysvaluerule_index?c}.init(this);
        this.registerSystemValueRuleModel(valueRuleModel${sysvaluerule_index?c});
</#list>    	
    	}
    	catch(Exception ex)
    	{
    		log.error(StringHelper.format("初始化系统值规则发生异常，%1$s",ex.getMessage()),ex);
    	}
    }
    
    /**
     * 准备系统实体
     */
    protected void prepareDataEntities() throws Exception{

<#list item.getAllPSDataEntities() as dataEntity>
<#if dataEntity.isSubSysDE() && (dataEntity.getDynamicMode()==2)>
         createObject("${pub.getPKGCodeName()}.srv.subsys.demodel.${dataEntity.codeName}DEModel");
</#if>
</#list> 
    	
<#list item.getAllPSDataEntities() as dataEntity>
<#if !dataEntity.isSubSysDE()>
         createObject("${pub.getPKGCodeName()}.srv.${dataEntity.getPSSystemModule().codeName?lower_case}.demodel.${dataEntity.codeName}DEModel");
</#if>
</#list>    	

<#list item.getAllPSDataEntities() as dataEntity>
       IDataEntityModel _${dataEntity.codeName}DEModel = DEModelGlobal.getDEModel(${dataEntity.getClassOrPkgName("DEMODEL",pub)}.class);
<#list dataEntity.getAllPSDEActions() as deaction>
<#if deaction.getPSDEActionLogics()??>
<#list deaction.getPSDEActionLogics() as actionlogic>
<#if actionlogic.getModelData().GetParamIntValue("VALIDFLAG",1)==1 && !actionlogic.isInternalLogic()>
	<#if actionlogic.isCloneParam() || actionlogic.isIgnoreException()>
        _${dataEntity.codeName}DEModel.registerDEActionLogic("${deaction.codeName?upper_case}","${actionlogic.getAttachMode()}","${actionlogic.getDstPSDE().name}","${actionlogic.getDstPSDEAction().name}",<#if actionlogic.isCloneParam()>true<#else>false</#if>,<#if actionlogic.isIgnoreException()>true<#else>false</#if>);
    <#else>
		_${dataEntity.codeName}DEModel.registerDEActionLogic("${deaction.codeName?upper_case}","${actionlogic.getAttachMode()}","${actionlogic.getDstPSDE().name}","${actionlogic.getDstPSDEAction().name}");
	</#if>
</#if>
</#list>
 </#if>
</#list>
</#list>    

    }

    /**
     * 准备系统实体服务对象
     */
    protected void prepareServices() throws Exception{

<#list item.getAllPSDataEntities() as dataEntity>
<#if dataEntity.isSubSysDE() && (dataEntity.getDynamicMode()==2)>
         createObject2("${pub.getPKGCodeName()}.srv.subsys.service.${dataEntity.codeName}Service");
</#if>
</#list>  
    	
<#list item.getAllPSDataEntities() as dataEntity>
<#if !dataEntity.isSubSysDE()>
<#if dataEntity.getIndexDEType()=='INHERIT'>
        createObject2("${pub.getPKGCodeName()}.srv.${dataEntity.getPSSystemModule().codeName?lower_case}.service.${dataEntity.codeName}ServiceProxy");
<#else>
        createObject2("${pub.getPKGCodeName()}.srv.${dataEntity.getPSSystemModule().codeName?lower_case}.service.${dataEntity.codeName}Service");
</#if>
</#if>
</#list>    	
    }
    
    /**
     * 准备系统实体数据访问对象
     */
    protected void prepareDAOs() throws Exception{
<#list item.getAllPSDataEntities() as dataEntity>
<#if !dataEntity.isSubSysDE()>
        createObject2("${pub.getPKGCodeName()}.srv.${dataEntity.getPSSystemModule().codeName?lower_case}.dao.${dataEntity.codeName}DAO");
</#if>
</#list>    	
    }

    /**
     * 准备系统工作流模型
     */
    protected void prepareWorkflows() throws Exception{

<#list item.getAllPSWFRoles() as wfrole>
    	 createObject("${pub.getPKGCodeName()}.srv.workflow.${wfrole.codeName}WFRoleModel");
</#list>  

<#list item.getAllPSWorkflows() as workflow>
    	 createObject("${pub.getPKGCodeName()}.srv.workflow.${workflow.codeName}WFModel");
</#list>    	
    }
    
    /**
     * 准备大数据架构模型
     */
    protected void prepareBASchemes() throws Exception{
<#list item.getAllPSSysBDSchemes() as bascheme>
    	 createObject2("${pub.getPKGCodeName()}.srv.ba.${bascheme.codeName}BASchemeModel");
</#list>    	
    }
    
    /**
     * 准备微信公众号
     */
    protected void prepareWXAccounts() throws Exception{
<#list item.getAllPSWXAccounts() as wxaccount>
    	 new ${pub.getPKGCodeName()}.srv.wx.${wxaccount.codeName}WXAccountModel();
</#list>    	
    }
    
    
    /* (non-Javadoc)
     * @see net.ibizsys.paas.sysmodel.SystemModelBase#onInstallRTDatas()
     */
    @Override
   	protected void onInstallRTDatas() throws Exception
   	{
   		super.onInstallRTDatas();
   		
   		net.ibizsys.psrt.srv.common.service.UserDictCatService userDictCatService  = (net.ibizsys.psrt.srv.common.service.UserDictCatService)ServiceGlobal.getService(net.ibizsys.psrt.srv.common.service.UserDictCatService.class);
   		net.ibizsys.psrt.srv.common.service.UserDictService userDictService  = (net.ibizsys.psrt.srv.common.service.UserDictService)ServiceGlobal.getService(net.ibizsys.psrt.srv.common.service.UserDictService.class);
   		net.ibizsys.psrt.srv.common.service.UniResService uniResService  = (net.ibizsys.psrt.srv.common.service.UniResService)ServiceGlobal.getService(net.ibizsys.psrt.srv.common.service.UniResService.class);
   		net.ibizsys.psrt.srv.common.service.MsgTemplateService msgTemplateService  = (net.ibizsys.psrt.srv.common.service.MsgTemplateService)ServiceGlobal.getService(net.ibizsys.psrt.srv.common.service.MsgTemplateService.class);
   		net.ibizsys.psrt.srv.common.service.ServiceService serviceService  = (net.ibizsys.psrt.srv.common.service.ServiceService)ServiceGlobal.getService(net.ibizsys.psrt.srv.common.service.ServiceService.class);
   		net.ibizsys.psrt.srv.wf.service.WFAppSettingService wfAppSettingService  = (net.ibizsys.psrt.srv.wf.service.WFAppSettingService)ServiceGlobal.getService(net.ibizsys.psrt.srv.wf.service.WFAppSettingService.class);
   		net.ibizsys.psrt.srv.common.service.DataSyncAgentService dataSyncAgentService  = (net.ibizsys.psrt.srv.common.service.DataSyncAgentService)ServiceGlobal.getService(net.ibizsys.psrt.srv.common.service.DataSyncAgentService.class);
   		net.ibizsys.psrt.srv.wx.service.WXAccountService wxAccountService  = (net.ibizsys.psrt.srv.wx.service.WXAccountService)ServiceGlobal.getService(net.ibizsys.psrt.srv.wx.service.WXAccountService.class);
   		net.ibizsys.psrt.srv.wx.service.WXEntAppService wxEntAppService  = (net.ibizsys.psrt.srv.wx.service.WXEntAppService)ServiceGlobal.getService(net.ibizsys.psrt.srv.wx.service.WXEntAppService.class);
   	
   		onInstallRTDatas_DataEntity();

		//初始化用户词条类别
<#list item.getAllPSSysDictCats() as sysdictcat>
		//类别 ${sysdictcat.name}
		if(true){
			net.ibizsys.psrt.srv.common.entity.UserDictCat userDictCat = new net.ibizsys.psrt.srv.common.entity.UserDictCat();
			userDictCat.setUserDictCatId("${sysdictcat.id}");
			userDictCat.setUserDictCatName("${sysdictcat.name}");
			userDictCat.setUserCat(<#if sysdictcat.isUserCat()>1<#else>0</#if>);
			if(userDictCatService.checkKey(userDictCat)==IService.CHECKKEYSTATE_OK)
 			{
				userDictCatService.create(userDictCat,false);
				ActionSessionManager.appendActionInfo(StringHelper.format("[%1$s]安装[%2$s][%3$s]\r\n",this.getName(),userDictCatService.getDEModel().getLogicName(),userDictCatService.getDEModel().getDataInfo(userDictCat)));
 			}
		}
</#list>

		//初始化系统统一资源
<#list item.getAllPSSysUniReses() as sysunires>
		//统一资源 ${sysunires.name}
		if(true){
			net.ibizsys.psrt.srv.common.entity.UniRes uniRes = new net.ibizsys.psrt.srv.common.entity.UniRes();
			uniRes.setUniResId("${sysunires.id}");
			uniRes.setUniResName("${sysunires.name}");
			uniRes.setUniResType("CUSTOM");
			uniRes.setResourceId("${sysunires.getResCode()}");
			if(uniResService.checkKey(uniRes)==IService.CHECKKEYSTATE_OK)
			{
				uniResService.create(uniRes,false);
				ActionSessionManager.appendActionInfo(StringHelper.format("[%1$s]安装[%2$s][%3$s]\r\n",this.getName(),uniResService.getDEModel().getLogicName(),uniResService.getDEModel().getDataInfo(uniRes)));
			}else{
				uniResService.update(uniRes,false);
				ActionSessionManager.appendActionInfo(StringHelper.format("[%1$s]更新[%2$s][%3$s]\r\n",this.getName(),uniResService.getDEModel().getLogicName(),uniResService.getDEModel().getDataInfo(uniRes)));
			}
		}
</#list>

		//初始化系统消息模板
<#list item.getAllPSSysMsgTemples() as sysmsgtempl>
		//消息模板 ${sysmsgtempl.name}
		if(true){
			net.ibizsys.psrt.srv.common.entity.MsgTemplate msgTemplate = new net.ibizsys.psrt.srv.common.entity.MsgTemplate();
			msgTemplate.setMsgTemplateId("${sysmsgtempl.id}");
			msgTemplate.setMsgTemplateName("${sysmsgtempl.name}");
			<#if sysmsgtempl.getSubject()??>
			msgTemplate.setSubject("${srfjavastring('${sysmsgtempl.getSubject()}')}");
			</#if>
			<#if sysmsgtempl.getContentType()??>
			msgTemplate.setContentType("${srfjavastring('${sysmsgtempl.getContentType()}')}");
			</#if>
			<#if sysmsgtempl.getContent()??>
			msgTemplate.setContent("${srfjavastring('${sysmsgtempl.getContent()}')}");
			</#if>
			<#if sysmsgtempl.getWCContent()??>
			msgTemplate.setWCContent("${srfjavastring('${sysmsgtempl.getWCContent()}')}");
			</#if>
			<#if sysmsgtempl.getIMContent()??>
			msgTemplate.setIMContent("${srfjavastring('${sysmsgtempl.getIMContent()}')}");
			</#if>
			<#if sysmsgtempl.getSMSContent()??>
			msgTemplate.setSMSContent("${srfjavastring('${sysmsgtempl.getSMSContent()}')}");
			</#if>
			msgTemplate.setMailGroupSend(<#if sysmsgtempl.isMailGroupSend()>1<#else>0</#if>);
			if(msgTemplateService.checkKey(msgTemplate)==IService.CHECKKEYSTATE_OK)
			{
				msgTemplateService.create(msgTemplate,false);
				ActionSessionManager.appendActionInfo(StringHelper.format("[%1$s]安装[%2$s][%3$s]\r\n",this.getName(),msgTemplateService.getDEModel().getLogicName(),msgTemplateService.getDEModel().getDataInfo(msgTemplate)));
			}
		}
</#list>

		//初始化数据同步代理
<#list item.getAllPSSysDataSyncAgents() as sysdatasyncagent>
		//数据同步代理 ${sysdatasyncagent.name}
		if(true){
			net.ibizsys.psrt.srv.common.entity.DataSyncAgent dataSyncAgent = new net.ibizsys.psrt.srv.common.entity.DataSyncAgent();
			dataSyncAgent.setDataSyncAgentId("${sysdatasyncagent.id}");
			dataSyncAgent.setDataSyncAgentName("${sysdatasyncagent.name}");
			dataSyncAgent.setAgentType("${sysdatasyncagent.getAgentType()}");
			dataSyncAgent.setSyncDir("${sysdatasyncagent.getSyncDir()}");
			if(dataSyncAgentService.checkKey(dataSyncAgent)==IService.CHECKKEYSTATE_OK)
			{
				dataSyncAgentService.create(dataSyncAgent,false);
				ActionSessionManager.appendActionInfo(StringHelper.format("[%1$s]安装[%2$s][%3$s]\r\n",this.getName(),dataSyncAgentService.getDEModel().getLogicName(),dataSyncAgentService.getDEModel().getDataInfo(dataSyncAgent)));
			}
		}
</#list>

		//初始化系统后台作业
<#list item.getAllPSSysBackServices() as sysbackservice>
		//后台作业 ${sysbackservice.name}
		if(true){
			net.ibizsys.psrt.srv.common.entity.Service service = new net.ibizsys.psrt.srv.common.entity.Service();
			service.setServiceId("${sysbackservice.id}");
			service.setServiceName("${sysbackservice.name}");
			<#if sysbackservice.getStartMode()??>
			service.setStartMode("${srfjavastring('${sysbackservice.getStartMode()}')}");
			</#if>
			service.setServiceObject("${sysbackservice.getServiceObject(pub)}");
			<#if sysbackservice.getServiceParams()??>
			service.setServiceParam("${srfjavastring('${sysbackservice.getServiceParams()}')}");
			</#if>
			<#if sysbackservice.getServiceContainer()??>
			service.setContainer("${srfjavastring('${sysbackservice.getServiceContainer()}')}");
			</#if>
			service.setRunOrder(${sysbackservice.getServiceOrder()?c});
			if(serviceService.checkKey(service)==IService.CHECKKEYSTATE_OK)
			{
				serviceService.create(service,false);
				ActionSessionManager.appendActionInfo(StringHelper.format("[%1$s]安装[%2$s][%3$s]\r\n",this.getName(),serviceService.getDEModel().getLogicName(),serviceService.getDEModel().getDataInfo(service)));
			}
		}
</#list>
<#if item.getPSSysWFSetting()??>
<#assign syswfsetting=item.getPSSysWFSetting()>
		//初始化系统流程配置
		if(true){
			net.ibizsys.psrt.srv.wf.entity.WFAppSetting wfAppSetting = new net.ibizsys.psrt.srv.wf.entity.WFAppSetting();
			wfAppSetting.setWFAppSettingId("${syswfsetting.id}");
			wfAppSetting.setApplicationId("${syswfsetting.id}");
			wfAppSetting.setWFAppSettingName("${syswfsetting.name}");
<#if syswfsetting.getRemindPSSysMsgTemplId()??>
			wfAppSetting.setRemindMsgTemplId("${syswfsetting.getRemindPSSysMsgTemplId()}");
</#if>
			if(wfAppSettingService.checkKey(wfAppSetting)==IService.CHECKKEYSTATE_OK)
			{
				wfAppSettingService.create(wfAppSetting,false);
				ActionSessionManager.appendActionInfo(StringHelper.format("[%1$s]安装[%2$s][%3$s]\r\n",this.getName(),wfAppSettingService.getDEModel().getLogicName(),wfAppSettingService.getDEModel().getDataInfo(wfAppSetting)));
			}
		}
</#if>
<#list item.getAllPSWXAccounts() as wxaccount>
		//初始化微信公众号[${wxaccount.name}]
		if(true){
			net.ibizsys.psrt.srv.wx.entity.WXAccount wxAccount = new net.ibizsys.psrt.srv.wx.entity.WXAccount();
			wxAccount.setWXAccountId("${wxaccount.id}");
			wxAccount.setWXAccountName("${wxaccount.name}");
			wxAccount.setValidFlag(1);
			if(wxAccountService.checkKey(wxAccount)==IService.CHECKKEYSTATE_OK)
			{
				wxAccountService.create(wxAccount,false);
				ActionSessionManager.appendActionInfo(StringHelper.format("[%1$s]安装[%2$s][%3$s]\r\n",this.getName(),wxAccountService.getDEModel().getLogicName(),wxAccountService.getDEModel().getDataInfo(wxAccount)));
			}
		}
<#list wxaccount.getPSWXEntApps() as wxentapp>
		//初始化微信企业应用[${wxentapp.name}]
		if(true){
			net.ibizsys.psrt.srv.wx.entity.WXEntApp wxEntApp = new net.ibizsys.psrt.srv.wx.entity.WXEntApp();
			wxEntApp.setWXEntAppId("${wxentapp.id}");
			wxEntApp.setWXEntAppName("${wxentapp.name}");
			wxEntApp.setWXAccountId("${wxaccount.id}");
			wxEntApp.setWXAccountName("${wxaccount.name}");
			wxEntApp.setValidFlag(1);
			wxEntApp.setAppType("${wxentapp.appType}");
			if(wxEntAppService.checkKey(wxEntApp)==IService.CHECKKEYSTATE_OK)
			{
				wxEntAppService.create(wxEntApp,false);
				ActionSessionManager.appendActionInfo(StringHelper.format("[%1$s]安装[%2$s][%3$s]\r\n",this.getName(),wxEntAppService.getDEModel().getLogicName(),wxEntAppService.getDEModel().getDataInfo(wxEntApp)));
			}
		}
</#list> 
</#list> 

   	}
    
    protected void onInstallRTDatas_DataEntity() throws Exception{
    	<#list item.getAllPSSystemModules() as module>
    	<#if !module.isSubSysModule()>
    		onInstallRTDatas_DataEntity_${module.codeName}();
    	</#if>
    	</#list>
    }
    
<#list item.getAllPSSystemModules() as module>
<#if !module.isSubSysModule()>
	protected void onInstallRTDatas_DataEntity_${module.codeName}() throws Exception{
		net.ibizsys.psrt.srv.demodel.service.DataEntityService dataEntityService  = (net.ibizsys.psrt.srv.demodel.service.DataEntityService)ServiceGlobal.getService(net.ibizsys.psrt.srv.demodel.service.DataEntityService.class);
		net.ibizsys.psrt.srv.demodel.service.QueryModelService queryModelService  = (net.ibizsys.psrt.srv.demodel.service.QueryModelService)ServiceGlobal.getService(net.ibizsys.psrt.srv.demodel.service.QueryModelService.class);
		
		<#list item.getAllPSDataEntities() as dataEntity>
		<#if (dataEntity.getPSSystemModule().getId() == module.getId()) && dataEntity.isSubSysDE() && dataEntity.getDynamicMode()==2>
				if(true){
					//初始化权限查询模型
		<#list dataEntity.getAllPSDEDataQueries() as dataquery>
		<#if dataquery.isPrivQuery() && dataquery.getExtendMode()==2>
					net.ibizsys.psrt.srv.demodel.entity.QueryModel ${srfparamname("${dataquery.codeName}")} = new net.ibizsys.psrt.srv.demodel.entity.QueryModel();
					${srfparamname("${dataquery.codeName}")}.setQueryModelId("${dataquery.id}");
					${srfparamname("${dataquery.codeName}")}.setQueryModelName("${dataquery.name}");
					${srfparamname("${dataquery.codeName}")}.setQMVersion(1);
					${srfparamname("${dataquery.codeName}")}.setDEId("${dataEntity.id}");
					${srfparamname("${dataquery.codeName}")}.setDEName("${dataEntity.name}");
					if(queryModelService.checkKey(${srfparamname("${dataquery.codeName}")})==IService.CHECKKEYSTATE_OK)
		 			{
						queryModelService.create(${srfparamname("${dataquery.codeName}")},false);
						ActionSessionManager.appendActionInfo(StringHelper.format("[%1$s]安装[%2$s][%3$s]\r\n",this.getName(),queryModelService.getDEModel().getLogicName(),queryModelService.getDEModel().getDataInfo(${srfparamname("${dataquery.codeName}")})));
		 			}
		</#if>
		</#list>
				}
		</#if>
		</#list>
		   		
		<#list item.getAllPSDataEntities() as dataEntity>
		<#if (dataEntity.getPSSystemModule().getId() == module.getId()) && !dataEntity.isSubSysDE()>
				// 安装实体 "${dataEntity.logicName}"
				if(true){
					net.ibizsys.psrt.srv.demodel.entity.DataEntity dataEntity = new net.ibizsys.psrt.srv.demodel.entity.DataEntity();
					dataEntity.setDEId("${dataEntity.id}");
					dataEntity.setDEName("${dataEntity.name}");
					dataEntity.setDEType(${dataEntity.getDEType()?c});
					dataEntity.setIsLogicValid(<#if dataEntity.isLogicValid()>1<#else>0</#if>);
					dataEntity.setDELogicName("${dataEntity.logicName}");
					dataEntity.setDEVersion(1);
					if(dataEntityService.checkKey(dataEntity)==IService.CHECKKEYSTATE_OK)
		 			{
						dataEntityService.create(dataEntity,false);
						ActionSessionManager.appendActionInfo(StringHelper.format("[%1$s]安装[%2$s][%3$s]\r\n",this.getName(),dataEntityService.getDEModel().getLogicName(),dataEntityService.getDEModel().getDataInfo(dataEntity)));
		 			}
					//初始化权限查询模型
		<#list dataEntity.getAllPSDEDataQueries() as dataquery>
		<#if dataquery.isPrivQuery()>
					net.ibizsys.psrt.srv.demodel.entity.QueryModel ${srfparamname("${dataquery.codeName}")} = new net.ibizsys.psrt.srv.demodel.entity.QueryModel();
					${srfparamname("${dataquery.codeName}")}.setQueryModelId("${dataquery.id}");
					${srfparamname("${dataquery.codeName}")}.setQueryModelName("${dataquery.name}");
					${srfparamname("${dataquery.codeName}")}.setQMVersion(1);
					${srfparamname("${dataquery.codeName}")}.setDEId(dataEntity.getDEId());
					${srfparamname("${dataquery.codeName}")}.setDEName(dataEntity.getDEName());
					if(queryModelService.checkKey(${srfparamname("${dataquery.codeName}")})==IService.CHECKKEYSTATE_OK)
		 			{
						queryModelService.create(${srfparamname("${dataquery.codeName}")},false);
						ActionSessionManager.appendActionInfo(StringHelper.format("[%1$s]安装[%2$s][%3$s]\r\n",this.getName(),queryModelService.getDEModel().getLogicName(),queryModelService.getDEModel().getDataInfo(${srfparamname("${dataquery.codeName}")})));
		 			}
		</#if>
		</#list>
				}
		</#if>
		</#list>

	}
</#if>
</#list>

<#if item.getAllPSSysBDSchemes()??>
<#list item.getAllPSSysBDSchemes() as bdscheme>
@Autowired(required=false)
	@Qualifier("${srfparamname('${bdscheme.codeName}')}BADialect")
	private net.ibizsys.psba.core.IBADialect ${srfparamname('${bdscheme.codeName}')}BADialect;

     public net.ibizsys.psba.core.IBADialect get${bdscheme.codeName}BADialect(){
         return this.${srfparamname('${item.codeName}')}BADialect;
     }


       @Autowired(required=false)
	@Qualifier("${srfparamname('${bdscheme.codeName}')}BADataSource")
	private net.ibizsys.psba.core.IBADataSource ${srfparamname('${bdscheme.codeName}')}BADataSource;

     public net.ibizsys.psba.core.IBADataSource get${bdscheme.codeName}BADataSource(){
         return this.${srfparamname('${bdscheme.codeName}')}BADataSource;
     }
</#list>
</#if>

	@Autowired(required=false)
	@Qualifier("${srfparamname('${item.codeName}')}Plugins")
	private net.ibizsys.paas.core.PluginList ${srfparamname('${item.codeName}')}Plugins;
	
	/***
	 * 获取系统插件配置
	 */
	 public net.ibizsys.paas.core.PluginList get${item.codeName}Plugins(){
	     return this.${srfparamname('${item.codeName}')}Plugins;
	 }

	 @Autowired(required=false)
	 @Qualifier("${srfparamname('${item.codeName}')}ServiceAPIClientId")
	 private String ${srfparamname('${item.codeName}')}ServiceAPIClientId;
	 
	 public String getServiceAPIClientId(){
		 return this.${srfparamname('${item.codeName}')}ServiceAPIClientId;
	 }

}