/*
 *表格 ${item.name} 后台处理
 */
protected AjaxActionResult on${srfclassname('${item.name}')}FetchAction() throws Exception{

    MDAjaxActionResult mdAjaxActionResult = new MDAjaxActionResult();
	    
	    Grid grid = this.getViewModel().get${srfclassname('${item.name}')}();
	    DEDataSetFetchContextImpl deDataSetFetchContextImpl = new DEDataSetFetchContextImpl(this.getWebContext());
	    fill${srfclassname('${item.name}')}FetchConditions(deDataSetFetchContextImpl);
	    
	    DBFetchResult fetchResult = this.getService().fetch${item.getPSAjaxControlParam().getPSDEDataSet().codeName}(deDataSetFetchContextImpl);
	    mdAjaxActionResult.setTotalRow(fetchResult.getTotalRow());
		fill${srfclassname('${item.name}')}FetchResult(mdAjaxActionResult,fetchResult.getDataSet().getDataTable(0));
		fetchResult.getDataSet().close();
	
	    return mdAjaxActionResult ;
	}  
	
	
	
	/**
	 * 填充表格搜索条件
	 * @param deDataSetFetchContextImpl
	 * @throws Exception
	 */
	protected void fill${srfclassname('${item.name}')}FetchConditions(DEDataSetFetchContextImpl deDataSetFetchContextImpl)throws Exception
	{
		// 获取动态条件
		this.onFill${srfclassname('${item.name}')}FetchSearchFormCSMConditions(deDataSetFetchContextImpl.getConditionList());
		this.onFill${srfclassname('${item.name}')}FetchSearchFormConditions(deDataSetFetchContextImpl.getConditionList());
		this.onFill${srfclassname('${item.name}')}FetchURLConditions(deDataSetFetchContextImpl.getConditionList());
	
	}
	
	
	protected void onFill${srfclassname('${item.name}')}FetchURLConditions(ArrayList<IDEDataSetCond> userConditions)throws Exception
	{
		String strParentType = WebContext.getParentType(this.getWebContext());
		if(StringHelper.isNullOrEmpty(strParentType))
			return;
		
		String strParentKey = WebContext.getParentKey(this.getWebContext());
		if(StringHelper.isNullOrEmpty(strParentKey))
		{
			DEDataSetCondImpl deDataSetCondImpl = new DEDataSetCondImpl();
			deDataSetCondImpl.setCondType(IDEDataSetCond.CONDTYPE_CUSTOM);
			deDataSetCondImpl.setCustomCond("1<>1");
			userConditions.add(deDataSetCondImpl);
			return;
		}
		
		if(StringHelper.compare(strParentType, WebContext.PARAM_PARENTTYPE_DER1N, true)==0)
		{
			String strDER1N = WebContext.getDER1NId(this.getWebContext());
			DER der = this.getSystemModel().getDER1N(strDER1N);
			DEField defield = this.getDEModel().getDEField(der.pickupdefname(), false);
			
			DEDataSetCondImpl deDataSetCondImpl = new DEDataSetCondImpl();
			deDataSetCondImpl.setCondType(IDEDataSetCond.CONDTYPE_DEFIELD);
			deDataSetCondImpl.setCondOp(ICondition.CONDOP_EQ);
			deDataSetCondImpl.setDEFName(defield.name());
			deDataSetCondImpl.setCondValue(strParentKey);
			
			userConditions.add(deDataSetCondImpl);
			return;
		}
		
	}
	
	protected void onFill${srfclassname('${item.name}')}FetchSearchFormConditions(ArrayList<IDEDataSetCond> userConditions)throws Exception
	{
		java.util.Iterator<DEField> deFields = this.getDEModel().getDEFields();
		while(deFields.hasNext())
		{
			DEField defield = deFields.next();
			DEFSearchMode[] defSearchModes= defield.defsearchmodes();
			if(defSearchModes == null)
				continue;
			
			for(DEFSearchMode defSearchMode:defSearchModes){
				String strFormItemId = defSearchMode.name();
				String strValue =getWebContext().getPostValue(strFormItemId.toLowerCase());
				if (StringHelper.isNullOrEmpty(strValue))
					continue;
				
				DEDataSetCondImpl deDataSetCondImpl = new DEDataSetCondImpl();
				deDataSetCondImpl.setCondType(IDEDataSetCond.CONDTYPE_DEFIELD);
				deDataSetCondImpl.setCondOp(defSearchMode.valueop());
				deDataSetCondImpl.setDEFName(defield.name());
				deDataSetCondImpl.setCondValue(strValue);
				
				userConditions.add(deDataSetCondImpl);
			}
		}
		
	}


	/**
	 * 填充搜索表单自定义搜索条件
	 * @param userConditions
	 * @param daQueryModelHelper
	 */
	protected void onFill${srfclassname('${item.name}')}FetchSearchFormCSMConditions(ArrayList <IDEDataSetCond> userConditions)throws Exception
	{
		
	}
	
	protected void fill${srfclassname('${item.name}')}FetchResult(MDAjaxActionResult fetchResult,IDataTable dt)throws Exception
	{
		Grid grid = this.getViewModel().getGrid();
		if(dt.getCachedRowCount()==-1)
		{
			while(true )
			{
				IDataRow iDataRow = dt.next();
				if(iDataRow==null)
					break;
				
				JSONObject jo = new JSONObject();
				DataItem[] dataItems = grid.griddataitems();
				for(DataItem dataItem:dataItems){
					Object objValue = onGetGridDataItemValue(dataItem,iDataRow);
					jo.put(dataItem.name(), objValue);
				}
				fetchResult.getRows().add(jo);
			}
		}
		else
		{
			int nRows = dt.getCachedRowCount();
			for(int i=0;i<nRows;i++)
			{
				IDataRow iDataRow = dt.getCachedRow(i);
				
				JSONObject jo = new JSONObject();
				DataItem[] dataItems = grid.griddataitems();
				for(DataItem dataItem:dataItems){
					Object objValue = onGetGridDataItemValue(dataItem,iDataRow);
					jo.put(dataItem.name(), objValue);
				}
				fetchResult.getRows().add(jo);
			}
		}
	}

	
	/**
	 * 
	 * @param dataItem
	 * @param object
	 * @return
	 * @throws Exception
	 */
	protected Object onGet${srfclassname('${item.name}')}DataItemValue(DataItem dataItem, Object object) throws Exception
	{
		return DataItemHelper.getValue(dataItem, this.getWebContext(), object);
	}