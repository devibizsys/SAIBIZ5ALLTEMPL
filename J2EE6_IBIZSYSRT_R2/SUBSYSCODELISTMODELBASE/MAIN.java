package ${pub.getPKGCodeName()}.srv.subsys.codelist;


import net.ibizsys.paas.codelist.CodeItem;
import net.ibizsys.paas.codelist.CodeItems;
import net.ibizsys.paas.codelist.CodeList;
import net.ibizsys.paas.sysmodel.StaticCodeListModelBase;
import net.ibizsys.paas.sysmodel.CodeListGlobal;

<#if item.getCodeListType()=='DYNAMIC' >
import net.ibizsys.paas.sysmodel.DynamicCodeListModelBase;
import net.ibizsys.paas.db.DBFetchResult;
import net.ibizsys.paas.core.DEDataSetFetchContext;
import net.ibizsys.paas.demodel.DEModelGlobal;
import net.ibizsys.paas.demodel.IDataEntityModel;
import net.ibizsys.paas.service.IService;
import net.ibizsys.paas.service.ServiceGlobal;

<#if item.getPSDEDataSet()??>
<#assign de=item.getPSDataEntity()>
<#if de.getDynamicMode() == 2>
import ${pub.getPKGCodeName()}.srv.subsys.service.${de.codeName}Service;
import ${pub.getPKGCodeName()}.srv.subsys.demodel.${de.codeName}DEModel;
<#else>
import ${de.getClassOrPkgName('SUBSYS_SERVICE',pub)};
import ${de.getClassOrPkgName('SUBSYS_DEMODEL',pub)};
</#if>
</#if>
</#if>

@CodeList(id="${item.id}",name="${item.name}",type="${item.getCodeListType()}",userscope=<#if item.isUserScope()>true<#else>false</#if>,emptytext="${item.getEmptyText()}"<#if item.getOrMode()?? && (item.getOrMode()?length>0)>,ormode="${item.getOrMode()}"</#if><#if  (item.getValueSeparator()??) && (item.getValueSeparator()?length>0)>,valueseparator="${item.getValueSeparator()}"</#if><#if (item.getTextSeparator()??)&&(item.getTextSeparator()?length>0)>,textseparator="${item.getTextSeparator()}"</#if>)

@CodeItems({
<#list codeitems as codeitem>
<#if (codeitem_index>0)>,</#if>@CodeItem(value="${codeitem.value}",text="${codeitem.text}",realtext="${codeitem.realText}"<#if codeitem.getParentCodeItem()??>,parentvalue="${codeitem.getParentCodeItem().value}"</#if><#if codeitem.textCls?? && codeitem.textCls?length gt 0>,textcls="${codeitem.textCls}"</#if><#if codeitem.getIconCls()?? && codeitem.getIconCls()?length gt 0>,iconcls="${codeitem.getIconCls()}"</#if><#if codeitem.iconClsX?? && codeitem.iconClsX?length gt 0>,iconclsx="${codeitem.iconClsX}"</#if><#if codeitem.getIconPath()?? && codeitem.getIconPath()?length gt 0>,iconpath="${codeitem.getIconPath()}"</#if><#if codeitem.iconPathX?? && codeitem.iconPathX?length gt 0>,iconpathx="${codeitem.iconPathX}"</#if><#if codeitem.disableSelect>,disableselect=true</#if><#if codeitem.userData?? && codeitem.userData?length gt 0>,userdata="${codeitem.userData}"</#if><#if codeitem.userData2?? && codeitem.userData2?length gt 0>,userdata2="${codeitem.userData2}"</#if>)
</#list>
})

<#assign codelistclass=''>
<#if item.getPredefinedType()??>
<#if item.getPredefinedType()=='OPERATOR'>
<#assign codelistclass='SysOperator'>
<#assign codelistbase='${pub.getBaseClassPKGCodeName()}.paas.sysmodel.'>

</#if>
<#else>
<#comment><#assign codelistbase='${pub.getPKGCodeName()}.srv.codelist.${item.getPSSystem().codeName}'></#comment>
<#assign codelistbase='${pub.getBaseClassPKGCodeName()}.paas.sysmodel.'>

<#if item.getCodeListType()=='STATIC'>
<#assign codelistclass='Static'>
<#else>
<#assign codelistclass='Dynamic'>
</#if>
</#if>

/**
 * <#if item.getCodeListType()=='STATIC'>静态<#else>动态</#if>代码表[${item.name}]模型类
 */
public class ${item.codeName}CodeListModel extends ${codelistbase}<#if item.isUserScope()>UserScope</#if>${codelistclass}CodeListModelBase  {

   <#list codeitems as codeitem>
   /**
    *  ${codeitem.text}
    */
   <#if item.isCodeItemValueNumber()>
   public final static Integer ${codeitem.getCodeName()?upper_case} = ${codeitem.value};
   <#else>
   public final static String ${codeitem.getCodeName()?upper_case} = "${codeitem.value}";
   </#if>
   </#list> 


   public ${item.codeName}CodeListModel(){
        super();
         this.initAnnotation(${item.codeName}CodeListModel.class); 
<#if item.getCodeListType()=='DYNAMIC' >
<#if item.getPSDataEntity()??>
<#if item.getTextPSDEField()??>
         this.setTextField("${item.getTextPSDEField().name}");
</#if>
<#if item.getValuePSDEField()??>
         this.setValueField("${item.getValuePSDEField().name}");
</#if>
<#if item.getPValuePSDEField()??>
         this.setParentValue("${item.getPValuePSDEField().name}");
</#if>
<#if item.getFetchCondition()??>
         this.setDSCondition("${item.getFetchCondition()}");
</#if>
<#if item.getMinorSortPSDEField()??>
         this.setMinorSortField("${item.getMinorSortPSDEField().name}");
    	 this.setMinorSortDir("${item.getMinorSortDir()}");
</#if>
<#if item.getIconClsPSDEField()??>
		this.setIconClsField("${item.getIconClsPSDEField().name}");
</#if>
<#if item.getIconClsXPSDEField()??>
		this.setIconClsXField("${item.getIconClsXPSDEField().name}");
</#if>
<#if item.getIconPathPSDEField()??>
		this.setIconPathField("${item.getIconPathPSDEField().name}");
</#if>
<#if item.getIconPathXPSDEField()??>
		this.setIconPathXField("${item.getIconPathXPSDEField().name}");
</#if>
</#if>
</#if>
        CodeListGlobal.registerCodeList("${pub.getPKGCodeName()}.srv.subsys.codelist.${item.codeName}CodeListModel", this);
   }

<#if item.getCodeListType()=='DYNAMIC' >
<#if item.getPSDEDataSet()??>
<#assign de=item.getPSDataEntity()>
       private ${de.codeName}DEModel ${srfparamname('${de.codeName}')}DEModel;

       /**
        * 获取实体[${de.name}]模型对象
        */
       public  ${de.codeName}DEModel get${de.codeName}DEModel() {
            if(this.${srfparamname('${de.codeName}')}DEModel==null){
                try
                {
                     this.${srfparamname('${de.codeName}')}DEModel = (${de.codeName}DEModel)DEModelGlobal.getDEModel(${de.codeName}DEModel.class);
                }
                catch(Exception ex)
                {
                }
            }
           return this.${srfparamname('${de.codeName}')}DEModel;
      }

       /* (non-Javadoc)
        * @see net.ibizsys.paas.sysmodel.DynamicCodeListModelBase#getDEModel()
        */
       @Override
       protected IDataEntityModel getDEModel(){
    	   return this.get${de.codeName}DEModel();
       }
    
     private ${de.codeName}Service ${srfparamname('${de.codeName}')}Service;

     /**
      * 获取实体[${de.name}]实际服务对象
      */
     public  ${de.codeName}Service getRealService() {
         if(this.${srfparamname('${de.codeName}')}Service==null){
                try
                {
                     this.${srfparamname('${de.codeName}')}Service = (${de.codeName}Service)ServiceGlobal.getService(${de.codeName}Service.class,this.getSessionFactory());
                }
                catch(Exception ex)
                {
                }
            }
        return this.${srfparamname('${de.codeName}')}Service;
    }

     /* (non-Javadoc)
 	 * @see net.ibizsys.paas.sysmodel.DynamicCodeListModelBase#getService()
 	 */
    @Override
	protected IService getService()
  	{
  		return getRealService();
  	}

<#assign dename=de.getName()>
<#assign dsname=item.getPSDEDataSet().getName()>
<#if !(dename='CODEITEM' && dsname='DEFAULT')>
    /* (non-Javadoc)
	 * @see net.ibizsys.paas.sysmodel.DynamicCodeListModelBase#onPrepareCodeItems()
	 */
	@Override
	protected void onPrepareCodeItems() throws Exception
	{
	    DEDataSetFetchContext deDataSetFetchContextImpl = new DEDataSetFetchContext(null);
            deDataSetFetchContextImpl.setSort(this.getMinorSortField());
            deDataSetFetchContextImpl.setSortDir(this.getMinorSortDir());
	    fillFetchConditions(deDataSetFetchContextImpl);
	    DBFetchResult fetchResult = this.getRealService().fetch${item.getPSDEDataSet().codeName}(deDataSetFetchContextImpl);
	    fillFetchResult(fetchResult.getDataSet().getDataTable(0));
	}
</#if>

</#if>
</#if>
}