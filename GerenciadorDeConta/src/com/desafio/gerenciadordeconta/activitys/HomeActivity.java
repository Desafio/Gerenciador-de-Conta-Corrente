package com.desafio.gerenciadordeconta.activitys;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.activeandroid.query.Select;
import com.desafio.gerenciadordeconta.fragments.DepositoFragment;
import com.desafio.gerenciadordeconta.fragments.ExtratoFragment;
import com.desafio.gerenciadordeconta.fragments.GerenteFragment;
import com.desafio.gerenciadordeconta.fragments.NavigationDrawerFragment;
import com.desafio.gerenciadordeconta.fragments.SairFragment;
import com.desafio.gerenciadordeconta.fragments.SaldoFragment;
import com.desafio.gerenciadordeconta.fragments.SaqueFragment;
import com.desafio.gerenciadordeconta.fragments.TransferenciaFragment;
import com.desafio.gerenciadordeconta.models.ContaCorrente;
import com.example.gerenciadordeconta.R;

public class HomeActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;

	ContaCorrente contaCorrente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		MyTimerTask myTask = new MyTimerTask();
		Timer myTimer = new Timer();
		myTimer.schedule(myTask, 0, 60000);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		position++;
		switch (position) {
		case 1:
			fragmentManager.beginTransaction()
					.replace(R.id.container, SaldoFragment.newInstance())
					.commit();
			break;
		case 2:
			fragmentManager.beginTransaction()
					.replace(R.id.container, ExtratoFragment.newInstance())
					.commit();
			break;
		case 3:
			fragmentManager.beginTransaction()
					.replace(R.id.container, SaqueFragment.newInstance())
					.commit();
			break;
		case 4:
			fragmentManager.beginTransaction()
					.replace(R.id.container, DepositoFragment.newInstance())
					.commit();
			break;
		case 5:
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							TransferenciaFragment.newInstance()).commit();
			break;
		case 6:
			if (!contaCorrente.getVIP()) {
				fragmentManager.beginTransaction()
						.replace(R.id.container, SairFragment.newInstance())
						.commit();
				break;
			}
			fragmentManager.beginTransaction()
					.replace(R.id.container, GerenteFragment.newInstance())
					.commit();
			break;
		case 7:
			fragmentManager.beginTransaction()
					.replace(R.id.container, SairFragment.newInstance())
					.commit();
			break;
		}
		onSectionAttached(position);
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_saldo);
			break;
		case 2:
			mTitle = getString(R.string.title_extrato);
			break;
		case 3:
			mTitle = getString(R.string.title_saque);
			break;
		case 4:
			mTitle = getString(R.string.title_deposito);
			break;
		case 5:
			mTitle = getString(R.string.title_transferencia);
			break;
		case 6:
			if (!contaCorrente.getVIP()) {
				mTitle = getString(R.string.title_sair);
				break;
			}
			mTitle = getString(R.string.title_gerente);
			break;
		case 7:
			mTitle = getString(R.string.title_sair);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			getMenuInflater().inflate(R.menu.global, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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

	class MyTimerTask extends TimerTask {
		public void run() {
			String idConta = getIntent().getStringExtra("idConta");

			contaCorrente = new Select().from(ContaCorrente.class)
					.where("id = ?", idConta).executeSingle();
			
			if (contaCorrente.getSaldo() < 0) {
				double novoSaldo = contaCorrente.getSaldo() * 1.01;
				contaCorrente
						.setSaldo(Float.valueOf(Double.toString(novoSaldo)));
				contaCorrente.save();
				
			}
		}
	}
}
