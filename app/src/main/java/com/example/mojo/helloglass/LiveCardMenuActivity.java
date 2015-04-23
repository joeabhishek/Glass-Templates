package com.example.mojo.helloglass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;


public class LiveCardMenuActivity extends Activity {

    private CardScrollView mCardScrollView;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = buildView();
        mCardScrollView =  new CardScrollView(this);

        mCardScrollView.setAdapter(new CardScrollAdapter() {
            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public Object getItem(int position) {
                return mView;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return mView;
            }

            @Override
            public int getPosition(Object item) {
                if (mView.equals(item)) {
                    return 0;
                }
                return AdapterView.INVALID_POSITION;
            }
        });

        mCardScrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openOptionsMenu();
            }
        });
        setContentView(mCardScrollView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_live_card_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the home_icon/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_about:
                // About menu item selected
                Intent intnet = new Intent("com.hmkcode.android.USER_ACTION");
                sendBroadcast(intnet);
                return true;
            case R.id.action_quit:
                // Quit menu item selected
                // we'll simply close the app
                LiveCardService liveCardService = new LiveCardService();
                liveCardService.mLiveCard.unpublish();
                liveCardService.stopSelf();
                finish();
                this.finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void openOptionsMenu() {
        super.openOptionsMenu();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //mCardScroller.activate();
        mCardScrollView.activate();
    }

    @Override
    protected void onPause() {
        //mCardScroller.deactivate();
        mCardScrollView.deactivate();
        super.onPause();
    }

    /**
     * Builds a Glass styled "Hello World!" view using the {@link com.google.android.glass.widget.CardBuilder} class.
     */
    private View buildView() {
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.TEXT);
        card.setText("Tap for options");
        return card.getView();
    }
}
