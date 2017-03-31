package com.wb.core.utils;

import java.util.HashSet;
import java.util.Set;

public class CountSet {
   
	
	private Set<Long> one;
	private Set<Long> two;
	
	

	public CountSet(Set<Long> one, Set<Long> two) {
		super();
		this.one = one;
		this.two = two;
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
		Set<Long> temp = new HashSet<Long>(two); 
		temp.retainAll(one); //计算交集
		two.removeAll(temp); //得出origin余集		
		one.removeAll(temp); //得出sample余集
		
	}




	public Set<Long> getOne() {
		return one;
	}




	public void setOne(Set<Long> one) {
		this.one = one;
	}




	public Set<Long> getTwo() {
		return two;
	}




	public void setTwo(Set<Long> two) {
		this.two = two;
	}
	
	
	
	
	
	
}
