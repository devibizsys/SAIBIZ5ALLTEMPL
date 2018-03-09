{ xtype:'tree',autoLoad: false,model: Ext.create('Ext.data.TreeModel',{fields: [ ]}),
	            proxy: {
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