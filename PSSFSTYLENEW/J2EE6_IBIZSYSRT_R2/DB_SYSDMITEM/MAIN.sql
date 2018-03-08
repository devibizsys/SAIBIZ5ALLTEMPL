<#assign alltype='TABLE|COLUMN|FKEY|INDEX|VIEW'>
<#assign types=alltype?split('|')>
<#list types as type>
<#list sys.getAllPSSubSysRefs() as sysref>
<#if sysref.getPSSubSysVer()??>
<#list sysref.getPSSubSysVer().getAllPSSubSysDMItems() as dmitem>
<#if (dmitem.getPSSystemDBCfgId()==item.id) && (dmitem.getDBObjType() == type)>
<#if dmitem.getDropSql()?? && dmitem.getDropSql()?length gt 0>
${dmitem.getDropSql()};
</#if>
<#if dmitem.getBeforeCreateSql()?? && dmitem.getBeforeCreateSql()?length gt 0>
${dmitem.getBeforeCreateSql()};
</#if>
<#if dmitem.getCreateSql()?? && dmitem.getCreateSql()?length gt 0>
${dmitem.getCreateSql()};
</#if>
<#if dmitem.getAfterCreateSql()?? && dmitem.getAfterCreateSql()?length gt 0>
${dmitem.getAfterCreateSql()};
</#if>
</#if>
</#list>
</#if>
</#list>
<#list sys.getAllPSSysDMItems() as dmitem>
<#if (dmitem.getPSSystemDBCfgId()==item.id) && (dmitem.getDBObjType() == type)>
<#if dmitem.getDropSql()?? && dmitem.getDropSql()?length gt 0>
${dmitem.getDropSql()};
</#if>
<#if dmitem.getBeforeCreateSql()?? && dmitem.getBeforeCreateSql()?length gt 0>
${dmitem.getBeforeCreateSql()};
</#if>
<#if dmitem.getCreateSql()?? && dmitem.getCreateSql()?length gt 0>
${dmitem.getCreateSql()};
</#if>
<#if dmitem.getAfterCreateSql()?? && dmitem.getAfterCreateSql()?length gt 0>
${dmitem.getAfterCreateSql()};
</#if>
</#if>
</#list>
</#list>