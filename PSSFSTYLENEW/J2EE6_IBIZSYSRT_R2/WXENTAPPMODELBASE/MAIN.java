package ${pub.getPKGCodeName()}.srv.wx;

import net.ibizsys.pswx.core.WXMenuItem;
import net.ibizsys.pswx.core.WXMenuModel;
import net.ibizsys.pswx.core.WXMenuRootItem;
import net.ibizsys.pswx.core.IWXLogicModel;
import net.ibizsys.pswx.core.WXLogicModel;

import ${pub.getPKGCodeName()}.srv.${sys.codeName}SysModel;

/**
 * ${item.name} 微信企业应用模型基类
 */
public class ${item.getPSWXAccount().codeName}${item.codeName}WXEntAppModel extends ${pub.getBaseClassPKGCodeName()}.pswx.core.WXEntAppModelBase{

    public ${item.getPSWXAccount().codeName}${item.codeName}WXEntAppModel() throws Exception{
       
        super();
        this.setId("${item.id}");
        this.setName("${item.name}");
        this.setAppURL("${item.appURL}");
        this.setAppType("${item.appType}");
<#if item.isReportLocation()>
        this.setReportLocation(true);
</#if>
<#if item.isReportEnter()>
        this.setReportEnter(true);
</#if>
     }
    

   
    protected void onInit()throws Exception{
    	this.onPrepareDefaultWXMenu();
    	this.onPrepareWXLogics();
    	super.onInit();
    }
    
    /**
     * 准备应用默认菜单
     * @throws Exception
     */
    protected void onPrepareDefaultWXMenu()throws Exception{
<#if item.getDefaultPSWXMenu()??>
		WXMenuModel wxMenuModel = new WXMenuModel();
		WXMenuRootItem wxMenuRootItem = wxMenuModel.getRootItem();
<#list item.getDefaultPSWXMenu().getRootItem().getAllItems() as wxmenuitem> 
		//添加 ${wxmenuitem.text}
		WXMenuItem wxMenuItem${wxmenuitem_index?c} = wxMenuRootItem.addItem("${wxmenuitem.id}",<#if wxmenuitem.getPId()??>"${wxmenuitem.getPId()}"<#else>""</#if>);
<#if wxmenuitem.getWXFunc()??>
		wxMenuItem${wxmenuitem_index?c}.setWXFunc("${srfjavastring('${wxmenuitem.getWXFunc()}')}");
</#if>
<#if wxmenuitem.getClickTag()??>
		wxMenuItem${wxmenuitem_index?c}.setClickTag("${srfjavastring('${wxmenuitem.getClickTag()}')}");
</#if>
		wxMenuItem${wxmenuitem_index?c}.setText("${srfjavastring('${wxmenuitem.text}')}");
</#list>
        wxMenuModel.init(this.getWXAccountModel(),this);
        this.setDefaultWXMenu(wxMenuModel);
</#if>
    }
    
    /**
     * 准备应用响应逻辑
     * @throws Exception
     */
    protected void onPrepareWXLogics()throws Exception{
    	IWXLogicModel iWXLogicModel = null;
<#if item.getPSWXLogics()??>    
<#list item.getPSWXLogics() as wxlogic>
		iWXLogicModel = createWXLogicModel("${srfjavastring('${wxlogic.getUserTag()}')}");
		if(iWXLogicModel==null){
			WXLogicModel wxLogicMode = new WXLogicModel();
			wxLogicMode.setId("${wxlogic.id}");
			wxLogicMode.setName("${srfjavastring('${wxlogic.name}')}");
			wxLogicMode.setUserTag("${srfjavastring('${wxlogic.getUserTag()}')}");
<#if wxlogic.getDEName()??>			
			wxLogicMode.setDEName("${srfjavastring('${wxlogic.getDEName()}')}");
</#if>
<#if wxlogic.getDEActionName()??>			
            wxLogicMode.setDEActionName("${srfjavastring('${wxlogic.getDEActionName()}')}");
</#if>
<#if wxlogic.getEventType()??>			
            wxLogicMode.setEventType("${srfjavastring('${wxlogic.getEventType()}')}");
</#if>
<#if wxlogic.getWXFunc()??>			
            wxLogicMode.setWXFunc("${srfjavastring('${wxlogic.getWXFunc()}')}");
</#if>
<#if wxlogic.getClickTag()??>			
            wxLogicMode.setClickTag("${srfjavastring('${wxlogic.getClickTag()}')}");
</#if>
            wxLogicMode.init(this.getWXAccountModel(),this);
			iWXLogicModel = wxLogicMode;
		}
		this.registerWXLogicModel(iWXLogicModel);
</#list>
</#if>
    }
    
    /**
     * 建立微信逻辑模型
     * @param strUserTag
     * @return
     * @throws Exception
     */
    protected IWXLogicModel createWXLogicModel(String strUserTag)throws Exception{
    	return null;
    }
}