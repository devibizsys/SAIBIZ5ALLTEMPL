package ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.demodel.${de.codeName?lower_case}.dbproc;


import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ibizsys.paas.core.IDataEntity;
import net.ibizsys.paas.core.DEDBProc;
import net.ibizsys.paas.core.DBProcParams;
import net.ibizsys.paas.core.DBProcParam;
import net.ibizsys.paas.core.DEDBProcDialect;


public class ${de.codeName}${item.codeName}DBProcModel extends ${de.codeName}${item.codeName}DBProcModelBase{

   public ${de.codeName}${item.codeName}DBProcModel(){
        super();

        this.initAnnotation(${de.codeName}${item.codeName}DBProcModel.class);
   }

}