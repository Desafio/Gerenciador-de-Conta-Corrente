package com.desafio.gerenciadordeconta.activitys;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.desafio.gerenciadordeconta.fragments.HelpFragment;
import com.example.gerenciadordeconta.R;

public class HelpActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new HelpFragment()).commit();
		}
	}
}
