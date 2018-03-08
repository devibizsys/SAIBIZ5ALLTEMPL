//处理表单 ${item.name}
if(StringHelper.compare(strCtrlId,"${item.name}",true) == 0)
{
    if(StringHelper.compare(strAction,net.ibizsys.paas.control.form.IEditForm.LoadAction,true) == 0)
    {
         return on${srfclassname('${item.name}')}LoadAction();
    }

    if(StringHelper.compare(strAction,net.ibizsys.paas.control.form.IEditForm.CreateAction,true) == 0)
    {
         return on${srfclassname('${item.name}')}CreateAction();
    }

    if(StringHelper.compare(strAction,net.ibizsys.paas.control.form.IEditForm.UpdateAction,true) == 0)
    {
         return on${srfclassname('${item.name}')}UpdateAction();
    }
}