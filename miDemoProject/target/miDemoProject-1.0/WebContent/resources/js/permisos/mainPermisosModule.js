var mainPermisosModule = (function(){
	'use strict';

	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	 var _tabuser = false;
     var _tabrole = false;
     var _tabpermiso = false;
     var _idLinkTabUser = "#tabuserlink";
     var _idLinkTabRoles = "#tabrolelink";
     var _idLinkTabPermisos = "#tabpermisolink";
     var _idUserTab = "#tabsopciones a[href='#tabUsuarios']";
     var _idRoleTab = "#tabsopciones a[href='#tabRoles']"; 
     var _idPermisoTab = "#tabsopciones a[href='#tabPermisos']";
     
     var setTabPermiso = function(v){
    	 _tabpermiso = v;
     };
     var setTabUser = function(v){
    	 _tabuser = v;
     };
     var setTabRole = function(v){
    	 _tabrole = v;
     };
     
     var checkTab = function(){
	   
		if ( _tabuser ){
	  		 $(_idLinkTabUser).addClass('active');
	  		 $(_idUserTab).tab('show');
	  		 
	  	}else{
	  			if ( _tabrole ){
	  				 $(_idLinkTabRoles).addClass('active');
	  		  		 $(_idRoleTab).tab('show');
	  				
	  			}else{
	  					if ( _tabpermiso){
	  						 $(_idLinkTabPermisos).addClass('active');
	  				  		 $(_idPermisoTab).tab('show');
	  					}
	  			}
	  	}
    	 
     }
     
	 var initializePage = function (){
		$(document).ready(function() {
			pestanaUsuarioModule.init();
			pestanaRoleModule.init();
			pestanaPermisosModule.init();
			
		} );
	}
	
	return {
		initPage: initializePage,
		setTabUser: setTabUser,
		setTabRole: setTabRole,
		setTabPermiso:setTabPermiso,
		checkTab: checkTab
	}
})();

mainPermisosModule.initPage();
