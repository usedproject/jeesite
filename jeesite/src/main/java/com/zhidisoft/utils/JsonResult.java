package com.zhidisoft.utils;

/**
 * {
 * 	"success":true|false,
 *  "message":"",
 *  "errorCode":-100 ?? -999
 *  "result":Object 
 * }
*/
public class JsonResult {

	/** ????????????????????????  */
	private Boolean success;
	/** ??????????????????????*/
	private String message;
	/** ??????????????????????????*/
	private Integer errorCode;
	/** ????????????????????*/
	private Object result;	
	
	/**
	 * JsonResult
	 * @param message
	 * @param result
	 * @return
	 */
	public static JsonResult buildSuccessResult(String message, Object result){		
		return new JsonResult(true,message,null,result);
	}
	
	
	public static JsonResult buildSuccessResult(String message){		
		return new JsonResult(true,message,null,null);
	}	
		

	public static JsonResult buildFailureResult(String message){		
		return new JsonResult(false,message,null,null);
	}
	
	public static JsonResult buildFailureResult(String message,Integer errorCode){		
		return new JsonResult(false,message,errorCode,null);
	}
	
	public JsonResult() {
		super();
	}

	public JsonResult(Boolean success, String message, Integer errorCode, Object result) {
		super();
		this.success = success;
		this.message = message;
		this.errorCode = errorCode;
		this.result = result;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	
}
