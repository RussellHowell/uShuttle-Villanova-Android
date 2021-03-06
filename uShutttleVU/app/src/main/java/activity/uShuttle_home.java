package activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import russellhowell.ushuttle.R;


public class uShuttle_home extends ActionBarActivity implements FragmentDrawer.FragmentDrawerListener  {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_shuttle_home);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);



        //Display the home fragment by default
        displayView(-1);

       //mToolbar.getBackground().setAlpha(0);



    }

    public void openNavDrawer(){
       drawerFragment.openNav();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_u_shuttle_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position){
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch(position){
            case -1: //this case is only achieved on opening of the app
                fragment = new HomeFragment(this);
                title = getString(R.string.title_home);
                break;
            case 0:
                fragment = new OnCampusFragment(this);
               // title = getString(R.string.title_on_campus);
                break;
            case 1:
                fragment = new MessagesFragment();
                break;
            default:
                break;
        }

        if (fragment !=null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            //set  the toolbar label
            getSupportActionBar().setTitle(title);
        }

    }





}