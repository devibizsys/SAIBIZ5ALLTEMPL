{ xtype:'store',autoLoad:true,model: Ext.create('Ext.data.Model',{
	            	    fields: [<#list records as record><#if (record_index>0) >,</#if>${record.code}</#list> ]
	            		}),
	            proxy: {
	                // load using HTTP
	                type: 'ajax',
	                actionMethods:{read:'POST'},
	                url: '${view.backendUrl}SRFCTRLID=${ctrl.name}',
	                timeout:60000,
	                noCache: true,                     
	                reader: {
	                    type: 'json',
	                    rootProperty:'items',
	                    code:'code',
	                    url:'url'
	                }
	            }
	        }