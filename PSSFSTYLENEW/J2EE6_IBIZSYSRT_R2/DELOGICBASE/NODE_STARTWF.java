${delogicnodeheader.code}

<#assign dstde=item.getPSDEWF().getPSDataEntity()>
<#assign dstparam=item.getDstPSDELogicParam()>
    String strKeyValue = DataObject.getStringValue(${srfparamname('${dstparam.codeName}')}.get("${dstde.getKeyPSDEField().name}"));
    //获取流程 "${item.getPSWorkflow().name}"
    IWFModel iWFModel = this.getSystemModel().getWFModel("${item.getPSWorkflow().id}");
   IWFService iWFService = iWFModel.getWFService();
   WFActionParam wfActionParam = new WFActionParam();
   wfActionParam.setUserData(strKeyValue );
   //设置流程实体[${dstde.name}]标示
   wfActionParam.setUserData4("${dstde.id}");
   wfActionParam.setOpPersonId(iActionContext.getOperator());
   iWFService.start(wfActionParam);

${delogicnodebottom.code}