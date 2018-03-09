package ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.demodel;



import net.ibizsys.paas.report.PrintServiceGlobal;


/**
 * 实体[${de.name}]打印[${item.name}]模型对象基类
 */
public class ${de.codeName}${item.codeName}PrintService extends ${pub.getBaseClassPKGCodeName()}.paas.report.${item.getReportType()?lower_case}.${item.getReportType()?upper_case}PrintServiceBase{

   public ${de.codeName}${item.codeName}PrintService(){
        super();
        //设置基本信息
        this.setId("${srfxmlvalue('${item.id}')}");
        this.setName("${srfxmlvalue('${item.name}')}");
<#if item.getDetailPSDE()??>
		this.setDetailDEName("${srfxmlvalue('${item.getDetailPSDE().name}')}");
</#if>
<#if item.getPSDEDataSet()??>
		//设置明细数据结果集合
		this.setDetailDEDataSetName("${srfxmlvalue('${item.getPSDEDataSet().name}')}");
</#if>
<#if item.isEnableColPriv()>
		//设置启用列权限控制
		this.setEnableColPriv(true);
</#if>
<#if item.isEnableLog()>
		//设置启用打印日志
		this.setEnableLog(true);
</#if>
<#if item.isEnableMulitPrint()>
		//设置启用多页连打
		this.setEnableMultiPrint(true);
</#if>        
<#if item.getGetDataPSDEAction()??>
		//设置获取数据的实体操作行为名称
		this.setGetDataDEActionName("${srfxmlvalue('${item.getGetDataPSDEAction().name}')}");
</#if> 
<#if item.getGetDataPSDEOPPriv()??>
		//设置获取数据的实体操作权限标识
		this.setGetDataDataAccessAction("${srfxmlvalue('${item.getGetDataPSDEOPPriv().name}')}");
</#if>     	
<#if item.getReportFile()??>
		//设置报表路径
		this.setReportFilePath("${srfxmlvalue('${item.getReportFile()}')}");
</#if>    

    }

}