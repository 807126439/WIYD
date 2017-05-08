package com.spr.core.utils;

import java.util.HashSet;
import java.util.Set;

public class CountSet<T> {
   
	
	private Set<T> one;
	private Set<T> two;
	private Set<T> share;
	

	public CountSet(Set<T> one, Set<T> two) {
		super();
		this.one = new HashSet<T>(one);
		this.two = new HashSet<T>(two);
	}

	/**
	 * 计算pass的余集
	 * @param pass
	 * @param near
	 * @return
	 */
	public static Set<Long> countComplementarySet(Set<Long> pass, Set<Long> near){
		Set<Long> temp = new HashSet<Long>(near); 
		temp.retainAll(pass); //计算交集
		pass.removeAll(temp); //计算pass的余集
		
		return pass;
	}
	
	


	/**
	 * 计算余集
	 * @return
	 */
	public void countComplementarySet(){
		share = new HashSet<T>(two); 
		share.retainAll(one); //计算交集
		two.removeAll(share); //得出two余集		
		one.removeAll(share); //得出one余集
		
	}




	public Set<T> getOne() {
		return one;
	}




	public void setOne(Set<T> one) {
		this.one = one;
	}




	public Set<T> getTwo() {
		return two;
	}




	public void setTwo(Set<T> two) {
		this.two = two;
	}

	public Set<T> getShare() {
		return share;
	}

	public void setShare(Set<T> share) {
		this.share = share;
	}
	
	
	
	
	
	
}
