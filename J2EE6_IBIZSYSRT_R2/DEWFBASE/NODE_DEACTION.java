${delogicnodeheader.code}
<#if item.getDstPSDataEntity()??>
<#assign dstde=item.getDstPSDataEntity()>
<#assign dstaction=item.getDstPSDEAction()>
<#assign dstparam=item.getDstLogicParam()>
     ${dstde.codeName}Service service = (${dstde.codeName}Service)ServiceGlobal.getService(${dstde.codeName}Service.class);
     service.${srfmethodname('${dstaction.codeName}')}(${srfparamname('${dstparam.codeName}')});
</#if>

${delogicnodebottom.code}