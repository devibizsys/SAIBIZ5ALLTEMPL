package ${pub.getPKGCodeName()}.srv.subsys.demodel;


import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ibizsys.paas.core.IDataEntity;
import net.ibizsys.paas.core.DEDataQuery;
import net.ibizsys.paas.core.DEDataQueryCodes;
import net.ibizsys.paas.core.DEDataQueryCode;
import net.ibizsys.paas.core.DEDataQueryCodeExp;
import net.ibizsys.paas.core.DEDataQueryCodeCond;

@DEDataQuery(id="${srfxmlvalue('${item.id}')}",name="${srfxmlvalue('${item.name}')}")
<#if ( item.getAllPSDEDataQueryCodes()??)>
@DEDataQueryCodes({<#list item.getAllPSDEDataQueryCodes() as dedqcode><#if (dedqcode_index>0)>,</#if>
 @DEDataQueryCode(querycode="${srfsqlcode('${dedqcode.getQueryCode()}')}",querycodetemp="${srfsqlcode('${dedqcode.getQueryCodeTemp()}')}",declarecode="${srfsqlcode('${dedqcode.getDeclareCode()}')}",dbtype="${dedqcode.getDBType()}",
 fieldexps={<#list dedqcode.getPSDEDataQueryCodeExps() as dedqfieldexp><#if (dedqfieldexp_index>0)>,</#if>@DEDataQueryCodeExp(name="${srfxmlvalue('${dedqfieldexp.name}')}",expression="${srfsqlcode('${dedqfieldexp.expression}')}")
</#list>},
 conds={<#list dedqcode.getPSDEDataQueryCodeConds() as dedqfieldcond><#if (dedqfieldcond_index>0)>,</#if>@DEDataQueryCodeCond(condition="${srfjavasqlcode('${dedqfieldcond.getCustomCond()}')}")
</#list>})</#list>})
</#if>

public class ${de.codeName}${item.codeName}DQModel extends ${pub.getBaseClassPKGCodeName()}.paas.demodel.DEDataQueryModelBase{

   public ${de.codeName}${item.codeName}DQModel(){
        super();

        this.initAnnotation(${de.codeName}${item.codeName}DQModel.class);
   }

}