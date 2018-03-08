import net.ibizsys.paas.web.GridRowAjaxActionResult;
import net.sf.json.JSONObject;
import net.ibizsys.paas.data.IDataObject;
import net.ibizsys.paas.entity.IEntity;

public class ${de.codeName}${ctrl.codeName}${srfclassname('${ctrl.getControlType()}')}${srfclassname('${item.codeName}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.GridEditItemUpdateHandlerBase{
   
    public ${de.codeName}${ctrl.codeName}${srfclassname('${ctrl.getControlType()}')}${srfclassname('${item.codeName}')}Handler()  {
        super();
        
    }
   
    /**
	 * 填充输出的数据
	 * @param iDataObject
	 * @param outputData
	 * @param outputState
	 * @param gridRowAjaxActionResult
	 * @throws Exception
	 */
        @Override
	protected void fillRowOutputDatas(IDataObject iDataObject,JSONObject outputData,JSONObject outputState, JSONObject outputConfig, GridRowAjaxActionResult gridRowAjaxActionResult) throws Exception
        {
<#if item.getPSDEGEIUpdateDetails()??>
<#list   item.getPSDEGEIUpdateDetails() as detail>
                gridRowAjaxActionResult.getData(true).put("${detail.getPSDEGridColumnName()?lower_case}",outputData.get("${detail.getPSDEGridColumnName()?lower_case}"));
                gridRowAjaxActionResult.getState(true).put("${detail.getPSDEGridColumnName()?lower_case}",outputState.get("${detail.getPSDEGridColumnName()?lower_case}"));
                if(outputConfig.opt("${detail.getPSDEGridColumnName()?lower_case}")!=null)
                    gridRowAjaxActionResult.getConfig(true).put("${detail.getPSDEGridColumnName()?lower_case}",outputConfig.get("${detail.getPSDEGridColumnName()?lower_case}"));
</#list>
</#if>
        }
	
	
	/**
	 * 执行行为
	 * @param iEntity
	 * @return
	 * @throws Exception
	 */
	protected  void executeAction(IEntity iEntity) throws Exception
        {
             this.getViewController().getService().executeAction("${item.getPSDEAction().name?upper_case}", iEntity);
        }


}