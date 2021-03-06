package com.desafio.gerenciadordeconta.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.desafio.gerenciadordeconta.fragments.LoginFragment;
import com.desafio.gerenciadordeconta.models.ContaCorrente;
import com.example.gerenciadordeconta.R;

public class LoginActivity extends ActionBarActivity {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new LoginFragment()).commit();
		}

		criarContaCorrente();
	}

	private void criarContaCorrente() {
		
		SharedPreferences sharedPreferences = getSharedPreferences("Preference", MODE_PRIVATE);
		if (sharedPreferences.getBoolean(
				"keyConta", true)) {
			createContas();
		}
		
		SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("keyConta", false);
        editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.global, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_help) {
			Intent intent = new Intent(this, HelpActivity.class);
			this.startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
	}
	
	protected void onPause() {
        super.onPause();
        dismissKeyboard();
    }
	
	protected void createContas() {
		ContaCorrente contaCorrente1 = new ContaCorrente();
		contaCorrente1.setConta("11111");
		contaCorrente1.setSenha("1111");
		contaCorrente1.setVIP(true);
		contaCorrente1.setSaldo(1000.0F);
		contaCorrente1.save();
		
		ContaCorrente contaCorrente2 = new ContaCorrente();
		contaCorrente2.setConta("22222");
		contaCorrente2.setSenha("2222");
		contaCorrente2.setVIP(false);
		contaCorrente2.setSaldo(500.0F);
		contaCorrente2.save();
		
		ContaCorrente contaCorrente3 = new ContaCorrente();
		contaCorrente3.setConta("33333");
		contaCorrente3.setSenha("3333");
		contaCorrente3.setVIP(true);
		contaCorrente1.setSaldo(100.0F);
		contaCorrente3.save();
    }
	
	private void dismissKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View viewCurrentFocus = getCurrentFocus();
        if (viewCurrentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(viewCurrentFocus.getWindowToken(), 0);
        }
    }
}
