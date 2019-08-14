package som.sps.zmoto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import som.sps.zmoto.databinding.ActivityMainBinding
import som.sps.zmoto.ui.main.SectionsPagerAdapter
import som.sps.zmoto.viewmodel.CategoryViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryViewModel: CategoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding= DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        categoryViewModel= ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        binding.categoryViewModel=categoryViewModel
        binding.lifecycleOwner=this
        binding.executePendingBindings()
        addObservers()
        categoryViewModel.fetchCategories()
    }

    private fun addObservers() {
        categoryViewModel.categories.observe(this, Observer {
            it?.let {
                val sectionsPagerAdapter =  SectionsPagerAdapter(this, supportFragmentManager,
                    it
                )
                binding.viewPager.adapter = sectionsPagerAdapter
                binding.tabs.setupWithViewPager(binding.viewPager)
            }

        })
    }


}