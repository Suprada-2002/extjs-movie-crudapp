Ext.application({
    name: 'Fiddle',
    launch: function () {
    	Ext.create('Ext.form.Panel',{
    		id:'searchForm',
    		title:'Movie Advance search',
    		buttonAlign : 'center',
    		renderTo:Ext.getBody(),
    		layout:{
    		    type:'hbox'
    		},
    		border:false,
    		items:[{
    			xtype:'container',
    			padding:'10 0 50 80',
    			layout: {
                    type: 'vbox'
                },
                flex:1,
                items:[{
                	xtype: 'textfield',
                    fieldLabel: 'Movie Name',
					name:'title'
            },{xtype: 'numberfield',
				maxValue: 2021,
              	minValue: 1900,
                fieldLabel: 'Release Date',
				name:'release_year'
            }]
    		},{
    			xtype:'container',
    			layout:{
    				type:'vbox'
    			},
    			flex:1,
    			padding:'10 0 50 0',
    			items:[{
    				xtype:'textfield',
    				fieldLabel:'Director Name',
					name:'director'
    			},{
                    xtype: 'combobox',
                    fieldLabel: 'Language',
                    store: Ext.create('Ext.data.Store', {
                       fields: ['abbr', 'name'],
                       data:[{
	                  	      'abbr':'English',
	                  	      'name':'English'
	                  	    },{
	                  	      'abbr':'Italian',
	                  	      'name':'Italian'
	                  	    },{
	                  	       'abbr':'Japanese',
	                  	       'name':'Japanese'
	                  	    },{
	                  	       'abbr':'Mandarin',
	                  	       'name':'Mandarin'
	                  	    },{
	                  	       'abbr':'French',
	                  	       'name':'French'
	                  	    },{
	                  	        'abbr':'German',
	                  	        'name':'German'
	                  	    },{
	                  	        'abbr':'Mongolian',
	                  	        'name':'Mongolian'
	                  	    }],
                    }),
                    valueField: 'abbr',
                    displayField: 'name',
					name:'language'
                 }]
    			}],
    			buttons: [{
                    text: 'Search',
                    formBind: true, //only enabled once the form is valid
                    disabled: true,
                    handler: function() {
                        var form = this.up('form').getForm();
						store.getProxy().extraParams = {
		    				title: "\""+Ext.getCmp('searchForm').getValues().title+"\"",
							director:"\""+Ext.getCmp('searchForm').getValues().director+"\"",
							language:"\""+Ext.getCmp('searchForm').getValues().language+"\"",
							release_year:"\""+Ext.getCmp('searchForm').getValues().release_year+"\""
							};
                        store.reload();
                    }
                },{
                    text: 'Reset',
                    handler: function() {
                        this.up('form').getForm().reset();
                        store.getProxy().extraParams = {
		    				title: "\""+"\"",
							director:"\""+"\"",
							Language:"\""+"\"",
							release_year:"\""+"\"",
							};
						store.reload();
                    }
                }
            ],
    	});
        Ext.define('Film', {
            extend: 'Ext.data.Model',
            fields:[ 'title', 'description', 'release_year','language','director_name','rating','secial_features'],
        });

        
        var itemsPerPage = 20;
        var store = Ext.create('Ext.data.Store', {
            model: 'Film',
            pageSize: itemsPerPage ,
            autoLoad: false,
            proxy: {
                type: 'ajax',
                url : 'http://localhost:8080/1829013/getdata',
                reader: {
                    type: 'json',
                    rootProperty: 'films',
                    totalProperty:'total'
                }
            }
        });
        store.load({
            params: {
                start: 0,
                limit: itemsPerPage
            }
        });
        Ext.create('Ext.grid.Panel', {
            title: 'Movie Grid',
            store: store,
            columns: [
                { text: 'Title', dataIndex: 'title',flex:0.2},
                { text: 'Description', dataIndex: 'description', flex:0.3},
                { text: 'Release Year', dataIndex: 'release_year', flex:0.1},
                { text: 'Language', dataIndex: 'language', flex:0.1},
                { text: 'Director', dataIndex: 'director_name', flex:0.1},
                { text: 'Rating', dataIndex: 'rating', flex:0.1},
                { text: 'Special Feature', dataIndex: 'secial_features', flex:0.2 }
            ],
								
			listeners: {
				select: function(){
					var len = Ext.getCmp('DataGrid').getSelectionModel().getCount();
					console.log(len);
					if(len==1){
						Ext.getCmp('Edit1').setDisabled(false);
					}
					else{
						Ext.getCmp('Edit1').setDisabled(true);
					}
					if(len>=1){
						Ext.getCmp('Delete1').setDisabled(false);
					}
				},
				deselect: function(){
					var len = Ext.getCmp('DataGrid').getSelectionModel().getCount();
					if(len==1){
						Ext.getCmp('Edit1').setDisabled(false);
					}
					if(len==0){
						Ext.getCmp('Edit1').setDisabled(true);
						Ext.getCmp('Delete1').setDisabled(true);
					}
				}
			},
            dockedItems: [{
                xtype: 'pagingtoolbar',
                dock: 'top',
                store: store,
                items: [
                	{ xtype: 'tbseparator' },
                    {
                        xtype: 'button',
                        iconCls: 'x-fa fa-upload'
                    },{ xtype: 'tbseparator' },
                    {
                        xtype: 'button',
                        text: 'Add',
                        iconCls: 'x-fa fa-plus-circle',
                        listeners: {
                        	click: function(){
                        		Ext.create('Ext.window.Window', {
	                  	    		  title: 'Add Film',
	                  	    		  height: 450,
	                  	    		  width: 400,    	                  	    	
	                  	    		  target : document.getElementById('Add1'),
	                  	    		  layout: 'fit',
	                  	    		  items: { 
	                  	    		   xtype: 'form',
	                  	    		   id: 'myForm',
	                  	    		   buttonAlign : 'center',
	                  	    		   url:'http://localhost:8080/1829013/add',
	                  	    		   border: false,
	                  	    		   closeAction:'close',
	                  	    		   plain: true,
	                  	    		   layout:'vbox',
	                  	    		   items: [{
	                  	    		    xtype: 'textfield',
	                  	    		    fieldLabel: 'Title',
	                  	    		    margin: '10 0 0 10',
	                  	    		    name:'title'
	                  	    		   }, {
	                  	    		    xtype: 'numberfield',
	                  	    		    fieldLabel: 'Release Year',
	                  	    		    margin: '10 0 0 10',
	                  	    		    maxValue: 2021,
	                  	    		    minValue: 1900,
	                  	    		    name:'release_year'
	                  	    		   }, {
	                  	    		    xtype: 'combobox',
	                  	    		    fieldLabel: 'special features',
	                  	    		    margin: '10 0 0 10',
	                  	    		    multiSelect : 'True',
	                  	    		    store:Ext.create('Ext.data.Store',{
	                  	    		     fields:['abbr','name'],
	                  	    		     data:[{
	                  	    		      'abbr':'Trailers',
	                  	    		      'name':'Trailers'
	                  	    		     },{
	                  	    		      'abbr':'Deleted Scenes',
	                  	    		      'name':'Deleted Scenes'
	                  	    		     },{
	                  	    		      'abbr':'Behind The Scenes',
	                  	    		      'name':'Behind The Scenes'
	                  	    		     },{
	                  	    		    	'abbr':'Commentaries',
	                  	    		      	'name':'Commentaries'
	                  	    		     }],
	                  	    		    }),
	                  	    		    valueField: 'abbr',
	                  	    		    displayField: 'name',
	                  	    		    name:'secial_features'
	                  	    		   }, {
	                  	    		    xtype: 'combobox',
	                  	    		    fieldLabel: 'Rating',
	                  	    		    margin: '10 0 0 10',
	                  	    		    store:Ext.create('Ext.data.Store',{
	                  	    		     fields:['abbr','name'],
	                  	    		     data:[{
	                  	    		      'abbr':'R',
	                  	    		      'name':'R'
	                  	    		     },{
	                  	    		      'abbr':'PG',
	                  	    		      'name':'PG'
	                  	    		     },{
	                  	    		      'abbr':'C',
	                  	    		      'name':'C'
	                  	    		     },{
	                  	    		      'abbr':'NC-17',
	                  	    		      'name':'NC-17'
	                  	    		     },{
	                  	    		      'abbr':'PG-13',
	                  	    		      'name':'PG-13'
	                  	    		     }],
	                  	    		    }),
	                  	    		    valueField: 'abbr',
	                  	    		    displayField: 'name',
	                  	    		    name:'rating',
	                  	    		   }, {
	                  	    		    xtype: 'combobox',
	                  	    		    fieldLabel: 'language',
	                  	    		    margin: '10 0 0 10',
	                  	    		    store:Ext.create('Ext.data.Store',{
	                  	    		     fields:['abbr','name'],
	                  	    		     data:[{
	                  	    		      'abbr':'English',
	                  	    		      'name':'English'
	                  	    		     },{
	                  	    		      'abbr':'Italian',
	                  	    		      'name':'Italian'
	                  	    		     },{
	                  	    		      'abbr':'Japanese',
	                  	    		      'name':'Japanese'
	                  	    		     },{
	                  	    		      'abbr':'Mandarin',
	                  	    		      'name':'Mandarin'
	                  	    		     },{
	                  	    		      'abbr':'French',
	                  	    		      'name':'French'
	                  	    		     },{
	                  	    		      'abbr':'German',
	                  	    		      'name':'German'
	                  	    		     },{
	                  	    		      'abbr':'Mongolian',
	                  	    		      'name':'Mongolian'
	                  	    		     }],
	                  	    		    }),
	                  	    		    valueField: 'abbr',
	                  	    		    displayField: 'name',
	                  	    		    name:'language'
	                  	    		   },{
	                  	    		    xtype: 'textfield',
	                  	    		    fieldLabel: 'Director',
	                  	    		    margin: '10 0 0 10',
	                  	    		    name:'director_name'
	                  	    		   },{
	                  	    		    xtype: 'textarea',
	                  	    		    fieldLabel: 'Description',
	                  	    		    margin: '10 0 0 10',
	                  	    		    name:'description'
	                  	    		   }], buttons: [{
	                  	    		    text: 'Save',
	                  	    		    margin:'0 20 0 0',
	                  	    		    
	                  	    		    handler:
	                  	    		     function () {
	                  	    		            var form = this.up("form").getForm();
	                  	    		            // console.log(form);
	                  	    		            if (form.isValid()) {
	                  	    		              form.submit({
	                  	    		                success: function (form, action) {
	                  	    		                  Ext.Msg.alert("Success!", action.response.status);
	                  	    		                  store.reload();
	                  	    		                  this.up('window').destroy();
	                  	    		                  // console.log(action.response.status);
	                  	    		                },
	                  	    		                failure: function (form, action) {
	                  	    		                  Ext.Msg.alert("Failed", "Please try again!");
	                  	    		                  // console.log(action.response.status);
	                  	    		                }})}},
	                  	    		    
	                  	    		   },{
	                 	                    text: 'Cancel',
	              	                    handler: function() {
	              	                        this.up('form').getForm().reset();
	              	                        this.up('window').destroy();
	              	                    }
	              	                }],
	                  	    		   store: Ext.create('Ext.data.ArrayStore', {})
	                  	    		  }
	                  	    		 }).show();
                        	}
                        }
                    },{ xtype: 'tbseparator' },
                    {
                        xtype: 'button',
                        text: 'Edit',
                        iconCls: 'x-fa fa-edit',
						id:'Edit1',
                        listeners: {
                        	click: function(){
                        		var selected=Ext.getCmp('DataGrid').getSelectionModel().getSelection()[0].data;
                        		console.log(selected);
                        		Ext.create('Ext.window.Window', {
	                  	    		  title: 'Edit Film',
	                  	    		  height: 450,
	                  	    		  width: 400,    
	                  	    		  
	                  	    		  target : document.getElementById('Edit1'),
	                  	    		  layout: 'fit',
	                  	    		  items: { 
	                  	    		   xtype: 'form',
	                  	    		   id: 'myForm',
	                  	    		   buttonAlign : 'center',
	                  	    		   url:'http://localhost:8080/1829013/edit',
	                  	    		   border: false,
	                  	    		   closeAction:'close',
	                  	    		   plain: true,
	                  	    		   layout:'vbox',
	                  	    		   items: [{
	                  	    		    xtype: 'textfield',
	                  	    		    fieldLabel: 'Title',
	                  	    		    value:selected.title,
	                  	    		    margin: '10 0 0 10',
	                  	    		    name:'title'
	                  	    		   }, {
	                  	    		    xtype: 'numberfield',
	                  	    		    fieldLabel: 'Release Year',
	                  	    		    margin: '10 0 0 10',
	                  	    		    maxValue: 2021,
	                  	    		    minValue: 1900,
	                  	    		    value:selected.release_year,
	                  	    		    name:'release_year'
	                  	    		   }, {
	                  	    		    xtype: 'combobox',
	                  	    		    fieldLabel: 'special features',
	                  	    		    margin: '10 0 0 10',
	                  	    		    multiSelect : 'True',
	                  	    		    value:selected.secial_features,
	                  	    		    store:Ext.create('Ext.data.Store',{
	                  	    		     fields:['abbr','name'],
	                  	    		     data:[{
	                  	    		      'abbr':'Trailers',
	                  	    		      'name':'Trailers'
	                  	    		     },{
	                  	    		      'abbr':'Deleted Scenes',
	                  	    		      'name':'Deleted Scenes'
	                  	    		     },{
	                  	    		      'abbr':'Behind The Scenes',
	                  	    		      'name':'Behind The Scenes'
	                  	    		     },{
	                  	    		    	'abbr':'Commentaries',
	                  	    		      	'name':'Commentaries'
	                  	    		     }],
	                  	    		    }),
	                  	    		    valueField: 'abbr',
	                  	    		    displayField: 'name',
	                  	    		    name:'secial_features'
	                  	    		   }, {
	                  	    		    xtype: 'combobox',
	                  	    		    fieldLabel: 'Rating',
	                  	    		    margin: '10 0 0 10',
	                  	    		    value:selected.rating,
	                  	    		    store:Ext.create('Ext.data.Store',{
	                  	    		     fields:['abbr','name'],
	                  	    		     data:[{
	                  	    		      'abbr':'R',
	                  	    		      'name':'R'
	                  	    		     },{
	                  	    		      'abbr':'PG',
	                  	    		      'name':'PG'
	                  	    		     },{
	                  	    		      'abbr':'C',
	                  	    		      'name':'C'
	                  	    		     },{
	                  	    		      'abbr':'NC-17',
	                  	    		      'name':'NC-17'
	                  	    		     },{
	                  	    		      'abbr':'PG-13',
	                  	    		      'name':'PG-13'
	                  	    		     }],
	                  	    		    }),
	                  	    		    valueField: 'abbr',
	                  	    		    displayField: 'name',
	                  	    		    name:'rating',
	                  	    		   }, {
	                  	    		    xtype: 'combobox',
	                  	    		    fieldLabel: 'language',
	                  	    		    value:selected.language,
	                  	    		    margin: '10 0 0 10',
	                  	    		    store:Ext.create('Ext.data.Store',{
	                  	    		     fields:['abbr','name'],
	                  	    		     data:[{
	                  	    		      'abbr':'English',
	                  	    		      'name':'English'
	                  	    		     },{
	                  	    		      'abbr':'Italian',
	                  	    		      'name':'Italian'
	                  	    		     },{
	                  	    		      'abbr':'Japanese',
	                  	    		      'name':'Japanese'
	                  	    		     },{
	                  	    		      'abbr':'Mandarin',
	                  	    		      'name':'Mandarin'
	                  	    		     },{
	                  	    		      'abbr':'French',
	                  	    		      'name':'French'
	                  	    		     },{
	                  	    		      'abbr':'German',
	                  	    		      'name':'German'
	                  	    		     },{
	                  	    		      'abbr':'Mongolian',
	                  	    		      'name':'Mongolian'
	                  	    		     }],
	                  	    		    }),
	                  	    		    valueField: 'abbr',
	                  	    		    displayField: 'name',
	                  	    		    name:'language'
	                  	    		   },{
	                  	    		    xtype: 'textfield',
	                  	    		    fieldLabel: 'Director',
	                  	    		    margin: '10 0 0 10',
	                  	    		    value:selected.director_name,
	                  	    		    name:'director_name'
	                  	    		   },{
	                  	    		    xtype: 'textarea',
	                  	    		    fieldLabel: 'Description',
	                  	    		    margin: '10 0 0 10',
	                  	    		    value:selected.description,
	                  	    		    name:'description'
	                  	    		   }], buttons: [{
	                  	    		    text: 'Save',
	                  	    		    margin:'0 20 0 0',
	                  	    		    
	                  	    		    handler:
	                  	    		     function () {
	                  	    		            var form = this.up("form").getForm();
	                  	    		            if (form.isValid()) {
	                  	    		              form.submit({
	                  	    		            	params : {id : selected.film_id},
	                  	    		                success: function (form, action) {
	                  	    		                  Ext.Msg.alert("Success!", action.response.msg);
	                  	    		                  store.reload();
	                  	    		                  this.up('window').destroy();
	                  	    		                },
	                  	    		                failure: function (form, action) {
	                  	    		                  Ext.Msg.alert("Failed", "Please try again!");
	                  	    		                }})}},
	                  	    		    
	                  	    		   },{
	                 	                    text: 'Cancel',
	              	                    handler: function() {
	              	                        this.up('form').getForm().reset();
	              	                        this.up('window').destroy();
	              	                    }
	              	                }],
	                  	    		   store: Ext.create('Ext.data.ArrayStore', {})
	                  	    		  }
	                  	    		 }).show();
                        	}
                        }
                    },{ xtype: 'tbseparator' },
                    {
                        xtype: 'button',
                        text: 'Delete',
						id:"Delete1",
                        handler: function(){ 
							      var x= Ext.getCmp('DataGrid').getSelectionModel().getSelection();
							      if(x.length==0)
							       Ext.Msg.alert('No record(s) to delete!!')
							      else
							       {
							      if(confirm('The selected record(s) will be deleted. Click OK to confirm'))
							       { 
							       selected = [];
							       Ext.each(x, function (item) {
							         selected.push(item.get('film_id'));
							       });
							       Ext.Ajax.request({
							              url:'http://localhost:8080/1829013/delete',
							              params:{id : selected},
							              success: function(response){
							               var text= response.responseText;
							               Ext.Msg.alert('Success!!',text);							              
											store.reload();
	                  	    		        this.up('window').destroy();
											}
							             });
							        }
							      else
							       {
							       Ext.Msg.alert('No deletion will occur!!');
							       }
							     }
							     }
                        
                    },{ xtype: 'tbseparator' 
                    	}
                    ],
                    }],
            height: 400,
            renderTo: Ext.getBody(),
            id:"DataGrid",
            selModel:{
            	injectCheckbox:"first",
            	checkOnly:true,
            	model:'SIMPLE',
            	type:'checkboxmodel'
            }
        });
       }
   });