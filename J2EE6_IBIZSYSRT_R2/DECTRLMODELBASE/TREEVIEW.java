import net.ibizsys.paas.ctrlmodel.TreeNodeRSModel;
import net.ibizsys.paas.ctrlmodel.TreeStaticNodeModel;
import net.ibizsys.paas.ctrlmodel.TreeCodeListNodeModel;
import net.ibizsys.paas.ctrlmodel.TreeDEDataSetNodeModel;
/**
 * 实体[${de.logicName}]树视图[${item.name}]部件模型
 */
public class  ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model extends ${pub.getBaseClassPKGCodeName()}.paas.ctrlmodel.${srfclassname('${item.getControlType()}')}ModelBase{
   
    public ${de.codeName}${item.codeName}${srfclassname('${item.getControlType()}')}Model()  {
        super();

<#if item.isEnableRootSelect()>
        this.setEnableRootSelect(true);
</#if>
<#if item.isRootVisible()>
        this.setRootVisible(true);
</#if>
<#if item.getCatPSCodeList()??>
        this.setCatCodeListId("${item.getCatPSCodeList().getClassOrPkgName('CODELIST',pub)}");
</#if>
    }

<#if item.getDataEntity()??>
<#assign de=item.getDataEntity()>
      private ${de.getClassOrPkgName('DEMODEL',pub)} ${srfparamname('${de.codeName}')}DEModel;
     protected  ${de.getClassOrPkgName('DEMODEL',pub)} get${de.codeName}DEModel() {
            if(this.${srfparamname('${de.codeName}')}DEModel==null){
                try
                {
                     this.${srfparamname('${de.codeName}')}DEModel = (${de.getClassOrPkgName('DEMODEL',pub)})DEModelGlobal.getDEModel("${de.getClassOrPkgName('DEMODEL',pub)}");
                }
                catch(Exception ex)
                {
                }
            }
           return this.${srfparamname('${de.codeName}')}DEModel;
      }

      @Override
      public IDataEntityModel getDEModel() {
          return this.get${de.codeName}DEModel();
      }
</#if>

  
   /**
     * 准备树模型
     * @throws Exception
     */
     @Override
     protected void onPrepareTreeMode() throws Exception
     {
<#list item.getPSDETreeNodes() as treenode>
<#if treenode.getTreeNodeType()=='STATIC'>
          TreeStaticNodeModel  node${treenode_index?c} = new TreeStaticNodeModel();
          node${treenode_index?c}.setNodeValue("${treenode.getNodeValue()}");
</#if>
<#if treenode.getTreeNodeType()=='CODELIST'>
          TreeCodeListNodeModel  node${treenode_index?c} = new TreeCodeListNodeModel ();
          node${treenode_index?c}.setCodeListId("${pub.getPKGCodeName()}.srv.codelist.${treenode.getPSCodeList().codeName}CodeListModel");
</#if>
  
<#if treenode.getTreeNodeType()=='DE'>
          TreeDEDataSetNodeModel  node${treenode_index?c} = new TreeDEDataSetNodeModel ();
          node${treenode_index?c}.setDEDataSetName("${treenode.getDEDataSetName()}");
<#if treenode.getActiveDataDELogicId()?? && treenode.getActiveDataDELogicId()?length gt 0>
	   node${treenode_index?c}.setActiveDataDELogicId("${srfjavastring('${treenode.getActiveDataDELogicId()}')}");
</#if>
<#if treenode.getFilterDEDataSetName()??>
          node${treenode_index?c}.setFilterDEDataSetName("${treenode.getFilterDEDataSetName()}");
</#if>
<#if ((treenode.getIdField()??) && (treenode.getIdField()?length gt 0)) >
          node${treenode_index?c}.setIdField("${treenode.getIdField()}");
</#if>
<#if ((treenode.getTextField()??) && (treenode.getTextField()?length gt 0)) >
          node${treenode_index?c}.setTextField("${treenode.getTextField()}");
</#if>
<#if ((treenode.getIconField()??) && (treenode.getIconField()?length gt 0)) >
          node${treenode_index?c}.setIconField("${treenode.getIconField()}");
</#if>
<#if ((treenode.getSortField()??) && (treenode.getSortField()?length gt 0)) >
          node${treenode_index?c}.setSortField("${treenode.getSortField()}");
</#if>
<#if ((treenode.getSortDir()??) && (treenode.getSortDir()?length gt 0)) >
          node${treenode_index?c}.setSortDir("${treenode.getSortDir()}");
</#if>
</#if>
<#if treenode.getPSDataEntity()??>
          //${treenode.getDEName()}
          node${treenode_index?c}.setDEName("${treenode.getPSDataEntity().name}");
</#if>
          node${treenode_index?c}.setId("${treenode.id}");
          node${treenode_index?c}.setName("${treenode.name}");
<#if (treenode.isRootNode()) >
          node${treenode_index?c}.setRootNode(true);
</#if>
<#if (treenode.isAppendPNodeId()) >
          node${treenode_index?c}.setAppendPNodeId(true);
</#if>
<#if ((treenode.getIconCls()??) && (treenode.getIconCls()?length gt 0)) >
          node${treenode_index?c}.setIconCls("${treenode.getIconCls()}");
</#if>
<#if (treenode.isExpanded()) >
          node${treenode_index?c}.setExpanded(true);
</#if>
<#if (treenode.isEnableCheck()) >
          node${treenode_index?c}.setEnableCheck(true);
</#if>
<#if (treenode.isChecked()) >
          node${treenode_index?c}.setChecked(true);
</#if>
<#if ((treenode.getNodeType()??) && (treenode.getNodeType()?length gt 0)) >
          node${treenode_index?c}.setNodeType("${treenode.getNodeType()}");
</#if>
<#assign removeName = treenode.getRemovePSDEActionName()>
<#if ((removeName??) && (removeName?length gt 0)) >
          node${treenode_index?c}.setRemoveDEActionName("${removeName}");
</#if>

          node${treenode_index?c}.init(this);
<#list item.getPSDETreeNodeRSs() as rs>
<#if rs.getParentTreeNodeId()== treenode.getId()>
          if(true)
          {
               TreeNodeRSModel rs = new TreeNodeRSModel();
               rs.setParentTreeNodeId("${rs.getParentTreeNodeId()}");
               rs.setChildTreeNodeId("${rs.getChildTreeNodeId()}");
<#if rs.getPSDEAction()??>
               rs.setDEActionName("${rs.getPSDEAction().name}");
</#if>
               rs.init(this);
               node${treenode_index?c}.registerTreeNodeRSModel(rs );
          }
</#if>    
</#list>
          registerTreeNodeModel(node${treenode_index?c});
</#list>
     }}