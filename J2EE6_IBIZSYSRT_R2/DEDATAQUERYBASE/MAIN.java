package ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.demodel;



import net.ibizsys.paas.core.DEDataQuery;
import net.ibizsys.paas.core.DEDataQueryCodes;
import net.ibizsys.paas.core.DEDataQueryCode;
import net.ibizsys.paas.core.DEDataQueryCodeExp;
import net.ibizsys.paas.core.DEDataQueryCodeCond;

@DEDataQuery(id="${srfxmlvalue('${item.id}')}",name="${srfxmlvalue('${item.name}')}"<#if item.isDefaultMode()>,defaultmode=true</#if><#if item.getViewLevel() gte 0>,viewlevel=${item.getViewLevel()?c}</#if>)
<#if ( item.getAllPSDEDataQueryCodes()??)>
@DEDataQueryCodes({<#list item.getAllPSDEDataQueryCodes() as dedqcode><#if (dedqcode_index>0)>,</#if>
 @DEDataQueryCode(querycode="${srfjavasqlcode('${dedqcode.getQueryCode()}')}",querycodetemp="${srfjavasqlcode('${dedqcode.getQueryCodeTemp()}')}",declarecode="${srfjavasqlcode('${dedqcode.getDeclareCode()}')}",dbtype="${dedqcode.getDBType()}",
 fieldexps={<#list dedqcode.getPSDEDataQueryCodeExps() as dedqfieldexp><#if (dedqfieldexp_index>0)>,</#if>@DEDataQueryCodeExp(name="${srfjavasqlcode('${dedqfieldexp.name}')}",expression="${srfjavasqlcode('${dedqfieldexp.expression}')}")
</#list>},
 conds={<#list dedqcode.getPSDEDataQueryCodeConds() as dedqfieldcond><#if (dedqfieldcond_index>0)>,</#if>@DEDataQueryCodeCond(condition="${srfjavasqlcode('${dedqfieldcond.getCustomCond()}')}")
</#list>})</#list>})
</#if>
/**
 *  实体数据查询 [${item.logicName}]模型基类
 */ 
public class ${de.codeName}${item.codeName}DQModel extends ${pub.getBaseClassPKGCodeName()}.paas.demodel.DEDataQueryModelBase{

   public ${de.codeName}${item.codeName}DQModel(){
        super();

        this.initAnnotation(${de.codeName}${item.codeName}DQModel.class);
   }

}