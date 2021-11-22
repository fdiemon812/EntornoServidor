package com.example.demo;

public class DependantService {
	
	private  Service1 service1;
	private  Service2 service2;
	
	

//	public DependantService(Service1 service1, Service2 service2) {
//		
//		this.service1= service1;
//		this.service2=service2;
//	}
	
	
	public DependantService() {
	
}

	public void dosmth() {
		service1.dosmth();
		service2.dosmth();
	}

	public Service1 getService1() {
		return service1;
	}

	public Service2 getService2() {
		return service2;
	}
	

	public void setService1(Service1 service1) {
		this.service1 = service1;
	}
	
	public void setService2(Service2 service2) {
		this.service2 = service2;
	}
}
