
	var langType=null;
		function initLanguageType(){
			var lan=getCookie("bosLanguage");
			if(lan !=undefined){
				return lan.substring(0,2);
			}
			return "en";
		}
		
		function getLanguageType(){
			if(langType==null){
				langType=initLanguageType().toUpperCase();
			}
			return langType;
		}
		
		function jsText(zhObj,enObj){
			if(getLanguageType()=="ZH"){
				return zhObj;
			}
			return enObj;
		}
		
		function countryText(countryCode,key){
			if(countryCode!=null&&countryCode!=undefined&&countryCode.toUpperCase()=="CN"){
				return Cpropertes_ZN[key];
			}else{
				return Cpropertes_EN[key];
			}
		}
		
		function getCookie(name){
			var arr=document.cookie.match(new RegExp("(^|)")+name+"=([^;]*)(;|$)");
			if(arr !=null){
				return unescape(arr[2]);
			}else{
				return "";
			}
		}
		
		function getResource(key){
			if(getLanguageType()=="ZH"){
				return Cpropertes_ZN[key];
			}else{
				return Cpropertes_EN[key];
			}
		}
		
		String.prototype.toMessage=function(args){
			if(this==""){
				return this;
			}else{
				var propVal=getResource(this);
				if(propVal !=undefined && args !=undefined){
					for(var i=0;i<args.length;i++){
						propVal=propVal.replace(/%s/,args[i]);
					}
				}
			}
		}
		
		String.prototype.toMessage2=function(){
			return this.toMessage(arguments);
		}
		
		var CPropertes_EN={
				show:"English Show"
		}
		
		var CPropertes_ZN={
				show:"中文展示页面"
		}
		
		function aa(){
			alert("aa");
		}
		
	

