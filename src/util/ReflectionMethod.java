package util;

import java.lang.reflect.*;
import org.json.simple.*;

public class ReflectionMethod {
	private static Class<?> _parentClass;
	private static Method[] _parentMethods;
	private static Object _parentClassInstance;

	/*
	 * ReflectionMEthod 클래스의 생성자 함수이다. 인스턴스가 존재해야한다. 
	 * 보통 실행할 함수가 속한 클래스의 this를 넘겨주면 된다.
	 * 
	 * @author 				KYEONGSOO YOO
	 * @param instance		실행할 함수가 속한 클래스의 인스턴스 
	 */
	public ReflectionMethod(Object instance) 
	{
		super();
		
		_parentClassInstance = instance;
		_parentClass = _parentClassInstance.getClass();
		_parentMethods = _parentClass.getDeclaredMethods();
	}
	
	/*
	 * 내부 함수이다. 생성자가 제대로 동작했는지 체크한다.
	 * 
	 * @author				KYEONGSOO YOO
	 * @return				생성자가 제대로 생성되었는지 여부 (Boolean)
	 */
	private boolean chkState() {
		return !(_parentMethods == null || _parentMethods.length < 0);
	}
	
	/*
	 * 현재 클래스에서 String을 기준으로 함수를 가져온다. 
	 * 만약 함수가 존재하지 않으면 null을 리턴한다.
	 * 
	 * @author 				KYEONGSOO YOO
	 * @param name			가져올 함수의 이름 
	 * @return 				가져온 함수 , 존재하지 않으면 null 
	 */
	private Method get(String name) {
		for(Method fn : _parentMethods) {
			if(fn.getName().equals(name)) {
				return fn;
			}
		}
		System.out.println("[RM] can not find method by [" + name + "]");
		return null;
	}

	/*
	 * 현재 클래스에 함수가 존재하는지 여부를 확인한다. 
	 * 
	 * @author 				KYEONGSOO YOO
	 * @param name			가져올 함수의 이름 
	 * @return 				함수의 존재 여부  
	 */
	public boolean exists(String fn_nm) {
		for(int i = 0 ; i < _parentMethods.length ; i++) {
			//System.out.println(_parentMethods[i]);
			if(_parentMethods[i].getName().equals(fn_nm)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 현재 클래스에서 함수를 가져오고 실제로 실행한다. 변수들은 json으로 받고 
	 * 그 결과도 json으로 넘겨준다.  
	 * 
	 * @author 				KYEONGSOO YOO
	 * @param fn_nm			가져올 함수의 이름
	 * @param params			함수에 넘겨줄 매개변수들, json 
	 * @return 				함수 실행 결과, json
	 */
	public JSONObject invoke(String fn_nm , JSONObject params) {
		JSONObject obj = new JSONObject();
		try {
			Method fn = get(fn_nm);
			Object value = fn.invoke(_parentClassInstance , new Object[] { params });
			obj.put("INVOKE_RESULT_CD", 1);
			obj.put("INVOKE_DATA", value);
			return obj;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			obj.put("INVOKE_RESULT_CD", -1);
			obj.put("INVOKE_ERR_MSG", e.getMessage());
			return obj;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			obj.put("INVOKE_RESULT_CD", -2);
			obj.put("INVOKE_ERR_MSG", e.getMessage());
			return obj;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			obj.put("INVOKE_RESULT_CD", -3);
			obj.put("INVOKE_ERR_MSG", e.getMessage());
			return obj;
			
		}
	
	}
	
	/*
	 * 클래스의 함수를 실행하고 결과 json까지 만들어준다. 
	 * invoke, exists, get을 합친 것이다. 본 함수를 사용할  
	 * 
	 * @author 				KYEONGSOO YOO
	 * @param fn_nm			가져올 함수의 이름
	 * @param params			함수에 넘겨줄 매개변수들, json 
	 * @return 				함수 실행 결과, json
	 */
	public JSONObject callAndGetResult(String fn_nm , JSONObject params) {
		JSONObject jsonObj = new JSONObject();
		if(chkState()) {
			if(exists(fn_nm)) {
				JSONObject ret = invoke(fn_nm , params);
				int resultCD = (int)ret.get("INVOKE_RESULT_CD");
				if(resultCD > 0) {
					jsonObj.put("RESULT", "SUCCESS");
					jsonObj.put("RESULT_CD", 1);
					jsonObj.put("RESULT_DATA", ret.get("INVOKE_DATA"));
				}
				else {
					jsonObj.put("RESULT", "FAIL");
					jsonObj.put("RESULT_CD", -2);
					jsonObj.put("ERR_MSG" , "[RM] 요청된 함수 ["+ fn_nm +"]의 실행 중 오류가 발생했습니다.");
					jsonObj.put("INVOKE_ERR_MSG", ret.get("INVOKE_ERR_MSG"));
				}
			}	
			else {
				jsonObj.put("RESULT", "FAIL");
				jsonObj.put("RESULT_CD", -1);
				jsonObj.put("ERR_MSG" , "[RM] 요청된 함수 ["+ fn_nm +"]이 존재하지 않습니다.");
			}
		}
		else {
			jsonObj.put("RESULT", "FAIL");
			jsonObj.put("RESULT_CD", 0);
			jsonObj.put("ERR_MSG" , "[RM] 리플렉션 클래스가 정상적으로 초기화되지 않았습니다.");
		}
		
		return jsonObj;
	}
	
	
}
