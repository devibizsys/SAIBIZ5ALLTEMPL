<#assign de=item.getPSDataEntity() >
   /* 
    * ${item.name} 操作
    */
   public void ${methodname}(IDEDBCallContext iDEDBCallContext,${de.codeName} ${de.codeName?lower_case})throws Exception
   {
	DBCallResult dbCallResult = this.executeDEDBProc( iDEDBCallContext, "", ${de.codeName?lower_case});
   }