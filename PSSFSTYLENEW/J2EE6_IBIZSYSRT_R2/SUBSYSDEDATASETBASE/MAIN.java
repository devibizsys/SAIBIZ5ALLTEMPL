package ${pub.getPKGCodeName()}.srv.subsys.demodel;


import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
public class ${de.codeName}${item.codeName}DSModel extends ${pub.getBaseClassPKGCodeName()}.paas.demodel.DEDataSetModelBase{
</#if>
<#if item.getPredefineType()=='INDEXDE'>
public class ${de.codeName}${item.codeName}DSModel extends ${pub.getBaseClassPKGCodeName()}.paas.demodel.CodeListDEDataSetModelBase{
</#if>
<#if item.getPredefineType()=='MULTIFORM'>
public class ${de.codeName}${item.codeName}DSModel extends ${pub.getBaseClassPKGCodeName()}.paas.demodel.CodeListDEDataSetModelBase{
</#if>
<#if item.getPredefineType()=='CODELIST'>
public class ${de.codeName}${item.codeName}DSModel extends ${pub.getBaseClassPKGCodeName()}.paas.demodel.CodeListDEDataSetModelBase{
</#if>
 public ${de.codeName}${item.codeName}DSModel(){
        super();
<#if item.isEnableGroup()>
        this.setEnableGroup(true);
<#if (item.getGroupTopCount()>0)>
        this.setGroupTopCount(${item.getGroupTopCount()?c});
</#if>
</#if>
        this.initAnnotation(${de.codeName}${item.codeName}DSModel.class);
   }

<#if item.isEnableGroup()>
     /**
       * 准备分组参数
       * @throws Exception
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
    
	@Override
	protected ICodeList getCodeList() throws Exception
	{
		return (ICodeList)CodeListGlobal.getCodeList("${pub.getPKGCodeName()}.srv.codelist.${codelist.codeName}CodeListModel");
	}
</#if>
</#if>

<#if (item.getPredefineType()=='CODELIST') && (item.getPSCodeList()??)>
<#assign codelist=item.getPSCodeList()>
 	@Override
	protected ICodeList getCodeList() throws Exception
	{
		return (ICodeList)CodeListGlobal.getCodeList("${codelist.getClassOrPkgName('CODELIST',pub)}");
	}
</#if>
}