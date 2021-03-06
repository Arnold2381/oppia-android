package org.oppia.android.app.help

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import org.oppia.android.R
import org.oppia.android.app.activity.ActivityScope
import org.oppia.android.app.drawer.NavigationDrawerFragment
import javax.inject.Inject

/** The presenter for [HelpActivity]. */
@ActivityScope
class HelpActivityPresenter @Inject constructor(private val activity: AppCompatActivity) {
  private lateinit var navigationDrawerFragment: NavigationDrawerFragment
  private lateinit var toolbar: Toolbar

  fun handleOnCreate(isFromNavigationDrawer: Boolean) {
    if (isFromNavigationDrawer) {
      activity.setContentView(R.layout.help_activity)
      setUpToolbar()
      setUpNavigationDrawer()
    } else {
      activity.setContentView(R.layout.help_without_drawer_activity)
      setUpToolbar()
      activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
      toolbar.setNavigationOnClickListener {
        activity.finish()
      }
    }
    if (getHelpFragment() == null) {
      activity.supportFragmentManager.beginTransaction().add(
        R.id.help_fragment_placeholder,
        HelpFragment()
      ).commitNow()
    }
  }

  private fun setUpToolbar() {
    toolbar = activity.findViewById<View>(R.id.help_activity_toolbar) as Toolbar
    activity.setSupportActionBar(toolbar)
  }

  private fun setUpNavigationDrawer() {
    activity.supportActionBar!!.setDisplayShowHomeEnabled(true)
    navigationDrawerFragment = activity
      .supportFragmentManager
      .findFragmentById(
        R.id.help_activity_fragment_navigation_drawer
      ) as NavigationDrawerFragment
    navigationDrawerFragment.setUpDrawer(
      activity.findViewById<View>(R.id.help_activity_drawer_layout) as DrawerLayout,
      toolbar, R.id.nav_help
    )
  }

  private fun getHelpFragment(): HelpFragment? {
    return activity
      .supportFragmentManager
      .findFragmentById(R.id.help_fragment_placeholder) as HelpFragment?
  }
}
