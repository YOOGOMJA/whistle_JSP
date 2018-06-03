import java.lang.reflect.*;
import org.json.simple.*;

public class ReflectionMethod {
	private static Class _parentClass;
	private static Method[] _parentMethods;
	
	private boolean chkState() {
		return _parentMethods == null || _parentMethods.length < 0;
	}
	
	private Method get(String name) {
		if(chkState()) {
			for(Method fn : _parentMethods) {
				if(fn.getName().equals(name)) {
					return fn;
				}
			}
			return null;
		}
		else {
			return null;
		}
	}
	
	public ReflectionMethod(Class parentClass) {
		super();
		if(parentClass == null) {
			new Exception("parent Class is empty");
		}
		else {
			_parentClass = parentClass;	
			_parentMethods = _parentClass.getMethods();
		}
	}
	
	public boolean exists(String fn_nm) {
		if(_parentMethods == null || _parentMethods.length <= 0) { return false; }
		for(int i = 0 ; i < _parentMethods.length ; i++) {
			System.out.println(_parentMethods[i]);
			if(_parentMethods[i].getName().equals(fn_nm)) {
				return true;
			}
		}
		return false;
	}
	
	public Object invoke(String fn_nm , JSONObject params) {
		if(chkState()) {
			try {
				return get(fn_nm).invoke(fn_nm, params);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
