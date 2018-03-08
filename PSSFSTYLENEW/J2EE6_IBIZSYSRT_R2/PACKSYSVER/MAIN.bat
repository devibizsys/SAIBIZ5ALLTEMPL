<#assign outputlog= " > " + codefolder+"\\"+sys.getPSDevCenterDomain()+"\\"+sys.getPubSystemId()+"\\"+sys.getVCName()+"\\"+"srv_"+pub.codeName+"\\run.log">

<#assign workshop="workshop">
<#if sys.getPSSVNInstRepo()?? && sys.getPSSVNInstRepo().getSVNType() =="GIT">
<#assign workshop="gitshop">
</#if>

<#if sysrun.getRunMode()=='PACKVER'||sysrun.getRunMode()=='PACKVER2'>
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver

del ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\*.* /S/F/Q ${outputlog}
rd  ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()} /S/Q ${outputlog}

mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\webapp
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\webapp\${pub.codeName}
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\doc
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\db
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_pub
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_pub\src
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_pub\src\main
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_pub\src\main\java
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_usr
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_usr\src
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_usr\src\main
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_usr\src\main\java
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\${pub.codeName}
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\${pub.codeName}\src
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\${pub.codeName}\src\main
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\${pub.codeName}\src\main\webapp
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\sln_${pub.codeName}
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\sln_${pub.codeName}\web

<#if sysrun.getPSApplication()??>
<#assign sysapp=sysrun.getPSApplication()>
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub\src
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub\src\main
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub\src\main\webapp
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub\src\main\webapp\WEB-INF
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub\src\main\webapp\${sysapp.getAppFolder()?lower_case}
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_usr
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_usr\src
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_usr\src\main
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_usr\src\main\webapp
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_usr\src\main\webapp\WEB-INF
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_usr\src\main\webapp\${sysapp.getAppFolder()?lower_case}
</#if>

xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\DB\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\db /E/Y/D ${outputlog}
<#if sysrun.getRunMode()=='PACKVER'>
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\DOC\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\doc /E/Y/D ${outputlog}
</#if>
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\VER\readme.txt ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst  /Y ${outputlog}

rem srv_pub项目
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\PRJ\pom.xml ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_pub /I/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\PRJ\.ibizproject ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_pub /I/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_pub\src\main\java /I/E/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC2\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_pub\src\main\java /I/E/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\USERCODE\SRV_PUB\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_pub /I/E/Y ${outputlog}

rem srv_usr项目
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr\pom.xml ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_usr /I/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr\src\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\srv_${pub.codeName}_usr\src /I/E/Y ${outputlog}

rem web部署项目
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEBPRJ\pom.xml ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\${pub.codeName} /I/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\${pub.codeName}\src\main\webapp /I/E/Y/D ${outputlog}

rem sln项目
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\PACKSLNPRJ\pom.xml ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\sln_${pub.codeName} /I/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\sln_${pub.codeName}\web /I/E/Y/D ${outputlog}

<#if sysrun.getRunMode()=='PACKVER'>
xcopy ${toolfolder}\SRV\${pub.getSFStyle()}\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\webapp\${pub.codeName} /E/Y/D ${outputlog}
::xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\web_${pub.codeName}\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\webapp\${pub.codeName} /E/Y/D ${outputlog}
rem xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr\lib\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\webapp\${pub.codeName}\WEB-INF\lib /Y ${outputlog}
rem xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}\${pub.destFile} ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\webapp\${pub.codeName}\WEB-INF\lib /Y ${outputlog}
rem 服务端部署文件
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\web\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\webapp\${pub.codeName} /I/E/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName}\src\main\webapp\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\webapp\${pub.codeName} /I/E/Y ${outputlog}
robocopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}\target\install_${pub.codeName}-${pub.getSFPubVersion()}\WEB-INF\lib ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\webapp\${pub.codeName}\WEB-INF\lib /XO /PURGE ${outputlog} 

</#if>


<#if sysrun.getPSApplication()??>
<#assign sysapp=sysrun.getPSApplication()>
<#if sysrun.getRunMode()=='PACKVER'>
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\webapp\${pub.codeName}\${sysapp.getAppFolder()?lower_case}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\app_${sysapp.getPKGCodeName()}\build\*.*  ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\webapp\${pub.codeName}\${sysapp.getAppFolder()?lower_case}  /E/Y/D ${outputlog}
</#if>

rem app_pub项目
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\app_${sysapp.getPKGCodeName()}\PRJ\pom.xml ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub /I/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\app_${sysapp.getPKGCodeName()}\PRJ\.ibizproject ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub /I/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\app_${sysapp.getPKGCodeName()}\WEBCONF\web.xml ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub\src\main\webapp\WEB-INF /I/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\app_${sysapp.getPKGCodeName()}\APP\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub\src\main\webapp\${sysapp.getAppFolder()?lower_case} /I/E/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\app_${sysapp.getPKGCodeName()}\JS\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub\src\main\webapp\${sysapp.getAppFolder()?lower_case} /I/E/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\app_${sysapp.getPKGCodeName()}\JS2\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub\src\main\webapp\${sysapp.getAppFolder()?lower_case} /I/E/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\app_${sysapp.getPKGCodeName()}\WEB\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub\src\main\webapp\${sysapp.getAppFolder()?lower_case} /I/E/Y/D ${outputlog}
xcopy ${toolfolder}\APP\${sysapp.getPFType()}-5.0.0.0\APP\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_pub\src\main\webapp\${sysapp.getAppFolder()?lower_case} /I/E/Y/D ${outputlog}

rem app_usr项目
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\app_${sysapp.getPKGCodeName()}\USRPRJ\pom.xml ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_usr\ /I/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\app_${sysapp.getPKGCodeName()}\WEBCONF2\web.xml ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_usr\src\main\webapp\WEB-INF /I/Y/D ${outputlog}
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\app_${sysapp.getPKGCodeName()}_usr\src\main\webapp\${sysapp.getAppFolder()?lower_case}\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst\src\app_${sysapp.getPKGCodeName()}_usr\src\main\webapp\${sysapp.getAppFolder()?lower_case}  /E/Y ${outputlog}

</#if>
${toolfolder}\7-Zip\7z.exe a -aoa "${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst.zip" "${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst" ${outputlog}
<#if sysrun.getRunParam3()?length gt 0>
copy "${codefolder}\${sys.getPSDevCenterDomain()}\_ver\${sysrun.getRunParam()}\inst.zip" "${sysrun.getRunParam3()}"
"${toolfolder}\java\bin\java.exe"  -cp  ${toolfolder}\js\orion-ssh2-214.jar;${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SSHCmd2  "${sysrun.getRunParam4()}" "${sysrun.getRunParam3()}"
</#if>
</#if>