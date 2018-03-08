//处理表格 ${item.name}
if(StringHelper.compare(strCtrlId,"${item.name}",true) == 0)
{
    if(StringHelper.compare(strAction,net.ibizsys.paas.control.grid.IGrid.FetchAction,true) == 0)
    {
         return on${srfclassname('${item.name}')}FetchAction();
     }
}