/**********************************************************************************************************************
 **	Copyright 2021-22 HANOVER.  All rights reserved.
 **
 **	No Part of this file should be copied or distributed without the permission of RNT.
 **	Application		:	RNT
 **	Module			:	Service module
 ** Version			:	1.0
 **	File			:	PropertyListner.java
 **	Description		:	The java PropertyListner. 
 **	Author			:	Keshav Varma
 **	Created Date	:	Tuesday April 13, 2021
 **********************************************************************************************************************
 **	Change History Header:
 **********************************************************************************************************************
 **	Date			Author    		  Version 		Description:
 **	-------			--------   		  --------		------------
 ** 04/13/2010      Keshav Varma       1.0           Created
 *********************************************************************************************************************/
package ai.rnt.rms.app.config;

import java.util.Objects;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jndi.JndiTemplate;


/**
 * The java class PropertyListner
 * 
 * @author Keshav Varma
 * @version 1.0
 */
public class PropertyListner {
	private static final Logger log = LogManager.getLogger(PropertyListner.class);
	Properties props = null;
	JndiTemplate jndiTemplate = null;
	
	public void setJndiTemplate(JndiTemplate jndiTemplate) {
		this.jndiTemplate = jndiTemplate;
	}
	 /**
	   * This method is used to init.
	   * @throws HMIRARBusinessException the HMIRAR business exception
	   */
	private void init() {
		if (jndiTemplate == null) {
			jndiTemplate = new JndiTemplate();
		}
		try {
			props = (Properties) jndiTemplate.lookup("java:comp/env/core/RNTResource");
			log.info("proprties read done");
		} catch (Exception e) {
			log.info("proprties reading exception");
			log.info(e);
		}
	}

	 /**
	   * This method is used to get properties.
	   * 
	   * @return Properties
	   */
	public Properties getProperties() {
		if (props == null) {
			init();
		}
		return this.props;
	}

	 /**
	   * This method is used to is local server.
	   * 
	   * @return boolean
	   * @throws HMIRARBusinessException the HMIRAR business exception
	   */
	public boolean isLocalServer() {
		try {
			if (!Objects.isNull(getProperties().getProperty("serverType"))
					&& getProperties().getProperty("serverType").equals("prod")) {
				return true;
			}
		} catch (Exception e) {
			log.info("Exception occured while checking server type");
			log.info(e);
		}
		return false;
	}

}
