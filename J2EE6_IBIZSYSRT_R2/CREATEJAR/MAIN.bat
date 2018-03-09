<#comment>脚本执行日志路径。</#comment>
<#assign outputlog= " > " + codefolder+"\\"+sys.getPSDevCenterDomain()+"\\"+sys.getPubSystemId()+"\\"+sys.getVCName()+"\\"+"srv_"+pub.codeName+"\\">

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

<#comment>关闭回显并开启变量延迟。</#comment>
@echo off&setlocal ENABLEDELAYEDEXPANSION

rem ---------------------------------------------------------------------------
rem   publisher		后台服务发布器PSJQAppCreateAppBatPublisherImpl 
rem   sys			后台服务系统
rem   pub			后台服务发布对象
rem   sf			后台服务技术体系
rem   sfstyle		后台服务样式
rem   sfcodetype	后台服务代码类型
rem   sysrun		系统运行
rem   toolfolder	发布工具目录路径
rem   codefolder	发布代码根目录路径 
rem   srfibiz5locale	iBiz5语言配置映射
rem   srfibiz5msg	iBiz5语言内容 处理对象
rem ---------------------------------------------------------------------------

rem 建立系统相关工作空间目录。
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}
<#if sysrun.isEnableVC() && (sys.getPSSVNInstRepo().getSVNType()=="GIT")>
"${toolfolder}\jdk1.8\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.GitHelper ${sys.getPSSVNInstRepo().getPSGitUser().getResCfgFilePath()} ${sys.getPSSVNInstRepo().getGitPath()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName} WEB_PUB ${projectNames} init
</#if>
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\web
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}\bin
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}\src
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_tmp
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_tmp\src
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\src\main\java
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\src\main\resources
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\src\test\java
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\src\test\resources
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\target
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr\src\main\java
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr\src\main\resources
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr\src\test\java
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr\src\test\resources
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr\target
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName}
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName}\src\main\java
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName}\src\main\resources
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName}\src\test\java
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName}\src\test\resources
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName}\src\main\webapp
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}\src\main\java
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}\src\main\resources
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}\src\test\java
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}\src\test\resources
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}\src\main\webapp

rem 为发布代码添加归档标记，便于后续文件的判别处理，如增量更新、文件增量修改的版本库提交等。
attrib +A ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC\*.*  /S
attrib +A ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC2\*.*  /S
attrib +A ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\USERCODE\SRV_PUB\*.*  /S
<#if sysrun.isRebuildMode()>
rem 重构模式时，系统配置文件也添加归档标记
attrib +A ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB\*.*  /S
</#if>

rem 工作空间合并项目文件取消归档标记
attrib -A ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}\src\*.*  /S
rem 拷贝发布代码到工作空间合并项目
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}\src /I/E/Y/D ${outputlog}run1.log
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC2\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}\src /I/E/Y/D ${outputlog}run2.log

rem 实现合成项目中文件（大小写差异）改名功能。实现原理：需要该名称的文件会追加.rename后缀，第一个for查询改名的文件并删除，第二个for查处对应的文件去掉.rename后缀。
for /f "delims=" %%i in ('dir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}\src\*.rename /b /s') do set info=%%i&del !info:~0,-7! /F/Q
for /f "delims=" %%i in ('dir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}\src\*.rename /b /s') do set info=%%i&move /Y !info! !info:~0,-7!

rem 实现生成代码的格式话显示
del ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_tmp\src\*.* /S/F/Q ${outputlog}run2.log
rd  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_tmp\src /S/Q ${outputlog}run3.log
mkdir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_tmp\src 
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}\src\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_tmp\src /I/E/Y/A ${outputlog}run4.log
"${toolfolder}\AStyle\AStyle.exe" --style=java -n -q  --recursive ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_tmp\src\*.java ${outputlog}run5.log
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_tmp\src\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}\src /I/E/Y ${outputlog}run6.log

<#comment>
rem 拷贝项目配置文件到解决方案
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\web /I/E/Y/D ${outputlog}run6.log
rem 运行项目（文件存在及忽略拷贝）
robocopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB\ ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName}\src\main\webapp /XN /XO /E ${outputlog}run7.log

rem 实现解决方案中文件（大小写差异）改名功能。实现原理同上。
for /f "delims=" %%i in ('dir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\web\*.rename /b /s') do set info=%%i&del !info:~0,-7! /F/Q
for /f "delims=" %%i in ('dir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\web\*.rename /b /s') do set info=%%i&move /Y !info! !info:~0,-7!
</#comment>

<#if sysrun.isRebuildMode()>
rem 重构模式

rem pub项目取消归档标记
attrib -A ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\src\*.*  /S
rem pub、sln、usr项目文件添加归档标记
attrib +A ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\PRJ\*.* /S
attrib +A ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SLNPRJ\*.* /S
attrib +A ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\USRPRJ\*.* /S
attrib +A ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_tmp\src\*.* /S

rem 拷贝初始文件到各项目
xcopy ${toolfolder}\J2EE\J2EE6_IBIZSYSRT_R2\WEBAPP\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName} /I/E/Y ${outputlog}run8.log
xcopy ${toolfolder}\J2EE\J2EE6_IBIZSYSRT_R2\WEBAPP\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName} /I/E/Y ${outputlog}run9.log
xcopy ${toolfolder}\J2EE\J2EE6_IBIZSYSRT_R2\SRV\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub /I/E/Y ${outputlog}run10.log
rem 用户项目（文件存在及忽略拷贝）
robocopy ${toolfolder}\J2EE\J2EE6_IBIZSYSRT_R2\SRV\ ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr /XN /XO /E ${outputlog}run11.log

rem 拷贝pub发布代码
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\src\main\java /I/E/Y ${outputlog}run12.log
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC2\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\src\main\java /I/E/Y ${outputlog}run13.log
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\PRJ\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub /I/E/Y ${outputlog}run14.log
rem 拷贝usr发布代码（文件存在及忽略拷贝）
robocopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\USRPRJ\ ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr /XN /XO /E ${outputlog}run15.log
rem 拷贝解决方案、运行项目、安装项目发布代码
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SLNPRJ\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName} /I/E/Y ${outputlog}run16.log
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEBPRJ\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName} /I/E/Y/D ${outputlog}run17.log
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\INSTALLPRJ\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName} /I/E/Y ${outputlog}run18.log

rem 拷贝格式化后的发布代码到pub
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_tmp\src\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\src\main\java /I/E/Y ${outputlog}run19.log

rem 实现pub项目中文件（大小写差异）改名功能。实现原理同上。
for /f "delims=" %%i in ('dir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\*.rename /b /s') do set info=%%i&del !info:~0,-7! /F/Q
for /f "delims=" %%i in ('dir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\*.rename /b /s') do set info=%%i&move /Y !info! !info:~0,-7!

rem 拷贝用户自定义文件到pub
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\USERCODE\SRV_PUB\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub /I/E/Y ${outputlog}run20.log

<#if sysrun.isEnableVC() && (sys.getPSSVNInstRepo().getSVNType()=="GIT")>
rem pub、usr项目与版本库同步
"${toolfolder}\jdk1.8\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.GitHelper ${sys.getPSSVNInstRepo().getPSGitUser().getResCfgFilePath()} ${sys.getPSSVNInstRepo().getGitPath()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub SRV_PUB <#if sysrun.getRebuildModeEx()!=2>REBUILD</#if>
"${toolfolder}\jdk1.8\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.GitHelper ${sys.getPSSVNInstRepo().getPSGitUser().getResCfgFilePath()} ${sys.getPSSVNInstRepo().getGitPath()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr SRV_USR
<#elseif sysrun.isEnableVC() && (sys.getPSSVNInstRepo().getSVNType()=="SVN")>
"${toolfolder}\java\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SvnCmd3 ${sys.getReadOnlyPSSVNInstRepo().getConnStr()}/${sys.getPSDevSlnCodeName()}/${sys.getTrunkSysName()}/${sys.getVCName()} ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub SRV_PUB <#if sysrun.getRebuildModeEx()!=2>REBUILD</#if>
"${toolfolder}\java\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SvnCmd3 ${sys.getPSSVNInstRepo().getConnStr()}/${sys.getPSDevSlnCodeName()}/${sys.getTrunkSysName()}/${sys.getVCName()} ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr SRV_USR
</#if>

rem 拷贝web文件到解决方案、运行项目、安装项目
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\web /I/E/Y ${outputlog}run21.log
robocopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName}\src\main\webapp /XN /XO /E /XD ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB\WEB-INF\classes\messages ${outputlog}run22.log
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}\src\main\webapp /I/E/Y ${outputlog}run23.log

rem 实现解决方案中文件（大小写差异）改名功能。实现原理同上。
for /f "delims=" %%i in ('dir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\*.rename /b /s') do set info=%%i&del !info:~0,-7! /F/Q
for /f "delims=" %%i in ('dir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\*.rename /b /s') do set info=%%i&move /Y !info! !info:~0,-7!

<#if sysrun.isEnableVC() && (sys.getPSSVNInstRepo().getSVNType()=="GIT")>
rem 解决方案、运行项目与版本库同步
"${toolfolder}\jdk1.8\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.GitHelper ${sys.getPSSVNInstRepo().getPSGitUser().getResCfgFilePath()} ${sys.getPSSVNInstRepo().getGitPath()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName} WEB_PUB <#if sysrun.getRebuildModeEx()!=2>REBUILD</#if>
"${toolfolder}\jdk1.8\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.GitHelper ${sys.getPSSVNInstRepo().getPSGitUser().getResCfgFilePath()} ${sys.getPSSVNInstRepo().getGitPath()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName} WEB_USR
<#elseif sysrun.isEnableVC() && (sys.getPSSVNInstRepo().getSVNType()=="SVN")>
"${toolfolder}\java\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SvnCmd3 ${sys.getReadOnlyPSSVNInstRepo().getConnStr()}/${sys.getPSDevSlnCodeName()}/${sys.getTrunkSysName()}/${sys.getVCName()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName} WEB_PUB <#if sysrun.getRebuildModeEx()!=2>REBUILD</#if>
"${toolfolder}\java\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SvnCmd3 ${sys.getPSSVNInstRepo().getConnStr()}/${sys.getPSDevSlnCodeName()}/${sys.getTrunkSysName()}/${sys.getVCName()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName} WEB_USR
</#if>

<#else>
rem 标准发布

rem 拷贝初始文件到各项目
xcopy ${toolfolder}\J2EE\J2EE6_IBIZSYSRT_R2\WEBAPP\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName} /I/E/Y/D ${outputlog}run24.log
xcopy ${toolfolder}\J2EE\J2EE6_IBIZSYSRT_R2\WEBAPP\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName} /I/E/Y/D ${outputlog}run25.log
xcopy ${toolfolder}\J2EE\J2EE6_IBIZSYSRT_R2\SRV\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub /I/E/Y/D ${outputlog}run26.log
rem 用户项目（文件存在及忽略拷贝）
robocopy ${toolfolder}\J2EE\J2EE6_IBIZSYSRT_R2\SRV\ ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr /XN /XO /E ${outputlog}run27.log

rem 拷贝发布文件到pub项目
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\src\main\java /I/E/Y/D ${outputlog}run28.log
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SRC2\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\src\main\java /I/E/Y/D ${outputlog}run29.log
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\PRJ\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub /I/E/Y/D ${outputlog}run30.log
rem 拷贝usr发布代码（文件存在及忽略拷贝）
robocopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\USRPRJ\ ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr /XN /XO /E ${outputlog}run31.log
rem 拷贝发布文件到解决方案
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\SLNPRJ\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName} /I/E/Y/D ${outputlog}run32.log
rem 拷贝发布文件到运行项目、安装项目
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEBPRJ\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName} /I/E/Y/D ${outputlog}run33.log
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\INSTALLPRJ\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName} /I/E/Y/D ${outputlog}run34.log
rem 拷贝格式化文件到pub（问题：删除pub改名文件操作之前，前面生成代码不需要拷贝）
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_tmp\src\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\src\main\java /I/E/Y ${outputlog}run35.log

rem 实现pub项目中文件（大小写差异）改名功能。实现原理同上。
for /f "delims=" %%i in ('dir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\*.rename /b /s') do set info=%%i&del !info:~0,-7! /F/Q
for /f "delims=" %%i in ('dir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub\*.rename /b /s') do set info=%%i&move /Y !info! !info:~0,-7!

rem 拷贝自定义文件
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\USERCODE\SRV_PUB\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub /I/E/Y ${outputlog}run36.log

<#if sysrun.isEnableVC() && (sys.getPSSVNInstRepo().getSVNType()=="GIT")>
rem pub、usr项目与版本库同步
"${toolfolder}\jdk1.8\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.GitHelper ${sys.getPSSVNInstRepo().getPSGitUser().getResCfgFilePath()} ${sys.getPSSVNInstRepo().getGitPath()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub SRV_PUB
"${toolfolder}\jdk1.8\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.GitHelper ${sys.getPSSVNInstRepo().getPSGitUser().getResCfgFilePath()} ${sys.getPSSVNInstRepo().getGitPath()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr SRV_USR
<#elseif sysrun.isEnableVC() && (sys.getPSSVNInstRepo().getSVNType()=="SVN")>
"${toolfolder}\java\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SvnCmd3 ${sys.getReadOnlyPSSVNInstRepo().getConnStr()}/${sys.getPSDevSlnCodeName()}/${sys.getTrunkSysName()}/${sys.getVCName()}   ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub SRV_PUB
"${toolfolder}\java\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SvnCmd3 ${sys.getPSSVNInstRepo().getConnStr()}/${sys.getPSDevSlnCodeName()}/${sys.getTrunkSysName()}/${sys.getVCName()}   ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr SRV_USR
</#if>

rem 拷贝web文件到解决方案、运行项目、安装项目
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\web /I/E/Y/D ${outputlog}run37.log
robocopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName}\src\main\webapp /XN /XO /E /XD ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB\WEB-INF\classes\messages ${outputlog}run38.log
xcopy ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\WEB\*.* ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}\src\main\webapp /I/E/Y ${outputlog}run39.log

rem 实现解决方案中文件（大小写差异）改名功能。实现原理同上。
for /f "delims=" %%i in ('dir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\*.rename /b /s') do set info=%%i&del !info:~0,-7! /F/Q
for /f "delims=" %%i in ('dir ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}\*.rename /b /s') do set info=%%i&move /Y !info! !info:~0,-7!

<#if sysrun.isEnableVC() && (sys.getPSSVNInstRepo().getSVNType()=="GIT")>
rem 解决方案、运行项目与版本库同步
"${toolfolder}\jdk1.8\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.GitHelper ${sys.getPSSVNInstRepo().getPSGitUser().getResCfgFilePath()} ${sys.getPSSVNInstRepo().getGitPath()} ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName} WEB_PUB
"${toolfolder}\jdk1.8\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.GitHelper ${sys.getPSSVNInstRepo().getPSGitUser().getResCfgFilePath()} ${sys.getPSSVNInstRepo().getGitPath()} ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName} WEB_USR
<#elseif sysrun.isEnableVC() && (sys.getPSSVNInstRepo().getSVNType()=="SVN")>
"${toolfolder}\java\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SvnCmd3 ${sys.getReadOnlyPSSVNInstRepo().getConnStr()}/${sys.getPSDevSlnCodeName()}/${sys.getTrunkSysName()}/${sys.getVCName()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName} WEB_PUB
"${toolfolder}\java\bin\java.exe" -cp ${toolfolder}\js\saibz5.jar net.ibizsys.paas.builder.SvnCmd3 ${sys.getPSSVNInstRepo().getConnStr()}/${sys.getPSDevSlnCodeName()}/${sys.getTrunkSysName()}/${sys.getVCName()}  ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName} WEB_USR
</#if>
</#if>

rem 删除发布代码目录中的.rename后缀文件
del ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${sys.getVCName()}\srv_${pub.codeName}\*.rename /S/Q/F ${outputlog}run50.log

rem 编译安装后台服务器
<#if !sysrun.isPubCodeOnly()>
chdir /D ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\sln_${pub.codeName}
call ${toolfolder}\maven\bin\mvn.cmd<#if sysrun.isRebuildMode() && sysrun.getRebuildModeEx()!=2> clean</#if> install 
chdir /D ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_pub
call ${toolfolder}\maven\bin\mvn.cmd install 
chdir /D ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\srv_${pub.codeName}_usr
call ${toolfolder}\maven\bin\mvn.cmd install
rem chdir /D ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\${pub.codeName}
rem 安装项目
chdir /D ${codefolder}\${sys.getPSDevCenterDomain()}\${sys.getPubSystemId()}\${workshop}\install_${pub.codeName}
call ${toolfolder}\maven\bin\mvn.cmd<#if sysrun.isRebuildMode() && sysrun.getRebuildModeEx()!=2> clean</#if> install
<#else>
ECHO BUILD SUCCESS
ECHO BUILD SUCCESS
ECHO BUILD SUCCESS
ECHO BUILD SUCCESS
</#if>