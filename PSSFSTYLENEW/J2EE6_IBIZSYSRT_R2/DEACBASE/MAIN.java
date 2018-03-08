package ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.demodel;

import net.ibizsys.paas.core.DEACMode;
import net.ibizsys.paas.data.DataItem;
import net.ibizsys.paas.data.DataItemParam;



@DEACMode(name="${item.codeName?upper_case}",id="${item.id}"<#if (item.defaultMode)>,defaultmode=true</#if>,dataitems={ 
<#list item.getDataItems() as dataitem>
<#if (dataitem_index>0)>,</#if> @DataItem(name="${dataitem.name}",dataitemparams={ 
<#list dataitem.getDataItemParams() as dataitemparam>
<#if (dataitemparam_index>0)>,</#if> @DataItemParam(name="${dataitemparam.name}",format="${dataitemparam.format}"<#if dataitemparam.getCodeListId()?? && dataitemparam.getCodeListId()!="">,codelistid="${dataitemparam.getCodeListId()}"</#if>)
</#list>})
</#list>}
)

/**
 *  实体[${de.name}]自动填充 [${item.logicName}]对象模型
 */ 
public class ${de.codeName}${item.codeName}ACModel extends ${pub.getBaseClassPKGCodeName()}.paas.demodel.DEACModelBase{

   public final static String NAME = "${item.codeName?upper_case}";

   public ${de.codeName}${item.codeName}ACModel(){
        super();

        this.initAnnotation(${de.codeName}${item.codeName}ACModel.class);
<#if item.getMinorSortPSDEF()??>
        //设置默认排序 
         this.setMinorSortField("${item.getMinorSortPSDEF().name}");
    	 this.setMinorSortDir("${item.getMinorSortDir()}");
</#if>
   }

}