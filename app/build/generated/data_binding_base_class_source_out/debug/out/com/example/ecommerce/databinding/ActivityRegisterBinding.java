// Generated by view binder compiler. Do not edit!
package com.example.ecommerce.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ecommerce.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText addressText;

  @NonNull
  public final ImageView appLogo;

  @NonNull
  public final EditText emailText;

  @NonNull
  public final EditText nameText;

  @NonNull
  public final EditText password;

  @NonNull
  public final ProgressBar progressbar;

  @NonNull
  public final Button registerBtn;

  private ActivityRegisterBinding(@NonNull RelativeLayout rootView, @NonNull EditText addressText,
      @NonNull ImageView appLogo, @NonNull EditText emailText, @NonNull EditText nameText,
      @NonNull EditText password, @NonNull ProgressBar progressbar, @NonNull Button registerBtn) {
    this.rootView = rootView;
    this.addressText = addressText;
    this.appLogo = appLogo;
    this.emailText = emailText;
    this.nameText = nameText;
    this.password = password;
    this.progressbar = progressbar;
    this.registerBtn = registerBtn;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address_text;
      EditText addressText = ViewBindings.findChildViewById(rootView, id);
      if (addressText == null) {
        break missingId;
      }

      id = R.id.app_logo;
      ImageView appLogo = ViewBindings.findChildViewById(rootView, id);
      if (appLogo == null) {
        break missingId;
      }

      id = R.id.email_text;
      EditText emailText = ViewBindings.findChildViewById(rootView, id);
      if (emailText == null) {
        break missingId;
      }

      id = R.id.name_text;
      EditText nameText = ViewBindings.findChildViewById(rootView, id);
      if (nameText == null) {
        break missingId;
      }

      id = R.id.password;
      EditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.progressbar;
      ProgressBar progressbar = ViewBindings.findChildViewById(rootView, id);
      if (progressbar == null) {
        break missingId;
      }

      id = R.id.register_btn;
      Button registerBtn = ViewBindings.findChildViewById(rootView, id);
      if (registerBtn == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((RelativeLayout) rootView, addressText, appLogo, emailText,
          nameText, password, progressbar, registerBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
