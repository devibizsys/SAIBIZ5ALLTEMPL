{ xtype:'store',remoteSort:true,autoLoad: false,model: Ext.create('Ext.data.Model',{
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
	                    totalProperty:'totalrow',
	                    summaryinfo:'summaryinfo',
	                    code:'code',
	                    url:'url'
	                }
	            }
	        }