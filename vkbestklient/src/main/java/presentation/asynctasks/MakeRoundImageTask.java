package presentation.asynctasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Pair;


public class MakeRoundImageTask extends AsyncTask<Pair<Context, Bitmap>, Void, Bitmap> {

    private Bitmap roundAvatar;

    public Bitmap getAvatarList() {
        return roundAvatar;
    }

    @Override
    protected Bitmap doInBackground(Pair<Context, Bitmap>... avatarResourceId) {

        Context context = avatarResourceId[0].first;
        roundAvatar = avatarResourceId[0].second;

        Bitmap avatar = null;
        // avatar = BitmapUtils.getCircleMaskedBitmapUsingShader(BitmapFactory.decodeResource(context.getResources(), avatarIdArray.get(avatarId)), 25);

        return avatar;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (isCancelled()) roundAvatar = result;

    }

    /*@Override
    protected List<Bitmap> doInBackground(Pair<Context, List>... avatarRecourcesIdArray) {

        Context context = avatarRecourcesIdArray[0].first;
        List<Integer> avatarIdArray = avatarRecourcesIdArray[0].second;
        List<Bitmap> avatarBitmapArray = new ArrayList<>();
            *//*for friendAvatars*//*
        Bitmap avatar;
        for (Integer avatarId : avatarIdArray) {

            avatar = BitmapUtils.getCircleMaskedBitmapUsingShader(BitmapFactory.decodeResource(context.getResources(), avatarIdArray.get(avatarId)), 25);
            avatarBitmapArray.add(avatar);
        }
        return avatarBitmapArray;

    }

    @Override
    protected void onPostExecute(List<Bitmap> result) {
        if (isCancelled()) avatarList = result;

    }*/

}


