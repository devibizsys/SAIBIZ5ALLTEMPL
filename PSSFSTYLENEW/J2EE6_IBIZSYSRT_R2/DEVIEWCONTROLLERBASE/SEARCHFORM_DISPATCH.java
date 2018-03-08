//处理搜索表单 ${item.name}
if(StringHelper.compare(strCtrlId,"${item.name}",true) == 0)
{
    if(StringHelper.compare(strAction,net.ibizsys.paas.control.form.ISearchForm.SearchAction,true) == 0)
    {
         return on${srfclassname('${item.name}')}SearchAction();
    }
    
}