/**
 * 
 */
package com.nebulent.vectura.mule.processors;

import java.io.ByteArrayInputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.transformer.DataType;
import org.mule.api.transformer.Transformer;
import org.mule.transformer.types.DataTypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mfedorov
 *
 */
public abstract class BaseProcessor implements MessageProcessor, MuleContextAware {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected MuleContext muleContext;
    
	/* (non-Javadoc)
	 * @see org.mule.api.context.MuleContextAware#setMuleContext(org.mule.api.MuleContext)
	 */
	@Override
	public void setMuleContext(MuleContext context) {
		this.muleContext = context;
	}
	
	/**
	 * @param expectedType
	 * @param target
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> T convertTo(Class<T> expectedType, Object target) throws Exception {
		//System.out.println(target.getClass());
		
		if (target.getClass().toString().equals("class [B")) {
			target = new ByteArrayInputStream((byte[])target);
		}
		
		//System.out.println(isAssignableFrom(expectedType, target.getClass()));
		
	    if ((target!= null) && (!isAssignableFrom(expectedType, target.getClass()))) {
	        DataType<? extends Object> sourceDataType = DataTypeFactory.create(target.getClass());
	        DataType<? extends Object> targetDataType = DataTypeFactory.create((expectedType));
	        Transformer t = muleContext.getRegistry().lookupTransformer(sourceDataType, targetDataType);
	        return (T)t.transform(target);
	    } 
	    else {
	        return (T)target;
	    }
	}
	
    /**
     * @param expectedType
     * @param clazz
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
	protected boolean isAssignableFrom(Type expectedType, Class clazz) {
        if (expectedType instanceof Class) {
            return ((Class) expectedType).isAssignableFrom(clazz);
        }
        if (expectedType instanceof ParameterizedType) {
            return isAssignableFrom(((ParameterizedType) expectedType).getRawType(), clazz);
        }
        if (expectedType instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) expectedType).getUpperBounds();
            if (upperBounds.length!= 0) {
                return isAssignableFrom(upperBounds[ 0 ], clazz);
            }
        }
        return false;
    }
}
