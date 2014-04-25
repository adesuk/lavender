package com.coba.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="helloWorldBean")
@ViewScoped
public class HelloWorldMBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@EJB
//	private HelloWorldBean helloWorldBean;
//	
	public String getHelloWorld() {
		return "Hallo"; //helloWorldBean.sayHello();
	}
}
