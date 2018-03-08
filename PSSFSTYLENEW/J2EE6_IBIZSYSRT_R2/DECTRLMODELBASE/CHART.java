import net.ibizsys.paas.ctrlmodel.ChartDataItemModel;
import net.ibizsys.paas.ctrlmodel.ChartAxisModel;
import net.ibizsys.paas.ctrlmodel.ChartSeriesModel;

/**
 * 实体[${de.logicName}]图表[${item.name}]部件模型
 */
public class  ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model()  {
        super();
        
     }


<#if item.getDataEntity()??>
<#assign de=item.getDataEntity()>
      private ${de.getClassOrPkgName('DEMODEL',pub)} ${srfparamname('${de.codeName}')}DEModel;
     protected  ${de.getClassOrPkgName('DEMODEL',pub)} get${de.codeName}DEModel() {
            if(this.${srfparamname('${de.codeName}')}DEModel==null){
                try
                {
                     this.${srfparamname('${de.codeName}')}DEModel = (${de.getClassOrPkgName('DEMODEL',pub)})DEModelGlobal.getDEModel("${de.getClassOrPkgName('DEMODEL',pub)}");
                }
                catch(Exception ex)
                {
                }
            }
           return this.${srfparamname('${de.codeName}')}DEModel;
      }

      @Override
      public IDataEntityModel getDEModel() {
          return this.get${de.codeName}DEModel();
      }

</#if>


      /**
      * 准备图表数据项模型
      * @throws Exception
      */
    @Override
     protected void prepareChartDataItemModels()throws Exception
     {
    	 super.prepareChartDataItemModels();
<#if item.getChartDataItems()??>
<#list item.getChartDataItems() as chartDataItem>
         // ${chartDataItem.name} 
         ChartDataItemModel chartDataItem${chartDataItem_index?c} = new ChartDataItemModel();
<#if chartDataItem.name??>
         chartDataItem${chartDataItem_index?c}.setName("${chartDataItem.name}");
</#if>
         chartDataItem${chartDataItem_index?c}.setDataType(${chartDataItem.getDataType()});
<#if chartDataItem.format??>
         chartDataItem${chartDataItem_index?c}.setFormat("${srfxmlvalue('${chartDataItem.format}')}");
</#if>
         chartDataItem${chartDataItem_index?c}.init(this);
         this.registerChartDataItem(chartDataItem${chartDataItem_index?c});
</#list>
</#if>	
     }
     
     
    /**
    * 准备图表坐标轴模型
    * @throws Exception
    */
    @Override
   protected void prepareChartAxisModels()throws Exception
   {
  	 super.prepareChartAxisModels();
<#if item.getPSDEChartAxeses()??>
<#list item.getPSDEChartAxeses() as chartAxis>
       // ${chartAxis.name} 
       ChartAxisModel chartAxis${chartAxis_index?c} = new ChartAxisModel();
<#if chartAxis.name??>
       chartAxis${chartAxis_index?c}.setName("${chartAxis.name}");
</#if>
<#if chartAxis.getCaption()??>
		chartAxis${chartAxis_index?c}.setCaption("${chartAxis.getCaption()}");
</#if>
<#if chartAxis.getAxesType()??>
       chartAxis${chartAxis_index?c}.setAxisType("${chartAxis.getAxesType()}");
</#if>
<#if chartAxis.getAxesPos()??>
	   chartAxis${chartAxis_index?c}.setAxisPos("${chartAxis.getAxesPos()}");
</#if>
       chartAxis${chartAxis_index?c}.init(this);
       this.registerChartAxisModel(chartAxis${chartAxis_index?c});
</#list>
</#if>	
   }
     
    /**
    * 准备图表序列模型
    * @throws Exception
    */
    @Override
    protected void prepareChartSeriesModels()throws Exception
    {
    	super.prepareChartSeriesModels();
<#if item.getPSDEChartSerieses()??>
<#list item.getPSDEChartSerieses() as series>
       // ${series.name} 
       	ChartSeriesModel chartSeries${series_index?c} = new ChartSeriesModel();
<#if series.name??>
       	chartSeries${series_index?c}.setName("${series.name}");
</#if>
<#if series.getCaption()??>
		chartSeries${series_index?c}.setCaption("${series.getCaption()}");
</#if>
<#if series.getSeriesType()??>
       	chartSeries${series_index?c}.setSeriesType("${series.getSeriesType()}");
</#if>
<#if series.getSeriesField()??>
		chartSeries${series_index?c}.setSeriesField("${series.getSeriesField()}");
</#if>
<#if series.getCatalogField()??>
	   	chartSeries${series_index?c}.setCatalogField("${series.getCatalogField()}");
</#if>
<#if series.getValueField()??>
		chartSeries${series_index?c}.setValueField("${series.getValueField()}");
</#if>
<#if series.getValue2Field()??>
		chartSeries${series_index?c}.setValue2Field("${series.getValue2Field()}");
</#if>
<#if series.getTimeGroupMode()??>
		chartSeries${series_index?c}.setTimeGroupMode("${series.getTimeGroupMode()}");
</#if>
       chartSeries${series_index?c}.init(this);
       this.registerChartSeriesModel(chartSeries${series_index?c});
</#list>
</#if>	
    }
 
}