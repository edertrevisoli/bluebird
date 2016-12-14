package com.bluebird.comum.filtro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WhereOrderParams {
	Map<Boolean, List<Cond>> mapCond = new HashMap<Boolean, List<Cond>>();
	List<Cond> condList = new ArrayList<Cond>();
	List<String> orderByList = new ArrayList<String>();
	private String sortOrder = null;

	public void addCod(String col, EnumCond op, Object value, String param, Boolean subSelect) {

		Cond cond = new Cond(col, op, value, param, subSelect);

		if (!mapCond.containsKey(subSelect)) {
			List<Cond> condList = new ArrayList<Cond>();
			condList.add(cond);
			mapCond.put(subSelect, condList);
		} else {
			List<Cond> list = mapCond.get(subSelect);
			list.add(cond);
		}
	}

	public void addOrder(String[] col, String flag) {
		if(sortOrder == null)
		this.setSortOrder(flag);
		
		List<String> orders = Arrays.asList(col);
		if(orders != null && !orders.isEmpty()){
			orderByList.addAll(orders);
		}
	}

	public Map<Boolean, List<Cond>> getMapCond() {
		return mapCond;
	}

	public void setMapCond(Map<Boolean, List<Cond>> mapCond) {
		this.mapCond = mapCond;
	}

	public List<String> getOrderByList() {
		return orderByList;
	}

	public void setOrderByList(List<String> orderByList) {
		this.orderByList = orderByList;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}
