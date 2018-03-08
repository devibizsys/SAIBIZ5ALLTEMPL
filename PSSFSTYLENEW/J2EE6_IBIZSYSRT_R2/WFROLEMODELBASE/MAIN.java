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


/**
 * ${item.name} 流程角色模型基类
 */
<#assign wfroletype="">
<#if item.getWFRoleType()=='USERGROUP'>
<#assign wfroletype="UserGroup">
</#if>
<#if item.getWFRoleType()=='CUSTOM'>
<#assign wfroletype="Custom">
</#if>
<#if item.getWFRoleType()=='CUSTOM'>
public class ${item.codeName}WFRoleModel extends ${pub.getPKGCodeName()}.srv.${sys.codeName}SysWFCustomRoleModelBase {
<#else>
public class ${item.codeName}WFRoleModel extends ${pub.getBaseClassPKGCodeName()}.pswf.core.WF${wfroletype}RoleModelBase  {
</#if>

    public ${item.codeName}WFRoleModel() throws Exception{
       
        super();
        this.setId("${item.id}");
        this.setName("${item.name}");
        this.setWFRoleSN("${item.getWFRoleSN()}");
        this.setUserData("${item.getUserData()}");
        this.setUserData2("${item.getUserData2()}");

         //注册流程角色
        get${sys.codeName}SysModel().registerWFRoleModel(this);
     }

      private ${sys.codeName}SysModel ${srfparamname('${sys.codeName}')}SysModel;
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



       @Override
       public  ISystemModel getSystemModel() {
        return this.get${sys.codeName}SysModel();
     }


 
}