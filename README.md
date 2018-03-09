# SAIBIZ5ALLTEMPL
#一、后台模板</br>
2018.2.28</br>
1.访问控制体系支持“当前系统角色及实体角色”模式</br>
	DEMODELBASE</br></br>
	SYSMODELBASE</br>
2.微服务接口功能调整</br>
	DESERVICEAPICONTROLLERBASE</br>
	DESERVICEBASE</br>
	SUBSYSSERVICEAPIMODELBASE</br>
3.工作流支持处理支持异步操作</br>
	WFVERSIONMODELBASE</br>
4.实体处理逻辑支持插件</br>
	DELOGICBASE/NODE_SFPLUGIN	（新增）</br></br>
	
2018.2.1</br>
1.微服务接口调整，添加版本支持及调用参数扩展</br>
	DESERVICEAPICONTROLLERBASE</br></br>

2018.1.30</br>
1.实体逻辑数据准备支持数据上下文</br>
	DEDATASETBASE</br>
	DELOGICBASE/NODE_PREPAREPARAM</br>
2.列表部件、移动端多数据部件支持权限设置</br>
	DECTRLHANDLERBASE/LIST</br>
	DECTRLHANDLERBASE/MOBMDCTRL</br></br>
	
2018.1.25</br>
1.微服务接口功能调整</br>
	DEMODELBASE</br>
	SYSMODELBASE</br>
	DESERVICEAPICONTROLLERBASE</br>
	SUBSYSSERVICEAPIMODELBASE</br></br>

2018.1.23</br>
1.向导面板部件处理对象调整（支持初始化操作中Get、Update获取主键）</br>
	DECTRLHANDLERBASE/WIZARDPANEL</br>
2.支持微服务接口发布</br>
	DECTRLMODELBASE/FORM</br>
	DEENTITYBASE</br>
	DESERVICEBASE</br>
	SYSMODELBASE</br>
	DESERVICEAPICONTROLLERBASE	（新增）</br>
	SUBSYSSERVICEAPIMODELBASE	（新增）</br>
	SYSAPIMODELBASE				（新增）</br></br>

2017.12.6</br>
1.实体处理逻辑发布增强</br>
	DELOGICBASE/NODE_PREPAREPARAM</br>
2.是否打印服务对象支持打印关系实体及关系数据集设置</br>
	DEPRINTBASE</br>
3.工作流版本提供业务主线标记（陈翔使用）</br>
	WFVERSIONMODELBASE	</br>

2017.12.6</br>
1.表格行编辑状态支持动态更新数据</br>
	DECTRLHANDLERBASE/GRID</br>
	DECTRLIMODELTEMHANDLERBASE/GRIDEDITITEM</br>
2.仅关系实体发布获取草稿方法</br>
	DECTRLHANDLERBASE/GRID</br>
3.向导操作</br>
	DECTRLHANDLERBASE/WIZARDPANEL</br>

2017.11.14</br>
1.子系统代码表扩展使用CodeItem数据问题（CodeITem数据）</br>
	CODELISTMODELBASE</br>
	SUBSYSCODELISTMODELBASE</br>
	SYSMODELBASE</br></br>

2017.11.14</br>
1.支持数据缓存</br>
	APPCTRLHANDLERBASE/PORTLET</br>
	DECTRLHANDLERBASE/CHART</br>
	DECTRLHANDLERBASE/DATAVIEW</br>
	DECTRLHANDLERBASE/FORM</br>
	DECTRLHANDLERBASE/GRID</br>
	DECTRLHANDLERBASE/LIST</br>
	DECTRLHANDLERBASE/MBMDCTRL</br>
	DECTRLHANDLERBASE/MOBMDCTRL</br>
	DECTRLHANDLERBASE/SEARCHFORM</br>
	DECTRLHANDLERBASE/TREEVIEW</br>
	DEMODELBASE</br>
	SYSMODELBASE</br></br>

2017.10.20</br>
1.值规则功能增强</br>
	DESERVICEBASE/DEFVR_REGEX</br>
	DESERVICEBASE/DEFVR_SIMPLE</br>
	DESERVICEBASE/DEFVR_STRINGLENGTH</br>
	DESERVICEBASE/DEFVR_SYSVALUERULE(新增)</br>
	DESERVICEBASE/DEFVR_VALUERANGE2</br>
	DESERVICEBASE/DEFVR_VALUERANGE3</br>
	SYSMODELBASE</br>
2.视图关系区支持挂载树视图节点</br></br>
	DECTRLMODELBASE/DRTAB</br>
	DECTRLMODELBASE/TREEVIEW</br>
3.实体逻辑支持直接代码</br>
	DELOGICBASE/NODE_RAWSFCODE</br>
2.选择数据处理模型，添加数据集名称注释，便于调试</br>
	DECTRLIMODELTEMHANDLERBASE/FORMITEM</br></br>

2017.9.11</br>
1.子系统扩展代码表模板修改（CodeItem的Default数据发布onPrepareCodeItems）</br>
	SUBSYSCODELISTMODELBASE</br>
2. 操作权限控制增强</br>
	DEMODELBASE</br>
3. 用户自定义行为支持插件</br>
	DESERVICEBASE</br></br>
	
2017.9.11</br>
1.属性输入提示信息相关功能增强</br>
	DECTRLIMODELTEMHANDLERBASE/FORMITEM</br>
	DECTRLMODELBASE/FORM</br>
	SYSMODELBASE</br>
2.主状态权限控制</br>
	DEMODELBASE</br>
3.支持关系数据拷贝功能</br>
	DESERVICEBASE</br></br>

2017.9.7</br>
1.代码表相关优化</br>
	CODELISTMODELBASE</br>
2.操作向导相关功能优化</br>
	DEMODELBASE</br>
3.提供视图消息功能	</br>
	DEVIEWCONTROLLERBASE</br>
	SYSMODELBASE</br></br>
4.菜单支持从Xml文件中加载	</br>
	APPMENUMODELBASE	</br>
5.自动填充模型支持代码表发布</br>
	DEACBASE</br></br>
	
2017.8.3</br></br>
1.实体支持多数据实体功能</br>
  DEMODELBASE</br>
  DEDATAQUERYBASE</br>
2.系统工作目录动态定义（workshop）</br>
  CREATEJAR</br>
  DEPLOYSYS</br>
3.支持后台服务器目录设置</br></br>

4.工作流超时功能支持</br>
	WFVERSIONMODELBASE</br></br>
	
5.表格部件支持用户自定义范围1，用户自定义范围2</br>
	DECTRLHANDLERBASE/GRID</br></br>

		
2017.7.25</br>
1.系统插件支持</br>
	SYSMODELBASE</br></br>
	
2017.7.20</br>
1.数据删除检查逻辑调整</br>
	DESERVICEBASE</br></br>

2017.7.13</br>
1.代码文件名特殊字符转义处理</br>
	CODELISTMODELBASE</br>
2.发布重置脚本和打包脚本优化</br>
	重置发布内容RESETPUB</br>
	部署系统CREATEJAR</br>
3.SLN项目发布project文件bug修复</br>
	SLN项目目录SLNPRJ_PROJECT</br>
4.大数据功能增强</br>
	BADAOBASE</br>
	BAENTITYBASE</br>
	BASCHEMEMODELBASE</br>
	GLOBALBADAO</br>
	DEBADAOBASE(取消)</br></br>
	

2017.6.20</br>
1.多语言资源支持相关调整</br>
	菜单模型PSSFSTYLECODE/APPMENUMODELBASE</br>
	应用程序上下文PSSFCODETYPE/APPLICATIONCONTEXT</br>
	代码表模型基类PSSFCODETYPE/CODELISTMODELBASE</br>
	实体界面控件模型基类PSSFCODETYPE/DECTRLMODELBASE/FORM</br>
	实体界面控件模型基类PSSFCODETYPE/DECTRLMODELBASE/WFEXPBAR</br>
	实体界面控制基类DEVIEWCONTROLLERBASE</br>
	消息配置PSSFCODETYPE/MESSAGE（新增，用于多语言资源文件的发布）</br></br>
	
2.树导航视图（IFrame）树节点支持右键菜单</br>
	实体界面控件模型基类PSSFCODETYPE/DECTRLMODELBASE/TREEVIEW	</br>
3.项目打包功能优化</br>
	部署系统PSSFCODETYPE/DEPLOYSYS</br>
	Web目录PSSFCODETYPE/INSTALLPRJ_POM（新增）</br>
	SLN项目目录(打包)PSSFCODETYPE/PACKSLNPRJ_POM（新增）</br>
	发布系统版本PSSFCODETYPE/PACKSYSVER</br>
4.Service合并子数据功能优化</br>
5.用于发布的主项目取消对应sasrf框架包的依赖</br>
	PSSFCODETYPE/WEBPRJ_POM</br>
6.微信帐户初始化信息方式调整</br>
	PSSFCODETYPE/WXACCOUNTMODELBASE</br></br>


2017.4.25</br>
1.添加子系统代码表发布对象（SUBSYSCODELISTMODELBASE）</br>
2.修改子系统实体数据实体模型基类模板，提供静态获取实例方法(getInstance)(SUBSYSDEMODELBASE)</br>
3.修改子系统扩展实体服务对象基类模板，提供静态获取实例方法(getInstance)(SUBSYSDESERVICEBASE)</br></br>


2017.4.22</br>
1.动态代码表选择子系统实体发布代码bug解决（该代码表需要选择用户系统模块才会发布代码）(CODELISTMODELBASE)</br>
2.实体模型对象发布模板调整，提供静态获取实例方法(getInstance)(DEMODELBASE)</br></br>

2017.4.8</br>
1.微信模块支持</br>
   WXACCOUNTMODELBASE（新增）</br>
   WXENTAPPMODELBASE（新增）</br>
   SYSMODELBASE</br></br>
   
2.前端视图发布代码优化(SYSAPPMODELBASE)</br>
3.实体模型拷贝代码优化(DEENTITYBASE)</br></br>

2017.3.29</br>
1.大数据支持</br>
	涉及的模板包括：</br>
	BASCHEMEMODELBASE（新增）</br>
	DEBADAOBASE（新增）</br>
	DEMODELBASE</br></br></br>
	DESERVICEBASE</br></br>
	GLOBALBADAO</br>
	SYSMODELBASE</br>
2.表格数据导出支持（DECTRLHANDLERBASE-GRID，DEDATAEXPORTBASE(新增)，DEMODELBASE）</br>
3.表单项输入提示支持（DECTRLMODELBASE-FORM）</br>
4.树节点提供实体名称（DECTRLMODELBASE-TREEVIEW）</br>
5.实体操作向导支持（DEMODELBASE，DEVIEWCONTROLLERBASE）</br>
6.DEDAOBASE查询模型名称宏定义前缀修改（QUERYCODE ==> DATAQUERY）</br></br>

2017.3.24</br>
1.菜单项支持计数标识发布（框架宏代码调整APPMENUMODELBASE）</br></br>


2017.3.16</br>
1.修改实体处理逻辑调用实体行为使用codename的问题（DELOGICBASE下的NODE_DEACTION和NODE_RAWSQLANDLOOPCALL）</br></br>


2017.3.2</br>
1.提供系统级数据集基类</br></br>

2017.2.25</br></br>
1.实体数据名称与标识的问题(表单及行编辑外键自动填充，表单项更新模式)</br></br>

2017.2.22</br>
1.系统级代码表基类调整（动态代码表基类，用户范围代码表基类）</br></br>


2017.2.18</br>
1.统一资源rt环境安装支持更新操作</br>
2.发布支持PGSQL和PPAS</br>
3.实体DAO提供实体QueryCode标识宏定义</br></br>
#二、前台模版</br>
2017.12.2</br>
1.无数据按钮的权限控</br>
	JSPPART_HEADE</br>
	TOOLBAR__PART/DEUIACTIO</br></br>

2017.12.</br>
1.支持表格列更</br>
	PSPFSTYLECODE/GRIDVIEWCONTROLLER_BAS</br>
2.多语言标记bug修</br>
	PSPFSTYLECODE/JSP_HEADE</br>
3.多数据视图控制器功能优化</br>
	PSPFSTYLECODE/MDVIEWCONTROLLER_BAS</br></br>

2017.11.</br>
1.表单项支持固定列宽设</br>
	PSPFCTRLTEMPL/FORM__HTML/FORMITE</br>
	PSPFCTRLTEMPL/FORM__PART/FORMITE</br>
	PSPFCTRLTEMPL/FORM__HTML/GROUPPANE</br>
	PSPFCTRLTEMPL/FORM__PART/GROUPPANE</br></br>


2017.7.1</br>
1.支持快速重构和完成重</br>
    PSPFAPPTEMPL/APP_CREATEAP</br>
    PSPFAPPTEMPL/APP_RESETPU</br>
    
2017.6.2</br>
1.表单文本框编辑器支持单位显</br>
	PSPFEDITORTEMPL/TEXTBOX__FORMITEM__HTM</br>
	PSPFEDITORTEMPL/TEXTBOX__FORMITEM__PAR</br>
2.多语言支</br>
	PSPFSTYLECODE/CONTROLLER_BAS</br>
	PSPFSTYLECODE/JSP_HEADE</br>
	PSPFSTYLECODE/JSPPART_HEADE</br>
	PSPFCTRLTEMPL/APPMENU__PART/MENUITE</br>
	PSPFCTRLTEMPL/FORM__PAR</br>
	PSPFCTRLTEMPL/FORM__PART/BUTTO</br>
	PSPFCTRLTEMPL/FORM__PART/DRUIPAR</br>
	PSPFCTRLTEMPL/FORM__PART/FORMITE</br>
	PSPFCTRLTEMPL/GRID__CONTROLLE</br>
	PSPFCTRLTEMPL/SEARCHFORM__PAR</br>
	PSPFCTRLTEMPL/TOOLBAR__PART/DEUIACTIO</br>
	PSPFAPPTEMPL/APP_LAN(新增)</br>
3.树导航视图（IFrame）树节点支持右键菜</br>
	PSPFSTYLECODE/EXPVIEWCONTROLLER_BAS</br>
4.表格视图支持默认数据不加</br>
	PSPFSTYLECODE/GRIDVIEWCONTROLLER_BAS</br>
5.表单相关部件提供父组件标</br>
	PSPFCTRLTEMPL/FORM__CONTROLLER/DRUIPAR</br>
	PSPFCTRLTEMPL/FORM__CONTROLLER/TABPAG</br>
6.前端app项目默认web.xml文件生</br>
	PSPFAPPTEMPL/APP_WEBXML(新增</br>
	PSPFAPPTEMPL/APP_WEBXML2(新增)</br></br>

2017.4.27</br>
1.首页视图控制器模板调整，支持"打开HTML页面"、"执行JavaScript"功能类型(APPINDEXVIEW__CONTROLLER)</br></br>

2017.4.14</br>
1.DEFAULT_EX_PGGRID表单成员支持系统样式配置（GROUPPANEL，BUTTON，FORMITEM,RAWITEM,TABPANEL）</br></br>

2017.4.7</br>
1.表单布局调整，取消table布局（只修改了DEFAULT_EX_PGGRID，DEFAULT已经还原回去，运行一段时间在更新DEFAULT）</br>
2.表格操作列支持（DEFAULT：GRIDVIEWCONTROLLER_BASE，GRID__CONTROLLER，DEFAULT_EX_PGGRID：GRID__CONTROLLER-COLUMN）</br>
3.列表视图数据导入地址后追加实体名称（CONTROLLER_BASE）</br></br>

2017.4.7</br>
1.表单布局调整，取消table布局（修改表单部件的fromitem的html代码模板）</br>
2.数据选择编辑器html模板bug修复（</div>标签位置错误）</br></br>


2017.3.2</br>
1.即时预览功能支持（JSP_HEADER、JSP_BOTTOM）</br>
</br>
2017.2.28</br>
1.实体表单分组支持是否收缩（part、controller）</br>
2.实体表单分页支持动态显示配置（part、controller）（需要前端脚本支持，添加了IBizFormTabPage）</br>

