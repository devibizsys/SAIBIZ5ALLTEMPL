import net.ibizsys.paas.web.FormAjaxActionResult;
import net.sf.json.JSONObject;
import net.ibizsys.paas.data.IDataObject;
import net.ibizsys.paas.entity.IEntity;

public class ${de.codeName}${ctrl.codeName}${srfclassname('${ctrl.getControlType()}')}${srfclassname('${item.codeName}')}Handler extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlhandler.FormItemUpdateHandlerBase{
   
    public ${de.codeName}${ctrl.codeName}${srfclassname('${ctrl.getControlType()}')}${srfclassname('${item.codeName}')}Handler()  {
        super();
        
    }
   
    	/**
	 * 填充输出的数据
	 * @param iDataObject
	 * @param outputData
	 * @param outputState
	 * @param formAjaxActionResult
	 * @throws Exception
	 */
        @Override
	protected void fillOutputDatas(IDataObject iDataObject,JSONObject outputData,JSONObject outputState, JSONObject outputConfig, FormAjaxActionResult formAjaxActionResult) throws Exception
        {
<#if item.getPSDEFIUpdateDetails()??>
<#list   item.getPSDEFIUpdateDetails() as detail>
                formAjaxActionResult.getData(true).put("${detail.getPSDEFormDetailName()?lower_case}",outputData.get("${detail.getPSDEFormDetailName()?lower_case}"));
                formAjaxActionResult.getState(true).put("${detail.getPSDEFormDetailName()?lower_case}",outputState.get("${detail.getPSDEFormDetailName()?lower_case}"));
                if(outputConfig.opt("${detail.getPSDEFormDetailName()?lower_case}")!=null)
                    formAjaxActionResult.getConfig(true).put("${detail.getPSDEFormDetailName()?lower_case}",outputConfig.get("${detail.getPSDEFormDetailName()?lower_case}"));
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