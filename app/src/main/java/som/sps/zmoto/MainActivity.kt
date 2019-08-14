package som.sps.zmoto

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import som.sps.zmoto.model.CategoriesResponse
import som.sps.zmoto.network.RetrofitClient
import som.sps.zmoto.ui.main.SectionsPagerAdapter
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
                RetrofitClient.getZomotoService().getCategories().enqueue(object :
        Callback<CategoriesResponse> {
                    override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                        Timber.e(t)
                    }

                    override fun onResponse(
                        call: Call<CategoriesResponse>,
                        response: Response<CategoriesResponse>
                    ) {
                        Timber.d("${response.body()}")
                    }
                }
                )

        }
    }
}