/**********************************************************************************************************************
 ** Copyright 2021-22 HANOVER. All rights reserved.
 **
 ** No Part of this file should be copied or distributed without the permission
 * of HANOVER. Application : HMIRAR Module : Service module Version : 1.0 File :
 * HMIRARFactory.java Description : The java HMIRARFactory. Author : Keshav
 * Varma Created Date : Tuesday April 13, 2021
 **********************************************************************************************************************
 ** Change History Header:
 **********************************************************************************************************************
 ** Date Author Version Description: ------- -------- -------- ------------
 ** 04/13/2010 Keshav Varma 1.0 Created
 *********************************************************************************************************************/

package ai.rnt.main.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

import org.apache.naming.ResourceRef;


/**
 * The java class HMIRARFactory
 * 
 * @author Keshav Varma
 * @version 1.0
 */

public class RNTFactory implements ObjectFactory {

	/**
	 * This method is used to get the object instance.
	 * 
	 * @param obj
	 * @param name
	 * @param nameCtx
	 * @param environment
	 * @return Object
	 * @throws HMIRARBusinessException the HMIRAR business exception
	 */
	@Override
	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
			throws NamingException {
		try {
			if (obj instanceof ResourceRef) {
				Reference ref = (Reference) obj;
				Enumeration<RefAddr> e = ref.getAll();
				Properties retProperties = new Properties();
				while (e.hasMoreElements()) {
					RefAddr ra = e.nextElement();
					String propName = ra.getType();
					String value = (String) ra.getContent();
					if (propName.equals("configPath")) {
						InputStream input = RNTFactory.class.getResourceAsStream(value);
						retProperties.load(input);
					}
					if (propName.equals("factory") || propName.equals("scope") || propName.equals("auth")
							|| propName.equals("singleton") || propName.equals("configPath")) {
						continue;
					}
					retProperties.put(propName, value);
				}
				return retProperties;
			} else {
				return null;
			}
		} catch (Exception ie) {
			NamingException ne = new NamingException(ie.getMessage());
			ne.setRootCause(ie);
			throw ne;
		}
	}
}
