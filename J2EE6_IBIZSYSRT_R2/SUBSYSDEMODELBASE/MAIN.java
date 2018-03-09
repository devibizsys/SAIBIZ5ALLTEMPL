package ${pub.getPKGCodeName()}.srv.subsys.demodel;


import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.SessionFactory;
import net.ibizsys.paas.core.DEDataSetCond;
import net.ibizsys.paas.logic.ICondition;
import net.ibizsys.paas.core.IDEDataSetCond;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import net.ibizsys.paas.core.IDEDataQuery;
import net.ibizsys.paas.core.IDEDataSet;
import net.ibizsys.paas.core.IDEAction;
import net.ibizsys.paas.core.IDEACMode;
import net.ibizsys.paas.core.IDELogic;
import net.ibizsys.paas.core.IDEUIAction;
import net.ibizsys.paas.core.IDEWF;
import net.ibizsys.paas.core.IDEMainState;
import net.ibizsys.paas.core.IDEDataImport;
import net.ibizsys.paas.report.IPrintService;
import net.ibizsys.paas.report.IReportService;
import ${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel;


import ${de.getClassOrPkgName('SERVICE',pub)};
import ${de.getClassOrPkgName('DAO',pub)};
import ${de.getClassOrPkgName('ENTITY',pub)};


/**
 * ${item.logicName} 实体模型
 * 注意，此文件不能修改
 */
public class ${item.codeName}DEModel extends ${de.getClassOrPkgName('SUBSYS_DEMODEL',pub)}  {

   public ${item.codeName}DEModel() throws Exception {
        super();

        DEModelGlobal.registerDEModel("${pub.getPKGCodeName()}.srv.subsys.demodel.${item.codeName}DEModel",this);
   }

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

	/**
     * 获取实体[${item.codeName}]模型对象
     * @return
     * @throws Exception
     */   
    public static ${item.codeName}DEModel getInstance() throws Exception{
    	return (${item.codeName}DEModel)DEModelGlobal.getDEModel(${item.codeName}DEModel.class);
    } 

  	@Override
  	public String getServiceId()
  	{
  		return "${pub.getPKGCodeName()}.srv.subsys.service.${item.codeName}Service";
  	}


   	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEDataSets()
	 */
	@Override
	protected void prepareDEDataSets() throws Exception
	{
		super.prepareDEDataSets();
<#list item.getAllPSDEDataSets() as dedataset>
<#if dedataset.getExtendMode()==2>
                //注册  ${dedataset.name}
               IDEDataSet ${srfparamname('${dedataset.codeName}')}DSModel = (IDEDataSet)this.get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.subsys.demodel.${item.codeName}${dedataset.codeName}DSModel");
               ${srfparamname('${dedataset.codeName}')}DSModel.init(this);
               this.registerDEDataSet(${srfparamname('${dedataset.codeName}')}DSModel);
</#if>        
</#list>
	}




	/* (non-Javadoc)
	 * @see net.ibizsys.paas.demodel.DataEntityModelBase#prepareDEDataQueries()
	 */
	@Override
	protected void prepareDEDataQueries() throws Exception
	{
		super.prepareDEDataQueries();
<#list item.getAllPSDEDataQueries() as dedataquery>
<#if dedataquery.getExtendMode()==2>
              //注册  ${dedataquery.name}
              IDEDataQuery ${srfparamname('${dedataquery.codeName}')}DQModel =(IDEDataQuery)this.get${sys.codeName}SysModel().createObject("${pub.getPKGCodeName()}.srv.subsys.demodel.${item.codeName}${dedataquery.codeName}DQModel");
               ${srfparamname('${dedataquery.codeName}')}DQModel.init(this);
               this.registerDEDataQuery(${srfparamname('${dedataquery.codeName}')}DQModel);
</#if> 
</#list>
	}

}