<#assign de=item.getPSDataEntity() >

    /* 
    * ${item.logicName} 操作
    */
    public void ${methodname}(${de.codeName} ${de.codeName?lower_case}) throws Exception
    {
        onBefore${item.codeName}(${de.codeName?lower_case});
        internal${item.codeName}(${de.codeName?lower_case});
        onAfter${item.codeName}(${de.codeName?lower_case});
    }
    
     /* 
    * ${item.logicName} 内部操作
    */
    protected void internal${item.codeName}(${de.codeName} ${de.codeName?lower_case}) throws Exception
    {

    }