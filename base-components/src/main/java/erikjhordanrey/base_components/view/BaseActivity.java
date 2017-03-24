/*
 * Copyright (C) 2017 Erik Jhordan Rey.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package erikjhordanrey.base_components.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import erikjhordanrey.base_components.R;

import static erikjhordanrey.base_components.utils.StringsUtils.EXTRA_ARGUMENTS;

public abstract class BaseActivity extends AppCompatActivity {

  private Toolbar toolbar;
  private Bundle activityArguments;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());
    initializeSupportActionBar();
    initializeArguments(savedInstanceState);
    initializePresenter();
    initializeActivity();
  }

  /**
   * Toolbar will be configured like a {@link ActionBar} if exists in the layout
   * if it doesn't exist will be ignored
   */

  private void initializeSupportActionBar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      onSetupSupportActionBar(getSupportActionBar());
    }
  }

  /**
   * Called just after bindViews.
   * Override this method to configure your {@link ActionBar}
   */

  protected void onSetupSupportActionBar(ActionBar actionBar) {

  }

  /**
   * Called after to initialize ui state.
   * Override this method to configure your presenter with extra data if needed.
   */

  protected void initializeArguments(Bundle savedInstanceState) {
    if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_ARGUMENTS)) {
      activityArguments = savedInstanceState.getBundle(EXTRA_ARGUMENTS);
    } else if (getIntent().getExtras() != null) {
      activityArguments = getIntent().getExtras().getBundle(EXTRA_ARGUMENTS);
    }
  }

  /**
   * Called before to initialize all the presenter instances linked to the component lifecycle.
   * Override this method to configure your presenter with extra data if needed.
   */

  protected void initializePresenter() {

  }

  /**
   * Called just after setContentView.
   * Override this method to configure your activity or initialize views
   */

  protected void initializeActivity() {

  }

  /**
   * @return Toolbar if you need configure directly the toolbar
   */

  @Nullable protected Toolbar getBaseToolbar() {
    return toolbar;
  }

  /**
   * @return the layout id associated to the layout used in the activity.
   */

  @LayoutRes protected abstract int getLayoutResId();

  public Bundle getActivityArguments() {
    return activityArguments;
  }
}