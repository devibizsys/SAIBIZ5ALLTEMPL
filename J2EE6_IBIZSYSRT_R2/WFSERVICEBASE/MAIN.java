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
import ${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel;
import net.ibizsys.pswf.core.IWFModel;


/**
 * ${item.name} 工作流服务类
 */
public class ${item.codeName}WFService extends ${pub.getPKGCodeName()}.srv.${item.getPSSystem().codeName}SysWFServiceBase {

    public ${item.codeName}WFService() throws Exception{
       
        super();
    }

}