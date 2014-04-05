package com.desafio.gerenciadordeconta;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;

public class GerenciadorDeContaApplication extends Application {
	
	private static Context globalApplicationContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		ActiveAndroid.initialize(this);

		GerenciadorDeContaApplication.globalApplicationContext = getApplicationContext();
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();

		ActiveAndroid.dispose();
	}

	public static Context globalApplicationContext() {
		if (globalApplicationContext == null) {
			throw new IllegalStateException(
					"Application still was initialized. onCreate should be called before.");
		}

		return GerenciadorDeContaApplication.globalApplicationContext;
	}
}
