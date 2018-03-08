//处理关系数据栏 ${item.name}
if(StringHelper.compare(strCtrlId,"${item.name}",true) == 0)
{
    if(StringHelper.compare(strAction,net.ibizsys.paas.ctrlhandler.IDRBarHandler.ACTION_FETCH,true) == 0)
    {
         return on${srfclassname('${item.name}')}FetchAction();
     }
}