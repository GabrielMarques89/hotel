package hotel.Util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionsUtil {

	public Type[] getGenericType(Class<?> target) {
		if (target == null) {
			return new Type[0];
		}
		Type[] types = target.getGenericInterfaces();
		if (types.length > 0) {
			return types;
		}
		Type type = target.getGenericSuperclass();
		if (type != null && type instanceof ParameterizedType) {
			return new Type[] { type };
		}
		return new Type[0];
	}

	public ParameterizedType getParameterizedType(Class<?> target) {
		Type[] types = this.getGenericType(target);
		if (types.length > 0 && types[0] instanceof ParameterizedType) {
			return (ParameterizedType) types[0];
		}
		return null;
	}

	public Type[] getParameterizedTypes(Class<?> target) {
		Type[] types = this.getGenericType(target);
		if (types.length > 0 && types[0] instanceof ParameterizedType) {
			return ((ParameterizedType) types[0]).getActualTypeArguments();
		}
		return new Type[0];
	}
}
