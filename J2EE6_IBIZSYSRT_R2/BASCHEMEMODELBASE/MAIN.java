package ${pub.getPKGCodeName()}.srv.ba;

import net.ibizsys.psba.core.IBATable;
import net.ibizsys.psba.core.IBATableDE;
import net.ibizsys.psba.core.IBATableModel;
import net.ibizsys.psba.core.IBAColumn;
import net.ibizsys.psba.core.BATableModel;
import net.ibizsys.psba.core.BATableDEModel;
import net.ibizsys.psba.core.BATableDERModel;
import net.ibizsys.psba.core.BAColumnModel;
import net.ibizsys.psba.core.BAColSetModel;
import net.ibizsys.psba.core.IBADataSource;
import net.ibizsys.psba.core.IBADialect;
import net.ibizsys.psba.entity.BAEntity;
import net.ibizsys.psba.entity.IBAEntity;
import net.ibizsys.paas.sysmodel.SysModelGlobal;
import net.ibizsys.paas.sysmodel.ISystemModel;
import net.ibizsys.paas.util.StringHelper;
import ${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel;



/**
 * ${item.name} 大数据架构模型基类
 */
public class ${item.codeName}BASchemeModel extends ${pub.getBaseClassPKGCodeName()}.psba.core.BASchemeModelBase{

    public ${item.codeName}BASchemeModel() throws Exception{
       
        super();
        this.setId("${item.id}");
        this.setName("${item.name}");

        //注册大数据架构
        get${sys.codeName}SysModel().registerBASchemeModel(this);
     
         //注册大数据表
        prepareBATableModels();

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

     @Override
     public IBADialect getBADialect(){
         return this.get${sys.codeName}SysModel().get${item.codeName}BADialect();
     }


       @Override
     public IBADataSource getBADataSource(){
         return this.get${sys.codeName}SysModel().get${item.codeName}BADataSource();
     }
 

     /**
      * 准备大数据表
      * @throws Exception
      */
	protected void prepareBATableModels() throws Exception
	{
<#if item.getAllPSSysBDTables()??>
<#list item.getAllPSSysBDTables() as batable>
		this.prepareBATable_${batable.getCodeName()}();
</#list>
</#if>
	}
	
<#if item.getAllPSSysBDTables()??>
<#list item.getAllPSSysBDTables() as batable>
     /**
      * 准备大数据表 ${batable.logicName}
      * @throws Exception
      */
	protected void prepareBATable_${batable.getCodeName()}() throws Exception
	{
		//注册表[${batable.logicName}]
		IBATable iBATable = this.createBATable("${batable.name}");
		if(iBATable == null){
			BATableModel baTableModel = new BATableModel();
			baTableModel.setId("${batable.id}");
			baTableModel.setName("${batable.name}");
            baTableModel.setBATableType(${batable.getBATableType()?c});
			baTableModel.init(this);
		//注册数据表实体
<#if batable.getAllPSSysBDTableDEs()??>
<#list batable.getAllPSSysBDTableDEs() as tablede>
			BATableDEModel baTableDEModel${tablede_index?c} = new BATableDEModel();
			baTableDEModel${tablede_index?c}.setId("${tablede.id}");
			baTableDEModel${tablede_index?c}.setName("${tablede.name}");
            baTableDEModel${tablede_index?c}.setBATableDEType(${tablede.getBATableDEType()?c});
            baTableDEModel${tablede_index?c}.setBAColSetName("${tablede.getBAColSetName()}");
            baTableDEModel${tablede_index?c}.setRowKeyFormat("${tablede.getRowKeyFormat()}");
            baTableDEModel${tablede_index?c}.setRowKeyParams("${tablede.getRowKeyParams()}");

			baTableDEModel${tablede_index?c}.init(baTableModel);
			baTableModel.registerBATableDE(baTableDEModel${tablede_index?c});
</#list>
</#if>
			//注册列族
<#if batable.getAllPSSysBDColSets()??>
<#list batable.getAllPSSysBDColSets() as colset>
			this.prepareBAColSet_${batable.getCodeName()}_${colset.name}(baTableModel);
</#list>
</#if>
				//注册数据表实体关系
<#if batable.getAllPSSysBDTableDERs()??>
<#list batable.getAllPSSysBDTableDERs() as tableder>
			BATableDERModel baTableDERModel${tableder_index?c} = new BATableDERModel();
			baTableDERModel${tableder_index?c}.setId("${tableder.id}");
			baTableDERModel${tableder_index?c}.setName("${tableder.name}");
			baTableDERModel${tableder_index?c}.setMajorDEName("${tableder.getMajorDEName()}");
			baTableDERModel${tableder_index?c}.setMinorDEName("${tableder.getMinorDEName()}");
			baTableDERModel${tableder_index?c}.setDERFieldName("${tableder.getDERFieldName()}");
			baTableDERModel${tableder_index?c}.init(baTableModel);
			baTableModel.registerBATableDER(baTableDERModel${tableder_index?c});
</#list>
</#if>
			iBATable = baTableModel;
		}
		this.registerBATable(iBATable);
	}
</#list>
</#if>	

<#if item.getAllPSSysBDTables()??>
<#list item.getAllPSSysBDTables() as batable>
<#if batable.getAllPSSysBDColSets()??>
<#list batable.getAllPSSysBDColSets() as colset>
     /**
      * 准备列族 ${colset.name}
      * @throws Exception
      */
	protected void prepareBAColSet_${batable.getCodeName()}_${colset.name}(IBATableModel baTableModel) throws Exception
	{
			BAColSetModel baColSetModel${colset_index?c} = new BAColSetModel();
			baColSetModel${colset_index?c}.setId("${colset.id}");
			baColSetModel${colset_index?c}.setName("${colset.name}");
			baColSetModel${colset_index?c}.init(baTableModel);
			baTableModel.registerBAColSet(baColSetModel${colset_index?c});
			//注册列
<#if colset.getAllPSSysBDColumns()??>
<#list colset.getAllPSSysBDColumns() as column>
			BAColumnModel baColumnModel${column_index?c} = new BAColumnModel();
			baColumnModel${column_index?c}.setId("${column.id}");
			baColumnModel${column_index?c}.setName("${column.name}");
			baColumnModel${column_index?c}.setBATableDEId("${column.getBATableDEId()}");
			baColumnModel${column_index?c}.setBAColSetName("${column.getBAColSetName()}");
			baColumnModel${column_index?c}.setDEName("${column.getDEName()}");
			baColumnModel${column_index?c}.setDEFieldName("${column.getDEFieldName()}");
			baColumnModel${column_index?c}.init(baTableModel);
			baTableModel.registerBAColumn(baColumnModel${column_index?c});
</#list>
</#if>
	}
</#list>
</#if>
</#list>
</#if>	

<#if item.getAllPSSysBDTables()??>
	@Override
	public IBAEntity createBAEntity(IBATable iBATable) throws Exception {
<#list item.getAllPSSysBDTables() as batable>
		if(StringHelper.compare(iBATable.getName(),"${batable.name}",true)==0){
			return new ${pub.getPKGCodeName()}.srv.ba.${item.getShortCodeName()?lower_case}.${batable.codeName}();
		}
</#list>
		return super.createBAEntity(iBATable);
	}
</#if> 
}