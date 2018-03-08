{<#if ctrl.getPSDETreeNodes()??>contextmenu:{<#assign firstNode=1><#list ctrl.getPSDETreeNodes() as node>
<#if node.getPSDEContextMenu()??><#if firstNode==1><#assign firstNode=0><#else>,</#if><#assign cm=node.getPSDEContextMenu()>"${node.nodeType}":{<#if cm.getPSContextMenuItems()??><#assign firstItem=1>
<#list cm.getPSContextMenuItems() as item><#if firstItem==1><#assign firstItem=0><#else>,</#if>"_${item_index?c}":{label:<#if item.getCapPSLanguageRes()??>$IGM('${item.getCapPSLanguageRes().getLanResTag()}',"${item.caption}")<#else>"${item.caption}"</#if>,type:"${item.getItemType()}"<#if item.getItemType()=='DEUIACTION'&& item.getPSUIAction()??><#assign deuiaction=item.getPSUIAction()>,ua:{tag:"${deuiaction.getUIActionTag()}",target:"${deuiaction.getActionTarget()}"}</#if>}
</#list>
</#if>}</#if>
</#list>}
</#if>
}