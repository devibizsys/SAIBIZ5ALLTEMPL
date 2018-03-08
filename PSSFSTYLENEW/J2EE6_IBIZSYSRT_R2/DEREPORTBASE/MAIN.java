package ${pub.getPKGCodeName()}.srv.${de.getPSSystemModule().codeName?lower_case}.demodel;


import java.util.List;
import net.ibizsys.paas.report.ReportServiceGlobal;


/**
 * 实体[${de.name}]报表[${item.name}]模型对象基类
 */
public class ${de.codeName}${item.codeName}ReportService extends ${pub.getBaseClassPKGCodeName()}.paas.report.${item.getReportType()?lower_case}.${item.getReportType()?upper_case}ReportServiceBase{

   public ${de.codeName}${item.codeName}ReportService(){
        super();
        //设置基本信息
        this.setId("${srfxmlvalue('${item.id}')}");
        this.setName("${srfxmlvalue('${item.name}')}");
<#if item.getPSDEDataSet()??>
		//设置报表数据集合名称
		this.setDEDataSetName("${srfxmlvalue('${item.getPSDEDataSet().name}')}");
</#if>
<#if item.isEnableLog()>
		//设置启用报表查询日志
		this.setEnableLog(true);
</#if>
<#if item.getPSSysUniRes()??>
		//设置报表查看需要的资源标识
		this.setAccessKey("${srfxmlvalue('${item.getPSSysUniRes().getResCode()}')}");
</#if>     	
<#if item.getReportFile()??>
		//设置报表文件
		this.setReportFilePath("${srfxmlvalue('${item.getReportFile()}')}");
</#if>    
<#if item.isMultiPage()>
		//当前支持复合报表，进行自报表注册
<#list item.getPSDEReportItems() as subrep>
		//注册子报表[${subrep.getMinorPSDEReport().name}]
		this.registerSubReport("${srfxmlvalue('${subrep.getMinorPSDEReport().id}')}");
</#list>
</#if>
    }

}