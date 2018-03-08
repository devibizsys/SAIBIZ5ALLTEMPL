<#assign outputlog= " > " + codefolder+"\\"+sys.getPSDevCenterDomain()+"\\"+sys.getPubSystemId()+"\\"+sys.getVCName()+"\\"+"srv_"+pub.codeName+"\\run.log">

<#assign workshop="workshop">
<#if sys.getPSSVNInstRepo()?? && sys.getPSSVNInstRepo().getSVNType() =="GIT">
<#assign workshop="gitshop">
</#if>

<#if sysrun.getPSAppServer()??>
<#if sysrun.getPSAppServer().isRemoteDeploy()>
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_as
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\ascache2
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName}
attrib -A ${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName}\*.* /S

rem xcopy ${toolfolder}\SRV\${pub.getSFStyle()}\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName} /I/E/Y/D ${outputlog}

attrib +A ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\web\*.*  /S
attrib +A  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}\target\install_${pub.codeName}-${pub.getSFPubVersion()}\WEB-INF\lib\*.* /S

xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\web\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName} /I/E/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName}\src\main\webapp\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName} /I/E/Y ${outputlog}
REM 同步lib目录
robocopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}\target\install_${pub.codeName}-${pub.getSFPubVersion()}\WEB-INF\lib ${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName}\WEB-INF\lib /XO /PURGE ${outputlog} 


<#if sysrun.getPSApplication()??>
<#assign sysapp=sysrun.getPSApplication()>
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName}\${sysapp.getAppFolder()?lower_case}
attrib +A  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\app_${sysapp.getPKGCodeName()}\build\*.* /S
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\app_${sysapp.getPKGCodeName()}\build\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName}\${sysapp.getAppFolder()?lower_case}  /I/E/Y/D ${outputlog}
</#if>

del ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\ascache2\${pub.codeName}.zip /S/F/Q ${outputlog}
del ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\ascache2\${pub.codeName} /S/F/Q

xcopy "${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName}\*.*" "${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\ascache2\${pub.codeName}" /A/I/E/Y
${toolfolder}\7-Zip\7z.exe a "${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\ascache2\${pub.codeName}.zip" "${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\ascache2\${pub.codeName}\*" -r

<#if sysrun.getPSDBDevInst()??>
<#assign devinst=sysrun.getPSDBDevInst()>
<#if devinst.getResPos()==2>
rem 自建数据库，需要远程导入数据结构
rem ${devinst.getResCfgFilePath()}
<#if devinst.getDBClientPath()?length gt 2>
<#assign sqlcmd=devinst.getDBClientPath()>
<#else>
<#assign sqlcmd="/usr/bin/">
</#if>
"${toolfolder}\java\bin\java.exe"  -cp  ${toolfolder}\js\orion-ssh2-214.jar;${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.MySqlCmd ${devinst.getResCfgFilePath()} ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\DB\mysql5.sql ${outputlog}
<#else>
rem "${toolfolder}\java\bin\java.exe"  -cp  ${toolfolder}\js\orion-ssh2-214.jar;${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.MySqlCmd 172.16.100.139 22 root Sa51rich SFTP root sa51rich E:/code/lab_ibiz5/AB83EF8F-2AC7-4FCC-B86F-8DAF6CF52D9C/trunk/srv_Lab9/DB/mysql5.sql lab9 /usr/bin ${outputlog}
</#if>
</#if>

<#if sysrun.isRebuildMode()>
"${toolfolder}\java\bin\java.exe"  -cp  ${toolfolder}\js\orion-ssh2-214.jar;${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SSHCmd3 ${sysrun.getPSAppServer().getResCfgFilePath()} "${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\ascache2\${pub.codeName}.zip" "${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName}" REBUILD ${outputlog}
<#else>
"${toolfolder}\java\bin\java.exe"  -cp  ${toolfolder}\js\orion-ssh2-214.jar;${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SSHCmd3 ${sysrun.getPSAppServer().getResCfgFilePath()} "${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\ascache2\${pub.codeName}.zip" "${codefolder}\${sys.getPSDevCenterDomain()}\_as\${sysrun.getPSAppServer().id}\${pub.codeName}" NO ${outputlog}
</#if>
<#else>

mkdir ${sysrun.getPSAppServer().getAppFolder()}\${pub.codeName}
xcopy ${toolfolder}\SRV\${pub.getSFStyle()}\*.* ${sysrun.getPSAppServer().getAppFolder()}\${pub.codeName} /I/E/Y/D ${outputlog}

xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\web_${pub.codeName}\*.* ${sysrun.getPSAppServer().getAppFolder()}\${pub.codeName} /I/E/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr\lib\*.* ${sysrun.getPSAppServer().getAppFolder()}\${pub.codeName}\WEB-INF\lib\ /Y ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}\${pub.destFile} ${sysrun.getPSAppServer().getAppFolder()}\${pub.codeName}\WEB-INF\lib\ /Y ${outputlog}

<#if sysrun.getPSApplication()??>
<#assign sysapp=sysrun.getPSApplication()>
mkdir ${sysrun.getPSAppServer().getAppFolder()}\${pub.codeName}\${sysapp.getAppFolder()?lower_case}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\app_${sysapp.getPKGCodeName()}\build\*.* ${sysrun.getPSAppServer().getAppFolder()}\${pub.codeName}\${sysapp.getAppFolder()?lower_case}  /I/E/Y/D ${outputlog}
"${toolfolder}\java\bin\java.exe"  -cp  ${toolfolder}\js\orion-ssh2-214.jar;${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SSHCmd2  "${sysrun.getRunParam4()}" "${sysrun.getRunParam3()}"
</#if>

</#if>
</#if>