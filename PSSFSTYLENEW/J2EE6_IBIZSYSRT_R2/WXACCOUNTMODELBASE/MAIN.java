package ${pub.getPKGCodeName()}.srv.wx;


import net.ibizsys.paas.sysmodel.SysModelGlobal;
import net.ibizsys.paas.sysmodel.ISystemModel;
import ${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel;
import net.ibizsys.pswx.core.WXGlobal;

/**
 * ${item.name} 微信公众号模型基类
 */
public class ${item.codeName}WXAccountModel extends ${pub.getBaseClassPKGCodeName()}.pswx.core.WXAccountModelBase{

    public ${item.codeName}WXAccountModel() throws Exception{
       
        super();
        this.setId("${item.id}");
        this.setName("${item.name}");

       ///注册企业应用
	prepareWXEntAppModels();

        //注册公众号
        WXGlobal.registerWXAccountModel("${item.id}",this);
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

     protected void prepareWXEntAppModels()throws Exception{
<#if item.getPSWXEntApps()??>
<#list item.getPSWXEntApps() as wxentapp>
            ${wxentapp.getPSWXAccount().codeName}${wxentapp.codeName}WXEntAppModel ${srfparamname('${wxentapp.codeName}')}WXEntAppModel = new ${wxentapp.getPSWXAccount().codeName}${wxentapp.codeName}WXEntAppModel();
            ${srfparamname('${wxentapp.codeName}')}WXEntAppModel.init(this);
            this.registerWXEntAppModel(${srfparamname('${wxentapp.codeName}')}WXEntAppModel);
</#list>
</#if>
    }

}