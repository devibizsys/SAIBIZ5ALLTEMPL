${delogicnodeheader.code}
<#if item.getDstPSDataEntity()??>
<#assign dstde=item.getDstPSDataEntity()>

<#assign dstaction=item.getDstPSDEAction()>
<#assign dstparam=item.getDstPSDELogicParam()>
     IService service = ServiceGlobal.getService(${dstde.getClassOrPkgName('SERVICE',pub)}.class,sessionFactory );
     service.executeAction(${dstde.getClassOrPkgName('SERVICE',pub)}.ACTION_${dstaction.name?upper_case},${srfparamname('${dstparam.codeName}')});
</#if>

${delogicnodebottom.code}