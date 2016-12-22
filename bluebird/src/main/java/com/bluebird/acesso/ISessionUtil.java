package com.bluebird.acesso;

public abstract interface ISessionUtil {
	public abstract void set(String paramString, Object paramObject, int paramInt);

	public abstract Object get(String paramString, int paramInt);

	public abstract void remove(String paramString, int paramInt);
}