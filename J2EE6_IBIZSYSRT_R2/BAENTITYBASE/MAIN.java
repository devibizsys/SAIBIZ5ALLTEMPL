package ${pub.getPKGCodeName()}.srv.ba.${item.getPSSysBDScheme().getShortCodeName()?lower_case};

import net.ibizsys.paas.entity.IEntity;
import net.ibizsys.psba.core.IBATableModel;
import net.ibizsys.psba.entity.IBAEntity;
import java.util.ArrayList;

/**
 * 大数据表[${item.name}]数据对象基类
 */
public class ${item.codeName} extends net.ibizsys.psba.entity.BAEntity {

   private static final long serialVersionUID = -1L;

   public ${item.codeName}(){
        super();
       
    }

//关系数据获取
<#if item.getAllPSSysBDTableRSes(true)??>
<#list item.getAllPSSysBDTableRSes(true) as bdTableRS>
<#assign bdTable = bdTableRS.getMinorPSSysBDTable()>
    /**
     * 获取${bdTable.getLogicName()}
     * @return
     * @throws Exception
     */
    public ArrayList<IBAEntity> get${bdTable.getCodeName()}s() throws Exception{
    	return this.children("${bdTable.name?upper_case}", null);
    }
</#list>
</#if>
}