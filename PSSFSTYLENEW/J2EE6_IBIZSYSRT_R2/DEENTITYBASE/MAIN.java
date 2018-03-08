package ${pub.getPKGCodeName()}.srv.${item.getPSSystemModule().codeName?lower_case}.entity;


import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;

import java.sql.Timestamp;
import net.ibizsys.paas.data.IDataObject;
import net.ibizsys.paas.data.DataObject;
import net.ibizsys.paas.util.StringHelper;
import net.sf.json.JSONObject;
import net.ibizsys.paas.util.JSONObjectHelper;
import net.ibizsys.paas.xml.XmlNode;
import net.ibizsys.paas.service.ServiceGlobal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

<#comment>
<#list  item.getPSDERs(false) as der >
<#if (der.getDERType()=='DER1N') >
<#assign majorde=der.getMajorPSDataEntity()>
<#if majorde.id != item.id>
import ${pub.getPKGCodeName()}.srv.${majorde.getPSSystemModule().codeName?lower_case}.entity.${majorde.getClassOrPkgName('ENTITY',pub)};
</#if>
import ${pub.getPKGCodeName()}.srv.${majorde.getPSSystemModule().codeName?lower_case}.service.${majorde.getClassOrPkgName('SERVICE',pub)};
</#if>
</#list>
<#list  item.getPSDERs(true) as der >
<#if (der.getDERType()=='DER1N') && der.getMinorCodeName()!=''>
<#assign minorde=der.getMinorPSDataEntity()>
<#if minorde.id != item.id>
import ${pub.getPKGCodeName()}.srv.${minorde.getPSSystemModule().codeName?lower_case}.entity.${minorde.getClassOrPkgName('ENTITY',pub)};
</#if>
import ${pub.getPKGCodeName()}.srv.${minorde.getPSSystemModule().codeName?lower_case}.service.${minorde.getClassOrPkgName('SERVICE',pub)};
</#if>
</#list>
</#comment>
<#if item.getInheritPSDataEntity()??>
<#assign inheritde=item.getInheritPSDataEntity()>
import ${pub.getPKGCodeName()}.srv.${inheritde.getPSSystemModule().codeName?lower_case}.entity.${inheritde.codeName};
import ${pub.getPKGCodeName()}.srv.${inheritde.getPSSystemModule().codeName?lower_case}.service.${inheritde.codeName}Service;
</#if>

/**
 * 实体[${item.codeName}] 数据对象
 */
public class ${item.codeName} extends <#if item.getInheritPSDataEntity()??>${pub.getPKGCodeName()}.srv.${inheritde.getPSSystemModule().codeName?lower_case}.entity.${inheritde.codeName}<#else>${pub.getBaseClassPKGCodeName()}.paas.entity.EntityBase implements Serializable</#if> {

   private static final long serialVersionUID = -1L;
   private static final Log log = LogFactory.getLog(${item.codeName}.class); 
   <#list item.getPSDEFields() as defield>
   <#if defield.dataType!='INHERIT' || item.isVirtual() >
   /**
    *   实体属性标识[${defield.logicName}]
    */
   public final static String FIELD_${defield.codeName?upper_case} = "${defield.name?upper_case}";
   </#if>
   </#list> 

   <#list item.getPSDEFields() as defield>
   <#if defield.dataType!='INHERIT' || item.isVirtual() >
   private final static int INDEX_${defield.codeName?upper_case} = ${defield_index};
   </#if>
   </#list> 

   private final static HashMap<String, Integer> fieldIndexMap = new HashMap<String, Integer>();
   static
   {
       <#list item.getPSDEFields() as defield>
       <#if defield.dataType!='INHERIT' || item.isVirtual() >
       fieldIndexMap.put( FIELD_${defield.codeName?upper_case}, INDEX_${defield.codeName?upper_case});
       </#if>
       </#list>
   }
   
   private ${item.codeName} proxy${item.codeName} = null;

   public ${item.codeName}(){
        super();
<#if inheritde??>
		try
		{
		    this.set(${inheritde.codeName}.FIELD_${inheritde.getIndexTypePSDEField().codeName?upper_case},"${item.getPSDERInherit().getTypeValue()}");
		}
		catch(Exception ex)
		{
			
		}
</#if>
   }
<#list item.getPSDEFields() as defield>
<#if defield.dataType!='INHERIT' || item.isVirtual() >
   private boolean ${defield.codeName?lower_case}DirtyFlag = false;
</#if>
</#list> 

<#list item.getPSDEFields() as defield>
<#if defield.dataType!='INHERIT' || item.isVirtual() >
    @Column(name="${defield.codeName?lower_case}")
    private ${srfjavatype(defield.stdDataType)} ${defield.codeName?lower_case};
</#if>
</#list>  

   
<#list item.getPSDEFields() as defield>
<#if defield.dataType!='INHERIT' || item.isVirtual() >
    /**
     *  设置属性值[${defield.logicName}]<#if (defield.getPSCodeList()??)>代码表：${defield.getPSCodeList().getClassOrPkgName('CODELIST',pub)}</#if>
     *  @param ${defield.codeName?lower_case}
     */
    public void set${defield.codeName}(${srfjavatype(defield.stdDataType)} ${defield.codeName?lower_case}){
    	
    	if(this.getProxyEntity()!=null){
    		this.getProxyEntity().set${defield.codeName}(${defield.codeName?lower_case});
    		return;
    	}
<#if srfjavatype(defield.stdDataType)=='String'>
        if(${defield.codeName?lower_case}!=null)
        {
        	${defield.codeName?lower_case} = StringHelper.trimRight(${defield.codeName?lower_case});
        	if(${defield.codeName?lower_case}.length()==0){
        		${defield.codeName?lower_case} = null;
        	}
        }
<#if defield.getStringCase()!=''>
        if(${defield.codeName?lower_case}!=null)
        {
<#if defield.getStringCase()=='UCASE'>
            ${defield.codeName?lower_case} = ${defield.codeName?lower_case}.toUpperCase();
<#else>    
            ${defield.codeName?lower_case} = ${defield.codeName?lower_case}.toLowerCase();
</#if>            
        }
</#if>
</#if>
        this.${defield.codeName?lower_case} =  ${defield.codeName?lower_case}; 
        this.${defield.codeName?lower_case}DirtyFlag  = true;
<#if defield.isKeyDEField()>
<#if inheritde??>
        //设置父数据主键
        this.set${inheritde.getKeyPSDEField().codeName}(${defield.codeName?lower_case}); 
</#if>
<#if !defield.isPhisicalDEField() && item.getUnionKeyValuePSDEFields()??>
        try{
        if(!StringHelper.isNullOrEmpty(${defield.codeName?lower_case})){
            String[] _values = StringHelper.split(${defield.codeName?lower_case},"||");
<#list item.getUnionKeyValuePSDEFields() as unionKeyField>
            this.set(FIELD_${unionKeyField.codeName?upper_case},_values[${unionKeyField_index?c}]);
</#list>           
        }
        }
        catch(Exception ex){ 
           log.error(ex);
        }
</#if>
<#if !defield.isPhisicalDEField() && item.getUnionKeyValuePSDEFields()??>
        try{
        if(!StringHelper.isNullOrEmpty(${defield.codeName?lower_case})){
            String[] _values = StringHelper.split(${defield.codeName?lower_case},"||");
<#list item.getUnionKeyValuePSDEFields() as unionKeyField>
            this.set(FIELD_${unionKeyField.codeName?upper_case},_values[${unionKeyField_index?c}]);
</#list>           
        }
        }
        catch(Exception ex){ 
           log.error(ex);
        }
</#if>
</#if>
<#if defield.isMajorDEField()>
<#if inheritde??>
        //设置父数据主属性
        this.set${inheritde.getMajorPSDEField().codeName}(${defield.codeName?lower_case}); 
</#if>
</#if>
    }
    
    /**
     *  获取属性值[${defield.logicName}]<#if (defield.getPSCodeList()??)>代码表：${defield.getPSCodeList().getClassOrPkgName('CODELIST',pub)}</#if>
     */
    public ${srfjavatype(defield.stdDataType)} get${defield.codeName}(){
    	if(this.getProxyEntity()!=null){
    		return this.getProxyEntity().get${defield.codeName}();
    	}
        return this.${defield.codeName?lower_case};
    }

    /**
     *  获取属性值[${defield.logicName}]是否修改
     */
    public boolean is${defield.codeName}Dirty(){
    	if(this.getProxyEntity()!=null){
    		return this.getProxyEntity().is${defield.codeName}Dirty();
    	}
        return this.${defield.codeName?lower_case}DirtyFlag;
    }

    /**
     *  重置属性值[${defield.logicName}]
     */
    public void reset${defield.codeName}(){
    	
    	if(this.getProxyEntity()!=null){
    		 this.getProxyEntity().reset${defield.codeName}();
    		 return;
    	}
    	
        this.${defield.codeName?lower_case}DirtyFlag = false;
        this.${defield.codeName?lower_case} = null;
<#if defield.isKeyDEField()>
<#if inheritde??>
        //设置父数据主键
        this.reset${inheritde.getKeyPSDEField().codeName}(); 
</#if>
</#if>
    }
</#if>
</#list>    

	/* (non-Javadoc)
	 * @see net.ibizsys.paas.entity.EntityBase#onReset()
	 */
    @Override
    protected void onReset()
    {
       ${item.codeName}.resetAll(this);
       super.onReset();
    }
    
    /**
     * 重置当前数据对象属性值
     * @param entity
     */
    private static void resetAll(${item.codeName} et){
<#list item.getPSDEFields() as defield>
<#if defield.dataType!='INHERIT' || item.isVirtual() >
        et.reset${defield.codeName}();
</#if>
</#list> 
    }

     /* (non-Javadoc)
      * @see net.ibizsys.paas.entity.EntityBase#onFillMap(java.util.HashMap, boolean)
      */
    @Override
    protected void onFillMap(HashMap<String, Object> params, boolean bDirtyOnly)
    {
    	<#list item.getPSDEFields() as defield>
<#if defield.dataType!='INHERIT' || item.isVirtual() >
        if(!bDirtyOnly || is${defield.codeName}Dirty()){
             params.put(FIELD_${defield.codeName?upper_case},get${defield.codeName}());
        } 
</#if>
        </#list> 
    	super.onFillMap(params, bDirtyOnly);
    }
   
    /* (non-Javadoc)
     * @see net.ibizsys.paas.data.DataObject#get(java.lang.String)
     */
    @Override
	public Object get(String strParamName) throws Exception
	{
    	if(this.getProxyEntity()!=null){
    		return this.getProxyEntity().get(strParamName);
    	}
    	
            if(StringHelper.isNullOrEmpty(strParamName))
                 throw new Exception("没有指定属性");
            Integer index=fieldIndexMap.get(strParamName.toUpperCase());
            if(index==null)
                 return super.get(strParamName);

                return  ${item.codeName}.get(this, index);
	}
    
    /**
     * 通过属性标识获取属性值
     * @param et 数据对象
     * @param index 属性标识
     * @return
     * @throws Exception
     */
    private static Object get(${item.codeName} et,int index) throws Exception{
             
            switch(index)
    	    {
<#list item.getPSDEFields() as defield>
<#if defield.dataType!='INHERIT' || item.isVirtual() >
               case INDEX_${defield.codeName?upper_case}:return et.get${defield.codeName}();
</#if>
</#list>
    	       default:
    		     throw new Exception("不明属性标识");
    	    }
    }

    /* (non-Javadoc)
     * @see net.ibizsys.paas.data.DataObject#set(java.lang.String, java.lang.Object)
     */
    @Override
	public void set(String strParamName,Object objValue) throws Exception
	{
    	if(this.getProxyEntity()!=null){
    		 this.getProxyEntity().set(strParamName,objValue);
    		 return;
    	}
            if(StringHelper.isNullOrEmpty(strParamName))
                 throw new Exception("没有指定属性");

            Integer index=fieldIndexMap.get(strParamName.toUpperCase());
            if(index==null)
            {
                super.set(strParamName,objValue);
                return;
            }
            
            ${item.codeName}.set(this,index,objValue);
 	}

    /**
     * 通过属性标识设定属性值
     * @param et 数据对象
     * @param index 属性标识
     * @param obj 值
     * @throws Exception
     */
            private static void set(${item.codeName} et,int index,Object obj) throws Exception
         {    
            switch(index)
    	    {
<#list item.getPSDEFields() as defield>
<#if defield.dataType!='INHERIT' || item.isVirtual() >
               case INDEX_${defield.codeName?upper_case}:et.set${defield.codeName}(DataObject.get${srfjavatype2(defield.stdDataType)}Value(obj));return ;
</#if>
</#list>
    	       default:
    		     throw new Exception("不明属性标识");
    	    }
        }

            /* (non-Javadoc)
             * @see net.ibizsys.paas.data.DataObject#isNull(java.lang.String)
             */
            @Override
    	public boolean isNull(String strParamName) throws Exception
    	{
			 if(this.getProxyEntity()!=null){
		 		return this.getProxyEntity().isNull(strParamName);
		 	}
            if(StringHelper.isNullOrEmpty(strParamName))
                 throw new Exception("没有指定属性");

    	    Integer index=fieldIndexMap.get(strParamName.toUpperCase());
            if(index==null)
                 return super.isNull(strParamName);

    	     return  ${item.codeName}.isNull(this, index);
    	}

            /**
             * 判断指定属性值是否为空值
             * @param et
             * @param index
             * @return
             * @throws Exception
             */
         private static boolean isNull(${item.codeName} et,int index) throws Exception{
             
            switch(index)
    	    {
<#list item.getPSDEFields() as defield>
<#if defield.dataType!='INHERIT' || item.isVirtual() >
               case INDEX_${defield.codeName?upper_case}:return et.get${defield.codeName}()==null;
</#if>
</#list>
    	       default:
    		     throw new Exception("不明属性标识");
    	    }
    }

    
         /* (non-Javadoc)
          * @see net.ibizsys.paas.data.DataObject#contains(java.lang.String)
          */
         @Override
    	public boolean contains(String strParamName) throws Exception
    	{
    	 	if(this.getProxyEntity()!=null){
		 		return this.getProxyEntity().contains(strParamName);
		 	}
    	 
            if(StringHelper.isNullOrEmpty(strParamName))
                 throw new Exception("没有指定属性");
    	    Integer index=fieldIndexMap.get(strParamName.toUpperCase());
            if(index==null)
                 return super.contains(strParamName);

    	    return  ${item.codeName}.contains(this, index);
    	}

    /**
     * 获取判断对象是否存在指定属性值
     * @param et
     * @param index
     * @return
     * @throws Exception
     */
         private static boolean contains(${item.codeName} et,int index) throws Exception{
             
            switch(index)
    	    {
<#list item.getPSDEFields() as defield>
<#if defield.dataType!='INHERIT' || item.isVirtual() >
               case INDEX_${defield.codeName?upper_case}:return et.is${defield.codeName}Dirty();
</#if>
</#list>
    	       default:
    		     throw new Exception("不明属性标识");
    	    }
    }

         /* (non-Javadoc)
          * @see net.ibizsys.paas.data.DataObject#onFillJSONObject(net.sf.json.JSONObject, boolean)
          */
    @Override
      protected void onFillJSONObject(JSONObject objJSON, boolean bIncludeEmpty) throws Exception
      {
          fillJSONObject(this,objJSON,bIncludeEmpty);
          super.onFillJSONObject(objJSON, bIncludeEmpty);
       }
        
    /**
     * 填充当前对象到JSON
     * @param et 当前数据对象
     * @param json JSON对象
     * @param bIncEmpty 是否包括空值
     * @throws Exception
     */
        private static  void fillJSONObject(${item.codeName} et,JSONObject json, boolean bIncEmpty) throws Exception
        {
            <#list item.getPSDEFields() as defield>
<#if defield.dataType!='INHERIT' || item.isVirtual() >
                if(bIncEmpty||et.get${defield.codeName}()!=null)
        	{
                	JSONObjectHelper.put(json,"${defield.name?lower_case}",getJSONValue(et.get${defield.codeName}()),false);
        	}
</#if>
        </#list> 
        }

        /* (non-Javadoc)
         * @see net.ibizsys.paas.data.DataObject#onFillXmlNode(net.ibizsys.paas.xml.XmlNode, boolean)
         */
     @Override
      protected void onFillXmlNode(XmlNode xmlNode,boolean bIncludeEmpty) throws Exception
      {
           fillXmlNode(this,xmlNode,bIncludeEmpty);
           super.onFillXmlNode(xmlNode, bIncludeEmpty);
       }
        
     /**
      * 填充当前对象到Xml节点中
      * @param et 当前数据对象
      * @param node Xml节点
      * @param bIncEmpty 是否包括空值
      * @throws Exception
      */
        private static void fillXmlNode(${item.codeName} et,XmlNode node,boolean bIncEmpty) throws Exception
        {
            <#list item.getPSDEFields() as defield>
<#if defield.dataType!='INHERIT' || item.isVirtual() >
                if(bIncEmpty||et.get${defield.codeName}()!=null)
        	{
                    Object obj = et.get${defield.codeName}();
                    node.setAttribute("${defield.xmlTagName}",(obj==null)?"":<#if srfjavatype(defield.stdDataType)=='String'>(String)obj<#else>StringHelper.format("${defield.getValueFormat()}",obj)</#if>);
         	}
</#if>
        </#list> 


        }

        /* (non-Javadoc)
         * @see net.ibizsys.paas.entity.EntityBase#onCopyTo(net.ibizsys.paas.data.IDataObject, boolean)
         */
	    @Override
	   	protected void onCopyTo(IDataObject dataEntity, boolean bIncludeEmtpy) throws Exception
	  	{
	          ${item.codeName}.copyTo(this,dataEntity,bIncludeEmtpy);
	          super.onCopyTo(dataEntity,bIncludeEmtpy);
	  	}
        
	    /**
         * 复制当前对象数据到目标对象
         * @param et 当前数据对象
         * @param dst 目标数据对象
         * @param bIncEmpty 是否包括空值
         * @throws Exception
         */
        private static void copyTo(${item.codeName} et,IDataObject dst,boolean bIncEmpty) throws Exception
        {
            <#list item.getPSDEFields() as defield>
<#if defield.dataType!='INHERIT' || item.isVirtual() >
            if(et.is${defield.codeName}Dirty() && (bIncEmpty||et.get${defield.codeName}()!=null))
        	{
        		dst.set(FIELD_${defield.codeName?upper_case},et.get${defield.codeName}());
         	}
</#if>
        </#list> 
        }
        
        /* (non-Javadoc)
         * @see net.ibizsys.paas.data.DataObject#remove(java.lang.String)
         */
        @Override
    	public boolean remove(String strParamName) throws Exception
    	{
        	if(this.getProxyEntity()!=null){
		 		return this.getProxyEntity().remove(strParamName);
		 	}
        	
            if(StringHelper.isNullOrEmpty(strParamName))
                 throw new Exception("没有指定属性");
            Integer index=fieldIndexMap.get(strParamName.toUpperCase());
            if(index==null)
                 return super.remove(strParamName);
            return  ${item.codeName}.remove(this, index);
    	}
        
        /**
         * 通过属性标识删除属性值
         * @param entity
         * @param index
         * @return
         * @throws Exception
         */
        private static boolean remove(${item.codeName} et,int index) throws Exception
        {
        	switch(index)
        	{
    <#list item.getPSDEFields() as defield>
    <#if defield.dataType!='INHERIT' || item.isVirtual() >
				case INDEX_${defield.codeName?upper_case}: et.reset${defield.codeName}();return true;
    </#if>
    </#list>
				default: throw new Exception("不明属性标识");
			}
        }

<#list  item.getPSDERs(false) as der >
<#if (der.getDERType()=='DER1N') || (der.getDERType()=='DER11') >
<#assign majorde=der.getMajorPSDataEntity()>

     private Object obj${der.codeName}Lock = new Object();
     private ${majorde.getClassOrPkgName('ENTITY',pub)} ${der.codeName?lower_case} = null;
     /**
     * 获取父数据 ${majorde.logicName}
      * @throws Exception
     */
    public ${majorde.getClassOrPkgName('ENTITY',pub)} get${der.codeName}() throws Exception
    {
    	if(this.getProxyEntity()!=null){
	 		return this.getProxyEntity().get${der.codeName}();
	 	}
    	
<#assign pickupfield=item.getPSDEField('${der.getPickupDEFName()}')>
        if(this.get${pickupfield.codeName}()==null)
             return null;
        synchronized(obj${der.codeName}Lock)
        {
        	if(${der.codeName?lower_case}==null)
        	{
        		${der.codeName?lower_case} = new ${majorde.getClassOrPkgName('ENTITY',pub)}();
        		${der.codeName?lower_case}.set${majorde.getKeyDEField().codeName}(this.get${pickupfield.codeName}());
     	        ${majorde.getClassOrPkgName('SERVICE',pub)} service = (${majorde.getClassOrPkgName('SERVICE',pub)})ServiceGlobal.getService(${majorde.getClassOrPkgName('SERVICE',pub)}.class,this.getSessionFactory()); 
     	        service.autoGet(${der.codeName?lower_case});
        	}
	        return ${der.codeName?lower_case};
        }
    }
</#if>
</#list>


<#list  item.getPSDERs(true) as der >
<#if (der.getDERType()=='DER1N') && (der.getMinorCodeName()!='') >
<#assign minorde=der.getMinorPSDataEntity()>

    private Object obj${der.getMinorCodeName()}Lock = new Object();
    private ArrayList<${minorde.getClassOrPkgName('ENTITY',pub)}> ${der.getMinorCodeName()?lower_case} = null;
   
     /**
     * 获取子数据 ${minorde.logicName}
      * @throws Exception
     */
    public ArrayList<${minorde.getClassOrPkgName('ENTITY',pub)}> get${der.getMinorCodeName()}() throws Exception
    {
    	if(this.getProxyEntity()!=null){
	 		return this.getProxyEntity().get${der.getMinorCodeName()}();
	 	}

        if(this.get${item.getKeyPSDEField().codeName}()==null)
             return null;
<#if (der.getTempDataOrder()>0)>        
        ${item.getClassOrPkgName('SERVICE',pub)} etservice = (${item.getClassOrPkgName('SERVICE',pub)})ServiceGlobal.getService(${item.getClassOrPkgName('SERVICE',pub)}.class,this.getSessionFactory());
</#if>  
        ${minorde.getClassOrPkgName('SERVICE',pub)} service = (${minorde.getClassOrPkgName('SERVICE',pub)})ServiceGlobal.getService(${minorde.getClassOrPkgName('SERVICE',pub)}.class,this.getSessionFactory());
        synchronized(obj${der.getMinorCodeName()}Lock)
        {
        	if(${der.getMinorCodeName()?lower_case}==null){
<#if (der.getTempDataOrder()>0)>
            if(etservice.isTempData(this))
                ${der.getMinorCodeName()?lower_case} =  service.selectTempBy${der.codeName}(this);
          else
               ${der.getMinorCodeName()?lower_case} =  service.selectBy${der.codeName}(this);

  <#else>
                ${der.getMinorCodeName()?lower_case} =  service.selectBy${der.codeName}(this);
</#if>      
            }
        	return ${der.getMinorCodeName()?lower_case};
         }
    }
</#if>
</#list>

<#list  item.getPSDERs(true) as der >
<#if (der.getDERType()=='DER11') && (der.getMinorCodeName()!='') >
<#assign minorde=der.getMinorPSDataEntity()>

    private Object obj${der.getMinorCodeName()}Lock = new Object();
    private ${minorde.getClassOrPkgName('ENTITY',pub)} ${der.getMinorCodeName()?lower_case} = null;
    
    /**
    * 获取子数据 ${minorde.logicName}
     * @throws Exception
    */
   public ${minorde.getClassOrPkgName('ENTITY',pub)} get${der.getMinorCodeName()}() throws Exception
   {
	   if(this.getProxyEntity()!=null){
	 		return this.getProxyEntity().get${der.getMinorCodeName()}();
	 	}
   	
	   if(this.get${item.getKeyPSDEField().codeName}()==null)
           return null;
       synchronized(obj${der.getMinorCodeName()}Lock)
       {
       	if(${der.getMinorCodeName()?lower_case}==null)
       	{
       		${der.getMinorCodeName()?lower_case} = new ${minorde.getClassOrPkgName('ENTITY',pub)}();
       		${der.getMinorCodeName()?lower_case}.set${minorde.getKeyDEField().codeName}(this.get${item.getKeyPSDEField().codeName}());
    	        ${minorde.getClassOrPkgName('SERVICE',pub)} service = (${minorde.getClassOrPkgName('SERVICE',pub)})ServiceGlobal.getService(${minorde.getClassOrPkgName('SERVICE',pub)}.class,this.getSessionFactory()); 
    	          if(this.get${item.getKeyPSDEField().codeName}().indexOf(net.ibizsys.paas.service.ServiceBase.TEMPKEY) == 0)
    	             service.getTemp(${der.getMinorCodeName()?lower_case});
    	        else
    	            service.get(${der.getMinorCodeName()?lower_case});
       	}
	        return ${der.getMinorCodeName()?lower_case};
       }
   }
</#if>
</#list>


	/**
	 *  获取代理的数据对象
	 */
	private ${item.codeName} getProxyEntity(){return this.proxy${item.codeName};}

	/* (non-Javadoc)
	 * @see net.ibizsys.paas.data.DataObject#onProxy(net.ibizsys.paas.data.IDataObject)
	 */
	@Override
	protected void onProxy(IDataObject proxyDataObject)
	{
		this.proxy${item.codeName} = null;
		if(proxyDataObject!=null && proxyDataObject instanceof ${item.codeName}){
			this.proxy${item.codeName} = (${item.codeName})proxyDataObject;
		}
			
	}

}