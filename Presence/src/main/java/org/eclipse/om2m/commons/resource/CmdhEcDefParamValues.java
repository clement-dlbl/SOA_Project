/*******************************************************************************
 * Copyright (c) 2013-2016 LAAS-CNRS (www.laas.fr)
 * 7 Colonel Roche 31077 Toulouse - France
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Initial Contributors:
 *     Thierry Monteil : Project manager, technical co-manager
 *     Mahdi Ben Alaya : Technical co-manager
 *     Samir Medjiah : Technical co-manager
 *     Khalil Drira : Strategy expert
 *     Guillaume Garzone : Developer
 *     François Aïssaoui : Developer
 *
 * New contributors :
 *******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.15 at 03:56:27 PM CEST 
//

package org.eclipse.om2m.commons.resource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}mgmtResource">
 *       &lt;sequence>
 *         &lt;element name="applicableEventCategory" type="{http://www.onem2m.org/xml/protocols}listOfEventCatWithDef"/>
 *         &lt;element name="defaultRequestExpTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="defaultResultExpTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="defaultOpExecTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="defaultRespPersistence" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="defaultDelAggregation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "applicableEventCategory",
		"defaultRequestExpTime", "defaultResultExpTime", "defaultOpExecTime",
		"defaultRespPersistence", "defaultDelAggregation" })
@XmlRootElement(name = "cmdhEcDefParamValues")
public class CmdhEcDefParamValues extends MgmtResource {

	@XmlList
	@XmlElement(required = true)
	protected List<String> applicableEventCategory;
	protected long defaultRequestExpTime;
	protected long defaultResultExpTime;
	protected long defaultOpExecTime;
	protected long defaultRespPersistence;
	protected boolean defaultDelAggregation;

	/**
	 * Gets the value of the applicableEventCategory property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the applicableEventCategory property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getApplicableEventCategory().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getApplicableEventCategory() {
		if (applicableEventCategory == null) {
			applicableEventCategory = new ArrayList<String>();
		}
		return this.applicableEventCategory;
	}

	/**
	 * Gets the value of the defaultRequestExpTime property.
	 * 
	 */
	public long getDefaultRequestExpTime() {
		return defaultRequestExpTime;
	}

	/**
	 * Sets the value of the defaultRequestExpTime property.
	 * 
	 */
	public void setDefaultRequestExpTime(long value) {
		this.defaultRequestExpTime = value;
	}

	/**
	 * Gets the value of the defaultResultExpTime property.
	 * 
	 */
	public long getDefaultResultExpTime() {
		return defaultResultExpTime;
	}

	/**
	 * Sets the value of the defaultResultExpTime property.
	 * 
	 */
	public void setDefaultResultExpTime(long value) {
		this.defaultResultExpTime = value;
	}

	/**
	 * Gets the value of the defaultOpExecTime property.
	 * 
	 */
	public long getDefaultOpExecTime() {
		return defaultOpExecTime;
	}

	/**
	 * Sets the value of the defaultOpExecTime property.
	 * 
	 */
	public void setDefaultOpExecTime(long value) {
		this.defaultOpExecTime = value;
	}

	/**
	 * Gets the value of the defaultRespPersistence property.
	 * 
	 */
	public long getDefaultRespPersistence() {
		return defaultRespPersistence;
	}

	/**
	 * Sets the value of the defaultRespPersistence property.
	 * 
	 */
	public void setDefaultRespPersistence(long value) {
		this.defaultRespPersistence = value;
	}

	/**
	 * Gets the value of the defaultDelAggregation property.
	 * 
	 */
	public boolean isDefaultDelAggregation() {
		return defaultDelAggregation;
	}

	/**
	 * Sets the value of the defaultDelAggregation property.
	 * 
	 */
	public void setDefaultDelAggregation(boolean value) {
		this.defaultDelAggregation = value;
	}

}
