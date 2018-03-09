package ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.demodel;


import net.ibizsys.paas.core.DEDataSet;
import net.ibizsys.paas.core.DEDataSetQuery;
import net.ibizsys.paas.sysmodel.CodeListGlobal;
import net.ibizsys.paas.codelist.ICodeList;
import net.ibizsys.paas.demodel.DEDataSetGroupParamModel;

@DEDataSet(id="${srfxmlvalue('${item.id}')}",name="${srfxmlvalue('${item.name}')}",queries={<#if item.getPSDEDataQueries()??>
<#list item.getPSDEDataQueries() as dedsquery>
<#if (dedsquery_index>0)>,</#if>@DEDataSetQuery(queryid="${srfxmlvalue('${dedsquery.id}')}",queryname="${srfxmlvalue('${dedsquery.name}')}")
</#list></#if>}) 

<#if item.getPredefineType()==''>
/**
 *  实体[${de.name}]数据集合[${item.logicName}]模型基类
 */
public class ${de.codeName}${item.codeName}DSModel extends ${pub.getPKGCodeName()}.srv.${item.getPSDataEntity().getPSSystem().codeName}SysDSModelBase{
</#if>
<#if item.getPredefineType()=='INDEXDE'>
/**
 *  实体[${de.name}]索引实体类型数据集合[${item.logicName}]模型基类
 */
public class ${de.codeName}${item.codeName}DSModel extends ${pub.getPKGCodeName()}.srv.${item.getPSDataEntity().getPSSystem().codeName}SysCodeListDSModelBase {
</#if>
<#if item.getPredefineType()=='MULTIFORM'>
/**
 *  实体[${de.name}]多表单类型数据集合[${item.logicName}]模型基类
 */
public class ${de.codeName}${item.codeName}DSModel extends ${pub.getPKGCodeName()}.srv.${item.getPSDataEntity().getPSSystem().codeName}SysCodeListDSModelBase {
</#if>
<#if item.getPredefineType()=='CODELIST'>
/**
 *  实体[${de.name}]代码表数据集合[${item.logicName}]模型基类
 */
public class ${de.codeName}${item.codeName}DSModel extends ${pub.getPKGCodeName()}.srv.${item.getPSDataEntity().getPSSystem().codeName}SysCodeListDSModelBase {
</#if>
 	public ${de.codeName}${item.codeName}DSModel(){
        super();
<#if item.isEnableGroup()>
		//设置启用数据集合分组功能
        this.setEnableGroup(true);
<#if (item.getGroupTopCount()>0)>
        this.setGroupTopCount(${item.getGroupTopCount()?c});
</#if>
</#if>
<#if item.isEnableOrgDR()>
         this.setEnableOrgDR(true);
         this.setOrgDR(${item.getOrgDR()?c});
</#if>
<#if item.isEnableSecDR()>
         this.setEnableSecDR(true);
         this.setSecDR(${item.getSecDR()?c});
</#if>
<#if item.isEnableSecBC()>
         this.setEnableSecBC(true);
         this.setSecBC("${item.getSecBC()}");
</#if>
<#if item.isEnableUserDR()>
         this.setEnableUserDR(true);
</#if>
<#if (item.getActiveDataDELogicId()??) && (item.getActiveDataDELogicId()?length gt 0)>
         //设置上下文数据计算逻辑
         this.setActiveDataDELogicId("${srfjavastring('${item.getActiveDataDELogicId()}')}");
</#if>
        this.initAnnotation(${de.codeName}${item.codeName}DSModel.class);
   }

<#if item.isEnableGroup()>
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DEDataSetModelBase#prepareDEDataSetGroupParams()
	 */
	@Override
	protected void prepareDEDataSetGroupParams() throws Exception
	{
            
<#list item.getPSDEDataSetGroupParams() as groupparam>
            //注册 ${groupparam.name}
            DEDataSetGroupParamModel paramModel${groupparam_index?c} = new DEDataSetGroupParamModel();
            paramModel${groupparam_index?c}.setName("${groupparam.name}");
<#if groupparam.getGroupCode()??>
            paramModel${groupparam_index?c}.setGroupCode("${groupparam.getGroupCode()}");
</#if>
<#if groupparam.getSortDir()??>
            paramModel${groupparam_index?c}.setSortDir("${groupparam.getSortDir()}");
</#if>
<#if groupparam.isEnableGroup()>
            paramModel${groupparam_index?c}.setEnableGroup(true);
</#if>
<#if groupparam.isReCalc()>
            paramModel${groupparam_index?c}.setReCalc(true);
</#if>
<#if groupparam.getGroupFields()??>                                                                                                                        
           paramModel${groupparam_index?c}.setGroupFields(new String[]{ <#list groupparam.getGroupFields() as field><#if (field_index>0)>,</#if>"${field}"</#list> });
</#if>
            paramModel${groupparam_index?c}.init(this);
            this.registerDEDataSetGroupParam(paramModel${groupparam_index?c});
</#list>          
	    super.prepareDEDataSetGroupParams();
	}
</#if>

<#if item.getPredefineType()=='INDEXDE'>
<#if (de.getIndexTypePSDEField()??)&& (de.getIndexTypePSDEField().getPSCodeList()??) >
<#assign codelist=de.getIndexTypePSDEField().getPSCodeList()>
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.CodeListDEDataSetModelBase#getCodeList()
	 */
	@Override
	protected ICodeList getCodeList() throws Exception
	{
		return (ICodeList)CodeListGlobal.getCodeList("${pub.getPKGCodeName()}.srv.codelist.${codelist.codeName}CodeListModel");
	}
</#if>
</#if>

<#if item.getPredefineType()=='MULTIFORM'>
<#if (de.getFormTypePSDEField()??)&& (de.getFormTypePSDEField().getPSCodeList()??) >
<#assign codelist=de.getFormTypePSDEField().getPSCodeList()>
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.CodeListDEDataSetModelBase#getCodeList()
	 */
	@Override
	protected ICodeList getCodeList() throws Exception
	{
		return (ICodeList)CodeListGlobal.getCodeList("${pub.getPKGCodeName()}.srv.codelist.${codelist.codeName}CodeListModel");
	}
</#if>
</#if>

<#if (item.getPredefineType()=='CODELIST') && (item.getPSCodeList()??)>
<#assign codelist=item.getPSCodeList()>
	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.CodeListDEDataSetModelBase#getCodeList()
	 */
 	@Override
	protected ICodeList getCodeList() throws Exception
	{
		return (ICodeList)CodeListGlobal.getCodeList("${codelist.getClassOrPkgName('CODELIST',pub)}");
	}
</#if>
}