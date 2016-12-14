package com.bluebird.comum.filtro;

import java.util.HashMap;
import java.util.Map;

public class Cond {

	public Map<EnumCond, String> operators = new HashMap<EnumCond, String>();

	private String col;
	private String param;
	private Object val;
	private String opSql;
	private EnumCond op;
	private Boolean subSelect;

	public Cond(String col, EnumCond op, Object value, String param, Boolean subSelect) {

		operators.put(EnumCond.GT, "> ");
		operators.put(EnumCond.GE, ">= ");
		operators.put(EnumCond.LT, "< ");
		operators.put(EnumCond.LE, "<= ");
		operators.put(EnumCond.EQ, "= ");
		operators.put(EnumCond.NE, "!= ");
		operators.put(EnumCond.IN, "IN ");
		operators.put(EnumCond.LIKE, "LIKE ");
		operators.put(EnumCond.UPPER, "UPPER ");

		this.col = col;
		this.val = value;
		this.param = param;
		this.opSql = operators.get(op);
		this.op = op;
		this.subSelect = subSelect;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}

	public String getOpSql() {
		return opSql;
	}

	public void setOpSql(String opSql) {
		this.opSql = opSql;
	}

	public EnumCond getOp() {
		return op;
	}

	public void setOp(EnumCond op) {
		this.op = op;
	}

	public Boolean getSubSelect() {
		return subSelect;
	}

	public void setSubSelect(Boolean subSelect) {
		this.subSelect = subSelect;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}
