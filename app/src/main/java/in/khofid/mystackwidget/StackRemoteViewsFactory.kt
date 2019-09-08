package `in`.khofid.mystackwidget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle


class StackRemoteViewsFactory(var context: Context): RemoteViewsService.RemoteViewsFactory {

    private var mWidgetItems: ArrayList<Bitmap> = arrayListOf()


    override fun onCreate() {

    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(p0: Int): Long = 0

    override fun onDataSetChanged() {
        mWidgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.darth_vader))
        mWidgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.star_wars_logo))
        mWidgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.storm_trooper))
        mWidgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.starwars))
        mWidgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.falcon))
    }

    override fun hasStableIds(): Boolean = false

    override fun getViewAt(position: Int): RemoteViews {
        var rv = RemoteViews(context.packageName, R.layout.widget_item)
        rv.setImageViewBitmap(R.id.imageView, mWidgetItems.get(position))

        val extras = Bundle()
        extras.putInt(ImageBannerWidget.EXTRA_ITEM, position)
        var fillIntent = Intent()
        fillIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.imageView, fillIntent)
        return rv
    }

    override fun getCount(): Int = mWidgetItems.count()

    override fun getViewTypeCount(): Int = 1

    override fun onDestroy() {
    }
}