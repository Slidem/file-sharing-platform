package com.file.sharing.jms.commons.converter;

/**
 * @author Alexandru Mihai
 * @created Oct 21, 2017
 * 
 *          <p>
 *          Converter for serializing/deserializing, jms messages published or
 *          consumed.
 *          </p>
 */
public interface JmsMessageConverter {

	/**
	 * @param object
	 *            Object to be converted to string representation
	 * @return String representation of the object. Depends on the underlying
	 *         implementation: E.g: JSON, XML etc..
	 */
	public String toString(Object object);

	/**
	 * @param message
	 *            Jms message to be converted to an object
	 * @param type
	 *            Type of the object to be returned
	 * @return The deserialized string message
	 */
	public <T> T toObject(String message, Class<T> type);

}
