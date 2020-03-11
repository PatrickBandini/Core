package patrick.core.view.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import patrick.core.R;

public class Message {

    private Activity activity;
    private AlertDialog.Builder builder;
    private Dialog dialog;

    private String message;

    public Message(Activity activity, String message) {
        this.activity = activity;
        this.message = message;
        createBuilder();
    }

    private void createBuilder() {
        this.builder = new AlertDialog.Builder(activity);
        builder.setMessage(message).setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        this.dialog = builder.create();
        this.dialog.setCancelable(false);
    }

    public void show() {
        this.dialog.show();
    }
}
