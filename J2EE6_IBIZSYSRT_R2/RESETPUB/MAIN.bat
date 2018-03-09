<#comment>脚本执行日志路径。</#comment>
<#assign outputlog= " > " + codefolder+"\\"+sys.getPSDevCenterDomain()+"\\"+sys.getPubSystemId()+"\\"+sys.getVCName()+"\\"+"srv_"+pub.codeName+"\\run.log">

<#assign workshop="workshop">
<#if sys.getPSSVNInstRepo()?? && sys.getPSSVNInstRepo().getSVNType() =="GIT">
<#assign workshop="gitshop">
</#if>

<#assign usrPrjNames = ''>
<#assign pubPrjNames = ''>
<#list sfstyle.getPSSFStylePrjs() as usrprj>
<#if usrprj.isReadOnlyMode()>
<#assign pubPrjNames += usrprj.getProjectName(pub)+';'>
<#else>
<#assign usrPrjNames += usrprj.getProjectName(pub)+';'>
</#if>
</#list>

<#if sysrun.getPSApplication().getPSPFStyle()??>
<#assign app=sysrun.getPSApplication().getPSPFStyle()>
<#list app.getPSPFStylePrjs() as pubprj>
<#if pubprj.isReadOnlyMode()>
<#assign pubPrjNames += pubprj.getProjectName(sysrun.getPSApplication())+';'>
<#else>
<#assign usrPrjNames += pubprj.getProjectName(sysrun.getPSApplication())+';'>
</#if>
</#list>
</#if>
<#assign allPrj = (pubPrjNames+'-'+usrPrjNames)>
<#assign projectNames=allPrj?substring(0,(allPrj?length)-1)>

<#if sysrun.isRebuildMode()>

rem 删除pub发布文件
del ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC\*.* /S/F/Q ${outputlog}
rd  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC /S/Q ${outputlog}
rem 删除usr发布文件
del ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC2\*.* /S/F/Q ${outputlog}
rd  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC2 /S/Q ${outputlog}
rem 删除web发布文件
del ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB\*.* /S/F/Q ${outputlog}
rd  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB /S/Q ${outputlog}
rem 删除用户自定义发布文件
del ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\USERCODE\*.* /S/F/Q ${outputlog}
rd  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\USERCODE /S/Q ${outputlog}
rem 删除maven编译安装文件
del ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}\target\install_${pub.codeName}-${pub.getSFPubVersion()}\WEB-INF\lib\*.* /S/F/Q ${outputlog}

<#if sysrun.getRebuildModeEx()==2>
rem 完整重构

<#if sysrun.isEnableVC() && (sys.getPSSVNInstRepo().getSVNType()=="GIT")>
"${toolfolder}\jdk1.8\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.GitHelper ${sys.getPSSVNInstRepo().getPSGitUser().getResCfgFilePath()} ${sys.getPSSVNInstRepo().getGitPath()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName} REMOVE ${projectNames}
<#elseif sysrun.isEnableVC() && (sys.getPSSVNInstRepo().getSVNType()=="SVN")>
"${toolfolder}\java\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SvnCmd3 ${sys.getReadOnlyPSSVNInstRepo().getConnStr()}/${sys.getPSDevSlnCodeName()}/${sys.getTrunkSysName()}/${sys.getVCName()}   ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub REMOVE
"${toolfolder}\java\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SvnCmd3 ${sys.getReadOnlyPSSVNInstRepo().getConnStr()}\${sys.getPSDevSlnCodeName()}\${sys.getTrunkSysName()}\${sys.getVCName()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName} REMOVE
</#if>

rem 删除合成项目
move /Y ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName} ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${sysrun.realRSId}
del ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${sysrun.realRSId}\*.* /S/F/Q ${outputlog}
rd  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${sysrun.realRSId} /S/Q ${outputlog}

rem 删除pub项目
move /Y ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${sysrun.realRSId}
del ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${sysrun.realRSId}\*.* /S/F/Q ${outputlog}
rd  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${sysrun.realRSId} /S/Q ${outputlog}
rem 删除解决方案
move /Y ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName} ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln2_${sysrun.realRSId}
del ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln2_${sysrun.realRSId}\*.* /S/F/Q ${outputlog}
rd  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln2_${sysrun.realRSId} /S/Q ${outputlog}

</#if>

<#if sysrun.getPSAppServer()??>
rem 一键启动重构，删除缓存目录数据
<#if sysrun.getPSAppServer().isRemoteDeploy()>
del ${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName}\*.* /S/F/Q  ${outputlog}
rd  ${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName} /S/Q ${outputlog}
<#else>
del ${sysrun.getPSAppServer().getAppFolder()}\${pub.codeName}\*.* /S/F/Q ${outputlog}
rd  ${sysrun.getPSAppServer().getAppFolder()}\${pub.codeName} /S/Q ${outputlog}
</#if>
</#if>

</#if>